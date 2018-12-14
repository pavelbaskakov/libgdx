package com.mygdx.game.chapter7.treasurequest;

import com.mygdx.game.chapter7.base.BaseGame;

public class TreasureQuestGame extends BaseGame {
    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }
}
