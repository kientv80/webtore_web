package com.xyz.hayhay.entirty;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.xyz.hayhay.db.dummydata.MappingHelper;

public class News {
	
	
	
	
	
	
	public static enum NEWS_ORDER {H,HI,M};//hotnews,highlight news, mid news
	
	
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
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the fromWebSite
	 */
	public String getFromWebSite() {
		return fromWebSite;
	}
	/**
	 * @param fromWebSite the fromWebSite to set
	 */
	public void setFromWebSite(String fromWebSite) {
		this.fromWebSite = fromWebSite;
	}
	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}
	public static final SimpleDateFormat df = new SimpleDateFormat("dd/MM/YY HH:mm:ss");
	
	private String strDate = "";
	public String getStrDate(){
		if(getDate() != null)
			strDate =  News.df.format(getDate());
		return strDate;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
		getStrDate();
	}
	String title;
	String shotDesc = "";
	String imageUrl;
	String url;
	String type;
	String fromWebSite;
	Timestamp date;
	int id;
	boolean isHotNews;
	String newsOrder="M";
	boolean is360hayArticle;
	private String uniqueName="";
	private String parentCateName;
	/**
	 * @return the is360hayArticle
	 */
	public boolean isIs360hayArticle() {
		return is360hayArticle;
	}
	/**
	 * @param is360hayArticle the is360hayArticle to set
	 */
	public void setIs360hayArticle(boolean is360hayArticle) {
		this.is360hayArticle = is360hayArticle;
	}
	/**
	 * @return the newsOrder
	 */
	public String getNewsOrder() {
		return newsOrder;
	}
	/**
	 * @param newsOrder the newsOrder to set
	 */
	public void setNewsOrder(String newsOrder) {
		this.newsOrder = newsOrder;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the isHotNews
	 */
	public boolean isHotNews() {
		return isHotNews;
	}
	/**
	 * @param isHotNews the isHotNews to set
	 */
	public void setHotNews(boolean isHotNews) {
		this.isHotNews = isHotNews;
	}
//	@Override
//	public boolean equals(Object obj) {
//		if (obj instanceof News) {
//			News tm = (News) obj;
//			if(tm != null && tm.getUrl() != null && tm.getUrl().equals(this.getUrl()))
//				return true;
//			if(tm != null && tm.getTitle() != null && tm.getUniqueName().equals(this.getUniqueName()))
//					return true;
//		}
//		return false;
//	}
	
	public String getCateName() {
		if(MappingHelper.categoryTypeLabelMapping.get(getType()) != null)
			return MappingHelper.categoryTypeLabelMapping.get(getType());
		else
			return "";
	}
	
	public String getUniqueName() {
		if(uniqueName.isEmpty() && title != null && !title.isEmpty()){
			for(String c: title.split(" ")){
				if(c.length() > 0)
					uniqueName = uniqueName + c.charAt(0);
			}
		}
		return uniqueName;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public String getParentCateName() {
		return parentCateName;
	}
	public void setParentCateName(String parentCateName) {
		this.parentCateName = parentCateName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof News)) {
			return false;
		}
		News other = (News) obj;
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if ( !isSiminler(url, other.url, 0.7)) {
			return false;
		}
		return true;
	}
	public static boolean isSiminler(String str1, String str2, double percent){
		String[] str1List = str1.toLowerCase().split(" ");
		str2 = str2.toLowerCase();
		double similarWorldCount = 0;
		for(int i = 0;i<str1List.length;i++){
			if(str2.contains(str1List[i])){
				similarWorldCount ++;
			}
		}
		if(similarWorldCount >  0 && ((similarWorldCount/str1List.length) > percent)){
			return true;
		}
		return false;
	}
	public static void main(String args[]){
		
	}
}
