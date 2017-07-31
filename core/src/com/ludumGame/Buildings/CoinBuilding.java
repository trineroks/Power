package com.ludumGame.Buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ludumGame.Map;

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
            coinRate = 10;
            decHappyRate = 2;
        } else {
            setPowerUpRate(150.0f);
            setPowerConsumption(15);
            coinRate = 2;
            decHappyRate = 0;
        }
    }

    @Override
    public void update(Map map) {
        map.incrementCoin(coinRate);
        map.decrementHappy(decHappyRate);
    }
}
