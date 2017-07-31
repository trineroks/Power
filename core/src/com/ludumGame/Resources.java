package com.ludumGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.xml.soap.Text;

/**
 * Created by trineroks on 7/29/17.
 */
public class Resources {
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
    public static TextureRegion insufficientPower;
    
    public static TextureRegion road;
    public static TextureRegion grass;

    public static TextureRegion pub;
    public static TextureRegion police;
    public static TextureRegion restaurant;
    public static TextureRegion supermarket;
    public static TextureRegion factory;
    public static TextureRegion bank;
    public static TextureRegion bowling;
    public static TextureRegion cafe;
    public static TextureRegion courthouse;

    public static void load() {
        buildings = loadSheet("BuildingSheet.png");
        GUI = loadSheet("GUISheet.png");

        //The terrains
        grass = new TextureRegion(GUI, 0, 0, 16, 16);
        road = new TextureRegion(GUI, 16,0,16,16);

        //The GUI
        coin = new TextureRegion(GUI, 32,0,30,30);
        creditButton = new TextureRegion(GUI, 0,80,200,50);
        crime = new TextureRegion(GUI, 182,0,30,30);
        deskNinePatch = new TextureRegion(GUI, 242,0,30,30);
        dialogNinePatch = new TextureRegion(GUI, 212, 0, 30, 30);
        happy = new TextureRegion(GUI, 152,0,30,30);
        hunger = new TextureRegion(GUI, 92,0,30,30);
        playButton = new TextureRegion(GUI, 200, 80, 200,50);
        power = new TextureRegion(GUI, 122, 0, 30, 30);
        insufficientPower = new TextureRegion(GUI, 0,30,400,50);

        //The buildings
        factory = new TextureRegion(buildings, 160,64,96,160);
        police = new TextureRegion(buildings, 0, 224, 128,64);
        pub = new TextureRegion(buildings, 128,224,48,48);
        supermarket = new TextureRegion(buildings, 96,288,96,96);
        restaurant = new TextureRegion(buildings, 0, 288, 96,64);
        bank = new TextureRegion(buildings, 0, 0, 64,64);
        bowling = new TextureRegion(buildings, 64,0,144,64);
        cafe = new TextureRegion(buildings, 208,0,48,48);
        courthouse = new TextureRegion(buildings, 0, 64, 160,112);
    }

    public interface GameOverTexts {
        String coinLoss = "Your town is in economic shambles! The people decide that if they can't have a job, neither can you. You are no longer mayor.";
        String crimeLoss = "Your town's been taken over by thugs! Rampant looting and destruction of property ensue. The National Guard is called in and you're no longer mayor.";
        String foodLoss = "Your town is starving! Don't worry, the military's sending emergency food rations your way! However you're no longer mayor.";
        String happyLoss = "Your town is unhappy! With no other outlet for frustration, the people take it upon themselves to overthrow you! You are no longer mayor.";
    }

    public interface GameStartTexts {
        String intro = "Mayor! Powerton's energy plant suffered an explosion! Your power allowance is on the top left. Click on buildings to power them on or off. Each building caters to " +
                "a specific need. The needs are listed at the very top, going left to right: happiness, food, economy, and crime. You lose if happiness, food, or economy reach 0, OR if crime reaches 100. " +
                "Keep in mind that buildings take time to power up, and larger buildings - although they satisfy a greater amount of needs - consume more power than smaller buildings." +
                " Good luck mayor! Click anywhere to dismiss this message.";
    }

    public static Texture loadSheet(String file) {
        return new Texture(Gdx.files.internal(file));
    }
}
