/*
 *  Copyright (c) 2015 The WebRTC project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a BSD-style license
 *  that can be found in the LICENSE file in the root of the source
 *  tree.
 */

'use strict';
var sendChannel, receiveChannel;
var mediaStream;
var localStream, lpc, rpc;
var canvasWidth, canvasHeight;
var recordVideoSeparately = !!navigator.webkitGetUserMedia;
if (!!navigator.webkitGetUserMedia && !recordVideoSeparately) {
        var cameraPreview = document.getElementById('camera-preview');
        cameraPreview.parentNode.innerHTML = '<audio id="camera-preview" controls style="border: 1px solid rgb(15, 158, 238); width:98%;"></audio> ';
}

var video = document.getElementsByTagName('video')[0];
var localVideo = document.getElementById("local-video");
var remoteVideo = document.getElementById("remote-video");

var photo = document.getElementById('photo');
var recordAudio, recordVideo;
var canvas = photo.getContext('2d');

var callButton = document.getElementById("call");
var snapButton = document.getElementById("snap");
var sendButton = document.getElementById("send");
var recdButton = document.getElementById("recd");
var isInitiator;
var room = window.location.hash.substring(1);
if (!room) {
    room = window.location.hash = randomToken();
}

var isCalling = false,isRecording = false;

snapButton.addEventListener('click', snapPhoto);
sendButton.addEventListener('click', sendPhoto);
callButton.addEventListener('click', callOrHangup);
recdButton.addEventListener('click',recordOrStop);
//snapAndSendBtn.addEventListener('click', snapAndSend);
video.addEventListener('play', setCanvasDimensions);

function randomToken() {
    return Math.floor((1 + Math.random()) * 1e16).toString(16).substring(1);
}

var params  = {'reconnection delay' : 2000,'force new connection' : true};
var socket = io.connect('http://'+location.hostname + ':7851',params);
/*if (room !== '') {
  console.log('Create or join room', room);
  socket.emit('create or join', room);
}*/

//to make call
//send a dial to user who is online identified by senssionid/
//server sends incomming to sessionid client
//sessionid client sends accepts sessionid
//server joins remoteclient to requsting client room;
//sever sends client joined to requestor
//requestor mplaces peerconnection for video chat by sending offer
//remoteckient accept offer

var userName = 'user' + Math.floor((Math.random()*1000)+1);
var localClientId;
socket.on('connect',function(){
	console.log('info: connected to socket.io server');
	console.log('info: authenticating...');
	socket.emit('authentication','user='+userName+',room='+room);
});

socket.on('authenticated', function (clientId) {
  console.log('info: authenticated. sessionid - ',clientId);
  localClientId = clientId;
  initRTC("local-video","remote-video",socket);
});

socket.on('online', function (user, clientId){

  if(clientId != localClientId){
	  console.log('info: '+user+' is online. client Id '+clientId);
	  $.notify({
		icon: 'https://randomuser.me/api/portraits/med/men/77.jpg',
		title: 'Becki Esin',
		message: user+' is now online.'},{
		type: 'minimalist',
		delay: 5000,
		icon_type: 'image',
		template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">' +
			'<img data-notify="icon" class="img-circle pull-left">' +
			'<span data-notify="title">{1}</span>' +
			'<span data-notify="message">{2}</span>' +
		'</div>'
	   });

		document.getElementById("msg").value=clientId;
	}
});

socket.on('incomming',function(clientid,iroom){

	isInitiator = false;
	console.log('info: incomming call from '+clientid);
	createPeerConnection(isInitiator,localClientId,clientid);
    socket.emit('accept','{client='+clientid+',room='+iroom+'}');
});

socket.on('joined',function(clientid){

	isInitiator = true;
	console.log('info: client '+clientid+' accepted call');
    createPeerConnection(isInitiator,localClientId,clientid);
});

socket.on('log', function (array) {
  console.log.apply(console, array);
});

/*if (location.hostname.match(/localhost|127\.0\.0/)) {
    socket.emit('ipaddr');
}*/

/**
 * Updates URL on the page so that users can copy&paste it to their peers.
 */
function updateRoomURL(ipaddr) {
    var url;
    if (!ipaddr) {
        url = location.href;
    } else {
        url = location.protocol + '//' + ipaddr + ':1337/socket.io/#' + room;
    }
    roomURL.innerHTML = url;
}

function callOrHangup(){

	if(isCalling === false){

		isCalling = true;
		callButton.innerHTML='<i class="fa fa-dot-circle-o"></i>&nbsp;Hangup';
	    callButton.className='btn btn-danger';
	    isInitiator = true;

	    console.log('info: calling '+document.getElementById("msg"));
	    socket.emit('dial','{client='+document.getElementById("msg").value+',room='+room+'}');

	}else{

		isCalling = false;
		callButton.innerHTML='<i class="fa fa-phone"></i>&nbsp;Call';
		callButton.className='btn btn-success';
		hangup();

	}
}

function recordOrStop(){

    if(isRecording === false){

		isRecording = true;
		recdButton.innerHTML='<i class="fa fa-circle" style="color:red;"></i>&nbsp;Stop';
		recordVideo();

	}else{

		isRecording = false;
		recdButton.innerHTML='<i class="fa fa-circle" style="color:green;"></i>&nbsp;Record';
		recordEnd();

	}
}

function snapPhoto() {

    console.log('info: snapping photo..');
    canvas.drawImage(video, 0, 0, canvasWidth, canvasHeight);
    show(photo, sendButton);
}

function sendPhoto() {
    // Split data channel message in chunks of this byte length.
    var CHUNK_LEN = 64000;

    var img = canvas.getImageData(0, 0, canvasWidth, canvasHeight),
        len = img.data.byteLength,
        n = len / CHUNK_LEN | 0;

    var notify = $.notify('<strong>Sending</strong>Sending a total of ' + len + ' byte(s)', {
		allow_dismiss: false,
		showProgressbar: true
    });
    console.log('info: Sending a total of ' + len + ' byte(s)');
    dataChannel.send(len);

    // split the photo and send in chunks of about 64KB
    for (var i = 0; i < n; i++) {
        var start = i * CHUNK_LEN,
            end = (i+1) * CHUNK_LEN;
        console.log(start + ' - ' + (end-1));
        notify.update('message', '<strong>Sending</strong>'+start + ' - ' + (end-1));
        dataChannel.send(img.data.subarray(start, end));
    }

    // send the reminder, if any
    if (len % CHUNK_LEN) {
        console.log('last ' + len % CHUNK_LEN + ' byte(s)');
        notify.update('message', '<strong>Sending</strong> last ' + len % CHUNK_LEN + ' byte(s)');
        dataChannel.send(img.data.subarray(n * CHUNK_LEN));
    }
}

function snapAndSend() {
    snapPhoto();
    sendPhoto();
}

function renderPhoto(data) {

    photo.classList.add('photo');
    trail.insertBefore(photo, trail.firstChild);

    var canvas = photo.getContext('2d');
    img = canvas.createImageData(300, 150);
    img.data.set(data);
    canvas.putImageData(img, 0, 0);
}

function setCanvasDimensions() {
    if (video.videoWidth == 0) {
        setTimeout(setCanvasDimensions, 200);
        return;
    }

    console.log('info: video width:', video.videoWidth, 'height:', video.videoHeight)

    canvasWidth = video.videoWidth / 2;
    canvasHeight = video.videoHeight / 2;
    //photo.style.width = canvasWidth + 'px';
    //photo.style.height = canvasHeight + 'px';
    // TODO: figure out right dimensions
    canvasWidth = 300; //300;
    canvasHeight = 150; //150;
}

function show() {
    Array.prototype.forEach.call(arguments, function(elem){
        elem.style.display = null;
    });
}

function hide() {
    Array.prototype.forEach.call(arguments, function(elem){
        elem.style.display = 'none';
    });
}

function recordVideo() {

	navigator.getUserMedia({audio: true,video: true}, function(stream) {
		mediaStream = stream;
		recordAudio = RecordRTC(stream, {
			onAudioProcessStarted: function() {
				recordVideoSeparately && recordVideo.startRecording();
				cameraPreview.src = window.URL.createObjectURL(stream);
				cameraPreview.play();
				cameraPreview.muted = true;
				cameraPreview.controls = false;
			}
		});
		recordVideo = RecordRTC(stream, {type: 'video'});
		recordAudio.startRecording();
	}, function(error) {
		alert(JSON.stringify(error));
	});
}

function recordEnd(){

        // stop audio recorder
        recordVideoSeparately && recordAudio.stopRecording(function() {
            // stop video recorder
            recordVideo.stopRecording(function() {
                // get audio data-URL
                recordAudio.getDataURL(function(audioDataURL) {
                    // get video data-URL
                    recordVideo.getDataURL(function(videoDataURL) {
                        var files = {
                            audio: {
                                type: recordAudio.getBlob().type || 'audio/wav',
                                dataURL: audioDataURL
                            },
                            video: {
                                type: recordVideo.getBlob().type || 'video/webm',
                                dataURL: videoDataURL
                            }
                        };
                        socket.emit('video-data', files);
                        if (mediaStream) mediaStream.stop();
                    });
                });
                cameraPreview.src = '';
                cameraPreview.poster = 'ajax-loader.gif';
            });
        });
        // if firefox or if you want to record only audio
        // stop audio recorder
        !recordVideoSeparately && recordAudio.stopRecording(function() {
            // get audio data-URL
            recordAudio.getDataURL(function(audioDataURL) {
                var files = {
                    audio: {
                        type: recordAudio.getBlob().type || 'audio/wav',
                        dataURL: audioDataURL
                    }
                };
                socket.emit('video-data', files);
                if (mediaStream) mediaStream.stop();
            });
            cameraPreview.src = '';
            cameraPreview.poster = 'ajax-loader.gif';
        });
    };

socket.on('merged', function(fileName) {

	var href = (location.href.split('/').pop().length ? location.href.replace(location.href.split('/').pop(), '') : location.href);
	href = href + '/uploads/' + fileName;
	console.log('got file ' + href);
	cameraPreview.src = href
	cameraPreview.play();
	cameraPreview.muted = false;
	cameraPreview.controls = true;
});

socket.on('ffmpeg-output', function(result) {
	if (parseInt(result) >= 100) {
		progressBar.parentNode.style.display = 'none';
		return;
	}
	progressBar.parentNode.style.display = 'block';
	progressBar.value = result;
	percentage.innerHTML = 'Ffmpeg Progress ' + result + "%";
});

socket.on('ffmpeg-error', function(error) {
	alert(error);
});