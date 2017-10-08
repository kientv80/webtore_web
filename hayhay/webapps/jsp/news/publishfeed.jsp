<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row"  style="padding: 0px;">
	<div class="col-xs-0 col-sm-3 col-md-4 col-lg-4"></div>
	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4"  style="padding: 0px;color: white;">
		<div class="container-fluid" id="publishcontent"  style="padding: 0px;">
			<c:forEach items="${categories}" var="cate">
				<c:forEach items="${cate.news}" var="news">
					<div class="container-fluid w3-card-2" id="${news.id}" style="background-color: #fff;margin-bottom: 20px; padding: 0px;">
						<div class="container-fluid">
							<a  onclick="return showWebcontent('${news.url}',${news.isIs360hayArticle()},'${news.id}','${news.type}');">
								<img alt="" src="${news.imageUrl }" class="img-responsive" style="width: 100%;">
							</a>
						</div>
						<div class="container-fluid">
							<h2><a  onclick="return showWebcontent('${news.url}',${news.isIs360hayArticle()},'${news.id}','${news.type}');" style="color: black;text-decoration: none;">${news.title}</a></h2>
							<p>${news.shotDesc}</p>
						</div>
					</div>
				</c:forEach>
			</c:forEach>
			<div class="container-fluid w3-card-2"  style="background-color: #fff;margin-bottom: 20px; padding: 0px;">
				<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
				<!-- rightside_zone1 -->
				<ins class="adsbygoogle"
				     style="display:block"
				     data-ad-client="ca-pub-3722392996468717"
				     data-ad-slot="2402347787"
				     data-ad-format="auto"></ins>
				<script>
				(adsbygoogle = window.adsbygoogle || []).push({});
				</script>
			</div>
			
			<div class="container-fluid w3-card-2"  style="background-color: #fff;margin-bottom: 20px; padding: 0px;">
				<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
				<!-- zone4 -->
				<ins class="adsbygoogle"
				     style="display:inline-block;width:728px;height:90px"
				     data-ad-client="ca-pub-3722392996468717"
				     data-ad-slot="2254243785"></ins>
				<script>
				(adsbygoogle = window.adsbygoogle || []).push({});
				</script>
			</div>
			
		</div>
	</div>
	<div class="col-xs-0 col-sm-3 col-md-4 col-lg-4"></div>
</div>
