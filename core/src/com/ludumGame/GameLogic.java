package com.ludumGame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.ludumGame.Buildings.*;

import java.util.ArrayList;

/**
 * Created by trineroks on 7/29/17.
 */
public class GameLogic {
    public ArrayList<Building> buildings = new ArrayList<Building>();

    private int power, hunger, crime, happy, coin;
    private int powerCounter; //to animate the actual power going up and down.

    private float second = 0;
    private float twoSecond = 0;
    private float dayCounter = 0.0f;
    public boolean crimeBuildingOn = false;
    private boolean gameOver = false;
    private int decrementer;
    public byte losestate;

    public boolean showWarning = false;
    public boolean showDay = false;
    public int currentDay = 0;
    private Map map;
    public int crimeBuildingCounter = 0;

    private final float warningLinger = 1.0f;
    private float warningTimer = 0.0f;

    public GameLogic() {
        powerCounter = 100;
        power = 100;
        hunger = 100;
        crime = 0;
        happy = 100;
        coin = 100;
        losestate = Settings.loseState.PLAYING;
        decrementer = 1;
        map = new Map();
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

    public void incrementFood(int value) {
        hunger += value;
        if (hunger >= Settings.needsHardCap)
            hunger = Settings.needsHardCap;
    }

    public void decrementFood(int value) {
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
        Building building;
        for (int y = 0; y < Settings.blocksHeight; y++) {
            for (int x = 0; x < Settings.blocksWidth; x++) {
                int posY = Settings.blocksHeight - y - 1;
                switch (map.getMap()[(y*Settings.blocksWidth) + x]) {
                    case Tile.factory:
                        building = new CoinBuilding(Resources.factory);
                        break;
                    case Tile.police:
                        building = new CrimeBuilding(Resources.police);
                        break;
                    case Tile.pub:
                        building = new HappyBuilding(Resources.pub);
                        break;
                    case Tile.supermarket:
                        building = new FoodBuilding(Resources.supermarket);
                        break;
                    case Tile.restaurant:
                        building = new FoodBuilding(Resources.restaurant);
                        break;
                    case Tile.bank:
                        building = new CoinBuilding(Resources.bank);
                        break;
                    case Tile.bowling:
                        building = new HappyBuilding(Resources.bowling);
                        break;
                    case Tile.cafe:
                        building = new FoodBuilding(Resources.cafe);
                        break;
                    case Tile.courthouse:
                        building = new CrimeBuilding(Resources.courthouse);
                        break;
                    case Tile.arcade:
                        building = new HappyBuilding(Resources.arcade);
                        break;
                    case Tile.burger:
                        building = new FoodBuilding(Resources.burger);
                        break;
                    case Tile.departmentstore:
                        building = new MallBuilding(Resources.departmentstore);
                        break;
                    case Tile.donuts:
                        building = new CopFoodBuilding(Resources.donuts);
                        break;
                    case Tile.icecream:
                        building = new DessertBuilding(Resources.icecream);
                        break;
                    case Tile.school:
                        building = new EducationBuilding(Resources.school);
                        break;
                    case Tile.sheriff:
                        building = new CrimeBuilding(Resources.sheriff);
                        break;
                    case Tile.shirts:
                        building = new CoinBuilding(Resources.shirts);
                        break;
                    default:
                        building = null;
                        break;
                }
                if (building != null) {
                    building.setInGamePosition(x, posY, this);
                    buildings.add(building);
                }
            }
        }
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
        decrementFood(decrementer);
        if (twoSecond >= 2.0f) {
            twoSecond = 0.0f;
            incrementCrime(decrementer);
        }
    }

    public void updateActiveBuildings() {
        for (Building e : buildings) {
            if (e.isPowered()) {
                e.update(this);
            }
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
                     if (e.isUnpowered()) {
                         power -= e.getPowerConsumption();
                         if (e.isCrimeBuilding)
                             crimeBuildingCounter++;
                     }
                     else if (!e.isUnpowered()) {
                         power += e.getPowerConsumption();
                         if (e.isCrimeBuilding)
                             crimeBuildingCounter--;
                     }
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
                switch (map.getMap()[(y*Settings.blocksWidth) + x]) {
                    case Tile.grass: cache.add(Resources.grass, posX, posY);
                        break;
                    case Tile.road: cache.add(Resources.road, posX, posY);
                        break;
                    default: break;
                }
            }
        }
        return cache.endCache();
    }

    public void renderTopLayerMap(SpriteBatch batch) {
        for (int y = 0; y < Settings.blocksHeight; y++) {
            for (int x = 0; x < Settings.blocksWidth; x++) {
                int posY = Settings.gameBoardHeight - heightToPixel(y) - Settings.tilePixelHeight;
                int posX = widthToPixel(x);
                switch (map.getMap()[(y*Settings.blocksWidth) + x]) {
                    case Tile.stop: batch.draw(Resources.stop, posX, posY);
                        break;
                    case Tile.flower: batch.draw(Resources.flower, posX, posY);
                        break;
                    default: break;
                }
            }
        }
    }
}
