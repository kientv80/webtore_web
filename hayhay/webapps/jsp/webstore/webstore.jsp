<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid" style="background-color: #fff;float: left;width: 100%;">
	<div class="container-fluid" style="float: left;width: 100%;">
		<ul class="nav nav-pills">
			<c:forEach items="${webstore}" var="w">
				<li role="presentation" id="tab_${w.name}"><a  onclick="return activeTab('${w.name}');">${w.title}</a></li>
			</c:forEach>
		</ul>
	</div>
	<div class="container-fluid" id="websiteCateContainer"  style="float: left;width: 100%;">
		<c:forEach items="${webstore}" var="w">
			<div class="container-fluid" id="${w.name }" style="display: none;float: left;width: 100%;background-color: #ecf0f5">
				<c:forEach items="${w.websites}" var="website">
					<div style="width: 33.33%;float: left;background-color: #fff; border: 1px solid #e9eaed;padding: 10px;text-align: center;">
						<div class="container-fluid">
							<a href="${website.url }">
								<img alt="" class="img-responsive" src="${ website.icon}"  onclick="openUrl('${website.url}');" style="width: 100%;">
							</a>
						</div>
						<div class="container-fluid" style="border-top : 1px solid #e9eaed;padding: 0px;padding-top:6px; text-align: center;">
							<a  style="color:#7f7f7f;font-weight: bold;">Quan Tâm</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
</div>

<script>
	var currentTab;
	function activeTab(id){
		if(currentTab == undefined || currentTab == null)
			currentTab = id;
		$("#tab_"+currentTab).removeClass();
		$("#tab_"+id).removeClass();
		$("#tab_"+id).addClass("active");
		$("#"+currentTab).css("display","none");
		$("#"+id).css("display","block");
		currentTab = id;
	}
	activeTab("news");//active default tab
	function openUrl(url){
		window.location=url;
	}
</script>