<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid" style="background-color: #fff;float: left;width: 100%;">
	<div class="container-fluid">
		<div class="form-group">
			<div class="form-group">
				<label>Chọn Ngày</label>
				<select id="date"  onchange="return filterLottery();" class="form-control">
					<option value=""></option>
					<c:forEach items="${days}" var="d">
						<option value="${d}">${d}</option>
					</c:forEach>
				</select>
			</div>
			<label>Chọn tỉnh/TP cần tra kết quả</label>
			<select id="agency" onchange="return filterLottery();" class="form-control">
				<option value=""></option>
				<c:forEach items="${agency}" var="a">
					<option value="${a.key }">${a.value }</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<label id ="lbDate" style="width: 100%;text-align: center;color: red;font-size: 20px">Kết quả xổ số toàn quốc ngày ${date}</label>
	<div class="container-fluid" id="lotterycontainer"  style="float: left;width: 100%;padding:0px;">
	</div>
</div>
<script>
		var data = ${data};
		renderLotteryHTML(data);

</script>