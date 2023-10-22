package edu.project1;

import java.util.HashMap;

public final class Settings {
    public static final HashMap<String, String> ITEMS = new HashMap<>() {{
        put("CORRECT_INPUT_REGEX", "\\pL");
        put("GIVE_UP_COMMAND", "exit");
        put("SEPARATOR_LINE", "┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅");
        put("WIN_MESSAGE", "You win!");
        put("DEFEAT_MESSAGE", "You lost!");
        put("SUCCESSFUL_MESSAGE", "Hit!");
        put("FAILED_MESSAGE", "Missed, mistake %d out of %d.");
        put("REPEATED_MESSAGE", "You've already used this letter! Try another one.");
        put("ATTEMPTS_MESSAGE", "You have %d attempts.%n");
        put("GIVE_UP_COMMAND_HINT_MESSAGE", "To exit the game, enter: %s%n");
        put("GUESS_PROMPT_MESSAGE", "Guess a letter: ");
        put("GAME_OVER_MESSAGE", "Thanks for playing!");
    }};

    private Settings() {
    }
}
