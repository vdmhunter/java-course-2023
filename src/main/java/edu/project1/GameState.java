package edu.project1;

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

    GameState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
