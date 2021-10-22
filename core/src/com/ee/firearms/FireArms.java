package com.ee.firearms;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ee.firearms.pantallas.PantallaCarga;
import com.ee.firearms.utiles.Render;

public class FireArms extends Game {
	
	@Override
	public void create () {
		Render.app = this;
		Render.batch = new SpriteBatch();
		
		this.setScreen(new PantallaCarga());
	}

	@Override
	public void render () {
		super.render();
		//update();
	}
	
//	private void update() {
//		cont++;
//		crash.setX(cont);
//	}

	@Override
	public void dispose () {
		Render.batch.dispose();
	}
}
