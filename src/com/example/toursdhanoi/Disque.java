package com.example.toursdhanoi;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Disque implements Cloneable{
	
	private Drawable drawable;

	public Drawable getDrawable() {
		return drawable;
	}
	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}

	private Bitmap disque;
	public int getxDebut() {
		return xDebut;
	}
	public void setxDebut(int xDebut) {
		this.xDebut = xDebut;
	}
	public int getyDebut() {
		return yDebut;
	}
	public void setyDebut(int yDebut) {
		this.yDebut = yDebut;
	}

	private int id;
	private int xDebut;
	private int yDebut;
	
	
	public Bitmap getDisque() {
		return disque;
	}
	public void setDisque(Bitmap disque) {
		this.disque = disque;
	}
	public int getId() {
		return id;
	}
	public Disque(Bitmap disque, int id, int xDebut, int yDebut) {
		super();
		this.disque = disque;
		this.id = id;
		this.xDebut = xDebut;
		this.yDebut = yDebut;
	}
	public Disque() {
		// TODO Auto-generated constructor stub
	}
	public int getXDebut() {
		return xDebut;
	}
	public void setXDebut(int xDebut) {
		this.xDebut = xDebut;
	}
	public int getYDebut() {
		return yDebut;
	}
	public void setYDebut(int yDebut) {
		this.yDebut = yDebut;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean movePossible(Disque disque){
		return (disque.getDisque().getWidth() >= this.getDisque().getWidth());
	}
	
	public boolean moveOn(Disque disque){
		
		if(!movePossible(disque)){
			return false;
		}
		else{
			this.setXDebut(disque.getXDebut() + ((int)(disque.getDisque().getWidth()/2)) - ((int)(this.getDisque().getWidth()/2)));
			this.setYDebut(disque.getYDebut() - 3 - this.getDisque().getHeight());
			return true;
		}
	}
	
	public boolean contientPoint(int x, int y){
		
		return	((this.xDebut<=x)
				&&
				(x <	(this.xDebut + this.disque.getWidth())		)
				&&
				(this.yDebut<=y)
				&&
				(y <	(this.yDebut + this.disque.getHeight())		));
	}
}
