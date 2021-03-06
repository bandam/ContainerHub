package com.amirab_soft.containerhub_helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.amirab_soft.containerhub.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

public class UploadImage extends Activity {
	InputStream inputStream;
	ArrayList<NameValuePair> nameValuePairs;
	String the_string_response, error;
	int contentLength;
	String res;

	public void uploadNewContainer() {
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.container_default);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream); // compress to
																// which format
																// you want.
		byte[] byte_arr = stream.toByteArray();
		String image_str = Base64.encodeBytes(byte_arr);
		nameValuePairs = new ArrayList<NameValuePair>();

		nameValuePairs.add(new BasicNameValuePair("image", image_str));

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost(
							"http://woltonguesthouse.com/php/ch/save_container.php");
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					HttpResponse response = httpclient.execute(httppost);
					the_string_response = convertHttpResponseToString(response);
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(UploadImage.this,
									"Response " + the_string_response,
									Toast.LENGTH_LONG).show();
						}
					});

				} catch (Exception e) {
					error = e.getMessage();
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(UploadImage.this, "ERROR " + error,
									Toast.LENGTH_LONG).show();
						}
					});
					System.out.println("Error in http connection "
							+ e.toString());
				}
			}
		});
		t.start();
	}

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.login_with_email);

	}

	public String convertHttpResponseToString(HttpResponse response)
			throws IllegalStateException, IOException {

		res = "";
		StringBuffer buffer = new StringBuffer();
		inputStream = response.getEntity().getContent();
		contentLength = (int) response.getEntity().getContentLength(); // getting
																		// content
																		// length�..
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(UploadImage.this,
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
																// stringbuffer�..
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				inputStream.close(); // closing the stream�..
			} catch (IOException e) {
				e.printStackTrace();
			}
			res = buffer.toString(); // converting stringbuffer to string�..

			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					Toast.makeText(UploadImage.this, "Result : " + res,
							Toast.LENGTH_LONG).show();
				}
			});
			// System.out.println("Response => " +
			// EntityUtils.toString(response.getEntity()));
		}
		return res;
	}
}