
/*
 * Crystal McDonald
 * MDF3
 * 1310
 * Week 2
 * 
 * Capture: Video PlayBack Project
 * Gathering users location for tracking.
 */package com.cm.capture;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


public class ProcessorLocationActivity extends Activity {

	//creating accessible references for lat/lon
	TextView textLat;
	TextView textLong;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);

		//toast set up to confirm video has been watched and confirmed by user.
		Context context = getApplicationContext();
		CharSequence text = "Video has been watched and confirmed.";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		
		//getting text view objects by id
		tv = (TextView)findViewById(R.id.tv);
//		textLat = (TextView)findViewById(R.id.textLat);
//		textLong = (TextView)findViewById(R.id.textLong);
		
		 LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		    LocationListener ll = new mylocationlistener();
		    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
		    }

		    private class mylocationlistener implements LocationListener {
		    @Override
		    public void onLocationChanged(Location location) {
		        if (location != null) {
		        Log.d("LOCATION CHANGED", location.getLatitude() + "");
		        Log.d("LOCATION CHANGED", location.getLongitude() + "");
		        
		        Toast.makeText(ProcessorLocationActivity.this,
		            location.getLatitude() + "" + location.getLongitude(),
		            Toast.LENGTH_LONG).show();
		        }
		    }
		    @Override
		    public void onProviderDisabled(String provider) {
		    }
		    @Override
		    public void onProviderEnabled(String provider) {
		    }
		    @Override
		    public void onStatusChanged(String provider, int status, Bundle extras) {
		    }
		    
		

	
	}//end of OnCreate
}//end
