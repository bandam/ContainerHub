package com.amirab_soft.containerhub;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class LoginWithFacebookActivity extends FragmentActivity {
	
	private LoginWithFacebookFragment mainFragment;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        if (savedInstanceState == null) {
        	// Add the fragment on initial activity setup
        	mainFragment = new LoginWithFacebookFragment();
            getSupportFragmentManager()
            .beginTransaction()
            .add(android.R.id.content, mainFragment)
            .commit();
        } else {
        	// Or set the fragment from restored state info
        	mainFragment = (LoginWithFacebookFragment) getSupportFragmentManager()
        	.findFragmentById(android.R.id.content);
        }
    }
    
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/
}
