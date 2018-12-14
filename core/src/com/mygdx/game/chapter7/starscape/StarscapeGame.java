package com.mygdx.game.chapter7.starscape;

import com.mygdx.game.chapter7.base.BaseGame;

public class StarscapeGame extends BaseGame {

    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }

}
