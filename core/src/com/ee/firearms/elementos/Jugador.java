package com.ee.firearms.elementos;

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
import com.ee.firearms.pantallas.PantallaUnJugador;
import com.ee.firearms.utiles.Constantes;

public class Jugador extends Sprite {
	
    public enum State { FALLING, JUMPING, STANDING, RUNNING, DEAD, ATTACKING };
    public State currentState;
    public State previousState;

    public World world;
    public Body b2body;

    private TextureRegion playerStand;
    private Animation<TextureRegion> playerRun;
    private Animation<TextureRegion> playerJump;
    private Animation<TextureRegion> playerAttack;
//    private Animation<TextureRegion> marioIdle;
//    private Animation<TextureRegion> marioAttack;

    private float stateTimer;
    private boolean runningRight;

    public Jugador(PantallaUnJugador screen){
    	
        this.world = screen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 1; i < 6; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("Run"), i * 47, 0, 47, 49));
        }
        playerRun = new Animation<TextureRegion>(0.1f, frames);

        frames.clear();

        // get attacking animation frames and add them to marioAttack Animation
//        for(int i = 0; i < 4; i++) {
//        	frames.add(new TextureRegion(screen.getAtlas().findRegion("Attack1"), i * 16, 0, 16, 32));
//        }
//        marioAttack = new Animation<TextureRegion>(0.2f, frames);
//
//        frames.clear();

        for(int i = 1; i < 3; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("Jump"), i * 42, 0, 42, 56));
        }
        playerJump = new Animation<TextureRegion>(1f, frames);
        
        frames.clear();
        
        for(int i = 1; i < 5; i++) {
        	if(i == 3) {
        		frames.add(new TextureRegion(screen.getAtlas().findRegion("Attack1"), 94, 0, 94, 65));
        	} else {
        		frames.add(new TextureRegion(screen.getAtlas().findRegion("Attack1"), i * 48, 0, 48, 65));
        	}
        }
        playerAttack = new Animation<TextureRegion>(0.1f, frames);
        
        playerStand = new TextureRegion(screen.getAtlas().findRegion("Idle"), 0, 0, 44, 56);

        // Box2d
        definePlayer();

        setBounds(0, 0, playerStand.getRegionWidth() / Constantes.PPM, playerStand.getRegionHeight() / Constantes.PPM);
        setRegion(playerStand);

    }

    public void update(float dt){
    	setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;

        switch(currentState){
        	case ATTACKING:
        		region = playerAttack.getKeyFrame(stateTimer);
        		break;
            case JUMPING:
                region = playerJump.getKeyFrame(stateTimer, true);
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

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }

        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;

    }

    public State getState(){
       // Eje Y positivo -> Saltando
    	if((b2body.getLinearVelocity().y > 0 && currentState == State.JUMPING) || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        // Eje Y negativo -> Cayendo
        else if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        // Corriendo
        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
    	// TODO
        else if(currentState != State.STANDING && b2body.getLinearVelocity().x != 0) {
        	return State.ATTACKING;
        }
        // Quieto
        else
            return State.STANDING;
    }

    public float getStateTimer(){
        return stateTimer;
    }

    public void jump(){
        if ( currentState != State.JUMPING ) {
            b2body.applyLinearImpulse(new Vector2(0, 4.5f), b2body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }
    }
    
    public void attack() {
    	currentState = State.ATTACKING;
    }

    public void definePlayer(){
    	BodyDef bdef = new BodyDef();
        bdef.position.set(playerStand.getRegionWidth() / Constantes.PPM, playerStand.getRegionHeight() / Constantes.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / Constantes.PPM);
        fdef.filter.categoryBits = Constantes.PLAYER_BIT;
        fdef.filter.maskBits = Constantes.GROUND_BIT |
        		Constantes.COIN_BIT |
        		Constantes.BRICK_BIT |
                Constantes.ENEMY_BIT |
                Constantes.OBJECT_BIT |
                Constantes.ENEMY_HEAD_BIT |
                Constantes.ITEM_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
        shape.setPosition(new Vector2(0, -16 / Constantes.PPM));
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-8 / Constantes.PPM, 24 / Constantes.PPM), new Vector2(8 / Constantes.PPM, 24 / Constantes.PPM));
        fdef.filter.categoryBits = Constantes.PLAYER_HEAD_BIT;
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData(this);
    }

}
