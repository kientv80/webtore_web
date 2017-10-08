package com.xyz.hayhay.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vnsoftware.facebook.HTTPClient;
import com.vnsoftware.facebook.Param;

@Controller
public class FBLoginControler {
	Logger log = Logger.getLogger(FBLoginControler.class);
	
	
	@RequestMapping(value="/fblogin", method=RequestMethod.GET)
	public void fblogin(HttpServletRequest request, HttpServletResponse resp){
		try {
			resp.sendRedirect("https://www.facebook.com/dialog/oauth?client_id=1489327961379891&response_type=code&scope=public_profile,user_friends,email&redirect_uri=http://360hay.com/fblogincallback");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/fblogincallback", method=RequestMethod.GET)
	public void fblogincallback(HttpServletRequest req, HttpServletResponse resp){
		String code = req.getParameter("code");
		log.info(">>>>>>>>>>>>>>> fblogincallback code = " + code);
		if(code != null && !code.isEmpty()){
			try {
				List<Param> params = new ArrayList<Param>();
				params.add(new Param("client_id", "1489327961379891"));
				params.add(new Param("client_secret", "553e0a5ed5fa3c1ede22b7df3f5d7e43"));
				params.add(new Param("code", code));
				params.add(new Param("redirect_uri", "http://360hay.com/fblogincallback"));
				String response = HTTPClient.executeHttpGet("https://graph.facebook.com/v2.4/oauth/access_token", params);
				JSONObject jobj =  (JSONObject)new JSONParser().parse(response);
				String accessToken = jobj.get("access_token").toString();
				log.info(">>>>>>>>>>>>>>> AccessToken = " + accessToken + " ======== " + response);
				
				response = HTTPClient.executeHttpGet("https://graph.facebook.com/v2.4/oauth/access_token?client_id=1489327961379891&client_secret=553e0a5ed5fa3c1ede22b7df3f5d7e43&grant_type=client_credentials",null);
				JSONObject appAccessToken =  (JSONObject)new JSONParser().parse(response);
				log.info(">>>>>>>>>>>>>>> appAccessToken ========= "  +appAccessToken.get("access_token").toString());
				response = HTTPClient.executeHttpGet("https://graph.facebook.com/v2.4/debug_token?method=get&access_token="+appAccessToken.get("access_token").toString()+"&input_token="+ accessToken, null);
				log.info(">>>>>>>>>>>>>>> debug_token ========= "  + response);
				JSONObject jobj2 =  (JSONObject)new JSONParser().parse(response);
//				if("true".equals(jobj2.get("is_valid").toString())){
					String userId = jobj2.get("user_id").toString();
					response = HTTPClient.executeHttpGet("https://graph.facebook.com/v2.4/"+ userId + "?method=get&fields=id,name,email,cover,picture,gender,hometown,last_name,first_name&access_token="+accessToken, null);
					log.info(">>>>>>>>>>>>>>> Profile ========= " + response);
					JSONObject profile =  (JSONObject)new JSONParser().parse(response);
					log.info(">>>>>>>>>>>>>>> Name ========= " + profile.get("name"));
					log.info(">>>>>>>>>>>>>>> Id ========= "  + profile.get("id"));
					log.info(">>>>>>>>>>>>>>> Cover ========= "  + profile.get("cover"));
					log.info(">>>>>>>>>>>>>>> email ========= "  + profile.get("email"));
					
//				}else{
//					log.info(">>>>>>>>>>>>>>> Seomething went wrong ========= ");
//				}
				log.info(">>>>>>>>>>>>>>> AccessToken ========= " + response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			log.info(">>>>>>> Somethings go wrong>>>>>>>> ");
		}
		System.out.println("Login callback"+req.getRequestURL());
	}

}
