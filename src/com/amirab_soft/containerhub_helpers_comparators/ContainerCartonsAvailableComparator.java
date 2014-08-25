package com.amirab_soft.containerhub_helpers_comparators;

import java.util.Comparator;

import com.amirab_soft.containerhub_helpers.ContainerItem;

public class ContainerCartonsAvailableComparator implements Comparator<ContainerItem>{
	public int compare(ContainerItem o1, ContainerItem o2){
		return (o1.getNoCartonsAvailable() > o2.getNoCartonsAvailable())? 1 :
			(o1.getNoCartonsAvailable() < o2.getNoCartonsAvailable())? -1 : 0;
	}
}
