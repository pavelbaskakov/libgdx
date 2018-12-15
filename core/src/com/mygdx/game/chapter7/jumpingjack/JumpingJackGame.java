package com.mygdx.game.chapter7.jumpingjack;

import com.mygdx.game.chapter7.base.BaseGame;

public class JumpingJackGame extends BaseGame {
    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }
}
