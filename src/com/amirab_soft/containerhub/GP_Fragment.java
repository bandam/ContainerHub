package com.amirab_soft.containerhub;

import com.amirab_soft.containerhub.R;

import android.app.Fragment;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;

@SuppressLint("NewApi")
public class GP_Fragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.gp_fragement, container, false);
		return rootView;
	}
}
