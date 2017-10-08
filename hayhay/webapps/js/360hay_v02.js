function hit(type, data) {
	$.ajax({
		type : "GET",
		url : "/hit.html?type=" + type + "&data=" + data,
	}).done(function(msg) {
	});
}
var selectedSectionId;
var selectedCategory;
function showWebcontent(url, isIs360hayArticle,articleId,cateName, ref) {
	var youtubeId = "";
	if(articleId != undefined && articleId.indexOf("hn_") >= 0){
		articleId = articleId.substring(3 ,articleId.length);
		selectedSectionId = "hn_" + articleId;
	} else {
		selectedSectionId = articleId;
	}
	if(url.indexOf("www.youtube.com") > 0){
		start = url.indexOf("v=");
		end = url.indexOf("&");
		if(end < 0)
			end = url.length;
		youtubeId = url.substring(start + 2,end);
		url = "http://www.youtube.com/embed/" + youtubeId+"?autoplay=1";
		$("#iframeYoutubeArticle").attr("src",url);
		$("#360Content").attr("style","display:none;padding: 0px;padding-top: 6px;");
		$("#youtubeEmbededContent").attr("style","display:block;padding: 0px;;height: 100%;");
		selectedCategory = cateName;
		loadYoutubeArticles();
	}else if(url.indexOf("muabannhadat.vn") > 0){
		window.open(url);
	}else{
		if(ref == undefined)
			ref = "";
		
		$("#iframeArticle").attr("src","/opennews.html?ref="+ref+"&articleId="+ articleId + "&targetUrl=" +url);
		$("#360Content").attr("style","display:none;padding: 0px;padding-top: 6px;");
		$("#embededContent").attr("style","display:block;padding: 0px;;height: 100%;");
		$("#header").attr("style","display:none;padding: 0px;");
		$("#bodyId").attr("style","background-color: #f1f1f1;overflow: hidden;");
		jump("embededContent", false);
	}
}
function closeEmbeddedContent(ref){
	if(ref == undefined || ref == ""){
		$("#iframeArticle").attr("src",null);
		$("#360Content").attr("style","display:block;;padding: 0px; padding-top: 6px;");
		$("#header").attr("style","display:block;;padding: 0px;margin-bottom: 0px;border: 0px;border-radius:0px;background-color: transparent;");
		$("#bodyId").attr("style","background-color: #f1f1f1;overflow: auto;");
		$("#embededContent").attr("style","display:none;height:0px;;padding: 0px;");
		jump(selectedSectionId,true);
	}else{
		window.location = "/home.html";
	}
}
function jump(sectionId, close){
	if(close == true){
		 $('html, body').animate({
		        scrollTop: $("#"+sectionId).offset().top -200
		 }, 500);
	}else{
		 $('html, body').animate({
		        scrollTop: $("#"+sectionId).offset().top
		 }, 500);
	}
}
function showRegisterDialog(){
	$("#loginDialog").modal('show');
}


var youtubeIndex = 0;
function loadYoutubeArticles(fromIndex, cateName, cateContainer){
	$.ajax({
		type : "GET",
		url : "/loadmorenews.html?cate=" + selectedCategory + "&fromIndex=" + youtubeIndex,
	}).done(function(news) {
		var data ={news:news};
		if(news.length > 0){
			var template = new EJS({url: '/js/ejs/templates/youtube_article.ejs'});
			var html = template.render(data);
			$("#listYoutubeArticle").append(html);
			youtubeIndex = youtubeIndex + 7;
		}
	});
	
}
function swiper(){
	var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        slidesPerView: 4,
        spaceBetween: 50,
       	autoHeight:true,
        breakpoints: {
            1024: {
                slidesPerView: 4,
                spaceBetween: 40
            },
            768: {
                slidesPerView: 3,
                spaceBetween: 30
            },
            640: {
                slidesPerView: 2,
                spaceBetween: 20
            },
            320: {
                slidesPerView: 2,
                spaceBetween: 10
            }
        }
    });
}