package com.ee.firearms.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ee.firearms.sprites.Brick;
import com.ee.firearms.sprites.Coin;
import com.ee.firearms.test2.PlayScreen;
import com.ee.firearms.utiles.Recursos;

public class B2WorldCreator {

	public B2WorldCreator(PlayScreen screen) {
		World world = screen.getWorld();
		TiledMap map = screen.getMap();
		
		BodyDef bDef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fDef = new FixtureDef();
		Body body;
		
		// Ground
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bDef.type = BodyDef.BodyType.StaticBody;
			bDef.position.set( (rect.getX() + rect.getWidth() / 2) / Recursos.PPM, (rect.getY() + rect.getHeight() / 2) / Recursos.PPM);
			
			body = world.createBody(bDef);
			
			shape.setAsBox( (rect.getWidth() / 2) / Recursos.PPM, (rect.getHeight() / 2) / Recursos.PPM);
			fDef.shape = shape;
			body.createFixture(fDef);
		}
		
		// Pipe
		for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bDef.type = BodyDef.BodyType.StaticBody;
			bDef.position.set((rect.getX() + rect.getWidth() / 2) / Recursos.PPM, (rect.getY() + rect.getHeight() / 2) / Recursos.PPM);
		
			body = world.createBody(bDef);
			
			shape.setAsBox(rect.getWidth() / 2 / Recursos.PPM, rect.getHeight() / 2 / Recursos.PPM);
			fDef.shape = shape;
			fDef.filter.categoryBits = Recursos.OBJECT_BIT;
			body.createFixture(fDef);
		}
		
		// Brick
		for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			new Brick(screen, rect);
		}
		
		// Coin
		for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			new Coin(screen, rect);
		}
	}
}
