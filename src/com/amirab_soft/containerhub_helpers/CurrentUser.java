package com.amirab_soft.containerhub_helpers;

public class CurrentUser {

	// Singleton instance of class to return to all callers
	private static CurrentUser currentUser = null;

	// User variables to track
	private String username;
	private String name;
	private String email;
	private String Phone;
	private String currentCity;
	private String uid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	// Dummy constructor to prevent instantiation
	public CurrentUser() {
	}

	public static CurrentUser getInstance() {
		if (currentUser == null) {
			currentUser = new CurrentUser();
		}

		return currentUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Setters and getters for the variables
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
}
