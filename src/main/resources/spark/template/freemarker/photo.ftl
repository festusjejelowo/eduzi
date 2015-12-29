<div class="modal fade" id="photo_window" tabindex="-1" role="dialog" aria-labelledby="event_label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                       <span aria-hidden="true">&times;</span>
                       <span class="sr-only">Close</span>
                </button>
                <h2 class="modal-title" id="event_label">Share Photo</h2>
            </div>            
            <div class="modal-body">                
                <form action="/api/move/message/new/${user.email}" method="post" enctype="multipart/form-data">
                 <div class="row">
                   <div class="col-md-3">Take Photo</div>
                   <div class="col-md-9">
                        <div class="photo-dropzone">
                             <input type="file" name="file" class="btn btn-default"id="fileUpload" />
                             <input type="hidden" name="type" value="timeline">
                             <br/>
                             <div id="image-holder"></div>
                        </div>
                    </div>
                 </div>
                 <div class="row">
		    <div class="col-md-2 col-md-offset-3">Mark As:</div>
		    <div class="col-md-7">
			   <div class="radio radio-inline radio-circle radio-warning">
				<input type="radio" name="profile" value="N" checked="true" id="agree1">
				<label for="agree1">Upload</label>
			   </div>
			  <div class="radio radio-inline radio-circle radio-success">
				<input type="radio" name="profile" value="Y" id="agree2">
				<label for="agree2">Profile Picture</label>
			  </div>
		     </div>
                 </div>
                 <div class="row">
		    <div class="col-md-3">Title</div>
		    <div class="col-md-9"><input type="text" name="title" class="form-control" placeholder="Add a short clear name"/></div>
                 </div>
                 <div class="row">
		    <div class="col-md-3">Where</div>
		    <div class="col-md-9"><input type="text" name="place" class="form-control" placeholder="Enter location"/></div>
                 </div>
                 <div class="row">
		    <div class="col-md-3">Description</div>
		    <div class="col-md-9"><textarea name="text" class="form-control" placeholder="say something about this photo"></textarea></div>
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