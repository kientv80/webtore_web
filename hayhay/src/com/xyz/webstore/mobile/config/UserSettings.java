package com.xyz.webstore.mobile.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.xyz.hayhay.db.JDBCConnection;

public class UserSettings {
	public static JSONObject getSettings(String userId, String type) throws Exception {
		JSONObject result = getUserSettings(userId, type);
		if (result == null) {
			result = getDefaultFavoriteCatesSettings();
		}
		return result;
	}

	private static JSONObject getDefaultFavoriteCatesSettings() throws JSONException {
		JSONObject result = new JSONObject();
		JSONArray settings = new JSONArray();
		int count = 0;
		for (Category cate : WebstoreMobileAppConfig.getCategories("")) {
			settings.add(createSetting(count, cate.getName(), cate.getLabel(), "checkbox",cate.getIsDefault()));
			count++;
		}
		result.put("settings", settings);
		result.put("title", "Danh Mục Yêu Thích");
		result.put("serviceUrl", "http://360hay.com/mobile/settings/update");
		return result;
	}

	private static JSONObject getUserSettings(String userId, String type) throws Exception {
		JSONObject result = null;
		JSONObject defaultSetting = getDefaultFavoriteCatesSettings();
		String sql = "select * from settings where userid=? and type=?";
		try (Connection conn = JDBCConnection.getInstance().getConnection()) {
			try (PreparedStatement stm = conn.prepareStatement(sql)) {
				stm.setString(1, userId);
				stm.setString(2, type);
				try (ResultSet rs = stm.executeQuery()) {
					while (rs.next()) {
						result = new JSONObject();
						result.put("title", rs.getString("title"));
						result.put("serviceUlr", rs.getString("service_url"));
						result.put("settings", rs.getString("settings"));
					}
				}
				
			}
		}
		if(result != null){
			JSONArray settings =(JSONArray) new JSONParser().parse(result.get("settings").toString());
			JSONArray dsettings = (JSONArray)defaultSetting.get("settings");
			for(int i=0;i<settings.size();i++){
				JSONObject st = (JSONObject)settings.get(i);
				((JSONObject)dsettings.get(((Long)st.get("id")).intValue())).put("value", st.get("value"));
			}
			result = defaultSetting;
		}
		return result;
	}
	public static JSONObject saveUserSettings(String title, String serviceUrl,String userId, String type, String settings) throws Exception {
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
