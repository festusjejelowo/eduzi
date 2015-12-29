<div class="modal fade" id="event_window" tabindex="-1" role="dialog" aria-labelledby="event_label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                       <span aria-hidden="true">&times;</span>
                       <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="event_label">create event</h4>
            </div>            
            <div class="modal-body">                
                <form action="/api/move/message/new/${user.email}" method="post" enctype="multipart/form-data">
                 <div class="row">
                   <div class="col-md-3">Event Photo</div>
                   <div class="col-md-9">
                        <div class="photo-dropzone">
                             <input type="file" name="file" class="btn btn-default"id="fileUpload" />
                             <input type="hidden" name="type" value="event">
                             <br/>
                             <div id="image-holder"></div>
                        </div>
                    </div>
                 </div>
                 <div class="row">
		    <div class="col-md-3">Event Name</div>
		    <div class="col-md-9"><input type="text" name="title" class="form-control" placeholder="Add a short clear name"/></div>
                 </div>
                 <div class="row">
		    <div class="col-md-3">Location</div>
		    <div class="col-md-9"><input type="text" name="place" class="form-control" placeholder="Enter location"/></div>
                 </div>
                 <div class="row">
		    <div class="col-md-3">Date</div>
		    <div class="col-md-3"><input type="text" name="date" class="form-control" placeholder=""/></div>
		    <div class="col-md-3">Time</div>
		    <div class="col-md-3"><input type="text" name="time" class="form-control" placeholder=""/></div>
                 </div>
                 <div class="row">
		    <div class="col-md-3">Description</div>
		    <div class="col-md-9"><textarea name="description" class="form-control" placeholder="Tell us about the event"></textarea></div>
                 </div>
                 <div class="row">
		    <div class="col-md-9 col-md-offset-3">
		        <button type="button" class="btn btn-default"data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary" id="btn_event">Post</button>
                    </div>
                 </div>
                </form>    
            </div> 
        </div>
    </div>
</div>