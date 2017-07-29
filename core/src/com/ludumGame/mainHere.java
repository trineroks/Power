package com.ludumGame;

import com.badlogic.gdx.Game;
import com.ludumGame.Screens.MainMenuScreen;

public class mainHere extends Game {
	public void create() {
		this.setScreen(new MainMenuScreen(this));
	}
}
