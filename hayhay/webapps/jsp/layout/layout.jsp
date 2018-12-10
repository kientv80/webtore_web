<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>

<script src="/js/jquery-2.1.3.min.js"></script>
<script src="/js/bootstrap_v01.min.js"></script>
<script src="/js/webstorejs_core_v06.js"></script>
<script src="/js/360hay_v23.js"></script>
<link rel="stylesheet" href="/css/bootstrap_v07.min.css">
<link rel="shortcut icon" href="/images/icons/hayicon.png"
	type="image/x-icon" />
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
<title>GlobalNewsIndex</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta property="fb:app_id" content="1641687746054225" />
<meta property="cookbook:author" content="http://globalnewsindex.com" />
<meta name="author" content="globalnewsindex.com" />
<meta content="vi-VN" />

<c:if test="${cateInfo != null }">
	<meta property="og:url" content="${cateInfo.url }" />
	<meta property="og:title" content="${cateInfo.title }" />
	<meta property="og:image" content="${cateInfo.image }" />
	<meta property="og:image" itemprop="thumbnailUrl" content="${cateInfo.image }" />
	<meta property="og:description" content="Thông tin nhanh &amp; mới nhất được cập nhật hàng giờ. Tin tức Việt Nam &amp; thế giới về xã hội, kinh doanh, pháp luật, khoa học, công nghệ, sức khoẻ, đời sống, văn hóa, rao vặt, tâm sự..., global, global news,globalnewsindex, global news index, index, economic, tech, opinion, politics,science,hot news,business" itemprop="description" />
	<meta name="keywords" content="Thông tin nhanh &amp; mới nhất được cập nhật hàng giờ. Tin tức Việt Nam &amp; thế giới về xã hội, kinh doanh, pháp luật, khoa học, công nghệ, sức khoẻ, đời sống, văn hóa, rao vặt, tâm sự..., global, global news,globalnewsindex, global news index, index, economic, tech, opinion, politics,science,hot news,business" /><!-- ${cateInfo.keywords }  -->
	<meta name="description" content="Thông tin nhanh &amp; mới nhất được cập nhật hàng giờ. Tin tức Việt Nam &amp; thế giới về xã hội, kinh doanh, pháp luật, khoa học, công nghệ, sức khoẻ, đời sống, văn hóa, rao vặt, tâm sự..., global, global news,globalnewsindex, global news index, index, economic, tech, opinion, politics,science,hot news,business" /><!-- ${cateInfo.desc} -->
	
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
	<div id="fb-root"></div>
	<script>(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = 'https://connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v3.2&appId=1658222504229501&autoLogAppEvents=1';
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));</script>
	
	
	<div class="container-fluid " style="padding: 0px;">
		<c:if test="${menuitems != null && menuitems.size() > 0 }">
			<div class="container-fluid contentcontainer"
				style="padding: 0px; z-index: 20;" id="headercontainer">
				<tiles:insertAttribute name="header" />
			</div>
		</c:if>
		<div class="row contentcontainer" style="padding: 0px;">
			<div class="hidden-xs hidden-sm col-md-1 col-lg-1"
				style="padding: 0px;">
				<div class="container-fluid"></div>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10"
				style="padding: 0px;">
				<div class="container-fluid" style="padding: 0px;">
					<div class="row" style="padding: 0px;">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"
							style="padding: 0px;">
							<div class="container-fluid"
								style="background-color: transparent; padding: 0px;">
								<!-- 		Content -->
								<div class="container-fluid" id="embededContent"
									style="display: none; padding: 0px;; height: 100%;">
									<iframe src="${target}"
										style="width: 100%; height: 100%; overflow: hidden;"
										id="iframeArticle"></iframe>
									<div class="row"
										style="background-color: #09a7e2; width: 100%;">
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"
											style="padding: 0px;">
											<div class="container-fluid">
												<button type="button" class="btn btn-primary"
													onclick="return parent.closeEmbeddedContent();"
													style="float: right;">Đóng</button>
											</div>
										</div>
									</div>
								</div>

								<div class="container-fluid" id="360Content"
									style="padding: 0px; padding-top: 6px;">
									<div class="row">
										<!-- Start action icons -->
										<div class="hidden-xs hidden-sm col-md-1 col-lg-1" style="z-index: 2; float: left;">
											<div style="width:100%">
												<div class="fb-like" data-href="http://globalnewsindex.com" data-layout="box_count" data-action="like" data-size="large" data-show-faces="true" data-share="true"></div>
											</div>
										</div>
										<!-- End action icons -->
										
										<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8"
											style="padding: 0px; z-index: 2; float: left;">
											<tiles:insertAttribute name="content" />
										</div>

										<!--  Column 2 social -->
										<div class="hidden-xs hidden-sm col-md-3 col-lg-3"
											style="padding: 0px; float: left; z-index: 1;">
											<tiles:insertAttribute name="rightsidebar" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="hidden-xs hidden-sm col-md-1 col-lg-1"
				style="padding: 0px;">
				<div class="container-fluid"></div>
			</div>
		</div>
	</div>

	<!-- Modal languageSettings -->
	<div class="modal fade" id="languageSettings" tabindex="-1"
		role="dialog" aria-labelledby="languageSettingsLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="languageSettingsLabel">Settings</h5>
				</div>
				<div class="modal-body">
					<form>
						<h5 class="modal-title" id="languageSettingsLabel">
							<b>Select website language</b>
						</h5>

						<div class="form-group">
							<label for="english">English</label> <input type="radio"
								class="form-check-input" name="locale" id="l_english" checked>
						</div>
						<div class="form-group">
							<label for="vn">Vietnamese</label> <input type="radio"
								class="form-check-input" name="locale" id="l_vn">
						</div>


						<h5 class="modal-title" id="languageSettingsLabel">
							<b>Read news writen in follow languages</b>
						</h5>
						<div class="form-group">
							<label for="english">English</label> <input type="checkbox"
								class="form-check-input" id="english"> <small
								id="englishHelp" class="form-text text-muted">Read news
								in English</small>
						</div>
						<div class="form-group">
							<label for="vn">Vietnamese</label> <input type="checkbox"
								class="form-check-input" id="vn"> <small id="vnlHelp"
								class="form-text text-muted">Read news in Vietnamese</small>
						</div>
						<div class="form-check">
							<label for="chinese">Chinese</label> <input type="checkbox"
								class="form-check-input" id="chinese"> <small
								id="chineselHelp" class="form-text text-muted">Read news
								in Chinese</small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						onclick="return saveLanguageSettings();">Save</button>
				</div>
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
		
	   	$(document).ready(function() {
		   var po = getPosition(document.getElementById('myNavbar'));
			$('#myNavbar').attr("data-offset-top",po.y);
		});
		
		
		//=============== show settings model ======================================
	      function saveLanguageSettings(){
			      var settings = undefined;
			      var profile = ClientCache.getLocalCacheJSON('profile');
			      var params = {};
			      if(profile == null || profile == undefined){
				      HTTPClient.callSyncAjaxService("http://globalnewsindex.com/profile?profileid=",params,"POST","profile");
				      profile = ClientCache.getLocalCacheJSON('profile');
				      if(profile != undefined && profile.id > 0){
					ClientCache.setCookie("uid",profile.id);
				      }
			      }else{
				ClientCache.setCookie("uid",profile.id);
			      }
			      if(profile != null && profile != undefined){
				      HTTPClient.callSyncAjaxService("http://globalnewsindex.com/mobile/settings/get?uid=" + profile.id  + "&option=favorite_languages",params,"GET","settings");
				      settings = ClientCache.getLocalCacheJSON('settings');
			      }
			      if(settings!=undefined){
				try{
				
				  if($("#l_vn:checked").val() != undefined && $("#l_vn:checked").val()=="on"){
				    ClientCache.setCookie("locale","vi_VN");
				  }else{
				    ClientCache.setCookie("locale","english");
				  }
				  
				  if($("#vn:checked").val() != undefined && $("#vn:checked").val()=="on"){
				    settings.settings[0].value=true;
				  }else{
				    settings.settings[0].value=false;
				  }
				  if($("#english:checked").val() != undefined && $("#english:checked").val()=="on"){
				    settings.settings[1].value=true;
				  }else{
				    settings.settings[1].value=false;
				  }

				  if($("#chinese:checked").val() != undefined && $("#chinese:checked").val()=="on"){
				    settings.settings[2].value=true;
				  }else{
				    settings.settings[2].value=false;
				  }
				  params={"uid":profile.id,"settings":JSON.stringify(settings)};
				  
				  HTTPClient.callSyncAjaxService("http://globalnewsindex.com/mobile/settings/update?a=1",params,"POST",);
				}catch(ex){
				  alert(ex);
				}
				$('#languageSettings').modal('toggle');
				window.location="http://globalnewsindex.com";
			      }
				      
		      }
		      function loadLanguageSettings(){
			      var settings = undefined;
			      var profile = ClientCache.getLocalCacheJSON('profile');
			      var params = {};
			      if(profile == null || profile == undefined){
				      HTTPClient.callSyncAjaxService("http://globalnewsindex.com/getprofile",params,"GET","profile");
				      profile = ClientCache.getLocalCacheJSON('profile');
			      }
			      if(profile != null && profile != undefined){
				      HTTPClient.callSyncAjaxService("http://globalnewsindex.com/mobile/settings/get?uid=" + profile.id  + "&option=favorite_languages",params,"GET","settings");
				      settings = ClientCache.getLocalCacheJSON('settings');
			      }
			      if(settings!=undefined){
				if(settings.settings[0].value==true){
				  $("#vn").attr('checked','checked');
				}else{	
				  $("#vn").removeAttr('checked');
				}
				if(settings.settings[1].value==true){
				  $("#english").attr('checked','checked');
				}else{
				  $("#english").removeAttr('checked');
				}
				if(settings.settings[2].value==true){
				  $("#chinese").attr('checked','checked');
				}else{
				  $("#chinese").removeAttr('checked');
				}
			      }
			      if(ClientCache.getCookie("locale") != undefined && ClientCache.getCookie("locale") == 'english'){
				 $("#l_english").attr('checked','checked');
			      }else{
				 $("#l_vn").attr('checked','checked');
			      }
		      }
		      function bindLanguageSettingsData(){
				$('#languageSettings').on('shown.bs.modal', function () {
				  loadLanguageSettings();
				});
		      }
		      function languageSettings(){
				var settings = ClientCache.getLocalCacheJSON('settings');
				if(settings == undefined){
				  showLanguageSettings();
				}
		      }
		      function showLanguageSettings(){
				$('#languageSettings').modal('show');
		      }
		      $(document).ready(function() {
				bindLanguageSettingsData();
				languageSettings();
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