<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid" style="padding: 0px;padding-top: 8px;">
      <div class="row" style=";background-color: #C6D4E1;">
        <c:forEach items = "${menuitems }" var="item">
        	<c:if test="${item.submenus.size() > 0 }">
        		<c:forEach items = "${item.submenus }" var="mn">
        			<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3" >
						<a href="${mn.href }" title="${mn.title }" style="padding: 4px;"><h4>${mn.lable }</h4></a>
					</div>
        		</c:forEach>
        	</c:if>
        	<c:if test="${item.submenus.size() == 0 }">
	        	<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3" >
					<a href="${item.href }" title="${item.title }" style="padding: 4px;"><h4>${item.lable }</h4></a>
				</div>
			</c:if>
		</c:forEach>
        <c:forEach items = "${othermenuitems }" var="item">
        	<c:if test="${item.submenus.size() > 0 }">
        		<c:forEach items = "${item.submenus }" var="mn">
        			<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3" >
						<a href="${mn.href }" title="${mn.title }" style="padding: 4px;"><h4>${mn.lable }</h4></a>
					</div>
        		</c:forEach>
        	</c:if>
        	<c:if test="${item.submenus.size() == 0 }">
	        	<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3" >
					<a href="${item.href }" title="${item.title }" style="padding: 4px;"><h4>${item.lable }</h4></a>
				</div>
			</c:if>
		</c:forEach>
      </div>
</div>


<div id="footer" class="container-fluid" style="padding-top: 10px;width: 100%;">
	<p>Copyright © 2015 by 360Hay.com</p>
</div>
