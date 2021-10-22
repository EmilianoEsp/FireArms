package com.ee.firearms.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.ee.firearms.elementos.Texto;
import com.ee.firearms.utiles.Recursos;
import com.ee.firearms.utiles.Render;

public class PantallaMundo implements Screen {

	Box2DDebugRenderer renderer;
	
	OrthographicCamera camera, oCamBox2D;
	World world;
	
	Texto fuente;
	
	@Override
	public void show() {
		fuente = new Texto(Recursos.FUENTEMENU, 40, Color.WHITE, true);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 640);
		
		oCamBox2D = new OrthographicCamera();
		renderer = new Box2DDebugRenderer();
		
		Vector2 gravity = new Vector2(0,-9.8f);
		
		world = new World(gravity, true);
		createBall();
	}
	
	private void createBall() {
		BodyDef bd = new BodyDef(); 
		bd.position.set(4, 4.5f);
		bd.type = BodyType.DynamicBody;
		
		CircleShape shape = new CircleShape();
		shape.setRadius(.25f);
		
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = shape;
		
		Body body = world.createBody(bd);
		body.createFixture(fixDef);
		
	}

	private void update(float delta) {
		world.step(delta, 8, 6);
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 1);
		update(delta);
		
		camera.update();
		Render.batch.setProjectionMatrix(camera.combined);
		
		Render.batch.begin();
		fuente.dibujar(Render.batch,"Fps"+Gdx.graphics.getFramesPerSecond(),0,20);
		Render.batch.end();
		
		oCamBox2D.update();
		renderer.render(world, oCamBox2D.combined);
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
		
	}

}