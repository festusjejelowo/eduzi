var owl = $("#owl-feed");

owl.owlCarousel({
	navigation : true,slideSpeed : 500,paginationSpeed : 400,singleItem : true,autoPlay : 1000,stopOnHover : true,goToFirstSpeed : 800,autoHeight : true,transitionStyle:"fade"
});
function downloadFeeds(){

	$.getJSON("/api/feeds", function(data) {

		console.log('new feeds received');
		pushFeed(data);
  });

}

function pushFeed(feeds){

	var content = "";
	$.each(feeds, function(idx, f) {

		var icon = "";
		if(f.category == "Any eduzi.movement"){
			icon = "<i class=\"fa fa-calendar-check-o\" style=\"color:#BCDE1D\"></i>";
		}else if(f.category == "traffic"){
			icon = "<i class=\"fa fa-car\" style=\"color:orange\"></i>";
		}else if(f.category == "accident"){
			icon = "<i class=\"fa fa-map-marker\" style=\"color:red\"></i>";
		}else{
			icon = "<i class=\"fa fa-map-marker\" style=\"color:#000000\"></i>";
		}

		content+="<div class=\"item\">"+
		"<p>"+icon+"&nbsp;"+
		((f.text.length > 150)?f.text.substring(0,150):f.text)+
		"</p>"+
		"<h4>"+f.category+"&nbsp;|&nbsp;<i class=\"fa fa-map-marker\"></i>&nbsp;Source: <a>"+f.url+"</a></h4>"+
	   "</div>";
	});

	owl.data('owlCarousel').destroy();
	$("#owl-feed").html(content);
	owl.owlCarousel({
		navigation : true,slideSpeed : 500,paginationSpeed : 400,singleItem : true,autoPlay : 3000,stopOnHover : true,goToFirstSpeed : 800,autoHeight : true,transitionStyle:"fade"
	});
}