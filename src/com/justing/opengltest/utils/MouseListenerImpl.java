package com.justing.opengltest.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseListenerImpl implements MouseMotionListener{

	private static MouseListenerImpl Instance = new MouseListenerImpl();
	public MouseListenerImpl(){}
	public final static MouseListenerImpl getInstance(){return Instance;}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
