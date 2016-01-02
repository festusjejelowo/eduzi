<!DOCTYPE html>
<html class="st-layout ls-top-navbar ls-bottom-footer hide-sidebar sidebar-r2 js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths"
lang="en" data-ember-extension="1">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link type="text/css" rel="stylesheet" href="styles/css">
  <style type="text/css">
    .gm-style .gm-style-cc span,
    .gm-style .gm-style-cc a,
    .gm-style .gm-style-mtc div {
      font-size: 10px
    }
  </style>
  <style type="text/css">
    @media print {
      .gm-style .gmnoprint,
      .gmnoprint {
        display: none
      }
    }
    @media screen {
      .gm-style .gmnoscreen,
      .gmnoscreen {
        display: none
      }
    }
  </style>
  <style type="text/css">
    .gm-style {
      font-family: Roboto, Arial, sans-serif;
      font-size: 11px;
      font-weight: 400;
      text-decoration: none
    }
    .gm-style img {
      max-width: none
    }
  </style>

  <link href='https://fonts.googleapis.com/css?family=Abel' rel='stylesheet' type='text/css'>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="eduzi is a traffic-monitoring, journey-planner and events analytic app that will allow commuters, police and business owners know where they have been, learn from what they are doing now, and using these insights to see where they are going and steer their strategy accordingly.">
  <meta name="author" content="Jejelowo B. Festus - Ocular-Minds Softwares">
  <title>Traffic-monitoring, journey-planner and events analysis | eduzi</title>
  <meta name="title" content="eduzi.com">
  <meta name="description" content=" eduzi is a traffic-monitoring, journey-planner and events analytic app">
  <meta name="image" content="/images/longo.png">
  <meta name="thumbail" content="/images/logo.png" />
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="keywords" content="traffic-monitoring,location based services,predictive analysis,movement,journey planning,mobile app,web scrapping">

  <meta property="og:title" content="eduzi | movement" />
  <meta property="og:description" content="eduzi is a traffic-monitoring, journey-planner and events analytic app that will allow commuters, police and business owners know where they have been, learn from what they are doing now, and using these insights to see where they are going and steer their strategy accordingly."
  />
  <meta property="og:image" content="http://www.eduzi.herokuapp.com/images/logo.png" />
  <meta property="og:type" content="Internet Services" />
  <meta property="og:url" content="http://www.eduzi.herokuapp.com" />
  <meta property="og:site_name" content="eduzi.herokuapp.com | move" />
  <link rel="icon" type="image/x-icon" href="images/favicon.ico">
  <link href="styles/all.css" rel="stylesheet">
  <link href="styles/app.css" rel="stylesheet">
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries
WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!-- If you don't need support for Internet Explorer <= 8 you can safely remove these -->
  <!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

  <style type="text/css">
    .jqstooltip {
      position: absolute;
      left: 0px;
      top: 0px;
      visibility: hidden;
      background: rgb(0, 0, 0) transparent;
      background-color: rgba(0, 0, 0, 0.6);
      filter: progid: DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000);
      -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";
      color: white;
      font: 10px arial, san serif;
      text-align: left;
      white-space: nowrap;
      padding: 5px;
      border: 1px solid white;
      z-index: 10000;
    }
    .jqsfield {
      color: white;
      font: 10px arial, san serif;
      text-align: left;
    }
  </style>
</head>

<body class="breakpoint-1024">
  <!-- Wrapper required for sidebar transitions -->
  <div class="st-container">
    <!-- Fixed navbar -->
    <div class="navbar navbar-main navbar-primary navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-nav">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.html"><img src="images/logo.png" style="height:45px;width:40px;border:none;">
          </a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="main-nav">
          <ul class="nav navbar-nav">
            <li>&nbsp; </li>
          </ul>
          <ul class="nav navbar-nav  navbar-right">
            <li>
              <form class="navbar-form" role="form-horizontal" action="/login" method="post">               
                <input type="text" class="form-control" name="email" placeholder="username" value="${email!}">
                <input type="password" class="form-control" name="password" placeholder="password">
                <input type="hidden" class="latitude" name="latitude">
                <input type="hidden" class="longitude" name="longitude">
                <button class="btn btn-default" type="submit"><i class="fa icon-lock"></i>&nbsp; Sign In</button>
              </form>
              <#if error??>
                <div class="alert alert-warning alert-block fade in col-md-6">
                  <a href="#" class="close" data-dismiss="alert">&times;</a> ${error}
                </div>
              </#if>
            </li>
          </ul>
        </div>
        <!-- /.navbar-collapse -->
      </div>
    </div>
    <div class="st-pusher" id="content">
      <div class="st-content">
        <div class="st-content-inner">
          <div class="container" style="height:100%;width:100%;background:url('images/bg.jpg'); no-repeat center center fixed; -webkit-background-size: cover; -moz-background-size: cover;-o-background-size: cover; background-size: cover;">
            <div class="cover profile" style="background: rgba(255, 255,255, 0.75);border:0px;">
              <div class="content" style="margin-top:15px;">
                <div class="col-md-9">
                  <div id="owl-feed">
                    <div class="item">
                      <p>
                        <i class="fa fa-calendar-check-o" style="color:#BCDE1D"></i>&nbsp;Learn. Anticipate. Move.
                        <br>
                      </p>
                      <blockquote style="font-size:22px;line-height:0.8;">
                        <b><i class="fa fa-users"></i></b>&nbsp;Connect with friends who are using <b>eduzi</b>&nbsp;
                        <br>
                        <br>
                        <b><i class="fa fa-car"></i></b>&nbsp;To anticipate and avoid traffic congestion
                        <br>
                        <br>
                        <b><i class="fa fa-map-marker"></i></b>&nbsp;Find route through nearby angel or map&nbsp;&nbsp;&nbsp;
                        <br>
                        <br>
                      </blockquote>
                      <h4>Any eduzi.movement&nbsp;|&nbsp;<i class="fa fa-map-marker"></i>&nbsp;Source: <a>#</a></h4>
                    </div>
                  </div>
                </div>
                <div class="col-md-3 container-fluid">
                  <div class="lock-container">
                    <div class="panel panel-default text-center" style="border-radius:10px;">
                      <img src="images/nogender.png" class="img-circle" style="height:50px;width:50px;">
                      <div class="panel-body">
                        <form style="color:#BCDE1D;" action="register" method="post">
                          <div class="input-group">
			    <div class="input-group-btn">
			       <button type="button" class="btn"><i class="fa fa-user"></i></button>
			    </div>
			    <input type="text" class="form-control" placeholder="Your name" name="name" id="name"></input>
		          </div>
		          <div class="input-group">
			    <div class="input-group-btn">
			       <button type="button" class="btn"><i class="fa fa-envelope"></i></button>
			    </div>
			    <input type="text" class="form-control" name="email" placeholder="Email">
		          </div>
                          <div class="input-group">
			    <div class="input-group-btn">
			       <button type="button" class="btn"><i class="fa fa-shield"></i></button>
			    </div>
			    <input class="form-control" type="password" name="password" placeholder="Enter Password">
		          </div>                          
                          <br>
                          <button type="submit" class="btn btn-primary btn-lg"><b>create account</b></button>
                          <br>
                          <a href="#" class="forgot-password">Forgot password?</a>
                          <#if createError??>
                            <div class="alert alert-danger" data-dismiss="alert">
                              <a href="#" class="close">&times;</a>
                              <strong>Error!</strong>&nbsp;${createError}
                            </div>
                          </#if>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="container">
              <div class="row" style="font-family: 'Abel', sans-serif;font-size:30px;font-weight:bold;line-height:30px;color:#ffffff;">
                <div class="col-md-12">Download the app free:
                  <br>
                  <br>
                </div>
              </div>
              <div class="row">
                <div class="col-md-3"><a class="btn btn-primary btn-lg" href="#"><i class="fa fa-android"></i>&nbsp;From Google Play</a>
                </div>
                <div class="col-md-3"><a class="btn btn-warning btn-lg" href="#"><i class="fa fa-windows"></i>&nbsp;And Window Phone Store</a>
                </div>
                <div class="col-md-3"><a class="btn btn-danger  btn-lg" href="#"><i class="fa fa-apple"></i>&nbsp;Also on App Store</a>
                </div>
                <div class="col-md-3">&nbsp;</div>
              </div>
              <div class="row" style="margin-top:60px;">
                <div class="col-md-4">
                  <h2>About</h2>
                  <ul class="footer-link">
                    <li><a href="/about">About Us</a>
                    </li>
                    <li><a href="/jobs">Jobs at eduzi</a>
                    </li>
                  </ul>
                  <h2>Support</h2>
                  <ul class="footer-link">
                    <li><a href="/terms">Terms of Use</a>
                    </li>
                    <li><a href="/privacy">Privacy Policy</a>
                    </li>
                    <li><a href="https://twitter.com/eduzi">Contact</a>
                    </li>
                  </ul>
                </div>
                <div class="col-md-4">
                  <h2>Blog</h2>
                  <ul class="footer-link">
                    <li>Coming soon.</li>
                  </ul>
                  <h2>Community</h2>
                  <ul class="footer-link">
                    <li><a href="https://www.facebook.com/groups/eduzi"><b>eduzi&apos;s</b> Facebook Group</a>
                    </li>
                  </ul>
                </div>
                <div class="col-md-4">
                  <h2>Developer</h2>
                  <ul class="footer-link">
                    <li>Coming soon.</li>
                  </ul>
                  <h2>Donate</h2>
                  <ul class="footer-link">
                    <li>Coming soon.
                      <br>We want to help people cross borders.</li>
                      <li><a><video id="localVideo"></video>
                       <video id="remoteVideo"></video></a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <!-- /st-content-inner -->
        </div>
        <!-- /st-content -->
      </div>
      <!-- /st-pusher -->
      <footer class="footer">
        <a href="#">About</a>&nbsp;<a href="#">Mobile</a>&nbsp;<a href="#">Terms</a>&nbsp;<a href="#">Privacy</a>&nbsp;<a href="#">Quick links</a>&nbsp;
        <a href="#">Routes</a>&nbsp;<a href="#">  Help</a> -
        <strong>eduzi</strong> &copy; Copyright 2015 Ocular-Minds Softwares
      </footer>
    </div>
    <script>
      var colors = {
        "danger-color": "#e74c3c",
        "success-color": "#81b53e",
        "warning-color": "#f0ad4e",
        "inverse-color": "#2c3e50",
        "info-color": "#2d7cb5",
        "default-color": "#6e7882",
        "default-light-color": "#cfd9db",
        "purple-color": "#9D8AC7",
        "mustard-color": "#d4d171",
        "lightred-color": "#e15258",
        "body-bg": "#f6f6f6"
      };
      var config = {
        theme: "eduzi",
        skins: {
          "default": {
            "primary-color": "#16ae9f"
          },
          "default-nav-inverse": {
            "color-block": "#242424"
          }
        }
      };
    </script>
    <script src="js/all.js"></script>
    <script src="js/app.js"></script>
    <script src="js/eduzi.js"></script>
    <script src="https://cdn.respoke.io/respoke.min.js"></script>
    <script>
      setInterval(function() {
        downloadFeeds()
      }, 30000);
    </script>
    <script>
    // App ID from the Respoke Dashboard for your App
    var appId = "46cb74b7-14b5-46e2-904b-f7731b800b65";
    
    // The unique username identifying the user
    var endpointId = "mail.festus@gmail.com";
    
    // Create an instance of the Respoke client using your App ID
    var client = respoke.createClient({appId: appId,developmentMode: true});
    
    // "connect" event fired after successful connection to Respoke
    client.listen("connect", function(e) {
        console.log("Connected to Respoke!", e);
    });   
    
    // Execute some signin event, then connect to Respoke with
    client.connect({endpointId: "olu@eduzi.com"});
    var endpoint = client.getEndpoint({id: "mail.festus@gmail.com"});
    var call = endpoint.startVideoCall({
        videoLocalElement: document.getElementById("localVideo"),
        videoRemoteElement: document.getElementById("remoteVideo")
    });
    client.listen("call", function(event) {
        var call = event.call;
    
        // Only answer the call if we didn't initiate it
        if(call.caller !== true) {
            call.answer({
                videoLocalElement: document.getElementById("localVideo"),
                videoRemoteElement: document.getElementById("remoteVideo")
            });
        }
});
    
      console.log('websocket running');
      navigator.geolocation.getCurrentPosition(function(position) {

        $('.latitude').val(position.coords.latitude);
        $('.longitude').val(position.coords.longitude);

      });
    </script>
</body>

</html>