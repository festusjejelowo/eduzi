  <div class="navbar navbar-main navbar-primary navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-nav">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="#sidebar-chat" data-toggle="sidebar-menu" class="toggle pull-right visible-xs"><i class="fa fa-comments"></i></a>
                    <a class="navbar-brand" href="index.html"><img src="images/logo.png" style="height:45px;width:40px;border:none;">
                    </a>
                </div>
                <div class="collapse navbar-collapse" id="main-nav">
                    <ul class="nav navbar-nav">
                        <li>
                            <form class="navbar-form" role="search" action="search" method="post">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search for people,business or stores near you" name="srch-term" id="srch-term" style="width:350px;">
                                    <div class="input-group-btn">
                                        <button class="btn btn-default" type="submit"><i class="fa icon-search"></i></button>
                                    </div>
                                </div>
                            </form>
                        </li>
                        <li><a href="#"><b>Report:</b></a></li>
                        <li><a href="#"><i class="fa fa-car" style="color:orange"></i>&nbsp;Traffic</a></li>
                        <li><a href="#"><i class="fa fa-fw fa-user-secret" style="color:#000000"></i>&nbsp;Crime</a></li>
                        <li><a href="#"><i class="fa fa-bell"></i><span class="badge" style="color:red;">2</span></a></li>
                    </ul>
                    <ul class="nav navbar-nav  navbar-right ">
                        <li class="hidden-xs">
                            <a href="#sidebar-chat" data-toggle="sidebar-menu">
                                <i class="fa fa-comments"></i>&nbsp;Chat
                            </a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle user" data-toggle="dropdown">
                                <img src="images/guy-5.jpg" alt="${user.email}" class="img-circle" width="40">${user.email}<span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="user-private-profile.html">Profile</a>
                                </li>
                                <li><a href="user-private-messages.html">Messages</a>
                                </li>
                                <li><a href="/index">Logout</a>
                                </li>
                            </ul>
                        </li>

                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
        </div>
        <!-- Sidebar component with st-effect-1 (set on the toggle button within the navbar) -->
        <div class="sidebar sidebar-chat right sidebar-size-2 sidebar-offset-0 chat-skin-dark sidebar-visible-mobile" id="sidebar-chat">
            <div class="split-vertical">
                <div class="chat-search">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <ul class="chat-filter nav nav-pills ">
                    <li class="active"><a href="#" data-target="li">All</a>
                    </li>
                    <li><a href="#" data-target=".online">Online</a>
                    </li>
                    <li><a href="#" data-target=".offline">Offline</a>
                    </li>
                </ul>
                <div class="split-vertical-body">
                    <div class="split-vertical-cell">
                        <div data-scrollable="" tabindex="0" style="overflow-y: hidden; outline: none;">
                            <ul class="chat-contacts">
                                <#if friends??>
                                    <#list friends as friend>
                                        <li class="online" data-user-id="${friends_index + 1}">
                                            <a href="#">
                                                <div class="media">
                                                    <div class="pull-left">
                                                        <span class="status"></span>
                                                        <img src="${friend.pic}" width="40" class="img-circle">
                                                    </div>
                                                    <div class="media-body">
                                                        <div class="contact-name">${friend.name}</div>
                                                        <small>${friend.status}</small>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                    </#list>
                                </#if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div id="ascrail2000" class="nicescroll-rails" style="width: 5px; z-index: 2; cursor: default; position: absolute; top: 0px; left: -5px; height: 0px; display: none;">
                <div style="position: relative; top: 0px; float: right; width: 5px; height: 0px; border: 0px; border-radius: 5px; background-color: rgb(22, 174, 159); background-clip: padding-box;"></div>
            </div>
        </div>