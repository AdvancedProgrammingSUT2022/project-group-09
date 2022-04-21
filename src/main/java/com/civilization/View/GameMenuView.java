package com.civilization.View;

import java.util.Scanner;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Controller.GameControllerPackage.GameMenuController;
import com.civilization.Controller.GameControllerPackage.MapController;
import com.civilization.MenuRegex.GameMenuRegex;
import com.civilization.MenuRegex.MainMenuRegex;

public class GameMenuView extends View {
    private final GameMenuController gameMenuController;
    private final MapController mapController;

    public GameMenuView(Scanner scanner, GameMenuController gameMenuController) {
        super(scanner);
        this.gameMenuController = gameMenuController;
        this.mapController = new MapController();
    }

    @Override
    public void run() {

        while (CurrentMenu.get() == CurrentMenu.GameMenu) {
            input = scanner.nextLine();
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.EXIT)) != null)
                System.out.println(gameMenuController.exit());
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWCURRENTMENU)) != null)
                System.out.println(CurrentMenu.get());
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWMAP)) != null)
                System.out.print(mapController.showMap(matcher, GameDataBase.getCurrentCivilization().getMap().getTerrains()));
            // TODO
            else
                System.out.println("invalid command");

        }
    }

    private void showMenu() {

    }
}
