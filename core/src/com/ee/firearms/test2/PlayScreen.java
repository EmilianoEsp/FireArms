package com.ee.firearms.test2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ee.firearms.scenes.Hud;
import com.ee.firearms.sprites.Player;
import com.ee.firearms.tools.B2WorldCreator;
import com.ee.firearms.tools.WorldContactListener;
import com.ee.firearms.utiles.Recursos;
import com.ee.firearms.utiles.Render;

public class PlayScreen implements Screen {

	private TextureAtlas atlas;
	
	private OrthographicCamera gameCam;
	private Viewport gamePort;
	private Hud hud;
	
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	// Box2d variables
	private World world;
	private Box2DDebugRenderer b2dr;
	
	private Player player;
	
	@Override
	public void show() {
		atlas = new TextureAtlas(Recursos.ATLAS_1);
		
		gameCam = new OrthographicCamera();
		gamePort = new FitViewport(Recursos.V_WIDTH / Recursos.PPM, Recursos.V_HEIGHT / Recursos.PPM, gameCam);
		hud = new Hud(Render.sb);
		
		mapLoader = new TmxMapLoader();
		map = mapLoader.load("mapas/2/mapaPrueba-v2.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / Recursos.PPM);
		
		gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		
		world = new World(new Vector2(0, -10), true);
		b2dr = new Box2DDebugRenderer();
		
		new B2WorldCreator(world, map);
		
		player = new Player(world, this);
		
		world.setContactListener(new WorldContactListener());
	}

	@Override
	public void render(float delta) {
		update(delta);
		
		Render.limpiarPantalla(0, 0, 0, 1);
		
		renderer.render();
		
		b2dr.render(world, gameCam.combined);
		
		Render.b.setProjectionMatrix(gameCam.combined);
		Render.b.begin();
		player.draw(Render.b);
		Render.b.end();
		
		Render.sb.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}
	
	private void update(float dt) {
		handleInput(dt);
		
		world.step(1/60f, 6, 2);
		
		player.update(dt);
		
		gameCam.position.x = player.b2Body.getPosition().x;
		
		gameCam.update();
		renderer.setView(gameCam);
	}

	private void handleInput(float dt) {
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			player.b2Body.applyLinearImpulse(new Vector2(0, 4f), player.b2Body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (player.b2Body.getLinearVelocity().x <= 2) ) {
			player.b2Body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2Body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && (player.b2Body.getLinearVelocity().x >= -2) ) {
			player.b2Body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2Body.getWorldCenter(), true);
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
		hud.dispose();
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}

}