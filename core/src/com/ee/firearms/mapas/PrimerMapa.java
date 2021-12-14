package com.ee.firearms.mapas;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.ee.firearms.JuegoAssetManager;
import com.ee.firearms.utiles.Constantes;

public class PrimerMapa {
	
	private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
	public PrimerMapa() {
		mapLoader = new TmxMapLoader();
		map = mapLoader.load(JuegoAssetManager.MAPA_1);
        renderer = new OrthogonalTiledMapRenderer(map, 1  / Constantes.PPM);
	}
	
	public void render() {
		renderer.render();
	}
	
	public void update(OrthographicCamera cam) {
		renderer.setView(cam);
	}

	public TiledMap getMap() {
		return map;
	}

	public OrthogonalTiledMapRenderer getRenderer() {
		return renderer;
	}
}