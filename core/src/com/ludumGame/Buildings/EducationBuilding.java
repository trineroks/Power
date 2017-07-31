package com.ludumGame.Buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ludumGame.GameLogic;

/**
 * Created by trineroks on 7/31/17.
 */
public class EducationBuilding extends Building {

    private int crimeRate;
    private int decHappyRate;

    public EducationBuilding(TextureRegion texture) {
        super(texture);
        if (large) {
            setPowerUpRate(60.0f);
            setPowerConsumption(60);
            crimeRate = 8;
            decHappyRate = 3;
        } else {
            setPowerUpRate(150.0f);
            setPowerConsumption(20);
            crimeRate = 5;
            decHappyRate = 2;
        }
    }

    @Override
    public void update(GameLogic gameLogic) {
        gameLogic.decrementCrime(crimeRate);
        gameLogic.decrementHappy(decHappyRate);
    }
}
