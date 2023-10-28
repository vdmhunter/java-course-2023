package edu.project1;

/**
 * The {@code GameState} enum represents the various states or outcomes that can occur during the execution
 * of a word-guessing game. Each enum constant represents a different game state and provides a corresponding
 * message that can be displayed to the player.
 */
public enum GameState {
    START(String.format(Settings.ATTEMPTS_MESSAGE, Settings.MAX_ATTEMPTS_COUNT)
        + String.format(Settings.EXIT_INFO_MESSAGE, Settings.EXIT_COMMAND)),
    INVALID_INPUT(Settings.INVALID_LETTER_MESSAGE),
    USED_LETTER_INPUT(Settings.USED_LETTER_MESSAGE),
    SUCCESSFUL_INPUT(Settings.HIT_MESSAGE),
    FAILED_INPUT(Settings.FAILED_MESSAGE),
    PLAYER_WIN(Settings.WIN_MESSAGE),
    PLAYER_LOSE(Settings.DEFEAT_MESSAGE),
    FINISH(Settings.EXIT_MESSAGE);

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
