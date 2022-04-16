package View;

import java.util.Scanner;

import GameControllerPackage.GameMenuController;

public class GameMenuView extends View {
    private GameMenuController gameMenuController;

    public GameMenuView(Scanner scanner, GameMenuController gameMenuController) {
        super(scanner);
        this.gameMenuController = gameMenuController;
    }

    @Override
    public void run() {
    }
    // TODO
}
