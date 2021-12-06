package com.ee.firearms.utiles;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class GameAssetManager {

	public static final AssetManager manager = new AssetManager();
	
	public GameAssetManager() {
		cargarMusica();
		cargarSonidos();
		
		manager.finishLoading();

//        manager.get(Recursos.MUSICAMENU, Music.class).setLooping(true);
//        manager.get(ConstantesJuego.MUSICA_JUEGO, Music.class).setLooping(true);
	}

	private void cargarSonidos() {
		manager.load("sonidos/coin.wav", Sound.class);
		manager.load("sonidos/bump.wav", Sound.class);
		manager.load("sonidos/break.wav", Sound.class);
	}

	private void cargarMusica() {
		manager.load(Recursos.MUSICA_1, Music.class);
		manager.load(Recursos.MUSICA_2, Music.class);
		manager.load(Recursos.MUSICA_3, Music.class);
		manager.load(Recursos.MUSICA_4, Music.class);
		manager.load(Recursos.MUSICA_5, Music.class);
		manager.load(Recursos.MUSICAJUEGO, Music.class);
	}
	
	public AssetManager getManager() {
		return manager;
	}
	
}
