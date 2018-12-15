package com.mygdx.game.chapter7.jumpingjack;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.chapter7.base.Box2DActor;

public class Coin extends Box2DActor {
    public Coin() {
        super();
    }

    public void initializePhysics(World world) {
        setStatic();
        setShapeCircle();
        fixtureDef.isSensor = true;
        super.initializePhysics(world);
    }

    public Coin clone() {
        Coin newbie = new Coin();
        newbie.copy( this );
        return newbie;
    }
}