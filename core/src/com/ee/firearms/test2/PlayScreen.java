package com.ee.firearms.test2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ee.firearms.scenes.Hud;
import com.ee.firearms.utiles.Render;

public class PlayScreen implements Screen {

	Texture texture;
	private OrthographicCamera gameCam;
	private Viewport gamePort;
	private Hud hud;
	
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	// Box2d variables
	private World world;
	private Box2DDebugRenderer b2dr;
	
	@Override
	public void show() {
		gameCam = new OrthographicCamera();
		gamePort = new FitViewport(400, 208, gameCam);
		hud = new Hud(Render.sb);
		
		mapLoader = new TmxMapLoader();
		map = mapLoader.load("mapas/2/mapaPrueba-v2.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		
		gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		
		world = new World(new Vector2(0, 0), true);
		b2dr = new Box2DDebugRenderer();
		
		BodyDef bDef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fDef = new FixtureDef();
		Body body;
		
		// Ground
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bDef.type = BodyDef.BodyType.StaticBody;
			bDef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);
			
			body = world.createBody(bDef);
			
			shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
			fDef.shape = shape;
			body.createFixture(fDef);
		}
	}

	@Override
	public void render(float delta) {
		update(delta);
		
		Render.limpiarPantalla(0, 0, 0, 1);
		
		renderer.render();
		
		b2dr.render(world, gameCam.combined);
		
		Render.sb.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}
	
	private void update(float dt) {
		handleInput(dt);
		
		gameCam.update();
		renderer.setView(gameCam);
	}

	private void handleInput(float dt) {
		if(Gdx.input.isTouched()) {
			gameCam.position.x += 100 * dt;
		}
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
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
		world.dispose();
		b2dr.dispose();
	}

}
