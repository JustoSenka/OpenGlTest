package com.justing.opengltest.main;

import java.awt.DisplayMode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.justing.glhandler.GlHandler;
import com.justing.glhandler.GlHandlerFactory;
import com.justing.opengltest.shapes.Cuboid;
import com.justing.opengltest.shapes.Rect;
import com.justing.opengltest.shapes.Shape;
import com.justing.opengltest.shapes.Sphere;
import com.justing.opengltest.shapes.TessShape;
import com.justing.opengltest.utils.CameraAngleHandler;

public class JoglCanvas extends GLCanvas implements GLEventListener {

	public static DisplayMode dm, dm_old;
	private GLU glu = new GLU();
	private GlHandler glh;

	private CameraAngleHandler cah = CameraAngleHandler.getInstance();

	private List<Shape> s = new ArrayList<>();
	private List<TessShape> ts = new ArrayList<>();
	
	private Texture grass, earth, earth2, earth3, sky, sun;
	
	private float test = 0;
	
	GLUquadric quad;
	
	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		GlHandler glh = GlHandlerFactory.getGlHandler(gl);
		
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();

		applyMovementAndRotation(gl);
		applyLight(gl);
		enableTextures(gl);
		
		grass.bind(gl);
		
		gl.glPushMatrix();
        // Draw solar panels.
        //gl.glScalef(6f, 2f, 2f);
        
        gl.glBegin(GL2.GL_QUADS);
        
//        gl.glDisable(GL2.GL_LIGHTING);
//        gl.glDisable(GL2.GL_LIGHT1);
        
        // Front Face.
        gl.glNormal3f(0.0f, 0.0f, 1.0f);
        
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-100, 0, 100);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(100, 0, 100);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(100, 0, -100);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-100, 0, -100);

        gl.glEnd();

		gl.glPopMatrix();
		
	
      /*
        GLUtessellator tobj = GLU.gluNewTess();
        
        glu.gluTessCallback(tobj, GLU_TESS_VERTEX,
                        (GLvoid (CALLBACK*) ()) &glVertex3dv);
        glu.gluTessCallback(tobj, GLU_TESS_BEGIN,
                        (GLvoid (CALLBACK*) ()) &glBegin);
        glu.gluTessCallback(tobj, GLU_TESS_END,
                        (GLvoid (CALLBACK*) ()) &glEnd);
*/
        /*
        double[][] car = new double[][] { {-1.93,0.3,0}, {-1.95,0.4,0}, {-2.2,0.6,0}, {-2.6,0.6,0}, {-2.82,0.4,0}, {-2.8,0.3,0}, {-2.78,0.0,0} };
        
        GLU.gluTessBeginPolygon(tobj, null);
        GLU.gluTessBeginContour(tobj);
              for(int i=0; i<7;i++)
            	  glu.gluTessVertex(tobj, car[i], 7, null);
              //call gluTessVertex for EVERY vertex in the polygon here 
              GLU.gluTessEndContour(tobj);
              GLU.gluTessEndPolygon(tobj);
              */
              
		for (Shape el : s){
			el.draw(gl);
		}
		
		for (TessShape el : ts){
			el.draw(gl, glu);
		}
		
		gl.glFlush();
	}

	private void enableTextures(final GL2 gl) {
		earth.enable(gl);
		earth2.enable(gl);
		sky.enable(gl);
	}

	private void applyLight(GL2 gl) {
        // Prepare light parameters.
        float SHINE_ALL_DIRECTIONS = 1;
        float[] lightPos = {0, 15, 0, SHINE_ALL_DIRECTIONS};
        float[] lightColorAmbient = {0.2f, 0.2f, 0.2f, 0.5f};
        float[] lightColorSpecular = {0.8f, 0.8f, 0.8f, 0.5f};
        
//        float[] lightColorAmbient = {0.8f, 0.8f, 0.8f, 1f};
//        float[] lightColorSpecular = {1f, 0f, 0f, 1f};

        // Set light parameters.
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightColorAmbient, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, lightColorSpecular, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, lightColorSpecular, 0);

        // Enable lighting in GL.
        gl.glEnable(GL2.GL_LIGHT1);
        gl.glEnable(GL2.GL_LIGHTING);

        // Set material properties.
//        float[] rgba = {0.3f, 0.5f, 1f};
        float[] rgba = {.8f, .8f, 1f};
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
		
		loadTextures();
		setQuadParams();
		
		addTestListData();
	}

	private void setQuadParams() {

		quad = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(quad, GLU.GLU_FILL);
        glu.gluQuadricNormals(quad, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(quad, GLU.GLU_OUTSIDE);
        glu.gluQuadricTexture(quad, true);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		final GL2 gl = drawable.getGL().getGL2();
		if (height <= 0) height = 1;

		final float h = (float) width / (float) height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		glu.gluPerspective(45.0f, h, 1.0, 1000.0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	private void addTestListData() {
		s.add(new Cuboid(0.5f, 0.3f, 0.1f, 0, 0, -3));
		s.add(new Cuboid(0.5f, 1, 1.5f, 5, 0, -5));
		s.add(new Cuboid(1, 1, 1, 0, 0, -7));
		s.add(new Cuboid(2, 3, 2, -5, 0, -9));
		s.add(new Cuboid(5, 0.4f, 2, 0, 0, 10));
		s.add(new Rect(100, 0, 100, 0, -5, 0));
		
		ts.add(new Sphere(quad, sun, 4, 0, 15, -50, -90, 0, 0));
		ts.add(new Sphere(quad, earth, 12, 30, 15, 10, -90, 0, 0));
		ts.add(new Sphere(quad, earth2, 12, -20, 15, 40, -90, 0, 0));
		ts.add(new Sphere(quad, earth2, 12, -20, 15, -20, -90, 0, 0));
		ts.add(new Sphere(quad, sky, 500, 0, 0, 0, -90, 0, 0));
	}
	
	private void loadTextures(){
		grass = loadTexture("grass_texture.jpg");
		earth = loadTexture("earth.jpg");
		earth2 = loadTexture("earth2.jpg");
		//earth3 = loadTexture("earth3.jpg");
		sky = loadTexture("sky.jpg");
		sun = loadTexture("sun.jpg");
	}

	private Texture loadTexture(String str) {
		Texture temp = null;
		try {
			temp = TextureIO.newTexture(new File("res//" + str), true);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed loading: " + str);
		}
		return temp;
	}
}