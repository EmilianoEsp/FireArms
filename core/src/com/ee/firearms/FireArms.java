package com.ee.firearms;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ee.firearms.pantallas.PantallaCarga;
import com.ee.firearms.utiles.Assets;
import com.ee.firearms.utiles.GameAssetManager;
import com.ee.firearms.utiles.Render;
import com.ee.firearms.utiles.ReproductorMusica;

public class FireArms extends Game {
	//Virtual Screen size and Box2D Scale(Pixels Per Meter)
	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 416;
	public static final float PPM = 100;

	//Box2D Collision Bits
	public static final short NOTHING_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short MARIO_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short ENEMY_HEAD_BIT = 128;
	public static final short ITEM_BIT = 256;
	public static final short MARIO_HEAD_BIT = 512;
	public static final short FIREBALL_BIT = 1024;

	Screen screen;
	public GameAssetManager gameManager;
	public ReproductorMusica repMusica;
	
	@Override
	public void create () {
		Render.sb = new SpriteBatch();
		gameManager = new GameAssetManager();
		repMusica = new ReproductorMusica();
		
		new Assets();
		
		setScreen(new PantallaCarga(this));
	}
	
	@Override
	public void render () {
		super.render();
		Render.limpiarPantalla(0, 0, 0, 0);
		screen.render(Gdx.graphics.getDeltaTime());
		repMusica.update();
	}
	
	@Override
	public void resize(int width, int height) {
		screen.resize(width, height);
	}
	
	public void setScreen(Screen screen) {
		if (this.screen != null) {
			this.screen.hide();
			this.screen.dispose();
		}
		this.screen = screen;
		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}


	@Override
	public void dispose() {
		gameManager.getManager().dispose();
		screen.dispose();
	}
}