package com.ee.firearms.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.ee.firearms.elementos.Animacion;
import com.ee.firearms.elementos.Guerrero;
import com.ee.firearms.io.Entradas;
import com.ee.firearms.utiles.Render;

public class PantallaUnJugador implements Screen {

	private Guerrero g1;
	private Animacion anim;
	
	Entradas entradas = new Entradas(this);
	
	@Override
	public void show() {
		g1 = new Guerrero(); 
		anim = new Animacion(g1,4);
		
		Gdx.input.setInputProcessor(entradas);
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 0);
		
		update();
		
		Render.begin();
		anim.render(Render.sb, (int) g1.getPosX(), (int) g1.getPosY());
		Render.end();
	}

	private void update() {
		
		if(entradas.isIzquierda()) {
			g1.moverIzquierda();
		}  
		
		if(entradas.isDerecha()) {
			g1.moverDerecha();
		} 
		
		if(entradas.isSaltar()) {
			g1.saltar();
		}
		
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
		g1.getTextura().dispose();
	}

}