package com.ludumGame.Buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ludumGame.Map;

/**
 * Created by trineroks on 7/30/17.
 */
public class FoodBuilding extends Building {

    private int foodRate;

    public FoodBuilding(TextureRegion texture) {
        super(texture);
        if (large) {
            setPowerUpRate(50.0f);
            setPowerConsumption(30);
            foodRate = 10;
        } else {
            setPowerUpRate(150.0f);
            setPowerConsumption(15);
            foodRate = 3;
        }
    }

    @Override
    public void update(Map map) {
        map.incrementHunger(foodRate);
    }
}
