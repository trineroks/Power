package com.ludumGame;

import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.utils.Json;
import com.ludumGame.Buildings.*;

import java.util.ArrayList;

/**
 * Created by trineroks on 7/29/17.
 */
public class Map {
    public int[][] tiles;
    public ArrayList<Building> buildings = new ArrayList<Building>();

    private int power, hunger, crime, happy, coin;
    private int powerCounter; //to animate the actual power going up and down.

    private float second = 0;
    private float twoSecond = 0;
    private float dayCounter = 0.0f;
    private boolean gameOver = false;
    private int decrementer;
    public byte losestate;

    public boolean showWarning = false;
    public boolean showDay = false;
    public int currentDay = 0;

    private final float warningLinger = 1.0f;
    private float warningTimer = 0.0f;

    public Map() {
        powerCounter = 100;
        power = 100;
        hunger = 100;
        crime = 0;
        happy = 100;
        coin = 100;
        losestate = Settings.loseState.PLAYING;
        decrementer = 1;
    }

    public int getPower() {
        return power;
    }

    public void incrementPower(int increment) { power += increment; }

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

    public void incrementHunger(int value) {
        hunger += value;
        if (hunger >= Settings.needsHardCap)
            hunger = Settings.needsHardCap;
    }

    public void decrementHunger(int value) {
        hunger -= value;
        if (hunger <= 0)
            hunger = 0;
    }

    public void incrementCrime(int value) {
        crime += value;
        if (crime >= 100)
            crime = 100;
    }

    public void decrementCrime(int value) {
        crime -= value;
        if (crime <= 0)
            crime = 0;
    }

    public void incrementHappy(int value) {
        happy += value;
        if (happy >= Settings.needsHardCap)
            happy = Settings.needsHardCap;
    }

    public void decrementHappy(int value) {
        happy -= value;
        if (happy <= 0)
            happy = 0;
    }

    public void incrementCoin(int value) {
        coin += value;
        if (coin >= Settings.needsHardCap)
            coin = Settings.needsHardCap;
    }

    public void decrementCoin(int value) {
        coin -= value;
        if (coin <= 0)
            coin = 0;
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
        building.setInGamePosition(7,6,this);
        buildings.add(building);

        building = new FoodBuilding(Resources.restaurant);
        building.setInGamePosition(20, 20, this);
        buildings.add(building);

        building = new CoinBuilding(Resources.factory);
        building.setInGamePosition(43, 6, this);
        buildings.add(building);

        building = new CoinBuilding(Resources.factory);
        building.setInGamePosition(36, 6, this);
        buildings.add(building);

        building = new CoinBuilding(Resources.bank);
        building.setInGamePosition(1, 1, this);
        buildings.add(building);

        building = new HappyBuilding(Resources.pub);
        building.setInGamePosition(1,26,this);
        buildings.add(building);

        building = new HappyBuilding(Resources.pub);
        building.setInGamePosition(1, 22, this);
        buildings.add(building);

        building = new HappyBuilding(Resources.bowling);
        building.setInGamePosition(40, 18, this);
        buildings.add(building);

        building = new CrimeBuilding(Resources.courthouse);
        building.setInGamePosition(20, 1,this);
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
        dayCounter += delta;
        second += delta;
        twoSecond += delta;
        if (warningTimer < warningLinger)
            warningTimer += delta;
        updatePowerCounter(delta);
        if (second >= 1.0f) {
            second = 0.0f;
            updateNeeds();
            updateActiveBuildings();
        }
        if (warningTimer >= warningLinger)
            showWarning = false;
        checkForGameOver();
        if (dayCounter >= Settings.maxDayTime) {
            currentDay++;
            decrementer++;
            incrementPower(10);
            showDay = true;
        }
    }

    public void resetDay() {
        showDay = false;
        dayCounter = 0.0f;
    }

    public void updateNeeds() {
        decrementHappy(decrementer);
        decrementCoin(decrementer);
        decrementHunger(decrementer);
        if (twoSecond >= 2.0f) {
            twoSecond = 0.0f;
            incrementCrime(decrementer);
        }
    }

    public void updateActiveBuildings() {
        for (Building e : buildings) {
            if (e.isPowered())
                e.update(this);
        }
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
                if (e.isUnpowered() && (power - e.getPowerConsumption() < 0)) {
                    showWarning = true;
                    warningTimer = 0.0f;
                }
                else {
                     if (e.isUnpowered())
                        power -= e.getPowerConsumption();
                     else if (!e.isUnpowered())
                        power += e.getPowerConsumption();
                    e.togglePowerState();
                }
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
                    case 1: cache.add(Resources.road, posX, posY);
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
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };
        Json json = new Json();

    }
}
