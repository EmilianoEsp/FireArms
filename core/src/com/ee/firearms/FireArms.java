package com.ee.firearms;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ee.firearms.test2.PlayScreen;
import com.ee.firearms.utiles.Render;

public class FireArms extends Game {
	public static AssetManager manager;
	
	@Override
	public void create () {
		Render.app = this;
		Render.sb = new SpriteBatch();
		
		manager = new AssetManager();
		manager.load("musica/music_1.wav", Music.class);
		manager.load("sonidos/coin.wav", Sound.class);
		manager.load("sonidos/bump.wav", Sound.class);
		manager.load("sonidos/break.wav", Sound.class);
		manager.finishLoading();
		
		this.setScreen(new PlayScreen());
	}

	@Override
	public void render () {
		super.render();
		//manager.update();
	}

	@Override
	public void dispose () {
		Render.sb.dispose();
		super.dispose();
		manager.dispose();
	}
}