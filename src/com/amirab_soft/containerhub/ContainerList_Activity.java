package com.amirab_soft.containerhub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.amirab_soft.containerhub_helpers.JSONParser;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.amirab_soft.containerhub.R;
import com.amirab_soft.containerhub.MainActivity.SlideitemListener;
import com.amirab_soft.containerhub_helpers.ContainerItem;
import com.amirab_soft.containerhub_helpers.ContainerItemCustomAdapter;

import android.widget.AdapterView;
import android.widget.ListView;

public class ContainerList_Activity extends Activity {
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_CONTAINER = "containers";
	private static final String TAG_IMAGEDIRECTORY = "imageDirectory";
	private static final String TAG_LOCATION = "location";
	private static final String TAG_DESTINATION = "destination";
	private static final String TAG_STATUS = "status";
	private static final String TAG_DEPARTUREDATE = "departureDate";
	private static final String TAG_ARRIVALDATE = "arrivalDate";
	private static final String TAG_CONTAINERID = "containerID";
	private static final String TAG_UID = "uid";
	private static final String TAG_OWNERNAME = "name";
	private static final String TAG_OWNEREMAIL = "email";
	private static final String TAG_OWNERCURCITY = "curcity";
	private static final String TAG_OWNERTELL = "tell";
	private static final String TAG_LOAD_CONTAINER_URL = "http://woltonguesthouse.com/php/ch/get_containers.php";

	private JSONParser jsonParser = new JSONParser();
	private LoadContainersTask loadContainerTask = null;
	private List<ContainerItem> containerList;

	private ListView listview;
	private ContainerItemCustomAdapter adapter;

	private View mLoginFormView;
	private View mLoginStatusView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.container_list_fragment);

		// View rootView = inflater.inflate(R.layout.container_list_fragment,
		// container, false);
		listview = (ListView) findViewById(R.id.container_list_layout);
		mLoginFormView = listview;
		mLoginStatusView = findViewById(R.id.load_status);

		containerList = new ArrayList<ContainerItem>();

		adapter = new ContainerItemCustomAdapter(this, containerList);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new ContainerListItemListener());

		showProgress(true);
		loadContainerTask = new LoadContainersTask();
		loadContainerTask.execute();

		adapter.notifyDataSetChanged();

	}

	public class LoadContainersTask extends AsyncTask<String, Void, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			int success;
			JSONObject container;

			try {
				List<NameValuePair> param = new ArrayList<NameValuePair>();
				JSONObject json = jsonParser.makeHttpRequest(
						TAG_LOAD_CONTAINER_URL, "GET", param);

				// put json response to log for check
				Log.d("Container Details", json.toString());

				// Check success status
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// Success recieved container details
					JSONArray containerjsonarray0 = json
							.getJSONArray(TAG_CONTAINER);
					JSONArray containerjsonarray = containerjsonarray0
							.getJSONArray(0);
					
					for (int i = 0; i < containerjsonarray.length(); i++) {
						container = containerjsonarray.getJSONObject(i);
						Drawable thumb_d;
						try {
							URL thumb_u = new URL(
									"http://www.woltonguesthouse.com/php/ch/containerImages/"
											+ container
													.getString(TAG_IMAGEDIRECTORY)+".jpg");
							thumb_d = Drawable.createFromStream(
									thumb_u.openStream(), "src");

							ContainerItem item = new ContainerItem(
									(TAG_CONTAINER +" "+ i).toUpperCase(),
									container.getString(TAG_LOCATION),
									container.getString(TAG_DEPARTUREDATE),
									container.getString(TAG_ARRIVALDATE),
									container.getString(TAG_DESTINATION),
									thumb_d, container.getInt(TAG_STATUS),
									container.getInt(TAG_UID),
									container.getInt(TAG_CONTAINERID),
									container.getString(TAG_OWNERNAME),
									container.getString(TAG_OWNEREMAIL),
									container.getString(TAG_OWNERCURCITY),
									container.getString(TAG_OWNERTELL));
							containerList.add(item);
						} catch (Exception e) {
							Log.e("image load from URL", e.toString());
						}

					}
				}
			} catch (JSONException exp) {
				Log.d("JSON DecodeContainerlist", exp.toString());
			}
			return true;
		}

		protected void onPostExecute(final Boolean success) {
			showProgress(false);
			if (success) {
				adapter.notifyDataSetChanged();
			} else {
				adapter.notifyDataSetChanged();
			}
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}
	
	private class ContainerListItemListener implements ListView.OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent containerDetailsIntent = new Intent(getBaseContext(),ContainerInfo_Main.class);
			startActivity(containerDetailsIntent);
			
			//Get selected Container from list and save it to the singleton for use in other activities
			CurrentContainer currentContainer = CurrentContainer.getInstance();
			ContainerItem selectedContainer = (ContainerItem)containerList.get(arg2);
			
			currentContainer.setArrival_date(selectedContainer.getArrivalDate());
			currentContainer.setDeparture_date(selectedContainer.getDepartureDate());
			currentContainer.setLocation(selectedContainer.getLocation());
			currentContainer.setDestination(selectedContainer.getDestination());
			currentContainer.setIcon(selectedContainer.getIcon());
			currentContainer.setProgress(selectedContainer.getProgess());
			currentContainer.setCid(selectedContainer.getCid());
			currentContainer.setUid(selectedContainer.getUid());
			currentContainer.setOwner_name(selectedContainer.getOwner_name());
			currentContainer.setOwner_email(selectedContainer.getOwner_email());
			currentContainer.setOwner_current_city(selectedContainer.getOwner_current_city());
			currentContainer.setOwner_tell(selectedContainer.getOwner_tell());

		}
		
	}
	
}
