package com.amirab_soft.containerhub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ContainerInfo extends Fragment {
	private String ownerPhoneClass;
	private String ownerEmailClass;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View containerDetails = inflater.inflate(
				R.layout.container_info_fragment, container, false);

		TextView location = (TextView) containerDetails
				.findViewById(R.id.containerDetails_containerLocation);
		TextView destination = (TextView) containerDetails
				.findViewById(R.id.containerDetails_toDestination);
		TextView departureDate = (TextView) containerDetails
				.findViewById(R.id.containerDetails_departureDate);
		ImageView containerImage = (ImageView) containerDetails
				.findViewById(R.id.containerDetails_containerImage);
		TextView arrivalDate = (TextView) containerDetails
				.findViewById(R.id.containerDetails_arrivalDate);
		TextView ownerName = (TextView) containerDetails
				.findViewById(R.id.containerDetails_ownerName);
		TextView ownerPhone = (TextView) containerDetails
				.findViewById(R.id.containerDetails_ownerPhone);
		TextView ownerEmail = (TextView) containerDetails
				.findViewById(R.id.containerDetails_ownerEmail);

		Button callbtn = (Button) containerDetails
				.findViewById(R.id.containerDetails_callOwner);
		Button sendEmailBtn = (Button) containerDetails
				.findViewById(R.id.containerDetails_emailOwner);
		ProgressBar containerProgress = (ProgressBar)containerDetails.findViewById(R.id.containerDetails_progressbar);

		// Get the selected container from the singleton object
		CurrentContainer currentContainer = CurrentContainer.getInstance();
		location.setText(currentContainer.getLocation());
		destination.setText(currentContainer.getDestination());
		departureDate.setText(currentContainer.getDeparture_date());
		containerImage.setImageDrawable(currentContainer.getIcon());
		arrivalDate.setText(currentContainer.getArrival_date());
		ownerName.setText(currentContainer.getOwner_name());
		ownerPhone.setText(currentContainer.getOwner_tell());
		ownerEmail.setText(currentContainer.getOwner_email());
		containerProgress.setProgress(currentContainer.getProgress());

		// Set owner phone and email variables to access from call function
		ownerPhoneClass = currentContainer.getOwner_tell();
		ownerEmailClass = currentContainer.getOwner_email();

		// call owner on 'callbtn' clicked
		callbtn.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				callNumber();
			}
		});

		// send email on 'sendEmailbtn' clicked
		sendEmailBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendEmail();
			}
		});

		return containerDetails;
	}

	/** Send a call number intent to call container owner */
	private void callNumber() {
		Intent callOwnerIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
				"tel", ownerPhoneClass, null));
		startActivity(callOwnerIntent);
	}

	private void sendEmail() {
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
				"mailto", ownerEmailClass, null));
		startActivity(Intent.createChooser(emailIntent, "Send email..."));
	}
}
