package com.amirab_soft.containerhub;

public class ContainerItem {
	private String title;
	private String location;
	private String date;
	private String to;
	private int icon;
	private int progress;
	
	
	public ContainerItem(String title, String description, String date, String to,int icon, int progress){
		this.title = title;
		this.location = description;
		this.date = date;
		this.to = to;
		this.icon = icon;
		this.progress = progress;
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
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
