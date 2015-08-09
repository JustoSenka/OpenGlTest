package com.justing.opengltest.main;

public class CameraAngleHandlerImpl extends CameraAngleHandler {

	private float x = 0, y = 0;
	private boolean run = false;
	private ArrowActionContainer aac = ArrowActionContainer.getInstance();
	private final static CameraAngleHandler Instance = new CameraAngleHandler();
	private CameraAngleHandler(){}
	
	public static final CameraAngleHandler getInstance() {
		return Instance;
	}
	
	public void startThread(){
		run = true;
	}
}
