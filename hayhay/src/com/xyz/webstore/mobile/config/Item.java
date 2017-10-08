package com.xyz.webstore.mobile.config;

public class Item {
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
	String name;
	String label;
	String url;
	String icon;
	public Item(String name, String label, String url) {
		this.name = name;
		this.label = label;
		this.url = url;
	}
}
