package com.ludumGame;

import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.ludumGame.Buildings.Building;
import com.ludumGame.Buildings.FoodBuilding;

import java.util.ArrayList;

/**
 * Created by trineroks on 7/29/17.
 */
public class Map {
    public int[][] tiles;
    public ArrayList<Building> buildings = new ArrayList<Building>();

    private int power, hunger, crime, happy, coin;
    private int powerCounter; //to animate the actual power going up and down.

    private float second;
    private boolean gameOver = false;
    public byte losestate;

    public Map() {
        powerCounter = 100;
        power = 100;
        hunger = 100;
        crime = 0;
        happy = 100;
        coin = 100;
        losestate = Settings.loseState.PLAYING;
        second = 0;
    }

    public int getPower() {
        return power;
    }

    public int getPowerCounter() {
        return powerCounter;
    }

    public int getHunger() {
        return hunger;
    }

    public int getCrime() {
        return crime;
    }

    public int getHappy() {
        return happy;
    }

    public int getCoin() {
        return coin;
    }

    public int widthToPixel(int x) {
        int posX = x * Settings.tilePixelWidth;
        return posX;
    }

    public int heightToPixel(int y) {
        int posY = y * Settings.tilePixelHeight;
        return posY;
    }

    public void generateBuildings() {
        Building building = new FoodBuilding(Resources.supermarket);
        building.setInGamePosition(6,5,this);
        building.setPowerUpRate(100.0f);
        building.setPowerConsumption(50);
        buildings.add(building);

        building = new FoodBuilding(Resources.supermarket);
        building.setInGamePosition(12, 5, this);
        building.setPowerUpRate(100.0f);
        building.setPowerConsumption(50);
        buildings.add(building);
    }

    public void updatePowerCounter(float delta) {
        if (powerCounter > power) {
            powerCounter--;
        }
        else if (powerCounter < power) {
            powerCounter++;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void update(float delta) {
        second += delta;
        updatePowerCounter(delta);
        if (second >= 1.0f) {
            second = 0.0f;
            hunger--;
            happy--;
            coin--;
            crime++;
        }
        checkForGameOver();
    }

    public void checkForGameOver() {
        if (coin <= 0) {
            gameOver = true;
            losestate = Settings.loseState.COIN;
        }
        if (crime >= 100) {
            gameOver = true;
            losestate = Settings.loseState.CRIME;
        }
        if (hunger <= 0) {
            gameOver = true;
            losestate = Settings.loseState.FOOD;
        }
        if (happy <= 0) {
            gameOver = true;
            losestate = Settings.loseState.HAPPY;
        }
    }

    public void handleClickEvent(int x, int y) {
        for (Building e : buildings) {
            if (e.isClicked(x, y)) {
                if (power - e.getPowerConsumption() < 0) {
                    //make screen flicker red
                }
                if (e.isUnpowered())
                    power -= e.getPowerConsumption();
                else if (!e.isUnpowered())
                    power += e.getPowerConsumption();
                e.togglePowerState();
            }
        }
    }

    public int generateTerrain(SpriteCache cache) {
        cache.beginCache();
        for (int y = 0; y < Settings.blocksHeight; y++) {
            for (int x = 0; x < Settings.blocksWidth; x++) {
                int posY = Settings.gameBoardHeight - heightToPixel(y) - Settings.tilePixelHeight;
                int posX = widthToPixel(x);
                switch (tiles[y][x]) {
                    case 0: cache.add(Resources.grass, posX, posY);
                        break;
                    case 1: cache.add(Resources.roadUp, posX, posY);
                        break;
                    case 2: cache.add(Resources.roadSide, posX, posY);
                        break;
                    case 3: cache.add(Resources.roadRight, posX, posY);
                        break;
                    case 4: cache.add(Resources.roadLeft, posX, posY);
                        break;
                    case 5: cache.add(Resources.roadRightUp, posX, posY);
                        break;
                    case 6: cache.add(Resources.roadLeftUp, posX, posY);
                        break;
                    default: break;
                }
            }
        }
        return cache.endCache();
    }

    public void generate() {
        tiles = new int[][] {
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,6,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,5,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };
    }
}
