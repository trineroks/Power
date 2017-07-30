package com.ludumGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by trineroks on 7/29/17.
 */
public class Resources {
    public static Texture terrain;
    public static TextureRegion officeBuilding;
    
    public static TextureRegion ninePatch;
    
    public static TextureRegion roadSide;
    public static TextureRegion roadUp;
    public static TextureRegion roadLeft;
    public static TextureRegion roadRight;
    public static TextureRegion roadLeftUp;
    public static TextureRegion roadRightUp;
    public static TextureRegion grass;

    public static void load() {
        terrain = loadSheet("TerrainSheet.png");

        //The terrains
        ninePatch = new TextureRegion(terrain, 0,0,30,30);
        grass = new TextureRegion(terrain, 0, 30, 16, 16);
        roadLeft = new TextureRegion(terrain, 16,30,16,16);
        roadLeftUp = new TextureRegion(terrain, 0,46,16,16);
        roadRight = new TextureRegion(terrain, 16,46,16,16);
        roadRightUp = new TextureRegion(terrain, 0,62,16,16);
        roadSide = new TextureRegion(terrain, 16,62,16,16);
        roadUp = new TextureRegion(terrain, 0,78,16,16);

        //officeBuilding = new TextureRegion(building);
    }

    public static Texture loadSheet(String file) {
        return new Texture(Gdx.files.internal(file));
    }
}
