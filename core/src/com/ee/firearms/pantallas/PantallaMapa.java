package com.ee.firearms.pantallas;

import com.badlogic.gdx.Gdx;
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
	
		Gdx.input.setInputProcessor(player);
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 1);
		
		camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		camera.update();
		
		renderer.setView(camera);
		
		renderer.getBatch().begin();
		renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("Background"));
		player.draw(renderer.getBatch());
		renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("Foreground"));
		renderer.getBatch().end();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width / 2.5f;
		camera.viewportHeight = height / 2.5f;
		//camera.update();
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