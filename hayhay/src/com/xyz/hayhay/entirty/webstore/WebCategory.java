package com.xyz.hayhay.entirty.webstore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WebCategory  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4264505085116122561L;
	public WebCategory(int id, String name, String label) {
		this.id = id;
		this.name = name;
		this.label = label;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<WebApp> getWebapps() {
		return webapps;
	}
	public void setWebapps(List<WebApp> webapps) {
		this.webapps = webapps;
	}
	int id;
	String name;
	String label;
	String image;
	private List<WebApp> webapps = new ArrayList<>();
}
