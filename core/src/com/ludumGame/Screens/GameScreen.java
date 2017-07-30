package com.ludumGame.Screens;

import com.badlogic.gdx.Gdx;
import com.ludumGame.Map;
import com.ludumGame.PowerTown;
import com.ludumGame.PowerTownRenderer;

public class GameScreen extends AbstractScreen {

    Map map;
    PowerTownRenderer renderer;

    public GameScreen(PowerTown game) {
        super(game);
    }

    @Override
    public void show() {
        map = new Map();
        map.generate();
        renderer = new PowerTownRenderer(map);
    }

    @Override
    public void render(float delta) {
        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
        renderer.render(delta);
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