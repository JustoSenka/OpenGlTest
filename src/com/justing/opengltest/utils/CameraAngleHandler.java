package com.justing.opengltest.utils;

public class CameraAngleHandler {
	
	private float x = 0, y = 0, cx = 10, cy = 0, cz = -60;
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
					x -= (float)(aac.getDx() * 0.05f);
					y += (float)(aac.getDy() * 0.05f);
					
					cx -= (float)(aac.getEx() * 0.4f * Math.cos(x) + aac.getEz() * 0.4f * Math.sin(x));
					cy += (float)(aac.getEy() * 0.4f);
					cz -= (float)(aac.getEz() * 0.4f * Math.cos(x) - aac.getEx() * 0.4f * Math.sin(x));
					
					try {
						Thread.sleep(30);
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

	public float getX() {return x;}
	public float getY() {return y;}
	public float getCx() {return cx;}
	public float getCy() {return cy;}
	public float getCz() {return cz;}
}
