package com.xyz.hayhay.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vnsoftware.facebook.FacebookService;
import com.vnsoftware.facebook.HTTPClient;
import com.xyz.hayhay.socialplugin.SocialServiceFactory;
import com.xyz.hayhay.util.ConfigurationHelper;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;

@Controller
public class LoginController extends BaseController {
	Logger log = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/login.html", method = { RequestMethod.POST, RequestMethod.GET })
	public String login(String userName, String password, HttpServletRequest req, HttpServletResponse rep) throws IOException {
		if ("admin".equals(userName) && "Welcome1VND".equals(password)) {
			req.getSession(true).setAttribute("login", true);
			req.getSession().setAttribute("authen", "true");
			if ("production".equals(ConfigurationHelper.getInstance().getValue("env"))) {
				Facebook facebook = new FacebookFactory().getInstance();
				facebook.setOAuthAppId("1641687746054225", "654f6acd0be6ae31d96de1e85f60a303");
				facebook.setOAuthPermissions("publish_actions,publish_pages,manage_pages");
				SocialServiceFactory.getFaceBookService().setFacebook(facebook);
				String callbackURL = ConfigurationHelper.getInstance().getValue("domain") + "admin/callback.html";
				rep.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));
			}
			return "redirect:/admin/mngarticle.html";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/logout.html", method = { RequestMethod.POST, RequestMethod.GET })
	public String logout(String userName, String password, HttpServletRequest req) {
		req.getSession(true).removeAttribute("login");
		return "redirect:home.html";
	}

	@RequestMapping(value = "/admin/callback.html", method = { RequestMethod.POST, RequestMethod.GET })
	public String fbCallback(HttpServletRequest req) throws Exception {
		if (req.getSession(true).getAttribute("login") != null) {
			try {
				Facebook facebook = SocialServiceFactory.getFaceBookService().getFacebook();
				String oauthCode = req.getParameter("code");
				SocialServiceFactory.getFaceBookService().getFacebook().getOAuthAccessToken(oauthCode);
				ConfigurationHelper.setDBValue("FB_oauthCode", oauthCode);
				String longLiveAccessToken = HTTPClient.executeHttpGet("https://graph.facebook.com/v2.3/oauth/access_token?grant_type=fb_exchange_token&client_id=1641687746054225&client_secret=654f6acd0be6ae31d96de1e85f60a303&fb_exchange_token=" + facebook.getOAuthAccessToken().getToken(), null);
				System.out.println("longLiveAccessToken=" + longLiveAccessToken);
				JSONObject objllAccessToken = (JSONObject)new JSONParser().parse(longLiveAccessToken);
				longLiveAccessToken = objllAccessToken.get("access_token")+"";
				ConfigurationHelper.setDBValue("FB_OAuthAccessToken", longLiveAccessToken);
				String result  = HTTPClient.executeHttpGet("https://graph.facebook.com/v2.3/me/accounts?access_token=" + longLiveAccessToken, null);
				JSONObject obj = (JSONObject)new JSONParser().parse(result);
				JSONArray b = (JSONArray)obj.get("data");
				String pageAccessToken = "";
				for(int i =0 ;i< b.size();i++){
					JSONObject c =  (JSONObject)b.get(0);
					if(c.get("id").toString().equals("545910802183206")){
						pageAccessToken =  c.get("access_token")+"";
						ConfigurationHelper.setDBValue("FB_pageAccessToken", pageAccessToken);
						System.out.println("oh found pageAccessToken = " + pageAccessToken);
					}
				}
				req.getSession().setAttribute("authen", "true");
				return "redirect:/admin/managearticle.html";
			} catch (Exception e) {
				e.printStackTrace();
				log.error("", e);
			}
		}
		return "login";
	}
	
	@RequestMapping(value = "/fblogin.html", method = {RequestMethod.GET })
	public void fbLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getSession(true).setAttribute("telco", req.getAttribute("telco"));
		FacebookService.getInstance().fblogin(req, resp);
	}
	@RequestMapping(value = "/fblogincallback.html", method = {RequestMethod.GET })
	public void fbLoginCallback(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		FacebookService.getInstance().fblogincallback(req, resp);
	}
	public static void main(String[] args) {
	/*	String json = "";
		try {
			String result  = HTTPClient.executeHttpGet("https://graph.facebook.com/v2.3/me/accounts?access_token=CAAXVGzbviFEBAIsCKH7EyEBZAN5W5ykBx2zq0Fam2ZCYsEpcXAIrEK3r4PhPF4skryZBU60toBZC1ZBMmiqWkYQ6XHcpdAcYRvNWWei9UAgvjy3ZBkymDC3ZAFHoaEqnvVwEtSEEIoOfDmSeAxRk5niLTDrk8F1ZB8cNN2AhstCn6ZCehndLQdxLm7SoHQKtzWXMZD", null);
			JSONObject obj = (JSONObject)new JSONParser().parse(result);
			JSONArray b = (JSONArray)obj.get("data");
			String pageAccessToken = "";
			for(int i =0 ;i< b.size();i++){
				JSONObject c =  (JSONObject)b.get(0);
				if(c.get("id").toString().equals("545910802183206")){
					pageAccessToken =  c.get("access_token")+"";
					ConfigurationHelper.setDBValue("FB_pageAccessToken", pageAccessToken);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		try {
			String response = HTTPClient.executeHttpGet("https://graph.facebook.com/v2.4/10153492241103614?method=get&access_token=CAAVKiTAXlDMBAJwMOGXR5ygXm8FVEMYbIJrVo0ZAON8ZAo4nTaZBuy56yYFgpwZB1AQsVUbbI3yHWRBRb1pJuPXn6hLLjpIGKkkZA9hKLyghxZCznCd2nIqt1ZC3Knxp3HeBAMTtaq9CnRvvjjclsycb9cRttQm52jzXjZBm0vSped6nSiyMAUplypzOcLdWenagPEnry4aHYAZDZD", null);
			System.out.println(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

