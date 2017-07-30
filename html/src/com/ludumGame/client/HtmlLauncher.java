package com.ludumGame.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.ludumGame.PowerTown;
import com.ludumGame.Settings;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(Settings.screenWidth, Settings.screenHeight);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new PowerTown();
        }
}