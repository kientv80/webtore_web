<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 	Column 1 menu -->
<c:if test="${device != 'mobile'}">
	<div class="container-fluid" style="height: 100%">
				<c:if test="${('kinhte'!=cate && giavang != null && giavang.size() > 0)}">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-left: 6px;">
						<div class="panel panel-default" style="margin-bottom:4px;">
							<div class="panel-heading"
								style="background-color: ${colorpicker.getColor(cate)} ;background-image: none;color: #fff;">
								<h3 class="panel-title" style="padding: 6px;">
									<b>Giá Vàng</b>
								</h3>
							</div>
							<div class="panel-body" style="padding: 0px; width: 100%;">
								<table class="table table-condensed" style="FONT-SIZE: SMALL;">
									<tr><th>Khu vực</th> <th>Mua</th> <th> Bán</th></tr>
									<tbody>
										<c:forEach items="${giavang }" var="gv">
											<tr><td>${gv.location }</td> <td>${gv.buy }</td> <td>${gv.salse }</td></tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${('kinhte'==cate)}">
					<div class="row" style="padding-bottom: 4px;">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-left: 6px;">
							<div class="container-fluid  w3-card-2">
								<img alt="" src="http://www.kitco.com/images/live/gold.gif" class="img-responsive" style="padding-bottom: 10px;">
								<img alt="" src="http://www.kitco.com/images/live/nygold.gif" class="img-responsive">
							</div>
						</div>
					</div>
				</c:if>

				<div class="row" style="padding-bottom: 4px;z-index: 2;" data-spy="affix" id="myAffix">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-left: 6px;">
						<div class="container-fluid  w3-card-2">
							<img class="card-img-top" src="/images/ads/mtsport.png" alt="Minh Thu Sport" style="width: 100%;">
							<div class="w3-container">
							    <h4 style="font-weight:bold; ">Minh Thư Sport</h4>
							    Chuyên cung cấp sỉ và lẻ dụng cụ thể thao.<br>
							    ĐC:4A-Tô Ký-P.Tân Chánh Hiệp-Q12-TP.HCM<br>
							    ĐT:0933834229
							 </div>
						</div>
						<div class="container-fluid  w3-card-2">
							<img src="http://static.new.tuoitre.vn/tto/r/2015/10/02/phongsuanh-300x80-1443758063.jpg" style="width: 100%;">	
						</div>
						<div class="container-fluid  w3-card-2">
							<img src="http://static.new.tuoitre.vn/tto/r/2015/11/16/thuc-hien-uoc-mo-banner-1447639859.jpg" style="width: 100%;">	
						</div>
						<div class="container-fluid" style="padding-top: 20px;">
							Copyright © 2015 by 360hay.com
						</div>
					</div>
					
				</div>

	</div>
	<script>
		function calculateAffix(){
			$('#myAffix').on('affixed.bs.affix',function(){
				$('#myAffix').css("top","60px");
			});
			$('#myAffix').on('affix.bs.affix',function(){
				var width = document.getElementById('myAffix').offsetWidth;
				$('#myAffix').css("width",width+"px");
			});
		}
		$(document).ready(function() {
			calculateAffix();
			swiper();
			var po = getPosition(document.getElementById('myAffix'));
			$('#myAffix').attr("data-offset-top",po.y);
		});

	</script>	
</c:if>

