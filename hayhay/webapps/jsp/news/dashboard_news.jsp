<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!-- 	Gia vang -->
<c:if test="${('kinhte'==cate && giavang != null && giavang.size() > 0)}">
	<div class="container-fluid" style="padding: 2px 0px 15px 0px;">
			<div class="row w3-card-2"   style="padding: 0px; background-color:#fff ;color: white;">
				<div class="container-fluid" style="padding: 0px;margin-bottom:8px;border-bottom : 4px solid ${colorpicker.getColor(category.cateId)}  !important; padding-left: 4px;">
					<h5><a style=" text-decoration: none;color:${colorpicker.getColor('')};font-weight: bold;">Giá Vàng</a></h5>
				</div>
				<div class="container-fluid" style="padding: 4px;" id="${category.cateId}_container">
					<!-- Hot news -->
					<div class="row" style="padding: 0px 4px 0px 4px;">
						<!-- Cate header -->
						<c:if test="${category.name != null && category.name.length() > 0}">
							<div class="container-fluid" style="padding: 0px;margin-bottom:8px;border-bottom : 4px solid ${colorpicker.getColor(category.cateId)}  !important;">
								<h5><a href="/news.html?from=${from }&cate=${category.cateId }" style=" text-decoration: none;color:${colorpicker.getColor(category.cateId)};font-weight: bold;">${category.name }</a></h5>
							</div>
						</c:if>
						<!-- Main news -->
						<div class="col-xs-12 col-sm-7 col-md-6 col-lg-6"  style="padding: 0px;">
							<div class="container-fluid" style="padding: 0px;" id="giavang">
								<div class="container-fluid" style="padding: 0px;">
								  	<a style="text-decoration: none;"> 
										<img class="img-responsive" src="/images/icons/gold.png" alt="" style="width: 100%;">
									</a>
								</div>
								<h3 style="padding-top: 10px;" class="mainarticletitle">Giá vàng</h3>
								<p>
									Bảng giá vàng cập nhật mới nhất
									<br><span class="website_info">giavang.doji.com</span>
								</p>
							</div>
						</div>
						<!-- hot  news -->
						<div class="col-xs-12 col-sm-5 col-md-6 col-lg-6"  style="padding: 0px;padding-left: 5px;">
							<div class="container-fluid" style="padding: 0px;">
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
					<c:if test="${device == 'mobile'}">
						<div class="row" style="padding: 0px 4px 0px 4px;">
							<div class="col-xs-12"  style="padding: 0px;">
								<div class="container-fluid" >
									<img alt="" src="http://www.kitco.com/images/live/gold.gif" class="img-responsive">
								</div>
							</div>
							<div class="col-xs-12"  style="padding: 0px;">
								<div class="container-fluid" >
									<img alt="" src="http://www.kitco.com/images/live/nygold.gif" class="img-responsive">
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
</c:if>
<!-- 		End Gia Vang -->

<!-- 		News -->
<div id="newsContainer" style="min-height: 100%;"></div>
<c:if test="${device != 'mobile'}">
	<div class="container-fluid" style="padding: 2px 0px 15px 0px;">
		<div class="row w3-card-2"   style="padding: 0px; background-color:#fff ;color: white;width: 100%;">
<!-- 			<iframe id="caooc-728X90" width="100%" height="90" frameborder="0" src="http://s.tuoitre.vn/images/Folder5/2015-10-31-01-00-13_caooc-728X90/caooc-728X90.html?link=http://s.tuoitre.vn/DominoX/click.ashx?ID=6785;fff4ce74-30df-4acb-bd09-c0e1da34211a;http://tuoitre.vn/"></iframe> -->
		</div>
	</div>
</c:if>
<div class="container-fluid" style="padding: 2px 0px 15px 0px;">
	<div class="row w3-card-2"   style="padding: 0px; background-color:#fff ;color: white;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding: 0px;">
			<c:import url="/jsp/layout/footer.jsp" />
		</div>
	</div>
</div>

<script type="text/javascript">
	var cates = ${categories};
	renderNewsHTML('${from}',${fromIndex},cates,'${cate}');
</script>

