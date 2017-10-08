package com.xyz.hayhay.controller;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xyz.hayhay.ads.AdsItem;


@Controller
public class AdsController {
	@Async
	@RequestMapping(value="/ads.html", method = RequestMethod.POST)
	public void loadAds(String zoneId,HttpServletResponse resp){
		if(zoneId == "home_z1"){
			AdsItem adsItem = new AdsItem("/images/ads/tmkff.png", "TMK Fast food", "Chuyên bán Hamburger, Nui/Mì xào, cơn trưa trong CVPM Quang Trung. ĐT:0933834229 ", null);
			JSONObject json = new JSONObject();
			json.put("image", adsItem.getImage());
			json.put("title", adsItem.getTitle());
			json.put("shotDesc", adsItem.getShotDesc());
			json.put("targetUrl", adsItem.getTargetUrl());
			json.toJSONString();
		}
	}
	@RequestMapping(value="/tangthecaomayman.html", method = RequestMethod.GET)
	public String tangthecaomayman(HttpServletResponse resp){
		return "tangthecaomayman";
	}
	
}
