package com.ee.firearms.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.ee.firearms.test2.PlayScreen;
import com.ee.firearms.utiles.Recursos;

public class Knight extends Enemy {

	private float stateTime;
	private Animation<TextureRegion> walkAnimation;
	private Array<TextureRegion> frames;
	
	public Knight(PlayScreen screen, float x, float y) {
		super(screen, x, y);
		frames = new Array<TextureRegion>();
		for(int i = 0; i < 2; i++) {
			frames.add(new TextureRegion(screen.getAtlas().findRegion("nombre"), i * 32, 0, 32, 32));
		}
		walkAnimation = new Animation<TextureRegion>(0.4f, frames);
		stateTime = 0;
		setBounds(getX(), getY(), 32 / Recursos.PPM, 32 / Recursos.PPM);
	}

	public void update(float dt) {
		stateTime += dt;
		setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
		setRegion(walkAnimation.getKeyFrame(stateTime, true));
	}
	
	@Override
	protected void defineEnemy() {
		BodyDef bDef = new BodyDef();
		bDef.position.set(32 / Recursos.PPM, 32 / Recursos.PPM);
		bDef.type = BodyDef.BodyType.DynamicBody;
		b2Body = world.createBody(bDef);

		FixtureDef fDef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(6 / Recursos.PPM);
		fDef.filter.categoryBits = Recursos.ENEMY_BIT;
		fDef.filter.maskBits = Recursos.GROUND_BIT | 
							   Recursos.COIN_BIT | 
							   Recursos.BRICK_BIT |
							   Recursos.ENEMY_BIT |
							   Recursos.OBJECT_BIT |
							   Recursos.PLAYER_BIT;

		fDef.shape = shape;
		b2Body.createFixture(fDef);
	}

}
