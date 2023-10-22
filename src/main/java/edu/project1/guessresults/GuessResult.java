package edu.project1.guessresults;

import org.jetbrains.annotations.NotNull;

public sealed interface GuessResult permits
    WinGuess, DefeatGuess, SuccessfulGuess,
    FailedGuess, RepeatedGuess, UncorrectedGuess {
    char[] state();

    int attempt();

    int maxAttempts();

    @NotNull String message();
}
