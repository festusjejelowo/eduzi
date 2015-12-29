var connection;

 function startwebsocket(){

	//Establish the WebSocket connection and set up event handlers
	var url = "ws://" + location.hostname + ":" + location.port + "/chat/";
	connection = new WebSocket(url);
	connection.onopen = function() {
	    connection.send('Authenticate^x500:Festus:om543azx');
	    console.log("connected to websocket @ "+url);
    };

    // Log errors
	connection.onerror = function (error) {
	  console.log('WebSocket Error ' + error);
   };

	connection.onmessage = function (event) {

		console.log('messaged received');
		fireMessageReceived(event);
	};

	connection.onclose = function () { alert("WebSocket connection closed") };
}

function sendMessage(message) {
	if (message !== "") {
		connection.send(message);
		$('#message').val('');
	}
}

//Update the chat-panel, and the list of connected users
function fireMessageReceived(event) {

   var obj;

	try{

		obj = JSON.parse(event.data);
		if(obj.group == 'feed'){
			pushFeed(obj.data);
		}else{
			pushChat(obj.data);
		}
	}catch(err){
		if (window.console && window.console.log ) {
		  console.log("[eduzi] "+err)
		  console.log(event.data)
		}
	}
}

function pushFeed(feeds){

	var content = "";
	$.each(feeds, function(idx, f) {

		content+="<div class=\"item\">"+
		"   <p style=\"font-weight:bold;font-size:40px;margin-right:25px;margin-left:50px;text-align:center;\">"+
		"      <i class=\"fa fa-car\" style=\"color:orange\"></i>&nbsp;"+
		((f.text.length > 150)?f.text.substring(0,150):f.text)+
				"   <span style=\"display:block;margin-left:50px;font-size:18px;text-align:left;\">"+f.category+"&nbsp;|&nbsp;<i class=\"fa fa-map-marker\"></i>&nbsp;Source: <a>"+f.source+"</a></span>"+

		"   </p>"+
	   "</div>";
	});

	$("#owl-feed").html(content);
	$("#owl-feed").owlCarousel({
		navigation : true,
		slideSpeed : 500,
		paginationSpeed : 400,
		singleItem : true,
		autoPlay : 1000,
		stopOnHover : true,
		goToFirstSpeed : 800,
		autoHeight : true,
		transitionStyle:"fade"
	  });
}

function pushChat(chat){

	  var content = "";
	  content+="<div class=\"message\">"+
	  "<img src=\"http://fillmurray.com/70/70\"/>"+
	  "<h2>"+chat.name+"</h2>"+
	  "<p>"+chat.message+"</p>"+
	  "<p class=\"time\"><span class=\"entypo-clock\"></span> just now</p>"+
	  "</div>";
	$(".messages").prepend(content);
}