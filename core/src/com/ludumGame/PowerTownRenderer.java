package com.ludumGame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by trineroks on 7/29/17.
 */
public class PowerTownRenderer {
    OrthographicCamera camera;
    SpriteCache cache;
    SpriteBatch batch = new SpriteBatch();
    Map map;
    int[][] tileIDs;
    int tileID;

    public PowerTownRenderer(Map map) {
        this.map = map;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Settings.screenWidth, Settings.screenHeight);
        this.cache = new SpriteCache(map.tiles.length * map.tiles[0].length, false);
        this.tileIDs = new int[Settings.blocksHeight][Settings.blocksWidth];
        createMap();
    }

    public void createMap() {
        cache.beginCache();
        for (int y = 0; y < Settings.blocksHeight; y++) {
        //for (int y = Settings.blocksHeight-1; y >= 0; y--) {
            for (int x = 0; x < Settings.blocksWidth; x++) {
                //cache.beginCache();
                int posY = Settings.screenHeight - (y * Settings.tilePixelHeight) - Settings.tilePixelHeight;
                int posX = x * Settings.tilePixelWidth;
                switch (map.tiles[y][x]) {
                    case 0: cache.add(Resources.roadHorizontal, posX, posY);
                            break;
                    case 1: cache.add(Resources.roadVertical, posX, posY);
                            break;
                    default: break;
                }
            }
        }
        tileID = cache.endCache();
    }

    public void render(float delta) {
        camera.update();
        cache.setProjectionMatrix(camera.combined);
        cache.begin();
        cache.draw(tileID);
        cache.end();
    }
}
