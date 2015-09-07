package com.justing.opengltest.main;

import java.awt.DisplayMode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.justing.opengltest.model.Model;
import com.justing.opengltest.model.ModelIO;
import com.justing.opengltest.shapes.*;
import com.justing.opengltest.utils.CameraAngleHandler;
import com.justing.opengltest.utils.PassiveObjectMovement;

public class Renderer implements GLEventListener{

	
	public final static float FOV = 45.0f;
	public static DisplayMode dm, dm_old;
	private GLU glu = new GLU();

	private CameraAngleHandler cah = CameraAngleHandler.getInstance();

	private List<Shape> s = new ArrayList<>();
	private List<ModelShape> ms = new ArrayList<>();
	
	private Texture sky, ring;
	private Texture[] planets = new Texture[20];
	
	private Model[] models = new Model[40];
	
	GLUquadric quad;
	
	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();

		applyMovementAndRotation(gl);
		applyLight(gl);
		//enableTransparency(gl);
        
		for (Shape el : s){
			el.draw(gl, glu);
		}
		
		for (ModelShape el : ms){
			el.draw(gl);
		}
		
		gl.glFlush();
	}

	private void applyMovementAndRotation(final GL2 gl) {
		glu.gluLookAt(0.0 + cah.getCx(), 0.0 + cah.getCy(), 0.0 + cah.getCz(),
				5.0 * Math.sin(cah.getX()) + cah.getCx(), 5.0 * Math.sin(cah.getY())+ cah.getCy(), 5.0 * Math.cos(cah.getX()) + cah.getCz(),
		              0.0, 1.0, 0.0);
	}
	
	private void applyLight(GL2 gl) {
        // Prepare light parameters.
        float SHINE_ALL_DIRECTIONS = 1;
        float[] lightPos = {0, 40, -100, SHINE_ALL_DIRECTIONS};
        float[] lightColorAmbient = {0.2f, 0.2f, 0.2f, 0.5f};
        float[] lightColorDiffuse = {1f, 1f, 1f, 0.5f};
        float[] lightColorSpecular = {0.8f, 0.8f, 0.8f, 0.5f};

        // Set light parameters.
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightColorAmbient, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, lightColorSpecular, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, lightColorDiffuse, 0);
        
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_POSITION, new float[]{0, 15, -40, SHINE_ALL_DIRECTIONS}, 0);
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_AMBIENT, lightColorAmbient, 0);
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_SPECULAR, lightColorSpecular, 0);
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_DIFFUSE, lightColorDiffuse, 0);


        // Enable lighting in GL.
        gl.glEnable(GL2.GL_LIGHT1);
        //gl.glEnable(GL2.GL_LIGHT2);
        gl.glEnable(GL2.GL_LIGHTING);

        // Set material properties.
//        float[] rgba = {0.3f, 0.5f, 1f};
        float[] rgba = {0.8f, 0.8f, 1f, 1f};
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, rgba, 0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, rgba, 0);
        gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 20f);
        
	}
	
	private void enableTransparency(final GL2 gl) {
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {}
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		final GL2 gl = drawable.getGL().getGL2();
		if (height <= 0) height = 1;

		final float h = (float) width / (float) height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		glu.gluPerspective(FOV, h, 1.0, 4000.0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0f, 0f, 0f, 0f);
		gl.glClearDepth(1.0f);
		
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		
		//gl.glEnable(GL2.GL_CULL_FACE); // don't render face backside of triangles
		//gl.glCullFace(GL2.GL_BACK); // backside
		
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
		
		
		loadTextures();
		loadModels();
		setQuadParams();
		
		addTestListData();
		PassiveObjectMovement.getInstance().startThread(s);
		
	}
	
	private void setQuadParams() {
		quad = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(quad, GLU.GLU_FILL);
		glu.gluQuadricNormals(quad, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(quad, GLU.GLU_OUTSIDE);
		glu.gluQuadricTexture(quad, true);
	}
		
	private void addTestListData() {
		
		//s.add(new Cylinder(quad, grass, 100, 2, 0, 0, 0, -90, 0, 0));
		s.add(new Sphere(quad, sky, 1000, 0, 0, 0, -90, 0, 0));
		
//		s.add(new Sphere(quad, planets[0], 3.8f, 50, 40, -50, -90, 0, 0));
//		s.add(new Sphere(quad, planets[1], 9.5f, 40, 40, -20, -90, 0, 0));
//		s.add(new Sphere(quad, planets[2], 10.f, 0, 40, -20, -90, 0, 0));
//		s.add(new Sphere(quad, planets[3], 5.3f,-20, 40, -50, -90, 0, 0));
//		
//		s.add(new Sphere(quad, planets[4], 112f,  410, 120, 100, -90, 0, 0));
//		s.add(new Sphere(quad, planets[5], 94.5f, 100, 120, 260, -90, 0, 0));
//		s.add(new Sphere(quad, planets[6], 40f,   -170, 100, 240, -90, 0, 0));
//		s.add(new Sphere(quad, planets[7], 38.8f, -270, 100, 200, -90, 0, 0));
//		
//		s.add(new Sphere(quad, planets[8], 5, -110, 50, 50, -90, 0, 0));
//		s.add(new Sphere(quad, planets[9], 2.5f, 0, 40, -40, -90, 0, 0));
//		
//		s.add(new Disc(quad, ring, 225, 100, 120, 260, -90, 0, 0));
		
		ms.add(new ModelShape(models[2], 12, 6, 12, 0, 0, 0, 0, 0, 0));
//		ms.add(new ModelShape(models[3], 1, 1, 1, -50, 15, -50, 0, 0, 0));
//		ms.add(new ModelShape(models[4], 1, 1, 1, 0, 15, -70, 0, 0, 0));
//		ms.add(new ModelShape(models[5], 1, 1, 1, 0, 15, -70, 0, 0, 0));
//		ms.add(new ModelShape(models[6], 1, 1, 1, 0, 15, -70, 0, 0, 0));
//		ms.add(new ModelShape(models[7], 1, 1, 1, 0, 15, -70, 0, 0, 0));
//		ms.add(new ModelShape(models[8], 3, 3, 3, 0, 20, -45, 0, 0, 0));
//		ms.add(new ModelShape(models[9], 1, 1, 1, 30, 15, -40, 0, 0, 0));
		//ms.add(new ModelShape(models[10], 0.2f, 0.2f, 0.2f, 0, -30, -40, 0, 0, 0));
	}
	
	private void loadTextures(){
		planets[0] = loadTexture("mercury.jpg");
		planets[1] = loadTexture("venus.jpg");
		planets[2] = loadTexture("earth.jpg");
		planets[3] = loadTexture("mars.jpg");
		planets[4] = loadTexture("jupiter.jpg");
		planets[5] = loadTexture("saturn.jpg");
		planets[6] = loadTexture("uranus.jpg");
		planets[7] = loadTexture("neptune.jpg");
		planets[8] = loadTexture("pluto.jpg");
		planets[9] = loadTexture("moon.jpg");
		
		ring = loadTexture("saturnring2.png");
		sky = loadTexture("sky.jpg");
	}

	private void loadModels() {
		models[2] = loadModel("COD.obj");
		models[3] = loadModel("T-90fx.obj");
		models[4] = loadModel("crate2.obj");
		models[5] = loadModel("sack.obj");
		models[6] = loadModel("barrel.obj");
		models[7] = loadModel("coffin.obj");
		models[8] = loadModel("FuelTank.obj");
		models[9] = loadModel("Cargo.obj");
		//models[10] = loadModel("Island.obj");
	}

	private static Texture loadTexture(String str) {
		Texture temp = null;
		try {
			temp = TextureIO.newTexture(new File("res/tex/" + str), true);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed loading: " + str);
		}
		return temp;
	}
	
	private static Model loadModel(String str) {
		Model temp = null;
		try {
			temp = ModelIO.newModel("res/obj/" + str);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed loading: " + str);
		}
		return temp;
	}
}
