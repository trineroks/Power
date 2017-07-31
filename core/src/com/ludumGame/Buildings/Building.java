package com.ludumGame.Buildings;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.ludumGame.Map;

/**
 * Created by trineroks on 7/29/17.
 */
public class Building extends Rectangle {

    TextureRegion texture;
    private State state;
    private float brightness;
    private float maxBright = 255.0f;
    private float minBright = 190.0f;
    private float glowRate = 200.0f;
    private boolean glowForward;

    public enum State {
        UNPOWERED,
        POWERED,
    }

    public Building(TextureRegion texture) {
        super();
        this.texture = texture;
        this.state = State.UNPOWERED;
        this.brightness = 0.0f;
        this.glowForward = true;
        setHeight(texture.getRegionHeight());
        setWidth(texture.getRegionWidth());
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public void togglePowerState() {
        if (state == State.POWERED)
            state = State.UNPOWERED;
        else
            state = State.POWERED;
    }

    public void setInGamePosition(int posX, int posY, Map map) {
        setX(map.widthToPixel(posX));
        setY(map.heightToPixel(posY));
    }

    public boolean isClicked(int x, int y) { //check only on keyup
        return contains((float) x, (float) y);
    }

    public void renderLightBox(ShapeRenderer lightBox, float delta) {
        lightBox.begin(ShapeRenderer.ShapeType.Filled);
        if (state == State.POWERED)
            animateLight(lightBox, delta);
        else {
            brightness = 0.0f;
            glowForward = true;
            lightBox.setColor(0,0,0,1);
        }
        lightBox.rect(x, y, width, height);
        lightBox.end();
    }

    public void animateLight(ShapeRenderer lightBox, float delta) {
        if (brightness <= minBright) {
            glowForward = true;
        }
        else if (brightness >= maxBright) {
            glowForward = false;
        }
        if (glowForward) {
            brightness += glowRate * delta;
        }
        else
            brightness -= glowRate * delta;
        lightBox.setColor(brightness/255.0f, brightness/255.0f, 0, 1);
    }

    //abstract void handleClickEvent();
}
