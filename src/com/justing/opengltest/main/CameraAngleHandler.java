package com.justing.opengltest.main;

public class CameraAngleHandler {
	
	private float x = 0, y = 0, cx = 0, cy = 0;
	private boolean run = false;
	Thread t = null;
	private ArrowActionContainer aac = ArrowActionContainer.getInstance();
	private final static CameraAngleHandler Instance = new CameraAngleHandler();
	
	public static final CameraAngleHandler getInstance() {
		return Instance;
	}

	private CameraAngleHandler(){
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (run){
					x += (float)(aac.getDx() * 2.0f);
					y += (float)(aac.getDy() * 2.0f);
					cx += (float)(aac.getEx() * 0.05f);
					cy += (float)(aac.getEy() * 0.05f);
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	public void startThread(){
		run = true;
		t.start();
	}
	public void stopThread(){
		run = false;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getCx() {
		return cx;
	}

	public float getCy() {
		return cy;
	}
	
}
