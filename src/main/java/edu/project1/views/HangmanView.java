package edu.project1.views;

import edu.project1.models.GameModel;
import edu.project1.views.base.Coordinate;
import edu.project1.views.base.Pixel;
import edu.project1.views.sprites.HangmanSprites;
import edu.project1.views.sprites.Sprite;

/**
 * The {@code HangmanView} class is responsible for rendering the visual representation of the hangman on the screen.
 * It displays the hangman's state based on the number of incorrect attempts made by the player.
 */
class HangmanView extends View {
    static final int HANGMAN_WIDTH = 30;

    /**
     * Constructs a {@code HangmanView} with the provided screen as the rendering target.
     *
     * @param screen The screen represented as a two-dimensional array of pixels.
     */
    HangmanView(Pixel[][] screen) {
        super(
            new Coordinate(0, 0),
            new Coordinate(HANGMAN_WIDTH, GLOBAL_HEIGHT - 1)
        );

        this.screen = screen;
    }

    /**
     * Renders the visual representation of the hangman on the screen based on the provided {@link GameModel}.
     *
     * @param model The {@link GameModel} containing the game's data and state,
     *              including the number of incorrect attempts.
     */
    @Override
    public void render(GameModel model) {
        Sprite sprite = HangmanSprites.get(model.getAttempts());
        drawSpriteOnScreen(sprite, topLeft.x(), topLeft.y(), bottomRight.x(), bottomRight.y());
    }
}
