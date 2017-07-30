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
    public static Texture buildings;
    
    public static TextureRegion ninePatch;
    
    public static TextureRegion roadSide;
    public static TextureRegion roadUp;
    public static TextureRegion roadLeft;
    public static TextureRegion roadRight;
    public static TextureRegion roadLeftUp;
    public static TextureRegion roadRightUp;
    public static TextureRegion grass;

    public static TextureRegion pub;
    public static TextureRegion police;
    public static TextureRegion supermarket;
    public static TextureRegion factory;

    public static void load() {
        terrain = loadSheet("TerrainSheet.png");
        buildings = loadSheet("BuildingSheet.png");

        //The terrains
        ninePatch = new TextureRegion(terrain, 0,0,30,30);
        grass = new TextureRegion(terrain, 0, 30, 16, 16);
        roadLeft = new TextureRegion(terrain, 16,30,16,16);
        roadLeftUp = new TextureRegion(terrain, 0,46,16,16);
        roadRight = new TextureRegion(terrain, 16,46,16,16);
        roadRightUp = new TextureRegion(terrain, 0,62,16,16);
        roadSide = new TextureRegion(terrain, 16,62,16,16);
        roadUp = new TextureRegion(terrain, 0,78,16,16);

        //The buildings
        factory = new TextureRegion(buildings, 96,64,96,160);
        police = new TextureRegion(buildings, 48, 0, 128,64);
        pub = new TextureRegion(buildings, 0,0,48,48);
        supermarket = new TextureRegion(buildings, 0,64,96,96);
    }

    public static Texture loadSheet(String file) {
        return new Texture(Gdx.files.internal(file));
    }
}
