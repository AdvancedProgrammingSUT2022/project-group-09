package game.civilization;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameMenuController;
import game.civilization.Controller.LoginMenuController;
import game.civilization.Controller.MainMenuController;
import game.civilization.Controller.ProfileMenuController;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.SceneController;
import game.civilization.View.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        UserDatabase.loadUsers();
        GameDataBase.runGameForFirstTime(UserDatabase.getUsers());
        stage.setTitle("CivilizationV");
        //   preStartRequired();
        SceneController.getInstance().game(stage);
    }

    public void preStartRequired() {
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
