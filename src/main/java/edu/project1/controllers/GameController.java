package edu.project1.controllers;

import edu.project1.Dictionary;
import edu.project1.Settings;
import edu.project1.guessresults.DefeatGuess;
import edu.project1.guessresults.FailedGuess;
import edu.project1.guessresults.GuessResult;
import edu.project1.guessresults.RepeatedGuess;
import edu.project1.guessresults.SuccessfulGuess;
import edu.project1.guessresults.UncorrectedGuess;
import edu.project1.guessresults.WinGuess;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GameController {
    private final String targetWord;
    private final char[] playerAnswer;
    private final Set<Character> usedLetters;
    private int attempts;
    private int numberOfHiddenLetters;
    private boolean isGameFinished;

    private final static int MAX_ATTEMPTS = 5;
    private final static String SEPARATOR_LINE = "SEPARATOR_LINE";
    private final static Logger LOGGER = LogManager.getLogger();

    public GameController() {
        this.targetWord = Dictionary.getRandomWord();
        this.playerAnswer = new char[targetWord.length()];
        this.usedLetters = new HashSet<>();
        this.attempts = 0;
        this.numberOfHiddenLetters = targetWord.length();
        this.isGameFinished = false;

        Arrays.fill(this.playerAnswer, '*');
    }

    public GameController(String word) {
        this.targetWord = word;
        this.playerAnswer = new char[targetWord.length()];
        this.usedLetters = new HashSet<>();
        this.attempts = 0;
        this.numberOfHiddenLetters = targetWord.length();
        this.isGameFinished = false;

        Arrays.fill(this.playerAnswer, '*');
    }

    public void playGame() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!isGameFinished()) {
                printGuessPrompt();

                String playerInput = getPlayerInput(scanner);
                GuessResult guessResult = tryGuess(playerInput);

                printGameState(guessResult);
            }
        }
    }

    public GuessResult tryGuess(String input) {
        if (isInvalidInput(input)) {
            return uncorrectedGuess();
        }

        if (isGiveUpCommand(input)) {
            return giveUp();
        }

        if (isUncorrectedGuess(input)) {
            return uncorrectedGuess();
        }

        return guess(input.toLowerCase(Locale.ROOT).charAt(0));
    }

    private @Nullable String getPlayerInput(@NotNull Scanner scanner) {
        String input = scanner.hasNext() ? scanner.next() : null;
        LOGGER.info("\n");

        return input;
    }

    private boolean isInvalidInput(String input) {
        return input == null || input.isEmpty();
    }

    private boolean isGiveUpCommand(@NotNull String input) {
        return input.equalsIgnoreCase(Settings.ITEMS.get("GIVE_UP_COMMAND"));
    }

    private boolean isUncorrectedGuess(@NotNull String input) {
        return !input.matches(Settings.ITEMS.get("CORRECT_INPUT_REGEX"));
    }

    private void printGuessPrompt() {
        LOGGER.info(Settings.ITEMS.get(SEPARATOR_LINE));
        LOGGER.info(Settings.ITEMS.get("GUESS_PROMPT_MESSAGE"));
    }

    private void printGameState(@NotNull GuessResult guess) {
        LOGGER.info(guess.message() + "\n");
        LOGGER.info("The word: " + String.valueOf(guess.state()));
        LOGGER.info(Settings.ITEMS.get(SEPARATOR_LINE));
    }

    @NotNull GuessResult guess(char guess) {
        if (isUsedLetter(guess)) {
            return new RepeatedGuess(playerAnswer, attempts, MAX_ATTEMPTS);
        }

        boolean isSuccessfulPlayerGuess = handlePlayerGuess(guess);

        return isSuccessfulPlayerGuess
            ? handleSuccessfulPlayerGuess()
            : handleFailedPlayerGuess();
    }

    @NotNull GuessResult giveUp() {
        return handlePlayerDefeated();
    }

    @NotNull GuessResult uncorrectedGuess() {
        return new UncorrectedGuess(playerAnswer, attempts, MAX_ATTEMPTS);
    }

    boolean isGameFinished() {
        return isGameFinished;
    }

    private boolean isMaxAttemptsExceeded() {
        return attempts >= MAX_ATTEMPTS;
    }

    private boolean isUsedLetter(char guess) {
        return usedLetters.contains(guess);
    }

    private boolean isAnswerFullyGuessed() {
        return numberOfHiddenLetters == 0;
    }

    private boolean handlePlayerGuess(char guess) {
        usedLetters.add(guess);
        boolean isSuccessfulGuess = false;

        for (int i = 0; i < targetWord.length(); ++i) {
            if (guess == targetWord.charAt(i)) {
                playerAnswer[i] = guess;
                numberOfHiddenLetters--;

                isSuccessfulGuess = true;
            }
        }

        return isSuccessfulGuess;
    }

    @Contract(" -> new")
    private @NotNull GuessResult handleSuccessfulPlayerGuess() {
        return isAnswerFullyGuessed()
            ? handlePlayerWon()
            : new SuccessfulGuess(playerAnswer, attempts, MAX_ATTEMPTS);
    }

    @Contract(" -> new")
    private @NotNull GuessResult handleFailedPlayerGuess() {
        attempts++;

        return isMaxAttemptsExceeded()
            ? handlePlayerDefeated()
            : new FailedGuess(playerAnswer, attempts, MAX_ATTEMPTS);
    }

    @Contract(" -> new")
    private @NotNull GuessResult handlePlayerDefeated() {
        isGameFinished = true;
        return new DefeatGuess(playerAnswer, attempts, MAX_ATTEMPTS);
    }

    @Contract(" -> new")
    private @NotNull GuessResult handlePlayerWon() {
        isGameFinished = true;
        return new WinGuess(playerAnswer, attempts, MAX_ATTEMPTS);
    }
}
