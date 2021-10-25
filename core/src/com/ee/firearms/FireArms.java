package com.ee.firearms;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ee.firearms.pantallas.*;
import com.ee.firearms.utiles.Render;

public class FireArms extends Game {
	
	@Override
	public void create () {
		Render.app = this;
		Render.batch = new SpriteBatch();
		
		this.setScreen(new PantallaMapa());
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		Render.batch.dispose();
	}
}