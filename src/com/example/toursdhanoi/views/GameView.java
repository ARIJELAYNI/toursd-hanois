package com.example.toursdhanoi.views;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.toursdhanoi.Disque;
import com.example.toursdhanoi.R;
import com.example.toursdhanoi.activities.CommencerJeuActivity;
import com.example.toursdhanoi.activities.DemoActivity;
import com.example.toursdhanoi.global.ParametresJeu;


public  class GameView extends SurfaceView {

	public GameView(Context context) {
		super(context);
		myContext = context;
		
		if(ParametresJeu.gameOver){
			//ParametresJeu.listeTour1 = this.buildDisques(new ArrayList<Disque>());
			ParametresJeu.disques = this.buildDisques(new ArrayList<Disque>());
			ParametresJeu.minimumMoves = (int) (Math.pow(2, ParametresJeu.gameLevel) ) - 1 ; 
			this.init();
			ParametresJeu.gameOver = false;
			
		}
		super.setKeepScreenOn(true);
		
		this.setBackgroundColor(Color.rgb(51, 71, 106));
		scale = myContext.getResources().getDisplayMetrics().density;
		greenPaint = new Paint(); 
		greenPaint.setAntiAlias(true); 
		greenPaint.setColor(Color.GREEN);
		greenPaint.setStyle(Paint.Style.STROKE);
		greenPaint.setTextAlign(Paint.Align.LEFT);
		greenPaint.setTextSize(scale*15);
		
		redPaint = new Paint(); 
		redPaint.setAntiAlias(true);
		redPaint.setColor(Color.RED);
		redPaint.setStyle(Paint.Style.STROKE);
		redPaint.setTextAlign(Paint.Align.LEFT);
		redPaint.setTextSize(scale*15);
	}
	
	/****Les attributs***************/
	
	protected Context myContext;
	
	protected int screenW;
	protected int screenH;
	//Paramètres d'écriture
	protected float scale;
	protected Paint greenPaint;
	protected Paint redPaint;
	
	
	protected Disque titleDisque;
	protected Disque[][] troisToursDeBase = new Disque[3][2];
	protected Disque retourDisque;
	protected Disque initialisationDisque;
	protected Disque resoudreDisque;
	protected Disque retourAuJeu = null;
	
	

	/*************** Méthodes de paramétrage de disques ***********************************/
	

	//Rédemarrer le jeu
		protected void init(){
			ParametresJeu.listeTour1.clear();
			ParametresJeu.listeTour1 = this.buildDisques(new ArrayList<Disque>());
			ParametresJeu.listeTour2.clear();
			ParametresJeu.listeTour3.clear();
			ParametresJeu.moves = 0;
			ParametresJeu.disqueAPlacer = null;
			ParametresJeu.indexPreContenantDuDisque = 0;
			ParametresJeu.movePossibleTo1 = 0;
			ParametresJeu.movePossibleTo2 = 0;
			ParametresJeu.moveImpossible = false;		
		}
	

	protected void retour(){
		

		if((ParametresJeu.indexPreContenantDuDisque > 0) && (ParametresJeu.retourPossible)){
			if(ParametresJeu.disqueAPlacer != null){
				ParametresJeu.disqueAPlacer = null;
				ParametresJeu.movePossibleTo1 = 0;
				ParametresJeu.movePossibleTo2 = 0;
			}
			else{
				if(ParametresJeu.destinationListeIndex == 1){
					if(ParametresJeu.indexPreContenantDuDisque == 2){
						this.fromListeToListe(ParametresJeu.listeTour1, ParametresJeu.listeTour2, ParametresJeu.listeTour3);
						ParametresJeu.moves--;
						ParametresJeu.retourPossible = false;
					}
					else if(ParametresJeu.indexPreContenantDuDisque == 3){
						this.fromListeToListe(ParametresJeu.listeTour1, ParametresJeu.listeTour3, ParametresJeu.listeTour2);
						ParametresJeu.moves--;
						ParametresJeu.retourPossible = false;
					}
				}
				if(ParametresJeu.destinationListeIndex == 2){
					if(ParametresJeu.indexPreContenantDuDisque == 1){
						this.fromListeToListe(ParametresJeu.listeTour2, ParametresJeu.listeTour1, ParametresJeu.listeTour3);
						ParametresJeu.moves--;
						ParametresJeu.retourPossible = false;
					}
					else if(ParametresJeu.indexPreContenantDuDisque == 3){
						this.fromListeToListe(ParametresJeu.listeTour2, ParametresJeu.listeTour3, ParametresJeu.listeTour1);
						ParametresJeu.moves--;
						ParametresJeu.retourPossible = false;
					}
				}
				if(ParametresJeu.destinationListeIndex == 3){
					if(ParametresJeu.indexPreContenantDuDisque == 1){
						this.fromListeToListe(ParametresJeu.listeTour3, ParametresJeu.listeTour1, ParametresJeu.listeTour2);
						ParametresJeu.moves--;
						ParametresJeu.retourPossible = false;
					}
					else if(ParametresJeu.indexPreContenantDuDisque == 2){
						this.fromListeToListe(ParametresJeu.listeTour3, ParametresJeu.listeTour2, ParametresJeu.listeTour1);
						ParametresJeu.moves--;
						ParametresJeu.retourPossible = false;
					}
				}
			}
		}
		else{
			return;
		}
	}

	public boolean onTouchEvent(MotionEvent event) { 
		
		int eventaction = event.getAction();
		int X = (int)event.getX();
		int Y = (int)event.getY();
		
		
		
		switch (eventaction ) {
			case MotionEvent.ACTION_DOWN:
				
				try{
				
				if((this.retourAuJeu != null) &&  (this.retourAuJeu.contientPoint(X, Y))){
					Intent intent = new Intent(myContext, CommencerJeuActivity.class);
					myContext.startActivity(intent);
				}
				
				if(this.titleDisque.contientPoint(X, Y)){
					Intent intent = new Intent(myContext, CommencerJeuActivity.class);
					myContext.startActivity(intent);
				}
				
				if(this.initialisationDisque.contientPoint(X, Y)){
					this.init();
				}


				if(this.retourDisque.contientPoint(X, Y)){
					this.retour();
				}
				
				if(this.resoudreDisque.contientPoint(X, Y)){

						Intent intent = new Intent(myContext, DemoActivity.class);
						myContext.startActivity(intent);
				}
				
				
				if(ParametresJeu.disqueAPlacer != null){
					
					ParametresJeu.disqueAPlacer.setXDebut(X);
					ParametresJeu.disqueAPlacer.setXDebut(Y);
					
					
					//si le disque à placer vient de Tour1 : 
					if(ParametresJeu.indexPreContenantDuDisque == 1){
						
						if(ParametresJeu.listeTour1.size() >0){
							if(ParametresJeu.listeTour1.get(0).contientPoint(X, Y)){
								//ParametresJeu.disqueAPlacer = null;
							}
						}
						
						boolean a = this.troisToursDeBase[1][0].contientPoint(X, Y);
						boolean b = this.troisToursDeBase[1][1].contientPoint(X, Y);
						boolean c = ParametresJeu.listeTour2.isEmpty() || ParametresJeu.disqueAPlacer.movePossible(ParametresJeu.listeTour2.get(0));
						
						boolean d = this.troisToursDeBase[2][0].contientPoint(X, Y);
						boolean e = this.troisToursDeBase[2][1].contientPoint(X, Y);
						boolean f = ParametresJeu.listeTour3.isEmpty() || ParametresJeu.disqueAPlacer.movePossible(ParametresJeu.listeTour3.get(0));
						
						if((a||b)&&(c)){
							this.fromListeToListe(ParametresJeu.listeTour1, ParametresJeu.listeTour2, ParametresJeu.listeTour3);
							this.placer();
							ParametresJeu.destinationListeIndex = 2;
						}				
						
						else if((d||e)&&(f)){
							this.fromListeToListe(ParametresJeu.listeTour1, ParametresJeu.listeTour3, ParametresJeu.listeTour2);
							this.placer();
							ParametresJeu.destinationListeIndex = 3;
						}
						
					}
					//si le disque à placer vient de Tour2 : 
					else if(ParametresJeu.indexPreContenantDuDisque == 2){
						try{
							boolean a = troisToursDeBase[0][0].contientPoint(X, Y);
							boolean b = troisToursDeBase[0][1].contientPoint(X, Y);
							boolean c = ParametresJeu.listeTour1.isEmpty() || ParametresJeu.disqueAPlacer.movePossible(ParametresJeu.listeTour1.get(0));
							
							boolean d = troisToursDeBase[2][0].contientPoint(X, Y);
							boolean e = troisToursDeBase[2][1].contientPoint(X, Y);
							boolean f = ParametresJeu.listeTour3.isEmpty() || ParametresJeu.disqueAPlacer.movePossible(ParametresJeu.listeTour3.get(0));
							
							if((a||b)&&(c)){
								this.fromListeToListe(ParametresJeu.listeTour2, ParametresJeu.listeTour1, ParametresJeu.listeTour3);
								this.placer();
								ParametresJeu.destinationListeIndex = 1;
							}
							else if((d||e)&&(f)){
								this.fromListeToListe(ParametresJeu.listeTour2, ParametresJeu.listeTour3, ParametresJeu.listeTour1);
								this.placer();
								ParametresJeu.destinationListeIndex = 3;
							}
							
						}
						catch(Exception e){
							//
						}
						
					}
					//si le disque à placer vient de Tour3 : 
					else if(ParametresJeu.indexPreContenantDuDisque == 3){
						boolean a = troisToursDeBase[0][0].contientPoint(X, Y);
						boolean b = troisToursDeBase[0][1].contientPoint(X, Y);
						boolean c = ParametresJeu.listeTour1.isEmpty() || ParametresJeu.disqueAPlacer.movePossible(ParametresJeu.listeTour1.get(0));
						
						boolean d = troisToursDeBase[1][0].contientPoint(X, Y);
						boolean e = troisToursDeBase[1][1].contientPoint(X, Y);
						boolean f = ParametresJeu.listeTour2.isEmpty() || ParametresJeu.disqueAPlacer.movePossible(ParametresJeu.listeTour2.get(0));
						
						if((a||b)&&(c)){
							this.fromListeToListe(ParametresJeu.listeTour3, ParametresJeu.listeTour1, ParametresJeu.listeTour2);
							this.placer();
							ParametresJeu.destinationListeIndex = 1;
						}
						
						else if((d||e)&&(f)){
							this.fromListeToListe(ParametresJeu.listeTour3, ParametresJeu.listeTour2, ParametresJeu.listeTour1);
							this.placer();
							ParametresJeu.destinationListeIndex = 2;
						}
					}
					
				}
				//Si le disque à placer est null c'est à dire : on commence un nouveau mouvement :
				else{
					if(ParametresJeu.listeTour1.size() >0){
						if(ParametresJeu.listeTour1.get(0).contientPoint(X, Y)){
							
							boolean a = ParametresJeu.listeTour2.isEmpty()||ParametresJeu.listeTour1.get(0).movePossible(ParametresJeu.listeTour2.get(0));
							boolean b = ParametresJeu.listeTour3.isEmpty()||ParametresJeu.listeTour1.get(0).movePossible(ParametresJeu.listeTour3.get(0));
							
							if(ParametresJeu.moveImpossible){
								ParametresJeu.moveImpossible = false;
							}
							if(a){
								ParametresJeu.movePossibleTo1 = 2;
							}
							if(b){
								ParametresJeu.movePossibleTo2 = 3;
							}
							if((!a) && (!b)){
								ParametresJeu.moveImpossible = true;
							}
							else{
								ParametresJeu.disqueAPlacer = ParametresJeu.listeTour1.get(0);
								ParametresJeu.indexPreContenantDuDisque = 1;
								ParametresJeu.lastDisque = ParametresJeu.listeTour1.get(0);
								ParametresJeu.retourPossible = true;
							}
							
						}
					}
					
					if(ParametresJeu.listeTour2.size() >0){
						if(ParametresJeu.listeTour2.get(0).contientPoint(X, Y)){
							
							boolean a = ParametresJeu.listeTour1.isEmpty()||ParametresJeu.listeTour2.get(0).movePossible(ParametresJeu.listeTour1.get(0));
							boolean b = ParametresJeu.listeTour3.isEmpty()||ParametresJeu.listeTour2.get(0).movePossible(ParametresJeu.listeTour3.get(0));
							
							if(ParametresJeu.moveImpossible){
								ParametresJeu.moveImpossible = false;
							}
							if(a){
								ParametresJeu.movePossibleTo1 = 1;
							}
							if(b){
								ParametresJeu.movePossibleTo2 = 3;
							}
							
							
							if((!a) && (!b)){
								ParametresJeu.moveImpossible = true;
							}
							else{
								ParametresJeu.disqueAPlacer = ParametresJeu.listeTour2.get(0);
								ParametresJeu.indexPreContenantDuDisque = 2;
								ParametresJeu.lastDisque = ParametresJeu.listeTour2.get(0);
								ParametresJeu.retourPossible = true;
								
							}
							
						}
					}
					
					if(ParametresJeu.listeTour3.size() >0){
						if(ParametresJeu.listeTour3.get(0).contientPoint(X, Y)){
							
							
							boolean a = ParametresJeu.listeTour1.isEmpty()||ParametresJeu.listeTour3.get(0).movePossible(ParametresJeu.listeTour1.get(0));
							boolean b = ParametresJeu.listeTour2.isEmpty()||ParametresJeu.listeTour3.get(0).movePossible(ParametresJeu.listeTour2.get(0));
							
							if(ParametresJeu.moveImpossible){
								ParametresJeu.moveImpossible = false;
							}
							if(a){
								ParametresJeu.movePossibleTo1 = 1;
							}
							if(b){
								ParametresJeu.movePossibleTo2 = 2;
							}
							if((!a) && (!b)){
								ParametresJeu.moveImpossible = true;
							}
							else{
								ParametresJeu.disqueAPlacer = ParametresJeu.listeTour3.get(0);
								ParametresJeu.indexPreContenantDuDisque = 3;
								ParametresJeu.lastDisque = ParametresJeu.listeTour3.get(0);
								ParametresJeu.retourPossible = true;
							}
							
						}
					}
				}
				
			break;
				}
				catch(Exception e){
					Intent intent = new Intent(myContext, CommencerJeuActivity.class);
					myContext.startActivity(intent);
				}
				
			case MotionEvent.ACTION_MOVE:
			break;
			case MotionEvent.ACTION_UP:
				
			break;
		}
		
		invalidate();
		return true;
  }


	protected void placer(){

		ParametresJeu.disqueAPlacer = null;
		ParametresJeu.movePossibleTo1 = 0;
		ParametresJeu.movePossibleTo2 = 0;
		ParametresJeu.moves++;
		ParametresJeu.retourPossible = true;
	}
	
	/*Transformer le premier disque de la liste l1 vers la première position dans la liste de disques l2 */
	void fromListeToListe(List<Disque> l1, List<Disque> l2, List<Disque> l3){	
		
		List<Disque> ds = new ArrayList<Disque>();
		
		for(int i=0; i<ParametresJeu.disques.size(); i++){
			if((!l2.contains(ParametresJeu.disques.get(i)))&&(!l3.contains(ParametresJeu.disques.get(i)))){
				ds.add(ParametresJeu.disques.get(i));			
			}
		}
		
		int a = ds.get(0).getId();
		/**/
		for(int i=0; i<ds.size(); i++){
			if(ds.get(i).getId() < a){
				a = ds.get(i).getId();
			}
		}
		
		l2.add(0, ParametresJeu.disques.get(a) );
		l1.remove(0);
	}
	
	//Méthode qui construit et retourne une liste de disques
	public List<Disque> buildDisques(List<Disque> dsqs){
		dsqs.clear();
		//Initialisation de la liste ...
		for(int i=0; i<ParametresJeu.gameLevel; i++){
			dsqs.add(new Disque());
		}
		for(int i=0; i<dsqs.size(); i++){
			String idString = "r" + (i+1);
			dsqs.get(i).setDisque(BitmapFactory.decodeResource(myContext.getResources(), this.getVariableName(idString)));
			dsqs.get(i).setDrawable(myContext.getResources().getDrawable(this.getVariableName(idString)));
			dsqs.get(i).setId(i);
		}
		return dsqs;
		
		
	}
	
	
	//Méthode qui récupére les id(s) des drawables à l'aide de leurs noms
	  public int getVariableName(String nomVariable){
		  
      	if (nomVariable.equals("ic_launcher")){
      		return R.drawable.ic_launcher;
      	}
      	else if (nomVariable.equals("r1")){
      		return R.drawable.r1;
      	}
      	else if (nomVariable.equals("r2")){
      		return R.drawable.r2;
      	}
      	else if (nomVariable.equals("r3")){
      		return R.drawable.r3;
      	}
      	else if (nomVariable.equals("r4")){
      		return R.drawable.r4;
      	}
      	else if (nomVariable.equals("r5")){
      		return R.drawable.r5;
      	}
      	else if (nomVariable.equals("r6")){
      		return R.drawable.r6;
      	}
      	else if (nomVariable.equals("r7")){
      		return R.drawable.r7;
      	}
      	else if (nomVariable.equals("r8")){
      		return R.drawable.r8;
      	}
      	else if (nomVariable.equals("tourw")){
      		return R.drawable.tourw;
      	}
      	else{
      		return R.drawable.tourh;
      	}
      }
	  
	  
	  

	  protected void buildListeTour1(Canvas canvas, Paint paint){
		  
		  ParametresJeu.listeTour1.get(ParametresJeu.listeTour1.size() - 1).moveOn(this.troisToursDeBase[0][0]);
		  for(int i=ParametresJeu.listeTour1.size() - 2; i>=0; i--){
			  ParametresJeu.listeTour1.get(i).moveOn(ParametresJeu.listeTour1.get(i+1));
		  }
		  drawDisques(ParametresJeu.listeTour1, canvas, paint);
	  }
	  
	  protected void buildListeTour2(Canvas canvas, Paint paint){
		  
		  ParametresJeu.listeTour2.get(ParametresJeu.listeTour2.size() - 1).moveOn(this.troisToursDeBase[1][0]);
		  for(int i=ParametresJeu.listeTour2.size() - 2; i>=0; i--){
			  ParametresJeu.listeTour2.get(i).moveOn(ParametresJeu.listeTour2.get(i+1));
		  }
		  this.drawDisques(ParametresJeu.listeTour2, canvas, paint);
	  }

	  protected void buildListeTour3(Canvas canvas, Paint paint){
		  
			  ParametresJeu.listeTour3.get(ParametresJeu.listeTour3.size() - 1).moveOn(this.troisToursDeBase[2][0]);
		  for(int i=ParametresJeu.listeTour3.size() - 2; i>=0; i--){
			  ParametresJeu.listeTour3.get(i).moveOn(ParametresJeu.listeTour3.get(i+1));
		  }
		  
		  this.drawDisques(ParametresJeu.listeTour3, canvas, paint);
	  }
	  
	  protected void drawDisque( Disque disque, Canvas canvas, Paint paint){
		  try{
			    disque.getDrawable().setBounds(disque.getXDebut(),disque.getYDebut(),(disque.getxDebut()+disque.getDisque().getWidth()),(disque.getYDebut()+disque.getDisque().getHeight()) ); 
			    disque.getDrawable().draw(canvas);
		  }
		  catch(NullPointerException e){
			  
		  }
		
	  }
	  
	  protected void drawDisques(List<Disque> Disques, Canvas canvas, Paint paint) {
			for(int i=0; i<Disques.size(); i++){
				this.drawDisque(Disques.get(i), canvas, paint);
			}
		}
	  
	  
	
}
