package com.example.geotrack;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * Class for creating a list of the geopoints.
 */
public class ListOfPlaces extends Activity {
	private ListView mainListView;
	private ArrayAdapter<String> listAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_of_places);
		mainListView = (ListView) findViewById(R.id.mainListView);
		ItemCollection list = GeotrackMap.getList();
		ArrayList<String> placeList = new ArrayList<String>();
		if (list != null) {
			// Fetches the information of the geopoints.
			for (int i = 0; i < list.size(); i++) {
				placeList.add(Integer.toString(list.getLat(i)) + ", "
						+ Integer.toString(list.getLng(i)) + ", " + list.getTime(i));
			}
		}
		listAdapter = new ArrayAdapter<String>(this, R.layout.simple_ro,placeList);
		mainListView.setAdapter(listAdapter);

		mainListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) { 
				
					Intent i = new Intent(ListOfPlaces.this, GeotrackMap.class);
					i.putExtra("index", Integer.toString(arg2));
					startActivity(i);
				
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_list_of_places, menu);
		return true;
	}

}
