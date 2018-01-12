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

<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
  Launch demo modal
</button>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Read news writen in follow languages</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
	    <form>
	      <div class="form-group">
		<label for="english">English</label>
		<input type="checkbox" class="form-check-input" id="english">
		<small id="englishHelp" class="form-text text-muted">Read news in English</small>
	      </div>
	      <div class="form-group">
		    <label for="vn">Vietnamese</label>
		<input type="checkbox" class="form-check-input" id="vn">
		<small id="vnlHelp" class="form-text text-muted">Read news in Vietnamese</small>
	      </div>
	      <div class="form-check">
		    <label for="chinese">Chinese</label>
		<input type="checkbox" class="form-check-input" id="chinese">
		<small id="chineselHelp" class="form-text text-muted">Read news in Chinese</small>
	      </div>
	    </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="return saveLanguageSettings();" >Save</button>
      </div>
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
	function saveLanguageSettings(){
		var settings = undefined;
		var profile = ClientCache.getLocalCacheJSON('profile');
		var params = {};
		if(profile == null || profile == undefined){
			HTTPClient.callSyncAjaxService("http://globalnewsindex.com/profile?profileid=",params,"POST","profile");
			profile = ClientCache.getLocalCacheJSON('profile');
			if(profile != undefined && profile.id > 0){
			  ClientCache.setCookie("uid",profile.id);
			}
		}else{
		  ClientCache.setCookie("uid",profile.id);
		}
		if(profile != null && profile != undefined){
			HTTPClient.callSyncAjaxService("http://globalnewsindex.com/mobile/settings/get?uid=" + profile.id  + "&option=favorite_languages",params,"GET","settings");
			settings = ClientCache.getLocalCacheJSON('settings');
		}
		if(settings!=undefined){
		  if($("#vn:checked").val() != undefined && $("#vn:checked").val()=="on"){
		    settings.settings[0].value=true;
		  }else{
		    settings.settings[0].value=false;
		  }
		  if($("#english:checked").val() != undefined && $("#english:checked").val()=="on"){
		    settings.settings[1].value=true;
		  }else{
		    settings.settings[1].value=false;
		  }

		  if($("#chinese:checked").val() != undefined && $("#chinese:checked").val()=="on"){
		    settings.settings[2].value=true;
		  }else{
		    settings.settings[2].value=false;
		  }
		  params={"uid":profile.id,"settings":JSON.stringify(settings)};
		  
		  HTTPClient.callSyncAjaxService("http://globalnewsindex.com/mobile/settings/update?a=1",params,"POST",);
		  $('#exampleModal').modal('toggle');
		}
			
	}
	function loadLanguageSettings(){
	  var settings = undefined;
		var profile = ClientCache.getLocalCacheJSON('profile');
		var params = {};
		if(profile == null || profile == undefined){
			HTTPClient.callSyncAjaxService("http://globalnewsindex.com/profile?profileid=",params,"POST","profile");
			profile = ClientCache.getLocalCacheJSON('profile');
		}
		if(profile != null && profile != undefined){
			HTTPClient.callSyncAjaxService("http://globalnewsindex.com/mobile/settings/get?uid=" + profile.id  + "&option=favorite_languages",params,"GET","settings");
			settings = ClientCache.getLocalCacheJSON('settings');
		}
		if(settings!=undefined){
		  if(settings.settings[0].value==true){
		    $("#vn").attr('checked','checked');
		  }else{
		    $("#vn").attr('checked','');
		  }
		   if(settings.settings[1].value==true){
		    $("#english").attr('checked','checked');
		  }else{
		    $("#english").attr('checked','');
		  }
		   if(settings.settings[2].value==true){
		    $("#chinese").attr('checked','checked');
		  }else{
		    $("#chinese").attr('checked','');
		  }
		}
	}
	function bindLanguageSettingsData(){
	  $('#exampleModal').on('shown.bs.modal', function () {
	    loadLanguageSettings();
	  });
	}
	function languageSettings(){
	  var settings = ClientCache.getLocalCacheJSON('settings');
	  if(settings == undefined){
	    $('#exampleModal').modal('show');
	  }
	}
	
	$(document).ready(function() {
	  bindLanguageSettingsData();
	  languageSettings();
	});
</script>