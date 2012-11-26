package com.example.geotrack;

import java.util.List;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;

/**
 * Class for showing the mapview to the user.
 */
public class GeotrackMap extends MapActivity {

	private MapController myMapController;
	private static List<Overlay> mapOverlays;
	private static final ItemCollection collection = new ItemCollection();;
	private LocationManager mLocationManager;
	private ItemsOverlay itemsoverlay;
	private MapView mapView;
	/*
	 * Location listener for the GPS-signal.
	 */
	private final LocationListener gpsLocationListener = new LocationListener() {

		/*
		 * Called when location is changed.
		 */
		public void onLocationChanged(Location location) {
			// mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

			collection.newItem(location.getLatitude(), location.getLongitude());
			myMapController.animateTo(collection.getPoint(collection.size() - 1));
			itemsoverlay.addOverlay(collection.getOverlayItem(collection.size() - 1));
			mapOverlays.add(itemsoverlay);
		}

		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_geotrack_map);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_geotrack_map, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		mLocationManager.removeUpdates(gpsLocationListener);

	}

	@Override
	protected void onResume() {
		super.onResume();
		String context = Context.LOCATION_SERVICE;
		mLocationManager = (LocationManager) getSystemService(context);

		// The location is updated every second if it has changed more than 10 meters.
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				1000, 10, (LocationListener) gpsLocationListener);

		mapView = (MapView) findViewById(R.id.mapview);

		mapView.setBuiltInZoomControls(true);
		mapOverlays = mapView.getOverlays();

		Drawable drawable = this.getResources().getDrawable(
				R.drawable.ic_launcher);
		itemsoverlay = new ItemsOverlay(drawable, this);
		myMapController = mapView.getController();
		Bundle extras = getIntent().getExtras();

		// Intents come from either Splash-activity or from the list of visited
		// locations.
		// Here the intents extra-fields are checked for the desired
		// information.
		if (extras == null) {
			return;
		} else {

			// Intents from the list-view are checked here.
			if (extras.getString("index") != null) {
				myMapController.setZoom(10);
				int i = Integer.parseInt(extras.getString("index"));
				myMapController.animateTo(collection.getPoint(i));
				itemsoverlay.addOverlay(collection.getOverlayItem(i));
				mapOverlays.add(itemsoverlay);
			} else {
				// Intents from Splash-activity contain the location information
				// of the received GPS-signal.
				double lat = extras.getDouble("latitude");
				double lng = extras.getDouble("longitude");
				collection.newItem(lat, lng);
				extras = null;
			}

		}

		// The visited locations are drawn to the map.
		for (int i = 0; i < collection.size(); i++) {

			itemsoverlay.addOverlay(collection.getOverlayItem(i));
			mapOverlays.add(itemsoverlay);
		}
		myMapController.animateTo(collection.getPoint(collection.size() - 1));
		
	}

	/**
	 * Method for getting the visited locations.
	 * 
	 * @return Collection of visited locations
	 */
	public static ItemCollection getList() {

		return collection;

	}

	/**
	 * Called when listview-button is pressed.
	 * 
	 * @param view
	 */
	public void listviewClicked(View view) {
		Intent i = new Intent(this, ListOfPlaces.class);
		startActivity(i);
	}

}
