package com.xyz.hayhay.controller.news;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.hayhay.controller.BaseController;
import com.xyz.hayhay.entirty.News;
import com.xyz.hayhay.service.article.NewsService;
import com.xyz.hayhay.util.HTMLLinkParser;
import com.xyz.hayhay.util.JSONHelper;
import com.xyz.hayhay.util.LinkInfo;

@Controller
public class ManageManualCollectedArticleController extends BaseController {
	@Autowired
	NewsService newsService;
	
	@RequestMapping(value ="/admin/mngarticle.html", method = RequestMethod.GET)
	public String mngArticle(String type,String index, HttpServletRequest rq, ModelMap model) {
		if(type != null && !type.isEmpty()){
			try {
				model.put("news", newsService.getNews(type, 100));
				if(index == null || index.isEmpty())
					model.put("index", 0);
				else
					model.put("index", index);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "mng_article";
	}
	
	@RequestMapping(value ="/admin/addarticle.html", method = RequestMethod.POST)
	public String mngArticle(String type,String website, String title, String desc, String image,String url, HttpServletRequest rq, ModelMap model) {
		if(type != null && !type.isEmpty()){
			try {
				News n = new News();
				n.setTitle(title);
				n.setShotDesc(desc);
				n.setUrl(url);
				n.setFromWebSite(website);
				n.setImageUrl(image);
				n.setType(type);
				newsService.storeNews(n);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			model.put("news", newsService.getNews(type, 100));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "mng_article";
	}
	
	
	@ResponseBody
	@Async
	@RequestMapping(value ="/getLinkInfo.html", method = RequestMethod.GET)
	public void getLinkInfo(String url, HttpServletRequest rq,HttpServletResponse resp, ModelMap model) {
		try {
			LinkInfo info = HTMLLinkParser.parseLink(url);
			JSONObject linkInfo = JSONHelper.toJSONObject(info);
			writeJSONResponsed(resp, linkInfo);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
