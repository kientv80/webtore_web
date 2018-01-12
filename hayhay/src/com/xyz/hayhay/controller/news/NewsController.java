package com.xyz.hayhay.controller.news;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.hayhay.controller.BaseController;
import com.xyz.hayhay.db.dummydata.MappingHelper;
import com.xyz.hayhay.entirty.Category;
import com.xyz.hayhay.entirty.News;
import com.xyz.hayhay.entirty.NewsTypes;
import com.xyz.hayhay.localization.LocalizedResource;
import com.xyz.hayhay.service.article.HayHayService;
import com.xyz.hayhay.service.article.NewsService;
import com.xyz.hayhay.util.JSONHelper;
import com.xyz.hayhay.util.MyUtil;
import com.xyz.hayhay.util.ValidationHelper;
import com.xyz.hayhay.website.collector.GoldCollector;
import com.xyz.hayhay.worker.SocialPostFeedNews;
import com.xyz.webstore.mobile.config.UserSettings;

import scala.Array;

@Controller
public class NewsController extends BaseController {

	@Autowired
	HayHayService stressService;

//	@Async
//	@ResponseBody
//	@RequestMapping(value = "/news/loadmorenews.html", method = RequestMethod.GET)
//	public void loadMoreNews(String from, String cate, String fromIndex, ModelMap model, HttpServletRequest request,
//			HttpServletResponse resp) {
//		int index = Integer.valueOf(fromIndex);
//		try {
//			if (!"dashboard".equals(from)) {
//				String uid = getUid("", request);
//				String type = cate;
//				List<News> news = newsService.getMoreNews(uid, type, index, 12);
//				writeJSONResponse(resp, JSONHelper.toJSONArray(news));
//			} else {
//				List<News> news = newsService.getMoreHighlightNews(cate, index, 12);
//				writeJSONResponse(resp, JSONHelper.toJSONArray(news));
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}


	@RequestMapping(value = "/news/opennews.html", method = RequestMethod.GET)
	public String openNews(String articleId, String targetUrl, String ref, HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		News news = null;
		if (ValidationHelper.isNumber(articleId)) {
			try {
				news = newsService.getNewsById(Long.valueOf(articleId));
				if (news != null) {
					model.put("title", news.getTitle());
					model.put("desc", news.getShotDesc());
					model.put("image", news.getImageUrl());
					model.put("url", news.getUrl());
					model.put("articleId", articleId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (news == null) {
			model.put("url", targetUrl);
		}
		model.put("ref", ref);
		return "opennews";
	}

	@RequestMapping(value = "/news/open/{target}", method = RequestMethod.GET)
	public String openTargetUrl(@PathVariable String target, HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) {
		if (target != null && !target.isEmpty()) {
			model.put("target", MyUtil.decodeUrl(target));
			return "dashboard_news";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = { "/", "/home.html", "/news" }, method = RequestMethod.GET)
	public String home(String target, String cate, HttpServletRequest rq, ModelMap model) {
		if (target != null && !target.isEmpty()) {
			model.put("target", target);
			return "category_news";
		} else {
			String uid = getUid("", rq);
			return getNewsByCate(uid, NewsTypes.CATEGORY.HotNews.name(), target, model);
		}
	}

	@RequestMapping(value = "/news/{cate}", method = RequestMethod.GET)
	public String newsCategory(@PathVariable String cate, String target, HttpServletRequest req,
			HttpServletResponse resp, ModelMap model, HttpServletRequest rq) {
		String uid = getUid("", rq);
		return getNewsByCate(uid, cate, target, model);
	}
	private String getNewsByCate(String uid, String cate, String target, ModelMap model) {
		try {
			if (target != null && !target.isEmpty()) {
				model.put("target", target);
				return "category_news";
			} else {
				JSONObject result = new JSONObject();
				model.put("fromIndex", 10);
				if (cate == null || cate.isEmpty() ||  NewsTypes.CATEGORY.HotNews.name().equals(cate)) {
					result = newsService.getHighlightNews(uid,LocalizedResource.VI_VN, 10, 0);
					
				}else{
					result = newsService.getNews(uid, LocalizedResource.VI_VN, MappingHelper.cateGroup.get(cate), 10, 0);
				}
				model.put("from", "dashboard");
				model.put("cate", cate);
				model.put("categories", result.get("categories"));
				model.put("giavang", GoldCollector.giaVang);
				System.out.println(result.get("categories").toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dashboard_news";
	}

	@RequestMapping(value = "/news/type/{cate}", method = RequestMethod.GET)
	public String newsInOneCategory(@PathVariable String cate, HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) {
		List<News> news = new ArrayList<>();
		try {
			String uid = getUid("", req);
			news = newsService.getNewsByType(uid,cate,0, 50);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("news", news);
		return "news_in_one_cate";
	}

	

	@ResponseBody
	@RequestMapping(value = "/news/json/{cate}", method = RequestMethod.GET)
	public void news(@PathVariable String cate, String target,String fromIndex, HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) {
		try {
			int fIndex = 0;
			if(fromIndex != null && !fromIndex.isEmpty()){
				try{
					fIndex = Integer.parseInt(fromIndex);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			String uid = getUid("", req);
			JSONObject result = null;
			List<String> cates = new ArrayList<>();
			if (cate == null || cate.isEmpty()) {
				cate = NewsTypes.CATEGORY.HotNews.name();
				result = newsService.getHighlightNews(uid,LocalizedResource.VI_VN, 10, fIndex);
			}else{
				cates = new ArrayList<>();
				cates = MappingHelper.cateGroup.get(cate);
				result = newsService.getNews(uid, LocalizedResource.VI_VN, cates, 10, fIndex);
			}
			
			if (result != null)
				writeSimpleJSONObjectResponse(resp, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping(value = "/news/type/json/{cate}", method = RequestMethod.GET)
	public void newsByType(@PathVariable String cate, String target,String fromIndex,  HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) {
		try {
			int fIndex = 0;
			if(fromIndex != null && !fromIndex.isEmpty()){
				try{
					fIndex = Integer.parseInt(fromIndex);
				}catch(Exception ex){
					
				}
			}
			String uid = getUid("", req);
			JSONObject result = null;
			result = newsService.getNews(uid, LocalizedResource.VI_VN, MappingHelper.cateGroup.get(cate), 10, fIndex);
			if (result != null)
				writeSimpleJSONObjectResponse(resp, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/publish_article.html", method = RequestMethod.GET)
	public String getPuslishFeed(String feedid, ModelMap model, HttpServletRequest request) {
		List<Category> categories = SocialPostFeedNews.getFeedNews(feedid);
		model.put("categories", categories);
		return "publishfeed";
	}

	@RequestMapping(value = "/news/feed/{feedid}", method = RequestMethod.GET)
	public String getPuslishedFeed(@PathVariable String feedid, ModelMap model, HttpServletRequest request) {
		List<Category> categories = SocialPostFeedNews.getFeedNews(feedid);
		model.put("categories", categories);
		return "publishfeed";
	}

	@ResponseBody
	@RequestMapping(value = "/rm.html", method = RequestMethod.GET)
	public void removeNews(String id, HttpServletRequest req, HttpServletResponse resp, ModelMap model) {
		try {
			newsService.removeNewsById(Long.valueOf(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Long.parseLong("d");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
