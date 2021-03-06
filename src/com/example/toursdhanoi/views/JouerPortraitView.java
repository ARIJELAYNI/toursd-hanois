package com.example.toursdhanoi.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.toursdhanoi.Disque;
import com.example.toursdhanoi.R;
import com.example.toursdhanoi.global.ParametresJeu;

public class JouerPortraitView extends GameView {

	public JouerPortraitView(Context context) {
		super(context);
	}

	//Dessin de la view en mode Portrait
	@Override
	protected void onDraw(Canvas canvas) { 
		
		if(ParametresJeu.listeTour3.size() == ParametresJeu.gameLevel){

			canvas.drawText("Félicitations, vous avez gagné !!!", (int)(screenW*0.2), screenH/2, super.greenPaint);
			this.retourAuJeu = new Disque(BitmapFactory.decodeResource(myContext.getResources(), R.drawable.retour_button), 10, (int)(screenW*0.1), (int)(screenH*0.6));
			canvas.drawBitmap(this.retourAuJeu.getDisque(), (int)(screenW*0.2), (int)(screenH*0.6), super.greenPaint);
		}
		else{
			canvas.drawText("Mouvements : " + ParametresJeu.moves, 10, super.greenPaint.getTextSize()+110, super.greenPaint);
			
			if((ParametresJeu.movePossibleTo1!= 0)&&(ParametresJeu.movePossibleTo2 != 0)){
				canvas.drawText("Mouvement possible vers : " + ParametresJeu.movePossibleTo1 + " et " + ParametresJeu.movePossibleTo2, 10, super.greenPaint.getTextSize()+140, super.greenPaint);
			}
			if((ParametresJeu.movePossibleTo1 != 0)&&(ParametresJeu.movePossibleTo2 == 0)){
				canvas.drawText("Mouvement possible vers  : " + ParametresJeu.movePossibleTo1, 10, super.greenPaint.getTextSize()+140, super.greenPaint);
			}
			if((ParametresJeu.movePossibleTo1 == 0)&&(ParametresJeu.movePossibleTo2 != 0)){
				canvas.drawText("Mouvement possible vers  : " + ParametresJeu.movePossibleTo2, 10, super.greenPaint.getTextSize()+140, super.greenPaint);
			}
			
			if(ParametresJeu.moveImpossible){
				canvas.drawText("Mouvement impossible ", 10, super.greenPaint.getTextSize()+140, redPaint);
			}
			
			
			if(ParametresJeu.disqueAPlacer != null){
				super.drawDisque(ParametresJeu.disqueAPlacer, canvas, super.greenPaint);
			}
			canvas.drawText("Minumum de mouvements : " + ParametresJeu.minimumMoves, 10, super.greenPaint.getTextSize()+80, super.greenPaint);
			intialiserDisquesBases();
			drawDisquesBases(canvas, super.greenPaint);
			
			if(ParametresJeu.listeTour1.size() >0 ){
				//this.drawDisques(ParametresJeu.listeTour1, canvas, greenPaint);
				this.buildListeTour1(canvas, super.greenPaint);
			}
			if(ParametresJeu.listeTour2.size() >0){
				this.buildListeTour2(canvas, super.greenPaint);
			}
			if(ParametresJeu.listeTour3.size() >0){
				this.buildListeTour3(canvas, super.greenPaint);
			}
		}
	}
	
	
	
	public void onSizeChanged (int w, int h, int oldw, int oldh){ 
		super.onSizeChanged(w, h, oldw, oldh);
		screenW = w;
		screenH = h;
		
	}
	
	

	  protected void intialiserDisquesBases(){
		  
		  for(int i=0; i<3; i++){
			  troisToursDeBase[i][0] = new Disque();
			  troisToursDeBase[i][0].setDisque(BitmapFactory.decodeResource(myContext.getResources(), R.drawable.tourw));
			  troisToursDeBase[i][0].setDrawable(myContext.getResources().getDrawable(R.drawable.tourw));
			  troisToursDeBase[i][1] = new Disque();
			  troisToursDeBase[i][1].setDisque(BitmapFactory.decodeResource(myContext.getResources(), R.drawable.tourh));
			  troisToursDeBase[i][1].setDrawable(myContext.getResources().getDrawable(R.drawable.tourh));
		  }
		  
		  titleDisque = new Disque();

		  titleDisque.setDisque(BitmapFactory.decodeResource(myContext.getResources(), R.drawable.title));
		  titleDisque.setDrawable(myContext.getResources().getDrawable(R.drawable.title));
		  
		  retourDisque = new Disque();
		  initialisationDisque = new Disque();
		  resoudreDisque = new Disque();
		  
		  retourDisque.setDisque(BitmapFactory.decodeResource(myContext.getResources(), R.drawable.retour_button));
		  retourDisque.setDrawable(myContext.getResources().getDrawable(R.drawable.retour_button));
		  
		  initialisationDisque.setDisque(BitmapFactory.decodeResource(myContext.getResources(), R.drawable.init_button));
		  initialisationDisque.setDrawable(myContext.getResources().getDrawable(R.drawable.init_button));
		  
		  resoudreDisque.setDisque(BitmapFactory.decodeResource(myContext.getResources(), R.drawable.demo_button));
		  resoudreDisque.setDrawable(myContext.getResources().getDrawable(R.drawable.demo_button));
	  }
	  
	  protected void drawDisquesBases(Canvas canvas, Paint paint){
		  
		  	int buttomHeight = (int) (screenH * 0.65) - 50; 
			int centerTowerLeft = (int)screenW/2 - (int)(troisToursDeBase[0][0].getDisque()).getWidth()/2;		
			int leftTowerLeft = (int)((centerTowerLeft - (troisToursDeBase[0][0].getDisque()).getWidth())/2 );		
			int rightTowerLeft = (int)screenW/2 + (int)(troisToursDeBase[0][0].getDisque()).getWidth()/2 + leftTowerLeft;
			
			titleDisque.setxDebut(leftTowerLeft);
			titleDisque.setyDebut((int)super.greenPaint.getTextSize()-10);
			drawDisque(titleDisque, canvas, paint);
			
			int buttonHeight = screenH -220;
			
			retourDisque.setXDebut(leftTowerLeft);
			retourDisque.setYDebut(buttonHeight);
			
			initialisationDisque.setXDebut(leftTowerLeft);
			initialisationDisque.setYDebut(buttonHeight +70);
			
			resoudreDisque.setXDebut(leftTowerLeft);
			resoudreDisque.setYDebut(buttonHeight +140);
			
			this.drawDisque(retourDisque, canvas, paint);
			this.drawDisque(initialisationDisque, canvas, paint);
			this.drawDisque(resoudreDisque, canvas, paint);
			
			troisToursDeBase[0][0].setXDebut(leftTowerLeft);
			troisToursDeBase[0][0].setYDebut(buttomHeight);
			troisToursDeBase[0][1].moveOn(troisToursDeBase[0][0]);
			
			this.drawDisque(troisToursDeBase[0][0], canvas, paint);
			this.drawDisque(troisToursDeBase[0][1], canvas, paint);
			
			troisToursDeBase[1][0].setXDebut(centerTowerLeft);
			troisToursDeBase[1][0].setYDebut(buttomHeight);
			troisToursDeBase[1][1].moveOn(troisToursDeBase[1][0]);
			
			this.drawDisque(troisToursDeBase[1][0], canvas, paint);
			this.drawDisque(troisToursDeBase[1][1], canvas, paint);
			
			troisToursDeBase[2][0].setXDebut(rightTowerLeft);
			troisToursDeBase[2][0].setYDebut(buttomHeight);
			troisToursDeBase[2][1].moveOn(troisToursDeBase[2][0]);
			
			this.drawDisque(troisToursDeBase[2][0], canvas, paint);
			this.drawDisque(troisToursDeBase[2][1], canvas, paint);
	  }
	  
protected void drawDisque( Disque disque, Canvas canvas, Paint paint){
	
		  try{
			  
			  	if(disque == this.titleDisque){
			  		disque.getDrawable().setBounds(disque.getXDebut(),disque.getYDebut(), screenW-10,(disque.getYDebut()+disque.getDisque().getHeight()) ); 
			  		disque.getDrawable().draw(canvas);
			  	}
			  	else if(disque == this.retourDisque){
			  		disque.getDrawable().setBounds(disque.getXDebut(),disque.getYDebut(), screenW-10,(disque.getYDebut()+disque.getDisque().getHeight()) ); 
			  		disque.getDrawable().draw(canvas);			  		
			  	}
			  	else if(disque == this.initialisationDisque){
			  		disque.getDrawable().setBounds(disque.getXDebut(),disque.getYDebut(), screenW-10,(disque.getYDebut()+disque.getDisque().getHeight()) ); 
			  		disque.getDrawable().draw(canvas);
			  	}
			  	else if(disque == this.resoudreDisque){
			  		disque.getDrawable().setBounds(disque.getXDebut(),disque.getYDebut(), screenW-10,(disque.getYDebut()+disque.getDisque().getHeight()) ); 
			  		disque.getDrawable().draw(canvas);
			  	}
			  	else{
			  		disque.getDrawable().setBounds(disque.getXDebut(),disque.getYDebut(),(disque.getxDebut()+disque.getDisque().getWidth()),(disque.getYDebut()+disque.getDisque().getHeight()) ); 
			  		disque.getDrawable().draw(canvas);
			  	}
		  }
		  catch(NullPointerException e){
			  
		  }
		
	  }
	
}
