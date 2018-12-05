package com.mygdx.game.balloonBuster;

import com.badlogic.gdx.Game;
import com.mygdx.game.BaseGame;

public class BalloonGame extends BaseGame {
    public void create() {
        BalloonLevel z = new BalloonLevel(this);
        setScreen(z);
    }
}
