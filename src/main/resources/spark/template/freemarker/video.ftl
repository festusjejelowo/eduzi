<!DOCTYPE html>
<html class="st-layout ls-top-navbar ls-bottom-footer hide-sidebar sidebar-r2 js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths"
lang="en" data-ember-extension="1">
<head>  
  <link href='https://fonts.googleapis.com/css?family=Abel' rel='stylesheet' type='text/css'>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="eduzi is a traffic-monitoring, journey-planner and events analytic app that will allow you to learn from what they are doing now, and using these insights to see where they are going and steer strategy accordingly.">
  <meta name="author" content="Jejelowo B. Festus - Ocular-Minds Softwares">
  <title>eduzi - video chat</title>
  <meta name="title" content="eduzi.com">
  <meta name="description" content=" eduzi is a traffic-monitoring, journey-planner and events analytic app">
  <meta name="image" content="/images/longo.png">
  <meta name="thumbail" content="/images/logo.png" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="traffic-monitoring,location based services,predictive analysis,movement,journey planning,mobile app,web scrapping">
  <meta name="mobile-web-app-capable" content="yes">
  <meta property="og:title" content="eduzi | movement" />
  <meta property="og:description" content="eduzi is a traffic-monitoring, journey-planner and events analytic app that will allow you to learn from what they are doing now, and using these insights to see where they are going and steer strategy accordingly."/>
  <meta property="og:image" content="http://www.eduzi.herokuapp.com/images/logo.png" />
  <meta property="og:type" content="Internet Services" />
  <meta property="og:url" content="http://www.eduzi.herokuapp.com" />
  <meta property="og:site_name" content="eduzi.herokuapp.com | move" />
  <link rel="icon" type="image/x-icon" href="images/favicon.ico">
  <link href="styles/all.css" rel="stylesheet">
  <link href="styles/app.css" rel="stylesheet">
  <!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
-->
  <base target="_blank">
  <link href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet" type="text/css">
  <style>  
  @media screen and (min-width: 1000px) {
    /* hack! to detect non-touch devices */
    div#links a {line-height: 0.8em; }
  } 
  .camera {
      width: 320px;min-height: 240px;
      float: left;margin: 0 20px 25px 0;border: 1px solid #aaa;
  }
  canvas {background-color: #ccc;max-width: 100%;width: 100%;}  
  html {
  /* avoid annoying page width change
  when moving from the home page */
  overflow-y: scroll;
  }
  img {border: none;max-width: 100%;}
  
  input[type=radio] {position: relative;top: -1px;}  
  input[type=radio] {position: relative;top: -1px;}  
  p#data {border-top: 1px dotted #666;
    font-family: Courier New, monospace;
    line-height: 1.3em;max-height: 1000px;
    overflow-y: auto;padding: 1em 0 0 0;
  }
  
  p.borderBelow {
    border-bottom: 1px solid #eee;
    padding: 0 0 20px 0;
  }
  
  ul {padding: 0 0 0 20px;}
  select {
    margin: 0 1em 1em 0;
    position: relative;
    top: -1px;
  }
  .alert-minimalist {
  	background-color: rgb(241, 242, 240);
  	border-color: rgba(149, 149, 149, 0.3);
  	border-radius: 3px;
  	color: rgb(149, 149, 149);
  	padding: 10px;
  }
  .alert-minimalist > [data-notify="icon"] {
  	height: 50px;
  	margin-right: 12px;
  }
  .alert-minimalist > [data-notify="title"] {
  	color: rgb(51, 51, 51);
  	display: block;font-weight: bold;margin-bottom: 5px;
  }
  .alert-minimalist > [data-notify="message"] {font-size: 80%;}
</style>
</head>
<body>
  <div class="container" style=">
    <h1>Video Chat</h1>
    <div class="row" id="videos">
	    <div class="col-md-4 col-sm-6" col-md-offset-2">
		<video id="local-video" autoplay muted class="camera"></video>
		<p style="display: inline-block;margin: 1em;text-align:center;">	  
		  <button id="call" class="btn btn-success"><i class="fa fa-phone"></i>&nbsp;Call</button>
		  <button id="snap" class="btn btn-warning"><i class="fa fa-camera-retro"></i>&nbsp;Snap</button>
		  <button id="recd" class="btn btn-default"><i class="fa fa-circle" style="color:green"></i>&nbsp;Record</button>
		  <button id="send" class="btn btn-primary">Send</button>
	        </p>
	        <form class="well form-inline" onsubmit="return false;">
		     <input id="msg" class="form-control input-lg" type="text" placeholder="Type something..." style="width:100%;"/>
		     <button type="button" onClick="sendMessage()" class="btn">Send</button>
               </form>
	    </div>
	    <div class="col-md-3 col-sm-4">
	        <div class="row"><video id="remote-video" autoplay class="camera"></video></div>
	        <div class="row"><canvas id="photo" style="display: inline-block;margin: 1em;width: 100%; height: 150px; border: 1px solid #ccc;"></canvas></div>	
	        <div class="row" id="cameraPreview"></div>
	   </div>
    </div>    
</div>
<script src="js/all.js"></script>
<script src="js/bootstrap-notify.min.js"></script>
<script src="js/socket.io.js"></script>
<script src="js/adapter.js"></script>
<script src="js/webrtc.io.js"></script>
<script src="js/RecordRTC.js"></script>
<script src="js/video.js"></script>
</body>
</html>
