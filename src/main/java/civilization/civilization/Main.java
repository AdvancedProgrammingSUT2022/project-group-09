package civilization.civilization;

import civilization.civilization.main.java.com.civilization.Controller.GameControllerPackage.GameMenuController;
import civilization.civilization.main.java.com.civilization.Controller.LoginMenuController;
import civilization.civilization.main.java.com.civilization.Controller.MainMenuController;
import civilization.civilization.main.java.com.civilization.Controller.ProfileMenuController;
import civilization.civilization.main.java.com.civilization.Controller.UserDatabase;
import civilization.civilization.main.java.com.civilization.View.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDatabase.loadUsers();
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
        UserDatabase.saveUsers();
    }
}
