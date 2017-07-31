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
    public boolean exiting = false;
    private boolean gameOver = false;

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
        dialog.setContent(Resources.GameStartTexts.intro);
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
                map.handleClickEvent(pointerX, pointerY);
            }
        }
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
        lightBox.setProjectionMatrix(camera.combined);
        handleLightBoxes(delta);
        batch.begin();
        renderBuildings();
        desk.render(batch, map, delta);
        dialog.render(batch, map, delta);
        handleInput();
        resetInput();
        if (dialog.isClosed()) {//we only want to update the game when there's no dialog screen.
            if (!gameOver)
                map.update(delta);
            else
                exiting = true;
        }
        if (map.isGameOver() && gameOver == false) {
            handleGameOver();
        }
        if (map.showWarning) {
            batch.draw(Resources.insufficientPower, 200, 215);
        }
        if (map.showDay) {
            dialog.setContent("Welcome to Day " + map.currentDay + "! We managed to repair some of the damage to our energy plant. Our energy allowance is " +
                    "slightly increased.");
            dialog.display();
            map.resetDay();
        }
        batch.end();
    }

    public void handleGameOver() {
        byte losestate = map.losestate;
        String string = "";
        switch (losestate) {
            case Settings.loseState.COIN:
                string += Resources.GameOverTexts.coinLoss;
                break;
            case Settings.loseState.CRIME:
                string += Resources.GameOverTexts.crimeLoss;
                break;
            case Settings.loseState.FOOD:
                string += Resources.GameOverTexts.foodLoss;
                break;
            case Settings.loseState.HAPPY:
                string += Resources.GameOverTexts.happyLoss;
                break;
            default:
                string += "Game over man, game over!";
                break;
        }
        string += "\n\n You managed to survive to Day " + map.currentDay + ".";
        dialog.setContent(string);
        dialog.display();
        gameOver = true;
    }

    public void renderBuildings() {
        for (Building e : map.buildings)
            handleRenderBuilding(e);
    }

    private void handleLightBoxes(float delta) {
        for (Building e : map.buildings)
            e.renderLightBox(lightBox, delta);
    }

    private void handleRenderBuilding(Building building) {
        batch.draw(building.getTexture(), building.getX(), building.getY());
    }

    public void dispose() {
        cache.dispose();
        lightBox.dispose();
        desk.dispose();
    }
}
