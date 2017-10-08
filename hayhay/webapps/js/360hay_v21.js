
function hit(type, data) {
	$.ajax({
		type : "GET",
		url : "/hit.html?type=" + type + "&data=" + data,
	}).done(function(msg) {
	});
}
var selectedSectionId;
var selectedCategory;
var allNews=[];
function showWebcontent(url, isIs360hayArticle,articleId,cateName, ref) {
	var youtubeId = "";
	var fromApp = ClientCache.getCookie("fromAndroidApp");
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
	}else if(url.indexOf("muabannhadat.vn") > 0 || url.indexOf("phimmoi.net") > 0){
		window.open(url);
	}else if(fromApp != null && fromApp != undefined && fromApp == "true" ){
		var article = findNewsById(parseInt(articleId));
		//Android.openArticle(article.id,article.url,article.title,article.shotDesc,article.imageUrl,article.strDate,article.fromWebSite);
		Android.openArticle(JSON.stringify(article));
	}else{
		if(ref == undefined)
			ref = "";
		$("#iframeArticle").attr("src","/news/opennews.html?ref="+ref+"&articleId="+ articleId + "&targetUrl=" +url);
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
		window.location = "/";
	}
}
function jump(sectionId, close){
	if($("#"+sectionId) != undefined && $("#"+sectionId).offset() != undefined){
		if(close == true){
			 $('html, body').animate({
			        scrollTop: $("#"+sectionId).offset().top -200
			 }, 500);
		}else{
			 $('html, body').animate({
			        scrollTop: $("#"+sectionId).offset().top -100
			 }, 500);
		}
	}
}
function showRegisterDialog(){
	$("#loginDialog").modal('show');
}


var youtubeIndex = 0;
function loadYoutubeArticles(fromIndex, cateName, cateContainer){
	$.ajax({
		type : "GET",
		url : "/news/loadmorenews.html?cate=" + selectedCategory + "&fromIndex=" + youtubeIndex,
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
        spaceBetween: 15,
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
function swiperCategory(){
	var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        slidesPerView: 1,
        spaceBetween: 50,
       	autoHeight:true,
       	autoplay: 3000,
        autoplayDisableOnInteraction: false,
        loop: true,
        effect: 'fade',
        breakpoints: {
            1024: {
                slidesPerView: 1,
                spaceBetween: 40
            },
            768: {
                slidesPerView: 1,
                spaceBetween: 30
            },
            640: {
                slidesPerView: 1,
                spaceBetween: 20
            },
            320: {
                slidesPerView: 1,
                spaceBetween: 10
            }
        }
    });
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length,c.length);
        }
    }
    return "";
}
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
function getFromLocalStorage(name) {
	if(supportLocalStorage()) {
	    return localStorage.getItem(name);
	}
	return "";
}
function setLocalStorage(name, value){
	if(supportLocalStorage()) {
		localStorage.setItem(name, value);
	}
}
function supportLocalStorage(){
	if(typeof(Storage) !== "undefined") {
		return true;
	}
	return false;
}
function renderNewsHTML(fromPage,fromNewsIndex, categories,cate){
	var cates = categories;
	var data ={categoriesNews:cates,from:fromPage,fromIndex:fromNewsIndex};
	addNews(cates[0].news);
	View.renderHTML("newsContainer",data, "/js/ejs/templates/groupnewsbycategory_v8.ejs", true)
	swiper();
	loadImages(cates[0].news);
	scheduleLoadOtherCategories(fromPage,fromNewsIndex,cate);
	
}
function addNews(news){
	if(news !==null && news!==undefined){
		var count = allNews.length;
		for(i=0;i<news.length;i++){
			allNews[count] = news[i];
			count=count+1;
		}
	}
}
function findNewsById(id){
	for(i=0;i<allNews.length;i++){
		if(allNews[i].id===id)
			return allNews[i];
	}
}
function scheduleLoadOtherCategories(fromPage,fromNewsIndex,cate){
	$( document ).ready(function(){
		loadNews(fromPage,fromNewsIndex, cate);
	});
}
function loadNews(fromPage, fromIndex, cate){
	$.ajax({
		type : "GET",
		url : "/news/json/" + cate,
	}).done(function(result) {
		if(result.categories != undefined && result.categories.length > 0){
			var currentIndex = 0;
			var renderCount = 0;
			View.scroll(View.scrollPosition._35_PERCENT,function(){
				renderCount = renderCount + 1;
				while(renderCount > 0){
					if(currentIndex < result.categories.length){
						var renderCate = [result.categories[currentIndex]];
						currentIndex ++;
						var data ={categoriesNews:renderCate,from:fromPage,fromIndex:fromIndex};
						View.renderHTML("newsContainer",data, "/js/ejs/templates/groupnewsbycategory_v8.ejs", true)
						swiper();
						loadImages(renderCate[0].news);
						addNews(renderCate[0].news);
					}
					renderCount = renderCount -1;
				}
			});	
		}

	});
}

function loadMore(from, fromIndex, cateName, cateContainer){
	fromIndex = $("#"+cateName+"_fromIndex").val();
	$.ajax({
		type : "GET",
		url : "/news/loadmorenews.html?from="+from+"&cate=" + cateName + "&fromIndex=" + fromIndex,
	}).done(function(news) {
		if(news.length > 0){
			var data ={news:news};
			View.renderHTML(cateContainer,data, "/js/ejs/templates/news_v2.ejs", true)
			$("#"+cateName+"_fromIndex").val(parseInt(fromIndex)+14);
			addNews(news);
		}
	});
}

function loadImages(news){
	for(i = 0;i<news.length; i++){
		try{
			if(news[i].imageUrl != null && news[i].imageUrl != undefined && (news[i].imageUrl.indexOf("http") >= 0 || news[i].imageUrl.indexOf("https") >= 0))
				$('#image_'+news[i].id).attr("src",news[i].imageUrl);
		}catch(e){
			$('#image_'+news[i].id).attr("src",news[i].imageUrl);
		}

	}
	
}

function renderLotteryHTML(data){
	var data ={lotteries:data};
	View.renderHTML("lotterycontainer",data, "/js/ejs/templates/lottery.ejs", false)
}
function filterLottery(){
	var agency = $("#agency").val();
	var date = $("#date").val();
	$("#lbDate").text("Kết quả xổ số ngày "+date);
	HTTPClient.callAjaxService("/webstore/json/lottery?date="+date+"&agency="+agency, null,"GET", "renderLotteryHTML",null, null, null);
}