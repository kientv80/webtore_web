package com.xyz.hayhay.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.hayhay.entirty.News;
import com.xyz.hayhay.entirty.NotifyInfo;
import com.xyz.hayhay.service.article.NewsService;
import com.xyz.hayhay.socialplugin.FaceBookFeed;
import com.xyz.hayhay.util.JSONHelper;

@Controller
public class NotifyHandler extends BaseController{
	
	private static final int ONE_HOUR = 60*60*1*1000;
	@ResponseBody
	@RequestMapping(value = "/getupdate", method = RequestMethod.GET)
	public void getUpdate(String userid,String lasttimeupdate, HttpServletResponse resp){
		if(userid == null || userid.isEmpty()){
			//error
		}
		try {
			long fromTime = System.currentTimeMillis()- ONE_HOUR;//one hour before
			if(lasttimeupdate != null && !lasttimeupdate.isEmpty()){
				try {
					fromTime = Long.parseLong(lasttimeupdate);
					if((System.currentTimeMillis() - fromTime) > ONE_HOUR)
						fromTime = System.currentTimeMillis()- ONE_HOUR;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			List<News> news = NewsService.getInstance().getLatestNews(10, fromTime);
			List<NotifyInfo> notifies = new ArrayList<>();
			for(News n  : news){
				NotifyInfo nf = new NotifyInfo(n.getId()+"",n.getType(), 0, n.getTitle(), n.getShotDesc(), n.getImageUrl(), n.getUrl(), n.getStrDate());
				nf.setFrom(n.getFromWebSite());
				notifies.add(nf);
			}
			JSONArray jsonNotify = JSONHelper.toJSONArray(notifies);
			writeJSONResponse(resp, jsonNotify);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		//1474770598000
		//1474770587982
		//1474771787982
		long a = 1474773381510l-20*60*1000;
		System.out.println(a);
	}
}
