package com.mygdx.game.Chapter6.src.rectangledestroyer;

import com.badlogic.gdx.math.Rectangle;

import com.mygdx.game.Chapter6.src.base_A.*;

;

/**
 * Created by robertoguazon on 29/07/2016.
 */
public class Paddle extends BaseActor {

    public Paddle() {
        super();
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
