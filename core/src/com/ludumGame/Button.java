package com.ludumGame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by trineroks on 7/30/17.
 */
public class Button extends Rectangle {
    TextureRegion texture;

    public Button(TextureRegion texture) {
        super();
        this.texture = texture;
        setHeight(texture.getRegionHeight());
        setWidth(texture.getRegionWidth());
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public void setButtonPosition(float posX, float posY) {
        setX(posX);
        setY(posY);
    }

    public boolean isClicked(int x, int y) { //check only on keyup
        return contains((float) x, (float) y);
    }
}
