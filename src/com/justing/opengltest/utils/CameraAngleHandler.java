package com.justing.opengltest.utils;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;

public class CameraAngleHandler {
	
	public final static float SENSITIVITY = 1.5f;
	
	private float x = 0, y = 0, cx = 0, cy = 40, cz = -100;
	private float mouseX, mouseY;
	private float width, height;
	private Robot r;
	
	private boolean run = false;
	private Thread t = null;
	private ArrowActionContainer aac = ArrowActionContainer.getInstance();
	
	private final static CameraAngleHandler Instance = new CameraAngleHandler();
	public final static CameraAngleHandler getInstance() {return Instance;}

	private CameraAngleHandler(){
		
		try {
			r = new Robot();
		} catch (AWTException e1) {
		}

		width = (float) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (float) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		mouseX = width / 2;
		mouseY = height / 2;
		
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (run){
					
					applyCameraRotation();
					applyCameraMovement();
					
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			private void applyCameraRotation() {
				
		        mouseX = MouseInfo.getPointerInfo().getLocation().x;
		        mouseY = MouseInfo.getPointerInfo().getLocation().y;
				
				x += (width / 2 - mouseX) * SENSITIVITY / width;
				y += (height / 2 - mouseY) * SENSITIVITY / width;
				
				x -= (float)(aac.getDx() * 0.05f);
				y += (float)(aac.getDy() * 0.05f);
				
				r.mouseMove((int)width / 2, (int)height / 2);
			}

			private void applyCameraMovement() {
				final float speed = 0.4f + aac.getShift() * 10;
				
				cx -= (float)(aac.getEx() * speed * Math.cos(x) + aac.getEz() * speed * Math.sin(x));
				cy += (float)(aac.getEy() * speed);
				cz -= (float)(aac.getEz() * speed * Math.cos(x) - aac.getEx() * speed * Math.sin(x));
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
