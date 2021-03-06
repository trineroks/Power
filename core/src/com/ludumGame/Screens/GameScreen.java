package com.ludumGame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.ludumGame.GameLogic;
import com.ludumGame.PowerTown;
import com.ludumGame.PowerTownRenderer;
import com.ludumGame.Settings;

public class GameScreen extends AbstractScreen implements InputProcessor {

    GameLogic gameLogic;
    PowerTownRenderer renderer;

    public GameScreen(PowerTown game, GameLogic gameLogic) {
        super(game);
        this.gameLogic = gameLogic;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {
        renderer = new PowerTownRenderer(gameLogic, game.batch);
    }

    @Override
    public void hide() {
        renderer.dispose();
    }

    @Override
    public void render(float delta) {
        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
        renderer.render(delta);
        if (renderer.exiting) {
            game.setScreen(new MainMenuScreen(game));
        }
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