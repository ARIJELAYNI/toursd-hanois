package com.example.toursdhanoi.activities;

import com.example.toursdhanoi.R;
import com.example.toursdhanoi.R.menu;
import com.example.toursdhanoi.global.ParametresJeu;
import com.example.toursdhanoi.views.DemoLandscapeView;
import com.example.toursdhanoi.views.DemoPortraitView;
import com.example.toursdhanoi.views.JouerLandscapeView;
import com.example.toursdhanoi.views.JouerPortraitView;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class DemoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	      Configuration config = getResources().getConfiguration();
	      
	      if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	    	  setContentView(new DemoLandscapeView(this));
	      }
	      else{
	    	  setContentView(new DemoPortraitView(this));
	      }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.demo, menu);
		return true;
	}

}
