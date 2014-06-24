package com.amirab_soft.containerhub;

import android.widget.BaseAdapter;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

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
		TextView descpTile = (TextView) convertView.findViewById(R.id.description);
		
		ContainerItem currentItem = containerItems.get(position);
		
		// Setting the image, title and descriptions
		imageIcon.setImageResource(currentItem.getIcon());
		titleTile.setText(currentItem.getTitle());
		descpTile.setText(currentItem.getDescription());
		
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
