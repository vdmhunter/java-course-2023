package edu.project1.views.sprites;

public final class Sprite {
    final String[] pixels;
    final String[] colorMask;

    Sprite(String[] pixels, String[] colorMask) {
        this.pixels = pixels;
        this.colorMask = colorMask;
    }

    public int getWidth() {
        return pixels[0].length();
    }

    public int getHeight() {
        return pixels.length;
    }

    public String[] getPixels() {
        return pixels;
    }

    public String[] getColorMask() {
        return colorMask;
    }
}
