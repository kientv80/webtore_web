<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="/css/bootstrap_v03.min.css">
<head>
	<title>360hay!</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="author" content="360hay.com" />
	<meta content="vi-VN" />
	<meta property="og:title" content="${title }" />
	<meta name="description" content="${desc }" />
	<meta property="og:image" itemprop="thumbnailUrl" content="${image}" />
	<meta property="og:url" content="http://360hay.com?article=${url}" />
	<meta content="Tin nhanh 360hay.com" property="og:site_name"/>
	<meta name="author" content="360hay.com" />
	<meta name="dc.publisher" content="360Hay" />
    <meta name="dc.rights.copyright" content="360hay.com" />
    <meta name="dc.creator.name" content="360hay.com" />
    <meta name="apple-mobile-web-app-title" content="360hay.com" />
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
<body class="container-fluid" style="height: 100%;overflow: hidden;">
	<div id="fb-root"></div>
	<script>
		window.fbAsyncInit = function() {
			// init the FB JS SDK
			FB.init({
				appId : '1641687746054225', // App ID from the app dashboard
				status : true, // Check Facebook Login status
				xfbml : true
			// Look for social plugins on the page
			});
		};

		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=1641687746054225";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	
	<div class="container-fluid" style="padding: 0px;">
			<div class="row" style="position: fixed;top: 0px;right: 0px;z-index: 10;background-color: #3e5062;width: 100%;"  data-spy="affix">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"  style="padding: 0px;">
					<div class="container-fluid">
						<div id= "close" style="float: left;">
							<button type="button" class="btn btn-primary" onclick="return parent.closeEmbeddedContent('${ref}');">Đóng</button>
						</div>
						<c:if test="${articleId != null && articleId != ''}">
							<div id= "fblike" style="float: right;  padding: 7px 12px;" class="fb-share-button" data-href="http://360hay.com/like?articleId=${articleId}&targetUrl=${url}" data-layout="button_count"></div>
						</c:if>
					</div>
				</div>
			</div>
		
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"  style="padding: 0px;margin: 0px;">
      			<div class="container-fluid" style="padding: 0px;margin-left: -8px;margin-right: -8px;   height: 100%;" id="targetIframe">
					<iframe src="${url}" style="width: 100%;height: 100%;" id="iframeArticle"></iframe>
				</div>
			</div>
		</div>

	</div>
</body>
</html>