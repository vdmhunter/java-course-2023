package edu.project1.views.sprites;

import java.util.HashMap;

/**
 * The {@code SecretWordSprites} class provides a collection of predefined ASCII art sprites for displaying secret words
 * or characters. These sprites are designed for use in text-based games or applications to add visual elements to the
 * user interface.
 * The class includes a set of methods for retrieving specific sprites based on characters, including uppercase letters
 * (A-Z) and a special '*' character.
 */
public final class SecretWordSprites {
    // CHECKSTYLE:OFF: Disable MultipleStringLiterals check
    private static final HashMap<Character, Sprite> sprites = new HashMap<>() {{
        put('A', new Sprite(
            new String[] {
                "  ██┐",
                " █│█│",
                "█┌┘█│",
                "████│",
                "█──█┘",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "  887",
                " 8787",
                "87787",
                "88887",
                "87787",
                "     ",
                "88888"
            }
        ));
        put('B', new Sprite(
            new String[] {
                "████ ",
                "█┌─┘█",
                "████┘",
                "█│  █",
                "████┘",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "8888 ",
                "87778",
                "88887",
                "87  8",
                "88887",
                "     ",
                "88888"
            }
        ));
        put('C', new Sprite(
            new String[] {
                " ████",
                "█┌──┘",
                "█│   ",
                "█│   ",
                " ████",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                " 8888",
                "87777",
                "87   ",
                "87   ",
                " 8888",
                "     ",
                "88888"
            }
        ));
        put('D', new Sprite(
            new String[] {
                "███┐ ",
                "█┌─█┐",
                "█│ ┌█",
                "█│┌█┘",
                "███┘ ",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "8887 ",
                "87787",
                "87 78",
                "87787",
                "8887 ",
                "     ",
                "88888"
            }
        ));
        put('E', new Sprite(
            new String[] {
                "█████",
                "█┌──┘",
                "████ ",
                "█┌─┘ ",
                "█████",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "88888",
                "87777",
                "8888 ",
                "8777 ",
                "88888",
                "     ",
                "88888"
            }
        ));
        put('F', new Sprite(
            new String[] {
                "█████",
                "█┌──┘",
                "███┐ ",
                "█┌─┘ ",
                "█┘   ",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "88888",
                "87777",
                "8887 ",
                "8777 ",
                "87   ",
                "     ",
                "88888"
            }
        ));
        put('G', new Sprite(
            new String[] {
                " ███▌",
                "█┌─┘ ",
                "█│ ██",
                "█│  █",
                "▀████",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                " 8888",
                "8777 ",
                "87 88",
                "87  8",
                "88888",
                "     ",
                "88888"
            }
        ));
        put('H', new Sprite(
            new String[] {
                "█┐ █┐",
                "█│ █│",
                "████│",
                "█┌─█│",
                "█┘ █┘",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "87 87",
                "87 87",
                "88887",
                "87787",
                "87 87",
                "     ",
                "88888"
            }
        ));
        put('I', new Sprite(
            new String[] {
                " ███┐",
                "  █┌┘",
                "  █│ ",
                "  █└┐",
                " ███┘",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                " 8887",
                "  877",
                "  87 ",
                "  877",
                " 8887",
                "     ",
                "88888"
            }
        ));
        put('J', new Sprite(
            new String[] {
                " ███┐",
                " └─█│",
                "   █│",
                "█┐ █│",
                "└██─┘",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                " 8887",
                " 7787",
                "   87",
                "87 87",
                "78877",
                "     ",
                "88888"
            }
        ));
        put('K', new Sprite(
            new String[] {
                "█┐  █",
                "█│ █┘",
                "███┘ ",
                "█┌┘█┐",
                "█┘ └█",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "87  8",
                "87 87",
                "8887 ",
                "87787",
                "87 78",
                "     ",
                "88888"
            }
        ));
        put('L', new Sprite(
            new String[] {
                "█┐   ",
                "█│   ",
                "█│   ",
                "█└──┐",
                "█████",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "87   ",
                "87   ",
                "87   ",
                "87777",
                "88888",
                "     ",
                "88888"
            }
        ));
        put('M', new Sprite(
            new String[] {
                "█┐  █",
                "██┐██",
                "█┌█┌█",
                "█│└┘█",
                "█┘  █",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "87  8",
                "88788",
                "87878",
                "87778",
                "87  8",
                "     ",
                "88888"
            }
        ));
        put('N', new Sprite(
            new String[] {
                "█┐ █┐",
                "██┐█│",
                "█│██│",
                "█│└█│",
                "█┘ █┘",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "87 87",
                "88787",
                "87887",
                "87787",
                "87 87",
                "     ",
                "88888"
            }
        ));
        put('O', new Sprite(
            new String[] {
                " ███ ",
                "█┌─┘█",
                "█│  █",
                "█│  █",
                " ███┘",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                " 888 ",
                "87778",
                "87  8",
                "87  8",
                " 8887",
                "     ",
                "88888"
            }
        ));
        put('P', new Sprite(
            new String[] {
                "████ ",
                "█┌─┘█",
                "████┘",
                "█┌─┘ ",
                "█┘   ",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "8888 ",
                "87778",
                "88887",
                "8777 ",
                "87   ",
                "     ",
                "88888"
            }
        ));
        put('Q', new Sprite(
            new String[] {
                " ███ ",
                "█┌─┘█",
                "█│ ▌█",
                "█│ ▐█",
                " ███▐",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                " 888 ",
                "87778",
                "87 88",
                "87 88",
                " 8888",
                "     ",
                "88888"
            }
        ));
        put('R', new Sprite(
            new String[] {
                "████┐",
                "█┌──█",
                "████┘",
                "█│ █┐",
                "█┘ └█",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "88887",
                "87778",
                "88887",
                "87 87",
                "87 78",
                "     ",
                "88888"
            }
        ));
        put('S', new Sprite(
            new String[] {
                " ▄██┐",
                "█┌──█",
                " ██▄ ",
                "█└──█",
                "└██▀┘",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                " 8887",
                "87778",
                " 888 ",
                "87778",
                "78887",
                "     ",
                "88888"
            }
        ));
        put('T', new Sprite(
            new String[] {
                "█████",
                " └█┌┘",
                "  █│ ",
                "  █│ ",
                "  █┘ ",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "88888",
                " 7877",
                "  87 ",
                "  87 ",
                "  87 ",
                "     ",
                "88888"
            }
        ));
        put('U', new Sprite(
            new String[] {
                "█┐  █",
                "█│  █",
                "█│  █",
                "█└─┐█",
                "└███ ",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "87  8",
                "87  8",
                "87  8",
                "87778",
                "7888 ",
                "     ",
                "88888"
            }
        ));
        put('V', new Sprite(
            new String[] {
                "█┐  █",
                "█│  █",
                "█│ ┌█",
                " █┐█┘",
                "  █┘ ",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "87  8",
                "87  8",
                "87 78",
                " 8787",
                "  87 ",
                "     ",
                "88888"
            }
        ));
        put('W', new Sprite(
            new String[] {
                "█┐█┐█",
                "█│█│█",
                "█│█│█",
                "█│█│█",
                " █▀█ ",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "87878",
                "87878",
                "87878",
                "87878",
                " 888 ",
                "     ",
                "88888"
            }
        ));
        put('X', new Sprite(
            new String[] {
                "█┐ █┐",
                "█└─█│",
                "└██┌┘",
                "█┌─█┐",
                "█┘ █┘",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "87 87",
                "87787",
                "78877",
                "87787",
                "87 87",
                "     ",
                "88888"
            }
        ));
        put('Y', new Sprite(
            new String[] {
                "█┐ █┐",
                "█│ █│",
                "└███│",
                " └─█┘",
                " ██┘ ",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "87 87",
                "87 87",
                "78887",
                " 7787",
                " 887 ",
                "     ",
                "88888"
            }
        ));
        put('Z', new Sprite(
            new String[] {
                "████▌",
                "└──█┘",
                "  █┘ ",
                " █└─┐",
                "▐████",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "88888",
                "77787",
                "  87 ",
                " 8777",
                "88888",
                "     ",
                "88888"
            }
        ));
        put('*', new Sprite(
            new String[] {
                "░░░░░",
                "░░░░░",
                "░░░░░",
                "░░░░░",
                "░░░░░",
                "     ",
                "▄▄▄▄▄"
            },
            new String[] {
                "88888",
                "88888",
                "88888",
                "88888",
                "88888",
                "     ",
                "88888"
            }
        ));
    }};
    // CHECKSTYLE:ON: Enable MultipleStringLiterals check

    private SecretWordSprites() {
    }

    /**
     * Retrieve a predefined ASCII art sprite for a specific character.
     *
     * @param ch The character for which to retrieve the sprite. This can be an uppercase letter (A-Z) or the special
     *           '*' character.
     * @return A {@code Sprite} object representing the ASCII art for the specified character.
     *     If the character is not found, null is returned.
     */
    public static Sprite get(char ch) {
        return sprites.get(ch);
    }
}
