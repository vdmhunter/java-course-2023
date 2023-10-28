package edu.project1.views.sprites;

import java.util.HashMap;

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

    public static Sprite get(int index) {
        return sprites.get(index);
    }
}
