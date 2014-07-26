package com.amirab_soft.containerhub_helpers;

import android.widget.BaseAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;

import java.net.URL;
import java.util.List;

import com.amirab_soft.containerhub.R;
import com.amirab_soft.containerhub.R.id;
import com.amirab_soft.containerhub.R.layout;

public class ContainerItemCustomAdapter extends BaseAdapter{
	
	Context context;
	List<ContainerItem> containerItems;
	
	public ContainerItemCustomAdapter(Context context, List<ContainerItem> containerItems){
		this.context = context;
		this.containerItems = containerItems;
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent){
		if(convertView == null){
			LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.container_list_item, null);
		}
		
		ImageView imageIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView titleTile = (TextView) convertView.findViewById(R.id.title);
		TextView descpTile = (TextView) convertView.findViewById(R.id.containerDetails_containerLocation);
		ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.container_progressbar);
		TextView departureDate_view = (TextView) convertView.findViewById(R.id.containerDetails_departureDate);
		TextView toDestinationView = (TextView)convertView.findViewById(R.id.containerDetails_toDestination);
		
		ContainerItem currentItem = containerItems.get(position);
		
		// Setting the image, title and descriptions
		imageIcon.setImageDrawable(currentItem.getIcon());
		titleTile.setText(currentItem.getTitle());
		descpTile.setText(currentItem.getLocation());
		progressBar.setProgress(currentItem.getProgess());
		departureDate_view.setText(currentItem.getDepartureDate());
		toDestinationView.setText(currentItem.getDestination());
		
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
