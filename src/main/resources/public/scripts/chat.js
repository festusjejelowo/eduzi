$(document).ready(function(){

	alert('document ready');
	//Establish the WebSocket connection and set up event handlers
	var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat/");
	webSocket.onopen = function(event) {
	    webSocket.send('Authenticate^x500:Daniel:az098mm');
    };
	webSocket.onmessage = function (msg) {fireMessageReceived(msg);};
	webSocket.onclose = function () { alert("WebSocket connection closed") };

	//Update the chat-panel, and the list of connected users
	function fireMessageReceived(message) {

	   var obj;

	    try{

		    obj = JSON.parse(message.data);
			if(obj.group == 'feed'){
				pushFeed(obj.data);
			}else{
				pushChat(obj.data);
			}
		}catch(err){
			if (window.console && window.console.log ) {
			  console.log("[eduzi] "+err)
			  console.log(message.data)
            }
		}
	}

	function pushFeed(feeds){

		var content = "";
		$.each(feeds, function(idx, f) {
			content+=
			"<div class=\"item\">"+
			"   <h1 style=\"font-weight:bold;font-size:40px;margin-right:25px;margin-left:50px;\">"+
			"      <i class=\"fa fa-car\" style=\"color:orange\"></i>&nbsp;"+
			((f.text.length > 150)?f.text.substring(0,150):f.text)+
			"   </h1>"+
			"   <p style=\"display:block;margin-left:50px;font-size:18px;\">"+f.category+"&nbsp;|&nbsp;<i class=\"fa fa-map-marker\"></i>&nbsp;Source: <a>"+f.source+"</a></p>"+
	    "</div>";
        });

        $("#owl-feed").html(content);
        $("#owl-feed").owlCarousel({
			navigation : true,
			slideSpeed : 500,
			paginationSpeed : 800,
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

	   // });

	    $(".messages").prepend(content);
	}
});