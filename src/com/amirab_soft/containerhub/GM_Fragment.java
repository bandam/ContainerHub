package com.amirab_soft.containerhub;

import com.amirab_soft.containerhub.R;

import android.app.Fragment;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;

@SuppressLint("NewApi")
public class GM_Fragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.gm_fragement, container, false);
		return rootView;
	}
}
