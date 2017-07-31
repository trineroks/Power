package com.ludumGame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.ludumGame.GameLogic;
import com.ludumGame.PowerTown;
import com.ludumGame.PowerTownMapRenderer;
import com.ludumGame.Settings;

/**
 * Created by trineroks on 7/31/17.
 */
public class MapEditorScreen extends AbstractScreen implements InputProcessor {

    PowerTownMapRenderer renderer;

    public MapEditorScreen(PowerTown game) {
        super(game);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
        renderer.render(delta);
    }

    @Override
    public void show() {
        renderer = new PowerTownMapRenderer(game.batch);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Input.Keys.Q:
                game.setScreen(new MainMenuScreen(game));
                break;
            case Input.Keys.M:
                renderer.saveToFile();
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        y = Settings.screenHeight - y; //moving origin to bottom left to conform to project
        renderer.keyDown(x,y);
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        y = Settings.screenHeight - y; //moving origin to bottom left to conform to project
        renderer.keyUp(x,y);
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        y = Settings.screenHeight - y; //moving origin to bottom left to conform to project
        renderer.keyDown(x,y);
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
