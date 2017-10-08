package com.xyz.hayhay.service.article;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xyz.hayhay.cache.JCSCacheClient;
import com.xyz.hayhay.cache.JSCCacheManager;
import com.xyz.hayhay.db.JDBCConnection;
import com.xyz.hayhay.entirty.Article;
import com.xyz.hayhay.entirty.Article.TYPE;
import com.xyz.hayhay.service.ads.LuckyPerson;
import com.xyz.hayhay.entirty.Comment;

public class HayHayServiceImpl implements HayHayService {

	private static final String ARTICLES = "ARTICLES_";
	private static final String ARTICLE = "ARTICLE_";
	private static final String BEST_ARTICLE = "BEST_ARTICLE_";
	private static final String LATEST_ARTICLE = "LATEST_ARTICLE_";

	@Override
	public int newArticle(Article article) throws Exception {
		String sql = "insert into article(subject,content,image,createddate,createdby,type)values(?,?,?,?,?,?)";
		Connection conn = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		stm.setString(1, article.getSubject());
		stm.setString(2, article.getContent());
		stm.setString(3, article.getImage());
		stm.setDate(4, new Date(System.currentTimeMillis()));
		stm.setString(5, article.getCreatedBy());
		stm.setInt(6, article.getType());
		stm.execute();
		ResultSet rs = stm.getGeneratedKeys();
		int id = 0;
		while (rs.next()) {
			id = rs.getInt(1);
		}
		rs.close();
		stm.close();
		String key = "lastest_"+article.getType();
		Statement st = conn.createStatement();
		int result = st.executeUpdate("update keyvalue set value_='" + id + "' where key_='" + key + "'");
		if (result == 0) {
			st.execute("insert into keyvalue(key_,value_) values ('" + key + "','" + id + "')");
		}
		st.close();
		conn.close();
		JCSCacheClient cache = JSCCacheManager.getInstace().getCache("article");
		cache.remove(LATEST_ARTICLE +  Article.TYPE.values()[article.getType()].name());
		cache.remove(ARTICLES +  Article.TYPE.values()[article.getType()].name());
		return id;
	}

	@Override
	public int updateArticle(Article article) throws Exception {
		String sql = "update article set subject=?, content=?, image=?, type=? where id=?";
		Connection conn = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, article.getSubject());
		stm.setString(2, article.getContent());
		stm.setString(3, article.getImage());
		stm.setInt(4, article.getType());
		stm.setLong(5, article.getId());
		int result = stm.executeUpdate();
		stm.close();
		conn.close();
		JCSCacheClient cache = JSCCacheManager.getInstace().getCache("article");
		cache.remove(ARTICLE + article.getId());
		cache.remove(LATEST_ARTICLE +  Article.TYPE.values()[article.getType()].name());
		cache.remove(ARTICLES +  Article.TYPE.values()[article.getType()].name());
		return result;
	}

	@Override
	public void removeArticle(Long id) throws Exception {
		if(id == null)
			return;
		
		Article art = getArticle(id.intValue());
		String sql = "delete from article where id = ?";
		Connection conn = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setLong(1, id);
		stm.execute();
		stm.close();
		conn.close();
		if(art != null){
			JCSCacheClient cache = JSCCacheManager.getInstace().getCache("article");
			cache.remove(ARTICLE + id);
			cache.remove(LATEST_ARTICLE +  Article.TYPE.values()[art.getType()].name());
			cache.remove(ARTICLES +  Article.TYPE.values()[art.getType()].name());
		}
	}

	@Override
	public void postCommentOnArticle(long articleId, Comment comment) throws Exception {
		String sql = "insert into article_comment(article_id,content,createddate,createdby)values(" + articleId + ",'" + comment.getContent() + "',CURRENT_TIMESTAMP,'" + comment.getCreatedBy() + "')";
		Connection conn = JDBCConnection.getInstance().getConnection();
		Statement stm = conn.createStatement();
		stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stm.getGeneratedKeys();
		long id = 0;
		while (rs.next()) {
			id = rs.getInt(1);
		}
		rs.close();
		sql = "update article set comment_ids = comment_ids || '" + id + "' || ','  where id=" + articleId;
		stm.executeUpdate(sql);
		sql = "update article set comment_count = comment_count + 1  where id=" + articleId;
		stm.executeUpdate(sql);
		stm.close();
		conn.close();
	}

	@Override
	public void likeArticle(long articleId, String userId) throws Exception {
		Connection conn = JDBCConnection.getInstance().getConnection();
		Statement stm = conn.createStatement();
		String sql = "update article set like_count = like_count + 1  where id=" + articleId;
		stm.executeUpdate(sql);
		stm.close();
		conn.close();
	}

	@Override
	public void shareArticle(long articleId, String userId) throws Exception {
		Connection conn = JDBCConnection.getInstance().getConnection();
		Statement stm = conn.createStatement();
		String sql = "update article set share_uids = share_uids || '" + userId + "' || ','  where id=" + articleId;
		stm.executeUpdate(sql);
		sql = "update article set share_count = share_count + 1  where id=" + articleId;
		stm.executeUpdate(sql);
		stm.close();
		conn.close();
	}

	@Override
	public void publishArticle(long articleId, boolean approve) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Article> getArticles(String type, int limit, boolean getFromCache) throws Exception {
		List<Article> articlees = new ArrayList<>();
		JCSCacheClient cache = JSCCacheManager.getInstace().getCache("article");
		if(getFromCache && cache.get(ARTICLES + type) != null){
			 return (List<Article>)cache.get(ARTICLES + type);
		}else {
			String sql = "select * from article where type=" + type + " order by id desc limit " + limit;
			Connection conn = JDBCConnection.getInstance().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Article art = new Article();
				art.setId(rs.getLong("id"));
				art.setSubject(rs.getString("subject"));
				art.setContent(rs.getString("content"));
				art.setImage(rs.getString("image"));
				art.setCreateDate(rs.getDate("createddate"));
				art.setCreatedBy(rs.getString("createdby"));
				art.setCommentIds(rs.getString("comment_ids"));
				art.setLikeIds(rs.getString("like_uids"));
				art.setShareIds(rs.getString("share_uids"));
				art.setType(rs.getInt("type"));
				art.setLikeCount(rs.getInt("like_count"));
				articlees.add(art);
			}
			stm.close();
			conn.close();
			cache.put(ARTICLES + type, articlees);
			return articlees;
		}
	}

	@Override
	public List<Comment> getArticleComments(String commentIds) throws Exception {
		List<Comment> comments = new ArrayList<>();
		if (commentIds != null && !commentIds.isEmpty() && commentIds.length() > 1) {
			commentIds = commentIds.substring(0, commentIds.length() - 1);
			String sql = "select * from article_comment where id in (" + commentIds + ")";
			Connection conn = JDBCConnection.getInstance().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Comment com = new Comment();
				com.setId(rs.getLong("id"));
				com.setItemId(rs.getLong("article_id"));
				com.setContent(rs.getString("content"));
				com.setCreateDate(rs.getDate("createddate"));
				com.setCreatedBy(rs.getString("createdby"));
				com.setReplyCommentIds(rs.getString("replycomment_ids"));
				com.setLikeIds(rs.getString("like_uids"));
				comments.add(com);
			}
			stm.close();
			conn.close();
		}
		return comments;
	}

	@Override
	public void removeArticleComment(long comId) throws Exception {
		String sql = "delete from article_comment where id = ?";
		Connection conn = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setLong(1, comId);
		stm.execute();
		stm.close();
		conn.close();
	}

	@Override
	public Article getArticle(int id) throws Exception {
		JCSCacheClient cache = JSCCacheManager.getInstace().getCache("article");
		if(cache.get(ARTICLE + id) != null){
			return (Article)cache.get(ARTICLE + id);
		}else {
			String sql = "select * from article where id=" + id;
			Connection conn = JDBCConnection.getInstance().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			Article art = null;
			while (rs.next()) {
				art = new Article();
				art.setId(rs.getLong("id"));
				art.setSubject(rs.getString("subject"));
				art.setContent(rs.getString("content"));
				art.setImage(rs.getString("image"));
				art.setCreateDate(rs.getDate("createddate"));
				art.setCreatedBy(rs.getString("createdby"));
				art.setCommentIds(rs.getString("comment_ids"));
				art.setLikeIds(rs.getString("like_uids"));
				art.setShareIds(rs.getString("share_uids"));
				art.setType(rs.getInt("type"));
			}
			stm.close();
			conn.close();
			cache.put(ARTICLE + id, art);
			return art;
		}
	}

	@Override
	public List<Article> getBestArticles(TYPE type) throws Exception {
		JCSCacheClient cache = JSCCacheManager.getInstace().getCache("article");
		if(cache.get(BEST_ARTICLE + type.name()) != null){
			return (List<Article>)cache.get(BEST_ARTICLE + type.name());
		}else {
			Connection conn = JDBCConnection.getInstance().getConnection();
			String sql = "select value_ from keyvalue where key_='" + type.name() + "' ";
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			String bestIds = "";
			while (rs.next()) {
				bestIds = rs.getString("value_");
			}
			rs.close();
			if (bestIds != null && bestIds.length() > 1 && bestIds.endsWith(",")) {
				bestIds = bestIds.substring(0, bestIds.length() - 1);
			}
			sql = "select * from article where id in ("+bestIds+")";
			rs = stm.executeQuery(sql);
			List<Article> bestArticles = new  ArrayList<>();
			while(rs.next()){
				Article art = new Article();
				art.setId(rs.getLong("id"));
				art.setSubject(rs.getString("subject"));
				art.setContent(rs.getString("content"));
				art.setImage(rs.getString("image"));
				art.setCreateDate(rs.getDate("createddate"));
				art.setCreatedBy(rs.getString("createdby"));
				art.setCommentIds(rs.getString("comment_ids"));
				art.setLikeIds(rs.getString("like_uids"));
				art.setShareIds(rs.getString("share_uids"));
				art.setType(rs.getInt("type"));
				bestArticles.add(art);
			}
			rs.close();
			stm.close();
			conn.close();
			cache.put(BEST_ARTICLE + type.name(), bestArticles);
			return bestArticles;
		}
		
	}

	@Override
	public Article getLatestArticle(TYPE type) throws Exception {
		JCSCacheClient cache = JSCCacheManager.getInstace().getCache("article");
		if(cache.get(LATEST_ARTICLE + type.name()) != null){
			return (Article)cache.get(LATEST_ARTICLE + type.name());
		}else {
			Connection conn = JDBCConnection.getInstance().getConnection();
			String sql = "select value_ from keyvalue where key_='" + "lastest_"+type.ordinal()+ "' ";
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			String id = "";
			while (rs.next()) {
				id = rs.getString("value_");
			}
			rs.close();
			sql = "select * from article where id=" + id;
			rs = stm.executeQuery(sql);
			Article art = null;
			while(rs.next()){
				art = new Article();
				art.setId(rs.getLong("id"));
				art.setSubject(rs.getString("subject"));
				art.setContent(rs.getString("content"));
				art.setImage(rs.getString("image"));
				art.setCreateDate(rs.getDate("createddate"));
				art.setCreatedBy(rs.getString("createdby"));
				art.setCommentIds(rs.getString("comment_ids"));
				art.setLikeIds(rs.getString("like_uids"));
				art.setShareIds(rs.getString("share_uids"));
				art.setType(rs.getInt("type"));
			}
			rs.close();
			stm.close();
			conn.close();
			cache.put(LATEST_ARTICLE + type.name(), art);
			return art;
		}
	}
	
	public LuckyPerson getLuckyUser(String luckyDate) throws Exception{
		String sql = "select * from luckyuser where lucky_date ='" + luckyDate + "'";
//		System.out.println(sql);
		Connection conn = JDBCConnection.getInstance().getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		LuckyPerson p = null;
		while (rs.next()) {
			p = new LuckyPerson();
			p.setName(rs.getString("name"));
			p.setImage(rs.getString("image"));
			p.setTelco(rs.getString("telco"));
			p.setMobileCode(rs.getString("mobile_code"));
			p.setValue(rs.getInt("value"));
		}
		stm.close();
		conn.close();
		return p;
	}
	public static void main(String[] args) {
		Connection conn = JDBCConnection.getInstance().getConnection();
		try {
			PreparedStatement stm = conn.prepareStatement("select id from news");
			ResultSet rs = stm.executeQuery();
			stm.close();
			rs.close();
			stm = conn.prepareStatement("select id from news");
			rs = stm.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString("id"));
				
			}
			rs.close();
			stm.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
