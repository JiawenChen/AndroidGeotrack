package com.example.geotrack;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
/*
 * Class for the splash screen.
 * */
@SuppressLint("NewApi")
public class Splash extends Activity {
	private LocationManager mLocationManager;
	private final LocationListener gpsLocationListener = new LocationListener() {
		
		public void onLocationChanged(Location location) {
			Intent i = new Intent(Splash.this, GeotrackMap.class);
			i.putExtra("latitude", location.getLatitude());    // The location parameters are sent with the intent.
			i.putExtra("longitude", location.getLongitude());
			startActivity(i);
			
			finish(); // Kill the activity when location is found.
			
		}

		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		public void onProviderEnabled(String provider) {
			

		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			
		}

	};
	
 

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar1);
        pb.animate();
        
    }
    
    @Override
	protected void onResume() {
		super.onResume();
		String context = Context.LOCATION_SERVICE;
        mLocationManager = (LocationManager) getSystemService(context);

		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				0, 0, (LocationListener) gpsLocationListener);
    }

    @Override
	protected void onPause() {
		super.onPause();
		mLocationManager.removeUpdates(gpsLocationListener);

	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_splash, menu);
        return true;
    }
}
