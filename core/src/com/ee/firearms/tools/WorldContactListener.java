package com.ee.firearms.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.ee.firearms.FireArms;
import com.ee.firearms.sprites.InteractiveTileObject;
import com.ee.firearms.test2.Mario;

public class WorldContactListener implements ContactListener {
	
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef){
            case FireArms.MARIO_HEAD_BIT | FireArms.BRICK_BIT:
            case FireArms.MARIO_HEAD_BIT | FireArms.COIN_BIT:
                if(fixA.getFilterData().categoryBits == FireArms.MARIO_HEAD_BIT)
                    ((InteractiveTileObject) fixB.getUserData()).onHeadHit((Mario) fixA.getUserData());
                else
                    ((InteractiveTileObject) fixA.getUserData()).onHeadHit((Mario) fixB.getUserData());
                break;
//            case FireArms.ENEMY_HEAD_BIT | FireArms.MARIO_BIT:
//                if(fixA.getFilterData().categoryBits == FireArms.ENEMY_HEAD_BIT)
//                    ((Enemy)fixA.getUserData()).hitOnHead((Mario) fixB.getUserData());
//                else
//                    ((Enemy)fixB.getUserData()).hitOnHead((Mario) fixA.getUserData());
//                break;
//            case FireArms.ENEMY_BIT | FireArms.OBJECT_BIT:
//                if(fixA.getFilterData().categoryBits == FireArms.ENEMY_BIT)
//                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
//                else
//                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
//                break;
//            case FireArms.MARIO_BIT | FireArms.ENEMY_BIT:
//                if(fixA.getFilterData().categoryBits == FireArms.MARIO_BIT)
//                    ((Mario) fixA.getUserData()).hit((Enemy)fixB.getUserData());
//                else
//                    ((Mario) fixB.getUserData()).hit((Enemy)fixA.getUserData());
//                break;
//            case FireArms.ENEMY_BIT | FireArms.ENEMY_BIT:
//                ((Enemy)fixA.getUserData()).hitByEnemy((Enemy)fixB.getUserData());
//                ((Enemy)fixB.getUserData()).hitByEnemy((Enemy)fixA.getUserData());
//                break;
//            case FireArms.ITEM_BIT | FireArms.OBJECT_BIT:
//                if(fixA.getFilterData().categoryBits == FireArms.ITEM_BIT)
//                    ((Item)fixA.getUserData()).reverseVelocity(true, false);
//                else
//                    ((Item)fixB.getUserData()).reverseVelocity(true, false);
//                break;
//            case FireArms.ITEM_BIT | FireArms.MARIO_BIT:
//                if(fixA.getFilterData().categoryBits == FireArms.ITEM_BIT)
//                    ((Item)fixA.getUserData()).use((Mario) fixB.getUserData());
//                else
//                    ((Item)fixB.getUserData()).use((Mario) fixA.getUserData());
//                break;
//            case FireArms.FIREBALL_BIT | FireArms.OBJECT_BIT:
//                if(fixA.getFilterData().categoryBits == FireArms.FIREBALL_BIT)
//                    ((FireBall)fixA.getUserData()).setToDestroy();
//                else
//                    ((FireBall)fixB.getUserData()).setToDestroy();
//                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
    	
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    	
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    	
    }
}
