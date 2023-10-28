package edu.project1.views;

import edu.project1.Dictionary;
import edu.project1.models.GameModel;
import edu.project1.views.base.Color;
import edu.project1.views.base.Coordinate;
import edu.project1.views.base.Pixel;
import edu.project1.views.sprites.DescriptionSprites;
import edu.project1.views.sprites.Sprite;

/**
 * The {@code DescriptionView} class is responsible for rendering the game's description on the screen. It includes
 * information about the secret word to be guessed.
 */
class DescriptionView extends View {
    static final int DESCRIPTION_FIRST_ROW_X = 35;
    static final int DESCRIPTION_FIRST_ROW_Y = 10;
    static final int DESCRIPTION_TOP_BORDER = 9;
    static final int DESCRIPTION_LOW_BORDER = 12;

    /**
     * Constructs a {@code DescriptionView} with the provided screen as the rendering target.
     *
     * @param screen The screen represented as a two-dimensional array of pixels.
     */
    DescriptionView(Pixel[][] screen) {
        super(
            new Coordinate(SCREEN_DIVIDER, DESCRIPTION_TOP_BORDER),
            new Coordinate(GLOBAL_WIDTH - 1, DESCRIPTION_LOW_BORDER)
        );

        this.screen = screen;
    }

    /**
     * Renders the description of the game based on the provided {@link GameModel},
     * including information about the secret word.
     *
     * @param model The {@link GameModel} containing the game's data and state.
     */
    @Override public void render(GameModel model) {
        Sprite sprite = DescriptionSprites.get(0);

        int topLeftX = topLeft.x();
        int topLeftY = topLeft.y();
        int bottomRightX = topLeftX + sprite.getWidth() - 1;
        int bottomRightY = topLeft.y() + sprite.getHeight() - 1;

        drawSpriteOnScreen(sprite, topLeftX, topLeftY, bottomRightX, bottomRightY);

        Dictionary.Lines lines = Dictionary.WORDS.get(model.getSecretWord());

        int i = 0;

        for (char ch : lines.getFirstLine().toCharArray()) {
            screen[DESCRIPTION_FIRST_ROW_X + i][DESCRIPTION_FIRST_ROW_Y] = new Pixel(ch, Color.WHITE);
            i++;
        }

        i = 0;

        for (char ch : lines.getSecondLine().toCharArray()) {
            screen[DESCRIPTION_FIRST_ROW_X + i][DESCRIPTION_FIRST_ROW_Y + 1] = new Pixel(ch, Color.WHITE);
            i++;
        }
    }
}
