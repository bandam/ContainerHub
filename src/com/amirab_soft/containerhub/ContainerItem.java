package com.amirab_soft.containerhub;

public class ContainerItem {
	private String title;
	private String location;
	private int icon;
	private int progress;
	
	
	public ContainerItem(String title, String description, int icon, int status){
		this.title = title;
		this.location = description;
		this.icon = icon;
		this.progress = status;
	}

	public String getTitle() {
		return title;
	}

	public int getProgess() {
		return progress;
	}

	public void setProgress(int status) {
		this.progress = status;
	}

	public void setTitle(String title) {
		this.title = title;
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
