package com.justing.opengltest.main;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.justing.opengltest.utils.ArrowActionContainer;
import com.justing.opengltest.utils.CameraAngleHandler;

public class Main {

	public static void main(String[] args) {
		final GLCapabilities capabilities = new GLCapabilities(GLProfile.get(GLProfile.GL2));
		final GLCanvas glcanvas = new GLCanvas(capabilities);

		final JoglCanvas canvas = new JoglCanvas();
		glcanvas.addGLEventListener(canvas);
		glcanvas.setSize(800, 600);

		final JFrame frame = new JFrame("OpenGl Example");
		frame.getContentPane().setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setResizable(false);
		//frame.pack();
		
		frame.getContentPane().add(glcanvas);
		/*
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run()
	        {
	          Point p = new Point(0, 0);
	          SwingUtilities.convertPointToScreen(p, frame.getContentPane());
	          Point l = frame.getLocation();
	          l.x -= p.x;
	          l.y -= p.y;
	          frame.setLocation(l);
	        }
	      });
		*/
		frame.setVisible(true);
		
		ArrowActionContainer.getInstance().configureKeyBindings(frame);
		CameraAngleHandler.getInstance().startThread();
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
		animator.start();
	}

}
