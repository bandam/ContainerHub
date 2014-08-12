package com.amirab_soft.containerhub_helpers;

import android.graphics.drawable.Drawable;

public class ContainerItem {
	private int uid;
	private int cid;
	private String title;
	private String location;
	private String departure_date;
	private String arrival_date;
	private String destination;
	private int palletPrice;
	private int cartonPrice;
	private int noPalletsAvailable;
	private int noCartonsAvailable;
	private Drawable icon;
	private int progress;
	private String owner_name;
	private String owner_email;
	private String owner_tell;
	private String owner_current_city;

	public ContainerItem(String title, String description,
			String departure_date, String arrival_date, String destination,
			Drawable icon, int progress, int uid, int cid, String owner_name,
			String owner_email, String owner_current_city, String owner_tell,
			int palletPrice, int cartonPrice, int noPalletsAvailable, int noCartonsAvailable) {
		this.title = title;
		this.location = description;
		this.departure_date = departure_date;
		this.destination = destination;
		this.icon = icon;
		this.progress = progress;
		this.uid = uid;
		this.cid = cid;
		this.owner_name = owner_name;
		this.owner_email = owner_email;
		this.owner_current_city = owner_current_city;
		this.owner_tell = owner_tell;
		this.arrival_date = arrival_date;
		this.palletPrice = palletPrice;
		this.cartonPrice = cartonPrice;
		this.noPalletsAvailable = noPalletsAvailable;
		this.noCartonsAvailable = noCartonsAvailable;
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

	public String getDepartureDate() {
		return departure_date;
	}

	public void setDepartureDate(String date) {
		this.departure_date = date;
	}

	public String getArrivalDate() {
		return arrival_date;
	}

	public void setArrivalDate(String arrival_date) {
		this.arrival_date = arrival_date;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getProgess() {
		return progress;
	}

	public void setProgress(int status) {
		this.progress = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String description) {
		this.location = description;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
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

}
