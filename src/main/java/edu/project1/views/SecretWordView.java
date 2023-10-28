package edu.project1.views;

import edu.project1.Settings;
import edu.project1.models.GameModel;
import edu.project1.views.base.Coordinate;
import edu.project1.views.base.Pixel;
import edu.project1.views.sprites.SecretWordSprites;
import edu.project1.views.sprites.Sprite;

class SecretWordView extends View {
    static final int SECRET_WORD_TOP_BORDER = 1;
    static final int SECRET_WORD_LOW_BORDER = 7;

    SecretWordView(Pixel[][] screen) {
        super(
            new Coordinate(SCREEN_DIVIDER, SECRET_WORD_TOP_BORDER),
            new Coordinate(GLOBAL_WIDTH - 1, SECRET_WORD_LOW_BORDER)
        );

        this.screen = screen;
    }

    @Override
    public void render(GameModel model) {
        int len = model.getSecretWord().length();

        for (int i = 0; i < Settings.MAX_SECRET_WORD_LENGTH; i++) {
            if (Settings.MAX_SECRET_WORD_LENGTH - i > len) {
                continue;
            }

            char ch = model.getSecretWord().toCharArray()[i - (Settings.MAX_SECRET_WORD_LENGTH - len)];
            Sprite sprite;

            if (model.getUsedLetters().contains(ch)) {
                sprite = SecretWordSprites.get(ch);
            } else {
                sprite = SecretWordSprites.get('*');
            }

            int topLeftX = topLeft.x() + (sprite.getWidth() + 1) * i;
            int topLeftY = topLeft.y();
            int bottomRightX = topLeftX + sprite.getWidth() - 1;
            int bottomRightY = topLeft.y() + sprite.getHeight() - 1;

            drawSpriteOnScreen(sprite, topLeftX, topLeftY, bottomRightX, bottomRightY);
        }
    }
}
