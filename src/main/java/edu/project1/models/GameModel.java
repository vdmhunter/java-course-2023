package edu.project1.models;

import edu.project1.Dictionary;
import edu.project1.GameState;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@code GameModel} class encapsulates the game state, player attempts, the secret word to guess, and
 * the set of used letters in the Hangman game.
 */
public class GameModel {
    private GameState state;
    private int attempts;
    private final String secretWord;
    private final Set<Character> usedLetters;

    /**
     * Constructs a new {@code GameModel} with default values for a new game.
     */
    public GameModel() {
        this(GameState.START, 0, Dictionary.getRandomWord(), new HashSet<>());
    }

    /**
     * Constructs a {@code GameModel} with specific initial values for game state, attempts, secret word,
     * and used letters.
     *
     * @param state       The initial game state.
     * @param attempts    The number of attempts made by the player.
     * @param secretWord  The secret word to be guessed.
     * @param usedLetters The set of letters already used by the player.
     */
    public GameModel(GameState state, int attempts, String secretWord, Set<Character> usedLetters) {
        this.state = state;
        this.attempts = attempts;
        this.secretWord = secretWord;
        this.usedLetters = usedLetters;
    }

    /**
     * Retrieves the current game state.
     *
     * @return The current game state.
     */
    public GameState getState() {
        return this.state;
    }

    /**
     * Sets the game state to a new value.
     *
     * @param state The new game state to set.
     */
    public void setState(GameState state) {
        this.state = state;
    }

    /**
     * Retrieves the number of attempts made by the player.
     *
     * @return The number of attempts made to guess the secret word.
     */
    public int getAttempts() {
        return this.attempts;
    }

    /**
     * Sets the number of attempts made by the player.
     *
     * @param attempts The new number of attempts to set.
     */
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    /**
     * Retrieves the secret word that the player needs to guess.
     *
     * @return The secret word as a String.
     */
    public String getSecretWord() {
        return this.secretWord;
    }

    /**
     * Retrieves the set of letters that the player has already used in their guesses.
     *
     * @return A set of used letters as characters.
     */
    public Set<Character> getUsedLetters() {
        return this.usedLetters;
    }
}
