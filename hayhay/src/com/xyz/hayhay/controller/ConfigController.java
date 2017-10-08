package com.xyz.hayhay.controller;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.hayhay.util.ConfigurationHelper;
import com.xyz.webstore.mobile.config.WebstoreMobileAppConfig;

@Controller
public class ConfigController extends BaseController {

	@RequestMapping(value = "/admin/config.html", method = RequestMethod.GET)
	public String vtvChannel(String key, String value, ModelMap map) {
		if("pageAccessToken".equals(key)){
			ConfigurationHelper.setDBValue("FB_pageAccessToken", value);
		}
		return "redirect:/admin/report.html";
	}
	
	@ResponseBody
	@RequestMapping(value = "/mobile/config", method = RequestMethod.GET)
	public void getMobileConfig(String version, HttpServletResponse resp) {
		try {
			writeJSONResponsed(resp, WebstoreMobileAppConfig.getConfig(version));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
