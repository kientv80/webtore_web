<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!-- 		News -->
<div class="row" style="padding: 0px;">
<!-- 	Hot news -->
	<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8"  style="padding: 0px;border-bottom : 0px solid #e9eaed  !important;">
		<div id="hotNewsContainerId" style="min-height: 100%;"></div>
	</div>
<!-- 	side news -->
	<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"  style="padding-left: 4px;padding-right: 0px;border-bottom : 0px solid #e9eaed  !important;">
		<div id="sideNewsContainerId" style="min-height: 100%;"></div>
	</div>
</div>




<c:if test="${device != 'mobile'}">
	<div class="container-fluid" style="padding: 2px 0px 15px 0px;">
		<div class="row w3-card-2"   style="padding: 0px; background-color:#fff ;color: white;width: 100%;">
		</div>
	</div>
</c:if>
<div class="container-fluid" style="padding: 2px 0px 15px 0px;">
	<div class="row w3-card-2"   style="padding: 0px; background-color:#fff ;color: white;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding: 0px;">
			<c:import url="/jsp/layout/footer.jsp" />
		</div>
	</div>
</div>

<script type="text/javascript">
	var cates = ${categories};
	renderNewsHTML('${from}',${fromIndex},cates,'${cate}');
	var fromIndex=${fromIndex};
	var cate = '${cate}';
	
	function loadMore(){
	    if(fromIndex > 100){
	      return;
	    }
	    fromIndex+=10;
	    loadNews2('${from}',fromIndex, cate);
	}
	var loading = false;
	View.scroll(View.scrollPosition._40_PERCENT,function(){
	  if(loading == false){
	    loading = true;
	    loadMore();
	    loading = false;
	  }
	});	
	
</script>

