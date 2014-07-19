package com.amirab_soft.containerhub;

import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import com.amirab_soft.containerhub_helpers.JSONParser;





import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.amirab_soft.containerhub.R;
import com.amirab_soft.containerhub_helpers.ContainerItem;
import com.amirab_soft.containerhub_helpers.ContainerItemCustomAdapter;

import android.widget.ListView;

public class ContainerList_Fragment extends Fragment{
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_CONTAINER = "containers";
	//private static final String TAG_IMAGEDIRECTORY = "imageDirectory";
	private static final String TAG_LOCATION = "location";
	private static final String TAG_DESTINATION = "destination";
	private static final String TAG_STATUS = "status";
	private static final String TAG_DEPARTUREDATE = "departureDate";
	private static final String TAG_ARRIVALDATE = "arrivalDate";
	private static final String TAG_LOAD_CONTAINER_URL = "http://woltonguesthouse.com/php/ch/get_containers.php";
	
	
	private JSONParser jsonParser = new JSONParser();
	private LoadContainersTask loadContainerTask = null;
	private List<ContainerItem> containerList;
	
	private ListView listview;
	private ContainerItemCustomAdapter adapter;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		
		View rootView = inflater.inflate(R.layout.container_list_fragment, container, false);
		listview = (ListView)rootView.findViewById(R.id.container_list_layout);
	
		
		
		containerList = new ArrayList<ContainerItem>();
		
		loadContainerTask = new LoadContainersTask();
		loadContainerTask.execute();
		
		adapter = new ContainerItemCustomAdapter(getActivity(), containerList);
		listview.setAdapter(adapter);
		
		return rootView;
	}
	
	public class LoadContainersTask extends AsyncTask<String, Void, Boolean>{

		@Override
		protected Boolean doInBackground(String... params) {
			int success;
			JSONArray containersjson;
			JSONObject container;
			
			try{
				List<NameValuePair> param = new ArrayList<NameValuePair>();
				JSONObject json = jsonParser.makeHttpRequest(TAG_LOAD_CONTAINER_URL, "GET", param);
				
				// put json response to log for check
				Log.d("Container Details", json.toString());
				
				// Check success status
				success = json.getInt(TAG_SUCCESS);
				
				if(success == 1){
					//Success recieved container details
					JSONArray containerjsonarray0 = json.getJSONArray(TAG_CONTAINER);
					JSONArray containerjsonarray = containerjsonarray0.getJSONArray(0);
					for(int i = 0; i < containerjsonarray.length(); i++){
						container = containerjsonarray.getJSONObject(i);
						
						ContainerItem item = new ContainerItem((TAG_CONTAINER + i).toUpperCase(), container.getString(TAG_LOCATION),
								container.getString(TAG_DEPARTUREDATE),
								container.getString(TAG_ARRIVALDATE),
								container.getString(TAG_DESTINATION),
								R.drawable.container_default, 
								container.getInt(TAG_STATUS));
						containerList.add(item);
					}
				}
			}
			catch(JSONException exp){
				Log.d("JSON DecodeContainerlist", exp.toString());
			}
			return null;
		}
		
	}
}
