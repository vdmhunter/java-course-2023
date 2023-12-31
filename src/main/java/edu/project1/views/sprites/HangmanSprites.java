package edu.project1.views.sprites;

import java.util.HashMap;

/**
 * The {@code HangmanSprites} class provides a collection of sprites for rendering different hangman stages.
 */
@SuppressWarnings("SpellCheckingInspection")
public final class HangmanSprites {
    private static final HashMap<Integer, Sprite> SPRITES = new HashMap<>();

    static {
        // CHECKSTYLE:OFF: Disable MultipleStringLiterals check
        SPRITES.put(0, new Sprite(
            new String[] {
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "▒▓████████████████████████████░"
            },
            new String[] {
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "                               ",
                "9999999999999999999999999999999"
            }
        ));
        SPRITES.put(1, new Sprite(
            new String[] {
                "          █░                   ",
                "         ██            ██      ",
                "   ████████████████████████    ",
                "   ██  ██              ██      ",
                "   ██ ██               ██      ",
                "   █████                       ",
                "   ████                        ",
                "   ███                         ",
                "  ▓██░                         ",
                "  ████                         ",
                "    ██                         ",
                "    ██                         ",
                "    ██░                        ",
                "    ▓██                        ",
                "    ▓██                        ",
                "     ███                       ",
                "     ███                       ",
                "     ████                      ",
                "      ███                      ",
                "      ████                     ",
                "      █████                    ",
                "      █████                    ",
                "      ▓████░                   ",
                "   ▓█████████░                 ",
                "▒▓████████████████████████████░"
            },
            new String[] {
                "          AA                   ",
                "         AA            AA      ",
                "   AAAAAAAAAAAAAAAAAAAAAAAA    ",
                "   AA  AA              AA      ",
                "   AA AA               AA      ",
                "   AAAAA                       ",
                "   AAAA                        ",
                "   AAA                         ",
                "  AAAA                         ",
                "  AAAA                         ",
                "    AA                         ",
                "    AA                         ",
                "    AAA                        ",
                "    AAA                        ",
                "    AAA                        ",
                "     AAA                       ",
                "     AAA                       ",
                "     AAAA                      ",
                "      AAA                      ",
                "      AAAA                     ",
                "      AAAAA                    ",
                "      AAAAA                    ",
                "      AAAAAA                   ",
                "   AAAAAAAAAAA                 ",
                "9999999999999999999999999999999"
            }
        ));
        SPRITES.put(2, new Sprite(
            new String[] {
                "          █░                   ",
                "         ██            ██      ",
                "   ████████████████████████    ",
                "   ██  ██              ██      ",
                "   ██ ██               ██      ",
                "   █████             ░▒▒▒▒░    ",
                "   ████            ▒▒▒▒▒▒▒▒▒▒  ",
                "   ███              █ ▄  ▄ █   ",
                "  ▓██░              █      █   ",
                "  ████              █ ▀▄▄▀ █   ",
                "    ██               █▄  ▄█    ",
                "    ██                 ██      ",
                "    ██░                        ",
                "    ▓██                        ",
                "    ▓██                        ",
                "     ███                       ",
                "     ███                       ",
                "     ████                      ",
                "      ███                      ",
                "      ████                     ",
                "      █████                    ",
                "      █████                    ",
                "      ▓████░                   ",
                "   ▓█████████░                 ",
                "▒▓████████████████████████████░"
            },
            new String[] {
                "          AA                   ",
                "         AA            AA      ",
                "   AAAAAAAAAAAAAAAAAAAAAAAA    ",
                "   AA  AA              AA      ",
                "   AA AA               AA      ",
                "   AAAAA             BBBBBB    ",
                "   AAAA            BBBBBBBBBB  ",
                "   AAA              C D  D C   ",
                "  AAAA              C      C   ",
                "  AAAA              C EEEE C   ",
                "    AA               CC  CC    ",
                "    AA                 CC      ",
                "    AAA                        ",
                "    AAA                        ",
                "    AAA                        ",
                "     AAA                       ",
                "     AAA                       ",
                "     AAAA                      ",
                "      AAA                      ",
                "      AAAA                     ",
                "      AAAAA                    ",
                "      AAAAA                    ",
                "      AAAAAA                   ",
                "   AAAAAAAAAAA                 ",
                "9999999999999999999999999999999"
            }
        ));
        SPRITES.put(3, new Sprite(
            new String[] {
                "          █░                   ",
                "         ██            ██      ",
                "   ████████████████████████    ",
                "   ██  ██              ██      ",
                "   ██ ██               ██      ",
                "   █████             ░▒▒▒▒░    ",
                "   ████            ▒▒▒▒▒▒▒▒▒▒  ",
                "   ███              █ ▄  ▄ █   ",
                "  ▓██░              █      █   ",
                "  ████              █ ▀▄▄▀ █   ",
                "    ██               █▄  ▄█    ",
                "    ██                 ██      ",
                "    ██░                ██      ",
                "    ▓██                ██      ",
                "    ▓██                ██      ",
                "     ███               ██      ",
                "     ███               ██      ",
                "     ████                      ",
                "      ███                      ",
                "      ████                     ",
                "      █████                    ",
                "      █████                    ",
                "      ▓████░                   ",
                "   ▓█████████░                 ",
                "▒▓████████████████████████████░"
            },
            new String[] {
                "          AA                   ",
                "         AA            AA      ",
                "   AAAAAAAAAAAAAAAAAAAAAAAA    ",
                "   AA  AA              AA      ",
                "   AA AA               AA      ",
                "   AAAAA             BBBBBB    ",
                "   AAAA            BBBBBBBBBB  ",
                "   AAA              C D  D C   ",
                "  AAAA              C      C   ",
                "  AAAA              C EEEE C   ",
                "    AA               CC  CC    ",
                "    AA                 CC      ",
                "    AAA                FF      ",
                "    AAA                FF      ",
                "    AAA                FF      ",
                "     AAA               FF      ",
                "     AAA               FF      ",
                "     AAAA                      ",
                "      AAA                      ",
                "      AAAA                     ",
                "      AAAAA                    ",
                "      AAAAA                    ",
                "      AAAAAA                   ",
                "   AAAAAAAAAAA                 ",
                "9999999999999999999999999999999"
            }
        ));
        SPRITES.put(4, new Sprite(
            new String[] {
                "          █░                   ",
                "         ██            ██      ",
                "   ████████████████████████    ",
                "   ██  ██              ██      ",
                "   ██ ██               ██      ",
                "   █████             ░▒▒▒▒░    ",
                "   ████            ▒▒▒▒▒▒▒▒▒▒  ",
                "   ███              █ ▄  ▄ █   ",
                "  ▓██░              █      █   ",
                "  ████              █ ▀▄▄▀ █   ",
                "    ██               █▄  ▄█    ",
                "    ██                 ██      ",
                "    ██░               ████     ",
                "    ▓██             ▒█▒██▒█▒   ",
                "    ▓██            ▒█  ██  █▒  ",
                "     ███               ██      ",
                "     ███               ██      ",
                "     ████                      ",
                "      ███                      ",
                "      ████                     ",
                "      █████                    ",
                "      █████                    ",
                "      ▓████░                   ",
                "   ▓█████████░                 ",
                "▒▓████████████████████████████░"
            },
            new String[] {
                "          AA                   ",
                "         AA            AA      ",
                "   AAAAAAAAAAAAAAAAAAAAAAAA    ",
                "   AA  AA              AA      ",
                "   AA AA               AA      ",
                "   AAAAA             BBBBBB    ",
                "   AAAA            BBBBBBBBBB  ",
                "   AAA              C D  D C   ",
                "  AAAA              C      C   ",
                "  AAAA              C EEEE C   ",
                "    AA               CC  CC    ",
                "    AA                 CC      ",
                "    AAA               FFFF     ",
                "    AAA             CCFFFFCC   ",
                "    AAA            CC  FF  CC  ",
                "     AAA               FF      ",
                "     AAA               FF      ",
                "     AAAA                      ",
                "      AAA                      ",
                "      AAAA                     ",
                "      AAAAA                    ",
                "      AAAAA                    ",
                "      AAAAAA                   ",
                "   AAAAAAAAAAA                 ",
                "9999999999999999999999999999999"
            }
        ));
        SPRITES.put(5, new Sprite(
            new String[] {
                "          █░                   ",
                "         ██            ██      ",
                "   ████████████████████████    ",
                "   ██  ██              ██      ",
                "   ██ ██               ██      ",
                "   █████             ░▒▒▒▒░    ",
                "   ████            ▒▒▒▒▒▒▒▒▒▒  ",
                "   ███              █ ▄  ▄ █   ",
                "  ▓██░              █      █   ",
                "  ████              █ ▀▄▄▀ █   ",
                "    ██               █▄  ▄█    ",
                "    ██                 ██      ",
                "    ██░               ████     ",
                "    ▓██             ▒█▒██▒█▒   ",
                "    ▓██            ▒█  ██  █▒  ",
                "     ███               ██      ",
                "     ███              ▒██▒     ",
                "     ████            ▒█  █▒    ",
                "      ███           ██▒  ▒██   ",
                "      ████                     ",
                "      █████                    ",
                "      █████                    ",
                "      ▓████░                   ",
                "   ▓█████████░                 ",
                "▒▓████████████████████████████░"
            },
            new String[] {
                "          AA                   ",
                "         AA            AA      ",
                "   AAAAAAAAAAAAAAAAAAAAAAAA    ",
                "   AA  AA              AA      ",
                "   AA AA               AA      ",
                "   AAAAA             BBBBBB    ",
                "   AAAA            BBBBBBBBBB  ",
                "   AAA              C D  D C   ",
                "  AAAA              C      C   ",
                "  AAAA              C EEEE C   ",
                "    AA               CC  CC    ",
                "    AA                 CC      ",
                "    AAA               FFFF     ",
                "    AAA             CCFFFFCC   ",
                "    AAA            CC  FF  CC  ",
                "     AAA               FF      ",
                "     AAA              FFFF     ",
                "     AAAA            CC  CC    ",
                "      AAA           111  111   ",
                "      AAAA                     ",
                "      AAAAA                    ",
                "      AAAAA                    ",
                "      AAAAAA                   ",
                "   AAAAAAAAAAA                 ",
                "9999999999999999999999999999999"
            }
        ));
        // CHECKSTYLE:ON: Enable MultipleStringLiterals check
    }

    private HangmanSprites() {
    }

    /**
     * Get the sprite for the specified index, which represents hangman stages.
     *
     * @param index The index of the hangman sprite.
     * @return The sprite representing the specified hangman stage.
     */
    public static Sprite get(int index) {
        return SPRITES.get(index);
    }
}
