package edu.project1.views.sprites;

import java.util.HashMap;

/**
 * The {@code DescriptionSprites} class provides a collection of sprites for rendering descriptive text or borders.
 */
public final class DescriptionSprites {

    // CHECKSTYLE:OFF: Disable MultipleStringLiterals check
    private static final HashMap<Integer, Sprite> sprites = new HashMap<>() {{
        put(0, new Sprite(
            new String[] {
                "╔═════════════════════════════════════════════╗",
                "║                                             ║",
                "║                                             ║",
                "╚═════════════════════════════════════════════╝"
            },
            new String[] {
                "88888888888888888888888888888888888888888888888",
                "8                                             8",
                "8                                             8",
                "88888888888888888888888888888888888888888888888"
            }
        ));
    }};
    // CHECKSTYLE:ON: Enable MultipleStringLiterals check

    private DescriptionSprites() {
    }

    /**
     * Get the sprite for the specified index, which represents descriptive text or borders.
     *
     * @param index The index of the description sprite.
     * @return The sprite representing the specified description or border.
     */
    public static Sprite get(int index) {
        return sprites.get(index);
    }
}
