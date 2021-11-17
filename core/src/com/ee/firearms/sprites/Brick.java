package com.ee.firearms.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.ee.firearms.scenes.Hud;
import com.ee.firearms.test2.PlayScreen;
import com.ee.firearms.utiles.GameAssetManager;
import com.ee.firearms.utiles.Recursos;

public class Brick extends InteractiveTileObject {

	public Brick(PlayScreen screen, Rectangle bounds) {
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(Recursos.BRICK_BIT);
	}

	@Override
	public void onHeadHit() {
		Gdx.app.log("Brick", "Collision");
		setCategoryFilter(Recursos.DETROYED_BIT);
		getCell().setTile(null);
		Hud.addScore(200);
		
		GameAssetManager.manager.get("sonidos/break.wav", Sound.class).play();
	}
}
