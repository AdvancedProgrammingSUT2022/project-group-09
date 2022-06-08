package game.civilization;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameMenuController;
import game.civilization.Controller.LoginMenuController;
import game.civilization.Controller.MainMenuController;
import game.civilization.Controller.ProfileMenuController;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.SceneController;
import game.civilization.Model.Units.Settler;
import game.civilization.View.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //ghabl az start userdatabase.json ro por konid ba 2 user masalan
        //[{"username":"payam","password":"pass","nickname":"payam","score":0,"lastLoginTime":"2022/06/09 01:04:18","rank":0,"inputStream":false},{"username":"aylin","password":"pass","nickname":"payam","score":0,"rank":0,"inputStream":false}]
        SceneController.getInstance().setStage(stage);
        UserDatabase.loadUsers();
        GameDataBase.runGameForFirstTime(UserDatabase.getUsers());
        ((Settler) (GameDataBase.getCurrentCivilization().getUnits().get(0))).foundCity();
        stage.setTitle("CivilizationV");
        SceneController.getInstance().game();


//        handleCloseRequest(stage);
//        SceneController.getInstance().setStage(stage);
//        UserDatabase.loadUsers();
//        UserDatabase.setCurrentUser(UserDatabase.getUsers().get(0));
//        stage.setTitle("CivilizationV");
//        // SceneController.getInstance().scoreBoard(stage);
//        SceneController.getInstance().MainMenu();
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
                UserDatabase.updateData();
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
