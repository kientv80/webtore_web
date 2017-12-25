package com.xyz.hayhay.controller.mobile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.hayhay.controller.BaseController;
import com.xyz.hayhay.db.dummydata.MappingHelper;
import com.xyz.hayhay.entirty.NewsTypes;
import com.xyz.hayhay.entirty.WebsiteInfo;
import com.xyz.hayhay.util.JSONHelper;
import com.xyz.hayhay.website.collector.TranslateService;
import com.xyz.webstore.mobile.config.Category;
import com.xyz.webstore.mobile.config.UserSettings;
import com.xyz.webstore.mobile.config.WebstoreMobileAppConfig;

import akka.util.Collections;

@Controller
public class MobileRestfulService extends BaseController {

	@ResponseBody
	@RequestMapping(value = "/mobile/translate", method = RequestMethod.GET)
	public void translate(String word, HttpServletResponse resp) {
		String result;
		JSONObject meaning = new JSONObject();
		try {
			result = TranslateService.getInstance().getTranslatedWord(word);
			meaning.put("meaning", result);
			writeJSONResponsed(resp, meaning);
		} catch (Exception e) {
			e.printStackTrace();
			meaning.put("meaning", "Not Found!");
			writeJSONResponsed(resp, meaning);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/mobile/category", method = RequestMethod.GET)
	public void getMobileConfig(String version, String type, String locale, HttpServletResponse resp) {
		try {
			List<Category> categories = null;
			categories = WebstoreMobileAppConfig.getCategories(version, locale);
			JSONObject conf = new JSONObject();
			// if (version != null && !version.isEmpty() &&
			// WebstoreMobileAppConfig.CURRENT_VERSION.equals(version)) {
			// conf.put("errorCode", "1");
			// conf.put("errorMsg", "No update");
			// } else {
			conf.put("errorCode", "0");
			conf.put("errorMsg", "");
			conf.put("version", WebstoreMobileAppConfig.CURRENT_VERSION);
			conf.put("categories", JSONHelper.toJSONArray(categories).toString());
			// }
			writeJSONResponsed(resp, conf);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/mobile/websiteinfo", method = RequestMethod.GET)
	public void getWebsites(String version, HttpServletResponse resp) {
		try {
			List<WebsiteInfo> webinfo = WebstoreMobileAppConfig.getWebsiteInfo(version);
			JSONObject conf = new JSONObject();
			// if (version != null && !version.isEmpty() &&
			// WebstoreMobileAppConfig.CURRENT_VERSION.equals(version)) {
			// conf.put("errorCode", "1");
			// conf.put("errorMsg", "No update");
			// } else {
			conf.put("errorCode", "0");
			conf.put("errorMsg", "");
			conf.put("version", WebstoreMobileAppConfig.CURRENT_VERSION);
			conf.put("webinfo", JSONHelper.toJSONArray(webinfo).toString());
			// }
			writeJSONResponsed(resp, conf);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/mobile/appversion", method = RequestMethod.GET)
	public void getAppVersion(String version, HttpServletResponse resp) {
		JSONObject appversion = new JSONObject();
		appversion.put("version", "1.0");
		appversion.put("desc", "New version has been released, please click here to update.");
		appversion.put("releasedDate", "04/06/2017");
		appversion.put("forceUpdate", "false");
		writeJSONResponsed(resp, appversion);
	}

	
	@ResponseBody
	@RequestMapping(value = "/mobile/article/{category}", method = RequestMethod.GET)
	public void getArticlesV2(@PathVariable String category, String uid, String from, String locale,
			HttpServletResponse resp) {
		try {
			System.out.println("Locale = " + locale);
			JSONObject result = null;
			
			if (category == null || category.isEmpty()) {
				category = NewsTypes.CATEGORY.HotNews.name();
			}
			int fromIndex;
			if (from == null || from.isEmpty())
				fromIndex = 0;
			else
				fromIndex = Integer.parseInt(from);
			
			if (NewsTypes.CATEGORY.HotNews.name().equals(category)) {
				result = newsService.getHighlightNews(uid,locale, 10,fromIndex);
			}else{
				result = newsService.getNews(uid,locale, MappingHelper.cateGroup.get(category),10, fromIndex);
			}
			try {
				if (result != null)
					writeSimpleJSONObjectResponse(resp, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	@ResponseBody
	@RequestMapping(value = "/mobile/article/update", method = RequestMethod.GET)
	public void getArticles(String uid, String time,String locale, HttpServletResponse resp) {
		try {
			long filterTime = System.currentTimeMillis() - 5 * 60 * 1000;
			if (time != null && !time.isEmpty()) {
				filterTime = Long.parseLong(time);
			}
			try {
				List<String> cates = UserSettings.getUserSetting(uid, UserSettings.TYPE_FAVORITE_CATE, locale);
				List<String> favoriteCountries = UserSettings.getUserSetting(uid, UserSettings.TYPE_FAVORITE_COUNTRIES, locale);
				JSONObject favoriteCates = UserSettings.getSettings(uid, UserSettings.TYPE_FAVORITE_CATE, locale);
				JSONObject dfFavoriteCates = UserSettings.getDefaultFavoriteCatesSettings(locale);
				JSONArray settings = (JSONArray) new JSONParser().parse(favoriteCates.get("settings").toString());
				JSONArray dfSettings = (JSONArray) new JSONParser().parse(dfFavoriteCates.get("settings").toString());
				for (int i = 0; i < settings.size(); i++) {
					JSONObject st = (JSONObject) settings.get(i);
					if (st.get("value").equals(true)) {
						JSONObject dfst = (JSONObject) dfSettings.get(((Long) st.get("id")).intValue());
						cates.add(dfst.get("name").toString());
					}
				}

				java.util.Collections.sort(cates);
				
				JSONObject result = null;
				if (cates != null && cates.size() > 0) {
					result = newsService.getLatestNews(cates,favoriteCountries, 10, filterTime);
				}
				if (result != null)
					writeSimpleJSONObjectResponse(resp, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/mobile/settings/get", method = RequestMethod.GET)
	public void getSettings(String uid, String option, String locale, HttpServletResponse resp) {
		try {
			JSONObject settings = UserSettings.getSettings(uid, option, locale);
			System.out.println(settings.toJSONString());
			writeSimpleJSONObjectResponse(resp, settings);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/mobile/settings/update", method = RequestMethod.POST)
	public void updateSettings(String uid, String settings, HttpServletResponse resp) {
		try {
			System.out.println(settings);
			JSONObject st = (JSONObject) new JSONParser().parse(settings);
			UserSettings.saveUserSettings(st.get("title").toString(), st.get("serviceUrl").toString(), uid,
					st.get("type").toString(), st.get("settings").toString());
			writeSuccessResponse(resp, "Saved");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println((System.currentTimeMillis() - 60 * 60 * 1000));
	}
}
