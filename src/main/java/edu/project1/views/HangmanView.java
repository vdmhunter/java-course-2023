package edu.project1.views;

import edu.project1.models.GameModel;
import edu.project1.views.base.Coordinate;
import edu.project1.views.base.Pixel;
import edu.project1.views.sprites.HangmanSprites;
import edu.project1.views.sprites.Sprite;

class HangmanView extends View {
    static final int HANGMAN_WIDTH = 30;

    HangmanView(Pixel[][] screen) {
        super(
            new Coordinate(0, 0),
            new Coordinate(HANGMAN_WIDTH, GLOBAL_HEIGHT - 1)
        );

        this.screen = screen;
    }

    @Override
    public void render(GameModel model) {
        Sprite sprite = HangmanSprites.get(model.getAttempts());
        drawSpriteOnScreen(sprite, topLeft.x(), topLeft.y(), bottomRight.x(), bottomRight.y());
    }
}
