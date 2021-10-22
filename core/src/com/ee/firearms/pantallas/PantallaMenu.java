package com.ee.firearms.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.ee.firearms.elementos.Imagen;
import com.ee.firearms.elementos.Texto;
import com.ee.firearms.io.Entradas;
import com.ee.firearms.utiles.Config;
import com.ee.firearms.utiles.Recursos;
import com.ee.firearms.utiles.Render;

public class PantallaMenu implements Screen {
	Imagen fondo;
	SpriteBatch b;
	
	Texto opciones[] = new Texto[4];
	String textos[] = {
						"Nueva partida",
						"Online",
						"Opciones",
						"Salir"
					  };
	
	Texto test;
	
	int opc = 1;
	
	boolean mouseArriba = false;
	
	public float tiempo = 0;
	
	ShapeRenderer sr;
	
	Entradas entradas = new Entradas(this);
	
	@Override
	public void show() {
		fondo = new Imagen(Recursos.FONDOMENU_1);
		fondo.setSize(Config.ANCHO, Config.ALTO);
		b = Render.batch;
		sr = new ShapeRenderer();

		Gdx.input.setInputProcessor(entradas);

		int avance = 30;
		
		test = new Texto(Recursos.FUENTEMENU, 40, Color.WHITE, true);
		test.setY(100);
		
		for (int i = 0; i < opciones.length; i++) {
			opciones[i] = new Texto(Recursos.FUENTEMENU, 60, Color.WHITE, true);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition((Config.ANCHO / 2) - (opciones[i].getAncho() / 2),
					((Config.ALTO / 2) + (opciones[0].getAlto() / 2)) - ((opciones[i].getAlto() * i) + (avance * i)));
		}
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 0);
		b.begin();
		fondo.dibujar();
	
		for (int i = 0; i < opciones.length; i++) {
			opciones[i].dibujar();
		}
		
		test.setTexto("Coord x: " + entradas.getMouseX() + " Coord y:" + entradas.getMouseY());
		test.dibujar();
		
		sr.begin(ShapeType.Line);
		sr.setColor(Color.RED);
		for (int i = 0; i < opciones.length; i++) {
			sr.rect(opciones[i].getX(), opciones[i].getY() - opciones[i].getAlto(), opciones[i].getAncho(), opciones[i].getAlto());
			opciones[i].dibujar();
		}
		sr.end();
		
		b.end();
		
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
			if ( (opc == 1) && (entradas.isEnter()) || ( (opc == 1) && (entradas.isClick()) && (mouseArriba) ) ) {
				Render.app.setScreen(new PantallaUnJugador());
			} else if ( (opc == 2) && (entradas.isEnter()) || ( (opc == 2) && (entradas.isClick()) && (mouseArriba) ) ) {
				Render.app.setScreen(new PantallaMultijugador());
			} else if ( (opc == 3) && (entradas.isEnter()) || ( (opc == 3) && (entradas.isClick()) && (mouseArriba) ) ) {
				Render.app.setScreen(new PantallaOpciones());
			} else if ( (opc == 4) && (entradas.isEnter()) || ( (opc == 4) && (entradas.isClick()) && (mouseArriba) ) ) {
				Gdx.app.exit();
			}
		}
		
//		if(opc == 1) {
//			o2.setColor(Color.WHITE);
//			o4.setColor(Color.WHITE);
//			o1.setColor(Color.YELLOW);
//		} else if(opc == 2) {
//			o1.setColor(Color.WHITE);
//			o3.setColor(Color.WHITE);
//			o2.setColor(Color.YELLOW);
//		} else if(opc == 3) {
//			o2.setColor(Color.WHITE);
//			o4.setColor(Color.WHITE);
//			o3.setColor(Color.YELLOW);
//		} else if(opc == 4) {
//			o1.setColor(Color.WHITE);
//			o3.setColor(Color.WHITE);
//			o4.setColor(Color.YELLOW);
//		}
//		System.out.println(tiempo);
		
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

	}
}
