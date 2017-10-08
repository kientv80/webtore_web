package com.xyz.hayhay.service.webstore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xyz.hayhay.cache.JCSCacheClient;
import com.xyz.hayhay.cache.JSCCacheManager;
import com.xyz.hayhay.db.JDBCConnection;
import com.xyz.hayhay.entirty.webstore.WebApp;
import com.xyz.hayhay.entirty.webstore.WebCategory;


public class WebStoreService {

	@SuppressWarnings("unchecked")
	public static List<WebCategory> getWebCategories() throws Exception {
		Map<Integer, WebCategory> webcates = new HashMap();
		
		JCSCacheClient cache = JSCCacheManager.getInstace().getCache("webcate");
		if (cache.get("webcate") != null) {
			return (List<WebCategory>) cache.get("webcate");
		} else {
			Connection conn = JDBCConnection.getInstance().getConnection();
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("select * from web_category");
			while (rs.next()) {
				webcates.put(rs.getInt("id"),new WebCategory(rs.getInt("id"), rs.getString("name"), rs.getString("label")));
			}
			rs.close();
			rs = stm.executeQuery("select * from web_app");
			while (rs.next()) {
				webcates.get(rs.getInt("id")).getWebapps().add(new WebApp(rs.getInt("id"), rs.getString("name"), rs.getString("label"), rs.getString("desc"), rs.getString("url"), rs.getString("icon"), rs.getString("cover"),rs.getInt("category_id")));
			}
			rs.close();
			stm.close();
			conn.close();
			cache.put("webcate", webcates.values());
			return new ArrayList<WebCategory>(webcates.values());
		}

	}
}
