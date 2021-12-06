package com.ee.firearms.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ee.firearms.FireArms;
import com.ee.firearms.elementos.Imagen;
import com.ee.firearms.elementos.Texto;
import com.ee.firearms.utiles.Config;
import com.ee.firearms.utiles.Recursos;
import com.ee.firearms.utiles.Render;

public class PantallaGameOver implements Screen {

	private Imagen fondo;
	private SpriteBatch b;
	private Texto titulo, subtitulo;
	
	private int cont = 0;
	
	private Game game;
	
	public PantallaGameOver(Game game){
        this.game = game;
        
        fondo = new Imagen(Recursos.FONDOGAMEOVER);
		fondo.setSize(Config.ANCHO, Config.ALTO);
		b = Render.sb;
		
		titulo = new Texto(Recursos.FUENTE_4, 90, Color.WHITE, true);
		subtitulo = new Texto(Recursos.FUENTE_4, 40, Color.WHITE, true);
		titulo.setTexto("Fin del juego");
		subtitulo.setTexto("Click para empezar de nuevo");
		titulo.setPosition(Config.ANCHO/2 - titulo.getAncho()/2, Config.ALTO/2 + titulo.getAlto()/2);
		subtitulo.setPosition(Config.ANCHO / 2 - subtitulo.getAncho() / 2, Config.ALTO / 2 + subtitulo.getAlto() / 2 - titulo.getAlto());
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 0);
		
		b.begin();
		fondo.dibujar();
		
		titulo.dibujar();
		subtitulo.dibujar();
		
		b.end();
		
		if(Gdx.input.justTouched()) {
			game.setScreen(new PantallaMenu((FireArms) game));
            dispose();
	    }
		 
//		cont++;
		
//		System.out.println(cont);
		
//		if(cont>250) {
//			Render.app.setScreen(new PantallaMenu());
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
		Render.dispose();
	}

}
