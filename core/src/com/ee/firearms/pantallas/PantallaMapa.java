package com.ee.firearms.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.ee.firearms.elementos.Player;
import com.ee.firearms.utiles.Render;

public class PantallaMapa implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	
	private Player player;
	
	@Override
	public void show() {
		//TmxMapLoader loader = new TmxMapLoader();
		//map = loader.load("./mapas/mapaFireArms-v1.tmx");
		map = new TmxMapLoader().load("mapas/mapaFireArms-v1.tmx");
		
		renderer = new OrthogonalTiledMapRenderer(map);
		
		camera = new OrthographicCamera();
		
		player = new Player(new Sprite(new Texture("personajes/SpriteIdle.png")), (TiledMapTileLayer) map.getLayers().get(0));
		
		player.setPosition(14 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getTileHeight() - 16) * player.getCollisionLayer().getTileHeight());
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 1);
		
		renderer.setView(camera);
		renderer.render();
		
		renderer.getBatch().begin();
		player.draw(renderer.getBatch());
		renderer.getBatch().end();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
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

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		player.getTexture().dispose();
	}

}