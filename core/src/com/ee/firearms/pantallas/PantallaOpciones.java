package com.ee.firearms.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.ee.firearms.elementos.Imagen;
import com.ee.firearms.elementos.Texto;
import com.ee.firearms.io.Entradas;
import com.ee.firearms.utiles.Config;
import com.ee.firearms.utiles.Recursos;
import com.ee.firearms.utiles.Render;

public class PantallaOpciones implements Screen {

	private Music musicOpc;
	private Imagen fondo;
	private Texto opcion;
	private String texto = "Volumen";
	boolean mouseArriba = false;
	Entradas entradas = new Entradas(this);
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(entradas);
		
		musicOpc = Gdx.audio.newMusic(Gdx.files.internal(Recursos.MUSICAOPCIONES));
		musicOpc.setLooping(true);
		musicOpc.play();
		//musicOpc.setVolume(1000);
		
		fondo = new Imagen(Recursos.FONDOMENU_1);
		fondo.setSize(Config.ANCHO, Config.ALTO);
		
		opcion = new Texto(Recursos.FUENTEMENU, 40, Color.WHITE, true);
		opcion.setTexto(texto);
		opcion.setPosition(Config.ANCHO/2, Config.ALTO/2);
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 0);
		
		Render.batch.begin();
		fondo.dibujar();
		opcion.setColor(Color.YELLOW);
		opcion.dibujar();
		Render.batch.end();
		
		if ( (entradas.isEnter()) || (entradas.isClick()) ) {
			if ( (entradas.isEnter()) || (entradas.isClick()) && (mouseArriba) ) {
				//musicOpc.setVolume(50);
				musicOpc.stop();
			}
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
		musicOpc.dispose();
		Render.batch.dispose();
	}

}
