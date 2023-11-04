package edu.project1;

import edu.project1.controllers.GameController;
import edu.project1.models.GameModel;
import edu.project1.views.GameView;

/**
 * The {@code Main} class serves as the entry point for the hangman game application.
 * It initializes the game's core components, including the model, view, and controller,
 * and starts the game by invoking the {@link GameController#playGame()} method on the controller.
 */
public final class Main {
    private Main() {
    }

    /**
     * The main method that is called when the application is executed.
     * It initializes the game model, view, and controller, and then starts the game by invoking
     * the {@link GameController#playGame()} method on the controller.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        var model = new GameModel();
        var view = new GameView();
        var controller = new GameController(model, view);

        controller.playGame();
    }
}
