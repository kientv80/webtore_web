package com.xyz.hayhay.db.dummydata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.common.io.Resources;
import com.xyz.hayhay.entirty.CategoryInfo;
import com.xyz.hayhay.entirty.MenuItem;
import com.xyz.hayhay.entirty.NewsTypes;
import com.xyz.hayhay.entirty.NewsTypes.CATEGORY;
import com.xyz.hayhay.entirty.NewsTypes.TYPE;
import com.xyz.hayhay.localization.LocalizedResource;
import com.xyz.webstore.mobile.config.Category;

public class MappingHelper {
	public static Map<String,String> categoryTypeLabelMapping = new HashMap<>();
	public static Map<String,CategoryInfo> cateInfo = new HashMap<>();
	public static Map<String,List<CategoryInfo>> parentCateMapping = new HashMap<>();
	public static Map<String,List<MenuItem>> cateMenuItemMapping = new HashMap<>();
	public static Map<String, List<String>> cateGroup = new TreeMap<>();
	public static List<MenuItem> mainMenuitems = new ArrayList<>();
	public static Map<String, List<String>> parentCates = new TreeMap<>();
	public static Map<String, List<String>> cateVietWorldMapping = new TreeMap<>();
	public static List<Category> categories = new ArrayList<>();
	static {
		for(CATEGORY category: CATEGORY.values()){
			mainMenuitems.add(new MenuItem(category.name(),"/news/" + category.name(), LocalizedResource.getInstance().getValue(category.name(), LocalizedResource.EN_ENGLISH), LocalizedResource.getInstance().getValue(category.name(), LocalizedResource.EN_ENGLISH), ""));
		}
		//Category group
		for(Entry<CATEGORY, List<TYPE>> cate : NewsTypes.cateTypeMapping.entrySet()){
			List<String> types = new ArrayList<>();
			List<CategoryInfo> subCates = new ArrayList<>();
			parentCateMapping.put(cate.getKey().name(), subCates);
			cateGroup.put(cate.getKey().name(),types);
			cateInfo.put(cate.getKey().name(), new CategoryInfo(cate.getKey().name(), "http://globalnewsindex.com/news/" + cate.getKey().name(), "http://globalnewsindex.com/images/icons/360logo.png", LocalizedResource.getInstance().getValue(cate.getKey().name(), null),  LocalizedResource.getInstance().getValue(cate.getKey().name(), null),  LocalizedResource.getInstance().getValue(cate.getKey().name(), null)));
			for(TYPE type : cate.getValue()){
				types.add(type.name());
				subCates.add(new CategoryInfo(LocalizedResource.getInstance().getValue(type.name(), null), "/news/type/"+type.name(), null	, null, null, null) );
				cateInfo.put(type.name(), new CategoryInfo(type.name(), "http://globalnewsindex.com/news/" + type.name(), "http://globalnewsindex.com/images/icons/360logo.png", LocalizedResource.getInstance().getValue(type.name(), null),  LocalizedResource.getInstance().getValue(type.name(), null),  LocalizedResource.getInstance().getValue(type.name(), null)));
			}
		}
	}

	public static List<MenuItem> getMenuItems(String category){
		return cateMenuItemMapping.get(category);
	}
}
