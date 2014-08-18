package com.amirab_soft.containerhub;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.apache.http.NameValuePair;

import com.amirab_soft.containerhub.R;
import com.amirab_soft.containerhub_helpers.Base64;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

//upload new container imports
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.widget.Toast;

@SuppressLint("NewApi")
public class PostContainer_Activity extends Activity {
	private static final int SELECT_PHOTO = 14000;
	private static final int REQUEST_CAPTURE_IMAGE = 15000;
	ImageView containerImage;
	Uri selectedImagePath;
	Uri capturedCamImagePath;
	EditText arrivalDate;
	Uri imageUri;
	Bitmap imageBitmap;

	// Form components
	EditText container_location_city;
	EditText container_location_country;
	EditText container_destination_city;
	EditText container_destination_country;
	SeekBar container_currentProgress;
	EditText container_departureDate;
	EditText container_arrivalDate;
	EditText container_palletPrice;
	EditText container_palletsAvailable;
	EditText container_cartonPrice;
	EditText container_cartonsAvailable;

	EditText container_ownerName;
	EditText container_ownerPhone;
	EditText container_ownerEmail;

	// post new container variables
	InputStream inputStream;
	ArrayList<NameValuePair> nameValuePairs;
	String the_string_response, error;
	int contentLength;
	String res;
	// end

	// Date picker variable
	private int selectedDateType = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_container_activity);

		containerImage = (ImageView) findViewById(R.id.post_container_containerImage);
		Button btn_selectImg = (Button) findViewById(R.id.post_contn_selectImg);
		Button btn_camCapture = (Button) findViewById(R.id.post_contn_capturecameraImg);
		Button btn_uploadContainer = (Button) findViewById(R.id.post_contn_uploadContainer);

		// Initialize form components
		container_location_city = (EditText) findViewById(R.id.post_contn_form_containerLocation_city);
		container_location_country = (EditText)findViewById(R.id.containerList_search_FromCountry);
		container_destination_city = (EditText) findViewById(R.id.post_contn_form_containerDestination_city);
		container_destination_country = (EditText)findViewById(R.id.containerList_search_ToCountry);
		
		container_currentProgress = (SeekBar) findViewById(R.id.post_contn_form_containerProgress);
		container_departureDate = (EditText) findViewById(R.id.post_contn_form_containerDepartureDate);
		container_arrivalDate = (EditText) findViewById(R.id.post_contn_form_containerArrivalDate);

		container_palletPrice = (EditText) findViewById(R.id.post_contn_form_palletPrice);
		container_palletsAvailable = (EditText) findViewById(R.id.post_contn_form_palletsAvailable);
		container_cartonPrice = (EditText) findViewById(R.id.post_contn_form_cartonPrice);
		container_cartonsAvailable = (EditText) findViewById(R.id.post_contn_form_cartonsAvailable);

		container_ownerName = (EditText) findViewById(R.id.containerDetails_ownerName);
		container_ownerPhone = (EditText) findViewById(R.id.containerDetails_ownerPhone);
		container_ownerEmail = (EditText) findViewById(R.id.containerDetails_ownerEmail);

		// Attach a listener to selectImage button
		btn_selectImg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				selectPhotoFromGallery();
			}
		});

		// Attach a listener to camCapture button
		btn_camCapture.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				takepicture();
			}
		});

		// Attach a listener to uploadContainer button
		btn_uploadContainer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				uploadNewContainer();
			}
		});

		// Show a datepicker when user selects arrival date TextEdit
		container_arrivalDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// set selected date variable
				selectedDateType = 2;
				// TODO Auto-generated method stub
				new DatePickerDialog(PostContainer_Activity.this, date,
						myCalendar.get(Calendar.YEAR), myCalendar
								.get(Calendar.MONTH), myCalendar
								.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		

		// Show a datepicker when user selects departure date TextEdit
		container_departureDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// set selected date variable
				selectedDateType = 1;
				// TODO Auto-generated method stub
				new DatePickerDialog(PostContainer_Activity.this, date,
						myCalendar.get(Calendar.YEAR), myCalendar
								.get(Calendar.MONTH), myCalendar
								.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

		// Preset current user as containerOwner
		setOwnerDetails();
	}

	// Date picker
	Calendar myCalendar = Calendar.getInstance();

	DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			myCalendar.set(Calendar.YEAR, year);
			myCalendar.set(Calendar.MONTH, monthOfYear);
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

			if (selectedDateType == 1) {
				updateLabel(container_departureDate);
			} else {
				updateLabel(container_arrivalDate);
			}

		}

	};

	private void updateLabel(EditText label) {
		String myFormat = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		label.setText(sdf.format(myCalendar.getTime()));
	}

	/** Select An Image from the gallery */
	private void selectPhotoFromGallery() {
		Intent selectPhotoIntent = new Intent(Intent.ACTION_PICK);
		selectPhotoIntent.setType("image/*");
		startActivityForResult(selectPhotoIntent, SELECT_PHOTO);
	}

	/** Capture camera Image */
	private void takepicture() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(takePictureIntent, REQUEST_CAPTURE_IMAGE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

		switch (requestCode) {

		/* Process image from gallery */
		case SELECT_PHOTO:
			if (resultCode == RESULT_OK) {
				selectedImagePath = imageReturnedIntent.getData();
				InputStream imageStream;
				try {
					imageStream = getContentResolver().openInputStream(
							selectedImagePath);
					imageBitmap = BitmapFactory.decodeStream(imageStream);
					containerImage.setImageBitmap(imageBitmap);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			break;

		/* Process image from camera capture */
		case REQUEST_CAPTURE_IMAGE:
			if (resultCode == RESULT_OK) {
				Bundle extras = imageReturnedIntent.getExtras();
				imageBitmap = (Bitmap) extras.get("data");
				containerImage.setImageBitmap(imageBitmap);
			}
		}
	}

	/** UPLOAD NEW CONTAINER */
	private void uploadNewContainer() {
		// Get container Image and compress it
		Bitmap bitmap = imageBitmap;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);

		byte[] byte_arr = stream.toByteArray();
		String image_str = Base64.encodeBytes(byte_arr);

		// Get form data
		String str_contn_location_city = container_location_city.getText().toString();
		String str_contn_location_country = container_location_country.getText().toString();
		String str_contn_destination_city = container_destination_city.getText()
				.toString();
		String str_contn_destination_country = container_destination_country.getText().toString();
		
		int int_contn_progress = container_currentProgress.getProgress();
		String str_contn_departureDate = container_departureDate.getText()
				.toString();
		String str_contn_arrivalDate = container_arrivalDate.getText()
				.toString();
		String str_contn_palletPrice = container_palletPrice.getText()
				.toString();
		String str_contn_palletsAvailable = container_palletsAvailable
				.getText().toString();
		String str_contn_cartonPrice = container_cartonPrice.getText()
				.toString();
		String str_contn_cartonsAvailable = container_cartonsAvailable
				.getText().toString();

		String str_contn_ownerName = container_ownerName.getText().toString();
		String str_contn_ownerPhone = container_ownerPhone.getText().toString();
		String str_contn_ownerEmail = container_ownerEmail.getText().toString();

		// Get current userid
		CurrentUser currentUser = CurrentUser.getInstance();
		String str_contn_ownerUId = currentUser.getUid();

		// Add data to send over to server
		nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("image", image_str));
		nameValuePairs.add(new BasicNameValuePair("container_location_city",
				str_contn_location_city));
		nameValuePairs.add(new BasicNameValuePair("container_location_country", str_contn_location_country));
		nameValuePairs.add(new BasicNameValuePair("container_destination_city",
				str_contn_destination_city));
		nameValuePairs.add(new BasicNameValuePair("container_destination_country", str_contn_destination_country));
		nameValuePairs.add(new BasicNameValuePair("container_progress", Integer
				.toString(int_contn_progress)));
		nameValuePairs.add(new BasicNameValuePair("container_departureDate",
				str_contn_departureDate));
		nameValuePairs.add(new BasicNameValuePair("container_arrivalDate",
				str_contn_arrivalDate));
		nameValuePairs.add(new BasicNameValuePair("container_palletPrice",
				str_contn_palletPrice));
		nameValuePairs.add(new BasicNameValuePair("container_palletsAvailable",
				str_contn_palletsAvailable));
		nameValuePairs.add(new BasicNameValuePair("container_cartonPrice",
				str_contn_cartonPrice));
		nameValuePairs.add(new BasicNameValuePair("container_cartonsAvailable",
				str_contn_cartonsAvailable));
		nameValuePairs.add(new BasicNameValuePair("container_ownerName",
				str_contn_ownerName));
		nameValuePairs.add(new BasicNameValuePair("container_ownerPhone",
				str_contn_ownerPhone));
		nameValuePairs.add(new BasicNameValuePair("container_ownerEmail",
				str_contn_ownerEmail));
		nameValuePairs.add(new BasicNameValuePair("container_ownerUserId",
				str_contn_ownerUId));

		// Define a new process to send data over to the server
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost(
							"http://woltonguesthouse.com/php/ch/containerImages/save_container.php");
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					HttpResponse response = httpclient.execute(httppost);
					the_string_response = convertHttpResponseToString(response);
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(PostContainer_Activity.this,
									"Response " + the_string_response,
									Toast.LENGTH_LONG).show();
						}
					});

				} catch (Exception e) {
					error = e.getMessage();
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(PostContainer_Activity.this,
									"ERROR " + error, Toast.LENGTH_LONG).show();
						}
					});
					System.out.println("Error in http connection "
							+ e.toString());
				}
			}
		});
		t.start();
	}

	/** Convert Http response from the server to string */
	private String convertHttpResponseToString(HttpResponse response)
			throws IllegalStateException, IOException {

		res = "";
		StringBuffer buffer = new StringBuffer();
		inputStream = response.getEntity().getContent();
		contentLength = (int) response.getEntity().getContentLength(); // getting
																		// content
																		// length…..
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(PostContainer_Activity.this,
						"contentLength : " + contentLength, Toast.LENGTH_LONG)
						.show();
			}
		});

		if (contentLength < 0) {
		} else {
			byte[] data = new byte[512];
			int len = 0;
			try {
				while (-1 != (len = inputStream.read(data))) {
					buffer.append(new String(data, 0, len)); // converting to
																// string and
																// appending to
																// stringbuffer…..
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {

				// closing the stream
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			res = buffer.toString(); // converting stringbuffer to string…..

			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					Toast.makeText(PostContainer_Activity.this,
							"Result : " + res, Toast.LENGTH_LONG).show();
				}
			});
			// System.out.println("Response => " +
			// EntityUtils.toString(response.getEntity()));
		}
		return res;
	}

	public void setOwnerDetails() {
		CurrentUser currentUser = CurrentUser.getInstance();
		container_ownerName.setText(currentUser.getName());
		container_ownerPhone.setText(currentUser.getPhone());
		container_ownerEmail.setText(currentUser.getEmail());
	}

}
