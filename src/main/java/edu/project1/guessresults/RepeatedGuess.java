package edu.project1.guessresults;

import edu.project1.Settings;
import org.jetbrains.annotations.NotNull;

public record RepeatedGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
    @Override
    public @NotNull String message() {
        return Settings.ITEMS.get("REPEATED_MESSAGE");
    }
}
