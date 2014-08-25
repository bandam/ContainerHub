package com.amirab_soft.containerhub_helpers_comparators;

import java.util.Comparator;

import com.amirab_soft.containerhub_helpers.ContainerItem;

public class ContainerCartonPriceComparator implements Comparator<ContainerItem>{
	public int compare(ContainerItem o1, ContainerItem o2){
		return (o1.getCartonPrice() > o2.getCartonPrice())? 1 : 
			(o1.getCartonPrice() < o2.getCartonPrice())? -1 : 0;
	}
}
