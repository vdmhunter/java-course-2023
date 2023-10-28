package edu.project1.views.sprites;

import java.util.HashMap;

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

    public static Sprite get(int index) {
        return sprites.get(index);
    }
}
