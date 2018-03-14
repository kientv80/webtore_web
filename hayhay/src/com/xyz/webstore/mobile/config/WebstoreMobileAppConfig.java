package com.xyz.webstore.mobile.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.simple.JSONObject;

import com.xyz.hayhay.entirty.NewsTypes;
import com.xyz.hayhay.entirty.NewsTypes.CATEGORY;
import com.xyz.hayhay.entirty.WebsiteInfo;
import com.xyz.hayhay.localization.LocalizedResource;

public class WebstoreMobileAppConfig {
	public static final String CURRENT_VERSION = "1.0";
	public static Map<String,String> cateIconMapping = new HashMap<>();
	static{
		cateIconMapping.put(NewsTypes.CATEGORY.Business.name(), AppConf.DOMAIN + "/images/icons/category/ic_kinhdoanh.png");
		cateIconMapping.put(NewsTypes.CATEGORY.CarAndMotor.name(), AppConf.DOMAIN + "/images/icons/category/ic_xe.png");
		cateIconMapping.put(NewsTypes.CATEGORY.Cooking.name(), AppConf.DOMAIN + "/images/icons/category/cooking.png");
		cateIconMapping.put(NewsTypes.CATEGORY.Economic.name(), AppConf.DOMAIN + "/images/icons/category/ic_economy.png");
		cateIconMapping.put(NewsTypes.CATEGORY.Entertainment.name(), AppConf.DOMAIN + "/images/icons/category/ngoisao.png");
		cateIconMapping.put(NewsTypes.CATEGORY.Funny.name(), AppConf.DOMAIN + "/images/icons/category/ic_smile.png");
		cateIconMapping.put(NewsTypes.CATEGORY.Health.name(), AppConf.DOMAIN + "/images/icons/category/ic_suckhoe.png");
		cateIconMapping.put(NewsTypes.CATEGORY.HotNews.name(), AppConf.DOMAIN + "/images/icons/category/ic_news.png");
		cateIconMapping.put(NewsTypes.CATEGORY.Love.name(), AppConf.DOMAIN + "/images/icons/category/ic_love.png");
		cateIconMapping.put(NewsTypes.CATEGORY.Realty.name(), AppConf.DOMAIN + "/images/icons/category/ic_building.png");
		cateIconMapping.put(NewsTypes.CATEGORY.Sport.name(), AppConf.DOMAIN + "/images/icons/category/ic_sport.png");
		cateIconMapping.put(NewsTypes.CATEGORY.Tech.name(), AppConf.DOMAIN + "/images/icons/category/ic_tech.png");
		
		cateIconMapping.put(NewsTypes.CATEGORY.Opinion.name(), AppConf.DOMAIN + "/images/icons/category/opinion.png");
		cateIconMapping.put(NewsTypes.CATEGORY.Politics.name(), AppConf.DOMAIN + "/images/icons/category/politic.png");
		cateIconMapping.put(NewsTypes.CATEGORY.Science.name(), AppConf.DOMAIN + "/images/icons/category/science.png");
	}
	public static List<Category> getCategories(String version, String locale) throws JSONException{
		List<Category> categories = new ArrayList<>();
		for(CATEGORY category: CATEGORY.values()){
			categories.add(new Category(category.name(),LocalizedResource.getInstance().getValue(category.name(), locale),AppConf.DOMAIN + "/mobile/article/"+category.name(),cateIconMapping.get(category.name()), true));
		}
		return categories;
	}
	
	public static List<WebsiteInfo> getWebsiteInfo(String version) throws JSONException{
		List<WebsiteInfo> webs = new ArrayList<>();
		webs.add(new WebsiteInfo("ngoisao.net", "", AppConf.DOMAIN + "/images/website_icon/ngoisaonet.jpg", "cover", "url",true));
		webs.add(new WebsiteInfo("muabannhadat.vn", "", "", "cover", "url",true));
		webs.add(new WebsiteInfo("galaxycine.vn", "", "icon", "cover", "url",true));
		webs.add(new WebsiteInfo("ictnews.vn", "", AppConf.DOMAIN + "/images/website_icon/ictnews.png", "cover", "url",true));
		webs.add(new WebsiteInfo("news.zing.vn", "", AppConf.DOMAIN + "/images/website_icon/zingnews.jpg", "cover", "url",true));
		webs.add(new WebsiteInfo("tuoitre.vn", "", AppConf.DOMAIN + "/images/website_icon/tuoitre.jpg", "cover", "url",true));
		webs.add(new WebsiteInfo("dantri.com.vn", "", AppConf.DOMAIN + "/images/website_icon/dantri.jpg", "cover", "url",false));
		webs.add(new WebsiteInfo("bizlive.vn", "", AppConf.DOMAIN + "/images/website_icon/bizlive.png", "cover", "url",true));
		webs.add(new WebsiteInfo("vneconomy.vn", "", AppConf.DOMAIN + "/images/website_icon/vneconomy.png", "cover", "url",true));
		webs.add(new WebsiteInfo("cafebiz.vn", "", "", "cover", "url",true));
		webs.add(new WebsiteInfo("suckhoedoisong.vn", "", "", "cover", "url",true));
		webs.add(new WebsiteInfo("thethao247.vn", "", AppConf.DOMAIN + "/images/website_icon/thethao247.png", "cover", "url",false));
		webs.add(new WebsiteInfo("24h.com.vn", "", "", "cover", "url",false));
		webs.add(new WebsiteInfo("afamily.vn", "", AppConf.DOMAIN + "/images/website_icon/afamily.png", "cover", "url",true));
		webs.add(new WebsiteInfo("monngonmoingay.com", "", "", "cover", "url",true));
		webs.add(new WebsiteInfo("nhaccuatui.com", "", AppConf.DOMAIN + "/images/website_icon/nhaccuatui.jpg", "cover", "url",false));
		webs.add(new WebsiteInfo("nhac.vn", "", AppConf.DOMAIN + "/images/website_icon/nhac.png", "cover", "url",true));
		webs.add(new WebsiteInfo("kinhdoanh.vnexpress.net", "", AppConf.DOMAIN + "/images/website_icon/vnexpress.png", "cover", "url",true));
		webs.add(new WebsiteInfo("vnexpress.net", "", AppConf.DOMAIN + "/images/website_icon/vnexpress.png", "cover", "url",true));
		webs.add(new WebsiteInfo("phimmoi.net", "", AppConf.DOMAIN + "/images/website_icon/phimmoi.png", "cover", "url",true));
		webs.add(new WebsiteInfo("baoxaydung.com.vn", "", AppConf.DOMAIN + "/images/website_icon/xaydung.png", "cover", "url",true));
		webs.add(new WebsiteInfo("pcworld.com.vn", "", AppConf.DOMAIN + "/images/website_icon/pcworld.png", "cover", "url",false));
		webs.add(new WebsiteInfo("bongda24h.vn", "", AppConf.DOMAIN + "/images/website_icon/bongda24h.png", "cover", "url",false));
		webs.add(new WebsiteInfo("suckhoegiadinh.com.vn", "", AppConf.DOMAIN + "/images/website_icon/suckhoegiadinh.jpg", "cover", "url",true));
		
		webs.add(new WebsiteInfo("foxbusiness.com", "", AppConf.DOMAIN + "/images/website_icon/foxnews.png", "cover", "url",true));
		webs.add(new WebsiteInfo("foxnews.com", "", AppConf.DOMAIN + "/images/website_icon/foxnews.png", "cover", "url",true));
		webs.add(new WebsiteInfo("nytimes.com", "", AppConf.DOMAIN + "/images/website_icon/nytimes.png", "cover", "url",true));
		webs.add(new WebsiteInfo("washingtonpost.com", "", "", "cover", "url",false));
		
		
		return webs;
	}
	public static JSONObject getConfig(String version) throws JSONException{
		JSONObject conf = new JSONObject();
		conf.put("errorCode", "1");
		conf.put("errorMsg", "No update");
		return conf;
	}
}
