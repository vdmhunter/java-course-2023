package edu.project1.views;

import edu.project1.models.GameModel;
import edu.project1.views.base.Color;
import edu.project1.views.base.Coordinate;
import edu.project1.views.base.Pixel;
import edu.project1.views.sprites.Sprite;

public abstract class View {
    Pixel[][] screen;
    public static final int GLOBAL_WIDTH = 80;
    public static final int GLOBAL_HEIGHT = 25;
    public static final int SCREEN_DIVIDER = 33;
    public static final int HEX_BASE = 16;

    final Coordinate topLeft;
    final Coordinate bottomRight;

    public View(Coordinate topLeft, Coordinate bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public abstract void render(GameModel model);

    protected void drawSpriteOnScreen(Sprite sprite, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
        for (int y = topLeftY; y <= bottomRightY; y++) {
            for (int x = topLeftX; x <= bottomRightX; x++) {
                char colorMaskChar = sprite.getColorMask()[y - topLeftY].charAt(x - topLeftX);

                Color color = colorMaskChar == ' '
                    ? Color.WHITE
                    : Color.values()[Character.digit(colorMaskChar, HEX_BASE)];

                char ch = sprite.getPixels()[y - topLeftY].charAt(x - topLeftX);

                screen[x][y] = new Pixel(ch, color);
            }
        }
    }
}
