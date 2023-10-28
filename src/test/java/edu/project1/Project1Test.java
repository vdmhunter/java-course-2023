package edu.project1;

import edu.project1.controllers.GameController;
import edu.project1.models.GameModel;
import edu.project1.views.GameView;
import java.io.ByteArrayInputStream;
import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Tests for Project 1
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Project1Test {
    @Test
    @Order(1)
    @DisplayName("Test the situation where the player wins")
    void gameController_TestTheSituationWhereThePlayerWins() {
        // Arrange
        String WIN_GUESSES = "A\nB\n4\nA\nG\nI\n[\nR\nT\nU";
        GameModel model = new GameModel(GameState.START, 0, "GUITAR", new HashSet<>());
        GameView view = new GameView();
        GameController controller = new GameController(model, view);

        ByteArrayInputStream in = new ByteArrayInputStream(WIN_GUESSES.getBytes());
        System.setIn(in);

        GameState expected = GameState.PLAYER_WIN;

        // Act
        controller.playGame();
        GameState actual = model.getState();

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    @DisplayName("Test the situation where the player lose")
    void gameController_TestTheSituationWhereThePlayerLose() {
        // Arrange
        String LOSE_GUESSES = "B\nC\nD\nE\nF\nH\n";
        GameModel model = new GameModel(GameState.START, 0, "GUITAR", new HashSet<>());
        GameView view = new GameView();
        GameController controller = new GameController(model, view);

        ByteArrayInputStream in = new ByteArrayInputStream(LOSE_GUESSES.getBytes());
        System.setIn(in);

        GameState expected = GameState.PLAYER_LOSE;

        // Act
        controller.playGame();
        GameState actual = model.getState();

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    @DisplayName("Test the situation when player type \"exit\"")
    void gameController_TestTheSituationWhenPlayerTypeExit() {
        // Arrange
        String EXIT_INPUT = "exit\n";
        GameModel model = new GameModel(GameState.START, 0, "GUITAR", new HashSet<>());
        GameView view = new GameView();
        GameController controller = new GameController(model, view);

        ByteArrayInputStream in = new ByteArrayInputStream(EXIT_INPUT.getBytes());
        System.setIn(in);

        GameState expected = GameState.FINISH;

        // Act
        controller.playGame();
        GameState actual = model.getState();

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    @DisplayName("Test Initial Game Model State")
    void gameModel_InitialState() {
        // Arrange
        GameModel model = new GameModel();

        // Act & Assert
        Assertions.assertAll(
            () -> Assertions.assertEquals(GameState.START, model.getState()),
            () -> Assertions.assertEquals(0, model.getAttempts()),
            () -> Assertions.assertNotEquals("", model.getSecretWord()),
            () -> Assertions.assertEquals(new HashSet<>(), model.getUsedLetters())
        );
    }
}
