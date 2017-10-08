<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"  style="padding: 0px;background-color:#428bca;color: white;">
		<div class="container-fluid">
<!-- 		Parse link -->
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"  style="padding: 0px;background-color:#428bca;color: white;">
				<div class="input-group">
				  <input id = "linkUrl" type="text" class="form-control" placeholder="url" aria-describedby="url">
			      <span class="input-group-btn">
			        <button class="btn btn-default" type="button" onclick="return getLinkInfo();">Get Info</button>
			      </span>
			      
				  <select  class="form-control" style="padding-left: 10px;" id="selectType" onchange="return loadNews();">
				  	<option value="" >Select type</option>
				  	<option value="MMALEARNING" >MMALEARNING</option>
				  	<option value="KICKBOXINGLEARNING" >KICKBOXINGLEARNING</option>
				  	<option value="BOXING" >BOXING</option>
				  	<option value="JUDOLEARNING" >JUDOLEARNING</option>
				  	<option value="YOGALEARNING">YOGALEARNING</option>
				  	<option value="HOCBADMINTON">BADMINTON</option>
				  	<option value="HOCTENNIS">TENNIS</option>
				  	<option value="HOCTAICHINH">HOCTAICHINH</option>
				  	<option value="HOCKTVMVM">HOC KINH TẾ VI MÔ, VĨ MÔ</option>
				  	
				  	<option value="NHACVANG">NHẠC VÀNG</option>
				  	<option value="NHACDO">NHẠC ĐỎ</option>
				  	<option value="NHACTRE">NHẠC TRẺ</option>
				  	<option value="NHACTHIEUNHI">NHAC THIẾU NHI</option>
				  	<option value="NHACKHONGLOI">NHAC KHÔNG LỜI</option>
				  	<option value="NHACNUOCNGOAI">NHẠC NƯỚC NGOÀI</option>
				  	<option value="NHACTET">NHẠC TẾT</option>
				  </select>
			      <span class="input-group-btn">
			        <button class="btn btn-default" type="button" onclick="return saveNews();">Save</button>
			      </span>
				</div>
			</div>
		</div>
<!-- 		view link info	 -->
		<div class="row">
			<form action="/admin/addarticle.html" method="post" id="formAddNews">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6"  style="padding: 0px;background-color:#428bca;color: white;">
					<img id = "linkImage" alt="" src="" class="img-responsive">
					<input type="hidden" id="image" name = "image">
					<input type="hidden" id="type" name="type">
					<input type="hidden" id="url" name="url">
					<input type="hidden" id="website" name="website">
				</div>
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6"  style="padding: 0px;background-color:#428bca;color: white;">
					<input id = "linkTitle" name = "title" type="text" class="form-control" placeholder="url" aria-describedby="url">
					<textarea id = "linkDesc" name = "desc"  class="form-control" placeholder="url" aria-describedby="url"></textarea>
				</div>
			</form>
		</div>
<!-- 		list news -->
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"  style="padding: 0px;background-color:#428bca;color: white;">
				<div class="container-fluid">
					<c:forEach items = "${news }" var="newsItem">
						<div class="row">
							<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"   style="padding: 0px;background-color:#428bca;color: white;">
								<img alt="" src="${newsItem.imageUrl }" class="img-responsive"  style="width: 80px; height: 70px;">
							</div>
							<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3"   style="padding: 0px;background-color:#428bca;color: white;">
								<span>${newsItem.title}</span>
							</div>
							<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5"   style="padding: 0px;background-color:#428bca;color: white;">
								<span>${newsItem.shotDesc}</span>
							</div>
							<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"   style="padding: 0px;background-color:#428bca;color: white;text-align: right;">
								Delete
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		
		</div>
	</div>
</div>
<script type="text/javascript">
	function getLinkInfo(){
		$.ajax({
			url : "/getLinkInfo.html?url=" + $("#linkUrl").val(),
			success : function(linkInfo) {
				$("#linkTitle").val(linkInfo.title);
				$("#linkDesc").text(linkInfo.desc);
				$("#linkImage").attr("src",linkInfo.image);
				$("#image").val(linkInfo.image);
				$("#url").val($("#linkUrl").val());
				$("#website").val(linkInfo.website);
			}
		});
	}
	function loadNews(){
// 		var selectItem = document.getElementById("selectType");
// 		var value = selectItem.options[selectItem.selectedIndex].value;
// 		window.location="/mngarticle.html?type=" + value+"&index="+selectItem.selectedIndex;
	}
	function saveNews(){
		var item = document.getElementById("selectType");
		var value = item.options[item.selectedIndex].value;
		$("#type").val(value);
		document.getElementById("formAddNews").submit();		
	}
</script>
