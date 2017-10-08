package com.xyz.hayhay.service.article;

import java.util.List;

import com.xyz.hayhay.entirty.Article;
import com.xyz.hayhay.entirty.Comment;
import com.xyz.hayhay.service.user.Person;

public interface HayHayService {
	public int newArticle(Article article) throws Exception;
	public int updateArticle(Article article) throws Exception;
	public void removeArticle(Long id) throws Exception;
	public void postCommentOnArticle(long articleId, Comment comment) throws Exception;
	public void likeArticle(long articleId, String userId) throws Exception;
	public void shareArticle(long articleId, String userId) throws Exception;
	public void publishArticle(long articleId, boolean approve) throws Exception;
	public List<Article> getArticles(String type, int limit, boolean getFromCache) throws Exception;
	public Article getArticle(int id) throws Exception;
	public List<Comment> getArticleComments(String commentIds) throws Exception;
	public void removeArticleComment(long comId) throws Exception;
	public List<Article> getBestArticles(Article.TYPE type) throws Exception;
	public Article getLatestArticle(Article.TYPE type) throws Exception;
	public Person getLuckyUser(String luckyDate) throws Exception;
}
