package com.mygdx.game.balloonBuster;

import com.badlogic.gdx.Game;

public class BalloonGame extends Game {
    public void create() {
        BalloonLevel z = new BalloonLevel(this);
        setScreen(z);
    }
}
