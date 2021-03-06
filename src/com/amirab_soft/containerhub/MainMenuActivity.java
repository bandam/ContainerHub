package com.amirab_soft.containerhub;

import java.util.ArrayList;
import java.util.List;

import com.amirab_soft.containerhub.R;
import com.amirab_soft.containerhub_helpers.MenuCustomAdapter;
import com.amirab_soft.containerhub_helpers.MenuRowItem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainMenuActivity extends Activity {
	String[] menuTitles;
	TypedArray menuIcons;

	// navigation drawer title
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private List<MenuRowItem> rowItems;
	private MenuCustomAdapter adapter;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Todo Auto-generated method stud
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu_activity);

		mTitle = mDrawerTitle = getTitle();

		menuTitles = getResources().getStringArray(R.array.titles);
		menuIcons = getResources().obtainTypedArray(R.array.icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.slider_list);

		rowItems = new ArrayList<MenuRowItem>();

		for (int i = 0; i < menuTitles.length; i++) {
			MenuRowItem item = new MenuRowItem(menuTitles[i],
					menuIcons.getResourceId(i, -1));
			rowItems.add(item);
		}

		menuIcons.recycle();

		adapter = new MenuCustomAdapter(getApplicationContext(), rowItems);

		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new SlideitemListener());

		// Enabling action bar app icon as a toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
			@Override
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);

				// call onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// call onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first navigation item
			updateDisplay(2);
		}
	}

	//Listener for slide menu items
	class SlideitemListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			updateDisplay(position);
		}
	}

	/**
	 * Selects the appropriate fragment to display based on user
	 * selected menu item
	 * */
	@SuppressLint("NewApi")
	private void updateDisplay(int position) {
		Fragment fragment = null;
		Intent startPostContainer = new Intent(getBaseContext(),
				PostContainer_Activity.class);
		Intent listContainer = new Intent(getBaseContext(),
				ContainerList_Activity.class);

		fragment = new MainMenuFragement();

		switch (position) {
		case 0:
			startActivity(listContainer);
			break;
		case 1:
			startActivity(startPostContainer);
			break;
		case 2:
			fragment = new MainMenuFragement();
			break;

		default:
			break;

		}

		// Insert the appropriate fragment into the Activity based on menu item selected
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction transaction = fragmentManager
					.beginTransaction();

			transaction.replace(R.id.frame_container, fragment);
			transaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
			transaction.commit();

			// update selected item and title, then close the drawer
			setTitle(menuTitles[3]);
			mDrawerLayout.closeDrawer(mDrawerList);
		}/* else 
		{
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}*/

	}

	@SuppressLint("NewApi")
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle navigation drawer on selecting action bar application
		// icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.container_list_searchView:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/***
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if navigation drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.container_list_searchView).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
