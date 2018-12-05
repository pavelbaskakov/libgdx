package com.mygdx.game.Turtle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.*;
import com.mygdx.game.BaseGame;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.*;

public class TurtleGame extends BaseGame {
    public void create() {
        // initialize resources common to multiple screens and store to skin database
        BitmapFont uiFont = new BitmapFont(Gdx.files.internal("cooper.fnt"));
        uiFont.getRegion().getTexture().setFilter(Linear, Linear);
        skin.add("uiFont", uiFont);
        LabelStyle uiLabelStyle = new LabelStyle(uiFont, Color.BLUE);
        skin.add("uiLabelStyle", uiLabelStyle);
        // initialize and start main game
        TurtleLevel tl = new TurtleLevel(this);
        setScreen(tl);
    }
}
