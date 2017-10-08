<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid" style="background-color: #fff;">
<c:forEach items="${news }" var="n">
	<div class="row" id="${n.id }" style="padding-bottom: 10px;border-bottom : 1px solid #e9eaed;">
		<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4"  style="padding: 0px;color: white;">
			<div class="container-fluid">
				<a class="mainarticletitle"  onclick="return showWebcontent('${n.url }',false,'${n.id }','${n.type }');">
					<img alt="" src="${n.imageUrl }" class="img-responsive mainarticleimage" style="width: 100%;">
				</a>
			</div>
		</div>
		<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8"  style="padding: 0px;color: white;">
			<div class="container-fluid" style="color: black;">
				<h3><a class="mainarticletitle"  onclick="return showWebcontent('${n.url }',false,'${n.id }','${n.type }');">${n.title }</a> </h3>
				<c:if test="${n.shotDesc != null && !n.shotDesc.isEmpty()}">
					<p>${n.shotDesc }</p>
				</c:if>
				<span class="website_info">(${ n.fromWebSite } ${ n.strDate })</span>
			</div>
		</div>
	</div>
</c:forEach>
</div>
<div class="container-fluid" style="padding: 0px;">
	<div class="row" style="padding: 0px;">
		<c:import url="footer.jsp" />
	</div>
</div>