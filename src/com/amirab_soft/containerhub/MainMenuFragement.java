package com.amirab_soft.containerhub;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainMenuFragement extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.main_menu_fragment, container, false);
		
		Button postContainerButton = (Button)rootView.findViewById(R.id.containerDetails_emailOwner);
		Button searchForContianerButton = (Button)rootView.findViewById(R.id.containerDetails_callOwner);
		
		postContainerButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent postContainerIntent = new Intent(getActivity().getBaseContext(), PostContainer_Activity.class);
				startActivity(postContainerIntent);
			}
			
		});
		
		
		searchForContianerButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent searchForContainerIntent = new Intent(getActivity().getBaseContext(), ContainerList_Activity.class);
				startActivity(searchForContainerIntent);
			}
			
		});
		return rootView;
	}
}
