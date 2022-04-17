package View;

import java.util.Scanner;
import java.util.regex.Matcher;

import Controller.MainMenuController;
import MenuRegex.MainMenuRegex;
import MenuRegex.ProfileMenuRegex;

public class MainMenuView extends View {
    private final MainMenuController MainMenuController;

    public MainMenuView(Scanner scanner, MainMenuController mainMenuController) {
        super(scanner);
        this.MainMenuController = mainMenuController;
    }

    @Override
    public void run() {

        while (CurrentMenu.get() == CurrentMenu.MainMenu) {
            input = scanner.nextLine();
            if ((matcher = MainMenuRegex.getMatcher(input, MainMenuRegex.ENTER)) != null)
                System.out.println(MainMenuController.menuNavigate(matcher));
            else if ((matcher = MainMenuRegex.getMatcher(input, MainMenuRegex.EXIT)) != null)
                System.out.println("use logout method");
            else if ((matcher = MainMenuRegex.getMatcher(input, MainMenuRegex.SHOWCURRENTMENU)) != null)
                System.out.println(CurrentMenu.get());
            else if ((matcher = MainMenuRegex.getMatcher(input, MainMenuRegex.LOGOUT)) != null)
                System.out.println(MainMenuController.logout());
            else if ((matcher = MainMenuRegex.getMatcher(input, MainMenuRegex.PLAYGAME)) != null)
                System.out.println(MainMenuController.playGame(input));
            else
                System.out.println("invalid command");
        }

    }
}
