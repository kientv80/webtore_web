<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>

<script src="/js/jquery-2.1.3.min.js"></script>
<script src="/js/bootstrap_v01.min.js"></script>
<script src="/js/webstorejs_core_v06.js"></script>
<script src="/js/360hay_v21.js"></script>
<link rel="stylesheet" href="/css/bootstrap_v07.min.css">
<link rel="shortcut icon" href="/images/icons/hayicon.png" type="image/x-icon" />
 <style>
		.swiper-container {
	        width: 100%;
	    }
	    .swiper-slide {
	        text-align: center;
	        font-size: 18px;
	        background: #fff;
	
	        /* Center slide text vertically */
	        display: -webkit-box;
	        display: -ms-flexbox;
	        display: -webkit-flex;
	        display: flex;
	        -webkit-box-pack: center;
	        -ms-flex-pack: center;
	        -webkit-justify-content: center;
	        justify-content: center;
	        -webkit-box-align: center;
	        -ms-flex-align: center;
	        -webkit-align-items: center;
	        align-items: center;
	    }
    </style>
<head>
<title>360hay!</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta property="fb:app_id" content="1641687746054225" />
<meta property="cookbook:author" content="http://360hay.com" />
<meta name="author" content="360hay.com" />
<meta content="vi-VN" />

<c:if test="${cateInfo != null }">
	<meta property="og:url" content="${cateInfo.url }" />
	<meta property="og:title" content="${cateInfo.title }" />
	<meta property="og:image" content="${cateInfo.image }" />
	<meta property="og:image" itemprop="thumbnailUrl" content="${cateInfo.image }" />
	<meta name="keywords" content="${cateInfo.keywords }" />
	<meta name="description" content="${cateInfo.desc}" />
</c:if>
<c:if test="${embededGA == true}">
	<script>
	
			(function(i, s, o, g, r, a, m) {
				i['GoogleAnalyticsObject'] = r;
				i[r] = i[r] || function() {
					(i[r].q = i[r].q || []).push(arguments)
				}, i[r].l = 1 * new Date();
				a = s.createElement(o), m = s.getElementsByTagName(o)[0];
				a.async = 1;
				a.src = g;
				m.parentNode.insertBefore(a, m)
			})(window, document, 'script',
					'//www.google-analytics.com/analytics.js', 'ga');
		
			ga('create', 'UA-45967649-2', 'auto');
			ga('send', 'pageview');
	</script>
</c:if>
</head>



<body style="background-color: #ecf0f5;" id="bodyId">

	<div class="container-fluid " style="padding: 0px;">
		<c:if test="${menuitems != null && menuitems.size() > 0 }">
		<div class="container-fluid contentcontainer"  style="padding: 0px;z-index: 20;" id="headercontainer">
			<tiles:insertAttribute name="header" />
		</div>
		</c:if>
		<div class="row contentcontainer" style="padding: 0px;">
			<div class="hidden-xs hidden-sm col-md-1 col-lg-1" style="padding: 0px;">
				<div class="container-fluid"></div>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10" style="padding: 0px;">
				<div class="container-fluid"  style="padding: 0px;">
					<div class="row" style="padding: 0px;">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"  style="padding: 0px;">
							<div class="container-fluid" style="background-color: transparent ;padding: 0px;">
								<!-- 		Content -->
								<div class="container-fluid" id="embededContent" style="display: none;padding: 0px;;height: 100%;">
									<iframe src="${target}" style="width: 100%;height: 100%;overflow: hidden;" id="iframeArticle"></iframe>
									<div class="row" style="background-color: #09a7e2;width: 100%;">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"  style="padding: 0px;">
											<div class="container-fluid">
												<button type="button" class="btn btn-primary" onclick="return parent.closeEmbeddedContent();"  style="float: right;">Đóng</button>
											</div>
										</div>
									</div>
								</div>
								
								<div class="container-fluid" id="360Content" style="padding: 0px;padding-top: 6px;">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8" style="padding: 0px;z-index: 2;float: left;">
											<tiles:insertAttribute name="content" />
										</div>

										<!--  Column 2 social -->
										<div class="hidden-xs hidden-sm col-md-4 col-lg-4" style="padding: 0px;float: left;z-index: 1;">
											<tiles:insertAttribute name="rightsidebar" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="hidden-xs hidden-sm col-md-1 col-lg-1" style="padding: 0px;">
				<div class="container-fluid"></div>
			</div>
		</div>
	</div>

	<script>
		function showTargetUrl(){
			if('${target}' != ''){
				showWebcontent('${target}', false,"","","social");
			}
		}
		showTargetUrl();
		
		function getPosition(element) {
		    var xPosition = 0;
		    var yPosition = 0;
		  
		    while(element) {
		        xPosition += (element.offsetLeft - element.scrollLeft + element.clientLeft);
		        yPosition += (element.offsetTop - element.scrollTop + element.clientTop);
		        element = element.offsetParent;
		    }
		    return { x: xPosition, y: yPosition };
		}
		$('#myNavbar').on('affixed.bs.affix',function(){
			$('#myNavbar').css("top","0px");
		});
		$('#myNavbar').on('affix.bs.affix',function(){
			var width = document.getElementById('myNavbar').offsetWidth;
			$('#myNavbar').css("width",width+"px");
		});
		
	   	$( document ).ready(function() {
		   var po = getPosition(document.getElementById('myNavbar'));
			$('#myNavbar').attr("data-offset-top",po.y);
		});
	 </script>	

	<script type="text/javascript">
		function showHideMenuBar() {
			try {
				var fromApp = ClientCache.getCookie("fromAndroidApp");
				//$("#log").html("from app: "+fromApp);
				if(fromApp === null || fromApp === undefined || fromApp === ""){
					$("#log").html("no cookie");
					var appinfo = Android.getAppInfo();
					if(appinfo != null && appinfo != undefined){
						ClientCache.setCookie("fromAndroidApp","true");
						$("#headercontainer").hide();
					}
				}else{
					//$("#log").html("have cookie" + fromApp);
				}
			} catch (e) {
				//$("#log").html("exception" + e);
				ClientCache.setCookie("fromAndroidApp","false");
			}
		}
		showHideMenuBar();
	</script>
</body>
</html>