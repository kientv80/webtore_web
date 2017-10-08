package com.xyz.hayhay.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.xyz.hayhay.entirty.Article;
import com.xyz.hayhay.entirty.Comment;
import com.xyz.hayhay.service.article.HayHayService;
import com.xyz.hayhay.service.article.HayHayServiceImpl;

public class DBSchema {
	public static void main(String[] args) throws Exception, IllegalAccessException, ClassNotFoundException {
		
//		new DBSchema().createSchemas();
		Article st = new Article(0,"Hay Hay","hello he la","/images/53518010916628hairstyle13.jpg",new Date(System.currentTimeMillis()),"kienjudo");
		st.setType(Article.TYPE.HAY.ordinal());
		HayHayService sv = new HayHayServiceImpl();
		int id = sv.newArticle(st);
		sv.postCommentOnArticle(id, new Comment(0, "my comment", new Date(System.currentTimeMillis()), "kienjudo"));
		sv.postCommentOnArticle(id, new Comment(0, "my comment 1", new Date(System.currentTimeMillis()), "kienjudo"));
		sv.postCommentOnArticle(id, new Comment(0, "my comment 2", new Date(System.currentTimeMillis()), "kienjudo"));
		sv.postCommentOnArticle(id, new Comment(0, "my comment 3", new Date(System.currentTimeMillis()), "kienjudo"));
		sv.likeArticle(id, id+"");
		sv.likeArticle(id, id+"");


		st.setType(Article.TYPE.CUOI.ordinal());
		st.setSubject("Cuoi cuoi");
		st.setImage("/images/399513122424943laugh.jpg");
		id = sv.newArticle(st);
		sv.postCommentOnArticle(id, new Comment(0, "my comment", new Date(System.currentTimeMillis()), "kienjudo"));
		sv.postCommentOnArticle(id, new Comment(0, "my comment 1", new Date(System.currentTimeMillis()), "kienjudo"));
		sv.likeArticle(id, id+"");
		sv.likeArticle(id, id+"");
		sv.likeArticle(id, id+"");
		
		st.setType(Article.TYPE.DOCDAO.ordinal());
		st.setSubject("Doc dao");
		st.setImage("/images/thiennhien.jpg");
		id = sv.newArticle(st);
		sv.postCommentOnArticle(id, new Comment(0, "my comment11", new Date(System.currentTimeMillis()), "kienjudo"));
		sv.postCommentOnArticle(id, new Comment(0, "my comment 12", new Date(System.currentTimeMillis()), "kienjudo"));
		sv.likeArticle(id, id+"");
		
		st.setType(Article.TYPE.HAY.ordinal());
		st.setSubject("hay hay 2");
		st.setImage("/images/relax.jpg");
		id = sv.newArticle(st);
		sv.postCommentOnArticle(id, new Comment(0, "my comment13", new Date(System.currentTimeMillis()), "kienjudo"));
		sv.postCommentOnArticle(id, new Comment(0, "my comment 14", new Date(System.currentTimeMillis()), "kienjudo"));
		sv.likeArticle(id, id+"");
		sv.likeArticle(id, id+"");
		sv.likeArticle(id, id+"");
		sv.likeArticle(id, id+"");
		
		st.setType(Article.TYPE.HAY.ordinal());
		st.setSubject("hay hay 3");
		st.setImage("/images/featured.jpg");
		id = sv.newArticle(st);
		sv.postCommentOnArticle(id, new Comment(0, "my comment5", new Date(System.currentTimeMillis()), "kienjudo"));
		sv.likeArticle(id, id+"");
		sv.likeArticle(id, id+"");
		sv.likeArticle(id, id+"");
		sv.likeArticle(id, id+"");
		sv.likeArticle(id, id+"");
		sv.likeArticle(id, id+"");
//		HayHayService sv = new HayHayServiceImpl();
		for(Article s : sv.getArticles(Article.TYPE.HAY.name(),100,false)){
			System.out.print(s.getId());
			System.out.print(s.getSubject());
			System.out.print(s.getCommentIds());
			System.out.print(sv.getArticleComments(s.getCommentIds()).size());
			System.out.println("");
		}
			
	}
	public Map<String,String> getSchemas(){
		Map<String,String> schemas = new HashMap<>();
		schemas.put("article","CREATE TABLE article ("+
		  " id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT STRESS_PK PRIMARY KEY,"+
		  " subject VARCHAR(200),"+
		  " content VARCHAR(5000),"+
		  " image VARCHAR(1000),"+
		  " createddate TIMESTAMP,"+
		  " createdby VARCHAR(5000),"+
		  " comment_ids VARCHAR(5000) default '',"+
		  " like_uids VARCHAR(5000) default '',"+
		  " share_uids VARCHAR(5000)  default '',"+
		  " type INTEGER NOT NULL"+
		  " )");
		
		schemas.put("article_comment","CREATE TABLE article_comment ("+
		  " id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT COMMENT_PK PRIMARY KEY,"+
		  " article_id INTEGER ," +
		  " content VARCHAR(5000),"+
		  " createddate TIMESTAMP,"+
		  " createdby VARCHAR(5000),"+
		  " replycomment_ids VARCHAR(5000) default '',"+
		  " like_uids VARCHAR(5000) default ''"+
		  " )");
		schemas.put("keyvalue","CREATE TABLE keyvalue ("+
				  " key_ VARCHAR(500) NOT NULL PRIMARY KEY,"+
				  " value_ VARCHAR(10000)"+
				  " )");
		return schemas;
	}
	public void createSchemas() throws Exception, IllegalAccessException, ClassNotFoundException{
		Connection conn = JDBCConnection.getInstance().getConnection();
		Statement stm = conn.createStatement();
		for(Entry<String,String> table : getSchemas().entrySet() ){
			try {
				stm.execute("drop table " + table.getKey());
			} catch (Exception e) {
				e.printStackTrace();
			}
			stm.execute(table.getValue());
		}
		stm.close();
		conn.close();
	}
}
