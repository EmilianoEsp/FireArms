package com.ee.firearms.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ee.firearms.FireArms;
import com.ee.firearms.test2.PlayScreen;
import com.ee.firearms.utiles.Assets;
import com.ee.firearms.utiles.Config;

public class PantallaMenu extends Menu implements Screen {
	
	private FireArms game;
	private Image fondo, titulo;
	private TextButton unJugador, opciones, multijugador, salir;
	

	public PantallaMenu(FireArms game) {
		this.game = game;
		crearWidgets();
		configurarWidgets();
		establecerListeners();
		Gdx.input.setInputProcessor(stage);
	}

	private void establecerListeners() {
		unJugador.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new PlayScreen(game));
				game.repMusica.pausada = true;
				game.repMusica.musica[game.repMusica.getMusicaActual()].stop();
			}
		});
		multijugador.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new PantallaMultijugador(game));
			}
		});
		opciones.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new PantallaOpciones(game));
			}
		});
		salir.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
	}

	private void configurarWidgets() {
		fondo.setSize(Config.ANCHO, Config.ALTO);
		titulo.setPosition(Config.ANCHO / 2 - titulo.getWidth() / 2, Config.ALTO - titulo.getHeight() * 1.20f );
		unJugador.setSize(300, 90);
		unJugador.setPosition(Config.ANCHO / 2 - unJugador.getWidth() / 2, Config.ALTO / 2 - 0);
		multijugador.setSize(300, 90);
		multijugador.setPosition(Config.ANCHO / 2 - unJugador.getWidth() / 2, Config.ALTO / 2 - 70);
		opciones.setSize(300, 90);
		opciones.setPosition(Config.ANCHO / 2 - unJugador.getWidth() / 2, Config.ALTO / 2 - 140);
		salir.setSize(300, 90);
		salir.setPosition(Config.ANCHO / 2 - unJugador.getWidth() / 2, Config.ALTO / 2 - 210);
		stage.addActor(fondo);
		stage.addActor(titulo);
		stage.addActor(unJugador);
		stage.addActor(multijugador);
		stage.addActor(opciones);
		stage.addActor(salir);

	}

	private void crearWidgets() {
		fondo = new Image(new Texture(Gdx.files.internal("fondos/fondo_1.png")));
		titulo = new Image(new Texture(Gdx.files.internal("fondos/tituloMenu.png")));
		unJugador = new TextButton("Un jugador", Assets.skin);
		multijugador = new TextButton("Multijugador", Assets.skin);
		opciones = new TextButton("Opciones", Assets.skin);
		salir = new TextButton("Salir", Assets.skin);
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show() {

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
}
