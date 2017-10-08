<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/css/bootstrap_v07.min.css">
<div class="container-fluid" style="background-color: #fff;float: left;width: 100%;">
	<div class="list-group">
		<c:forEach items="${items}" var="item">
			<a href="${item.url }" class="list-group-item" style="font-weight: bold;;"><img alt="" src="${item.image }" style="padding-right: 20px; width: 50px;">${item.name }</a>
		</c:forEach>
	</div>
</div>
