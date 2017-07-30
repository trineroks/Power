package com.ludumGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ludumGame.Screens.MainMenuScreen;

public class PowerTown extends Game {

	public SpriteBatch batch;

	public void create() {
		batch = new SpriteBatch();
		Resources.load();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
}
