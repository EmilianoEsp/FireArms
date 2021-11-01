package com.ee.firearms.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.ee.firearms.FireArms;
import com.ee.firearms.scenes.Hud;
import com.ee.firearms.test2.PlayScreen;
import com.ee.firearms.utiles.Recursos;

public class Coin extends InteractiveTileObject {

	private static TiledMapTileSet tileSet;
	private final int BLANK_COIN = 62;
	
	public Coin(PlayScreen screen, Rectangle bounds) {
		super(screen, bounds);
		tileSet = map.getTileSets().getTileSet("Tileset-v2");
		fixture.setUserData(this);
		setCategoryFilter(Recursos.COIN_BIT);
	}

	@Override
	public void onHeadHit() {
		Gdx.app.log("Coin", "Collision");
		
		if(getCell().getTile().getId() == BLANK_COIN) {
			FireArms.manager.get("sonidos/bump.wav", Sound.class);
		} else {
			FireArms.manager.get("sonidos/coin.wav", Sound.class);
		}
		
		getCell().setTile(tileSet.getTile(BLANK_COIN));
		Hud.addScore(100);
		
		
	}

}
