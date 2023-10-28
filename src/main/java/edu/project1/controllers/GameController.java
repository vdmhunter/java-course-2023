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

/**
 * The {@code GameController} class is responsible for controlling the game logic and user interactions
 * in a Hangman game.
 */
public class GameController {
    private final GameModel model;
    private final GameView view;
    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * Constructs a {@code GameController} with a specified {@link GameModel} and {@link GameView}.
     *
     * @param model The game model containing game data and state.
     * @param view  The view responsible for rendering the game to the user.
     */
    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Starts and manages the game loop, allowing the player to interact with the game.
     */
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

    /**
     * Checks if the game is over based on the current state.
     *
     * @return true if the game is over, false otherwise.
     */
    private boolean isGameOver() {
        return model.getState() == GameState.FINISH
            || model.getState() == GameState.PLAYER_WIN
            || model.getState() == GameState.PLAYER_LOSE;
    }

    /**
     * Prints a guess prompt message to the log.
     */
    private void printGuessPrompt() {
        LOGGER.info(Settings.SEPARATOR_MESSAGE + Settings.GUESS_PROMPT_MESSAGE);
    }

    /**
     * Reads and retrieves player input from the provided Scanner.
     *
     * @param scanner The Scanner object used for input.
     * @return The player's input as a String.
     */
    private String getPlayerInput(Scanner scanner) {
        return scanner.next();
    }

    /**
     * Attempts to process and validate the player's guess input.
     *
     * @param input The player's input as a {@link String}.
     * @return The game state based on the input and guess result.
     */
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

    /**
     * Handles the player's guess attempt, updating the game state accordingly.
     *
     * @param ch The character representing the player's guess.
     * @return The game state after processing the player's guess.
     */
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

    /**
     * Checks if the player's guess is correct by comparing it with the secret word.
     *
     * @param ch The character representing the player's guess.
     * @return true if the guess is correct, false otherwise.
     */
    private boolean isGuessCorrect(char ch) {
        return model.getSecretWord().contains(String.valueOf(ch));
    }

    /**
     * Checks if the player has won the game by guessing all the letters in the secret word.
     *
     * @param usedLetters The set of used letters by the player.
     * @return true if the player has won, false otherwise.
     */
    private boolean isGameWon(Set<Character> usedLetters) {
        return usedLetters.containsAll(getSecretWordCharacterSet());
    }

    /**
     * Retrieves the characters of the secret word as a set of characters.
     *
     * @return A set of characters representing the secret word.
     */
    private Set<Character> getSecretWordCharacterSet() {
        return model.getSecretWord().chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toSet());
    }

    /**
     * Handles an incorrect guess by updating the number of attempts and determining if
     * the player has lost the game.
     *
     * @return The game state after processing the incorrect guess.
     */
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
