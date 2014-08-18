package com.amirab_soft.containerhub_helpers;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;

import java.util.List;

import com.amirab_soft.containerhub.R;

public class ContainerItemCustomAdapter extends BaseAdapter{
	
	Context context;
	List<ContainerItem> containerItems;
	
	public ContainerItemCustomAdapter(Context context, List<ContainerItem> containerItems){
		this.context = context;
		this.containerItems = containerItems;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		if(convertView == null){
			LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.container_list_item, null);
		}
		
		ImageView imageIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView titleTile = (TextView) convertView.findViewById(R.id.title);
		TextView descpTile = (TextView) convertView.findViewById(R.id.containerDetails_containerLocation);
		ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.container_progressbar);
		TextView departureDate_view = (TextView) convertView.findViewById(R.id.containerDetails_departureDate);
		TextView toDestinationView = (TextView)convertView.findViewById(R.id.containerDetails_toDestination);
		TextView palletPrice = (TextView)convertView.findViewById(R.id.containerDetails_palletPrice);
		TextView cartonPrice = (TextView)convertView.findViewById(R.id.containerDetails_cartonPrice);
		TextView noPalletsAvailable = (TextView)convertView.findViewById(R.id.containerDetails_noPalletAvailable);
		TextView noCartonsAvailable = (TextView)convertView.findViewById(R.id.containerDetails_noCartonsAvailable);
		
		ContainerItem currentItem = containerItems.get(position);
		
		// Setting the image, title and descriptions
		imageIcon.setImageDrawable(currentItem.getIcon());
		titleTile.setText(currentItem.getTitle());
		descpTile.setText(currentItem.getLocation());
		progressBar.setProgress(currentItem.getProgess());
		departureDate_view.setText(currentItem.getDepartureDate());
		toDestinationView.setText(currentItem.getDestination());
		palletPrice.setText(Integer.toString(currentItem.getPalletPrice()));
		cartonPrice.setText(Integer.toString(currentItem.getCartonPrice()));
		noPalletsAvailable.setText(Integer.toString(currentItem.getNoPalletsAvailable()));
		noCartonsAvailable.setText(Integer.toString(currentItem.getNoCartonsAvailable()));
		
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
