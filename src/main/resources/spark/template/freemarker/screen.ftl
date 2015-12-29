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
                                                <i class="fa fa-calendar-check-o" style="color:#BCDE1D"></i>&nbsp; Good afternoon, ${user.name}.
                                                <br> Tomorrow is <b style="color:orange;">Wednesday</b> and it is going to rain.&nbsp;&nbsp;&nbsp; You should leave home between <b style="color:orange;">7.50 and 8.00</b> and take Ikeja Maryland route.                                       
                                                <h4>Suggestion&nbsp;|&nbsp;<i class="fa fa-map-marker"></i>&nbsp;Source: <a>eduzi.move</a></h4>
                                            </p>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="panel panel-primary friends">
                                       <div class="panel-heading">Where are you going?&nbsp;<a href="#" class="btn btn-default"><i class="fa fa-map-marker" style="color:#BCDE1D;font-size:16px;"></i>&nbsp;Plan</a></div>
                                       <div class="panel-body">
                                         <p><i class="fa fa-car fa-fw"></i>&nbsp;<a href="#">Magodo gate - 10mins walk</a></p>
					 <p><i class="fa fa-car fa-fw"></i>&nbsp;<a href="#">Ojodu Berger - 3mins drive</a></p>
					 <p><i class="fa fa-car fa-fw"></i>&nbsp;<a href="#">Law School V/I-1hr drive</a></p>
					 <p><i class="fa fa-car fa-fw"></i>&nbsp;<a href="#">Oshodi oke   -50mins drive</a></p>
                                       </div> 
                                    </div>
                                </div>
                                <div class="cover-info">
                                    <div class="avatar">
                                        <img src="images/guy-5.jpg" alt="Change picture" data-toggle="modal" data-target="#photo_window">
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
                                                                <li><a href="#"><img src="images/guy-6.jpg" alt="people" class="img-responsive"></a>
                                                                </li>
                                                            </#list>
                                                        </ul>
                                                        <div class="clearfix"></div>
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
<script>setInterval(function(){downloadFeeds()},30000);</script>
<script>
navigator.geolocation.getCurrentPosition(function(position) {

 var a = "{userid:\"Michael\",lat:\"" + position.coords.latitude + "\",lon:\"" + position.coords.longitude+"\"}";
 $.ajax({url: '/api/move/location',data: a,type: 'POST',success: function(result) {
         // Do something with the result
     }
});
 console.log(a);
});
</script>
</body>
</html>