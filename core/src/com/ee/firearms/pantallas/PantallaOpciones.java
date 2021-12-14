package com.ee.firearms.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ee.firearms.FireArms;
import com.ee.firearms.JuegoAssetManager;
import com.ee.firearms.JuegoStateManager;
import com.ee.firearms.SkinManager;
import com.ee.firearms.utiles.Config;
import com.ee.firearms.utiles.Constantes;

public class PantallaOpciones extends PantallaAbstracta {
	
	private Image fondo, titulo;
	private TextButton volver;
	private CheckBox fullscreen;
	private Slider volumenSlider;
	private Label sonido, video, volumen, valorVolumen;
	private TextButton pausa, siguiente, anterior;
	private Stage stage;

	public PantallaOpciones(JuegoStateManager jsm) {
		super(jsm);
		stage = new Stage(new FitViewport(Config.ANCHO, Config.ALTO));
		crearWidgets();
		configurarWidgets();
		establecerListeners();
		Gdx.input.setInputProcessor(stage);
	}
	
	private void establecerListeners() {
		volver.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				jsm.mostrarPantalla(Pantallas.MENU_PRINCIPAL);
			}
		});
		fullscreen.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(fullscreen.isChecked()) {
					Config.fullscreen = true;
					getGraphs().setFullscreenMode(Gdx.graphics.getDisplayMode());
				} else {
					getGraphs().setWindowedMode(Config.ANCHO, Config.ALTO);
				}
			}
		});
		volumenSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Constantes.volumenMusica = volumenSlider.getValue();
				valorVolumen.setText((int) Constantes.volumenMusica);
			}
		});
		pausa.addListener(new ClickListener() {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			FireArms.repMusica.pausada = !FireArms.repMusica.pausada;
		}
		});
		siguiente.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				FireArms.repMusica.siguiente();
			}
		});
		anterior.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				FireArms.repMusica.anterior();
			}
		});
		
	}

	private void configurarWidgets() {
		fondo.setSize(Config.ANCHO, Config.ALTO);
		titulo.setPosition(Config.ANCHO / 2 - titulo.getWidth() / 2, Config.ALTO - titulo.getHeight() * 1.20f );
		sonido.setFontScale(1.5f);
		sonido.setPosition(Config.ANCHO / 2 - sonido.getWidth() / 1.5f, titulo.getY() - 80);
		volumen.setPosition(Config.ANCHO / 2 - volumen.getWidth() / 2, sonido.getY() - 40);
		volumenSlider.setSize(220, 50);
		volumenSlider.setPosition(Config.ANCHO / 2 - volumenSlider.getWidth() / 2, volumen.getY() - 50);
		volumenSlider.setValue(Constantes.volumenMusica);
		valorVolumen.setPosition(Config.ANCHO / 2 - volumenSlider.getX() + 650, volumen.getY() - 25);
		valorVolumen.setText((int) Constantes.volumenMusica);
		anterior.setSize(200, 60);
		anterior.setPosition(Config.ANCHO / 2 - volumenSlider.getWidth(), volumenSlider.getY() - 50);
		pausa.setSize(150, 60);
		pausa.setPosition(anterior.getX() + 150, volumenSlider.getY() - 50);
		siguiente.setSize(200, 60);
		siguiente.setPosition(pausa.getX() + 100, volumenSlider.getY() - 50);
		video.setFontScale(1.5f);
		video.setPosition(Config.ANCHO / 2 - video.getWidth() / 1.5f, volumenSlider.getY() - 100);
		fullscreen.setPosition(Config.ANCHO / 2 - fullscreen.getWidth() / 2, video.getY() - 40);
		volver.setSize(300, 90);
		volver.setPosition(Config.ANCHO / 2 - volver.getWidth() / 2, Config.ALTO / 2 - 250);
		if(Config.fullscreen) fullscreen.setChecked(true);
		stage.addActor(fondo);
		stage.addActor(titulo);
		stage.addActor(volver);
		stage.addActor(sonido);
		stage.addActor(volumen);
		stage.addActor(volumenSlider);
		stage.addActor(valorVolumen);
		stage.addActor(anterior);
		stage.addActor(pausa);
		stage.addActor(siguiente);
		stage.addActor(video);
		stage.addActor(fullscreen);
	}

	private void crearWidgets() {
		fondo = new Image(new Texture(Gdx.files.internal(JuegoAssetManager.FONDO_MENU)));
		titulo = new Image(new Texture(Gdx.files.internal(JuegoAssetManager.TITULO_OPCIONES)));
		volver = new TextButton("Volver", SkinManager.skin);	
		sonido = new Label("Sonido", SkinManager.skin);
		volumen = new Label("Musica", SkinManager.skin);
		volumenSlider = new Slider(0, 100, 1, false, SkinManager.skin);
		valorVolumen = new Label("", SkinManager.skin);
		anterior = new TextButton("Anterior", SkinManager.skin);
		pausa = new TextButton("Pausa", SkinManager.skin);
		siguiente= new TextButton("Siguiente", SkinManager.skin);
		video = new Label("Video", SkinManager.skin);
		fullscreen = new CheckBox("Pantalla Completa", SkinManager.skin);
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
	
	public Graphics getGraphs() {
		return Gdx.app.getGraphics();
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}

}
