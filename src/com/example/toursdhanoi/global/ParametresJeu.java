package com.example.toursdhanoi.global;

import java.util.ArrayList;
import java.util.List;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.toursdhanoi.Disque;
import com.example.toursdhanoi.activities.MainActivity;
import com.example.toursdhanoi.views.GameView;

public class ParametresJeu {
	
	public static int gameLevel ;
	public static  List<Disque> disques = new ArrayList<Disque>();
	public static List<Disque> _disquesAlternative = new ArrayList<Disque>();
	public static List<Disque> listeTour1 = new ArrayList<Disque>();
	public static List<Disque> listeTour2 = new ArrayList<Disque>();
	public static List<Disque> listeTour3 = new ArrayList<Disque>();
	public static int destinationListeIndex = 0;
	public static Disque lastDisque;
	public static  int minimumMoves =  (int) (Math.pow(2, gameLevel) ) - 1 ; 
	public static Disque titleDisque;
	public static Disque disqueAPlacer = null;
	public static Disque[][] troisToursDeBase = new Disque[3][2];
	public static boolean gameOver = true;
	public static int moves = 0;
	public static int movePossibleTo1 = 0;
	public static int movePossibleTo2 = 0;
	public static int indexPreContenantDuDisque =0;
	public static boolean moveImpossible = false;
	public static boolean retourPossible = true;
		
	

}
