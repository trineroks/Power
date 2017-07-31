package com.ludumGame.Buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ludumGame.GameLogic;

/**
 * Created by trineroks on 7/31/17.
 */
public class MallBuilding extends Building {

    private int coinRate;
    private int happyRate;

    public MallBuilding(TextureRegion texture) {
        super(texture);
        if (large) {
            setPowerUpRate(50.0f);
            setPowerConsumption(50);
            coinRate = 8;
            happyRate = 1;
        } else {
            setPowerUpRate(150.0f);
            setPowerConsumption(15);
            coinRate = 2;
            happyRate = 0;
        }
    }

    @Override
    public void update(GameLogic gameLogic) {
        gameLogic.incrementCoin(coinRate);
        gameLogic.incrementHappy(happyRate);
    }
}
