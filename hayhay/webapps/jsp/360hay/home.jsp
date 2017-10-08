<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid" style="padding: 0px;">
	<div class="row" style="padding: 0px;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding: 0px;">
			<div class="swiper-container" style="width: 100%">
				<div class="swiper-wrapper" style="width: 100%">
					<c:forEach items="${features }" var="feature">
							<div class="swiper-slide" style="width: 100%">
								<div class="container-fluid " style="width: 100%;padding: 0px;">
									<a  href="${feature.url }" >
										<img alt="" src="${feature.image }" class="img-responsive" style="width: 100%;">
									</a>
								</div>
								<a  href="${feature.url }" >
									<div class="container-fluid" style="width:100%; text-align:left ; position: absolute;bottom: 0;left: 0;background: rgba(0, 0, 0, 0.6);">
										<h3 style="color: #fff;text-transform: uppercase;">${feature.title }</h3>
										<h5 style="color: #fff;">${feature.desc }</h5>
									</div>
								</a>
							</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="row" style="padding:10px 0px  0px 0px;;margin-top: 10px;">
		<div class="container-fluid" style="padding: 0px;">
		    <c:forEach items="${features }" var="feature">
				<div class="container-fluid" style=";width: 33.333%;float: left;">
					<div class="container-fluid" style="background-color: #fff;margin: 1px;">
						<div class="container-fluid">
							<a  href="${feature.url }" style="color: black;    text-decoration: none;">
								<img alt="" src="${feature.thumeImage}" class="img-responsive">
							</a>
						</div>
						<div class="container-fluid w3-xlarge">
							<h6>
								<a  href="${feature.url }" style="color: #000;text-decoration: none;color:rgba(0, 0, 0, 0.75);text-transform: uppercase;text-align: center;font-weight: bold;">
									${feature.title}
								</a>
							</h6> 
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<script>
	swiperCategory();
</script>