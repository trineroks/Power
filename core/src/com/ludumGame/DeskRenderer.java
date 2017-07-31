package com.ludumGame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by trineroks on 7/30/17.
 */
public class DeskRenderer {

    private NinePatch deskPatch;
    private BitmapFont font;

    public DeskRenderer() {
        this.deskPatch = new NinePatch(Resources.deskNinePatch, 10, 10, 10, 10);
        font = new BitmapFont();
    }

    public void render(SpriteBatch batch, GameLogic gameLogic, float delta) {
        int x = 25;
        deskPatch.draw(batch, 0, Settings.gameBoardHeight, Settings.deskWidth, Settings.deskHeight);
        batch.draw(Resources.power, x, Settings.GUIElementHeight);
        font.draw(batch, "" + gameLogic.getPowerCounter(), x + Settings.GUIElementSize+ 10, Settings.GUIElementHeight + Settings.GUIElementSize/2);
        x+=Settings.GUIElementSpacing;
        batch.draw(Resources.happy, x, Settings.GUIElementHeight);
        font.draw(batch, "" + gameLogic.getHappy(), x + Settings.GUIElementSize+ 10, Settings.GUIElementHeight + Settings.GUIElementSize/2);
        x+=Settings.GUIElementSpacing;
        batch.draw(Resources.hunger, x, Settings.GUIElementHeight);
        font.draw(batch, "" + gameLogic.getHunger(), x + Settings.GUIElementSize+ 10, Settings.GUIElementHeight + Settings.GUIElementSize/2);
        x+=Settings.GUIElementSpacing;
        batch.draw(Resources.coin, x, Settings.GUIElementHeight);
        font.draw(batch, "" + gameLogic.getCoin(), x + Settings.GUIElementSize+ 10, Settings.GUIElementHeight + Settings.GUIElementSize/2);
        x+=Settings.GUIElementSpacing;
        batch.draw(Resources.crime, x, Settings.GUIElementHeight);
        font.draw(batch, "" + gameLogic.getCrime(), x + Settings.GUIElementSize+ 10, Settings.GUIElementHeight + Settings.GUIElementSize/2);
    }

    public void dispose() {
        font.dispose();
    }
}
