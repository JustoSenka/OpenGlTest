package com.justing.opengltest.main;

import java.awt.DisplayMode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.justing.glhandler.GlHandler;
import com.justing.glhandler.GlHandlerFactory;
import com.justing.opengltest.shapes.Cuboid;
import com.justing.opengltest.shapes.Rect;
import com.justing.opengltest.shapes.Shape;
import com.justing.opengltest.utils.CameraAngleHandler;

public class JoglCanvas extends GLCanvas implements GLEventListener {

	public static DisplayMode dm, dm_old;
	private GLU glu = new GLU();
	private GlHandler glh;

	private CameraAngleHandler cah = CameraAngleHandler.getInstance();

	private List<Shape> list = new ArrayList<>();
	private Texture grass;
	
	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		GlHandler glh = GlHandlerFactory.getGlHandler(gl);
		
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();

		applyMovementAndRotation(gl);
		//applyLight(gl);
		
		//grass.enable(gl);
		//grass.bind(gl);
		/*
	       gl.glBegin(GL2.GL_POLYGON);
	        gl.glNormal3f(0,0,1);
	            gl.glTexCoord2d(-grass.getWidth(), -grass.getHeight());
	            gl.glVertex2d(-25, -25);
	            gl.glTexCoord2d(-grass.getWidth(), grass.getHeight());
	            gl.glVertex2d(grass.getWidth(),0);
	            gl.glTexCoord2d(grass.getWidth(), grass.getHeight());
	            gl.glVertex2d(grass.getWidth(), grass.getHeight());
	            gl.glTexCoord2d(grass.getWidth(), -grass.getHeight());
	            gl.glVertex2d(0, grass.getHeight());
	        gl.glEnd();
		*/
		for (Shape el : list){
			el.draw(gl);
		}
		
		gl.glFlush();
	}

	private void applyLight(GL2 gl) {
        // Prepare light parameters.
        float SHINE_ALL_DIRECTIONS = 1;
        float[] lightPos = {-30, 0, 0, SHINE_ALL_DIRECTIONS};
        float[] lightColorAmbient = {0.2f, 0.2f, 0.2f, 0.5f};
        float[] lightColorSpecular = {0.8f, 0.8f, 0.8f, 0.5f};

        // Set light parameters.
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightColorAmbient, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, lightColorSpecular, 0);

        // Enable lighting in GL.
        gl.glEnable(GL2.GL_LIGHT1);
        gl.glEnable(GL2.GL_LIGHTING);

        // Set material properties.
        float[] rgba = {0.3f, 0.5f, 1f};
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 0.5f);
        
	}

	private void applyMovementAndRotation(final GL2 gl) {
		//gl.glTranslatef(cah.getCx(), 0, cah.getCy());
		//gl.glRotatef(cah.getX(), 0, 5, 0);
		//gl.glRotatef(cah.getY(), 10, 0, 0);
		glu.gluLookAt(0.0 + cah.getCx(), 0.0 + cah.getCy(), 0.0 + cah.getCz(),
				5.0 * Math.sin(cah.getX()) + cah.getCx(), 5.0 * Math.sin(cah.getY())+ cah.getCy(), 5.0 * Math.cos(cah.getX()) + cah.getCz(),
		              0.0, 1.0, 0.0);
		
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glClearColor(0f, 0f, 0f, 0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
		
		addTestListData();
	   // loadTextures();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		final GL2 gl = drawable.getGL().getGL2();
		if (height <= 0) height = 1;

		final float h = (float) width / (float) height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		glu.gluPerspective(45.0f, h, 1.0, 100.0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	private void addTestListData() {

		list.add(new Cuboid(0.5f, 0.3f, 0.1f, 0, 0, -3));
		list.add(new Cuboid(0.5f, 1, 1.5f, 5, 0, -5));
		list.add(new Cuboid(1, 1, 1, 0, 0, -7));
		list.add(new Cuboid(2, 3, 2, -5, 0, -9));
		list.add(new Cuboid(5, 0.4f, 2, 0, 0, 10));
		list.add(new Rect(100, 0, 100, 0, -5, 0));
	}
	
	private void loadTextures(){
		try {
			grass = TextureIO.newTexture(new File("res//grass_texture.jpg"), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}