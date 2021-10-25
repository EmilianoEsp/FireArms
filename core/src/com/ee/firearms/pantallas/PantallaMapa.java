package com.ee.firearms.pantallas;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.ee.firearms.elementos.Player;
import com.ee.firearms.utiles.Recursos;
import com.ee.firearms.utiles.Render;

public class PantallaMapa implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	
	private Player player;
	
	private int[] background = new int[] {0}, foreground = new int[] {1};
	
	private ShapeRenderer sr;
	
	@Override
	public void show() {
		//TmxMapLoader loader = new TmxMapLoader();
		//map = loader.load("./mapas/mapaFireArms-v1.tmx");
		map = new TmxMapLoader().load("mapas/mapaFireArms-v1.tmx");
		
		renderer = new OrthogonalTiledMapRenderer(map);
		
		sr = new ShapeRenderer();
		sr.setColor(Color.CYAN);
		Gdx.gl.glLineWidth(3);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		
		player = new Player(new Sprite(new Texture("personajes/SpriteIdle.png")), (TiledMapTileLayer) map.getLayers().get(0));
		
		player.setPosition(14 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getTileHeight() - 16) * player.getCollisionLayer().getTileHeight());
	
		Gdx.input.setInputProcessor(player);
		
		//Animated Tiles
		Array<StaticTiledMapTile> frameTiles = new Array<StaticTiledMapTile>(4);
		
		Iterator<TiledMapTile> tiles = map.getTileSets().getTileSet(Recursos.SHEET).iterator();
		while(tiles.hasNext()) {
			TiledMapTile tile = tiles.next();
			if(tile.getProperties().containsKey("animation") && tile.getProperties().get("animation", String.class).equals("color")) {
				frameTiles.add((StaticTiledMapTile) tile);
			}
		}
		
		AnimatedTiledMapTile animatedTile = new AnimatedTiledMapTile(1/3f, frameTiles);
		
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("background");
		
		/*for(int x = 0; x < layer.getWidth(); x++) {
			for(int y = 0; y < layer.getHeight(); y++) {
				Cell cell = layer.getCell(x, y);
				if(cell.getTile().getProperties().containsKey("animation") && cell.getTile().getProperties().get("animation", String.class).equals("color")) {
					cell.setTile(animatedTile);
				}
			}
		}*/
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 1);
		Render.batch.setProjectionMatrix(camera.combined);
		camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		camera.update();
		
		renderer.setView(camera);
		
		renderer.render(background);
		
		renderer.getBatch().begin();
		//renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("Background"));
		player.draw(renderer.getBatch());
		//renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("Foreground"));
		renderer.getBatch().end();
		
		renderer.render(foreground);
		
		// render objects
		sr.setProjectionMatrix(camera.combined);
		for(MapObject object : map.getLayers().get("objects").getObjects()) {
			if(object instanceof RectangleMapObject) {
				Rectangle rect = ((RectangleMapObject) object).getRectangle();
				sr.begin(ShapeType.Filled);
				sr.rect(rect.x, rect.y, rect.width, rect.height);
				sr.end();
			} else if(object instanceof CircleMapObject) {
				Circle circle = ((CircleMapObject) object).getCircle();
				sr.begin(ShapeType.Filled);
				sr.circle(circle.x, circle.y, circle.radius);
				sr.end();
			} else if(object instanceof EllipseMapObject) {
				Ellipse ellipse = ((EllipseMapObject) object).getEllipse();
				sr.begin(ShapeType.Filled);
				sr.ellipse(ellipse.x, ellipse.y, ellipse.width, ellipse.height);
				sr.end();
			} else if(object instanceof PolylineMapObject) {
				Polyline line = ((PolylineMapObject) object).getPolyline();
				sr.begin(ShapeType.Line);
				sr.polyline(line.getTransformedVertices());
				sr.end();
			} else if(object instanceof PolygonMapObject) {
				Polygon poly = ((PolygonMapObject) object).getPolygon();
				sr.begin(ShapeType.Line);
				sr.polygon(poly.getTransformedVertices());
				sr.end();
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		//camera.viewportWidth = width / 2.5f;
		//camera.viewportHeight = height / 2.5f;]
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