package com.amirab_soft.containerhub_helpers;

import com.amirab_soft.containerhub.ContainerInfo;
import com.amirab_soft.containerhub.ContainerLocOnMap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ContainerDetails_TabPagerAdapter extends FragmentStatePagerAdapter{

	public ContainerDetails_TabPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg) {
		switch(arg){
		case 0:
			return new ContainerInfo();
		case 1:
			return new ContainerLocOnMap();
		}
		
		return null;
	}

	@Override
	public int getCount() {
		return 2;
	}

}
