package com.amirab_soft.containerhub;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import android.widget.ListView;

public class ContainerList_Fragment extends Fragment{
	
	private List<ContainerItem> containerList;
	private ListView listview;
	TypedArray menuIcons;
	private ContainerItemCustomAdapter adapter;
	
	private String[] tempTitles = {"Container 1", "Container 2", "Container 3"};
	private String[] tempDescps = {"Description 1", "Description 2", "Description 3"};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		
		View rootView = inflater.inflate(R.layout.container_list_fragment, container, false);
		listview = (ListView)rootView.findViewById(R.id.containerList);
		menuIcons = getResources().obtainTypedArray(R.array.icons);
		
		
		containerList = new ArrayList<ContainerItem>();
		// add items to the container list
		for(int i = 0; i < 3; i++){
			ContainerItem item = new ContainerItem(tempTitles[i].toUpperCase(), tempDescps[i],
					menuIcons.getResourceId(i, -1));
			containerList.add(item);
		}
		
		menuIcons.recycle();
		
		adapter = new ContainerItemCustomAdapter(getActivity(), containerList);
		listview.setAdapter(adapter);
		
		return rootView;
	}
}
