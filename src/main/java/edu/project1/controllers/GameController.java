package edu.project1.controllers;

import edu.project1.GameState;
import edu.project1.Settings;
import edu.project1.models.GameModel;
import edu.project1.views.GameView;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameController {
    private final GameModel model;
    private final GameView view;
    private final static Logger LOGGER = LogManager.getLogger();

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public void playGame() {
        view.render(model);

        try (Scanner scanner = new Scanner(System.in)) {
            while (!isGameOver()) {
                printGuessPrompt();

                String input = getPlayerInput(scanner);
                GameState state = tryGuess(input);
                model.setState(state);
                view.render(model);
            }
        }
    }

    private boolean isGameOver() {
        return model.getState() == GameState.FINISH
            || model.getState() == GameState.PLAYER_WIN
            || model.getState() == GameState.PLAYER_LOSE;
    }

    private void printGuessPrompt() {
        LOGGER.info(Settings.SEPARATOR_MESSAGE + Settings.GUESS_PROMPT_MESSAGE);
    }

    private String getPlayerInput(Scanner scanner) {
        return scanner.next();
    }

    private GameState tryGuess(String input) {
        if (input.equalsIgnoreCase(Settings.EXIT_COMMAND)) {
            return GameState.FINISH;
        }

        if (!input.matches(Settings.INPUT_REGEX)) {
            return GameState.INVALID_INPUT;
        }

        char ch = input.toUpperCase().charAt(0);

        return model.getUsedLetters().contains(ch)
            ? GameState.USED_LETTER_INPUT
            : handlePlayerGuess(ch);
    }

    private GameState handlePlayerGuess(char ch) {
        Set<Character> usedLetters = model.getUsedLetters();
        usedLetters.add(ch);

        if (isGuessCorrect(ch)) {
            if (isGameWon(usedLetters)) {
                return GameState.PLAYER_WIN;
            }
            return GameState.SUCCESSFUL_INPUT;
        } else {
            return handleIncorrectGuess();
        }
    }

    private boolean isGuessCorrect(char ch) {
        return model.getSecretWord().contains(String.valueOf(ch));
    }

    private boolean isGameWon(Set<Character> usedLetters) {
        return usedLetters.containsAll(getSecretWordCharacterSet());
    }

    private Set<Character> getSecretWordCharacterSet() {
        return model.getSecretWord().chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toSet());
    }

    private GameState handleIncorrectGuess() {
        var attempts = model.getAttempts();
        ++attempts;
        model.setAttempts(attempts);

        if (attempts >= Settings.MAX_ATTEMPTS_COUNT) {
            return GameState.PLAYER_LOSE;
        } else {
            return GameState.FAILED_INPUT;
        }
    }
}
