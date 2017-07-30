package com.ludumGame.Buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.ludumGame.Map;

/**
 * Created by trineroks on 7/29/17.
 */
public class Building extends Rectangle {

    TextureRegion texture;
    private State state;

    public enum State {
        UNPOWERED,
        POWERED,
    }

    public Building(TextureRegion texture) {
        super();
        this.texture = texture;
        this.state = State.UNPOWERED;
        setHeight(texture.getRegionHeight());
        setWidth(texture.getRegionWidth());
    }

    public void setPosition(int posX, int posY, Map map) {
        setX(map.widthToPixel(posX));
        setY(map.heightToPixel(posY));
    }


}
