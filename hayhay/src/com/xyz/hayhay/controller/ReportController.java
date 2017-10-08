package com.xyz.hayhay.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xyz.hayhay.entirty.Report;
import com.xyz.hayhay.service.report.ReportServiceImpl;

@Controller
public class ReportController  extends BaseController{
	Logger log = Logger.getLogger(ReportController.class);
	
	@RequestMapping(value = "/admin/report.html", method = {RequestMethod.POST,RequestMethod.GET})
	public String cateReport(String time, HttpServletRequest req, HttpServletResponse rep, ModelMap map) throws Exception{
		System.out.println("time=" + time);
		if(time == null || time.isEmpty())
			time = "today";
		List<Report> reports = ReportServiceImpl.getInstance().getCategoryReport(time);
		map.put("reports", reports);
		map.put("time", time);
		return "report";
	}
	
}
