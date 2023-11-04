package edu.project1.views.sprites;

/**
 * The {@code Sprite} class represents a two-dimensional graphical sprite in ASCII art format.
 * It consists of an array of strings representing the visual pixels of the sprite and an optional color mask
 * for styling the sprite. This class is designed to be used in text-based applications or games
 * for rendering visual elements.
 */
public final class Sprite {
    final String[] pixels;
    final String[] colorMask;

    /**
     * Constructs a new {@code Sprite} object with the provided visual pixel data and color mask.
     *
     * @param pixels    An array of strings representing the visual pixels of the sprite.
     * @param colorMask An array of strings representing the color mask for styling the sprite.
     *                  This can be null if no color mask is provided.
     */
    Sprite(String[] pixels, String[] colorMask) {
        this.pixels = pixels;
        this.colorMask = colorMask;
    }

    /**
     * Get the width of the sprite, which is the number of characters in each row of the pixel array.
     *
     * @return The width of the sprite.
     */
    public int getWidth() {
        return pixels[0].length();
    }

    /**
     * Get the height of the sprite, which is the number of rows in the pixel array.
     *
     * @return The height of the sprite.
     */
    public int getHeight() {
        return pixels.length;
    }

    /**
     * Get the array of strings representing the visual pixels of the sprite.
     *
     * @return An array of strings containing the visual pixel data.
     */
    public String[] getPixels() {
        return pixels;
    }

    /**
     * Get the array of strings representing the color mask for the sprite.
     *
     * @return An array of strings containing the color mask data. This can be null if no color mask is provided.
     */
    public String[] getColorMask() {
        return colorMask;
    }
}
