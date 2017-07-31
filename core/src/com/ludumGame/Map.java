package com.ludumGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by trineroks on 7/31/17.
 */
public class Map {
    private int[] map;
    private int max = Settings.blocksHeight * Settings.blocksWidth;

    public Map() {
        //generateDefaultMap();
        //writeToFile();
        readFromFile();
    }

    public int[] getMap() {
        return map;
    }

    public void writeToFile() {
        String string = "";
        for (int i = 0; i < max; i++) {
            string += Integer.toString(map[i]) + ",";
        }
        string = string.substring(0, string.length()-1);
        FileHandle file = Gdx.files.local("map/map.txt");
        file.writeString(string, false);
    }

    public void readFromFile() {
        FileHandle file = Gdx.files.internal("map/map.txt");
        String[] handler = file.readString().split(",");
        map = new int[max];
        for (int i = 0; i < max; i++) {
            map[i] = Integer.parseInt(handler[i]);
        }
    }

    public void generateDefaultMap() {
        map = new int[max];
    }
}
