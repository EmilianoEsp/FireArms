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
	private Texto opciones[] = new Texto[3];
	private String textos[] = { 
								"Opciones",
								"Volumen: Activado",
							    "Volver"
							  };
	boolean mouseArriba = false, activado = true;
	public float tiempo;
	private int opc = 1;
	
	Entradas entradas = new Entradas(this);
	
	@Override
	public void show() {
		fondo = new Imagen(Recursos.FONDOMENU_1);
		fondo.setSize(Config.ANCHO, Config.ALTO);
		
		musicOpc = Gdx.audio.newMusic(Gdx.files.internal(Recursos.MUSICAOPCIONES));
		musicOpc.setLooping(true);
		musicOpc.play();
		
		int avance = 30;
		
		for (int i = 0; i < opciones.length; i++) {
			opciones[i] = new Texto(Recursos.FUENTEMENU, 40, Color.WHITE, true);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition((Config.ANCHO / 2) - (opciones[i].getAncho() / 2),
					((Config.ALTO / 2) + (opciones[0].getAlto() / 2)) - ((opciones[i].getAlto() * i) + (avance * i)));
		}
		
//		titulo = new Texto(Recursos.FUENTEMENU, 40, Color.WHITE, true);
//		volumen = new Texto(Recursos.FUENTEMENU, 25, Color.WHITE, true);
//		volver = new Texto(Recursos.FUENTEMENU, 25, Color.WHITE, true);
//		titulo.setTexto("Opciones");
//		volumen.setTexto("Volumen: Activado");
//		volver.setTexto("Volver");
//		titulo.setPosition(Config.ANCHO/2 - titulo.getAncho(), Config.ALTO/2 + titulo.getAlto());
//		volumen.setPosition(Config.ANCHO/2 - volumen.getAncho(), Config.ALTO/2 + volumen.getAlto() + titulo.getAlto());
//		volver.setPosition(Config.ANCHO/2 - volver.getAncho(), Config.ALTO/2 + volver.getAlto() + volumen.getAlto() + titulo.getAlto());
		
		Gdx.input.setInputProcessor(entradas);
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 0);
		
		Render.begin();
		
		fondo.dibujar();
//		titulo.dibujar();
//		volumen.dibujar();
//		volver.dibujar();
		for (int i = 0; i < opciones.length; i++) {
			opciones[i].dibujar();
		}
		
		Render.end();
		
		tiempo += delta;
		
		if (entradas.isAbajo()) {
			if (tiempo > 0.1f) {
				opc++;
				if (opc > 4) {
					opc = 1;
				}
			}
		}

		if (entradas.isArriba()) {
			if (tiempo > 0.1f) {
				opc--;
				if (opc < 1) {
					opc = 4;
				}
			}
		}
		
		int cont = 0;
		for (int i = 1; i < opciones.length; i++) {
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
		
		switch(opc) {
		case 1: 
			opciones[1].setColor(Color.YELLOW);
			opciones[2].setColor(Color.WHITE);
			break;
		case 2:
			opciones[1].setColor(Color.WHITE);
			opciones[2].setColor(Color.YELLOW);
			break;
		}
		
		if ( (entradas.isEnter()) || (entradas.isClick()) && (activado) ) {
			if ( (opc == 1 && entradas.isEnter()) || (opc == 1 && entradas.isClick()) && (mouseArriba) ) {
				opciones[opc].setTexto("Volumen: Desactivado");
				musicOpc.stop();
			}
		} else if( (!activado) && (entradas.isEnter()) || (entradas.isClick() ) ) {
			if( (opc == 1 && entradas.isEnter()) || (opc == 1 && entradas.isClick()) && (mouseArriba) ) {
				opciones[opc].setTexto("Volumen: Activado");
				musicOpc.setLooping(true);
				musicOpc.play();
			}
		} else if( (opc == 2 && entradas.isEnter()) || (opc == 2 && entradas.isClick()) && (mouseArriba)) {
			Render.app.setScreen(new PantallaMenu());
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
		Render.dispose();
	}

}
