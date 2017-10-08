package com.xyz.hayhay.entirty.webstore;

import java.io.Serializable;

public class WebApp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7495962948502661976L;
	public WebApp(){}
	public WebApp(int id, String name, String label, String desc, String url, String icon, String cover, int cateId) {
		super();
		this.id = id;
		this.name = name;
		this.label = label;
		this.desc = desc;
		this.url = url;
		this.icon = icon;
		this.cover = cover;
		this.cateId = cateId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public int getCateId() {
		return cateId;
	}
	public void setCateId(int cateId) {
		this.cateId = cateId;
	}
	int id;
	String name;
	String label;
	String desc;
	String url;
	String icon;
	String cover;
	int cateId;
}
