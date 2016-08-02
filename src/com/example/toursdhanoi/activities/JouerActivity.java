package com.example.toursdhanoi.activities;

import com.example.toursdhanoi.R;
import com.example.toursdhanoi.R.layout;
import com.example.toursdhanoi.R.menu;
import com.example.toursdhanoi.global.ParametresJeu;
import com.example.toursdhanoi.views.JouerLandscapeView;
import com.example.toursdhanoi.views.JouerPortraitView;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class JouerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		
		JouerLandscapeView jlnd = new JouerLandscapeView(this);
		JouerPortraitView jprtr = new JouerPortraitView(this);

		
	      Configuration config = getResources().getConfiguration();
	      
	      if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			        setContentView(jlnd ); 
	      }
	      else {
			    setContentView(jprtr); 
	      }
	      
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jouer, menu);
		return true;
	}

}
