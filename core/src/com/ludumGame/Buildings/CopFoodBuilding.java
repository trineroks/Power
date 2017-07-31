package com.ludumGame.Buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ludumGame.GameLogic;

/**
 * Created by trineroks on 7/31/17.
 */
public class CopFoodBuilding extends Building {

    private int foodRate;
    private int happyRate;

    public CopFoodBuilding(TextureRegion texture) {
        super(texture);
        if (large) {
            setPowerUpRate(50.0f);
            setPowerConsumption(50);
            foodRate = 8;
            happyRate = 2;
        } else {
            setPowerUpRate(150.0f);
            setPowerConsumption(15);
            foodRate = 2;
            happyRate = 1;
        }
    }

    @Override
    public void update(GameLogic gameLogic) {
        gameLogic.incrementFood(foodRate);
        if (gameLogic.crimeBuildingCounter > 0)
            gameLogic.incrementHappy(happyRate);
    }
}
