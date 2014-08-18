package com.amirab_soft.containerhub;

import com.amirab_soft.containerhub_helpers.CurrentContainer;

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
	 
	 
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			View containerLocOnMap = inflater.inflate(R.layout.container_loconmap_fragment,container, false);
			  myWebView = (WebView)containerLocOnMap.findViewById(R.id.mapview);
			  myWebView.getSettings().setJavaScriptEnabled(true);
			  myWebView.setWebViewClient(new WebViewClient());
			  
			  
			  CurrentContainer currentContainer = CurrentContainer.getInstance();
			  
			  String mapPath = "https://www.google.com/maps/place/" + currentContainer.getLocation();
			  //String mapPath = "https://www.google.com/maps/dir/"+currentContainer.getLocation()+"/"+currentContainer.getDestination()+"/";
			  myWebView.loadUrl(mapPath);
			return containerLocOnMap;
		}

}
