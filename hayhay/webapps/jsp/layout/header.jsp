<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="container-fluid" style="background-color: #2b3843;width: 100%;padding: 0px;margin: 0px;">
		<div class="hidden-xs col-sm-12 col-md-12 col-lg-12" style="padding: 0px;">
			<div style="float: left;">
				<a href="/home.html">
			 		<span style="font-weight: bold;font-size: 30px;color: white;padding-left: 10px;">36<img src="/images/icons/earth.png" style="padding-bottom: 10px;"></span><span style="color: white;">hay</span>
			 	</a>
		 	</div>
<!-- 		 	<div style="float: right;padding-right: 10px;"> -->
<!-- 		 		<a href="/location/nearby"> -->
<!-- 			 		<span style="font-weight: bold;font-size: 30px;color: white;padding-left: 10px;"> -->
<!-- 			 		<img src="/images/icons/locationsearch.png" style="width:24px;height: 24px;"></span> -->
<!-- 			 		<span style="color: white;">Tìm Quanh Đây</span> -->
<!-- 			 	</a> -->
<!-- 		 	</div> -->
		</div>
	</div>
</div>

<div class="row" style="background-color: #3e5062;width: 100%;padding: 0px;margin: 0px;top: 0px;z-index: 10;"  data-spy="affix" id="myNavbar">
	<div class="hidden-xs hidden-sm col-md-1 col-lg-1" style="padding: 0px;">
	</div>
	<div class="col-xs-12 col-sm-12 col-md-11 col-lg-11" style="padding: 0px;">
		<div class="container-fluid" style="padding: 0px;">
			<nav  class="navbar navbar-inverse" style="margin-bottom: 0px;background-color: transparent;border-color: transparent;" id="header">
			    <div class="navbar-header" >
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar" style="margin-left: 10px;">
					  <span class="icon-bar"></span>
					  <span class="icon-bar"></span>
					  <span class="icon-bar"></span> 
					</button>
			    </div>
			    <div class="collapse navbar-collapse" id="navbar" style="max-height:400px;overflow-y:scroll; ">
			      <ul class="nav navbar-nav" >
					<!-- 	menu	-->
			        <c:forEach items = "${menuitems }" var="item">
			        	<c:if test="${item.submenus.size() > 0 }">
			        		<li class="dropdown">
					          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${item.lable } <span class="caret"></span></a>
					          <ul class="dropdown-menu">
					             <c:forEach items = "${item.submenus }" var="submenu">
					             	<li><a href="${submenu.href }" title="${submenu.title }" class="${item.cateId }">${submenu.lable }</a></li> 
					             </c:forEach>
					          </ul>
					        </li>
			        	</c:if>
			        	<c:if test="${item.submenus.size() == 0 }">
							<li style="padding-top: 5px;">
								<c:if test="${item.cateId == cate}">
									<a href="${item.href }" title="${item.title }" style="color:#fff;text-transform: uppercase;"><u>${item.lable }</u></a>
								</c:if>
								<c:if test="${item.cateId != cate}">
									<a href="${item.href }" title="${item.title }"  style="text-transform: uppercase;color: rgba(255, 255, 255, 0.6);">${item.lable }</a>
								</c:if>
							</li> 
						</c:if>
					</c:forEach>
					<li class="dropdown">
			      		<a title="Trang chính" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="padding-top:10px;">
			      			<img alt="" src="/images/icons/chuyenmuc.png" class="img-responsive" style="padding-right: 10px;width: 34px;height: 20px;" title="Danh mục khác">
			      		</a>
			      		<ul class="dropdown-menu" style="width: 300px;background-color: #3e5062;">
			      			<c:forEach items = "${othermenuitems }" var="item">
					        	<c:if test="${item.submenus.size() > 0 }">
					        		<li class="dropdown">
							          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${item.lable } <span class="caret"></span></a>
							          <ul class="dropdown-menu">
							             <c:forEach items = "${item.submenus }" var="submenu">
							             	<li><a href="${submenu.href }" title="${submenu.title }" class="${item.cateId }">${submenu.lable }</a></li> 
							             </c:forEach>
							          </ul>
							        </li>
					        	</c:if>
					        	<c:if test="${item.submenus.size() == 0 }">
									<li style="padding-top: 5px;">
										<c:if test="${item.cateId == cate}">
											<a href="${item.href }" title="${item.title }" style="color:#fff;text-transform: uppercase;"><u>${item.lable }</u></a>
										</c:if>
										<c:if test="${item.cateId != cate}">
											<a href="${item.href }" title="${item.title }"  style="text-transform: uppercase;color: rgba(255, 255, 255, 0.6);">${item.lable }</a>
										</c:if>
									</li> 
								</c:if>
							</c:forEach>
<!-- 							<li style="padding-top: 5px;"> -->
<!-- 								<a href="/location/nearby"> -->
<!-- 							 		<span style="font-weight: bold;font-size: 30px;color: white;padding-left: 10px;"> -->
<!-- 							 		<img src="/images/icons/locationsearch.png" style="width:24px;height: 24px;"></span> -->
<!-- 							 		<span style="color: white;">Tìm Quanh Đây</span> -->
<!-- 							 	</a> -->
<!-- 							 </li> -->
			      		</ul>
			      	</li> 
			      </ul>
			    </div>
			</nav>
		</div>
	</div>
</div>

<script>
	function selectNewsType(type){
		if(type=="worldnews"){
			document.cookie="newstype=worldnews;path=/";
		}else{
			document.cookie="newstype=vnnews;path=/";
		}
	}
</script>