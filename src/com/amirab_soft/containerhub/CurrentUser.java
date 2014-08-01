package com.amirab_soft.containerhub;

public class CurrentUser {

	// Singleton instance of class to return to all callers
	private static CurrentUser currentUser = null;

	// User variables to track
	private String username;
	private String email;
	private String Phone;
	private String currentCity;
	private int uid;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
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
