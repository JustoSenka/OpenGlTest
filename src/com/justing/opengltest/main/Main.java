package com.justing.opengltest.main;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import com.jogamp.opengl.GLCapabilities;

public class Main {
    public final static void main(String[] args) {
    	
        JFrame frame = createFrame();
        CameraAngleHandler.getInstance().startThread();
    }
    
	private static JFrame createFrame() {
		JoglCanvas canvas = new JoglCanvas(createGLCapabilities(), 800, 500);
        JFrame frame = new JFrame("Test OpenGL");
        frame.getContentPane().add(canvas, BorderLayout.CENTER);
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        canvas.requestFocus();
        canvas.setFrame(frame);
		return frame;
	}
    
    private static GLCapabilities createGLCapabilities() {
        GLCapabilities capabilities = new GLCapabilities(null);
        capabilities.setRedBits(8);
        capabilities.setBlueBits(8);
        capabilities.setGreenBits(8);
        capabilities.setAlphaBits(8);
        return capabilities;
    }
}
