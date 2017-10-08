package com.xyz.hayhay.controller.news;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xyz.hayhay.controller.BaseController;

@Controller
public class JobController extends BaseController{
	@RequestMapping(value = "/job/vieclam", method = RequestMethod.GET)
	public String vieclam( ModelMap model) {
		model.put("cate", "vieclam");
		return "vieclam";
	}
}
