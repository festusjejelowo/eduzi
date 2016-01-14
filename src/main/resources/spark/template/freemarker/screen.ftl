<!DOCTYPE html>
<html class="st-layout ls-top-navbar ls-bottom-footer hide-sidebar sidebar-r2 js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths" lang="en" data-ember-extension="1">
<head>
   <#include "header.ftl"> 
</head>
<body class="breakpoint-1024">
    <!-- Wrapper required for sidebar transitions -->
    <div class="st-container">
        <!-- Fixed navbar -->
        <#include "navbar.ftl">
        <#include "chat-window.ftl">
            <div class="chat-window-container"></div>
            <!-- content push wrapper -->
            <div class="st-pusher" id="content">
                <div class="st-content">
                    <div class="st-content-inner">
                        <div class="container">
                            <div class="cover profile">
                                <div class="wrapper">
                                    <div class="image">
                                      <div  id="owl-feed" style="height:200px;" class="owl-carousel">
                                        <div class="item" style="width:850px;font-family: 'Abel', sans-serif;font-size:30px;">
                                            <p style="font-family: 'Abel', sans-serif;">
                                                <i class="fa fa-calendar-check-o" style="color:#BCDE1D"></i>&nbsp;${message.title}.
                                                <br> Tomorrow is <b style="color:orange;">${message.time}</b> and ${message.type}.&nbsp;You should leave home between <b style="color:orange;">${message.text}</b> and take ${message.place} route.                                       
                                                <h4>Suggestion&nbsp;|&nbsp;<i class="fa fa-map-marker"></i>&nbsp;Source: <a>eduzi.move</a></h4>
                                            </p>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="panel panel-primary friends">
                                       <div class="panel-heading">Where are you going?&nbsp;<a href="#" class="btn btn-default"><i class="fa fa-map-marker" style="color:#BCDE1D;font-size:16px;"></i>&nbsp;Plan</a></div>
                                       <div class="panel-body" id="neigbourhood" style="overflow:hidden;">
                                         &nbsp;
                                       </div> 
                                    </div>
                                </div>
                                <div class="cover-info">
                                    <div class="avatar">
                                        <img src="/api/move/photo/${user.avatar!"avatar.png"}" alt="Change picture" data-toggle="modal" data-target="#photo_window">
                                    </div>
                                    <ul class="cover-nav">
                                        <li><a href="user-public-timeline.html"><i class="fa fa-fw icon-ship-wheel"></i> Timeline</a></li>
                                        <li><a href="user-public-profile.html"><i class="fa fa-fw icon-user-1"></i> About</a></li>
                                        <li><a href="user-public-users.html"><i class="fa fa-fw fa-users"></i> Friends</a></li>
                                        <li><a data-toggle="modal" data-target="#event_window"><i class="fa fa-calendar" style="color:red"></i> Event</a></li>
                                    </ul>
                                </div>
                                <#include "event.ftl">
                                <#include "photo.ftl">
                            </div>
                            <div class="timeline row" data-toggle="isotope" style="position: relative; height: 1954.98px;">
                                <div class="col-xs-12 col-md-6 col-lg-4 item" style="position: absolute; left: 0px; top: 0px;">
                                    <div class="timeline-block">
                                        <div class="panel panel-default share clearfix-xs">
                                        <form action="/api/move/message/new/${user.email}" method="post">
                                            <div class="panel-heading panel-heading-gray title">
                                                What&apos;s new
                                            </div>
                                            <div class="panel-body">
                                                <textarea id="status" name="text" class="form-control share-text" rows="3" placeholder="Share your status..."></textarea>
                                                <input type="hidden" name="type" value="timeline">
                                            </div>
                                            <div class="panel-footer share-buttons">
                                                <a href="#" id="btn-place"><i class="fa fa-map-marker" style="font-size:16px;"></i></a>
                                                <a href="#" id="btn-photo" data-toggle="modal" data-target="#photo_window"><i class="fa fa-photo" style="font-size:16px;"></i></a>
                                                <a href="#" id="btn-video"><i class="fa fa-video-camera" style="font-size:16px;"></i></a>
                                                <button type="submit" class="btn btn-primary btn-xs pull-right display-none" id="btn_timeline">Post</button>
                                            </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <#if timelines??>
                                    <#list timelines as timeline>
                                        <#if timeline.type=="event">
                                            <div class="col-xs-12 col-md-6 col-lg-4 item" style="position: absolute; left:${(timeline_index%3)*386}px;top:${((timeline_index/3)?int)*176}px;">
                                                <div class="timeline-block">
                                                    <div class="panel panel-default event">
                                                        <div class="panel-heading title">${timeline.title}</div>
                                                        <ul class="icon-list icon-list-block">
                                                            <li><i class="fa fa-globe"></i>${timeline.place!""}</li>
                                                            <li><i class="fa fa-calendar-o"></i>${timeline.date!timeline.publishedStr}</li>
                                                            <li><i class="fa fa-clock-o"></i>${timeline.time!""}</li>
                                                            <li><i class="fa fa-users"></i> ${timeline.attendees?size} Attendees <a href="/api/move/message/attend/${user.email}/${timeline.id}" class="btn btn-primary btn-stroke btn-xs pull-right">Attend</a>
                                                            </li>
                                                        </ul>
                                                        <ul class="img-grid">
                                                            <#list timeline.attendees as attendee>
                                                                <li><a href="#"><img src="/api/move/photo/${attendee.pic}" alt="people" class="img-responsive"></a>
                                                                </li>
                                                            </#list>
                                                        </ul>
                                                        <div class="clearfix" style="display:none;">
                                                        <span id="startLat"></span><span id="startLon"></span>
							<span id="currentLat"></span><span id="currentLon"></span>
							<span id="distance">0</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <#elseif timeline.type=="timeline">
                                               <#include "timeline-block.ftl">
                                            <#elseif timeline.type=="photo">
                                               <#include "photo-block.ftl">
                                            <#else>
                                                <div class="col-xs-12 col-md-6 col-lg-4 item" style="position: absolute;left: 386px; top: 0px;">
                                                    <div class="timeline-block">
                                                        <div class="panel panel-default relative">
                                                            <img src="images/place2-full.jpg" alt="place" class="img-responsive">
                                                            <div class="panel-body panel-boxed text-center">
                                                                <div class="rating">
                                                                    <span class="star"></span>
                                                                    <span class="star filled"></span>
                                                                    <span class="star filled"></span>
                                                                    <span class="star filled"></span>
                                                                    <span class="star filled"></span>
                                                                </div>
                                                            </div>
                                                            <div class="panel-body">
                                                                <img src="images/guy-2(1).jpg" alt="people" class="img-circle">
                                                                <img src="images/woman-2.jpg" alt="people" class="img-circle">
                                                                <img src="images/guy-3(1).jpg" alt="people" class="img-circle">
                                                                <img src="images/woman-3(1).jpg" alt="people" class="img-circle">
                                                                <a href="#" class="user-count-circle">12+</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                        </#if>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /st-content-inner -->
    </div>
    <!-- /st-content -->
    </div>
    <!-- /st-pusher -->
<#include "footer.ftl">
<style>

.infos {border: 1px solid #ccc;margin: 25px 0;}
.infos li {padding: 5px;float:none;width:300px;height:150px;line-height:150px;border-bottom: 1px dashed #ccc;}
.infos li.odd {background: #fafafa;}
.infos li:after {content: '';display: block;clear: both;}
.infos img {float: left;width: 100px;margin: 5px 15px 0 0;}
.infos a {font-family: Arial, sans-serif;font-size: 20px;font-weight: bold;color: #06f;}
.infos p {margin: 15px 0 0;font-size: 14px;}
</style>
<script>setInterval(function(){downloadFeeds()},30000);</script>
<script src="js/socket.io.js"></script>
<script type="text/javascript" src="js/jquery.easing.min.js"> </script>
<script type="text/javascript" src="js/jquery.easy-ticker.min.js"> </script>
<script>
var params  = {'reconnection delay' : 2000,'force new connection' : true};
var socket = io.connect('http://'+location.hostname + ':7851',params);
var startPos;
socket.on('connect',function(){
   console.log('info: connected to socket.io server');
   socket.on('location',function(places){
      
      var content = "<ul class='list-unstyled'>";
      /*Object.keys(places).forEach(function(k, i) {
      	content+='<p style="height:150px;"><i class="fa fa-car fa-fw"></i>&nbsp;<a href="#">'+k+'</a></p>';
	});*/
      for(var i=0; i < places.length; i++){
       
       content+='<li style="float:none;width:100%;">'+
       '<img style="float:left;margin:2px;color:#990012;-webkit-filter: sepia(100%); filter: sepia(100%);" src="images/place.png" height="35" width="35"/>'+
       '<a style="font-family: Arial, sans-serif;font-size: 16px;font-weight: bold;" href="#">Expandable Field</a>'+
       '<p style="margin: 15px 0 0;font-size: 14px;">'+places[i]+'</p>'+
       '</li>';
      }
      content+= '</ul>';
      document.getElementById('neigbourhood').innerHTML=content;
      $('#neigbourhood').easyTicker({
	direction: 'up',
	easing: 'easeOutSine',speed:'slow',
	interval: 2000,height: '200px',
	visible: 2,mousePause: 1,
	controls: {
		up: '',
		down: '',
		toggle: '',
		playText: 'Play',
		stopText: 'Stop'
	}
     });
   });
   navigator.geolocation.getCurrentPosition(function(position) {
      startPos = position;
      document.getElementById('startLat').innerHTML = startPos.coords.latitude;
      document.getElementById('startLon').innerHTML = startPos.coords.longitude;
      socket.emit('location',{userId:${user.id},longitude:position.coords.longitude,latitude:position.coords.latitude});
   }, function(error) {
   
    console.log('warn: error on geopisition. Error code: ' + error.code);    
    //   0: unknown error
    //   1: permission denied
    //   2: position unavailable (error response from locaton provider)
    //   3: timed out    
  });
  navigator.geolocation.watchPosition(function(position) {
    document.getElementById('currentLat').innerHTML = position.coords.latitude;
    document.getElementById('currentLon').innerHTML = position.coords.longitude;
    
    socket.emit('distance',{
           longitude:startPos.coords.latitude,
           latitude:startPos.coords.longitude,
           longitude2:position.coords.latitude,
           latitude2:position.coords.longitude
          });
    socket.on('distance',function(dist){
       document.getElementById('distance').innerHTML = dist;
    });
  });
});
</script>
</body>
</html>