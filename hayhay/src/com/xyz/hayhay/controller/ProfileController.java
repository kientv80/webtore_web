package com.xyz.hayhay.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.hayhay.service.user.Person;
import com.xyz.hayhay.service.user.ProfileService;
import com.xyz.hayhay.util.JSONHelper;
import com.xyz.hayhay.util.ValidationHelper;

@Controller
public class ProfileController extends BaseController{

	@ResponseBody
	@RequestMapping(value = "/profile", method = { RequestMethod.POST})
	public void getProfile(String profileid,String deviceid,String deviceinfo,HttpServletResponse resp) throws Exception, SQLException{
		Person p = null;
		if(profileid != null && ValidationHelper.isLong(profileid)){
			p = ProfileService.getInstance().getPerson(Long.parseLong(profileid));
		} else if( deviceid != null && !deviceid.isEmpty()){
			List<Person> ps = ProfileService.getInstance().getPersonByDeviceId(deviceid);
			if(ps == null || ps.isEmpty() || ps.size() > 1){
				p = new Person();
				p.setDeviceid(deviceid);
				p.setDeviceinfo(deviceinfo);
				p = ProfileService.getInstance().addPerson(p);
			} else if(ps.size() == 1){
				p = ps.get(0);
			}
		}
		if(p != null)
			writeJSONResponsed(resp, JSONHelper.toJSONObject(p));
	}
}
