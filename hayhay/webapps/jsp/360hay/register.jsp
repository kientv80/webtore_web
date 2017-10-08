<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="/css/bootstrap.min.css">
	<!-- Optional theme -->
	<link rel="stylesheet" href="/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="/css/w3.css">
	<script src="/js/jquery-2.1.3.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/css/360haylayout.css">
<head>
	<title>360hay!</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="author" content="360hay.com" />
	<meta content="vi-VN" />

</head>
<body class="container-fluid">
	<div class="container-fluid" style="padding: 0px;">
		<div class="row">
	      		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding: 0px;">
	      			<div class="w3-card-2">
		      			<div class="container-fluid">
		      				<img alt="" src="/images/icons/mobilecard.jpg" class="img-responsive">
		      			</div>
		      			<div class="container-fluid">
		      				Mỗi ngày 360hay sẽ chọn ra một bạn đọc ngẫu nhiên may mắn để trao một thẻ nạp tiền điện thoại có giá trị từ <b>10.000</b> tới <b>100.000 VNĐ</b>.<br>
		      				Để tham gia, bạn chỉ cần đọc tin tức trên 360hay một lần/ngày(đọc nhiều cơ hội nhiều).<br>
		      				Người may mắn sẽ được 360hay thông báo sau 5 giờ chiều mỗi ngày qua facebook, vì vậy các bạn vui lòng bấm vào nút "Tham Gia" để nhận quà nhé.
							<b>Chương trình này bắt đầu từ 14/3 tới 31/3/2016.</b><br>
							Mọi thắc mắc xin vui lòng gửi mail tới hotro@360hay.com
						</div>
						<div class="container-fluid">
							<select id="telco" class="form-control">
								<option value="">Chọn nhà mạng</option>
								<option value="Vietel">Vietel</option>
								<option value="Mobifone">Mobifone</option>
								<option value="Vinaphone">Vinaphone</option>
							</select>
						</div>
						<div class="container-fluid">
							<a href="#" onclick="register();" class="btn btn-primary" >Tham Gia</a>
						</div>
					</div>
	      		</div>
	     </div>
	</div>
<script type="text/javascript">
	function register(){
		var index = document.getElementById("telco").selectedIndex;
		var telco = document.getElementById("telco").options[index].value;
		if(telco == ""){
			alert("Vui lòng chọn nhà mạng bạn đang dùng đề chúng tôi gửi đúng loai thẻ nạp tiền cho bạn.");
		}else{
			document.cookie="telco=" + telco;
			window.location = "/fblogin.html?telco=" +telco;
		}
		
	}
</script>
</body>
</html>