package com.example.geotrack;

import android.text.format.Time;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;


/*
 * Class for storing the geopoints.
 */
public class Items {
	private GeoPoint point;
	private OverlayItem overlayitem;
	private String time;
	/*
	 * Constructure of the GeoPoints and Time
	 */
	public Items(double lat, double lng) {
		point = new GeoPoint((int)(lat*1E6), (int)(lng*1E6));
		overlayitem = new OverlayItem(point, "Latitude: " +  Double.toString(lat), "Longitude: " + Double.toString(lng));
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();
        StringBuilder sb = new StringBuilder(Integer.toString(today.monthDay));    
        sb.append(".");
        sb.append(Integer.toString(today.month + 1));
        sb.append(".");
        sb.append(Integer.toString(today.year));
        sb.append(" ");
        sb.append(Integer.toString(today.hour));
        sb.append(":");
        sb.append(Integer.toString(today.minute));
        sb.append(":");
        sb.append(Integer.toString(today.second));
        time = sb.toString();
        
	}
	public GeoPoint getPoint() {
		
		return point;
	}
	public int getLongitudeE6() {
		
		return point.getLongitudeE6();
	}
	public int getLatitudeE6() {
		return point.getLatitudeE6();
	}
	public OverlayItem getOverlayItem(){
		return overlayitem;
		
	}
	
	public String getTime(){
		return time;
	}
}
