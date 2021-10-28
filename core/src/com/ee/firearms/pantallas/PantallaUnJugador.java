package com.ee.firearms.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.ee.firearms.elementos.Animacion;
import com.ee.firearms.elementos.Guerrero;
import com.ee.firearms.io.Entradas;
import com.ee.firearms.utiles.Recursos;
import com.ee.firearms.utiles.Render;

public class PantallaUnJugador implements Screen {

	private Guerrero g1;
	Animacion animDerecha, animIzquierda;
	Entradas entradas = new Entradas(this);
	boolean band = false;
	
	@Override
	public void show() {
		g1 = new Guerrero(); 
		g1.texturasAnim[0] = new Texture(Recursos.GUERRERO_CAMINAR_DERECHA);
		g1.texturasAnim[1] = new Texture(Recursos.GUERRERO_CAMINAR_IZQUIERDA);
		animDerecha = new Animacion(g1, g1.texturasAnim[0], 8);
		animIzquierda = new Animacion(g1, g1.texturasAnim[1], 8);
		
		Gdx.input.setInputProcessor(entradas);
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 0);
		
		Render.begin();
		update();
		Render.end();
	}

	private void update() {
		
		
		if(entradas.isIzquierda()) {
			g1.moverIzquierda(animIzquierda);
			band = true;
		}  else if(entradas.isDerecha()) {
			g1.moverDerecha(animDerecha);
			band = false;
		} else { 
			if (band){
				g1.dibujarIzq();
			} else {
				g1.dibujarDer();
			}
		}
		
		if(entradas.isSaltar()) {
			g1.saltar();
		}
//		if(entradas.isSaltar()) {
//			g1.saltar();
//		}
		
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