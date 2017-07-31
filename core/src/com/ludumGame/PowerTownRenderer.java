package com.ludumGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ludumGame.Buildings.Building;

/**
 * Created by trineroks on 7/29/17.
 */
public class PowerTownRenderer {
    OrthographicCamera camera;
    SpriteCache cache;
    ShapeRenderer lightBox;
    Map map;
    int tileID;
    NinePatchDialog dialog;
    inputState input;
    SpriteBatch batch;

    DeskRenderer desk;

    int pointerX, pointerY;

    public enum inputState {
        DEFAULT,
        KEYDOWN,
        KEYUP
    }

    public PowerTownRenderer(Map map, SpriteBatch batch) {
        this.map = map;
        this.batch = batch;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Settings.screenWidth, Settings.screenHeight);
        this.cache = new SpriteCache(map.tiles.length * map.tiles[0].length, false);
        this.lightBox = new ShapeRenderer();
        this.dialog = new NinePatchDialog();
        this.desk = new DeskRenderer();
        dialog.setContent("Welcome to PowerTown! You are mayor of a town that is limited on power. Make sure to budget your power wisely between buildings to maintain high approval ratings!");
        dialog.display();
        this.input = inputState.DEFAULT;
        pointerX = -1;
        pointerY = -1;
        this.tileID = map.generateTerrain(cache);
        map.generateBuildings();
    }

    public void handleInput() {
        if (!dialog.isClosed()) { //ensure no input goes through when a dialog pops up
            if (dialog.isOpened() && input == inputState.KEYUP) {
                dialog.closeDialog();
            }
        }
        else {
            if (input == inputState.KEYUP) {
                if (map.building.isClicked(pointerX, pointerY)) {
                    map.building.togglePowerState();
                }
            }
        }
//        else if (dialog.isClosed() && input == inputState.KEYDOWN) {
//            dialog.setContent("I like your style!");
//            dialog.display();
//        }
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

    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        cache.setProjectionMatrix(camera.combined);
        cache.begin();
        cache.draw(tileID);
        cache.end();
        batch.setProjectionMatrix(camera.combined);
        handleLightBoxes(delta);
        batch.begin();
        renderBuildings();
        desk.render(batch, map, delta);
        dialog.render(batch, map, delta);
        batch.end();
        handleInput();
        resetInput();
        map.update(delta);
    }

    public void renderBuildings() {
        lightBox.setProjectionMatrix(camera.combined);
        handleRenderBuilding(map.building);
    }

    private void handleLightBoxes(float delta) {
        map.building.renderLightBox(lightBox, delta);
    }

    private void handleRenderBuilding(Building building) {
        batch.draw(building.getTexture(), building.getX(), building.getY());
    }
}
