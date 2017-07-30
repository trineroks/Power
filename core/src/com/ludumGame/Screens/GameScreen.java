package com.ludumGame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.ludumGame.Map;
import com.ludumGame.PowerTown;
import com.ludumGame.PowerTownRenderer;
import com.ludumGame.Settings;

public class GameScreen extends AbstractScreen implements InputProcessor {

    Map map;
    PowerTownRenderer renderer;

    public GameScreen(PowerTown game) {
        super(game);
        Gdx.input.setInputProcessor(this);
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
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        y = Settings.screenHeight - y; //moving origin to bottom left to conform to project
        renderer.keyDown(x, y);
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        y = Settings.screenHeight - y; //moving origin to bottom left to conform to project
        renderer.keyUp(x, y);
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        y = Settings.screenHeight - y; //moving origin to bottom left to conform to project
        renderer.keyDown(x, y);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

}