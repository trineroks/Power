package com.ludumGame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;

/**
 * Created by trineroks on 7/30/17.
 */
public class NinePatchDialog {
    private NinePatch patch;
    private BitmapFont font;
    private String content;
    private State state;
    private float height, width, x, y;

    public enum State {
        Opening,
        Opened,
        Closing,
        Closed
    }

    public NinePatchDialog() {
        this.patch = new NinePatch(Resources.dialogNinePatch, 10, 10, 10, 10);
        this.font = new BitmapFont();
        this.state = State.Closed;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void display() {
        state = State.Opening;
        this.x = Settings.gameBoardWidth/2;
        this.y = Settings.gameBoardHeight/2;
        this.height = 0;
        this.width = 0;
    }

    private void hide() {
        state = State.Closed;
        this.x = -20;
        this.y = -20;
        this.height = 0;
        this.width = 0;
    }

    public void closeDialog() {
        state = State.Closing;
    }

    public boolean isClosed() {
        return state == State.Closed;
    }

    public boolean isOpening() {
        return state == State.Opening;
    }

    public boolean isOpened() {
        return state == State.Opened;
    }

    public void render(Batch batch, Map map, float delta) {
        if (state == State.Opening) {
            if (height != Settings.dialogHeight)
                height += 112 * delta * Settings.dialogTimeMultiplier;
            if (width != Settings.dialogWidth)
                width += 208 * delta * Settings.dialogTimeMultiplier;
            if (y != Settings.dialogPositionY)
                y -= 56 * delta * Settings.dialogTimeMultiplier;
            if (x != Settings.dialogPositionX)
                x -= 104 * delta * Settings.dialogTimeMultiplier;
            patch.draw(batch, x, y, width, height);
            if (height >= Settings.dialogHeight && width >= Settings.dialogWidth && x <= Settings.dialogPositionX && y <= Settings.dialogPositionY)
                state = State.Opened;
        }
        else if (state == State.Opened) {
            patch.draw(batch, Settings.dialogPositionX, Settings.dialogPositionY, Settings.dialogWidth, Settings.dialogHeight);
            font.drawWrapped(batch, content, map.widthToPixel(14), map.heightToPixel(21), map.widthToPixel(22));
        }
        else if (state == State.Closing) {
            if (height >= 20)
                height -= 112 * delta * Settings.dialogTimeMultiplier;
            if (width >= 20)
                width -= 208 * delta * Settings.dialogTimeMultiplier;
            if (y <= Settings.gameBoardHeight/2)
                y += 56 * delta * Settings.dialogTimeMultiplier;
            if (x <= Settings.gameBoardWidth/2)
                x += 104 * delta * Settings.dialogTimeMultiplier;
            patch.draw(batch, x, y, width, height);
            if (height <= 20 && width <= 20) {
                hide();
            }
        }
    }
}
