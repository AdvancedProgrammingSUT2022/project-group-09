package game.civilization;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameMenuController;
import game.civilization.Controller.LoginMenuController;
import game.civilization.Controller.MainMenuController;
import game.civilization.Controller.ProfileMenuController;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.SceneController;
import game.civilization.Model.User;
import game.civilization.View.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        preStartRequired(stage);
        stage.setTitle("CivilizationV");
        stage.sizeToScene();
        stage.setMinWidth(1144);
        stage.setMinHeight(670);
        stage.setMaxWidth(1144);
        stage.setMaxHeight(670);
        UserDatabase.setCurrentUser(new User("hi", "hi", "hi"));
        SceneController.getInstance().chatMenu(stage);
    }

    public void preStartRequired(Stage stage) throws IOException {
        UserDatabase.loadUsers();
        Scanner scanner = new Scanner(System.in);
        LoginMenuView loginMenu = new LoginMenuView(scanner, new LoginMenuController());
        MainMenuView mainMenu = new MainMenuView(scanner, new MainMenuController());
        ProfileMenuView profileMenu = new ProfileMenuView(scanner, new ProfileMenuController());
        GameMenuView gameMenu = new GameMenuView(scanner, new GameMenuController());
//        while (CurrentMenu.get() != CurrentMenu.EndGame) {
//            if (CurrentMenu.get().equals(CurrentMenu.MainMenu)){
//                SceneController.getInstance().MainMenu(stage);
//                System.out.println(2);
//                CurrentMenu.set(CurrentMenu.Nothing);
//            }
//            if (CurrentMenu.get().equals(CurrentMenu.LoginMenu)){
//                SceneController.getInstance().LoginMenu(stage);
//                System.out.println(1);
//                CurrentMenu.set(CurrentMenu.Nothing);
//            }
////            loginMenu.run();
////            mainMenu.run();
////            profileMenu.run();
////            gameMenu.run();
//        }
        UserDatabase.saveUsers();
    }
}
