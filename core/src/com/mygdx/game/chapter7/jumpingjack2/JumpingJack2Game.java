package com.mygdx.game.chapter7.jumpingjack2;

import com.mygdx.game.chapter7.base.BaseGame;

public class JumpingJack2Game extends BaseGame {
    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }
}
