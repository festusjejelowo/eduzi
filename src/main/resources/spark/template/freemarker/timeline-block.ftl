<div class="col-xs-12 col-md-6 col-lg-4 item" style="position: absolute; left:386px;">
  <div class="timeline-block">
    <div class="panel panel-default">
      <div class="panel-heading">
        <div class="media">
          <div class="media-left">
          <#if timeline.author.pic??>
	       <p><a href="#"><img src="/api/move/photo/${timeline.author.pic}" class="media-object"/></a></p>
          <#else>
             <a href=""><img src="images/icon.png" class="media-object"></a>
          </#if>           
          </div>
          <div class="media-body">
            <a href="#" class="pull-right text-muted"><i class="icon-reply-all-fill fa fa-2x "></i></a>
            <a href="">${timeline.author.name}</a>
            <span>on ${timeline.publishedStr}</span>
          </div>
        </div>
      </div>
      <div class="panel-body">
        <p>${timeline.text}</p>
        <#if timeline.photo??>
           <p><a href="#"><img src="/api/move/photo/${timeline.photo}" class="img-responsive"/></a></p>
        </#if>
      </div>
      <div class="view-all-comments">
        <a href="#"><i class="fa fa-comments-o"></i> View all</a>
        <span>${timeline.comments?size} comments</span>
      </div>
      <ul class="comments">
       <#list timeline.comments as comment>
        <li class="media">
          <div class="media-left">
            <a href="">
              <img src="/api/move/photo/${comment.author.pic!"icon.png"}" class="media-object">
            </a>
          </div>
          <div class="media-body">
            <div class="pull-right dropdown" data-show-hover="li" style="display: none;">
              <a href="#" data-toggle="dropdown" class="toggle-button">
                <i class="fa fa-pencil"></i>
              </a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="/api/move/message/comment/${comment.id}">Edit</a></li>
                <li><a href="/api/move/message/uncomment/${user.email}/${comment.id}">Delete</a></li>
              </ul>
            </div>
            <a href="" class="comment-author pull-left">${comment.author.name}</a>
            <span>${comment.text}</span>
            <div class="comment-date">${comment.published}</div>
          </div>
        </li>
        </#list>
        <li class="comment-form">
          <form action="/api/move/message/comment/${user.email}/${timeline.id}" method="post">
          <div class="input-group">
            <span class="input-group-btn">
                <a href="#" class="btn btn-default"><i class="fa fa-photo"></i></a>
            </span>
            <input name="text" type="text" class="form-control">
            <span class="input-group-btn">
	        <button type="submit" class="btn btn-default"><i class="fa fa-photo"></i></button>
            </span>
          </div>
          </form>
        </li>
      </ul>
    </div>
  </div>
</div>