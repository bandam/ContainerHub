package com.amirab_soft.containerhub;

import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amirab_soft.containerhub_helpers.CurrentUser;
import com.facebook.Request;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class LoginWithFacebookFragment extends Fragment {
	
	private static final String TAG = "FacebookFragment";
	Button continueBtn;
	Button loginWithEmailBtn;
	View userDetailsContinueView;
	TextView userNametxtv;
	
	private UiLifecycleHelper uiHelper;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(final Session session, final SessionState state, final Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };
    
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, 
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.login_with_facebook, container, false);
		
		LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
		authButton.setFragment(this);
		authButton.setReadPermissions(Arrays.asList("user_location","public_profile"));
		
		//User Details and Continue button values
		continueBtn= (Button)view.findViewById(R.id.fabookLogin_continueBtn);
		userDetailsContinueView = view.findViewById(R.id.facebookLogin_userDetailsContinuePanel);
		userNametxtv = (TextView)view.findViewById(R.id.facebookLoging_username);
		
		loginWithEmailBtn = (Button)view.findViewById(R.id.loginWithEmailBtn);
		
		
		
		continueBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		        Intent intent = new Intent(getActivity().getBaseContext(), MainMenuActivity.class);
				startActivity(intent);
			}
		});
		
		loginWithEmailBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent loginWithEmailIntent = new Intent(getActivity().getBaseContext(), LoginWithEmailActivity.class);
				startActivity(loginWithEmailIntent);
			}
		});
        
		return view;
	}
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        
        // For scenarios where the main activity is launched and user
		// session is not null, the session state change notification
		// may not be triggered. Trigger it if it's open/closed.
		Session session = Session.getActiveSession();
		if (session != null &&
				(session.isOpened() || session.isClosed()) ) {
			onSessionStateChange(session, session.getState(), null);
		}
		
        uiHelper.onResume();
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        Session.getActiveSession().onActivityResult(getActivity(), requestCode, resultCode, data);

        if (Session.getActiveSession().isOpened())
        {
            // Request user data and show the results
            Request.newMeRequest(Session.getActiveSession(), new GraphUserCallback()
            {

				@Override
				public void onCompleted(GraphUser user, Response response) {
					// TODO Auto-generated method stub
                    if (null != user)
                    {
                    	
    					CurrentUser currentUser = CurrentUser.getInstance();
						currentUser.setUsername(user.getUsername());
						currentUser.setEmail("");
						currentUser.setCurrentCity("Bergen");//user.getLocation().getName());
						currentUser.setPhone("");
						currentUser.setUid(user.getId());
						currentUser.setName(user.getName());
                    	
                        // Display the parsed user info
                        /*Log.v(TAG, "Response : " + response);
                        Log.v(TAG, "UserID : " + user.getId());
                        Log.v(TAG, "User FirstName : " + user.getFirstName());*/
                        //Log.v(TAG,user.asMap().get("email").toString());
						
						Intent intent = new Intent(getActivity().getBaseContext(), MainMenuActivity.class);
						startActivity(intent);
                    }
					
				}
            }).executeAsync();
        }
        uiHelper.onActivityResult(requestCode, resultCode, data);

    }
    
    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }
    
    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
    	if (state.isOpened()) {
			//Set the name in the facebook login page
    		CurrentUser currentUser = CurrentUser.getInstance();
			userNametxtv.setText(currentUser.getName());
    		userDetailsContinueView.setVisibility(View.VISIBLE);
    		loginWithEmailBtn.setVisibility(View.GONE);
			
    		Log.i(TAG, "Logged in...");
    		
        } else if (state.isClosed()) {
        	userDetailsContinueView.setVisibility(View.GONE);
        	loginWithEmailBtn.setVisibility(View.VISIBLE);
        	Log.i(TAG, "Logged out...");
        }
    }
}
