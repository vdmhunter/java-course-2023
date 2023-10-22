package edu.project1.guessresults;

import edu.project1.Settings;
import org.jetbrains.annotations.NotNull;

public record FailedGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
    @Override
    public @NotNull String message() {
        return String.format(Settings.ITEMS.get("FAILED_MESSAGE"), attempt, maxAttempts);
    }
}
