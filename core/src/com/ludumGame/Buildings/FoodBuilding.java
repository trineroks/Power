package com.ludumGame.Buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by trineroks on 7/30/17.
 */
public class FoodBuilding extends Building {

    private int foodRate;

    public FoodBuilding(TextureRegion texture) {
        super(texture);
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    @Override
    public void update() {

    }
}
