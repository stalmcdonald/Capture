/*
 * Crystal McDonald
 * MDF3
 * 1310
 * Week 2
 * 
 * Capture: Video Player Project
 */
package com.cm.capture;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;


public class MainActivity extends Activity {

	//global variables
	String stopStart;
	MediaPlayer mediaPlay;
	Button SS;
	VideoView vidView;
	Context _context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//set context
		_context = this;
		
		//add music/sound to media player
		mediaPlay = MediaPlayer.create(MainActivity.this, R.raw.soundfile);
		
		//Set button
		SS = (Button) findViewById(R.id.button1);
		stopStart = "Play";
		
		//start button 1 listener
		SS.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				//starting and stopping the audio
				if(stopStart.equals("Play")){
					mediaPlay.start();
					stopStart = "Pause";
					SS.setText(stopStart);
				}else {
					mediaPlay.pause();
					stopStart = "Play";//changes text back to play
					SS.setText(stopStart);
					
				}
			}
		});
		
		//Set SS text to play initially
		SS.setText(stopStart);
		Button Record = (Button) findViewById(R.id.btnRecordVideo);
		Record.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// Set and Play Video
				vidView = (VideoView) findViewById(R.id.videoView1);
				//create a path for video
				String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
				vidView.setVideoURI(Uri.parse(uriPath));
				vidView.setMediaController(new MediaController(_context));  //allows to have control video features
				vidView.start();
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
