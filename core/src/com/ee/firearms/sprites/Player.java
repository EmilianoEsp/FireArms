package com.ee.firearms.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.ee.firearms.test2.PlayScreen;
import com.ee.firearms.utiles.Recursos;

public class Player extends Sprite {

	public enum State {FALLING, JUMPING, STANDING, RUNNING};
	public State currentState, previousState;
	public World world;
	public Body b2Body;
	private TextureRegion playerStand;
	private Animation<TextureRegion> playerRun, playerJump;
	private float stateTimer;
	private boolean runningRight;
	
	public Player(World world, PlayScreen screen) {
		super(screen.getAtlas().findRegion("guerrero"));
		this.world = world;
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		runningRight = true;
		
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int i = 1; i < 4; i++) {
			frames.add(new TextureRegion(getTexture(), i * 32, 0, 32, 32));
		}
		playerRun = new Animation<TextureRegion>(0.1f, frames);
		frames.clear();
		
		for(int i = 4; i < 6; i++) {
			frames.add(new TextureRegion(getTexture(), i * 32, 0, 32, 32));
		}
		playerJump = new Animation<TextureRegion>(0.1f, frames);
		
		playerStand = new TextureRegion(getTexture(), 0, 0, 32, 32);
		
		definePlayer();
		setBounds(0, 0, 32 / Recursos.PPM, 32 / Recursos.PPM);
		setRegion(playerStand);
	}

	private void definePlayer() {
		BodyDef bDef = new BodyDef();
		bDef.position.set(32 / Recursos.PPM, 32 / Recursos.PPM);
		bDef.type = BodyDef.BodyType.DynamicBody;
		b2Body = world.createBody(bDef);
		
		FixtureDef fDef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(6 / Recursos.PPM);
		
		fDef.shape = shape;
		b2Body.createFixture(fDef);
		
		EdgeShape head = new EdgeShape();
		head.set(new Vector2(-2 / Recursos.PPM, 6 / Recursos.PPM), new Vector2(2 / Recursos.PPM, 6 / Recursos.PPM));
		fDef.shape = head;
		fDef.isSensor = true;
		
		b2Body.createFixture(fDef).setUserData("head");
	}
	
	public void update(float dt) {
		setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
		setRegion(getFrame(dt));
	}

	private TextureRegion getFrame(float dt) {
		currentState = getState();
		
		TextureRegion region;
		switch(currentState) {
		case JUMPING:
			region = playerJump.getKeyFrame(stateTimer);
			break;
		case RUNNING:
			region = playerRun.getKeyFrame(stateTimer, true);
			break;
		case FALLING:
		case STANDING:
		default:
			region = playerStand;
			break;
		}
		
		if((b2Body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
			region.flip(true, false);
			runningRight = false;
		} else if((b2Body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
			region.flip(true, false);
			runningRight = true;
		}
		
		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		previousState = currentState;
		return region;
	}
	
	public State getState() {
		if((b2Body.getLinearVelocity().y > 0) || (b2Body.getLinearVelocity().y < 0 && previousState == State.JUMPING)) {
			return State.JUMPING;
		} else if(b2Body.getLinearVelocity().y < 0) {
			return State.FALLING;
		} else if(b2Body.getLinearVelocity().x != 0) {
			return State.RUNNING;
		} else {
			return State.STANDING;
		}
	}
}