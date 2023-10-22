package edu.project1;

import edu.project1.controllers.GameController;
import edu.project1.guessresults.DefeatGuess;
import edu.project1.guessresults.FailedGuess;
import edu.project1.guessresults.GuessResult;
import edu.project1.guessresults.RepeatedGuess;
import edu.project1.guessresults.SuccessfulGuess;
import edu.project1.guessresults.UncorrectedGuess;
import edu.project1.guessresults.WinGuess;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class HangmanGameTest {
    private static final String ANSWER = "elephant";
    private static final String CORRECT_GUESSES = "elphant";
    private static final String WRONG_GUESSES = "zwxoi";
    private static final int MAX_ATTEMPTS = 5;

    private GameController testGame;

    @BeforeEach
    void initNewSession() {
        testGame = new GameController(ANSWER);
    }

    @Test
    @DisplayName("Test when player wins")
    void testPlayerWon() {
        // Arrange
        List<String> inputs = new ArrayList<>();
        for (char correctGuess : CORRECT_GUESSES.toCharArray()) {
            inputs.add(String.valueOf(correctGuess));
        }

        for (int i = 0; i < inputs.size() - 1; ++i) {
            // Act
            GuessResult result = testGame.tryGuess(inputs.get(i));

            // Assert
            assertThat(result).isInstanceOf(SuccessfulGuess.class);
        }

        // Act
        GuessResult result = testGame.tryGuess(inputs.get(inputs.size() - 1));

        // Assert
        assertThat(result).isInstanceOf(WinGuess.class);
    }

    @Test
    @DisplayName("Test when player is defeated")
    void testPlayerDefeated() {
        // Arrange
        List<String> inputs = new ArrayList<>();
        for (int i = 0; i < MAX_ATTEMPTS; ++i) {
            char wrongGuess = WRONG_GUESSES.charAt(i);
            inputs.add(String.valueOf(wrongGuess));
        }

        for (int i = 0; i < MAX_ATTEMPTS - 1; ++i) {
            // Act
            GuessResult result = testGame.tryGuess(inputs.get(i));

            // Assert
            assertThat(result).isInstanceOf(FailedGuess.class);
        }

        // Act
        GuessResult result = testGame.tryGuess(inputs.get(inputs.size() - 1));

        // Assert
        assertThat(result).isInstanceOf(DefeatGuess.class);
    }

    @Test
    @DisplayName("Test when player gives up")
    void testGiveUpCommand() {
        // Arrange
        String input = Settings.ITEMS.get("GIVE_UP_COMMAND");

        // Act
        GuessResult result = testGame.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(DefeatGuess.class);
    }

    @Test
    @DisplayName("Test repeated input")
    void testRepeatedInput() {
        // Arrange
        String input = String.valueOf(ANSWER.charAt(0));

        // Act
        GuessResult result = testGame.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(SuccessfulGuess.class);

        for (int i = 0; i < 10; ++i) {
            // Act
            GuessResult resultRepeat = testGame.tryGuess(input);

            // Assert
            assertThat(resultRepeat).isInstanceOf(RepeatedGuess.class);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "*", "6", "122", "ab", "ABC"})
    @DisplayName("Test uncorrected input")
    void testUncorrectedInput(String input) {
        // Act
        GuessResult result = testGame.tryGuess(input);

        // Assert
        assertThat(result).isInstanceOf(UncorrectedGuess.class);
    }
}
