package edu.project1.views;

import edu.project1.models.GameModel;
import edu.project1.views.base.Color;
import edu.project1.views.base.Coordinate;
import edu.project1.views.base.Pixel;
import edu.project1.views.sprites.Sprite;

/**
 * The {@code View} class is an abstract base class that provides common functionality for rendering
 * game-related information on a screen. It defines the screen size and contains methods to
 * draw sprites on the screen.
 */
public abstract class View {
    Pixel[][] screen;
    public static final int GLOBAL_WIDTH = 80;
    public static final int GLOBAL_HEIGHT = 25;
    public static final int SCREEN_DIVIDER = 33;
    public static final int HEX_BASE = 16;

    final Coordinate topLeft;
    final Coordinate bottomRight;

    /**
     * Constructs a {@code View} with specified top-left and bottom-right coordinates to define
     * the region of the screen it operates on.
     *
     * @param topLeft     The top-left coordinate of the screen region.
     * @param bottomRight The bottom-right coordinate of the screen region.
     */
    View(Coordinate topLeft, Coordinate bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    /**
     * Abstract method to be implemented by subclasses for rendering the game based on the
     * provided {@link GameModel}.
     *
     * @param model The GameModel containing the game's data and state.
     */
    public abstract void render(GameModel model);

    /**
     * Draws a sprite on the screen within a specified region defined by coordinates.
     *
     * @param sprite       The Sprite to be drawn on the screen.
     * @param topLeftX     The top-left X-coordinate of the region to draw the sprite.
     * @param topLeftY     The top-left Y-coordinate of the region to draw the sprite.
     * @param bottomRightX The bottom-right X-coordinate of the region to draw the sprite.
     * @param bottomRightY The bottom-right Y-coordinate of the region to draw the sprite.
     */
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
