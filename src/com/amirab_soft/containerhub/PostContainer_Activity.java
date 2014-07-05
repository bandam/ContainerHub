package com.amirab_soft.containerhub;

import java.io.FileNotFoundException;
import java.io.InputStream;
import com.amirab_soft.containerhub.R;

import android.app.Activity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class PostContainer_Activity extends Activity{
	private static final int SELECT_PHOTO = 100;   
	ImageView containerImage;
	Uri selectedImagePath;
	EditText arrivalDate;
	Uri imageUri;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_container_activity);
		
		containerImage = (ImageView)findViewById(R.id.post_container_containerImage);
		Button btn_selectImg = (Button)findViewById(R.id.post_contn_selectImg);

		
		/*Attact a listener to selectImage button*/
		btn_selectImg.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				selectPhotoFromGallery();
			}
		});
	}
	
	
	/*Select An Image from the gallery*/
	public void selectPhotoFromGallery() {
		Intent selectPhotoIntent = new Intent(Intent.ACTION_PICK);
		selectPhotoIntent.setType("image/*");
		startActivityForResult(selectPhotoIntent, SELECT_PHOTO);
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
	    super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

	    switch(requestCode) { 
	    case SELECT_PHOTO:
	        if(resultCode == RESULT_OK){  
	            selectedImagePath = imageReturnedIntent.getData();
	            InputStream imageStream;
				try {
					imageStream = getContentResolver().openInputStream(selectedImagePath);
		            Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
		            containerImage.setImageBitmap(yourSelectedImage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	}

}

