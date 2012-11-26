package com.example.geotrack;

import com.example.geotrack.MapViewFragment.OnHeadlineSelectedListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

/**
 * 
 * Main menu of the application.
 *
 */
public class MainActivity extends FragmentActivity implements
		OnHeadlineSelectedListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (findViewById(R.id.fragment_container) != null) {
			if (savedInstanceState != null) {
				return;
			}
			MapViewFragment firstFragment = new MapViewFragment();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.fragment_container, firstFragment).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * Method for calling the correct activity.
	 */
	public void onViewSelected(int position) {
		// Calling the activities based on the element clicked.
		if (position == 0){
			Intent i = new Intent(this, Splash.class);
			startActivity(i);
		}
		if (position == 1) {
			Intent i = new Intent(this, ListOfPlaces.class);
			startActivity(i);
		}
	}
}
