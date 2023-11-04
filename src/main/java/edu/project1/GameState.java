package edu.project1;

import edu.project1.views.base.Color;

/**
 * The {@code GameState} enum represents the various states or outcomes that can occur during the execution
 * of a word-guessing game. Each enum constant represents a different game state and provides a corresponding
 * message that can be displayed to the player.
 */
public enum GameState {
    START(Color.BODY.getValue() + "You have"
        + Color.CYAN.getValue() + " 5"
        + Color.BODY.getValue() + " attempts.\n"
        + Color.BODY.getValue() + "To exit the game, write: "
        + Color.CYAN.getValue() + "exit\n"),
    INVALID_INPUT(Color.BODY.getValue() + "Invalid input! Try to guess a letter again.\n"),
    USED_LETTER_INPUT(Color.BODY.getValue() + "You have already used this letter! Try another one.\n"),
    SUCCESSFUL_INPUT(Color.BODY.getValue() + "Hit!\n"),
    FAILED_INPUT(Color.BODY.getValue() + "Missed!\n"),
    PLAYER_WIN(Color.BODY.getValue() + "You win!\n"),
    PLAYER_LOSE(Color.BODY.getValue() + "You lost!\n"),
    FINISH(Color.BODY.getValue() + "Thanks for playing! Good bye!\n");

    private final String message;

    /**
     * Constructs a {@code GameState} enum constant with the provided message.
     *
     * @param message The message associated with the game state.
     */
    GameState(String message) {
        this.message = message;
    }

    /**
     * Get the message associated with the game state.
     *
     * @return The message as a `String`.
     */
    public String getMessage() {
        return message;
    }
}
