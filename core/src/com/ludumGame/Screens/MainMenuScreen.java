package com.ludumGame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ludumGame.*;

/**
 * Created by trineroks on 7/29/17.
 */
public class MainMenuScreen extends AbstractScreen implements InputProcessor {
    OrthographicCamera camera;
    GameLogic gameLogic;
    NinePatchDialog dialog;

    Button play, credits;

    //Map map;

    public MainMenuScreen(PowerTown game) {
        super(game);
        //map = new Map();
        Gdx.input.setInputProcessor(this);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Settings.screenWidth, Settings.screenHeight);
        this.gameLogic = new GameLogic();
        this.dialog = new NinePatchDialog();
        play = new Button(Resources.playButton);
        credits = new Button(Resources.creditButton);
    }

    @Override
    public void show() {
        play.setButtonPosition(300,130);
        credits.setButtonPosition(300, 200);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,1,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(Resources.powertonsplash, 0, 0);
        game.batch.draw(play.getTexture(), play.getX(), play.getY());
        game.batch.draw(credits.getTexture(), credits.getX(), credits.getY());
        dialog.render(game.batch, gameLogic, delta);
        game.batch.end();

//        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
//            game.setScreen(new GameScreen(game, gameLogic));
//        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.M)
            game.setScreen(new MapEditorScreen(game));
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        y = Settings.screenHeight - y;
        if (!dialog.isClosed()) { //ensure no input goes through when a dialog pops up
            if (dialog.isOpened())
                dialog.closeDialog();
        }
        else if (credits.isClicked(x, y)) {
            dialog.setContent(Settings.creditsText);
            dialog.display();
        }
        else if (play.isClicked(x, y)) {
            game.setScreen(new GameScreen(game, gameLogic));
        }
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
}
