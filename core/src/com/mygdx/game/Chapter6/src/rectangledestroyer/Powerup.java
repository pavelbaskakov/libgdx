package com.mygdx.game.Chapter6.src.rectangledestroyer;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

import com.mygdx.game.Chapter6.src.base_A.*;

/**
 * Created by robertoguazon on 30/07/2016.
 */
public class Powerup extends PhysicsActor {

    public Powerup() {
        super();
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public Powerup clone() {
        Powerup newbie = new Powerup();
        newbie.copy(this);
        return newbie;
    }

    public boolean overlaps(Paddle other) {
        return Intersector.overlaps(this.getRectangle(), other.getRectangle());
    }

    //randomly select one of the stored animations
    public void randomize() {
        ArrayList<String> names = new ArrayList<>(getAnimationStorage().keySet());
        int n = MathUtils.random(names.size() - 1);
        setActiveAnimation(names.get(n));
    }

}
