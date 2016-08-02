package com.example.toursdhanoi.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.toursdhanoi.R;
import com.example.toursdhanoi.global.ParametresJeu;

public class MainActivity extends Activity {

	Button jouer;
	Button instructions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.activity_main);

		Button jouer = (Button) this.findViewById(R.id.jouer_button);
		Button instructions = (Button) this.findViewById(R.id.instruct_button);

		jouer.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						CommencerJeuActivity.class);
				ParametresJeu.gameOver = true;
				startActivity(intent);
			}
		});

		instructions.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						InstructionsActivity.class);
				ParametresJeu.gameOver = true;
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
