package com.xyz.hayhay.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.hayhay.service.user.Profile;
import com.xyz.hayhay.service.user.ProfileService;
import com.xyz.hayhay.util.JSONHelper;

@Controller
public class ProfileController extends BaseController {

	@ResponseBody
	@RequestMapping(value = "/getprofile", method = { RequestMethod.GET })
	public void getProfile(String profileid, HttpServletResponse resp) throws Exception, SQLException {
		System.out.println("/profile profileid=" + profileid);
		Profile p = null;
		if (profileid != null && !profileid.isEmpty()) {
			p = ProfileService.getInstance().getProfile(profileid);
		} else {
			p = new Profile();
			p.setId("visitor_" + System.nanoTime());
		}
		JSONObject result = JSONHelper.toJSONObject(p);
		System.out.println("profile=" + result.toJSONString());
		result.put("errorCode", RESULT.SUCCESS.ordinal());
		writeJSONResponsed(resp, result);
	}

	@ResponseBody
	@RequestMapping(value = "/saveprofile", method = { RequestMethod.POST })
	public void saveProfile(String profile, HttpServletResponse resp) throws Exception, SQLException {
		System.out.print(profile);
		JSONObject jprofile = (JSONObject) new JSONParser().parse(profile);
		Profile p = null;
		String id = jprofile.containsKey("id") ? jprofile.get("id").toString() : null;
		p = ProfileService.getInstance().getProfile(id);
		if (p == null) {
			String name = jprofile.containsKey("name") ? jprofile.get("name").toString() : null;
			String firstName = jprofile.containsKey("firstName") ? jprofile.get("firstName").toString() : null;
			String lastName = jprofile.containsKey("lastName") ? jprofile.get("lastName").toString() : null;
			String avatar = jprofile.containsKey("avatar") ? jprofile.get("avatar").toString() : null;
			String token = jprofile.containsKey("token") ? jprofile.get("token").toString() : null;
			String permissions = jprofile.containsKey("permissions") ? jprofile.get("permissions").toString() : null;
			String declinedPermissions = jprofile.containsKey("declinedPermissions")
					? jprofile.get("declinedPermissions").toString() : null;
			p = new Profile(id, name, firstName, lastName, avatar, token, permissions, declinedPermissions);
			if (id != null && !id.isEmpty()) {
				p = ProfileService.getInstance().saveProfile(p);
			}
		}else{
			//Compare and update if any change
		}
		JSONObject result = new JSONObject();
		if (p != null) {
			result = JSONHelper.toJSONObject(p);
			result.put("errorCode", RESULT.SUCCESS.ordinal());
		}else{
			result.put("errorCode", RESULT.FAILED.ordinal());
		}
		writeJSONResponsed(resp, result);
	}
}
