package com.ludumGame.Buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ludumGame.Map;

/**
 * Created by trineroks on 7/31/17.
 */
public class HappyBuilding extends Building {

    private int happyRate;

    public HappyBuilding(TextureRegion texture) {
        super(texture);
        if (large) {
            setPowerUpRate(50.0f);
            setPowerConsumption(40);
            happyRate = 8;
        } else {
            setPowerUpRate(150.0f);
            setPowerConsumption(15);
            happyRate = 2;
        }
    }

    @Override
    public void update(Map map) {
        map.incrementHappy(happyRate);
    }
}
