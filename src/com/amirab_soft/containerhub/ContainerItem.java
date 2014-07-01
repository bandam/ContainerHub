package com.amirab_soft.containerhub;

public class ContainerItem {
	private String title;
	private String location;
	private String departure_date;
	private String arrival_date;
	private String destination;
	private int icon;
	private int progress;
	
	
	public ContainerItem(String title, String description, String departure_date, String arrival_date,
			String destination,int icon, int progress){
		this.title = title;
		this.location = description;
		this.departure_date = departure_date;
		this.destination = destination;
		this.icon = icon;
		this.progress = progress;
		
	}

	public String getDepartureDate() {
		return departure_date;
	}

	public void setDepartureDate(String date) {
		this.departure_date = date;
	}
	
	public String getArrivalDate(){
		return arrival_date;
	}
	
	public void setArrivalDate(String arrival_date){
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

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}
}
