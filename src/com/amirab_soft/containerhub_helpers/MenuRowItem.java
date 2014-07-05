package com.amirab_soft.containerhub_helpers;

public class MenuRowItem {
	private String title;
	private int icon;
	
	public MenuRowItem(String title, int icon){
		this.title = title;
		this.icon = icon;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public int getIcon(){
		return icon;
	}
	
	public void setIcon(int icon){
		this.icon = icon;
	}
}
