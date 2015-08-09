package com.justing.glhandler;

import com.jogamp.opengl.GL2;

public class GlHandlerImpl implements GlHandler{

	private GL2 gl;
	
	public GlHandlerImpl(GL2 gl) {
		this.gl = gl;
	}

	@Override
	public void drawCube(float x) {
		this.drawCuboid(x, x, x);
	}

	@Override
	public void drawCuboid(float x, float y, float z) {
		
		
		 gl.glBegin( GL2.GL_QUADS ); // Start Drawing The Cube
	      gl.glColor3f( 1f,0f,0f );   //red color
	      gl.glVertex3f( 1.0f, 1.0f, -1.0f ); // Top Right Of The Quad (Top)
	      gl.glVertex3f( -1.0f, 1.0f, -1.0f); // Top Left Of The Quad (Top)
	      gl.glVertex3f( -1.0f, 1.0f, 1.0f ); // Bottom Left Of The Quad (Top)
	      gl.glVertex3f( 1.0f, 1.0f, 1.0f ); // Bottom Right Of The Quad (Top)
	      gl.glColor3f( 0f,1f,0f ); //green color
	      gl.glVertex3f( 1.0f, -1.0f, 1.0f ); // Top Right Of The Quad 
	      gl.glVertex3f( -1.0f, -1.0f, 1.0f ); // Top Left Of The Quad 
	      gl.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad 
	      gl.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad 
	      gl.glColor3f( 0f,0f,1f ); //blue color
	      gl.glVertex3f( 1.0f, 1.0f, 1.0f ); // Top Right Of The Quad (Front)
	      gl.glVertex3f( -1.0f, 1.0f, 1.0f ); // Top Left Of The Quad (Front)
	      gl.glVertex3f( -1.0f, -1.0f, 1.0f ); // Bottom Left Of The Quad 
	      gl.glVertex3f( 1.0f, -1.0f, 1.0f ); // Bottom Right Of The Quad 
	      gl.glColor3f( 1f,1f,0f ); //yellow (red + green)
	      gl.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad 
	      gl.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad
	      gl.glVertex3f( -1.0f, 1.0f, -1.0f ); // Top Right Of The Quad (Back)
	      gl.glVertex3f( 1.0f, 1.0f, -1.0f ); // Top Left Of The Quad (Back)
	      gl.glColor3f( 1f,0f,1f ); //purple (red + green)
	      gl.glVertex3f( -1.0f, 1.0f, 1.0f ); // Top Right Of The Quad (Left)
	      gl.glVertex3f( -1.0f, 1.0f, -1.0f ); // Top Left Of The Quad (Left)
	      gl.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad 
	      gl.glVertex3f( -1.0f, -1.0f, 1.0f ); // Bottom Right Of The Quad 
	      gl.glColor3f( 0f,1f, 1f ); //sky blue (blue +green)
	      gl.glVertex3f( 1.0f, 1.0f, -1.0f ); // Top Right Of The Quad (Right)
	      gl.glVertex3f( 1.0f, 1.0f, 1.0f ); // Top Left Of The Quad 
	      gl.glVertex3f( 1.0f, -1.0f, 1.0f ); // Bottom Left Of The Quad 
	      gl.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad 
	      gl.glEnd(); // Done Drawing The Quad
/*
		drawRect(x, y);
		gl.glTranslatef(x, 0, 0);
		gl.glRotatef(90, 0, 1, 0);
		drawRect(x, y);
		*/
	}
	
	@Override
	public void drawSquare(float x) {
		this.drawRect(x, x);
	}
	
	@Override
	public void drawRect(float x, float y) {
		//x /= 500; y /= 500;
		
        gl.glBegin( GL2.GL_POLYGON );
        
        gl.glVertex3f(-x/2 , -y/2, 0f);
        gl.glVertex3f(x/2 , -y/2, 0f);
        gl.glVertex3f(x/2 , y/2, 0f);
        gl.glVertex3f(-x/2 , y/2, 0f);

        gl.glEnd(); 
	}
}
