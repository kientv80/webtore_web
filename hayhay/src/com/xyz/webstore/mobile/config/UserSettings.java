package com.xyz.webstore.mobile.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.xyz.hayhay.db.JDBCConnection;
import com.xyz.hayhay.entirty.News;
import com.xyz.hayhay.entirty.NewsTypes;
import com.xyz.hayhay.localization.LocalizedResource;

public class UserSettings {
	public static String TYPE_FAVORITE_CATE = "favorite_cates";
	public static String TYPE_FAVORITE_COUNTRIES = "favorite_countries";
	public static String TYPE_FAVORITE_LANGUAGES = "favorite_languages";
	public static String TYPE_SETTING = "settings";

	public static JSONObject getSettings(String userId, String type, String locale) throws Exception {
		JSONObject result = getUserSettings(userId, type, locale);
		if (result == null) {
			if (TYPE_FAVORITE_CATE.equals(type))
				result = getDefaultFavoriteCatesSettings(locale);
			else if (TYPE_FAVORITE_COUNTRIES.equals(type))
				result = getDefaultFavoriteCountriesSettings(locale);
			else if (TYPE_FAVORITE_LANGUAGES.equals(type))
				result = getDefaultFavoriteLanguagesSettings(locale);
		}
		return result;
	}

	private static JSONObject getDefaultFavoriteLanguagesSettings(String locale) {
		JSONObject result = new JSONObject();
		JSONArray settings = new JSONArray();
		if ("vi_VN".equals(locale)) {
			settings.add(createSetting(News.LANGUAGE.VIETNAMESE.ordinal(), News.LANGUAGE.VIETNAMESE.name(), LocalizedResource.getInstance().getValue("language.vn", locale), "checkbox",
					true));
		}else{
			settings.add(createSetting(News.LANGUAGE.VIETNAMESE.ordinal(), News.LANGUAGE.VIETNAMESE.name(), LocalizedResource.getInstance().getValue("language.vn", locale), "checkbox",
					false));
		}

		settings.add(createSetting(News.LANGUAGE.ENGLISH.ordinal(), News.LANGUAGE.ENGLISH.name(), LocalizedResource.getInstance().getValue("language.en", locale),
				"checkbox", true));
		settings.add(createSetting(News.LANGUAGE.CHINESE.ordinal(),  News.LANGUAGE.CHINESE.name(), LocalizedResource.getInstance().getValue("language.china", locale),
				"checkbox", false));


		result.put("settings", settings);
		result.put("title", LocalizedResource.getInstance().getValue("setting.languages", locale));
		result.put("serviceUrl", "http://360hay.com/mobile/settings/update");
		result.put("type", TYPE_FAVORITE_LANGUAGES);
		return result;
	}

	public static JSONObject getDefaultFavoriteCatesSettings(String locale) throws JSONException {
		JSONObject result = new JSONObject();
		JSONArray settings = new JSONArray();
		int count = 0;
		for (Category cate : WebstoreMobileAppConfig.getCategories("", locale)) {
			settings.add(createSetting(count, cate.getName(), cate.getLabel(), "checkbox", cate.getIsDefault()));
			count++;
		}
		result.put("settings", settings);
		result.put("title", LocalizedResource.getInstance().getValue(TYPE_FAVORITE_CATE, locale));
		result.put("serviceUrl", "http://360hay.com/mobile/settings/update");
		result.put("type", TYPE_FAVORITE_CATE);
		return result;
	}

	public static JSONObject getDefaultFavoriteCountriesSettings(String locale) throws JSONException {
		JSONObject result = new JSONObject();
		JSONArray settings = new JSONArray();
		if ("vi_VN".equals(locale)) {
			settings.add(createSetting(News.COUNTRY.VN.ordinal(), News.COUNTRY.VN.name(),
					LocalizedResource.getInstance().getValue("country.vn", locale), "checkbox", true));
			settings.add(createSetting(News.COUNTRY.US.ordinal(), News.COUNTRY.US.name(),
					LocalizedResource.getInstance().getValue("country.us", locale), "checkbox", true));
			settings.add(createSetting(News.COUNTRY.CHINA.ordinal(), News.COUNTRY.CHINA.name(),
					LocalizedResource.getInstance().getValue("country.china", locale), "checkbox", false));
			settings.add(createSetting(News.COUNTRY.ASIAN.ordinal(), News.COUNTRY.ASIAN.name(),
					LocalizedResource.getInstance().getValue("country.asian", locale), "checkbox", false));

		} else {
			settings.add(createSetting(News.COUNTRY.VN.ordinal(), News.COUNTRY.VN.name(),
					LocalizedResource.getInstance().getValue("country.vn", locale), "checkbox", false));
			settings.add(createSetting(News.COUNTRY.US.ordinal(), News.COUNTRY.US.name(),
					LocalizedResource.getInstance().getValue("country.us", locale), "checkbox", true));
			settings.add(createSetting(News.COUNTRY.CHINA.ordinal(), News.COUNTRY.CHINA.name(),
					LocalizedResource.getInstance().getValue("country.china", locale), "checkbox", false));
			settings.add(createSetting(News.COUNTRY.ASIAN.ordinal(), News.COUNTRY.ASIAN.name(),
					LocalizedResource.getInstance().getValue("country.asian", locale), "checkbox", false));
		}

		result.put("settings", settings);
		result.put("title", LocalizedResource.getInstance().getValue(TYPE_FAVORITE_COUNTRIES, locale));
		result.put("serviceUrl", "http://360hay.com/mobile/settings/update");
		result.put("type", TYPE_FAVORITE_COUNTRIES);
		return result;
	}

	private static JSONObject getUserSettings(String userId, String type, String locale) throws Exception {
		JSONObject result = null;
		JSONObject defaultSetting = getDefaultFavoriteCatesSettings(locale);
		if (TYPE_FAVORITE_COUNTRIES.equals(type)) {
			defaultSetting = getDefaultFavoriteCountriesSettings(locale);
		} else if (TYPE_FAVORITE_CATE.equals(type)) {
			defaultSetting = getDefaultFavoriteCatesSettings(locale);
		} else if (TYPE_FAVORITE_LANGUAGES.equals(type)) {
			defaultSetting = getDefaultFavoriteLanguagesSettings(locale);
		}
		String sql = "select * from settings where userid=? and type=?";
		try (Connection conn = JDBCConnection.getInstance().getConnection()) {
			try (PreparedStatement stm = conn.prepareStatement(sql)) {
				stm.setString(1, userId);
				stm.setString(2, type);
				try (ResultSet rs = stm.executeQuery()) {
					while (rs.next()) {
						result = new JSONObject();
						result.put("title", rs.getString("title"));
						result.put("type", rs.getString("type"));
						result.put("serviceUlr", rs.getString("service_url"));
						result.put("settings", rs.getString("settings"));
					}
				}

			}
		}
		if (result != null) {
			JSONArray settings = (JSONArray) new JSONParser().parse(result.get("settings").toString());
			JSONArray dsettings = (JSONArray) defaultSetting.get("settings");
			for (int i = 0; i < settings.size(); i++) {
				JSONObject st = (JSONObject) settings.get(i);
				((JSONObject) dsettings.get(((Long) st.get("id")).intValue())).put("value", st.get("value"));
			}
			result = defaultSetting;
		}
		return result;
	}

	public static JSONObject saveUserSettings(String title, String serviceUrl, String userId, String type,
			String settings) throws Exception {
		JSONObject result = null;
		String sql = "insert into settings(title,service_url,type,userid,settings)values(?,?,?,?,?)";
		try (Connection conn = JDBCConnection.getInstance().getConnection()) {
			try (PreparedStatement stm = conn.prepareStatement("delete from settings where userid=? and type=?")) {
				stm.setString(1, userId);
				stm.setString(2, type);
				stm.execute();
			}
			try (PreparedStatement stm = conn.prepareStatement(sql)) {
				stm.setString(1, title);
				stm.setString(2, serviceUrl);
				stm.setString(3, type);
				stm.setString(4, userId);
				stm.setString(5, settings);
				stm.execute();
			}
		}
		return result;
	}

	public static List<String> getUserSetting(String userId,final String type, String locale) throws Exception {
		List<String> settingParams = new ArrayList<String>();
		JSONObject favoriteCates = UserSettings.getSettings(userId, type, locale);
		JSONArray settings = (JSONArray) new JSONParser().parse(favoriteCates.get("settings").toString());
		for (int i = 0; i < settings.size(); i++) {
			JSONObject st = (JSONObject) settings.get(i);
			if (st.get("value").equals(true)) {
				settingParams.add(st.get("name").toString());
			}
		}
		java.util.Collections.sort(settingParams, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if (TYPE_FAVORITE_CATE.equals(type)) {
					return Long.compare(NewsTypes.CATEGORY.valueOf(s1).ordinal(),
							NewsTypes.CATEGORY.valueOf(s2).ordinal());
				}else if (TYPE_FAVORITE_COUNTRIES.equals(type)) {
					return Long.compare(News.COUNTRY.valueOf(s1).ordinal(),
							News.COUNTRY.valueOf(s2).ordinal());
				}else if (TYPE_FAVORITE_LANGUAGES.equals(type)) {
					return Long.compare(News.LANGUAGE.valueOf(s1).ordinal(),
							News.LANGUAGE.valueOf(s2).ordinal());
				}else{
					return 0;
				}
				
			}
		});
		return settingParams;
	}

	private static JSONObject createSetting(int id, String name, String label, String type, Object value) {
		JSONObject st = new JSONObject();
		st.put("id", id);
		st.put("name", name);
		st.put("label", label);
		st.put("value", value);
		st.put("type", type);
		return st;
	}
}
