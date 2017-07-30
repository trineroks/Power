package com.ludumGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;

/**
 * Created by trineroks on 7/29/17.
 */
public class PowerTownRenderer {
    OrthographicCamera camera;
    SpriteCache cache;
    SpriteBatch batch = new SpriteBatch();
    Map map;
    int tileID;
    NinePatchDialog dialog;
    inputState input;

    int pointerX, pointerY;

    public enum inputState {
        DEFAULT,
        KEYDOWN,
        KEYUP
    }

    public PowerTownRenderer(Map map) {
        this.map = map;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Settings.screenWidth, Settings.screenHeight);
        this.cache = new SpriteCache(map.tiles.length * map.tiles[0].length, false);
        this.dialog = new NinePatchDialog();
        dialog.setContent("Welcome to PowerTown! You are mayor of a town that is limited on power. Make sure to budget your power wisely between buildings to maintain high approval ratings!");
        dialog.display();
        this.input = inputState.DEFAULT;
        this.tileID = map.generateTerrain(cache);
    }

    public void keyDown(int x, int y) {
        input = inputState.KEYDOWN;
        pointerX = x;
        pointerY = y;
    }

    public void keyUp(int x, int y) {
        input = inputState.KEYUP;
        pointerX = x;
        pointerY = y;
    }

    private void resetInput() {
        if (input == inputState.KEYUP) {
            input = inputState.DEFAULT;
            pointerX = -1;
            pointerY = -1;
        }
    }

    public void createMap() {

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        cache.setProjectionMatrix(camera.combined);
        cache.begin();
        cache.draw(tileID);
        cache.end();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //renderBuildings();
        dialog.render(batch, map, delta);
        batch.end();
        handleInput();
        resetInput();
    }

    public void handleInput() {
        if (dialog.isOpened() && input == inputState.KEYUP) {
            dialog.closeDialog();
        }
        else if (dialog.isClosed() && input == inputState.KEYDOWN) {
            dialog.setContent("I like your style!");
            dialog.display();
        }
    }

}
