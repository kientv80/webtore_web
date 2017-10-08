package com.xyz.hayhay.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xyz.hayhay.controller.BaseController;
import com.xyz.hayhay.db.dummydata.MappingHelper;
import com.xyz.hayhay.entirty.ColorPicker;
import com.xyz.hayhay.util.MyUtil;
import com.xyz.hayhay.util.TargetingUtil;

public class UIInterceptor extends HandlerInterceptorAdapter {
	Logger log = Logger.getLogger(UIInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		// used in header.jsp to set active menu
		try {
			if (modelAndView != null && modelAndView.getModelMap() != null) {
				modelAndView.getModelMap().put("device", request.getSession().getAttribute("device"));
				modelAndView.getModelMap().put("tmps", System.nanoTime());
				if (request.getRequestURL().indexOf("localhost") > 0) {
					modelAndView.getModelMap().put("embededGA", false);
				} else {
					modelAndView.getModelMap().put("embededGA", true);
				}
				Cookie fromAndroidApp = MyUtil.getCookie("fromAndroidApp", request);
				if(fromAndroidApp == null || !"true".equals(fromAndroidApp.getValue())){
					Cookie c = MyUtil.getCookie("newstype", request);
					if (c != null && c.getValue().endsWith("worldnews")) {
						modelAndView.getModelMap().put("menuitems", BaseController.worldnews);
					} else {
						String uri = request.getRequestURI();
						String category = "";
						if (uri.indexOf("/", 1) > 0) {
							category = uri.substring(1, uri.indexOf("/", 1));
						} else {
							if (uri.indexOf("?", 1) > 0) {
								category = uri.substring(1, uri.indexOf("?", 1));
							} else {
								category = uri.substring(1);
							}
						}
						if (category == null || category.isEmpty() || category.indexOf("home") >= 0)
							category = "news";
						modelAndView.getModelMap().put("menuitems", MappingHelper.mainMenuitems);
						modelAndView.getModelMap().put("othermenuitems", MappingHelper.otherMenuitems);
					}
				}
				
				if(request.getRequestURI().endsWith("home.html") || request.getRequestURI().equals("/"))
					modelAndView.getModelMap().put("page","home");
				
				modelAndView.getModelMap().put("colorpicker", ColorPicker.getInstance());
				if (modelAndView.getModelMap().get("cate") != null) {
					String cate = modelAndView.getModelMap().get("cate").toString();
					modelAndView.getModelMap().put("cateInfo", MappingHelper.getCateInfo(cate));
				} else {
					modelAndView.getModelMap().put("cateInfo", MappingHelper.getCateInfo("default"));// default
				}
			}
		} catch (Exception e) {
			log.error(request.getQueryString(), e);
			e.printStackTrace();
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (request.getSession().getAttribute("device") == null) {
			if (TargetingUtil.isMobilePhone(request)) {
				request.getSession().setAttribute("device", "mobile");
			} else {
				request.getSession().setAttribute("device", "notmobile");
			}
		}
		return super.preHandle(request, response, handler);
	}
}
