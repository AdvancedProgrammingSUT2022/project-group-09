package com.civilization.View;

import java.util.Scanner;
import java.util.regex.Matcher;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Controller.GameControllerPackage.GameMenuController;
import com.civilization.Controller.GameControllerPackage.MapController;
import com.civilization.Main;
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
                showMap(matcher);
            else
                System.out.println("invalid command");

        }
    }

    private void showMap(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        while (true) {
            System.out.println(GameDataBase.getCurrentCivilization().getMap().showMap(x, y));
            System.out.println("W for up \n S for down \n D for right \n A for left \n back for end showing map");
            input = scanner.nextLine();
            if (input == "W")
                x--;
            else if (input == "S")
                x++;
            else if (input == "D")
                y++;
            else if (input == "A")
                y--;
            else if (input == "back")
                break;
        }
    }
}
