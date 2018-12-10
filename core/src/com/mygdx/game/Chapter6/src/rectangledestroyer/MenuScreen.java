package com.mygdx.game.Chapter6.src.rectangledestroyer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import com.mygdx.game.Chapter6.src.base_A.*;


/**
 * Created by robertoguazon on 30/07/2016.
 */
public class MenuScreen extends BaseScreen {

    public MenuScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void create() {
        Label title = new Label("RECTANGLE DESTROYER", game.skin, "labelStyle");
        title.setFontScale(2);

        Label clickInfo = new Label("Click to PLAY", game.skin, "labelStyle");
        clickInfo.setFontScale(1.5f);
        clickInfo.setColor(Color.ORANGE);
        clickInfo.addAction(
                Actions.forever(
                        Actions.sequence(
                                Actions.alpha(0,1.5f),
                                Actions.alpha(1,1.5f)
                        )
                )
        );

        uiTable.add(title);
        uiTable.row();
        uiTable.add(clickInfo).padTop(30);
    }

    @Override
    public void update(float dt) {

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
        }

    }

    @Override
    public void hide() {
        uiTable.clearChildren();
    }
}
