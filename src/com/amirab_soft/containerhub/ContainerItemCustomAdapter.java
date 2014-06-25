package com.amirab_soft.containerhub;

import android.widget.BaseAdapter;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;

import java.util.List;

public class ContainerItemCustomAdapter extends BaseAdapter{
	
	Context context;
	List<ContainerItem> containerItems;
	
	ContainerItemCustomAdapter(Context context, List<ContainerItem> containerItems){
		this.context = context;
		this.containerItems = containerItems;
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent){
		if(convertView == null){
			LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.container_item, null);
		}
		
		ImageView imageIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView titleTile = (TextView) convertView.findViewById(R.id.title);
		TextView descpTile = (TextView) convertView.findViewById(R.id.container_location);
		ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.container_progressbar);
		TextView departureDate_view = (TextView) convertView.findViewById(R.id.departure_date);
		TextView toDestinationView = (TextView)convertView.findViewById(R.id.to_destination);
		
		ContainerItem currentItem = containerItems.get(position);
		
		// Setting the image, title and descriptions
		imageIcon.setImageResource(currentItem.getIcon());
		titleTile.setText(currentItem.getTitle());
		descpTile.setText(currentItem.getLocation());
		progressBar.setProgress(currentItem.getProgess());
		departureDate_view.setText(currentItem.getDate());
		toDestinationView.setText(currentItem.getTo());
		
		return convertView;
	}
	
	@Override
	public int getCount(){
		return containerItems.size();
	}
	
	@Override
	public Object getItem(int position){
		return containerItems.get(position);
	}
	
	@Override
	public long getItemId(int position){
		return containerItems.indexOf(getItem(position));
	}
}
