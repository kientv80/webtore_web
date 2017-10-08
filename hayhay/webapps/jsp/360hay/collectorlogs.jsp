<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid">
	<a href="/collectorlogs.html" style="color: black;">All</a> | <a href="/collectorlogs.html?status=error" style="color: black;">Error</a> | <a href="/collectorlogs.html?status=warning" style="color: black;">Warning</a>
	<br><br>
	<table class="table">
		<thead>
			<tr>
				<th>Id</th>
				<th>Collected Time</th>
				<th>Collector Name</th>
				<th>Found News</th>
				<th>New News</th>
				<th>Status</th>
				<th>Error</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${collectorlogs }" var="log">
				<tr>
					<td>${log.id }</td>
					<td>${log.collectedTime }</td>
					<td>${log.name }</td>
					<td>${log.foundNews }</td>
					<td>${log.newNews }</td>
					<c:choose>
						<c:when test="${log.status=='sucess'}">
							<td style="color: green;font-weight: bold;">${log.status }</td>
						</c:when>
						<c:when test="${log.status=='error'}">
							<td style="color: red;;font-weight: bold;">${log.status }</td>
						</c:when>
						<c:otherwise>
        					<td style="color: orange;;font-weight: bold;">${log.status }</td>
    					</c:otherwise>
					</c:choose>
					<td>${log.error }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
