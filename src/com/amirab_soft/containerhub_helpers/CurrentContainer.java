package com.amirab_soft.containerhub_helpers;

import android.graphics.drawable.Drawable;

public class CurrentContainer {

	// Class Variable
	private int uid;
	private int cid;
	private String title;
	private String location;
	private String departure_date;
	private String arrival_date;
	private String destination;
	private Drawable icon;
	private int progress;
	private int palletPrice;
	private int cartonPrice;
	private int noPalletsAvailable;
	private int noCartonsAvailable;
	private String owner_name;
	private String owner_email;
	private String owner_tell;
	private String owner_current_city;

	// Singleton Container instance to return
	private static CurrentContainer instance = null;

	// Constructor to defeat Instantiation
	protected CurrentContainer() {
	}

	public static CurrentContainer getInstance() {
		if (instance == null) {
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

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public String getOwner_email() {
		return owner_email;
	}

	public void setOwner_email(String owner_email) {
		this.owner_email = owner_email;
	}

	public String getOwner_tell() {
		return owner_tell;
	}

	public void setOwner_tell(String owner_tell) {
		this.owner_tell = owner_tell;
	}

	public String getOwner_current_city() {
		return owner_current_city;
	}

	public void setOwner_current_city(String owner_current_city) {
		this.owner_current_city = owner_current_city;
	}

	public int getPalletPrice() {
		return palletPrice;
	}

	public void setPalletPrice(int palletPrice) {
		this.palletPrice = palletPrice;
	}

	public int getCartonPrice() {
		return cartonPrice;
	}

	public void setCartonPrice(int cartonPrice) {
		this.cartonPrice = cartonPrice;
	}

	public int getNoPalletsAvailable() {
		return noPalletsAvailable;
	}

	public void setNoPalletsAvailable(int noPalletsAvailable) {
		this.noPalletsAvailable = noPalletsAvailable;
	}

	public int getNoCartonsAvailable() {
		return noCartonsAvailable;
	}

	public void setNoCartonsAvailable(int noCartonsAvailable) {
		this.noCartonsAvailable = noCartonsAvailable;
	}
	
	
}
