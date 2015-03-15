package com.amirab_soft.containerhub_helpers;

import java.util.List;

//import com.amirab_soft.containerhub.ContainerList_Activity.LoadContainersTask;

public class LoadContainersTask implements Runnable {
	/* Tags for container properties */
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_CONTAINER = "containers";
	private static final String TAG_IMAGEDIRECTORY = "imageDirectory";
	private static final String TAG_LOCATION_CITY = "location_city";
	private static final String TAG_LOCATION_COUNTRY = "location_country";
	private static final String TAG_DESTINATION_CITY = "destination_city";
	private static final String TAG_DESTINATION_COUNTRY = "destination_country";
	private static final String TAG_STATUS = "status";
	private static final String TAG_DEPARTUREDATE = "departureDate";
	private static final String TAG_ARRIVALDATE = "arrivalDate";
	private static final String TAG_CONTAINERID = "containerID";
	private static final String TAG_UID = "uid";
	private static final String TAG_OWNERNAME = "name";
	private static final String TAG_OWNEREMAIL = "email";
	private static final String TAG_OWNERCURCITY = "curcity";
	private static final String TAG_OWNERTELL = "tell";
	private static final String TAG_PALLETPRICE = "palletPrice";
	private static final String TAG_CARTONPRICE = "cartonPrice";
	private static final String TAG_NOPALLETSAVAILABLE = "noPalletsAvailable";
	private static final String TAG_NOCARTONSAVAILABLE = "noCartonsAvailable";
	private static final String TAG_LOAD_CONTAINER_URL = "http://woltonguesthouse.com/php/ch/get_containers.php";

	/* Network Variable */
	private JSONParser jsonParser = new JSONParser();
	private LoadContainersTask loadContainerTask = null;
	private List<ContainerItem> containerList;

	@Override
	public void run() {

	}

}
