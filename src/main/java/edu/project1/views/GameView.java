package edu.project1.views;

import edu.project1.GameState;
import edu.project1.models.GameModel;
import edu.project1.views.base.Coordinate;
import edu.project1.views.base.Pixel;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code GameView} class is responsible for rendering the visual elements and messages of the game to the screen.
 * It includes views for the hangman, secret word, game description, and alphabet for letter selection.
 */
public class GameView extends View {
    private final List<View> views;
    public static final String SESSION_SEPARATOR = "\n\n\n\n\n\n\n";
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Constructs a {@code GameView} with predefined coordinates for the game screen.
     */
    public GameView() {
        super(
            new Coordinate(0, 0),
            new Coordinate(GLOBAL_WIDTH - 1, GLOBAL_HEIGHT - 1)
        );

        this.screen = new Pixel[GLOBAL_WIDTH][GLOBAL_HEIGHT];

        views = new ArrayList<>();
        views.add(new HangmanView(this.screen));
        views.add(new SecretWordView(this.screen));
        views.add(new DescriptionView(this.screen));
        views.add(new AlphabetView(this.screen));
    }

    /**
     * Renders the game based on the provided {@link GameModel}, updating the visual elements and displaying messages.
     *
     * @param model The {@link GameModel} containing the game's data and state.
     */
    @Override
    public void render(GameModel model) {
        if (model.getState() != GameState.FINISH) {
            LOGGER.info(SESSION_SEPARATOR);

            views.forEach(view -> view.render(model));

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

                sb.append('\n');

                LOGGER.info(sb);
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
