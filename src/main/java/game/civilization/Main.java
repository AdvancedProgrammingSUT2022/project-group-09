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
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        handleCloseRequest(stage);
        SceneController.getInstance().setStage(stage);
        UserDatabase.loadUsers();
        UserDatabase.setCurrentUser(UserDatabase.getUsers().get(0));
        stage.setTitle("CivilizationV");
        // SceneController.getInstance().scoreBoard(stage);
        SceneController.getInstance().profileMenu();
        // preStartRequired(stage);
        // stage.setTitle("CivilizationV");
        // stage.sizeToScene();
        // stage.setMinWidth(1144);
        // stage.setMinHeight(670);
        // stage.setMaxWidth(1144);
        // stage.setMaxHeight(670);
        // UserDatabase.setCurrentUser(new User("hi", "hi", "hi"));
        // SceneController.getInstance().chatMenu(stage);
    }

    private void handleCloseRequest(Stage stage) {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                if (UserDatabase.getCurrentUser() != null) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    UserDatabase.getCurrentUser().setLastLoginTime(dtf.format(now));
                }
                UserDatabase.saveUsers();
            }
            
        });
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
