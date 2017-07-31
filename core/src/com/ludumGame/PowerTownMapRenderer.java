package com.ludumGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by trineroks on 7/31/17.
 */
public class PowerTownMapRenderer {
    Map map;
    SpriteBatch batch;
    OrthographicCamera camera;
    int pointerX, pointerY;
    int gameCoordX, gameCoordY;
    inputState input;
    int selectedTile;

    public enum inputState {
        DEFAULT,
        KEYDOWN,
        KEYUP
    }

    public int widthToPixel(int x) {
        int posX = x * Settings.tilePixelWidth;
        return posX;
    }

    public int heightToPixel(int y) {
        int posY = y * Settings.tilePixelHeight;
        return posY;
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

    private void handleInput() {
        if (input == inputState.KEYDOWN) {
            map.getMap()[((Settings.blocksHeight - gameCoordY - 1) * Settings.blocksWidth) + gameCoordX] = selectedTile;
        }
    }

    public void saveToFile() {
        map.writeToFile();
        System.out.println("Map saved!");
    }

    public PowerTownMapRenderer(SpriteBatch batch) {
        this.batch = batch;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Settings.screenWidth, Settings.screenHeight);
        this.map = new Map();
        this.input = inputState.DEFAULT;
        pointerX = -1;
        pointerY = -1;
        gameCoordX = -1;
        gameCoordY = -1;
        selectedTile = Tile.road;
        map.readFromFile();
    }

    private void renderMap() {
        for (int y = 0; y < Settings.blocksHeight; y++) {
            for (int x = 0; x < Settings.blocksWidth; x++) {
                int posY = Settings.gameBoardHeight - heightToPixel(y) - Settings.tilePixelHeight;
                int posX = widthToPixel(x);
                switch (map.getMap()[(y*Settings.blocksWidth) + x]) {
                    case Tile.grass: batch.draw(Resources.grass, posX, posY);
                        break;
                    case Tile.road: batch.draw(Resources.road, posX, posY);
                        break;
                    default: break;
                }
            }
        }
    }

    private void renderBuildings() {

    }

    private void renderSelectedTile() {
        switch (selectedTile) {
            case Tile.grass:
        }
    }

    private void convertToGameCoords() {
        if (pointerX >= Settings.gameBoardWidth || pointerX < 0 || pointerY >= Settings.gameBoardHeight || pointerY < 0) {
            gameCoordX = -1;
            gameCoordY = -1;
        }
        else {
            gameCoordX = pointerX / Settings.tilePixelWidth;
            gameCoordY = pointerY / Settings.tilePixelHeight;
        }
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        convertToGameCoords();
        batch.begin();
        renderMap();
        renderSelectedTile();
        batch.end();
        output();
        handleInput();
        resetInput();
    }

    private void output() {
        System.out.println("" + gameCoordX + "," + gameCoordY);
    }
}
