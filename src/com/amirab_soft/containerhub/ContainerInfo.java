package com.amirab_soft.containerhub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class ContainerInfo extends Fragment{
	 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View containerDetails = inflater.inflate(R.layout.container_info_fragment,container, false);
		
		TextView location = (TextView)containerDetails.findViewById(R.id.containerDetails_containerLocation);
		TextView destination = (TextView)containerDetails.findViewById(R.id.containerDetails_toDestination);
		TextView departureDate = (TextView)containerDetails.findViewById(R.id.containerDetails_departureDate);
		ImageView containerImage = (ImageView)containerDetails.findViewById(R.id.containerDetails_containerImage);
		TextView arrivalDate = (TextView)containerDetails.findViewById(R.id.containerDetails_arrivalDate);
		TextView ownerName = (TextView)containerDetails.findViewById(R.id.containerDetails_ownerName);
		TextView ownerPhone = (TextView)containerDetails.findViewById(R.id.containerDetails_ownerPhone);
		TextView ownerEmail = (TextView)containerDetails.findViewById(R.id.containerDetails_ownerEmail);
		
		
		//Get the selected container from the singleton object
		CurrentContainer currentContainer = CurrentContainer.getInstance();
		location.setText(currentContainer.getLocation());
		destination.setText(currentContainer.getDestination());
		departureDate.setText(currentContainer.getDeparture_date());
		containerImage.setImageDrawable(currentContainer.getIcon());
		arrivalDate.setText(currentContainer.getArrival_date());
		ownerName.setText(currentContainer.getOwner_name());
		ownerPhone.setText(currentContainer.getOwner_tell());
		ownerEmail.setText(currentContainer.getOwner_email());
		
		return containerDetails;
	}
}
