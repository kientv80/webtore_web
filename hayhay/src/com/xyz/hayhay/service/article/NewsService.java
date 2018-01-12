package com.xyz.hayhay.service.article;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
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
import com.xyz.webstore.mobile.config.UserSettings;

public class NewsService {

	private static NewsService instance;
	private Map<String, Category> cachedArticles = new ConcurrentHashMap<>();
	private Timer t = new Timer();
	private static String block = new String("block");

	public static synchronized NewsService getInstance() {
		synchronized (block) {
			if (instance == null)
				instance = new NewsService();
		}
		return instance;
	}

	private NewsService() {
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					System.out.println("loading articles...");
					loadLatestArticles();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}, 0, 5 * 60 * 1000);// update every five minutes
	}

	private void loadLatestArticles() throws SQLException {
		StringBuilder filterTypes = new StringBuilder();
		for (String type : MappingHelper.cateGroup.get(NewsTypes.CATEGORY.HotNews.name())) {
			filterTypes.append("'").append(type).append("'").append(",");
		}
		String sql = "SELECT distinct url,id,title, shotdesc,fromwebsite,imageurl,type,collectedtime,title_id,parent_catename,country,language FROM news WHERE parent_catename in ("
				+ filterTypes.substring(0, filterTypes.length() - 1) + ") order by id desc limit 50";
		try (Connection conn = JDBCConnection.getInstance().getConnection()) {
			try (PreparedStatement stm = conn.prepareStatement(sql)) {
				try (ResultSet rs = stm.executeQuery()) {
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
				}

			}

		}

	}

	public List<News> getLatestNews(String uid, String locale, int limit, long fromTime) throws Exception {
		List<String> lgs = UserSettings.getUserSetting(uid, UserSettings.TYPE_FAVORITE_LANGUAGES, locale);

		List<News> news = new ArrayList<>();
		for (Category c : cachedArticles.values()) {
			for (News n : c.getNews()) {
				if (lgs.contains(n.getLang()) && n.getDate().getTime() > fromTime) {
					news.add(n);
				}
			}
		}
		return news;
	}

	public List<Category> getOfflineNews(String uid, String locale, int limit, long fromTime) throws Exception {
		List<News> news = getLatestNews(uid, locale, limit, fromTime);
		Map<String, Category> cates = new HashMap<>();
		if (news != null && !news.isEmpty()) {
			for (News n : news) {
				Category c = cates.get(n.getParentCateName());
				if (c == null) {
					c = new Category(n.getParentCateName());
					c.setNews(new ArrayList<News>());
					cates.put(n.getParentCateName(), c);
				}
				c.addNews(n);
			}
		}
		return new ArrayList<>(cates.values());
	}

	public List<News> getNews(String type, int limit) throws SQLException {
		List<News> newsList = new ArrayList<>();
		String sql = "select * from news where type=?  order by id desc limit " + limit;
		if (limit == -1)
			sql = "select * from news where type=?";
		try (Connection conn = JDBCConnection.getInstance().getConnection()) {
			try (PreparedStatement stm = conn.prepareStatement(sql)) {
				stm.setString(1, type);
				try (ResultSet rs = stm.executeQuery()) {
					while (rs.next()) {
						newsList.add(dataToNews(rs));
					}
				}
			}

		}
		return newsList;
	}

	public List<News> getHighlightNews(String parentCate, int limit) throws SQLException {
		List<News> newsList = new ArrayList<>();
		String sql = "select distinct url,id,title, shotdesc,fromwebsite,imageurl,type,collectedtime,title_id,parent_catename,country,language from news where parent_catename = ?  order by id desc limit "
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

	private final int MAX_CACHED = 200;

	public JSONObject getHighlightNews(String uid, String locale, int limit, int fromIndex) throws Exception {
		JSONObject result = new JSONObject();
		Map<String, Category> categoryList = new LinkedHashMap<>();
		List<String> cateNames = UserSettings.getUserSetting(uid, UserSettings.TYPE_FAVORITE_CATE, locale);
		List<String> languages = UserSettings.getUserSetting(uid, UserSettings.TYPE_FAVORITE_LANGUAGES, locale);
		List<String> countries = UserSettings.getUserSetting(uid, UserSettings.TYPE_FAVORITE_COUNTRIES, locale);
		StringBuilder cachedKey = new StringBuilder("article_");
		cachedKey.append(StringUtils.join(cateNames, "_"));
		cachedKey.append(StringUtils.join(languages, "_"));
		cachedKey.append(StringUtils.join(countries, "_")).append("_from_" + fromIndex);
		System.out.println(cateNames.toString());
		System.out.println(languages.toString());
		System.out.println(countries.toString());
		System.out.println("fromIndex=" + fromIndex);

		StringBuilder country = new StringBuilder();
		StringBuilder cates = new StringBuilder();
		StringBuilder language = new StringBuilder();

		for (String c : countries) {
			country.append("?,");
		}
		country.deleteCharAt(country.length() - 1);
		for (String type : cateNames) {
			cates.append("?,");
		}
		cates.deleteCharAt(cates.length() - 1);
		for (String l : languages) {
			language.append("?,");
		}
		language.deleteCharAt(language.length() - 1);

		JCSCacheClient cache = JSCCacheManager.getInstace().getCache(cachedKey.toString());
		Object newsList = cache.get(cachedKey);
		if (fromIndex <= MAX_CACHED && newsList != null && ((org.json.simple.JSONArray) newsList).size() > 0) {
			result.put("categories", (org.json.simple.JSONArray) newsList);
		} else {
			String sql = "select distinct url,id,title, shotdesc,fromwebsite,imageurl,type,collectedtime,title_id,parent_catename,country,language  from news where parent_catename in ("
					+ cates.toString() + ") and country in (" + country.toString() + ") and language in ("
					+ language.toString() + ")  order by id desc limit " + limit + " offset " + fromIndex;
			int count = 0;
			try (Connection conn = JDBCConnection.getInstance().getConnection()) {
				try (PreparedStatement stm = conn.prepareStatement(sql)) {
					for (int i = 0; i < cateNames.size(); i++) {
						count++;
						stm.setString(count, cateNames.get(i));
					}
					for (int i = 0; i < countries.size(); i++) {
						count++;
						stm.setString(count, countries.get(i));
					}
					for (int i = 0; i < languages.size(); i++) {
						count++;
						stm.setString(count, languages.get(i));
					}
					System.out.println(sql);
					System.out.println("count=" + count);
					try (ResultSet rs = stm.executeQuery()) {
						while (rs.next()) {
							News n = dataToNews(rs);
							Category c = categoryList.get(n.getParentCateName());
							if (c == null) {
								c = new Category(n.getParentCateName());
								c.setNews(new ArrayList<News>());
								categoryList.put(n.getParentCateName(), c);
							}
							c.addNews(n);
						}
					}
					List<Category> sortCates = new ArrayList<>(categoryList.values());
					java.util.Collections.sort(sortCates, new Comparator<Category>() {
						@Override
						public int compare(Category o1, Category o2) {
							// TODO Auto-generated method stub
							return Integer.compare(NewsTypes.CATEGORY.valueOf(o1.getCateId()).ordinal(),
									NewsTypes.CATEGORY.valueOf(o2.getCateId()).ordinal());
						}
					});

					org.json.simple.JSONArray list = (org.json.simple.JSONArray) new JSONParser()
							.parse(JSONHelper.toJSONArray(sortCates).toString());
					sortCates.clear();
					categoryList.clear();
					sortCates = null;
					categoryList = null;
					if (fromIndex <= MAX_CACHED && list != null && list.size() > 0)
						cache.put(cachedKey, list);
					result.put("categories", list);
				}
			}
		}
		return result;
	}

	public JSONObject getNews(String uid, String locale, List<String> types, int limit, int fromIndex)
			throws Exception {
		JSONObject result = new JSONObject();
		List<Category> categoryList = new ArrayList<>();
		List<String> languages = UserSettings.getUserSetting(uid, UserSettings.TYPE_FAVORITE_LANGUAGES, locale);
		List<String> countries = UserSettings.getUserSetting(uid, UserSettings.TYPE_FAVORITE_COUNTRIES, locale);
		StringBuilder cachedKey = new StringBuilder("article_");
		System.out.println(types.toString());
		System.out.println(languages.toString());
		System.out.println(countries.toString());

		cachedKey.append(StringUtils.join(types, "_"));
		cachedKey.append(StringUtils.join(languages, "_"));
		cachedKey.append(StringUtils.join(countries, "_")).append("_from_" + fromIndex);
		JCSCacheClient cache = JSCCacheManager.getInstace().getCache(cachedKey.toString());
		Object newsList = cache.get(cachedKey.toString());

		if (fromIndex <= MAX_CACHED && newsList != null) {
			result.put("categories", (org.json.simple.JSONArray) newsList);
		} else {
			StringBuilder type = new StringBuilder();
			StringBuilder langs = new StringBuilder();
			StringBuilder counts = new StringBuilder();
			for (int i = 0; i < types.size(); i++) {
				type.append("?,");
			}
			type.deleteCharAt(type.length() - 1);
			for (String l : languages) {
				langs.append("?,");
			}
			langs.deleteCharAt(langs.length() - 1);
			for (String c : countries) {
				counts.append("?,");
			}
			counts.deleteCharAt(counts.length() - 1);

			String sql = "select distinct url,id,title, shotdesc,fromwebsite,imageurl,type,collectedtime,title_id,parent_catename,country,language from news where type in ("
					+ type.toString() + ") and country in (" + counts + ") and language in (" + langs.toString()
					+ ")  order by id desc limit " + limit + " offset " + fromIndex;
			System.out.println(sql);

			Map<String, Category> news = new HashMap<>();
			try (Connection conn = JDBCConnection.getInstance().getConnection()) {
				try (PreparedStatement stm = conn.prepareStatement(sql)) {
					int count = 0;
					for (int i = 0; i < types.size(); i++) {
						count++;
						stm.setString(count, types.get(i));
					}
					for (int i = 0; i < countries.size(); i++) {
						count++;
						stm.setString(count, countries.get(i));
					}
					for (int i = 0; i < languages.size(); i++) {
						count++;
						stm.setString(count, languages.get(i));
					}

					System.out.println("count=" + count);
					try (ResultSet rs = stm.executeQuery()) {
						while (rs.next()) {
							News n = dataToNews(rs);
							Category c = news.get(n.getType());
							if (c == null) {
								c = new Category(n.getType());
								c.setNews(new ArrayList<News>());
								news.put(n.getType(), c);
							}
							c.addNews(n);
						}
					}
				}
			}
			if (!news.isEmpty()) {
				categoryList = new ArrayList<>(news.values());
				java.util.Collections.sort(categoryList, new Comparator<Category>() {
					@Override
					public int compare(Category o1, Category o2) {
						return Integer.compare(NewsTypes.TYPE.valueOf(o1.getCateId()).ordinal(),
								NewsTypes.TYPE.valueOf(o2.getCateId()).ordinal());
					}

				});

				org.json.simple.JSONArray list = (org.json.simple.JSONArray) new JSONParser()
						.parse(JSONHelper.toJSONArray(categoryList).toString());
				categoryList.clear();
				categoryList = null;
				result.put("categories", list);

				if (fromIndex <= MAX_CACHED && list != null && list.size() > 0 && cachedKey != null) {
					cache.put(cachedKey, list);
				}
			}

		}
		return result;
	}

	public List<News> getNewsByType(String uid, String type, int fromIndex, int limit) throws Exception {
		List<News> newsList = new ArrayList<>();
		List<String> languages = UserSettings.getUserSetting(uid, UserSettings.TYPE_FAVORITE_LANGUAGES, "");
		List<String> countries = UserSettings.getUserSetting(uid, UserSettings.TYPE_FAVORITE_COUNTRIES, "");
		StringBuilder cachedKey = new StringBuilder("article_");

		System.out.println(languages.toString());
		System.out.println(countries.toString());

		cachedKey.append(type);
		cachedKey.append(StringUtils.join(languages, "_"));
		cachedKey.append(StringUtils.join(countries, "_")).append("_from_" + fromIndex);
		JCSCacheClient cache = JSCCacheManager.getInstace().getCache(cachedKey.toString());
		Object cnewsList = cache.get(cachedKey.toString());
		if (cnewsList != null) {
			newsList = (List<News>) cnewsList;
		} else {
			StringBuilder langs = new StringBuilder();
			StringBuilder counts = new StringBuilder();
			for (String l : languages) {
				langs.append("?,");
			}
			langs.deleteCharAt(langs.length() - 1);
			for (String c : countries) {
				counts.append("?,");
			}
			counts.deleteCharAt(counts.length() - 1);
			String sql = "select distinct url,id,title, shotdesc,fromwebsite,imageurl,type,collectedtime,title_id,parent_catename,country,language  from news where type = ?"
					+ " and country in (" + counts.toString() + ") and language in (" + langs.toString()
					+ ")  order by id desc limit " + limit + " offset " + fromIndex;
			int count = 1;
			try (Connection conn = JDBCConnection.getInstance().getConnection()) {
				try (PreparedStatement stm = conn.prepareStatement(sql)) {
					stm.setString(1, type);
					for (int i = 0; i < countries.size(); i++) {
						count++;
						stm.setString(count, countries.get(i));
					}
					for (int i = 0; i < languages.size(); i++) {
						count++;
						stm.setString(count, languages.get(i));
					}
					System.out.println(sql);
					System.out.println("count=" + count);
					try (ResultSet rs = stm.executeQuery()) {
						while (rs.next()) {
							News n = dataToNews(rs);
							newsList.add(n);
						}
					}

					if (newsList.size() > 0)
						cache.put(cachedKey, newsList);
				}
			}
		}
		return newsList;
	}

	public List<News> getArticles(String cate, Set<String> types, List<String> countries, int fromIndex, int limit)
			throws SQLException {

		StringBuilder country = new StringBuilder();
		StringBuilder cacheCountry = new StringBuilder();
		for (String c : countries) {
			country.append("?,");
			cacheCountry.append(c);
		}
		country.deleteCharAt(country.length() - 1);
		for (String type : types) {
			cacheCountry.append(type);
		}
		String cached = cate + cacheCountry.toString() + fromIndex;
		JCSCacheClient cache = JSCCacheManager.getInstace().getCache(cached);
		Object newsList = cache.get(cate + cached + fromIndex);
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
					+ filterTypes.substring(0, filterTypes.length() - 1) + ") and country in (" + country.toString()
					+ ") order by id desc limit " + limit + " offset " + fromIndex;

			Connection conn = JDBCConnection.getInstance().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			int count = 1;
			for (String c : countries) {
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
			// if (news.size() > 0)
			// cache.put(cached, news);
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
		news.setLang(rs.getString("language"));
		news.setCountry(rs.getString("country"));
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
		try (Connection conn = JDBCConnection.getInstance().getConnection()) {
			try (PreparedStatement stm = conn.prepareStatement(sql)) {
				stm.setString(1, cate);
				try (ResultSet rs = stm.executeQuery()) {
					while (rs.next()) {
						newsList.add(dataToNews(rs));
					}
				}
			}
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
