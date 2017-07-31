package com.ludumGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
    TextureRegion currentTexture;

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

    public void cleanMap() {
        map.generateDefaultMap();
        map.writeToFile();
        map.readFromFile();
    }

    public void setSelectedTile(int selectedTile) {
        this.selectedTile = selectedTile;
    }

    public int getSelectedTile() {
        return selectedTile;
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
        selectedTile = Tile.grass;
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
                    default:
                        break;
                }
            }
        }
    }

    private void renderTopLayerMap() {
        for (int y = 0; y < Settings.blocksHeight; y++) {
            for (int x = 0; x < Settings.blocksWidth; x++) {
                int posY = Settings.gameBoardHeight - heightToPixel(y) - Settings.tilePixelHeight;
                int posX = widthToPixel(x);
                switch (map.getMap()[(y*Settings.blocksWidth) + x]) {
                    case Tile.stop: batch.draw(Resources.stop, posX, posY);
                        break;
                    case Tile.flower: batch.draw(Resources.flower, posX, posY);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void renderBuildings() {
        for (int y = 0; y < Settings.blocksHeight; y++) {
            for (int x = 0; x < Settings.blocksWidth; x++) {
                int posY = Settings.gameBoardHeight - heightToPixel(y) - Settings.tilePixelHeight;
                int posX = widthToPixel(x);
                switch (map.getMap()[(y * Settings.blocksWidth) + x]) {
                    case Tile.factory:
                        currentTexture = Resources.factory;
                        break;
                    case Tile.police:
                        currentTexture = Resources.police;
                        break;
                    case Tile.pub:
                        currentTexture = Resources.pub;
                        break;
                    case Tile.supermarket:
                        currentTexture = Resources.supermarket;
                        break;
                    case Tile.restaurant:
                        currentTexture = Resources.restaurant;
                        break;
                    case Tile.bank:
                        currentTexture = Resources.bank;
                        break;
                    case Tile.bowling:
                        currentTexture = Resources.bowling;
                        break;
                    case Tile.cafe:
                        currentTexture = Resources.cafe;
                        break;
                    case Tile.courthouse:
                        currentTexture = Resources.courthouse;
                        break;
                    case Tile.arcade:
                        currentTexture = Resources.arcade;
                        break;
                    case Tile.burger:
                        currentTexture = Resources.burger;
                        break;
                    case Tile.departmentstore:
                        currentTexture = Resources.departmentstore;
                        break;
                    case Tile.donuts:
                        currentTexture = Resources.donuts;
                        break;
                    case Tile.icecream:
                        currentTexture = Resources.icecream;
                        break;
                    case Tile.school:
                        currentTexture = Resources.school;
                        break;
                    case Tile.sheriff:
                        currentTexture = Resources.sheriff;
                        break;
                    case Tile.shirts:
                        currentTexture = Resources.shirts;
                        break;
                    default:
                        currentTexture = null;
                        break;
                }
                if (currentTexture != null)
                    batch.draw(currentTexture, posX, posY);
            }
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
        Gdx.gl.glClearColor(0,0,255,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        convertToGameCoords();
        batch.begin();
        renderMap();
        renderSelectedTile();
        renderBuildings();
        renderTopLayerMap();
        batch.end();
        output();
        handleInput();
        resetInput();
    }

    private void output() {
        System.out.println("" + gameCoordX + "," + gameCoordY);
    }

    private void renderSelectedTile() {
        switch (selectedTile) {
            case Tile.grass:
                currentTexture = Resources.grass;
                break;
            case Tile.road:
                currentTexture = Resources.road;
                break;
            case Tile.stop:
                currentTexture = Resources.stop;
                break;
            case Tile.flower:
                currentTexture = Resources.flower;
                break;
            case Tile.factory:
                currentTexture = Resources.factory;
                break;
            case Tile.police:
                currentTexture = Resources.police;
                break;
            case Tile.pub:
                currentTexture = Resources.pub;
                break;
            case Tile.supermarket:
                currentTexture = Resources.supermarket;
                break;
            case Tile.restaurant:
                currentTexture = Resources.restaurant;
                break;
            case Tile.bank:
                currentTexture = Resources.bank;
                break;
            case Tile.bowling:
                currentTexture = Resources.bowling;
                break;
            case Tile.cafe:
                currentTexture = Resources.cafe;
                break;
            case Tile.courthouse:
                currentTexture = Resources.courthouse;
                break;
            case Tile.arcade:
                currentTexture = Resources.arcade;
                break;
            case Tile.burger:
                currentTexture = Resources.burger;
                break;
            case Tile.departmentstore:
                currentTexture = Resources.departmentstore;
                break;
            case Tile.donuts:
                currentTexture = Resources.donuts;
                break;
            case Tile.icecream:
                currentTexture = Resources.icecream;
                break;
            case Tile.school:
                currentTexture = Resources.school;
                break;
            case Tile.sheriff:
                currentTexture = Resources.sheriff;
                break;
            case Tile.shirts:
                currentTexture = Resources.shirts;
                break;
            default:
                currentTexture = Resources.grass;
                break;
        }
        batch.draw(currentTexture, Settings.screenWidth/2, Settings.gameBoardHeight + 10);
    }
}
