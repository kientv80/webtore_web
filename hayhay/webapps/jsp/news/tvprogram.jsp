<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<span style="text-align: center;width: 100%;font-weight: bold;"><h1>Lịch phát sóng truyền hình</h1></span>
<div class="row" style="padding-top: 20px;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"  style="padding: 0px;">
			<div class="container-fluid" style="background-color: transparent ;padding: 0px;">
			<ul class="nav nav-pills" role="tablist">
				<li>
					<div class="dropdown">
						  <button id="btnChonDai" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						   	<c:if test="${tvstation != null && !tvstation.isEmpty() }">
						   		<span id="btnChonDaiText">${tvstation}</span>
						   	</c:if>
						   	<c:if test="${tvstation == null || tvstation.isEmpty() }">
						   		<span id="btnChonDaiText">Tất cả đài truyền hình</span>
						   	</c:if>
						    <span class="caret"></span>
						  </button>
						  <ul class="dropdown-menu" aria-labelledby="btnChonDai">
							<c:forEach items="${ tvStations}" var="tv">
								<li>
									<a href="#" onclick="return selectTV('${tv.name}','btnChonDaiText');"> <img src="${tv.logo}" alt="" style="width: 100px;height: 50px;"></a>
								</li>
							</c:forEach>
						  </ul>
					</div>
				</li>

				<li>
					<div class="dropdown">
						  <button id="btnChonChannel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						   	<c:if test="${channel != null && !channel.isEmpty() }">
						   		<span id="btnChonChannelText">${channel}</span>
						   	</c:if>
						   	<c:if test="${channel == null || channel.isEmpty() }">
						   		<span id="btnChonChannelText">Tất cả các kênh</span>
						   	</c:if>
						    <span class="caret"></span>
						  </button>
						  <ul class="dropdown-menu" aria-labelledby="btnChonChannel">
							<c:forEach items="${channels}" var="c">
								<li>
									<a href="#" onclick="return selectChanel('${c.id}','btnChonChannelText','${c.name}');">${c.name}</a>
								</li>
							</c:forEach>
						  </ul>
					</div>
				</li>
			</ul>
			</div>
		</div>
</div>
<div class="row" style="padding-top: 20px;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"  style="padding: 0px;">
			<div class="container-fluid" style="background-color: transparent ;padding: 0px;">
				<c:if test="${runningPrograms != null}">
					<div class="row" style="border-bottom: 1px solid #ccc;">
						<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3"  style="padding: 0px;">
							<span style="padding: 4px;font-weight: bold;">Thời gian</span>
						</div>
						<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3"  style="padding: 0px;">
							<span style="padding: 4px;font-weight: bold;">Kênh</span>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"  style="padding: 0px;">
							<span style="padding: 4px;font-weight: bold;">Chương trình</span>
						</div>
					</div>
					<c:forEach items="${runningPrograms}" var="p">
						<div class="row"  style="border-bottom: 1px solid #ccc;">
							<div class="col-xs-2 col-sm-3 col-md-3 col-lg-3"  style="padding: 0px;">
								<span style="padding: 4px;color: #337ab7;">${p.time}</span>
							</div>
							<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3"  style="padding: 0px;">
								<span style="padding: 4px;color: #449d44;">${p.channelName}</span>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"  style="padding: 0px;">
									<span style="padding: 4px;">${p.title}
										<c:if test="${p.programName != null && !p.programName.isEmpty() }">
											(${p.programName})
										</c:if>
									</span>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
</div>
<script type="text/javascript">
	var tv = "${tvstation}";
	var programe = "";
	var hour2 = "${hour}";
	function selectTV(tvStation,controlId){
		$("#"+controlId).text(tvStation);
		tv = tvStation;
		window.location = "/entertainment/tvprogram?tvstation=" + tv ;
	}
	function selectChanel(chanel,controlId, chanelName){
		$("#"+controlId).text(chanel);
		programe = chanel;
		window.location = "/entertainment/tvprogram/filter?tvstation=" + tv + "&id=" +  chanel + "&chanelName=" + chanelName;
	}
	function selectHour(hour,controlId){
		$("#"+controlId).text(hour);
		hour2 = hour;
		window.location = "/entertainment/tvprogram/filter?tvstation=" + tv + "&hour=" +  hour2;
	}
</script>