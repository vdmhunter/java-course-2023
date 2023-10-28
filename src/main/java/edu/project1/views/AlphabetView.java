package edu.project1.views;

import edu.project1.models.GameModel;
import edu.project1.views.base.Color;
import edu.project1.views.base.Coordinate;
import edu.project1.views.base.Pixel;
import edu.project1.views.sprites.AlphabetSprites;
import edu.project1.views.sprites.Sprite;

/**
 * The {@code AlphabetView} class is responsible for rendering the alphabet and displaying the player's letter
 * selection on the screen. It uses sprites to render individual letters.
 */
class AlphabetView extends View {
    static final int ALPHABET_FIRST_ROW_X = 37;
    static final int ALPHABET_FIRST_ROW_Y = 14;
    static final int ALPHABET_COUNT_OF_POSITIONS = 27;
    static final int ALPHABET_UNUSED_POSITION_NUMBER1 = 20;
    static final int ALPHABET_UNUSED_POSITION_NUMBER2 = 21;
    static final int DECIMAL_BASE = 10;

    /**
     * Constructs an {@code AlphabetView} with the provided screen as the rendering target.
     *
     * @param screen The screen represented as a two-dimensional array of pixels.
     */
    AlphabetView(Pixel[][] screen) {
        super(
            new Coordinate(ALPHABET_FIRST_ROW_X, ALPHABET_FIRST_ROW_Y),
            new Coordinate(GLOBAL_WIDTH - 1, GLOBAL_HEIGHT - 1)
        );

        this.screen = screen;
    }

    /**
     * Renders the alphabet and player's letter selection based on the provided {@link GameModel}.
     *
     * @param model The {@link GameModel} containing the game's data and state.
     */
    @Override
    public void render(GameModel model) {
        Sprite sprite = AlphabetSprites.get(0);

        for (int i = 0; i <= ALPHABET_COUNT_OF_POSITIONS; i++) {
            if (i == ALPHABET_UNUSED_POSITION_NUMBER1 || i == ALPHABET_UNUSED_POSITION_NUMBER2) {
                continue;
            }

            char ch = (char) ('A' + (i > ALPHABET_UNUSED_POSITION_NUMBER1 ? i - 2 : i));
            int x = i % DECIMAL_BASE;
            int y = i / DECIMAL_BASE;

            int topLeftX = topLeft.x() + (sprite.getWidth() + 1) * x;
            int topLeftY = topLeft.y() + (sprite.getHeight() + 1) * y;
            int bottomRightX = topLeftX + sprite.getWidth() - 1;
            int bottomRightY = topLeftY + sprite.getHeight() - 1;

            drawSpriteOnScreen(sprite, topLeftX, topLeftY, bottomRightX, bottomRightY);

            Color color = !model.getUsedLetters().contains(ch)
                ? Color.WHITE
                : model.getSecretWord().contains(String.valueOf(ch)) ? Color.GRASS : Color.RED;

            screen[topLeftX + 1][topLeftY + 1] = new Pixel(ch, color);
        }
    }
}
