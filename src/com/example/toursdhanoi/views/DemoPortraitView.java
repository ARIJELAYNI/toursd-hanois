package com.example.toursdhanoi.views;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.toursdhanoi.Disque;
import com.example.toursdhanoi.activities.JouerActivity;
import com.example.toursdhanoi.global.ParametresJeu;

public class DemoPortraitView extends JouerPortraitView implements
		SurfaceHolder.Callback {

	SurfaceHolder mSurfaceHolder;
	// Le thread dans lequel le dessin se fera
	DrawingThread mThread;

	public DemoPortraitView(Context context) {

		super(context);
		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
		mThread = new DrawingThread(mSurfaceHolder, myContext, this);
		this.init();
	}

	@Override
	protected void onDraw(Canvas canvas) {

		intialiserDisquesBases();
		drawDisquesBases(canvas, greenPaint);

		// super.buildListeTour1(canvas, greenPaint);

		if (ParametresJeu.listeTour1.size() > 0) {
			this.buildListeTour1(canvas, greenPaint);
		}
		/**/
		if (ParametresJeu.listeTour2.size() > 0) {
			this.buildListeTour2(canvas, greenPaint);
		}
		if (ParametresJeu.listeTour3.size() > 0) {
			this.buildListeTour3(canvas, greenPaint);
		}

	}

	public boolean onTouchEvent(MotionEvent event) {

		int eventaction = event.getAction();
		int X = (int) event.getX();
		int Y = (int) event.getY();

		switch (eventaction) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:

			break;
		}

		invalidate();
		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

		screenW = width;
		screenH = height;

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		try {
			mThread = new DrawingThread(mSurfaceHolder, myContext, this);
			mThread.mRun = true;
			mThread.start();
		} catch (Exception e) {
			Intent intent = new Intent(myContext, JouerActivity.class);
			myContext.startActivity(intent);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}

	public final class DrawingThread extends Thread {

		boolean mRun;

		Canvas mcanvas;

		SurfaceHolder surfaceHolder;

		Context context;

		DemoPortraitView msurfacePanel;

		public DrawingThread(SurfaceHolder sholder, Context ctx,
				DemoPortraitView spanel)

		{

			surfaceHolder = sholder;

			context = ctx;

			mRun = false;

			msurfacePanel = spanel;

		}

		void setRunning(boolean bRun)

		{

			mRun = bRun;

		}

		// Utilisé pour arrêter le dessin quand il le faut
		boolean keepDrawing = true;

		@Override
		public void run() {
			while (keepDrawing) {
				Canvas canvas = null;
				try {
					// On récupère le canvas pour dessiner dessus
					canvas = mSurfaceHolder.lockCanvas();
					// On s'assure qu'aucun autre thread n'accède au holder
					synchronized (mSurfaceHolder) {
						// Et on dessine
						draw(canvas);
					}
				} catch (Exception e) {
					Intent intent = new Intent(myContext, JouerActivity.class);
					myContext.startActivity(intent);
				} finally {
					// Notre dessin fini, on relâche le Canvas pour que le
					// dessin s'affiche
					if (canvas != null)
						mSurfaceHolder.unlockCanvasAndPost(canvas);
				}
				//
				if (ParametresJeu.listeTour3.size() != ParametresJeu.gameLevel) {
					resoudre(
							ParametresJeu.listeTour1.get(
									ParametresJeu.listeTour1.size() - 1)
									.getId(), ParametresJeu.listeTour1,
							ParametresJeu.listeTour2, ParametresJeu.listeTour3);
				} else {
					try {
						this.join();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		public void resoudre(int first_disc, List<Disque> aTower,
				List<Disque> bTower, List<Disque> cTower) {
			if (first_disc == 0) {
				fromListeToListe(aTower, cTower, bTower);
				this.attend();
			} else {
				resoudre(first_disc - 1, aTower, cTower, bTower);
				fromListeToListe(aTower, cTower, bTower);
				this.attend();
				resoudre(first_disc - 1, bTower, aTower, cTower);
			}
		}

		public void attend() {

			try {
				this.sleep(1000);
				postInvalidate();
			} catch (InterruptedException e) {

			}
		}
	}
}
