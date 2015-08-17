package com.justing.opengltest.shapes;

import com.jogamp.opengl.GL2;
import com.justing.opengltest.utils.CameraAngleHandler;

public class Rect implements Shape{

	private float x, y, z, cx, cy, cz;
	private CameraAngleHandler cah = CameraAngleHandler.getInstance();
	
	public Rect(float x, float y, float z, float cx, float cy, float cz) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.cx = cx;
		this.cy = cy;
		this.cz = cz;
	}
	
	@Override
	public void draw(GL2 gl){
	      gl.glBegin( GL2.GL_POLYGON ); 
	      gl.glColor3f( 0f,1f,0f ); //green color
	      gl.glVertex3f( x + cx, y + cy, -z + cz); 
	      gl.glVertex3f( x + cx, -y + cy, z + cz); 
	      gl.glVertex3f( -x + cx, -y + cy, z + cz); 
	      gl.glVertex3f( -x + cx, y + cy, -z + cz);
	      gl.glEnd();
	}
	
}
