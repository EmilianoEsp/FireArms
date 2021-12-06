package com.ee.firearms.test2;

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
import com.ee.firearms.FireArms;
import com.ee.firearms.utiles.Recursos;

public class Mario extends Sprite {
    public enum State { FALLING, JUMPING, STANDING, RUNNING, DEAD, ATTACKING };
    public State currentState;
    public State previousState;

    public World world;
    public Body b2body;

    private TextureRegion marioStand;
    private Animation<TextureRegion> marioRun;
    private Animation<TextureRegion> marioJump;
    private Animation<TextureRegion> marioAttack1;
    private Animation<TextureRegion> marioIdle;
    private Animation<TextureRegion> marioAttack;

    private float stateTimer;
    private boolean runningRight;
    private PlayScreen screen;

    public Mario(PlayScreen screen){
        //initialize default values
        this.screen = screen;
        this.world = screen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        //get run animation frames and add them to marioRun Animation
        for(int i = 1; i < 6; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("Run"), i * 47, 0, 47, 49));
        }
        marioRun = new Animation<TextureRegion>(0.1f, frames);

        frames.clear();

        // get attacking animation frames and add them to marioAttack Animation
//        for(int i = 0; i < 4; i++) {
//        	frames.add(new TextureRegion(screen.getAtlas().findRegion("Attack1"), i * 16, 0, 16, 32));
//        }
//        marioAttack = new Animation<TextureRegion>(0.2f, frames);
//
//        frames.clear();

        //get jump animation frames and add them to marioJump Animation
        for(int i = 1; i < 3; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("Jump"), i * 42, 0, 42, 56));
        }
        marioJump = new Animation<TextureRegion>(1f, frames);
        
        frames.clear();
        
        for(int i = 1; i < 5; i++) {
        	if(i == 3) {
        		frames.add(new TextureRegion(screen.getAtlas().findRegion("Attack1"), 94, 0, 94, 65));
        	} else {
        		frames.add(new TextureRegion(screen.getAtlas().findRegion("Attack1"), i * 48, 0, 48, 65));
        	}
        }
        marioAttack1 = new Animation<TextureRegion>(0.1f, frames);
        
        //create texture region for mario standing
        marioStand = new TextureRegion(screen.getAtlas().findRegion("Idle"), 0, 0, 44, 56);

        //define mario in Box2d
        defineMario();

        //set initial values for marios location, width and height. And initial frame as marioStand.
        setBounds(0, 0, marioStand.getRegionWidth() / Recursos.PPM, marioStand.getRegionHeight() / Recursos.PPM);
        setRegion(marioStand);

    }

    public void update(float dt){

    	setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        //update sprite with the correct frame depending on marios current action
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        //get marios current state. ie. jumping, running, standing...
        currentState = getState();

        TextureRegion region;

        //depending on the state, get corresponding animation keyFrame.
        switch(currentState){
        	case ATTACKING:
        		region = marioAttack1.getKeyFrame(stateTimer);
        		break;
            case JUMPING:
                region = marioJump.getKeyFrame(stateTimer, true);
                break;
            case RUNNING:
                region = marioRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = marioStand;
                break;
        }

        //if mario is running left and the texture isnt facing left... flip it.
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }

        //if mario is running right and the texture isnt facing right... flip it.
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }

        //if the current state is the same as the previous state increase the state timer.
        //otherwise the state has changed and we need to reset timer.
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        //update previous state
        previousState = currentState;
        //return our final adjusted frame
        return region;

    }

    public State getState(){
        //Test to Box2D for velocity on the X and Y-Axis
        //if mario is going positive in Y-Axis he is jumping... or if he just jumped and is falling remain in jump state
    	if((b2body.getLinearVelocity().y > 0 && currentState == State.JUMPING) || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        //if negative in Y-Axis mario is falling
        else if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        //if mario is positive or negative in the X axis he is running
        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else if(currentState != State.STANDING && b2body.getLinearVelocity().x != 0) {
        	return State.ATTACKING;
        }
        //if none of these return then he must be standing
        else
            return State.STANDING;
    }

    public float getStateTimer(){
        return stateTimer;
    }

    public void jump(){
        if ( currentState != State.JUMPING ) {
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }
    }
    
    public void attack() {
    	currentState = State.ATTACKING;
    }

    public void defineMario(){
    	BodyDef bdef = new BodyDef();
        bdef.position.set(marioStand.getRegionWidth() / FireArms.PPM, marioStand.getRegionHeight() / FireArms.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / FireArms.PPM);
        fdef.filter.categoryBits = FireArms.MARIO_BIT;
        fdef.filter.maskBits = FireArms.GROUND_BIT |
        		FireArms.COIN_BIT |
        		FireArms.BRICK_BIT |
                FireArms.ENEMY_BIT |
                FireArms.OBJECT_BIT |
                FireArms.ENEMY_HEAD_BIT |
                FireArms.ITEM_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
        shape.setPosition(new Vector2(0, -16 / FireArms.PPM));
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-8 / FireArms.PPM, 24 / FireArms.PPM), new Vector2(8 / FireArms.PPM, 24 / FireArms.PPM));
        fdef.filter.categoryBits = FireArms.MARIO_HEAD_BIT;
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData(this);
    }

}
