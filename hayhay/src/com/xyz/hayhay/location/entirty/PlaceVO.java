package com.xyz.hayhay.location.entirty;

public class PlaceVO {
	
	public PlaceVO(String name, String address, String icon, String id, String googleLink, String placeId, Location location, String reference) {
		super();
		this.name = name;
		this.address = address;
		this.icon = icon;
		this.id = id;
		this.googleLink = googleLink;
		this.placeId = placeId;
		this.location = location;
		this.reference = reference;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the googleLink
	 */
	public String getGoogleLink() {
		return googleLink;
	}
	/**
	 * @param googleLink the googleLink to set
	 */
	public void setGoogleLink(String googleLink) {
		this.googleLink = googleLink;
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
	String name;
	String address;
	String icon;
	String id;
	String googleLink;
	String placeId;
	Location location;
	String reference;
}
