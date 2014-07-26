package com.amirab_soft.containerhub;

import android.graphics.drawable.Drawable;

public class CurrentContainer {
	
	//Class Variable
	private String title;
	private String location;
	private String departure_date;
	private String arrival_date;
	private String destination;
	private Drawable icon;
	private int progress;
	
	// Singleton Container instance to return
	private static CurrentContainer instance = null;
	
	
	// Constructor to defeat Instantiation
	protected  CurrentContainer(){}
	
	public static CurrentContainer getInstance(){
		if(instance ==  null){
			instance = new CurrentContainer();
		}
		
		return instance;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(String departure_date) {
		this.departure_date = departure_date;
	}

	public String getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	
	
}
