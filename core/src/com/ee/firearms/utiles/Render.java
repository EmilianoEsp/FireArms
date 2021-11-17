package com.ee.firearms.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ee.firearms.FireArms;
import com.ee.firearms.test2.PlayScreen;

public class Render {
	
	public static SpriteBatch sb;
	
	public static FireArms app;
	
	public static PlayScreen game;
	
	public static void limpiarPantalla(float r, float g, float b, float a) {
		Gdx.gl.glClearColor(r, g, b, a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	public static Batch b;
	
	public static void begin() {
		sb.begin();
	}
	
	public static void end() {
		sb.end();
	}
	
	public static void dispose() {
		sb.dispose();
	}
	
	public static void salir() {
		Gdx.app.exit();
	}

}
