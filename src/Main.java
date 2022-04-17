import Controller.GameControllerPackage.GameMenuController;
import Controller.LoginMenuController;
import Controller.MainMenuController;
import Controller.ProfileMenuController;
import View.*;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginMenuView loginMenu = new LoginMenuView(scanner, new LoginMenuController());
        MainMenuView mainMenu = new MainMenuView(scanner, new MainMenuController());
        ProfileMenuView profileMenu = new ProfileMenuView(scanner, new ProfileMenuController());
        GameMenuView gameMenu = new GameMenuView(scanner, new GameMenuController());
        while (CurrentMenu.get() != CurrentMenu.EndGame) {
            loginMenu.run();
            mainMenu.run();
            profileMenu.run();
            gameMenu.run();
        }
        System.out.println(CurrentMenu.get());
        
    }

}
