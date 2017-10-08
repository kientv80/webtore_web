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

import com.xyz.hayhay.entirty.MenuItem;
import com.xyz.hayhay.entirty.NewsTypes;
import com.xyz.hayhay.service.article.NewsService;
import com.xyz.hayhay.util.TargetingUtil;

public class BaseController {
	@Autowired
	protected NewsService newsService;
	
	public static final List<MenuItem> vnnews = new ArrayList<>();
	static {
		vnnews.add(new MenuItem(NewsTypes.TINTUC,"/news/" + NewsTypes.TINTUC, "Tuyển tập các thông tin trong nước mới nhất", "Tin tức", "/images/icons/newspaper.png"));
		vnnews.add(new MenuItem(NewsTypes.KINHTE,"/news/" + NewsTypes.KINHTE, "Tuyển tập các thông tin kinh tế trong nước mới nhất", "Kinh tế", "/images/icons/newspaper.png"));
		vnnews.add(new MenuItem(NewsTypes.CONGNGHE,"/news/" + NewsTypes.CONGNGHE, "Tuyển tập các thông tin kinh tế trong nước mới nhất", "Công Nghệ", "/images/icons/newspaper.png"));
		vnnews.add(new MenuItem(NewsTypes.THETHAO,"/news/" + NewsTypes.THETHAO, "Tuyển tập thông tin thể thao mới nhất", "Thể Thao", "/images/icons/sport.png"));
		vnnews.add(new MenuItem(NewsTypes.SUCKHOE,"/news/" + NewsTypes.SUCKHOE, "Tuyển tập các thông tin về sức khỏe, gia đình mới nhất", "Sức khỏe", "/images/icons/newspaper.png"));
		vnnews.add(new MenuItem(NewsTypes.NGOISAO,"/news/" + NewsTypes.NGOISAO, "Tuyển tập các thông tin về người nổi tiếng, ngôi sao", "Ngôi Sao", "/images/icons/star.png"));
		vnnews.add(new MenuItem(NewsTypes.TINHYEU,"/news/" + NewsTypes.TINHYEU, "Tình yêu - Gia đình", "Tinh Yêu", "/images/icons/love.png"));
		vnnews.add(new MenuItem(NewsTypes.NHAC,"/news/" + NewsTypes.NHAC, "Tập hợp các bài hát hay", "Nhạc", "/images/icons/guitar.png"));
		vnnews.add(new MenuItem(NewsTypes.XE,"/news/" + NewsTypes.XE, "Xe", "Xe", "/images/icons/newspaper.png"));
		vnnews.add(new MenuItem(NewsTypes.VIECLAM,"/" + NewsTypes.VIECLAM, "Việc làm", "Việc làm", "/images/icons/newspaper.png"));
		vnnews.add(new MenuItem(NewsTypes.BATDONGSAN,"/news/" + NewsTypes.BATDONGSAN, "Nhà Đất", "Nhà Đất", "/images/icons/newspaper.png"));
		vnnews.add(new MenuItem(NewsTypes.KYNANGMEN,"/news/" + NewsTypes.KYNANGMEN, "Kỹ năng", "Kỹ năng", "/images/icons/newspaper.png"));
		vnnews.add(new MenuItem(NewsTypes.TVPROGRAM,"/tienich/" + NewsTypes.TVPROGRAM, "360hay TV, tập hợp lịch phát sóng của các đài truyền hình VN như VTV, VTVC, HTV, Truyền hình Hà Nội vv", "TV", "/images/icons/tv.png"));
	}
	public static final List<MenuItem> worldnews = new ArrayList<>();
	static {
		worldnews.add(new MenuItem(NewsTypes.WN_HOME,"/home.html", "Home", "Home", "/images/icons/home_menu_icon.png"));
		worldnews.add(new MenuItem(NewsTypes.WN_HOME,"/news/" + NewsTypes.WN_HOME, "Hot news", "Hot news", "/images/icons/home_menu_icon.png"));
		worldnews.add(new MenuItem(NewsTypes.WN_TECH,"/news/" + NewsTypes.WN_TECH, "Tech", "Tech", "/images/icons/khcn.png"));
		worldnews.add(new MenuItem(NewsTypes.WN_SIENCE,"/news/" + NewsTypes.WN_SIENCE, "Sience", "Sience", "/images/icons/science.png"));
		worldnews.add(new MenuItem(NewsTypes.WN_BIZ,"/news/" + NewsTypes.WN_BIZ, "Business", "Business", "/images/icons/kt.png"));
	}
	
	enum WEBSITE_TYPE {VNNEWS,WORLDNEWS}
	public boolean isMobile(HttpServletRequest req){
		return TargetingUtil.isMobilePhone(req);
	}
	protected List<MenuItem> getListMenuItems (WEBSITE_TYPE type){
		if(type == WEBSITE_TYPE.VNNEWS)
			return vnnews;
		else
			return null;
	}
	/*protected void setHighlineNews(NewsService newsService, HttpServletRequest rq, ModelMap model) throws SQLException {
		List<News> hotNews = newsService.getHotNews(null);
		List<News> hotNewsFilter = new ArrayList<News>();
		Cookie screenheight = MyUtil.getCookie("screenheight", rq);
		int hotNewsNumber = 10;
		if(screenheight != null){
			screenheight.getValue();
			try{
				hotNewsNumber = (Integer.valueOf(screenheight.getValue())/90)-2;
			}catch(Exception ex){
			}
		}
		Cookie c = MyUtil.getCookie("newstype", rq);
		String cate = rq.getParameter("cate");

		if(c != null && c.getValue().endsWith("worldnews")){
			if(cate == null || cate.isEmpty())
				cate = NewsTypes.WN_HOME;
			for (News n : hotNews) {
				if (model.get("hotnews") == null && cate.equals(n.getType())) {
					model.put("hotnews", n);
				} else if(n.getType().equals(NewsTypes.WN_BIZ) || n.getType().equals( NewsTypes.WN_HOME) || n.getType().equals( NewsTypes.WN_SIENCE )|| n.getType().equals(NewsTypes.WN_TECH)){
					if(n.getTitle().length() > 76)
						n.setTitle(n.getTitle().substring(0,65) + " ...");
					
					hotNewsFilter.add(n);
				}
			}
		}else{
			if(cate == null || cate.isEmpty())
				cate = NewsTypes.TYPE_TINTRONGNUOC;
			for (News n : hotNews) {
				if (model.get("hotnews") == null && cate.equals(n.getType())) {
					model.put("hotnews", n);
				} else if(!n.getType().equals( NewsTypes.HUONGTHIEN) && !n.getType().equals( NewsTypes.SOFTSKILLS)  && !n.getType().equals( NewsTypes.WN_BIZ) && !n.getType().equals(NewsTypes.WN_HOME) && !n.getType().equals( NewsTypes.WN_SIENCE) && !n.getType().equals( NewsTypes.WN_SIENCE)){
					if(n.getTitle().length() > 76)
						n.setTitle(n.getTitle().substring(0,65) + " ...");
					hotNewsFilter.add(n);
				}
			}
		}
		if(hotNewsFilter.size() > hotNewsNumber){
			hotNewsFilter = hotNewsFilter.subList(0, hotNewsNumber);
		}
		model.put("highlineNews", hotNewsFilter);
	}*/
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

}
