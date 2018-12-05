package com.mygdx.game.Chapter4.base_gamepad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by robertoguazon on 06/07/2016.
 */
public abstract class BaseScreen implements Screen, InputProcessor{

    protected BaseGame game;
    protected Stage mainStage;
    protected Stage uiStage;
    protected Table uiTable;

    public final int viewWidth = 800;
    public final int viewHeight = 600;

    private boolean paused;

    public BaseScreen(BaseGame game) {
        this.game = game;

        mainStage = new Stage(new FitViewport(viewWidth,viewHeight));
        uiStage = new Stage(new FitViewport(viewWidth, viewHeight));

        paused = false;

        InputMultiplexer im = new InputMultiplexer(this, uiStage,mainStage);
        Gdx.input.setInputProcessor(im);

        uiTable = new Table();
        uiTable.setFillParent(true);
        uiStage.addActor(uiTable);

        create();
    }

    public abstract void create();
    public abstract void update(float dt);

    //game loop code; update, then render
    @Override
    public void render(float dt) {
        uiStage.act(dt);

        //only pause gameplay events, not UI events
        if (!isPaused()) {
            mainStage.act(dt);
            update(dt);
        }

        //render
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.draw();
        uiStage.draw();
    }

    //pause methods
    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void togglePaused() {
        this.paused = !this.paused;
    }


    public void resize(int width, int height) {
        mainStage.getViewport().update(width,height, true);
        uiStage.getViewport().update(width,height, true);
    }

    //methods required by screen interface
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void dispose() {}
    @Override public void show() {}
    @Override public void hide() {}

    // methods required by InputProcessor interface
    @Override
    public boolean keyDown(int keyCode) {
        return false;
    }

    @Override
    public boolean keyUp(int keyCode) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

}
