package com.xyz.hayhay.controller.thirparties;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.hayhay.controller.BaseController;
import com.xyz.hayhay.location.bean.LocationService;
import com.xyz.hayhay.location.entirty.Location;

@Controller
public class LocationController extends BaseController{
	
	@RequestMapping(value = "/location/nearby", method = RequestMethod.GET)
	public String nearBy(ModelMap map){
		map.put("searchTypes", LocationService.getSearchTypes());
		return "search";
	}
	@ResponseBody
	@RequestMapping(value = "/location/searchnearby", method = RequestMethod.GET)
	public void searchNearBy(String type, String lat, String lng, String radius,String name, HttpServletResponse resp){
		JSONObject result = LocationService.searchNearby(new Location(lat, lng), Integer.valueOf(radius), type, name);
		writeSimpleJSONObjectResponse(resp, result);
	}
	@ResponseBody
	@RequestMapping(value = "/location/placedetail", method = RequestMethod.GET)
	public void placeDetail(String placeid, HttpServletResponse resp){
		JSONObject result = LocationService.getPlaceDetail(placeid);
		writeSimpleJSONObjectResponse(resp, result);
	}
}

