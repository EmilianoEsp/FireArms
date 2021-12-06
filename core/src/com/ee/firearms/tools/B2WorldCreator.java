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
import com.ee.firearms.FireArms;
import com.ee.firearms.sprites.Brick;
import com.ee.firearms.sprites.Coin;
import com.ee.firearms.test2.PlayScreen;

public class B2WorldCreator {

	public B2WorldCreator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        //create body and fixture variables
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //create ground bodies/fixtures
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / FireArms.PPM, (rect.getY() + rect.getHeight() / 2) / FireArms.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / FireArms.PPM, rect.getHeight() / 2 / FireArms.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        
      //create limits bodies/fixtures
//        for(MapObject object : map.getLayers().get(3).getObjects().getByType(PolylineMapObject.class)){
//            Polyline pol = ((PolylineMapObject) object).getPolyline();
//
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((pol.getOriginX()) / FireArms.PPM, (pol.getOriginY()) / FireArms.PPM);
//
//            body = world.createBody(bdef);
//
//            shape.set(pol.getTransformedVertices());
//            fdef.shape = shape;
//            body.createFixture(fdef);
//        }

        //create brick bodies/fixtures
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            new Brick(screen, object);
        }

        //create coin bodies/fixtures
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){

            new Coin(screen, object);
        }
	} 
	
}
