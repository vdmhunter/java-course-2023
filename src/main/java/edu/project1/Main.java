package edu.project1;

import edu.project1.controllers.GameController;
import edu.project1.models.GameModel;
import edu.project1.views.GameView;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        var model = new GameModel();
        var view = new GameView();
        var controller = new GameController(model, view);

        controller.playGame();
    }
}
