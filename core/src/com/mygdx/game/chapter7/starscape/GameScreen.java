package com.mygdx.game.chapter7.starscape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.chapter7.base.BaseActor;
import com.mygdx.game.chapter7.base.BaseGame;
import com.mygdx.game.chapter7.base.BaseScreen;
import com.mygdx.game.chapter7.base.PhysicsActor;

public class GameScreen extends BaseScreen {
    private PhysicsActor spaceship;
    private ParticleActor thruster;
    private ParticleActor baseExplosion;
    public GameScreen(BaseGame g) {
        super(g);
    }

    public void create() {
        BaseActor background = new BaseActor();
        background.setTexture(new Texture(Gdx.files.internal("space.png")));
        background.setPosition(0, 0);
        mainStage.addActor(background);
        spaceship = new PhysicsActor();
        Texture shipTex = new Texture(Gdx.files.internal("spaceship.png"));
        shipTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        spaceship.storeAnimation( "default", shipTex );
        spaceship.setPosition(400, 300);
        spaceship.setOriginCenter();
        spaceship.setMaxSpeed(200);
        spaceship.setDeceleration(20);
        mainStage.addActor(spaceship);
        thruster = new ParticleActor();
        thruster.load("starscape/thruster.pfx", "starscape/");
        BaseActor thrusterAdjuster = new BaseActor();
        thrusterAdjuster.setTexture(new Texture(Gdx.files.internal("starscape/blank.png")));
        thrusterAdjuster.addActor(thruster);
        thrusterAdjuster.setPosition(0,32);
        thrusterAdjuster.setRotation(90);
        thrusterAdjuster.setScale(0.25f);
        spaceship.addActor(thrusterAdjuster);
        baseExplosion = new ParticleActor();
        baseExplosion.load("starscape/explosion.pfx", "starscape/");
    }

    public void update(float dt) {
        spaceship.setAccelerationXY(0,0);
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            spaceship.rotateBy(180 * dt);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            spaceship.rotateBy(-180 * dt);
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            spaceship.addAccelerationAS(spaceship.getRotation(), 100);
            thruster.start();
        } else {
            thruster.stop();
        }
    }

    public boolean keyDown(int keycode) {
        if (keycode == Keys.P)
            togglePaused();
        if (keycode == Keys.R)
            game.setScreen(new GameScreen(game));
        if (keycode == Keys.SPACE) {
            ParticleActor explosion = baseExplosion.clone();
            explosion.setPosition( MathUtils.random(800), MathUtils.random(600) );
            explosion.start();
            mainStage.addActor(explosion);
        }
        return false;
    }
}
