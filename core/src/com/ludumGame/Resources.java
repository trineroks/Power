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
    public static Texture GUI;
    
    public static TextureRegion dialogNinePatch;
    public static TextureRegion deskNinePatch;
    public static TextureRegion coin;
    public static TextureRegion power;
    public static TextureRegion crime;
    public static TextureRegion hunger;
    public static TextureRegion happy;
    public static TextureRegion playButton;
    public static TextureRegion creditButton;
    
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
        GUI = loadSheet("GUISheet.png");

        //The terrains
        grass = new TextureRegion(terrain, 0, 0, 16, 16);
        roadLeft = new TextureRegion(terrain, 16,0,16,16);
        roadLeftUp = new TextureRegion(terrain, 32,0,16,16);
        roadRight = new TextureRegion(terrain, 48,0,16,16);
        roadRightUp = new TextureRegion(terrain, 64,0,16,16);
        roadSide = new TextureRegion(terrain, 80,0,16,16);
        roadUp = new TextureRegion(terrain, 96,0,16,16);

        //The GUI
        coin = new TextureRegion(GUI, 150,0,30,30);
        creditButton = new TextureRegion(GUI, 0,80,200,50);
        crime = new TextureRegion(GUI, 180,0,30,30);
        deskNinePatch = new TextureRegion(GUI, 120,0,30,30);
        dialogNinePatch = new TextureRegion(GUI, 90, 0, 30, 30);
        happy = new TextureRegion(GUI, 30,0,30,30);
        hunger = new TextureRegion(GUI, 0,0,30,30);
        playButton = new TextureRegion(GUI, 0, 30, 200,50);
        power = new TextureRegion(GUI, 60, 0, 30, 30);

        //The buildings
        factory = new TextureRegion(buildings, 96,64,96,160);
        police = new TextureRegion(buildings, 48, 0, 128,64);
        pub = new TextureRegion(buildings, 0,0,48,48);
        supermarket = new TextureRegion(buildings, 0,64,96,96);
    }

    public interface GameOverTexts {
        String coinLoss = "Your town is in economic shambles! The people decide that if they can't have a job, neither can you. You are no longer mayor.";
        String crimeLoss = "Your town's been taken over by thugs! Rampant looting and destruction of property ensue. The National Guard is called in and you're no longer mayor.";
        String foodLoss = "Your town is starving! Don't worry, the military's sending emergency food rations your way! However you're no longer mayor.";
        String happyLoss = "Your town is unhappy! With no other outlet for frustration, the people take it upon themselves to overthrow you! You are no longer mayor.";
    }

    public interface GameStartTexts {
        String intro = "Welcome to Powerton, mayor! We have a limited supply of power as you can see on the top left. Click on buildings to power them on or off. Each building caters to " +
                "a specific need. The needs are listed at the very top, going left to right: happiness, food, economy, and crime. You lose if happiness, food, or economy reach 0, OR if crime reaches 100. " +
                "Keep in mind that buildings take time to power up, and larger buildings - although they satisfy a greater amount of needs - consume more power than smaller buildings." +
                " Good luck mayor! Click anywhere to dismiss this message.";
    }

    public static Texture loadSheet(String file) {
        return new Texture(Gdx.files.internal(file));
    }
}
