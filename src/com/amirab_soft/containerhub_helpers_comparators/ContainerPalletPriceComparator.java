package com.amirab_soft.containerhub_helpers_comparators;

import java.util.Comparator;

import com.amirab_soft.containerhub_helpers.ContainerItem;

public class ContainerPalletPriceComparator implements Comparator<ContainerItem>{
	public int compare(ContainerItem o1, ContainerItem o2){
		return (o1.getPalletPrice() > o2.getPalletPrice())? 1 : 
			(o1.getPalletPrice() < o2.getPalletPrice())?-1 : 0; 
	}
}
