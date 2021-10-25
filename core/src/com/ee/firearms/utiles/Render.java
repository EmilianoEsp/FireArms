package com.ee.firearms.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ee.firearms.FireArms;

public class Render {
	
	public static SpriteBatch batch;
	
	public static FireArms app;
	
	public static void limpiarPantalla(float r, float g, float b, float a) {
		Gdx.gl.glClearColor(r, g, b, a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	public SpriteBatch getSpriteBatch() {
		return batch;

	}
}
