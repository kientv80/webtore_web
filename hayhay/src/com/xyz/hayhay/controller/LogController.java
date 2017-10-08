package com.xyz.hayhay.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.hayhay.logcentral.TextFileLogger;

@Controller
public class LogController extends BaseController{
	
	
	@ResponseBody
	@RequestMapping(value = "/log", method = { RequestMethod.POST, RequestMethod.GET})
	public void login(String data, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("log>>>>>>>>> " + data);
		if(req.getAttribute("data") != null){
			String mydata = req.getAttribute("data").toString();
			System.out.println(mydata);
		}
		if(req.getParameter("data") != null){
			String mydata = req.getParameter("data").toString();
			System.out.println("param="+mydata);
		}
		TextFileLogger.getInstance().log(data);
		writeSuccessResponse(resp, "ok");
	}
	
}
