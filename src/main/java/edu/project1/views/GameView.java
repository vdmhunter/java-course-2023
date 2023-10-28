package edu.project1.views;

import edu.project1.GameState;
import edu.project1.Settings;
import edu.project1.models.GameModel;
import edu.project1.views.base.Coordinate;
import edu.project1.views.base.Pixel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameView extends View {
    private final HangmanView hangmanView;
    private final SecretWordView secretWordView;
    private final DescriptionView descriptionView;
    private final AlphabetView alphabetView;
    private final static Logger LOGGER = LogManager.getLogger();

    public GameView() {
        super(
            new Coordinate(0, 0),
            new Coordinate(GLOBAL_WIDTH - 1, GLOBAL_HEIGHT - 1)
        );

        this.screen = new Pixel[GLOBAL_WIDTH][GLOBAL_HEIGHT];

        this.hangmanView = new HangmanView(this.screen);
        this.secretWordView = new SecretWordView(this.screen);
        this.descriptionView = new DescriptionView(this.screen);
        this.alphabetView = new AlphabetView(this.screen);
    }

    @Override
    public void render(GameModel model) {
        if (!(model.getState() == GameState.FINISH)) {
            LOGGER.info(Settings.SESSION_SEPARATOR);

            hangmanView.render(model);
            secretWordView.render(model);
            descriptionView.render(model);
            alphabetView.render(model);

            for (int y = topLeft.y(); y <= bottomRight.y(); y++) {
                var sb = new StringBuilder();

                for (int x = topLeft.x(); x <= bottomRight.x(); x++) {
                    if (screen[x][y] == null) {
                        sb.append(" ");
                    } else {
                        sb.append(screen[x][y].color().getValue());
                        sb.append(screen[x][y].value());
                    }
                }

                LOGGER.info(sb + "\n");
            }
        }

        LOGGER.info("\n");

        // CHECKSTYLE:OFF: Disable MissingSwitchDefault check
        switch (model.getState()) {
            case START -> LOGGER.info(GameState.START.getMessage());
            case INVALID_INPUT -> LOGGER.info(GameState.INVALID_INPUT.getMessage());
            case USED_LETTER_INPUT -> LOGGER.info(GameState.USED_LETTER_INPUT.getMessage());
            case SUCCESSFUL_INPUT -> LOGGER.info(GameState.SUCCESSFUL_INPUT.getMessage());
            case FAILED_INPUT -> LOGGER.info(GameState.FAILED_INPUT.getMessage());
            case PLAYER_WIN -> LOGGER.info(GameState.PLAYER_WIN.getMessage());
            case PLAYER_LOSE -> LOGGER.info(GameState.PLAYER_LOSE.getMessage());
            case FINISH -> LOGGER.info(GameState.FINISH.getMessage());
        }
        // CHECKSTYLE:ON: Enable MissingSwitchDefault check
    }
}
