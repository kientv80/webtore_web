package com.xyz.hayhay.website.collector;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.log4j.Logger;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeIterator;

import com.xyz.hayhay.db.JDBCConnection;
import com.xyz.hayhay.entirty.Channel;
import com.xyz.hayhay.entirty.CollectorLog;
import com.xyz.hayhay.entirty.News;
import com.xyz.hayhay.entirty.Program;
import com.xyz.hayhay.entirty.TVStation;
import com.xyz.hayhay.entirty.Website;

import net.htmlparser.jericho.Source;

public abstract class BasedCollector extends WebsiteCollector {
	private static final int MAX_OLD_NEWS = 100;
	public Logger log = Logger.getLogger(BasedCollector.class);
	protected Set<String> collectedArticles = new HashSet<>();

	protected boolean isCollected(String articleTitle) {
		return false;
	}

	protected void storeTVProgram(TVStation tvStation) {
		if (tvStation.getChannels().size() > 0) {
			Connection con = JDBCConnection.getInstance().getConnection();
			PreparedStatement stm = null;
			PreparedStatement stm1 = null;
			Statement stm2 = null;
			try {
				stm = con.prepareStatement("insert into channel(name,tvstation)values(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
				stm1 = con.prepareStatement("insert into tvprogram(time,name,title,channel)values(?,?,?,?)");
				stm2 = con.createStatement();
				stm2.execute("delete from tvprogram where channel in (select id from channel where tvstation='" + tvStation.getName() + "')");
				stm2.execute("delete from channel where tvstation='" + tvStation.getName() + "'");
				stm2.close();
				if (tvStation.getChannels() != null && tvStation.getChannels().size() > 0) {
					CollectorLog clog = new CollectorLog(-1, getCollectorName(), 0, 0, "", null, "sucess", "");
					clog.setFoundNews(tvStation.getChannels().size());
				} else {
					CollectorLog clog = new CollectorLog(-1, getCollectorName(), 0, 0, "", null, "warning", "Cannot get chancels");
					clog.setFoundNews(tvStation.getChannels().size());
				}
				System.out.println( getCollectorName() + " collect " + tvStation.getChannels().size() + " channels");
				for (Channel c : tvStation.getChannels()) {
					if (c != null && c.getPrograms().size() > 0) {
						System.out.println(c.getName()+ " collect " + c.getPrograms().size() + " programes");
						stm.setString(1, c.getName());
						stm.setString(2, tvStation.getName());
						stm.execute();
						ResultSet rs = stm.getGeneratedKeys();
						int id = 0;
						while (rs.next()) {
							id = rs.getInt(1);
						}
						rs.close();
						for (Program p1 : c.getPrograms()) {
							stm1.setString(1, p1.getTime());
							stm1.setString(2, p1.getProgramName());
							stm1.setString(3, p1.getTitle());
							stm1.setInt(4, id);
							stm1.addBatch();
						}
					}
				}
				stm1.executeBatch();
				// SocialServiceFactory.getZaloService().sendTextMessageByPhoneNum(new
				// ZaloMessage(84908995558l, "Store" + tvStation.getName()));
			} catch (Exception e) {
				log.error("", e);
				e.printStackTrace();
			} finally {
				try {
					stm.close();
					stm1.close();
					con.close();
				} catch (SQLException e) {
					log.error("", e);
					e.printStackTrace();
				}
			}
		}
	}

	protected void storeNews(Website website) {

		if (website.getNews().size() > 0) {
			removeDuplicatedNews(website);

			Connection con = JDBCConnection.getInstance().getConnection();
			PreparedStatement stm = null;
			CollectorLog clog = new CollectorLog(-1, getCollectorName(), 0, 0, "", null, "sucess", "");
			clog.setFoundNews(website.getNews().size());
			System.out.println(">>>>>>>>>>>>>>>>>>> news website = " + website.getName() + " found count" + website.getNews().size());
			if (website.getNews().size() > 100) {
				website.setNews(website.getNews().subList(0, 100));
			}
			try {
				Statement stm2 = con.createStatement();
				if (!website.isOverwrite()) {
					ResultSet rs = stm2.executeQuery("select title_id  from news where fromwebsite='" + website.getName() + "' and parent_catename='" + website.getNews().get(0).getParentCateName() + "'");
					StringBuilder keepIds = new StringBuilder();
					while (rs.next()) {
						if (rs.getString("title_id") != null && !rs.getString("title_id").isEmpty()) {
							keepIds.append(rs.getString("title_id")).append(",");
						}
					}
					rs.close();
					if (keepIds.length() > 0) {
						List<News> removeList = new ArrayList<News>();
						for (News n : website.getNews()) {
							if (keepIds.indexOf(n.getUniqueName()) >= 0) {
								removeList.add(n);
							}
						}
						website.getNews().removeAll(removeList);
						clog.setNewNews(website.getNews().size());
					} else {
						stm2.execute("delete from news where fromwebsite='" + website.getName() + "' and type='" + website.getNews().get(0).getType() + "'");
					}
				}else{
					stm2.execute("delete from news where fromwebsite='" + website.getName() + "' and type='" + website.getNews().get(0).getType() + "'");
				}
				stm2.close();
				Collections.reverse(website.getNews());
				log.info(">>>>>>>>>>>>>>>>>>> news website = " + website.getName() + " count" + website.getNews().size());
				System.out.println(">>>>>>>>>>>>>>>>>>> news website = " + website.getName() + " store count" + website.getNews().size());
				if (website.getNews().size() > 0) {
					stm = con.prepareStatement("insert into news(title,shotdesc,url,fromwebsite,imageurl,type,ishotnews,newsorder,collectedtime,title_id,parent_catename)values(?,?,?,?,?,?,?,?,?,?,?)");
					for (News n : website.getNews()) {
						stm.clearParameters();
						stm.setString(1, n.getTitle());
						stm.setString(2, n.getShotDesc());
						stm.setString(3, n.getUrl());
						stm.setString(4, n.getFromWebSite());
						stm.setString(5, n.getImageUrl());
						stm.setString(6, n.getType());
						stm.setBoolean(7, n.isHotNews());
						stm.setString(8, n.getNewsOrder());
						stm.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
						stm.setString(10, n.getUniqueName());
						stm.setString(11, n.getParentCateName());
						stm.addBatch();
					}
					stm.executeBatch();
					removeOldNewsAndKeepOnly50LatestNews(website.getNews().get(0).getType(), website.getName(), con);
				}
			} catch (Exception e) {
				log.error("", e);
				e.printStackTrace();
			} finally {
				try {
					if (stm != null)
						stm.close();

					con.close();
				} catch (SQLException e) {
					log.error("", e);
				}
			}

		} else {
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + website.getName() + " collect " + "0 news ");
		}
	}

	private void removeDuplicatedNews(Website website) {
		Set<String> urls = new HashSet<>();
		List<News> removeIds = new ArrayList<>();
		for (News n : website.getNews()) {
			if (urls.contains(n.getUrl())) {
				removeIds.add(n);
			} else {
				urls.add(n.getUrl());
			}
		}
		website.getNews().removeAll(removeIds);
	}

	private void removeOldNewsAndKeepOnly50LatestNews(String type, String website, Connection con) throws SQLException {
		String sql = "select id  from news where type='" + type + "' and fromwebsite='" + website + "'  order by id desc limit " + MAX_OLD_NEWS + "";
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		StringBuilder ids = new StringBuilder();
		while (rs.next()) {
			ids.append(rs.getString(1)).append(",");
		}
		ids.append(0);
		stm.execute("delete from news where type='" + type + "' and fromwebsite='" + website + "' AND id not in(" + ids.toString() + ") ");
		stm.close();
	}

	public String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;

		for (NameValuePair pair : params) {
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
		}

		return result.toString();
	}
	
	protected Source getSource(String url) throws Exception {
		Parser pa = new Parser(url);
		NodeIterator no = pa.elements();
		StringBuilder content = new StringBuilder();
		while (no.hasMoreNodes()) {
			content.append(no.nextNode().toHtml());
		}
		Source s = new Source(content);
		return s;
	}

}

interface ParseVisitor {
	String parseValue(String string);
}
