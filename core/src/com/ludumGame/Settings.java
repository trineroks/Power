package com.ludumGame;

/**
 * Created by trineroks on 7/29/17.
 */
public class Settings {
    public final static int screenHeight = 530;
    public final static int screenWidth = 800;

    public final static int blocksWidth = 50;
    public final static int blocksHeight = 30;

    public final static int tilePixelWidth = 16;
    public final static int tilePixelHeight = 16;

    public final static int gameBoardHeight = 480;
    public final static int gameBoardWidth = 800;

    public final static int deskWidth = screenWidth;
    public final static int deskHeight = screenHeight - gameBoardHeight;

    public final static int GUIElementHeight = gameBoardHeight + 10;
    public final static int GUIElementSpacing = 150;
    public final static int GUIElementSize = 30;

    public final static float dialogTimeMultiplier = 2.0f;
    public final static float dialogPositionX = tilePixelWidth * 12;
    public final static float dialogPositionY = tilePixelHeight * 8;
    public final static float dialogWidth = tilePixelWidth * 26;
    public final static float dialogHeight = tilePixelHeight * 14;

    public final static String creditsText = "PowerTown programmed by trineroks for Ludum Dare 39. Building sprites provided by What.\n\n" +
                                        "Special thanks to Stuhuffsty for providing some of the minor artwork.";
}
