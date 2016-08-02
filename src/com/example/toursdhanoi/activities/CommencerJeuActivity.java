package com.example.toursdhanoi.activities;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.toursdhanoi.R;
import com.example.toursdhanoi.global.ParametresJeu;

public class CommencerJeuActivity extends Activity {


	private Spinner spinner;
	private Button btnSubmit;
	private ImageView titre;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commencer_jeu);
		

		addItemsOnSpinner2();
		addListenerOnButton();
		addListenerOnSpinnerItemSelection();
		titre = (ImageView)this.findViewById(R.id.imageView1);
		titre.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(CommencerJeuActivity.this, MainActivity.class);
				CommencerJeuActivity.this.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.commencer_jeu, menu);
		return true;
	}



	//add items into spinner dynamically
	public void addItemsOnSpinner2() {

		spinner = (Spinner) findViewById(R.id.spinner);
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
	}

	public void addListenerOnSpinnerItemSelection(){
		
		spinner = (Spinner) findViewById(R.id.spinner);
		//spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}
	
	//get the selected dropdown list value
	public void addListenerOnButton() {

		spinner = (Spinner) findViewById(R.id.spinner);
		
		btnSubmit = (Button) findViewById(R.id.btnSubmit);

		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

					Intent intent = new Intent(CommencerJeuActivity.this, JouerActivity.class);
					ParametresJeu.gameLevel = Integer.parseInt(String.valueOf(spinner.getSelectedItem()));     
					ParametresJeu.gameOver = true;
					startActivity(intent);
			}

		});

	}

}
