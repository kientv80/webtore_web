package com.xyz.hayhay.service.user;

public class Profile {
	String id;
	String name;
	String firstName;
	String lastName;
	String avatar;
	String token;
	String permissions;
	String declinedPermissions;
	public Profile() {
		// TODO Auto-generated constructor stub
	}
	public Profile(String id, String name, String firstName, String lastName,String avatar, String token, String permissions, String declinedPermissions) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.token = token;
		this.permissions = permissions;
		this.declinedPermissions = declinedPermissions;
		this.name = name;
		this.avatar = avatar;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	public String getDeclinedPermissions() {
		return declinedPermissions;
	}
	public void setDeclinedPermissions(String declinedPermissions) {
		this.declinedPermissions = declinedPermissions;
	}

}
