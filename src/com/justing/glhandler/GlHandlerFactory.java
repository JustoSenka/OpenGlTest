package com.justing.glhandler;

import com.jogamp.opengl.GL2;

public final class GlHandlerFactory {

	public static final GlHandler getGlHandler(GL2 gl){
		return new GlHandlerImpl(gl);
	}
}
