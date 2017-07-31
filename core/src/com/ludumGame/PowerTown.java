package com.ludumGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ludumGame.Screens.MainMenuScreen;

public class PowerTown extends Game {

	public SpriteBatch batch;
	public Map map;

	public void create() {
		batch = new SpriteBatch();
		Resources.load();
		map = new Map();
		this.setScreen(new MainMenuScreen(this, map));
	}

	@Override
	public void render() {
		super.render();
	}
}
