package com.xyz.hayhay.location.entirty;

import java.util.List;

public class Place {
	
	public Place(Location location, String icon, String id, String name, List<Photo> photos, String placeId, String reference, String types, String vicinity) {
		super();
		this.location = location;
		this.icon = icon;
		this.id = id;
		this.name = name;
		this.photos = photos;
		this.placeId = placeId;
		this.reference = reference;
		this.types = types;
		this.vicinity = vicinity;
	}
	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the photos
	 */
	public List<Photo> getPhotos() {
		return photos;
	}
	/**
	 * @param photos the photos to set
	 */
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	/**
	 * @return the placeId
	 */
	public String getPlaceId() {
		return placeId;
	}
	/**
	 * @param placeId the placeId to set
	 */
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	/**
	 * @return the types
	 */
	public String getTypes() {
		return types;
	}
	/**
	 * @param types the types to set
	 */
	public void setTypes(String types) {
		this.types = types;
	}
	/**
	 * @return the vicinity
	 */
	public String getVicinity() {
		return vicinity;
	}
	/**
	 * @param vicinity the vicinity to set
	 */
	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}
	
	public String getLink() {
		if(photos != null && photos.size() > 0)
			return photos.get(0).getHtml_attributions();
		
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	Location location;
	List<Photo> photos;
	String icon;
	String id;
	String name;
	String placeId;
	String reference;
	String types;
	String vicinity;
	private String link = "";
}
