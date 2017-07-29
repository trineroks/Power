package com.ludumGame.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Created by trineroks on 7/29/17.
 */
public abstract class AbstractScreen implements Screen {
    Game game;

    public AbstractScreen (Game game) {
        this.game = game;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
