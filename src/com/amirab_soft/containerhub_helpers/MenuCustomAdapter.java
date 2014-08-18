package com.amirab_soft.containerhub_helpers;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import java.util.List;

import com.amirab_soft.containerhub.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MenuCustomAdapter extends BaseAdapter{
	Context context;
	List<MenuRowItem> rowItem;
	
	public MenuCustomAdapter(Context context, List<MenuRowItem> rowItem){
		this.context = context;
		this.rowItem = rowItem;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		if(convertView == null){
			LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.slidemenu_list_item, null);
		}
		
		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView txttTile = (TextView) convertView.findViewById(R.id.title);
		
		
		MenuRowItem row_pos = rowItem.get(position);
		
		// Setting the image and text description
		imgIcon.setImageResource(row_pos.getIcon());
		txttTile.setText(row_pos.getTitle());
		return convertView;
	}
	
	@Override
	public int getCount(){
		return rowItem.size();
	}
	
	@Override
	public Object getItem(int position){
		return rowItem.get(position);
	}
	
	@Override
	public long getItemId(int position){
		return rowItem.indexOf(getItem(position));
	}
}
