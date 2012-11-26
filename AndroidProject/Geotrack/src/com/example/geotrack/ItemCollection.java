package com.example.geotrack;

import java.util.ArrayList;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;


/*
 * Class for Collection of GeoPoints
 * 
*/
public class ItemCollection {
	
	private ArrayList<Items> list = new ArrayList<Items>();
/*
 * Dummy constructor for the collection.
 */
	public ItemCollection() {
		
	}
/*
 * Called when a geopoint is created.
 */
	public void newItem(double lat, double lng) {
		Items point = new Items(lat, lng);
		list.add(point);
	}
	
	public GeoPoint getPoint(int i){
		return list.get(i).getPoint();
	}
	
	public int getLng(int i){
		return list.get(i).getLongitudeE6();
	}
	
	public int getLat(int i){
		return list.get(i).getLatitudeE6();
	}
	public OverlayItem getOverlayItem(int i){
		return list.get(i).getOverlayItem();
	}
	public int size(){
		return list.size();
	}
	public String getTime(int i){
		return list.get(i).getTime();
	}
}
