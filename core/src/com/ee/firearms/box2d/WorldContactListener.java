package com.ee.firearms.box2d;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.ee.firearms.elementos.Jugador;
import com.ee.firearms.utiles.Constantes;

public class WorldContactListener implements ContactListener {
	
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef){
            case Constantes.PLAYER_HEAD_BIT | Constantes.BRICK_BIT:
            case Constantes.PLAYER_HEAD_BIT | Constantes.COIN_BIT:
                if(fixA.getFilterData().categoryBits == Constantes.PLAYER_HEAD_BIT)
                    ((InteractiveTileObject) fixB.getUserData()).onHeadHit((Jugador) fixA.getUserData());
                else
                    ((InteractiveTileObject) fixA.getUserData()).onHeadHit((Jugador) fixB.getUserData());
                break;
//            case Constantes.ENEMY_HEAD_BIT | Constantes.MARIO_BIT:
//                if(fixA.getFilterData().categoryBits == Constantes.ENEMY_HEAD_BIT)
//                    ((Enemy)fixA.getUserData()).hitOnHead((Mario) fixB.getUserData());
//                else
//                    ((Enemy)fixB.getUserData()).hitOnHead((Mario) fixA.getUserData());
//                break;
//            case Constantes.ENEMY_BIT | Constantes.OBJECT_BIT:
//                if(fixA.getFilterData().categoryBits == Constantes.ENEMY_BIT)
//                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
//                else
//                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
//                break;
//            case Constantes.MARIO_BIT | Constantes.ENEMY_BIT:
//                if(fixA.getFilterData().categoryBits == Constantes.MARIO_BIT)
//                    ((Mario) fixA.getUserData()).hit((Enemy)fixB.getUserData());
//                else
//                    ((Mario) fixB.getUserData()).hit((Enemy)fixA.getUserData());
//                break;
//            case Constantes.ENEMY_BIT | Constantes.ENEMY_BIT:
//                ((Enemy)fixA.getUserData()).hitByEnemy((Enemy)fixB.getUserData());
//                ((Enemy)fixB.getUserData()).hitByEnemy((Enemy)fixA.getUserData());
//                break;
//            case Constantes.ITEM_BIT | Constantes.OBJECT_BIT:
//                if(fixA.getFilterData().categoryBits == Constantes.ITEM_BIT)
//                    ((Item)fixA.getUserData()).reverseVelocity(true, false);
//                else
//                    ((Item)fixB.getUserData()).reverseVelocity(true, false);
//                break;
//            case Constantes.ITEM_BIT | Constantes.MARIO_BIT:
//                if(fixA.getFilterData().categoryBits == Constantes.ITEM_BIT)
//                    ((Item)fixA.getUserData()).use((Mario) fixB.getUserData());
//                else
//                    ((Item)fixB.getUserData()).use((Mario) fixA.getUserData());
//                break;
//            case Constantes.FIREBALL_BIT | Constantes.OBJECT_BIT:
//                if(fixA.getFilterData().categoryBits == Constantes.FIREBALL_BIT)
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
