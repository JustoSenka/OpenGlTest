package com.justing.opengltest.shapes;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;

public class Sphere implements TessShape{

	private float x, y, z, cx, cy, cz, radius;
	private GLUquadric quad;
	private Texture texture;
	
	public Sphere(GLUquadric quad, Texture texture, float radius, float cx, float cy, float cz, float x, float y, float z) {
		this.quad = quad;
		this.texture = texture;
		this.radius = radius;
		this.x = x;
		this.y = y;
		this.z = z;
		this.cx = cx;
		this.cy = cy;
		this.cz = cz;
	}
	
	@Override
	public void draw(GL2 gl, GLU glu){
		
		gl.glPushMatrix();
		
		try {
			texture.bind(gl);
		} catch (GLException e) {
			System.out.println("gl error on texture bind");
		} catch (NullPointerException npe) {
			System.out.println("no texture");
		}
		
		gl.glTranslatef(cx, cy, cz);
		gl.glRotatef(x, 1, 0, 0);
		gl.glRotatef(y, 0, 1, 0);
		gl.glRotatef(z, 0, 0, 1);
        
        glu.gluSphere(quad, radius, 64, 64);
		
        gl.glPopMatrix();
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
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

	public GLUquadric getQuad() {
		return quad;
	}

	public void setQuad(GLUquadric quad) {
		this.quad = quad;
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
