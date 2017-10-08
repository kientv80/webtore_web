<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid">
	<div class="row" style="text-align: center;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"
			style="text-align: center; padding: 0px;">
			<div class="container-fluid"
				style="text-align: center; padding: 0px;">
				<img alt="" src="/images/icons/findaround.gif"
					class="img-responsive">
			</div>
		</div>
	</div>
	<div class="row" style="text-align: center;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"
			style="text-align: center; padding: 0px;">
			<div class="container-fluid">
				<div class="container-fluid">
					<input type="text" class="form-control" id="searchText" placeholder="Search text" style="border-radius:0px;margin-top: 5px;">
				</div>
				<div class="container-fluid" style="width: 100%;text-align: center;">
					<select class="btn-primary" onchange="return seach();" id="searchtype" style="width: 50%;margin-top: 10px;text-align: center;height: 30px;border-radius:4px;">
						<c:forEach items="${searchTypes }" var="type">
							<option value="${type.type}" style="margin: 4px;">${type.label}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="currentLocation" value="">

<div id="searchResultContainer" class="container-fluid" style="margin-top: 20px;margin-left: 4px;"></div>
<script>
	function seach() {
		var type = $("#searchtype option:selected").val();
		getLocationAndSearch(type);
	}
	function getLocationAndSearch(type) {
		var fromApp = ClientCache.getCookie("fromAndroidApp");
		if (fromApp != null && fromApp != undefined && fromApp == "true") {
			try {
				var location = AndroidWebstoreAPIs.getLocation();
				var position = JSON.parse(location);
				if (position != null && position != undefined
						&& position != "null") {
					searchNearby(position,type);
				} else {
					alert("cannot get current location");
				}
			} catch (ex) {
				alert(ex);
			}
		} else {
			alert("Sorry, this is only supported on Android webstore app");
		}
	}

	function searchNearby(position,type) {
		$.ajax(
				{
					type : "GET",
					url : "/location/searchnearby?type=" + type + "&lat="
							+ position.latitude + "&lng=" + position.longitude
							+ "&radius=5000&name=" + $("#searchText").val(),
				}).done(function(result) {
			if (result.errorCode == "success" && result.places.length > 0) {
				var data = {
					places : result.places,
					currentlocation : result.currentlocation
				};
				var template = new EJS({
					url : '/js/ejs/templates/nearbysearchresult_v1.0.ejs'
				});
				$("#searchResultContainer").html(template.render(data));

			} else {
				$("#searchResultContainer").html("Không có kết quả phù hợp");
			}
		});

	}
	function loadMap(s_lat, s_lng, d_lat, d_lng, mapcontainer) {
		if ($("#currentLocation").val() == mapcontainer) {
			toggleMap(mapcontainer);
			return;
		}
		$("#link" + mapcontainer).text("Đóng Bản Đồ");
		$("#" + mapcontainer).css("height", "500px");
		$("#currentLocation").val(mapcontainer);
		var source = {
			lat : s_lat,
			lng : s_lng
		};
		var destination = {
			lat : d_lat,
			lng : d_lng
		};

		var map = new google.maps.Map(document.getElementById(mapcontainer), {
			center : source,
			scrollwheel : false,
			zoom : 7
		});

		var directionsDisplay = new google.maps.DirectionsRenderer({
			map : map
		});

		// Set destination, origin and travel mode.
		var request = {
			destination : destination,
			origin : source,
			travelMode : google.maps.TravelMode.DRIVING
		};

		// Pass the directions request to the directions service.
		var directionsService = new google.maps.DirectionsService();
		directionsService.route(request, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {
				// Display the route on the map.
				directionsDisplay.setDirections(response);
			}
		});
	}
	function toggleMap(id) {
		if ($("#" + id).css("display") == 'block'
				|| $("#" + id).css("display") == ''
				|| $("#" + id).css("display") == undefined) {
			$("#openmap" + id).text("Bản Đồ");
		} else {
			$("#openmap" + id).text("Đóng Bản Đồ");
		}
		$("#" + id).toggle("fast", "linear");
	}
	function placeDetail(placeid, containerid) {
		$.ajax({
			type : "GET",
			url : "/location/placedetail?placeid=" + placeid,
		}).done(
				function(result) {
					if (result.errorCode == "success") {
						var data = {
							placedetail : result.result
						};
						var template = new EJS({
							url : '/js/ejs/templates/placedetail.ejs'
						});
						$("#detail" + containerid).html(template.render(data));
					} else {
						$("#detail" + containerid).html(
								"Không có kết thông tin chi tiết");
					}
					//$("#detail" + containerid).toggle("fast", "linear");
				});
	}
</script>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD_lWvBcnRaXdGuR1kGoZzv3mnOx5QjAKs"
	async defer></script>
<script src="/js/jquery-ui.js"></script>


