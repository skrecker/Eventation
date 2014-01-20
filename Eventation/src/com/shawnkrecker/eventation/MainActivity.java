package com.shawnkrecker.eventation;



import android.annotation.TargetApi;
import android.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements ActionBar.OnNavigationListener, EventFragment.OnFragmentInteractionListener,GroupFragment.OnFragmentInteractionListener,ProfileFragment.OnFragmentInteractionListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
				 

		// Set up the action bar to show a dropdown list.
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(
		// Specify a SpinnerAdapter to populate the dropdown list.
				new ArrayAdapter<String>(getActionBarThemedContextCompat(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, new String[] {
								getString(R.string.events),
								getString(R.string.groups),
								getString(R.string.profile), }), this);
	}

	/**
	 * Backward-compatible version of {@link ActionBar#getThemedContext()} that
	 * simply returns the {@link android.app.Activity} if
	 * <code>getThemedContext</code> is unavailable.
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private Context getActionBarThemedContextCompat() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return getActionBar().getThemedContext();
		} else {
			return this;
		}
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		Intent intent = null;
				
		switch(item.getItemId()){
			case R.id.log_out:
				intent = new Intent(this, LogOutActivity.class);
				startActivity(intent);
				finish();
				break;
			case R.id.action_settings:
				break;
				
			default:
				break;
				
		}
		
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
		
		Fragment fragment = null;
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		
		switch(position){
			case 0:
				fragment = EventFragment.newInstance();
				break;		
			case 1:
				fragment = GroupFragment.newInstance();
				break;
			case 2:				
				fragment = ProfileFragment.newInstance();
				break;
			default:
				fragment = GroupFragment.newInstance();
				break;
		}
		
		transaction.replace(R.id.container , fragment);
		transaction.commit();
		
		return true;
		
	}
	
	
	public void createGroup(){
		Intent intent = new Intent(this,CreateGroupActivity.class);
		startActivity(intent);
	}
	
	public void onFragmentInteraction(){
		Intent intent = new Intent(this, CreateGroupActivity.class);
		startActivity(intent);
	}
	
	public void onFragmentInteraction(String s){
		
	}
	
	public void onFragmentInteraction(Uri uri){
		
	}


}
