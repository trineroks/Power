package com.ludumGame.Buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ludumGame.GameLogic;

/**
 * Created by trineroks on 7/31/17.
 */
public class DessertBuilding extends Building {

    private int foodRate;
    private int happyRate;

    public DessertBuilding(TextureRegion texture) {
        super(texture);
        if (large) {
            setPowerUpRate(50.0f);
            setPowerConsumption(20);
            foodRate = 8;
            happyRate = 4;
        } else {
            setPowerUpRate(150.0f);
            setPowerConsumption(10);
            foodRate = 2;
            happyRate = 2;
        }
    }

    @Override
    public void update(GameLogic gameLogic) {
        gameLogic.incrementFood(foodRate);
        gameLogic.incrementHappy(happyRate);
    }
}
