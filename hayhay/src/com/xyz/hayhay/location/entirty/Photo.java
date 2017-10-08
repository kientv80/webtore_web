package com.xyz.hayhay.location.entirty;

public class Photo {
	
	public Photo(int height, int width, String html_attributions, String photo_reference) {
		super();
		this.height = height;
		this.width = width;
		this.html_attributions = html_attributions;
		this.photo_reference = photo_reference;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the html_attributions
	 */
	public String getHtml_attributions() {
		return html_attributions;
	}
	/**
	 * @param html_attributions the html_attributions to set
	 */
	public void setHtml_attributions(String html_attributions) {
		this.html_attributions = html_attributions;
	}
	/**
	 * @return the photo_reference
	 */
	public String getPhoto_reference() {
		return photo_reference;
	}
	/**
	 * @param photo_reference the photo_reference to set
	 */
	public void setPhoto_reference(String photo_reference) {
		this.photo_reference = photo_reference;
	}
	int height;
	int width;
	String html_attributions;
	String photo_reference;
}
