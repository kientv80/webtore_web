<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
	<title>360hay!</title>
	<meta property="fb:app_id" content="1658222504229501" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="author" content="globalnewsindex.com" />
	<meta content="vi-VN" />
	<meta property="og:title" content="${title }" />
	<meta name="description" content="${desc }" />
	<meta property="og:image" itemprop="thumbnailUrl" content="${image}" />
	<meta property="og:url" content="http://globalnewsindex.com/like?articleId=${articleId}&targetUrl=${url}" />
	<meta content="globalnewsindex.com" property="og:site_name"/>
	<meta name="author" content="globalnewsindex.com" />
	<meta name="dc.publisher" content="GlobalNewsIndex" />
    <meta name="dc.rights.copyright" content="globalnewsindex.com" />
    <meta name="dc.creator.name" content="globalnewsindex.com" />
    <meta name="apple-mobile-web-app-title" content="globalnewsindex.com" />
    <meta property="og:type" content="article" />
</head>
<body>
	<script type="text/javascript">
		window.location = "/home.html?target=${url}";
	</script>
</body>
</html>