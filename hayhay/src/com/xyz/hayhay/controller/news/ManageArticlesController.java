package com.xyz.hayhay.controller.news;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xyz.hayhay.controller.BaseController;
import com.xyz.hayhay.entirty.Article;
import com.xyz.hayhay.entirty.Article.TYPE;
import com.xyz.hayhay.service.article.HayHayService;
import com.xyz.hayhay.socialplugin.FaceBookFeed;
import com.xyz.hayhay.socialplugin.SocialServiceFactory;
import com.xyz.hayhay.socialplugin.ZaloLinkFeed;
import com.xyz.hayhay.util.ConfigurationHelper;

@Controller
public class ManageArticlesController  extends BaseController{
	private static final int LIMIT = 100;
	@Autowired
	HayHayService service;

	@RequestMapping(value = "/admin/newarticle.html", method = RequestMethod.POST )
	public String manageArticle(@RequestParam("file") MultipartFile file, @ModelAttribute("article") Article article, ModelMap model,HttpServletRequest req) throws Exception {
		String path = ConfigurationHelper.getInstance().getValue("appDir")+"/webapps/images/";
		if (validateArticle(article)) {
			if (article.getId() == 0) {
				article.setCreateDate(new Date(System.currentTimeMillis()));
				article.setCreatedBy("kientv");
				String fileName = System.nanoTime() + file.getOriginalFilename();
				try {
					byte[] bytes = file.getBytes();
					File serverFile = new File(path  + fileName);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				article.setImage("/images/" + fileName);
				int id = service.newArticle(article);
				if("production".equals(ConfigurationHelper.getInstance().getValue("env"))){
					FaceBookFeed feed = new FaceBookFeed(ConfigurationHelper.getInstance().getValue("domain") + "article.html?id=" + id, article.getSubject(), article.getContent(), ConfigurationHelper.getInstance().getValue("domain")+article.getImage());
					SocialServiceFactory.getFaceBookService().postFeed(feed);
					ZaloLinkFeed zfeed = new ZaloLinkFeed(ConfigurationHelper.getInstance().getValue("domain") + "article.html?id=" + id, article.getSubject(), article.getContent(), ConfigurationHelper.getInstance().getValue("domain")+article.getImage());
					zfeed.setMessage(article.getShortContent2());
				}
				
			} else {//update
				Article old = service.getArticle(article.getId().intValue());
				String fileName = System.nanoTime() + file.getOriginalFilename();
				try {
					if (file != null && !file.isEmpty()) {
						byte[] bytes = file.getBytes();
						File serverFile = new File(path + "images/" + fileName);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						article.setImage("/images/" + fileName);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				old.setSubject(article.getSubject());
				old.setContent(article.getContent());
				if (file != null && !file.isEmpty())
					old.setImage(article.getImage());
				old.setType(article.getType());
				service.updateArticle(old);
			}

		} else {
			//Invalid data
		}
		model.put("articles", service.getArticles(TYPE.HAY.name(), 100,true));
		return "managearticle";
	}

	@RequestMapping(value = "/admin/newarticle.html", method = RequestMethod.GET)
	public String newArticle(ModelMap model) throws Exception {
		Article a = new Article();
		model.put("article", a);
		return "newarticle";
	}

	@RequestMapping(value = "/admin/editarticle.html", method = RequestMethod.GET)
	public String editArticle(Integer id, ModelMap model) throws Exception {
		Article atr = service.getArticle(id);
		model.put("article", atr);
		return "newarticle";
	}
	@RequestMapping(value = "/admin/removearticle.html", method = RequestMethod.GET)
	public String removeArticle(Long id, ModelMap model) throws Exception {
		service.removeArticle(id);
		model.put("articles", service.getArticles(TYPE.HAY.name(),LIMIT,true));
		return "managearticle";
	}
	
	@RequestMapping(value = "/admin/managearticle.html", method = RequestMethod.GET)
	public String managearticle(Integer type, ModelMap model) throws Exception {
		if (type == null)
			type = 0;
		List<Article> articles = service.getArticles(TYPE.values()[type].name(),100,false);
		for(Article ar : articles){
			if(ar.getContent().length() > 200){
				ar.setContent(ar.getContent().substring(0,200));
			}
		}
		model.put("articles", articles);
		return "managearticle";
	}

	private boolean validateArticle(Article article) {
		if (article == null || article.getSubject() == null || article.getSubject().isEmpty() || article.getContent() == null || article.getContent().isEmpty()) {
			return false;
		}
		return true;
	}

}
