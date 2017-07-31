package com.ludumGame.Buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ludumGame.GameLogic;

/**
 * Created by trineroks on 7/31/17.
 */
public class CrimeBuilding extends Building {

    private int decCrimeRate;

    public CrimeBuilding(TextureRegion texture) {
        super(texture);
        if (large) {
            setPowerUpRate(75.0f);
            setPowerConsumption(50);
            decCrimeRate = 4;
        } else {
            setPowerUpRate(200.0f);
            setPowerConsumption(20);
            decCrimeRate = 1;
        }
        setCrimeBuilding();
    }

    @Override
    public void update(GameLogic gameLogic) {
        gameLogic.decrementCrime(decCrimeRate);
        gameLogic.crimeBuildingOn = true;
    }
}
