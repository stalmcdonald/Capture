package com.cm.capture;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class ProcessorLocationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView tv = new TextView(this);
		tv.setText("If you feel like you have been processed in error please go to the nearest facility for updates.");
		
		setContentView(tv);
	}
}
