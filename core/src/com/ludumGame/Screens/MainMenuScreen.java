package com.ludumGame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ludumGame.PowerTown;
import com.ludumGame.Settings;

/**
 * Created by trineroks on 7/29/17.
 */
public class MainMenuScreen extends AbstractScreen {
    BitmapFont font;

    OrthographicCamera camera;

    public MainMenuScreen(PowerTown game) {
        super(game);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Settings.screenWidth, Settings.screenHeight);

        game.batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        font.draw(game.batch, "Click anywhere to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new GameScreen(game));
            //dispose();
        }
    }

    @Override
    public void hide() {
        font.dispose();
    }
}
