package com.xyz.hayhay.service.article;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONException;
import org.json.simple.JSONObject;
//import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.xyz.hayhay.cache.JCSCacheClient;
import com.xyz.hayhay.cache.JSCCacheManager;
import com.xyz.hayhay.db.JDBCConnection;
import com.xyz.hayhay.db.dummydata.MappingHelper;
import com.xyz.hayhay.entirty.Category;
import com.xyz.hayhay.entirty.News;
import com.xyz.hayhay.entirty.NewsTypes;
import com.xyz.hayhay.util.JSONHelper;

import akka.util.Collections;

public class NewsService {

	private static NewsService instance;
	Map<String, Category> cachedArticles = new ConcurrentHashMap<>();

	public static NewsService getInstance() {
		if (instance == null)
			instance = new NewsService();
		return instance;
	}

	private NewsService() {
		startLoadArticlesWorker();
	}

	private void startLoadArticlesWorker() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					System.out.println("loading articles...");
					loadLatestArticles();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		};
		// Timer t = new Timer();
		// t.schedule(task, 0, 5 * 60 * 1000);// update every five minutes
	}

	private void loadLatestArticles() throws SQLException {
		StringBuilder filterTypes = new StringBuilder();
		for (String type : MappingHelper.cateGroup.get(NewsTypes.CATEGORY.HotNews.name())) {
			filterTypes.append("'").append(type).append("'").append(",");
		}
		String sql = "SELECT distinct url,id,title, shotdesc,fromwebsite,imageurl,type,collectedtime,title_id,parent_catename FROM news WHERE parent_catename in ("
				+ filterTypes.substring(0, filterTypes.length() - 1) + ") order by id desc limit 200";
		Connection conn = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			Map<String, Category> categoryList = new ConcurrentHashMap<>();
			while (rs.next()) {
				News n = dataToNews(rs);
				try {
					Category c = categoryList.get(n.getParentCateName());
					if (c == null) {
						c = new Category(new ArrayList<News>(), "", rs.getString("parent_catename"));
						categoryList.put(n.getParentCateName(), c);
					}
					c.addNews(dataToNews(rs));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			cachedArticles = categoryList;
		} finally {
			if (stm != null)
				stm.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
	}

	public List<News> getLatestNews(int limit, long fromTime) throws SQLException {
		List<News> newsList = new ArrayList<>();
		String sql = "select * from news where fromwebsite <> 'muabannhadat.vn' and type not like 'WN_%' and (UNIX_TIMESTAMP(collectedtime)*1000) > "
				+ fromTime + " order by id desc limit " + limit;
		if (fromTime == -1)
			sql = "select * from news where fromwebsite <> 'muabannhadat.vn' order by id desc limit " + limit;

		if (limit == -1)
			sql = "select * from news where type=?";
		Connection conn = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);

		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			newsList.add(dataToNews(rs));
		}
		rs.close();
		stm.close();
		conn.close();
		return newsList;
	}

	public List<News> getNews(String type, int limit) throws SQLException {
		List<News> newsList = new ArrayList<>();
		String sql = "select * from news where type=?  order by id desc limit " + limit;
		if (limit == -1)
			sql = "select * from news where type=?";
		Connection conn = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, type);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			newsList.add(dataToNews(rs));
		}
		rs.close();
		stm.close();
		conn.close();
		return newsList;
	}

	public List<News> getHighlightNews(String parentCate, int limit) throws SQLException {
		List<News> newsList = new ArrayList<>();
		String sql = "select distinct url,id,title, shotdesc,fromwebsite,imageurl,type,collectedtime,title_id,parent_catename from news where parent_catename = ?  order by id desc limit "
				+ limit;
		if (limit == -1)
			sql = "select * from news where parent_catename = ?";
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getInstance().getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, parentCate);
			rs = stm.executeQuery();
			int count = 0;
			while (rs.next()) {
				if (count == 0)
					newsList.add(dataToNews(rs));
				else
					newsList.add(dataToNews(rs));
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				rs.close();
				stm.close();
				conn.close();
			}
		}
		return newsList;
	}

	public JSONObject getHighlightNews(String cacheName, List<String> cateNames,List<String> countries, int limit, int fromIndex)
			throws Exception {
		JSONObject result = new JSONObject();
		Map<String, Category> categoryList = new LinkedHashMap<>();
		StringBuilder country = new StringBuilder();
		StringBuilder cacheCountry = new StringBuilder();
		for(String c : countries){
			country.append("?,");
			cacheCountry.append(c);
		}
		country.deleteCharAt(country.length()-1);
		for (String type : cateNames) {
			cacheCountry.append(type);
		}
		String cached ="article"+cacheCountry.toString();
		JCSCacheClient cache = JSCCacheManager.getInstace().getCache(cached);
		Object newsList = cache.get(cacheName);
		if (newsList != null) {
			result.put("categories", (org.json.simple.JSONArray) newsList);
		} else {
			String sql = "select distinct url,id,title, shotdesc,fromwebsite,imageurl,type,collectedtime,title_id,parent_catename  from news where parent_catename = ? and country in ("+country.toString()+")  order by id desc limit "
					+ limit + " offset " + fromIndex;
			try (Connection conn = JDBCConnection.getInstance().getConnection()) {
				try (PreparedStatement stm = conn.prepareStatement(sql)) {
					for (String type : cateNames) {
						stm.clearParameters();
						stm.setString(1, type);
						int count=2;
						for(String c : countries){
							stm.setString(count, c);
							count++;
						}
						ResultSet rs = stm.executeQuery();
						while (rs.next()) {
							Category c = categoryList.get(type);
							if (c == null) {
								if (MappingHelper.categoryTypeLabelMapping.get(type) != null) {
									c = new Category(new ArrayList<News>(),
											MappingHelper.categoryTypeLabelMapping.get(type), type);
								} else {
									c = new Category(new ArrayList<News>(), "", rs.getString("parent_catename"));
								}
								categoryList.put(c.getCateId(), c);
							}
							c.addNews(dataToNews(rs));
						}
						rs.close();
					}

					for (Category c : categoryList.values()) {
						c.setSubcates(MappingHelper.parentCateMapping.get(c.getCateId()));
					}
					org.json.simple.JSONArray list = (org.json.simple.JSONArray) new JSONParser().parse(JSONHelper.toJSONArray(categoryList.values()).toString());
					cache.put(cached, list);
					result.put("categories", list);
				}
			}
		}
		return result;
	}

	public JSONObject getNews(String cacheName, List<String> types,List<String> countries, int limit, int fromIndex)
			throws SQLException, JSONException {
		JSONObject result = new JSONObject();
		List<Category> categoryList = new ArrayList<>();
		StringBuilder country = new StringBuilder();
		StringBuilder cacheCountry = new StringBuilder();
		for(String c : countries){
			country.append("?,");
			cacheCountry.append(c);
		}
		country.deleteCharAt(country.length()-1);
		for (String type : types) {
			cacheCountry.append(type);
		}
		String cached =cacheName+cacheCountry.toString();
		JCSCacheClient cache = JSCCacheManager.getInstace().getCache(cached);
		Object newsList = cache.get(cacheName);
		
		if (newsList != null) {
			result.put("categories", (org.json.simple.JSONArray) newsList);
		}
		if (categoryList == null || categoryList.isEmpty()) {
			String sql = "select distinct url,id,title, shotdesc,fromwebsite,imageurl,type,collectedtime,title_id,parent_catename from news where type = ? and country in ("+country.toString()+")  order by id desc limit "
					+ limit + " offset " + fromIndex;
			Connection conn = JDBCConnection.getInstance().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			try {
				for (String type : types) {
					stm.clearParameters();
					stm.setString(1, type);
					int count=2;
					for(String c : countries){
						stm.setString(count, c);
						count++;
					}
					ResultSet rs = stm.executeQuery();
					Category c = new Category(type);
					while (rs.next()) {
						if (!categoryList.contains(c)) {
							if (MappingHelper.categoryTypeLabelMapping.get(rs.getString("type")) != null) {
								c = new Category(new ArrayList<News>(),
										MappingHelper.categoryTypeLabelMapping.get(rs.getString("type")),
										rs.getString("type"));
							} else {
								c = new Category(new ArrayList<News>(), "", rs.getString("type"));
							}
							categoryList.add(c);
						} else {
							c = categoryList.get(categoryList.indexOf(c));
						}
						c.addNews(dataToNews(rs));
					}
					rs.close();
				}

				for (Category c : categoryList) {
					c.setSubcates(MappingHelper.parentCateMapping.get(c.getCateId()));
				}

				org.json.simple.JSONArray list = (org.json.simple.JSONArray) new JSONParser()
						.parse(JSONHelper.toJSONArray(categoryList).toString());
				result.put("categories", list);
				if (cacheName != null) {
					cache.put(cached, list);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				stm.close();
				conn.close();
			}

		}
		return result;
	}

	public JSONObject getLatestNews(List<String> types,List<String> countries, int limit, long time) throws SQLException, JSONException {
		JSONObject result = new JSONObject();
		List<Category> categoryList = new ArrayList<>();
		StringBuilder country = new StringBuilder();
		StringBuilder cacheCountry = new StringBuilder();
		for(String c : countries){
			country.append("?,");
			cacheCountry.append(c);
		}
		country.deleteCharAt(country.length()-1);

		if (categoryList == null || categoryList.isEmpty()) {
			String sql = "select distinct url,id,title, shotdesc,fromwebsite,imageurl,type,collectedtime,title_id,parent_catename from news where type = ? and collectedtime > ? and country in ("+country.toString()+")  order by id desc limit "
					+ limit;
			Connection conn = JDBCConnection.getInstance().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			try {
				
				for (String type : types) {
					stm.clearParameters();
					stm.setString(1, type);
					stm.setTimestamp(2, new Timestamp(time));
					int count=3;
					for(String c : countries){
						stm.setString(count, c);
						count++;
					}
					ResultSet rs = stm.executeQuery();
					Category c = new Category(type);
					while (rs.next()) {
						if (!categoryList.contains(c)) {
							if (MappingHelper.categoryTypeLabelMapping.get(rs.getString("type")) != null) {
								c = new Category(new ArrayList<News>(),
										MappingHelper.categoryTypeLabelMapping.get(rs.getString("type")),
										rs.getString("type"));
							} else {
								c = new Category(new ArrayList<News>(), "", rs.getString("type"));
							}
							categoryList.add(c);
						} else {
							c = categoryList.get(categoryList.indexOf(c));
						}
						c.addNews(dataToNews(rs));
					}
					rs.close();
				}

				for (Category c : categoryList) {
					c.setSubcates(MappingHelper.parentCateMapping.get(c.getCateId()));
				}

				org.json.simple.JSONArray list = (org.json.simple.JSONArray) new JSONParser()
						.parse(JSONHelper.toJSONArray(categoryList).toString());
				result.put("categories", list);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				stm.close();
				conn.close();
			}

		}
		return result;
	}

	public List<News> getMoreNews(String type,List<String> countries, int fromIndex, int limit) throws SQLException {
		List<News> newsList = new ArrayList<>();
		StringBuilder country = new StringBuilder();
		for(String c : countries){
			country.append("?,");
		}
		country.deleteCharAt(country.length()-1);
		String sql = "select * from news where type=? and country in ("+country.toString()+")  order by id desc limit " + limit + " offset " + fromIndex;
		Connection conn = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, type);
		int count=2;
		for(String c : countries){
			stm.setString(count, c);
			count++;
		}
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			newsList.add(dataToNews(rs));
		}
		rs.close();
		stm.close();
		conn.close();
		return newsList;
	}

	public List<News> getArticles(String cate, Set<String> types,List<String> countries, int fromIndex, int limit) throws SQLException {
		
		StringBuilder country = new StringBuilder();
		StringBuilder cacheCountry = new StringBuilder();
		for(String c : countries){
			country.append("?,");
			cacheCountry.append(c);
		}
		country.deleteCharAt(country.length()-1);
		for (String type : types) {
			cacheCountry.append(type);
		}
		String cached =cate+cacheCountry.toString()+ fromIndex;
		JCSCacheClient cache = JSCCacheManager.getInstace().getCache(cached);
		Object newsList = cache.get(cate+ cached + fromIndex);
		List<News> news = null;
		
		if (newsList != null) {
			news = (List<News>) newsList;
		}
		if (news == null || news.isEmpty()) {
			news = new ArrayList<>();
			StringBuilder filterTypes = new StringBuilder();
			for (String type : types) {
				filterTypes.append("'").append(type).append("'").append(",");
			}
			String sql = "select * from news where parent_catename in ("
					+ filterTypes.substring(0, filterTypes.length() - 1) + ") and country in ("+country.toString()+") order by id desc limit " + limit
					+ " offset " + fromIndex;
			
			Connection conn = JDBCConnection.getInstance().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			int count=1;
			for(String c : countries){
				stm.setString(count, c);
				count++;
			}
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				news.add(dataToNews(rs));
			}
			rs.close();
			stm.close();
			conn.close();
			if (news.size() > 0)
				cache.put(cached, news);
		}
		return news;

	}

	public News getNewsById(long id) throws SQLException {
		News news = null;
		String sql = "select * from news where id = ?  order by id desc";
		Connection conn = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setLong(1, id);
		ResultSet rs = stm.executeQuery();
		List<News> newsList = new ArrayList<>();
		while (rs.next()) {
			newsList.add(dataToNews(rs));
		}
		if (newsList.size() > 0)
			news = newsList.get(0);
		rs.close();
		stm.close();
		conn.close();
		return news;
	}

	public void removeNewsById(long id) throws SQLException {
		String sql = "delete from news where id = ?";
		Connection conn = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setLong(1, id);
		stm.execute();
		stm.close();
		conn.close();
	}

	private News dataToNews(ResultSet rs) throws SQLException {
		News news = new News();
		news.setTitle(rs.getString("title"));
		news.setShotDesc(rs.getString("shotdesc"));
		news.setImageUrl(rs.getString("imageurl"));
		news.setType(rs.getString("type"));
		news.setFromWebSite(rs.getString("fromwebsite"));
		news.setUrl(rs.getString("url"));
		news.setId(rs.getInt("id"));
		news.setParentCateName(rs.getString("parent_catename"));
		try {
			news.setDate(rs.getTimestamp("collectedtime"));
		} catch (Exception ex) {
		}
		return news;
	}

	public void storeNews(News news) {
		Connection con = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement(
					"insert into news(title,shotdesc,url,fromwebsite,imageurl,type,ishotnews,newsorder,collectedtime,title_id)values(?,?,?,?,?,?,?,?,?,?)");
			stm.setString(1, news.getTitle());
			stm.setString(2, news.getShotDesc());
			stm.setString(3, news.getUrl());
			stm.setString(4, news.getFromWebSite());
			stm.setString(5, news.getImageUrl());
			stm.setString(6, news.getType());
			stm.setBoolean(7, news.isHotNews());
			stm.setString(8, news.getNewsOrder());
			stm.setDate(9, new Date(System.currentTimeMillis()));
			stm.setString(10, news.getUniqueName());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null)
					stm.close();
				con.close();
			} catch (SQLException e) {
			}
		}
	}

	public List<News> getMoreHighlightNews(String cate, int fromIndex, int limit) throws Exception {
		List<News> newsList = new ArrayList<>();
		String sql = "select * from news where parent_catename=?  order by id desc limit " + limit + " offset "
				+ fromIndex;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getInstance().getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, cate);
			rs = stm.executeQuery();
			while (rs.next()) {
				newsList.add(dataToNews(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stm.close();
			conn.close();
		}
		return newsList;
	}

	public List<News> getArticles(String userId, int fromIndex, int numofarcts) {
		List<News> news = new ArrayList<News>();
		List<String> interestedCates = getUserInterestedCategories(userId);
		int count = 0;
		for (String cate : interestedCates) {
			Category c = cachedArticles.get(cate);
			if (c != null) {
				for (News n : c.getNews()) {
					if (count >= fromIndex && news.size() < numofarcts) {
						news.add(n);
					} else if (news.size() == numofarcts) {
						break;
					}
					count++;
				}
			}
			if (news.size() == numofarcts) {
				break;
			}
		}
		return news;
	}

	private List<String> getUserInterestedCategories(String userId) {
		// not yet know user interested so return default
		return MappingHelper.cateGroup.get(NewsTypes.CATEGORY.HotNews.name());
	}

}
