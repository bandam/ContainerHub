package com.amirab_soft.containerhub_helpers_comparators;

import java.util.Comparator;

import com.amirab_soft.containerhub_helpers.ContainerItem;

public class ContainerProgressComparator implements Comparator<ContainerItem>{
	public int compare(ContainerItem o1, ContainerItem o2){
		return (o1.getProgess() > o2.getProgess())? 1 :
			(o1.getProgess() < o2.getProgess())? -1 : 0;
	}
}
