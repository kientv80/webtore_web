package com.xyz.hayhay.worker;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.xyz.hayhay.cache.JCSCacheClient;
import com.xyz.hayhay.cache.JSCCacheManager;
import com.xyz.hayhay.db.JDBCConnection;
import com.xyz.hayhay.entirty.Category;
import com.xyz.hayhay.entirty.News;
import com.xyz.hayhay.entirty.NewsTypes;
import com.xyz.hayhay.service.article.NewsService;
import com.xyz.hayhay.socialplugin.FaceBookFeed;
import com.xyz.hayhay.socialplugin.SocialServiceFactory;
import com.xyz.hayhay.util.MyUtil;

public class SocialPostFeedNews implements Runnable {
	Logger log = Logger.getLogger(SocialPostFeedNews.class);
	List<Integer> runningHours = new ArrayList<>();
	{
		runningHours.add(8);
		runningHours.add(10);
		runningHours.add(11);
		runningHours.add(12);
		runningHours.add(13);
		runningHours.add(14);
		runningHours.add(15);
		runningHours.add(16);
		runningHours.add(17);
		runningHours.add(18);
		runningHours.add(19);
		runningHours.add(20);
		runningHours.add(21);
		runningHours.add(22);
	}
	int count = 0;
	String currentDay = "";
	int currentHour = 0;
	public static int lastPostedHour = 0;
	private static SocialPostFeedNews instance;

	public static SocialPostFeedNews getInstance() {
		if (instance == null)
			instance = new SocialPostFeedNews();
		return instance;
	}

	@Override
	public void run() {
		try {
			if (runningHours.contains(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
					&& Calendar.getInstance().get(Calendar.HOUR_OF_DAY) != SocialPostFeedNews.lastPostedHour) {

				log.info(">>>>>>>>>>>>>>> START running SocialPostFeedNews");
				JSONObject news = NewsService.getInstance().getNews("all",
						Arrays.asList(new String[] { NewsTypes.SHOWBIZVIET, NewsTypes.HOLLYWOOD, NewsTypes.PHONGCACH,
								NewsTypes.TINHYEU, NewsTypes.GIOITINH, NewsTypes.GIADINH, NewsTypes.YHOCCOTRUYEN,
								NewsTypes.BAITHUOC, NewsTypes.THUOCQUANHTA, NewsTypes.AMTHUC, NewsTypes.HUONGTHIEN,
								NewsTypes.TYPE_TINTRONGNUOC, NewsTypes.TYPE_TINQUOCTE, NewsTypes.NHACMOI }),
						1,0);
				SocialPostFeedNews.lastPostedHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
				JSONArray listNewsCates = (JSONArray) news.get("categories");
				JSONObject oneCate = (JSONObject) listNewsCates.get(count);
				JSONArray newsInOneCate = (JSONArray) oneCate.get("news");
				JSONObject oneNews = (JSONObject)newsInOneCate.get(0);

				String imageUrl = oneNews.get("imageUrl").toString();

				count++;
				if (count == runningHours.size())
					count = 0;

				if (oneNews != null) {
					String dayName = new SimpleDateFormat("E").format(new Date());
					try {
						String url = oneNews.get("url").toString();
						FaceBookFeed f = null;
						if (url.indexOf("~") < 0) {
							url = MyUtil.encodeUrl(url);
							url = "http://360hay.com/news/open/" + url;
						} else {
							url = "http://360hay.com?target=" + URLEncoder.encode(url, "UTF-8");

						}
						f = new FaceBookFeed(url, oneNews.get("title").toString(), oneNews.get("shotDesc").toString(),
								imageUrl);
						SocialServiceFactory.getFaceBookService().postFeed(f);
					} catch (Exception ex) {
						ex.printStackTrace();
						log.error("Post FB feed failed", ex);
					}

				} else {// Sun,Mon,Tue,Wed,Thu,Fri,Sat
					log.info(">>>>>>>>>>>>>>> Cannot find postNews for FB current time=" + Calendar.HOUR_OF_DAY
							+ " -- last time processed=" + SocialPostFeedNews.lastPostedHour);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public News getNews(String type, List<News> newsList) {
		for (News n : newsList) {
			if (n.getType().equals(type))
				return n;
		}
		return null;
	}

	public static List<Category> getFeedNews(String id) {
		List<Category> categoryList = new ArrayList<>();
		JCSCacheClient cache = JSCCacheManager.getInstace().getCache("article");
		if (id != null && cache.get(id) != null) {
			categoryList = (List<Category>) cache.get(id);
			return categoryList;
		}

		String sql = "select * from publish_feed where feed_id = ?";
		Connection conn = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, id);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				String cates = rs.getString("articles");
				JSONArray jcs = (JSONArray)new JSONParser().parse(cates);
				
				for (int i = 0; i < jcs.size(); i++) {
					org.json.JSONObject jc = (org.json.JSONObject) jcs.get(i);
					Category c = new Category(jc.get("name").toString());
					JSONArray jnews = (JSONArray) jc.get("news");
					for (int j = 0; j < jnews.size(); j++) {
						org.json.JSONObject jn = (org.json.JSONObject) jnews.get(j);
						News n = new News();
						n.setTitle(jn.getString("title"));
						n.setImageUrl(jn.getString("imageUrl"));
						n.setUrl(jn.getString("url"));
						n.setShotDesc(jn.getString("shotDesc"));
						c.getNews().add(n);
					}
					categoryList.add(c);
				}
			}
			rs.close();
			cache.put(id, categoryList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stm.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return categoryList;
	}

	public static void main(String[] args) {
		new SocialPostFeedNews().run();
	}
}
