package com.xyz.hayhay.ads;


public class AdsItem {
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the shotDesc
	 */
	public String getShotDesc() {
		return shotDesc;
	}
	/**
	 * @param shotDesc the shotDesc to set
	 */
	public void setShotDesc(String shotDesc) {
		this.shotDesc = shotDesc;
	}
	/**
	 * @return the targetUrl
	 */
	public String getTargetUrl() {
		return targetUrl;
	}
	/**
	 * @param targetUrl the targetUrl to set
	 */
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	
	public AdsItem(String image, String title, String shotDesc,String targetUrl) {
		this.image = image;
		this.title = title;
		this.shotDesc = shotDesc;
		this.targetUrl = targetUrl;
	}
	String image;
	String title;
	String shotDesc;
	String targetUrl;
}
