package com.justing.opengltest.shapes;

import com.jogamp.opengl.GL2;
import com.justing.opengltest.utils.CameraAngleHandler;

public class Cuboid implements Shape{

	private float x, y, z, cx, cy, cz;
	private CameraAngleHandler cah = CameraAngleHandler.getInstance();
	
	public Cuboid(float x, float y, float z, float cx, float cy, float cz) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.cx = cx;
		this.cy = cy;
		this.cz = cz;
	}
	
	@Override
	public void draw(GL2 gl){
			float ccx = 0, ccy = 0, ccz = 0;
		
	      gl.glBegin( GL2.GL_QUADS ); // Start Drawing The Cube
	      gl.glColor3f( 1f,0f,0f );   //red color
	      gl.glVertex3f( x + cx + ccx, y + cy + ccy, -z + cz + ccz); // Top Right Of The Quad (Top)
	      gl.glVertex3f( -x + cx + ccx, y + cy + ccy, -z + cz + ccz); // Top Left Of The Quad (Top)
	      gl.glVertex3f( -x + cx + ccx, y + cy + ccy, z + cz + ccz); // Bottom Left Of The Quad (Top)
	      gl.glVertex3f( x + cx + ccx, y + cy + ccy, z + cz + ccz); // Bottom Right Of The Quad (Top)
	      gl.glColor3f( 0f,1f,0f ); //green color
	      gl.glVertex3f( x + cx + ccx, -y + cy + ccy, z + cz + ccz); // Top Right Of The Quad 
	      gl.glVertex3f( -x + cx + ccx, -y + cy + ccy, z + cz + ccz); // Top Left Of The Quad 
	      gl.glVertex3f( -x + cx + ccx, -y + cy + ccy, -z + cz + ccz); // Bottom Left Of The Quad 
	      gl.glVertex3f( x + cx + ccx, -y + cy + ccy, -z + cz + ccz); // Bottom Right Of The Quad 
	      gl.glColor3f( 0f,0f,1f ); //blue color
	      gl.glVertex3f( x + cx + ccx, y + cy + ccy, z + cz + ccz); // Top Right Of The Quad (Front)
	      gl.glVertex3f( -x + cx + ccx, y + cy + ccy, z + cz + ccz); // Top Left Of The Quad (Front)
	      gl.glVertex3f( -x + cx + ccx, -y + cy + ccy, z + cz + ccz); // Bottom Left Of The Quad 
	      gl.glVertex3f( x + cx + ccx, -y + cy + ccy, z + cz + ccz); // Bottom Right Of The Quad 
	      gl.glColor3f( 1f,1f,0f ); //yellow (red + green)
	      gl.glVertex3f( x + cx + ccx, -y + cy + ccy, -z + cz + ccz); // Bottom Left Of The Quad 
	      gl.glVertex3f( -x + cx + ccx, -y + cy + ccy, -z + cz + ccz); // Bottom Right Of The Quad
	      gl.glVertex3f( -x + cx + ccx, y + cy + ccy, -z + cz + ccz); // Top Right Of The Quad (Back)
	      gl.glVertex3f( x + cx + ccx, y + cy + ccy, -z + cz + ccz); // Top Left Of The Quad (Back)
	      gl.glColor3f( 1f,0f,1f ); //purple (red + green)
	      gl.glVertex3f( -x + cx + ccx, y + cy + ccy, z + cz + ccz); // Top Right Of The Quad (Left)
	      gl.glVertex3f( -x + cx + ccx, y + cy + ccy, -z + cz + ccz); // Top Left Of The Quad (Left)
	      gl.glVertex3f( -x + cx + ccx, -y + cy + ccy, -z + cz + ccz); // Bottom Left Of The Quad 
	      gl.glVertex3f( -x + cx + ccx, -y + cy + ccy, z + cz + ccz); // Bottom Right Of The Quad 
	      gl.glColor3f( 0f,1f, 1f ); //sky blue (blue +green)
	      gl.glVertex3f( x + cx + ccx, y + cy + ccy, -z + cz + ccz); // Top Right Of The Quad (Right)
	      gl.glVertex3f( x + cx + ccx, y + cy + ccy, z + cz + ccz); // Top Left Of The Quad 
	      gl.glVertex3f( x + cx + ccx, -y + cy + ccy, z + cz + ccz); // Bottom Left Of The Quad 
	      gl.glVertex3f( x + cx + ccx, -y + cy + ccy, -z + cz + ccz); // Bottom Right Of The Quad 
	      gl.glEnd(); // Done Drawing The Quad
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getCx() {
		return cx;
	}

	public void setCx(float cx) {
		this.cx = cx;
	}

	public float getCy() {
		return cy;
	}

	public void setCy(float cy) {
		this.cy = cy;
	}

	public float getCz() {
		return cz;
	}

	public void setCz(float cz) {
		this.cz = cz;
	}
}
