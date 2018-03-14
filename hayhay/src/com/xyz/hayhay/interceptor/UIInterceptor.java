package com.xyz.hayhay.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xyz.hayhay.db.dummydata.MappingHelper;
import com.xyz.hayhay.entirty.ColorPicker;
import com.xyz.hayhay.entirty.NewsTypes;
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
				String locale = MyUtil.getStringCookie("locale", request);
				modelAndView.getModelMap().put("menuitems", MappingHelper.getMenuitems(locale));
				
				if(request.getRequestURI().endsWith("home.html") || request.getRequestURI().equals("/"))
					modelAndView.getModelMap().put("page","home");
				
				modelAndView.getModelMap().put("colorpicker", ColorPicker.getInstance());
				if (modelAndView.getModelMap().get("cate") != null) {
					String cate = modelAndView.getModelMap().get("cate").toString();
					modelAndView.getModelMap().put("cateInfo", MappingHelper.cateInfo.get(cate));
				} else {
					modelAndView.getModelMap().put("cateInfo", MappingHelper.cateInfo.get(NewsTypes.CATEGORY.HotNews.name()));// default
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
