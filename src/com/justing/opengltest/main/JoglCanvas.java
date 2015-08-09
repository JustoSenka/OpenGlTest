package com.justing.opengltest.main;


import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.jogamp.opengl.DebugGL2;
import com.jogamp.opengl.DebugGL4bc;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.justing.glhandler.GlHandler;
import com.justing.glhandler.GlHandlerFactory;

public class JoglCanvas extends GLCanvas implements GLEventListener {

    private static final long serialVersionUID = 1L;
    private int fps = 60;
    private FPSAnimator animator;
    private GLU glu;
	private JFrame frame;
	
	private ArrowActionContainer aac = ArrowActionContainer.getInstance();
	private CameraAngleHandler cah = CameraAngleHandler.getInstance();
	
	private float a = 0;

    public JoglCanvas(GLCapabilities capabilities, int width, int height) {
        addGLEventListener(this);
    }

    public void init(GLAutoDrawable drawable) {
        drawable.setGL(new DebugGL2((GL2) drawable.getGL()));
        final GL2 gl = drawable.getGL().getGL2();
        glu = new GLU();

        // Enable z- (depth) buffer for hidden surface removal. 
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);

        // Enable smooth shading.
       // gl.glShadeModel(GL.gl_smo);

        // Define "clear" color.
        gl.glClearColor(0f, 0f, 0f, 0f);

        // We want a nice perspective.
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glHint(GL2.GL_LINE_SMOOTH_HINT, GL2.GL_NICEST);
        
        // Start animator.
        animator = new FPSAnimator(this, fps);
        animator.start();
    }

    public void display(GLAutoDrawable drawable) {
        if (!animator.isAnimating()) {
            return;
        }
        final DebugGL4bc gl = (DebugGL4bc) drawable.getGL();
        GlHandler glh = GlHandlerFactory.getGlHandler(gl);
       // setCamera(gl, glu, 100);

        // Clear screen.
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); 
        
        gl.glRotatef(cah.getX(), 0.0f, 1.0f, 0.0f );
        gl.glRotatef(cah.getY(), 1, 0.0f, 0.0f );
        gl.glTranslatef(cah.getCx(), 0, cah.getCy());
        
        //gl.glColor3f(1f, 0f, 0f); //applying red   
        //glh.drawCuboid(1, 1, 1);
        //glh.drawSquare(1);
        glh.drawCuboid(0, 0, 0);
        
        gl.glFlush();
    }
    
    private void setCamera(GL2 gl, GLU glu, float distance) {
        // Change to projection matrix.
        //gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        float widthHeightRatio = (float) getWidth() / (float) getHeight();
        glu.gluPerspective(45, widthHeightRatio, 1, 1000);
        glu.gluLookAt(0, 0, distance, 0, 0, 0, 0, 1, 0);

        // Change back to model view matrix.
        //gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL gl = drawable.getGL();
        gl.glViewport(0, 0, width, height);
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        throw new UnsupportedOperationException("Changing display is not supported.");
    }

	@Override
	public void dispose(GLAutoDrawable arg0) {}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		aac.configureKeyBindings(frame);
	}
}