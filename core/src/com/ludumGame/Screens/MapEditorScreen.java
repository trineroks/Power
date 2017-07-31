package com.ludumGame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.ludumGame.*;

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
            case Input.Keys.ESCAPE:
                game.setScreen(new MainMenuScreen(game));
                break;
            case Input.Keys.M:
                renderer.saveToFile();
                break;
            case Input.Keys.NUM_1:
                setSelectedTile(Tile.grass);
                break;
            case Input.Keys.NUM_2:
                setSelectedTile(Tile.road);
                break;
            case Input.Keys.Q:
                setSelectedTile(Tile.arcade);
                break;
            case Input.Keys.W:
                setSelectedTile(Tile.burger);
                break;
            case Input.Keys.E:
                setSelectedTile(Tile.departmentstore);
                break;
            case Input.Keys.R:
                setSelectedTile(Tile.donuts);
                break;
            case Input.Keys.T:
                setSelectedTile(Tile.icecream);
                break;
            case Input.Keys.Y:
                setSelectedTile(Tile.school);
                break;
            case Input.Keys.U:
                setSelectedTile(Tile.sheriff);
                break;
            case Input.Keys.I:
                setSelectedTile(Tile.shirts);
                break;
            case Input.Keys.O:
                setSelectedTile(Tile.factory);
                break;
            case Input.Keys.P:
                setSelectedTile(Tile.police);
                break;
            case Input.Keys.A:
                setSelectedTile(Tile.pub);
                break;
            case Input.Keys.S:
                setSelectedTile(Tile.supermarket);
                break;
            case Input.Keys.D:
                setSelectedTile(Tile.restaurant);
                break;
            case Input.Keys.F:
                setSelectedTile(Tile.bank);
                break;
            case Input.Keys.G:
                setSelectedTile(Tile.bowling);
                break;
            case Input.Keys.H:
                setSelectedTile(Tile.cafe);
                break;
            case Input.Keys.J:
                setSelectedTile(Tile.courthouse);
                break;
            case Input.Keys.NUM_0:
                renderer.cleanMap();
                break;
            default:
                break;
        }
        return false;
    }

    private void setSelectedTile(int tile) {
        renderer.setSelectedTile(tile);
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
        int tile = renderer.getSelectedTile();
        if (tile == Tile.grass || tile == Tile.road)
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
