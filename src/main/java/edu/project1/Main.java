package edu.project1;

import edu.project1.controllers.GameController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static int MAX_ATTEMPTS = 5;
    private final static String SEPARATOR_LINE = "SEPARATOR_LINE";
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        var controller = new GameController();

        LOGGER.info(String.format(Settings.ITEMS.get("ATTEMPTS_MESSAGE"), MAX_ATTEMPTS));
        LOGGER.info(String.format(
            Settings.ITEMS.get("GIVE_UP_COMMAND_HINT_MESSAGE"),
            Settings.ITEMS.get("GIVE_UP_COMMAND")
        ));
        LOGGER.info(Settings.ITEMS.get(SEPARATOR_LINE));

        controller.playGame();

        LOGGER.info(Settings.ITEMS.get(SEPARATOR_LINE));
        LOGGER.info(Settings.ITEMS.get("GAME_OVER_MESSAGE"));
        LOGGER.info(Settings.ITEMS.get(SEPARATOR_LINE));
    }
}
