package com.xyz.hayhay.controller.news;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.xyz.hayhay.service.article.HayHayService;
import com.xyz.hayhay.service.article.NewsService;
import com.xyz.hayhay.util.JSONHelper;
import com.xyz.hayhay.util.MyUtil;
import com.xyz.hayhay.util.ValidationHelper;
import com.xyz.hayhay.website.collector.GoldCollector;
import com.xyz.hayhay.worker.SocialPostFeedNews;

@Controller
public class NewsController extends BaseController {

	@Autowired
	HayHayService stressService;

	@Async
	@ResponseBody
	@RequestMapping(value = "/news/loadmorenews.html", method = RequestMethod.GET)
	public void loadMoreNews(String from, String cate, String fromIndex, ModelMap model, HttpServletRequest request,
			HttpServletResponse resp) {
		int index = Integer.valueOf(fromIndex);
		try {
			if (!"dashboard".equals(from)) {
				List<News> news = newsService.getMoreNews(cate, index, 12);
				writeJSONResponse(resp, JSONHelper.toJSONArray(news));
			} else {
				List<News> news = newsService.getMoreHighlightNews(cate, index, 12);
				writeJSONResponse(resp, JSONHelper.toJSONArray(news));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/latestnews", method = RequestMethod.GET)
	public String getUpdate(HttpServletResponse resp, ModelMap model) {
		try {
			Map<String, Category> categoryList = new LinkedHashMap<>();
			List<News> news = NewsService.getInstance().getLatestNews(50, -1);
			for (News n : news) {
				Category c = categoryList.get(n.getType());
				if (c == null) {
					if (MappingHelper.categoryTypeLabelMapping.get(n.getType()) != null) {
						c = new Category(new ArrayList<News>(), MappingHelper.categoryTypeLabelMapping.get(n.getType()),
								n.getType());
					} else {
						c = new Category(new ArrayList<News>(), "", n.getParentCateName());
					}
					categoryList.put(c.getCateId(), c);
				}
				c.addNews(n);
			}
			org.json.simple.JSONArray list = (org.json.simple.JSONArray) new JSONParser()
					.parse(JSONHelper.toJSONArray(categoryList.values()).toString());
			model.put("from", "dashboard");
			model.put("fromIndex", 10);
			model.put("categories", list);
			model.put("giavang", GoldCollector.giaVang);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dashboard_news";
	}

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
			return homeOfNews(cate, target, model);
		}
	}

	@RequestMapping(value = "/news/{cate}", method = RequestMethod.GET)
	public String newsCategory(@PathVariable String cate, String target, HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		return homeOfNews(cate, target, model);
	}

	@RequestMapping(value = "/cate/{cate}", method = RequestMethod.GET)
	public String category(@PathVariable String cate, String target, HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) {
		return homeOfNews(cate, target, model);
	}

	@RequestMapping(value = "/news/type/{cate}", method = RequestMethod.GET)
	public String newsInOneCategory(@PathVariable String cate, HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) {
		List<News> news = new ArrayList<>();
		try {
			news = newsService.getNews(cate, 50);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model.put("news", news);
		return "news_in_one_cate";
	}

	private String homeOfNews(String cate, String target, ModelMap model) {
		try {
			if (target != null && !target.isEmpty()) {
				model.put("target", target);
				return "category_news";
			} else {
				JSONObject result = new JSONObject();
				model.put("fromIndex", 10);
				if (cate == null || cate.isEmpty()) {
					cate = NewsTypes.TINTUC;
				}
				if (NewsTypes.TINTUC.equals(cate) || NewsTypes.ENTERTAINMENT.equals(cate)
						|| NewsTypes.NHAC_FILMS.equals(cate)) {
					model.put("from", "dashboard");
				}
				model.put("cate", cate);
				List<String> categories = MappingHelper.cateGroup.get(cate);

				if (categories != null && categories.size() > 0) {
					List<String> queryCate = categories.subList(0, 1);
					if (NewsTypes.TINTUC.equals(cate) || NewsTypes.ENTERTAINMENT.equals(cate)
							|| NewsTypes.NHAC_FILMS.equals(cate)) {
						result = newsService.getHighlightNews(cate, queryCate, 10, 0);
					} else {
						result = newsService.getNews(cate, queryCate, 10, 0);
					}

					model.put("categories", result.get("categories"));
					model.put("giavang", GoldCollector.giaVang);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dashboard_news";
	}

	@ResponseBody
	@RequestMapping(value = "/news/json/{cate}", method = RequestMethod.GET)
	public void news(@PathVariable String cate, String target, HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) {
		try {
			if (cate == null || cate.isEmpty()) {
				cate = NewsTypes.TINTUC;
			}
			List<String> categories = MappingHelper.cateGroup.get(cate);
			JSONObject result = null;

			if (categories != null && categories.size() >= 2) {
				List<String> queryCate = categories.subList(1, categories.size());
				if (NewsTypes.TINTUC.equals(cate) || NewsTypes.ENTERTAINMENT.equals(cate)
						|| NewsTypes.NHAC_FILMS.equals(cate))
					result = newsService.getHighlightNews(cate + "_more2", queryCate, 10, 0);
				else {
					result = newsService.getNews(cate + "_more2", queryCate, 10, 0);
				}
			}

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
