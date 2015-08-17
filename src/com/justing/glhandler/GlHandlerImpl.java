package com.justing.glhandler;

import com.jogamp.opengl.GL2;
import com.justing.opengltest.utils.CameraAngleHandler;

public class GlHandlerImpl implements GlHandler{

	private GL2 gl;
	private CameraAngleHandler cah = CameraAngleHandler.getInstance();
	
	public GlHandlerImpl(GL2 gl) {
		this.gl = gl;
	}

	@Override
	public void drawCube(float x) {
		this.drawCuboid(x + cah.getCx(), x + cah.getCx(), x + cah.getCx());
	}

	@Override
	public void drawCuboid(float x, float y, float z) {
		
	      gl.glBegin( GL2.GL_QUADS ); // Start Drawing The Cube
	      gl.glColor3f( 1f,0f,0f );   //red color
	      gl.glVertex3f( x + cah.getCx(), y, -z + cah.getCy() ); // Top Right Of The Quad (Top)
	      gl.glVertex3f( -x + cah.getCx(), y, -z + cah.getCy()); // Top Left Of The Quad (Top)
	      gl.glVertex3f( -x + cah.getCx(), y, z + cah.getCy() ); // Bottom Left Of The Quad (Top)
	      gl.glVertex3f( x + cah.getCx(), y, z + cah.getCy() ); // Bottom Right Of The Quad (Top)
	      gl.glColor3f( 0f,1f,0f ); //green color
	      gl.glVertex3f( x + cah.getCx(), -y, z + cah.getCy() ); // Top Right Of The Quad 
	      gl.glVertex3f( -x + cah.getCx(), -y, z + cah.getCy() ); // Top Left Of The Quad 
	      gl.glVertex3f( -x + cah.getCx(), -y, -z + cah.getCy() ); // Bottom Left Of The Quad 
	      gl.glVertex3f( x + cah.getCx(), -y, -z + cah.getCy() ); // Bottom Right Of The Quad 
	      gl.glColor3f( 0f,0f,1f ); //blue color
	      gl.glVertex3f( x + cah.getCx(), y, z + cah.getCy() ); // Top Right Of The Quad (Front)
	      gl.glVertex3f( -x + cah.getCx(), y, z + cah.getCy() ); // Top Left Of The Quad (Front)
	      gl.glVertex3f( -x + cah.getCx(), -y, z + cah.getCy() ); // Bottom Left Of The Quad 
	      gl.glVertex3f( x + cah.getCx(), -y, z + cah.getCy() ); // Bottom Right Of The Quad 
	      gl.glColor3f( 1f,1f,0f ); //yellow (red + green)
	      gl.glVertex3f( x + cah.getCx(), -y, -z + cah.getCy() ); // Bottom Left Of The Quad 
	      gl.glVertex3f( -x + cah.getCx(), -y, -z + cah.getCy() ); // Bottom Right Of The Quad
	      gl.glVertex3f( -x + cah.getCx(), y, -z + cah.getCy() ); // Top Right Of The Quad (Back)
	      gl.glVertex3f( x + cah.getCx(), y, -z + cah.getCy() ); // Top Left Of The Quad (Back)
	      gl.glColor3f( 1f,0f,1f ); //purple (red + green)
	      gl.glVertex3f( -x + cah.getCx(), y, z + cah.getCy() ); // Top Right Of The Quad (Left)
	      gl.glVertex3f( -x + cah.getCx(), y, -z + cah.getCy() ); // Top Left Of The Quad (Left)
	      gl.glVertex3f( -x + cah.getCx(), -y, -z + cah.getCy() ); // Bottom Left Of The Quad 
	      gl.glVertex3f( -x + cah.getCx(), -y, z + cah.getCy() ); // Bottom Right Of The Quad 
	      gl.glColor3f( 0f,1f, 1f ); //sky blue (blue +green)
	      gl.glVertex3f( x + cah.getCx(), y, -z + cah.getCy() ); // Top Right Of The Quad (Right)
	      gl.glVertex3f( x + cah.getCx(), y, z + cah.getCy() ); // Top Left Of The Quad 
	      gl.glVertex3f( x + cah.getCx(), -y, z + cah.getCy() ); // Bottom Left Of The Quad 
	      gl.glVertex3f( x + cah.getCx(), -y, -z + cah.getCy() ); // Bottom Right Of The Quad 
	      gl.glEnd(); // Done Drawing The Quad
	}
	
	@Override
	public void drawSquare(float x) {
		this.drawRect(x + cah.getCx(), x + cah.getCx());
	}
	
	@Override
	public void drawRect(float x, float y) {
		//x + cah.getCx() /= 500; y /= 500;
		
        gl.glBegin( GL2.GL_POLYGON );
        
        gl.glVertex3f(-x + cah.getCx()/2 , -y/2, 0f);
        gl.glVertex3f(x + cah.getCx()/2 , -y/2, 0f);
        gl.glVertex3f(x + cah.getCx()/2 , y/2, 0f);
        gl.glVertex3f(-x + cah.getCx()/2 , y/2, 0f);

        gl.glEnd(); 
	}
}
