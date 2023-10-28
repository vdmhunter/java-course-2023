package edu.project1;

import edu.project1.views.base.Color;

public final class Settings {
    public static final int MAX_SECRET_WORD_LENGTH = 8;
    public static final int MAX_ATTEMPTS_COUNT = 5;
    public static final String ATTEMPTS_MESSAGE =
        Color.BODY.getValue() + "You have" + Color.CYAN.getValue() + " %d" + Color.BODY.getValue() + " attempts.\n";
    public static final String EXIT_COMMAND = "EXIT";
    public static final String EXIT_INFO_MESSAGE =
        Color.BODY.getValue() + "To exit the game, write: " + Color.CYAN.getValue() + "%s%n";
    public static final String GUESS_PROMPT_MESSAGE = Color.BODY.getValue() + "Guess a letter: ";
    public static final String INPUT_REGEX = "^[a-zA-Z]$";
    public static final String HIT_MESSAGE = Color.BODY.getValue() + "Hit!\n";
    public static final String WIN_MESSAGE = Color.BODY.getValue() + "You win!\n";
    public static final String DEFEAT_MESSAGE = Color.BODY.getValue() + "You lost!\n";
    public static final String FAILED_MESSAGE = Color.BODY.getValue() + "Missed!\n";
    public static final String USED_LETTER_MESSAGE =
        Color.BODY.getValue() + "You have already used this letter! Try another one.\n";
    public static final String INVALID_LETTER_MESSAGE =
        Color.BODY.getValue() + "Invalid input! Try to guess a letter again.\n";
    public static final String EXIT_MESSAGE = Color.BODY.getValue() + "Thanks for playing! Good bye!\n";
    public static final String SESSION_SEPARATOR = "\n\n\n\n\n";
    public static final String SEPARATOR_MESSAGE =
        Color.BODY.getValue() + "===================================================\n";

    private Settings() {
    }
}
