package com.ee.firearms.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.ee.firearms.elementos.Imagen;
import com.ee.firearms.elementos.Texto;
import com.ee.firearms.io.Entradas;
import com.ee.firearms.utiles.Config;
import com.ee.firearms.utiles.GameAssetManager;
import com.ee.firearms.utiles.Recursos;
import com.ee.firearms.utiles.Render;

public class PantallaOpciones implements Screen {

	private Imagen fondo;
	private Texto opciones[] = new Texto[2];
	private String textos[] = { 
								"Volumen: Activado",
							    "Volver"
							  };
	private Texto titulo;
	boolean mouseArriba = false, activado = true;
	public float tiempo;
	private int opc = 1;
	
	private Music musicaMenu;
	
	private Entradas entradas = new Entradas(this);
	
	@Override
	public void show() {
		fondo = new Imagen(Recursos.FONDOMENU_1);
		fondo.setSize(Config.ANCHO, Config.ALTO);
		
		musicaMenu = GameAssetManager.manager.get(Recursos.MUSICAMENU, Music.class);
		
		titulo = new Texto(Recursos.FUENTE_3, 90, Color.WHITE, true);
		titulo.setTexto("Opciones");
		titulo.setPosition(Config.ANCHO/2 - titulo.getAncho()/2, Config.ALTO - titulo.getAlto()*2);
		
		int avance = 40;
		
		for (int i = 0; i < opciones.length; i++) {
			opciones[i] = new Texto(Recursos.FUENTE_3, 60, Color.WHITE, true);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition((Config.ANCHO / 2) - (opciones[i].getAncho() / 2),
					((Config.ALTO / 2) - (opciones[0].getAlto() / 2)) - ((opciones[i].getAlto() * i) + (avance * i)));
		}
		
		Gdx.input.setInputProcessor(entradas);
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 0);
		
		Render.begin();
		
		fondo.dibujar();
		
		titulo.dibujar();
		
		for (int i = 0; i < opciones.length; i++) {
			opciones[i].dibujar();
		}
		
		Render.end();
		
		tiempo += delta;
		
		if (entradas.isAbajo()) {
			if (tiempo > 0.1f) {
				tiempo = 0;
				opc++;
				if (opc > 2) {
					opc = 1;
				}
			}
		}

		if (entradas.isArriba()) {
			if (tiempo > 0.1f) {
				tiempo = 0;
				opc--;
				if (opc < 1) {
					opc = 2;
				}
			}
		}
		
		int cont = 0;
		for (int i = 0; i < opciones.length; i++) {
			if( (entradas.getMouseX() >= opciones[i].getX()) && (entradas.getMouseX() <= (opciones[i].getX() + opciones[i].getAncho())) ) {
				if( (entradas.getMouseY() >= opciones[i].getY() - opciones[i].getAlto()) && (entradas.getMouseY() <= (opciones[i].getY())) ) {
					opc = i + 1;
					cont++;
				}
			}
		}
		
		if(cont > 0) {
			mouseArriba = true;
		} else {
			mouseArriba = false;
		}
		
		for (int i = 0; i < opciones.length; i++) {
			if (i == (opc - 1)) {
				opciones[i].setColor(Color.YELLOW);
			} else {
				opciones[i].setColor(Color.WHITE);
			}
		}
		
		if ( (entradas.isEnter()) || (entradas.isClick()) ) {
			if ( ( ((opc == 1) && (entradas.isEnter())) || ((opc == 1) && entradas.isClick() && (mouseArriba)) ) && (activado) ) {
				opciones[0].setTexto("Volumen: Desactivado");
				musicaMenu.stop();
				activado = false;
			} else if( ( ((opc == 1) && entradas.isEnter()) || ((opc == 1) && entradas.isClick() && (mouseArriba)) ) && (!activado) ) {
				opciones[0].setTexto("Volumen: Activado");
				musicaMenu.setLooping(true);
				musicaMenu.play();
				activado = true;
			} else if ( (opc == 2) && (entradas.isEnter()) || ( (opc == 2) && (entradas.isClick()) && (mouseArriba) ) ) {
				Render.app.setScreen(new PantallaMenu());
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
		Render.dispose();
	}

}
