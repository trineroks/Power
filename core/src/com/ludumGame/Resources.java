package com.ludumGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by trineroks on 7/29/17.
 */
public class Resources {
    public static Texture terrain;
    public static Texture terrain2;
    public static TextureRegion roadHorizontal;
    public static TextureRegion roadVertical;

    public static void load() {
        terrain = loadSheet("RoadHorizontal.png");
        terrain2 = loadSheet("RoadVertical.png");
        roadHorizontal = new TextureRegion(terrain, 0,0,16,16);
        roadVertical = new TextureRegion(terrain2, 0,0,16,16);
    }

    public static Texture loadSheet(String file) {
        return new Texture(Gdx.files.internal(file));
    }
}
