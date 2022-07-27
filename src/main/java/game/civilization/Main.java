package game.civilization;

import com.google.gson.Gson;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.GameControllerPackage.GameMenuController;
import game.civilization.Controller.LoginMenuController;
import game.civilization.Controller.MainMenuController;
import game.civilization.Controller.ProfileMenuController;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.GameScenes.GameSceneController;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.FxmlController.SceneController;
import game.civilization.Model.City;
import game.civilization.Model.JSONWebToken;
import game.civilization.Model.Improvements.Improvement;
import game.civilization.Model.Terrains.Terrain;
import game.civilization.Model.Terrains.TerrainType;
import game.civilization.Model.Units.Settler;
import game.civilization.View.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    public static void main(String[] args) {

    }

    @Override
    public void start(Stage stage) throws Exception {
        UserDatabase.loadUsers();
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
