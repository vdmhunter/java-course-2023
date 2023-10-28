package edu.project1.models;

import edu.project1.Dictionary;
import edu.project1.GameState;
import java.util.HashSet;
import java.util.Set;

public class GameModel {
    private GameState state;
    private int attempts;
    private final String secretWord;
    private final Set<Character> usedLetters;

    public GameModel() {
        this(GameState.START, 0, Dictionary.getRandomWord(), new HashSet<>());
    }

    public GameModel(GameState state, int attempts, String secretWord, Set<Character> usedLetters) {
        this.state = state;
        this.attempts = attempts;
        this.secretWord = secretWord;
        this.usedLetters = usedLetters;
    }

    public GameState getState() {
        return this.state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public int getAttempts() {
        return this.attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getSecretWord() {
        return this.secretWord;
    }

    public Set<Character> getUsedLetters() {
        return this.usedLetters;
    }
}
