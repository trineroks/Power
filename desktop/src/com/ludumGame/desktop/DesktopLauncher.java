package com.ludumGame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ludumGame.PowerTown;
import com.ludumGame.Settings;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Test";
		config.width = Settings.screenWidth;
		config.height = Settings.screenHeight;
		new LwjglApplication(new PowerTown(), config);
	}
}
