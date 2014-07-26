package com.amirab_soft.containerhub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ContainerLocOnMap extends Fragment{
	 WebView myWebView;
	 //private static final  String mapPath = "https://maps.google.com/?ll=37.0625,-95.677068&spn=29.301969,56.513672&t=h&z=4";
	 private static final String mapPath = "https://www.google.com/maps/place/Accra,+Ghana/";
	 
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			View containerLocOnMap = inflater.inflate(R.layout.container_loconmap_fragment,container, false);
			  myWebView = (WebView)containerLocOnMap.findViewById(R.id.mapview);
			  myWebView.getSettings().setJavaScriptEnabled(true);
			  myWebView.setWebViewClient(new WebViewClient());
			  
			  myWebView.loadUrl(mapPath);
			return containerLocOnMap;
		}

}
