package com.xyz.hayhay.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xyz.hayhay.entirty.News;
import com.xyz.hayhay.service.article.NewsService;
import com.xyz.hayhay.util.ValidationHelper;

@Controller
public class FBRedirectController {
	@Autowired
	NewsService newsService;

	@RequestMapping(value = "/like", method = RequestMethod.GET)
	public String fbLike(String articleId, String targetUrl, HttpServletRequest req, HttpServletResponse resp, ModelMap model) {
		try {
			News news = null;
			if (ValidationHelper.isNumber(articleId)) {
				news = newsService.getNewsById(Long.valueOf(articleId));
				if (news != null) {
					model.put("title", news.getTitle());
					model.put("desc", news.getShotDesc());
					model.put("image", news.getImageUrl());
					model.put("url", news.getUrl());
					model.put("articleId", articleId);
				}
			}
			if (news == null) {
				model.put("url", targetUrl);
			}
			return "fblike";
		} catch (Exception e) {
			e.printStackTrace();
			try {
				resp.sendRedirect("/home.html");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}

	}
}
