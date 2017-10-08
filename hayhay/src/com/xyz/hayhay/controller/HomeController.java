package com.xyz.hayhay.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.hayhay.service.article.HayHayService;
import com.xyz.hayhay.service.article.NewsService;

@Controller
public class HomeController extends BaseController {
	@Autowired
	HayHayService stressService;
	@Autowired
	NewsService newsService;

	@RequestMapping(value = { "/register.html" }, method = RequestMethod.GET)
	public String register(String target, String cate, HttpServletRequest rq, ModelMap model) {
		return "register";
	}

	@RequestMapping(value = "/favicon.ico", method = RequestMethod.GET)
	public String faviconico() {
		return "about";
	}
	@RequestMapping(value = "/react.html", method = RequestMethod.GET)
	public String reactHelloWorld() {
		return "reactindex";
	}
	@ResponseBody
	@RequestMapping(value = "/about.html", method = RequestMethod.GET)
	public String about() {
		return "about";
	}
	
	@ResponseBody
	@RequestMapping(value = "/googleb15db46baf3c868f.html", method = RequestMethod.GET)
	public String verifyGoogle(String articleId, String targetUrl, HttpServletRequest req, HttpServletResponse resp, ModelMap model) {
		return "google-site-verification: googleb15db46baf3c868f.html";
	}
	public static void main(String[] args) {
		int count = 2;
		System.out.println(count % 2);
	}
}
