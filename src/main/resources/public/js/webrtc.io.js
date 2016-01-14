'use strict';

var isChannelReady;
var isInitiator = false,isStarted = false,turnReady;
var pc;
var dataChannel;
var localStream,remoteStream;
var localVideo,remoteVideo;
var socket;
var localSessionId,remoteSessionId;
var pc_config = {'iceServers': [{'url': 'stun:stun.services.mozilla.com'}, {'url': 'stun:stun.l.google.com:19302'}]};
var pc_constraints = {'optional': [{'DtlsSrtpKeyAgreement': true}]};

// Set up audio and video regardless of what devices are present.
var sdpConstraints = {'mandatory': {'OfferToReceiveAudio':true,'OfferToReceiveVideo':true }};

function escapeJson(str)  {
    return str.replace(/\n/g, "\\\\n").replace(/\r/g, "\\\\r").replace(/\t/g, "\\\\t");
}

function escapeJsonNoFormat(str)  {
    return str.replace(/\n/g, "").replace(/\r/g, " ").replace(/\t/g, " ");
}

function sendMessage(signal){

    socket.emit('video-message',{to:remoteSessionId,data:signal});
}

function initRTC(local_video_id,remote_video_id,socket_io){

	localVideo = document.getElementById(local_video_id);
	remoteVideo = document.getElementById(remote_video_id);

	socket = socket_io;
	socket.on('video-message', function (signal){

		console.log('recv message: ', signal);
		if (signal.data.type === 'offer') {

			console.log('info: got an offer. sending answer to peer..');
			pc.setRemoteDescription(new RTCSessionDescription(signal.data), function(){}, warn);
            pc.createAnswer(onLocalSessionCreated, warn);

		}else if (signal.data.type === 'answer') {

			console.log('info: received answer to offer. answering call.'+signal);
			pc.setRemoteDescription(new RTCSessionDescription(signal.data));

		}else if (signal.data.type === 'candidate') {

			var candidate = new RTCIceCandidate({sdpMLineIndex: signal.data.label,candidate: signal.data.candidate});
			pc.addIceCandidate(candidate);

		} else if (msg.type === 'quit') {
		 	 handleRemoteHangup();
		}
	});
}

function onUserMediaReady(stream) {

  console.log('info: adding local stream.');
  localVideo.src = window.URL.createObjectURL(stream);
  localStream = stream;

}

function onUserMediaError(error){
  console.log('error: getUserMedia: '+error);
}

var constraints = {video: true};
getUserMedia(constraints, onUserMediaReady, onUserMediaError);
console.log('info: getting user media with constraints'+ constraints);
/*if (location.hostname != "localhost" || location.hostname != "127.0.0.1") {
  requestTurn('https://computeengineondemand.appspot.com/turn?username=41784574&key=4080218913');
}*/

window.onbeforeunload = function(e){
	sendMessage({type:'quit',text:'bye'});
}

function warn(e){
	console.log(e.toString(), e);
}

function createPeerConnection(initiator,localclientId,clientid) {

  isInitiator = initiator;
  localSessionId = localclientId;
  remoteSessionId = clientid;
  console.log('info: creating peer connection between Host '+localSessionId+' and remote '+remoteSessionId);
  try {

    pc = new RTCPeerConnection(pc_config);
    pc.onicecandidate = onIceCandidateFound;
    pc.onaddstream = onRemoteStreamAdded;
    pc.onremovestream = onRemoteStreamRemoved;
    pc.addStream(localStream);

    console.log('info: created RTCPeerConnnection');

  } catch (e) {
    console.log('error: Failed to create PeerConnection - ' + e.message);
    alert('Cannot create RTCPeerConnection object.\n'+e);
      return;
  }
  if(isInitiator) {

          console.log('info: creating Data Channel');
          dataChannel = pc.createDataChannel("photos");
          onDataChannelCreated(dataChannel);

          console.log('info: creating an offer');
          pc.createOffer(onLocalSessionCreated, warn);

      } else {

          pc.ondatachannel = function (event) {

              console.log('info: ondatachannel:'+JSON.stringify(event.channel));
              dataChannel = event.channel;
              onDataChannelCreated(dataChannel);
          };
    }
}

function onIceCandidateFound(event) {
  console.log('info: onIceCandidateFound event: ', event);
  console.log('info: sending candidate data to remote user '+remoteSessionId);
  if (event.candidate) {
    sendMessage({
      type: 'candidate',
      label: event.candidate.sdpMLineIndex,
      id: event.candidate.sdpMid,
      candidate: event.candidate.candidate,
      text:'ice candidate request'});
  } else {
    console.log('info: End of candidates.');
  }
}

function onLocalSessionCreated(desc) {

    console.log('info: local session created:');
    pc.setLocalDescription(desc, function () {
        console.log('info: sent local desc: '+JSON.stringify(desc));
        sendMessage(desc);
    }, warn);
}

function onDataChannelCreated(channel) {

    console.log('info: onDataChannelCreated:', channel);
    channel.onopen = function () {
        console.log('info: CHANNEL opened!!!');
    };

    channel.onmessage = (webrtcDetectedBrowser == 'firefox') ?
        receiveDataFirefoxFactory():receiveDataChromeFactory();
}

function onRemoteStreamAdded(event) {
  console.log('info: remote stream added.');
  remoteVideo.src = window.URL.createObjectURL(event.stream);
  remoteStream = event.stream;
}

function handleCreateOfferError(event){
  console.log('error :could not create offer: '+JSON.stringify(event));
}

function doCall() {
  console.log('info Sending offer to peer');
  pc.createOffer(setLocalAndSendMessage, handleCreateOfferError);
}

function doAnswer() {
  console.log('info: creating answer to peer.');
  pc.createAnswer(setLocalAndSendMessage, null, sdpConstraints);
}

function setLocalAndSendMessage(sessionDescription) {
  // Set Opus as the preferred codec in SDP if Opus is present.
  sessionDescription.sdp = preferOpus(sessionDescription.sdp);
  pc.setLocalDescription(sessionDescription);
  console.log('info: setLocalAndSendMessage sessionDescription' , +JSON.stringify(sessionDescription));
  sendMessage({'sdp':sessionDescription});
}

function onRemoteStreamAdded(event) {
  console.log('info: emote stream added.');
  remoteVideo.src = window.URL.createObjectURL(event.stream);
  remoteStream = event.stream;
}

function onRemoteStreamRemoved(event) {
  console.log('info: remote stream removed. Event: ', event);
}

function hangup() {
  console.log('info: Hanging up.');
  stop();
  sendMessage({text:'bye'});
}

function handleRemoteHangup() {
//  console.log('Session terminated.');
  // stop();
  isInitiator = false;
}

function stop() {
  isStarted = false;
  // isAudioMuted = false;
  // isVideoMuted = false;
  pc.close();
  pc = null;
}

///////////////////////////////////////////

// Set Opus as the default audio codec if it's present.
function preferOpus(sdp) {
  var sdpLines = sdp.split('\r\n');
  var mLineIndex;
  // Search for m line.
  for (var i = 0; i < sdpLines.length; i++) {
      if (sdpLines[i].search('m=audio') !== -1) {
        mLineIndex = i;
        break;
      }
  }
  if (mLineIndex === null) {
    return sdp;
  }

  // If Opus is available, set it as the default in m line.
  for (i = 0; i < sdpLines.length; i++) {
    if (sdpLines[i].search('opus/48000') !== -1) {
      var opusPayload = extractSdp(sdpLines[i], /:(\d+) opus\/48000/i);
      if (opusPayload) {
        sdpLines[mLineIndex] = setDefaultCodec(sdpLines[mLineIndex], opusPayload);
      }
      break;
    }
  }

  // Remove CN in m line and sdp.
  sdpLines = removeCN(sdpLines, mLineIndex);
  sdp = sdpLines.join('\r\n');
  return sdp;
}

function extractSdp(sdpLine, pattern) {
  var result = sdpLine.match(pattern);
  return result && result.length === 2 ? result[1] : null;
}

// Set the selected codec to the first in m line.
function setDefaultCodec(mLine, payload) {
  var elements = mLine.split(' ');
  var newLine = [];
  var index = 0;
  for (var i = 0; i < elements.length; i++) {
    if (index === 3) { // Format of media starts from the fourth.
      newLine[index++] = payload; // Put target payload to the first.
    }
    if (elements[i] !== payload) {
      newLine[index++] = elements[i];
    }
  }
  return newLine.join(' ');
}

// Strip CN from sdp before CN constraints is ready.
function removeCN(sdpLines, mLineIndex) {
  var mLineElements = sdpLines[mLineIndex].split(' ');
  // Scan from end for the convenience of removing an item.
  for (var i = sdpLines.length-1; i >= 0; i--) {
    var payload = extractSdp(sdpLines[i], /a=rtpmap:(\d+) CN\/\d+/i);
    if (payload) {
      var cnPos = mLineElements.indexOf(payload);
      if (cnPos !== -1) {
        // Remove CN payload from m line.
        mLineElements.splice(cnPos, 1);
      }
      // Remove CN line in sdp
      sdpLines.splice(i, 1);
    }
  }

  sdpLines[mLineIndex] = mLineElements.join(' ');
  return sdpLines;
}

function receiveDataChromeFactory() {
    var buf, count;

    return function onmessage(event) {
        if (typeof event.data === 'string') {
            buf = window.buf = new Uint8ClampedArray(parseInt(event.data));
            count = 0;
            console.log('Expecting a total of ' + buf.byteLength + ' bytes');
            return;
        }

        var data = new Uint8ClampedArray(event.data);
        buf.set(data, count);

        count += data.byteLength;
        console.log('count: ' + count);

        if (count == buf.byteLength) {
            // we're done: all data chunks have been received
            console.log('Done. Rendering photo.');
            renderPhoto(buf);
        }
    }
}

function receiveDataFirefoxFactory() {

    var count, total, parts;

    return function onmessage(event) {
        if (typeof event.data === 'string') {
            total = parseInt(event.data);
            parts = [];
            count = 0;
            console.log('info: Expecting a total of ' + total + ' bytes');
            return;
        }

        parts.push(event.data);
        count += event.data.size;
        console.log('Got ' + event.data.size + ' byte(s), ' + (total - count) + ' to go.');

        if (count == total) {
            console.log('info: Assembling payload')
            var buf = new Uint8ClampedArray(total);
            var compose = function(i, pos) {
                var reader = new FileReader();
                reader.onload = function() {
                    buf.set(new Uint8ClampedArray(this.result), pos);
                    if (i + 1 == parts.length) {
                        console.log('Done. Rendering photo.');
                        renderPhoto(buf);
                    } else {
                        compose(i + 1, pos + this.result.byteLength);
                    }
                };
                reader.readAsArrayBuffer(parts[i]);
            }
            compose(0, 0);
        }
    }
}

function requestTurn(turn_url) {
  var turnExists = false;
  for (var i in pc_config.iceServers) {
    if (pc_config.iceServers[i].url.substr(0, 5) === 'turn:') {
      turnExists = true;
      turnReady = true;
      break;
    }
  }
  if (!turnExists) {
    console.log('info: getting TURN server from ', turn_url);
    // No TURN server. Get one from computeengineondemand.appspot.com:
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
      if (xhr.readyState === 4 && xhr.status === 200) {
        var turnServer = JSON.parse(xhr.responseText);
      	console.log('info: Got TURN server: ', turnServer);
        pc_config.iceServers.push({
          'url': 'turn:' + turnServer.username + '@' + turnServer.turn,
          'credential': turnServer.password
        });
        turnReady = true;
      }
    };
    xhr.open('GET', turn_url, true);
    xhr.send();
  }
}

