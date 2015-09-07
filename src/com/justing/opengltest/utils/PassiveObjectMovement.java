package com.justing.opengltest.utils;

import java.util.List;

import com.justing.opengltest.shapes.Shape;

public class PassiveObjectMovement {
	
	private float x = 0, y = 0, cx = 0, cy = 0, cz = 0;
	private boolean run = false;
	private List<Shape> s;
	Thread t = null;
	private final static PassiveObjectMovement Instance = new PassiveObjectMovement();
	
	public static final PassiveObjectMovement getInstance() {
		return Instance;
	}

	private PassiveObjectMovement(){
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (run){
					x += 0.05f;
					y += 0.05f;
					
					cx += 0.4f;
					cy += 0.4f;
					cz += 0.4f;
					
					int i = 0;
					for (Shape el : s){
						if (i >= 2 && i <= 12) el.z += 0.2f;
						i++;
					}
					
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public <E extends Shape> void startThread(List<E> s){
		this.s = (List<Shape>) s;
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
