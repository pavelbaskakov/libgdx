package com.mygdx.game.balloonBuster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.BaseActor;

public class Balloon extends BaseActor {
    public float speed;
    public float amplitude;
    public float oscillation;
    public float initialY;
    public float offsetX;
    public float time;
    public int offset;

    public Balloon() {
        speed = 80 * MathUtils.random(0.5f, 2.0f);
        amplitude = 50 * MathUtils.random(0.5f, 2.0f);
        oscillation = 0.01f * MathUtils.random(0.5f, 2.0f);
        initialY = 120 * MathUtils.random(0.5f, 2.0f);
        time = 0;
        offsetX = -100;
        setTexture(new Texture(Gdx.files.internal("red-balloon.png")));
        // initial spawn location off-screen
        setX(offsetX);
    }

    public void act(float dt) {
        super.act(dt);
        time += dt;
        // set starting location to left of window
        float xPos = speed * time + offsetX;
        float yPos = amplitude * MathUtils.sin(oscillation * xPos) + initialY;
        setPosition(xPos, yPos);
    }
}