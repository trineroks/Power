package com.ludumGame.Buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ludumGame.GameLogic;

/**
 * Created by trineroks on 7/30/17.
 */
public class CoinBuilding extends Building {

    private int coinRate;
    private int decHappyRate;

    public CoinBuilding(TextureRegion texture) {
        super(texture);
        if (large) {
            setPowerUpRate(50.0f);
            setPowerConsumption(50);
            coinRate = 12;
            decHappyRate = 2;
        } else {
            setPowerUpRate(150.0f);
            setPowerConsumption(15);
            coinRate = 3;
            decHappyRate = 0;
        }
    }

    @Override
    public void update(GameLogic gameLogic) {
        gameLogic.incrementCoin(coinRate);
        gameLogic.decrementHappy(decHappyRate);
    }
}
