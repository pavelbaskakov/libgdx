package com.mygdx.game.cheesePlease;
import com.badlogic.gdx.Game;

public class CheeseGame extends Game {
    public void create() {
        CheeseMenu cm = new CheeseMenu(this);
        setScreen(cm);
    }
}