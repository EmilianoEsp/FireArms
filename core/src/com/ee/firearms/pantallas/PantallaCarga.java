package com.ee.firearms.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.ee.firearms.FireArms;
import com.ee.firearms.elementos.Imagen;
import com.ee.firearms.utiles.Config;
import com.ee.firearms.utiles.Recursos;
import com.ee.firearms.utiles.Render;

public class PantallaCarga implements Screen {
	
	private FireArms game;
	
	private Imagen fondo;
	private boolean fadeInTerminado = false, termina = false;
	private float a = 0;
	private float contTiempo = 0, tiempoEspera = 5;
	private float contTiempoTermina = 0, tiempoTermina = 5;
	
	public Music musicMenu;
	
	public PantallaCarga(FireArms game) {
		this.game = game;
		
		fondo = new Imagen(Recursos.LOGO);
		fondo.setSize(400, 400);
		fondo.setPosition(Config.ANCHO / 2 - fondo.getWidth() / 2, Config.ALTO / 2 - fondo.getHeight() / 2);
		fondo.setTransparencia(1);
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0,0,0,0);
		
		Render.sb.begin();
		fondo.dibujar();
		Render.sb.end();
		
		procesarFade();
	}

	private void procesarFade() {
		if (!fadeInTerminado) {
			a += 0.01f;
			if (a > 1) {
				a = 1;
				fadeInTerminado = true;
			}
		} else {
			contTiempo += 0.05f;
			if (contTiempo > tiempoEspera) {
				a -= 0.01f;
				if (a < 0) {
					a = 0;
					termina = true;
				}
			}
		}
		System.out.println(a);
		fondo.setTransparencia(a);
		
		if (termina) {
			contTiempoTermina += 0.08f;
			if (contTiempoTermina > tiempoTermina) {
				game.setScreen(new PantallaMenu(game));
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
