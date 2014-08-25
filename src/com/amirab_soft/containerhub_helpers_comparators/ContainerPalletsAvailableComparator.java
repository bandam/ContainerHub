package com.amirab_soft.containerhub_helpers_comparators;

import java.util.Comparator;

import com.amirab_soft.containerhub_helpers.ContainerItem;

public class ContainerPalletsAvailableComparator implements Comparator<ContainerItem>{
	public int compare(ContainerItem o1, ContainerItem o2){
		return (o1.getNoPalletsAvailable() > o2.getNoPalletsAvailable())? 1 :
			(o1.getNoPalletsAvailable() < o2.getNoPalletsAvailable())? -1 : 0;
	}
}
