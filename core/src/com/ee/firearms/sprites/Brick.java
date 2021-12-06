package com.ee.firearms.sprites;

import com.badlogic.gdx.maps.MapObject;
import com.ee.firearms.FireArms;
import com.ee.firearms.test2.Mario;
import com.ee.firearms.test2.PlayScreen;

public class Brick extends InteractiveTileObject {
    public Brick(PlayScreen screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(FireArms.BRICK_BIT);
    }

    @Override
    public void onHeadHit(Mario mario) {
//        if(mario.isBig()) {
            setCategoryFilter(FireArms.DESTROYED_BIT);
            getCell().setTile(null);
//            Hud.addScore(200);
//            FireArms.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
//        }
//        MarioBros.manager.get("audio/sounds/bump.wav", Sound.class).play();
    }

}