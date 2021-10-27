package com.ee.firearms.pantallas;

import com.badlogic.gdx.Screen;
import com.ee.firearms.elementos.Guerrero;
import com.ee.firearms.utiles.Render;

public class PantallaUnJugador implements Screen {

	private Guerrero g2;
	
	@Override
	public void show() {
		
		//g1 = new Guerrero(200,200);
		g2 = new Guerrero(400,200); 
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 0);
		
		Render.begin();
		//g1.render(Render.batch);
		g2.render(Render.sb);
		Render.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		Render.sb.dispose();
		g2.getTextura().dispose();
	}

}