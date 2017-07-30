package com.ludumGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by trineroks on 7/29/17.
 */
public class Resources {
    public static Texture terrain;
    public static TextureRegion roadHorizontal;

    public static Texture loadSheet(String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load() {
        terrain = loadSheet("RoadHorizonal.png");
        roadHorizontal = new TextureRegion(terrain, 0,0,16,16);
    }
}
