package com.amirab_soft.containerhub_helpers;

import com.amirab_soft.containerhub.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/** Fragment to ask the user the criteria to sort search results by */
public class SortByDialogeFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceStage) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle(R.string.sortbyfragement_title).setItems(
				R.array.sortbyfragment_sortcriteria,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						mListener.onSortByDialogItemSelected(SortByDialogeFragment.this, which);
					}
				});
		return builder.create();
	}

	/* Interface to be implemented by activity starting this dialog */
	public interface SortByDialogListener {
		public void onSortByDialogItemSelected(DialogFragment dialog, int where);
	}

	// Instance of listener Interface to deliver select events
	SortByDialogListener mListener;

	/*
	 * Override the Fragment.on Attach() method to instantiate the
	 * SortByDialogListener
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Verify that the host activity implements the callback interface
		try {
			// Instantiate the NoticeDialogListener so we can send events to the
			// host
			mListener = (SortByDialogListener) activity;
		} catch (ClassCastException ex) {
			// The activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString()
					+ "must implement SortByDialogListener");
		}
	}

}
