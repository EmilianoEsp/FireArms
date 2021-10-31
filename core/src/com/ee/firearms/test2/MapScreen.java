package com.ee.firearms.test2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ee.firearms.utiles.Recursos;
import com.ee.firearms.utiles.Render;

public class MapScreen implements Screen {

	private OrthographicCamera camera;
	private Jugador jugador;
	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;
	private Vector2 position;
	
	@Override
	public void show() {
		position = new Vector2(100,100);
		jugador = new Jugador(position, new Texture(Recursos.GUERRERO_DER), new Rectangle(jugador.getPosition().x, jugador.getPosition().y, jugador.getTextura().getWidth(), jugador.getTextura().getHeight()));
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Recursos.TILES_IN_CAMERA_WIDTH * Recursos.TILE_WIDTH, Recursos.TILES_IN_CAMERA_HEIGHT * Recursos.TILE_WIDTH);
		camera.update();
		
		map = new TmxMapLoader().load("mapas/2/mapaPrueba-v2.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		Render.b = mapRenderer.getBatch();
		 
		mapRenderer.setView(camera);
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 1);
		
		camera.update();
		
		mapRenderer.render(new int[]{0, 1});
		
//		mapRenderer.getBatch().begin();
		Render.b.begin();
		jugador.dibujar();
		Render.b.end();
//		mapRenderer.getBatch().end();
		
		handleCamera();
		handleInput();
		
		// Obtiene todos los objetos de la capa 'objetos'
		MapLayer collisionsLayer = map.getLayers().get("objetos");
		for (MapObject object : collisionsLayer.getObjects()) {
		  RectangleMapObject rectangleObject = (RectangleMapObject) object;
		 
		  // Caso 1: Comprueba si el objeto contiene una propiedad
		  if (rectangleObject.getProperties().containsKey("enemy")) {
		    //
		  }
		 
		  // Caso 2: Obtiene el valor de una propiedad
		  //int score = (int) (rectangleObject.getProperties().get("score"));
		  //
		 
		  // Caso 3: Obtiene el rectangulo ocupado por el objeto
		  Rectangle rect = rectangleObject.getRectangle();
		  if (jugador.rect.overlaps(rect)) {
		    //
		  }
		}
		
	}

	private void handleCamera() {
		if (jugador.getPosition().x < Recursos.TILES_IN_CAMERA_WIDTH * Recursos.TILE_WIDTH / 2) {
			camera.position.set(Recursos.TILES_IN_CAMERA_WIDTH * Recursos.TILE_WIDTH / 2,
					Recursos.TILES_IN_CAMERA_HEIGHT * Recursos.TILE_WIDTH / 2 - Recursos.TILE_WIDTH, 0);
		} else {
			camera.position.set(jugador.getPosition().x,
					Recursos.TILES_IN_CAMERA_HEIGHT * Recursos.TILE_WIDTH / 2 - Recursos.TILE_WIDTH, 0);
		}
		camera.update();
		mapRenderer.setView(camera);
	}
	
	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			jugador.move(new Vector2(-10, 0));
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			jugador.move(new Vector2(10, 0));
		}
	}
	
	@Override
	public void resize(int width, int height) {

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
		Render.b.dispose();
		map.dispose();
		mapRenderer.dispose();
	}

}
