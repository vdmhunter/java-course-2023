package edu.project1.views.sprites;

import java.util.HashMap;

/**
 * The {@code AlphabetSprites} class provides a collection of sprites for rendering alphabet characters.
 */
public final class AlphabetSprites {

    // CHECKSTYLE:OFF: Disable MultipleStringLiterals check
    private static final HashMap<Integer, Sprite> sprites = new HashMap<>() {{
        put(0, new Sprite(
            new String[] {
                "┌─╖",
                "│ ║",
                "╘═╝"
            },
            new String[] {
                "888",
                "888",
                "888"
            }
        ));
    }};
    // CHECKSTYLE:ON: Enable MultipleStringLiterals check

    private AlphabetSprites() {
    }

    /**
     * Get the sprite for the specified index, which represents an alphabet character.
     *
     * @param index The index of the alphabet character sprite.
     * @return The sprite representing the specified alphabet character.
     */
    public static Sprite get(int index) {
        return sprites.get(index);
    }
}
