/*
 * Crystal McDonald
 * MDF3
 * 1310
 * Week 2
 * 
 * Capture: Video PlayBack Project
 * Capture is a government run program that informs you that the user is being watched
 */
package com.cm.capture;

import android.media.MediaPlayer;
import android.media.RingtoneManager;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;


@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
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
		// listener handler 
		//set up a switch in case I want to add more functionality later
	    View.OnClickListener handler = new View.OnClickListener(){
	        public void onClick(View v) {
	            
	            switch (v.getId()) {

	                case R.id.confirmBttn:
	                	notifyConfirmation();
	                    break;
	            }
	        }
	    };
	        
	    // setting the notification listener
	    findViewById(R.id.confirmBttn).setOnClickListener(handler);

	}
	    public void notifyConfirmation(){

			// gets users default message ringtone to play for notification
			Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			
			// intent set up to show more information and possible location information for processing to be added later
			Intent intent = new Intent(MainActivity.this, ProcessorLocationActivity.class);
			PendingIntent processingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
			//disabled the start activity here. Decided its better for the user to click the notification for processing completion 
			//startActivity(intent);
			
			// Creating Notification
			// Processes the user.  User is aware of SisterSeesYou and the Capture program: Marital Law
			Notification processNotification = new Notification.Builder(this)
				
				.setContentTitle("Message from SisterSeesYou")
				.setContentText("Citizen Accounted for Successfully.")
				.setSmallIcon(R.drawable.ssy)
				.setContentIntent(processingIntent)
				.setSound(soundUri)			
				.build();
			
			NotificationManager confirmationNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			
			confirmationNotification.notify(0, processNotification);
		}
		


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
