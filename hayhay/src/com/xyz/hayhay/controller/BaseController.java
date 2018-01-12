package com.xyz.hayhay.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.xyz.hayhay.db.dummydata.MappingHelper;
import com.xyz.hayhay.entirty.MenuItem;
import com.xyz.hayhay.entirty.NewsTypes;
import com.xyz.hayhay.service.article.NewsService;
import com.xyz.hayhay.util.TargetingUtil;

public class BaseController {
	@Autowired
	protected NewsService newsService;
	
	
	
	enum WEBSITE_TYPE {VNNEWS,WORLDNEWS}
	public boolean isMobile(HttpServletRequest req){
		return TargetingUtil.isMobilePhone(req);
	}
	public List<MenuItem> getListMenuItems (WEBSITE_TYPE type){
		return MappingHelper.mainMenuitems;
	}
	
	enum RESULT{SUCCESS,FAILED};
	public void writeSuccessResponse(HttpServletResponse resp, String message){
		try {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			JSONObject jobj = new JSONObject();
			jobj.put("errorCode", RESULT.SUCCESS.ordinal());
			jobj.put("msg", message);
			out.write(jobj.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void writeFailedResponse(HttpServletResponse resp, String message){
		try {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			JSONObject jobj = new JSONObject();
			jobj.put("errorCode", RESULT.FAILED.ordinal());
			jobj.put("msg", message);
			out.write(jobj.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void writeJSONResponsed(HttpServletResponse resp, JSONObject json){
		try {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.write(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void writeJSONResponse(HttpServletResponse resp, JSONArray json){
		try {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.write(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void writeJSONArrayResponse(HttpServletResponse resp,  org.json.simple.JSONArray json){
		try {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.write(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void writeSimpleJSONObjectResponse(HttpServletResponse resp,  JSONObject json){
		try {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.write(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected String getCookie(String key, HttpServletRequest rq) {
		String value = null;
		if(rq.getCookies() != null && rq.getCookies().length > 0){
			for(Cookie c : rq.getCookies()){
				if(key.equals(c.getName())){
					value = c.getValue();
					break;
				}
			}
		}
		return value;
	}
	public String getUid(String uid, HttpServletRequest rq){
		if(uid == null || uid.isEmpty()){
			uid = getCookie("uid", rq);
			if(uid == null || uid.isEmpty()){
				uid = "-1";
			}
		}
		return uid;
	}
}
