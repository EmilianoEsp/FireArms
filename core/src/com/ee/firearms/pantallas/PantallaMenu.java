package com.ee.firearms.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ee.firearms.FireArms;
import com.ee.firearms.JuegoAssetManager;
import com.ee.firearms.SkinManager;
import com.ee.firearms.utiles.Config;

public class PantallaMenu extends PantallaAbstracta {
	
	private Image fondo, titulo;
	private TextButton unJugador, opciones, multijugador, salir;
	private Stage stage;
	
	public PantallaMenu(FireArms jsm) {
		super(jsm);
		stage = new Stage(new FitViewport(Config.ANCHO, Config.ALTO));
		crearWidgets();
		configurarWidgets();
		establecerListeners();
		Gdx.input.setInputProcessor(stage);
	}

	private void establecerListeners() {
		unJugador.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				FireArms.repMusica.pausada = true;
				FireArms.repMusica.musica[FireArms.repMusica.getMusicaActual()].stop();
				jsm.mostrarPantalla(Pantallas.UN_JUGADOR);
			}
		});
		multijugador.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				jsm.mostrarPantalla(Pantallas.MULTIJUGADOR);
			}
		});
		opciones.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				jsm.mostrarPantalla(Pantallas.OPCIONES);
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
		fondo = new Image(new Texture(Gdx.files.internal(JuegoAssetManager.FONDO_MENU)));
		titulo = new Image(new Texture(Gdx.files.internal(JuegoAssetManager.TITULO_MENU)));
		unJugador = new TextButton("Un jugador", SkinManager.skin);
		multijugador = new TextButton("Multijugador", SkinManager.skin);
		opciones = new TextButton("Opciones", SkinManager.skin);
		salir = new TextButton("Salir", SkinManager.skin);
	}

	@Override
	public void render(float delta) {
		jsm.limpiarPantalla();
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
