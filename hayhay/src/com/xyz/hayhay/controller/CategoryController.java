package com.xyz.hayhay.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xyz.hayhay.db.dummydata.DummyData;
import com.xyz.hayhay.entirty.CategoryInfo;

@Controller
public class CategoryController extends BaseController {
	@RequestMapping(value = "/webstore", method = RequestMethod.GET)
	public String webstore(HttpServletRequest request, HttpServletResponse resp, ModelMap map) {
		map.put("webstore", DummyData.webstores);
		return "webstore";
	}
	@RequestMapping(value = "/category/{catename}", method = RequestMethod.GET)
	public String cateItems(@PathVariable String catename ,HttpServletRequest request, HttpServletResponse resp, ModelMap map) {
		List<CategoryInfo> items = DummyData.categories.get(catename);
		map.put("items", items);
		return "menuitems";
//		try {
//			writeJSONResponse(resp, JSONHelper.toJSONArray(items));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
