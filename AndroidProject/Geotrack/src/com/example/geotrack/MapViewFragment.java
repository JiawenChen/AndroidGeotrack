package com.example.geotrack;






import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/*
 * Class for showing the main menu.
 */
public class MapViewFragment extends ListFragment{
	OnHeadlineSelectedListener mCallback;
	public interface OnHeadlineSelectedListener {
		public void onViewSelected(int position);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
	                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
	        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Caption.Headlines));
	}
	
	 @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);

	        // This makes sure that the container activity has implemented
	        // the callback interface. If not, it throws an exception.
	        try {
	            mCallback = (OnHeadlineSelectedListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement OnHeadlineSelectedListener");
	        }
	    }
	
	
	  @Override
	    public void onStart() {
	        super.onStart();
	        
	  }
	  @Override
	    public void onListItemClick(ListView l, View v, int position, long id) {
		  mCallback.onViewSelected(position);
	  }
}
