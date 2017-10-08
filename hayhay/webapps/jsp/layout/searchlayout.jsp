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
 
<head>
<title>360hay!</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
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

<body style="background-color: #fff;" id="bodyId">
	<div class="container-fluid">
		<tiles:insertAttribute name="content" />
	</div>
</body>
</html>