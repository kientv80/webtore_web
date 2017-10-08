package com.xyz.webstore.mobile.config;

import java.util.List;

public class Category {
	public static enum TYPE {NEWS,HEALTH,FAMILY,ENTERTAINMENT,GAME,WEB_APP,COMMUNITY,UTIL}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public Category(String name,String label, String url, String icon) {
		this.name = name;
		this.label = label;
		this.url = url;
		this.setIcon(icon);
	}
	String name;
	String label;
	String url;
	String icon;
	
	List<Item> items;
	boolean openLink;
	public boolean getOpenLink() {
		return openLink;
	}
	public void setOpenLink(boolean openLink) {
		this.openLink = openLink;
	}
	
}
