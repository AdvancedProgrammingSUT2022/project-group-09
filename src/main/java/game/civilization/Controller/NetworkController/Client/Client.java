package game.civilization.Controller.NetworkController.Client;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.SceneController;
import game.civilization.Model.Units.Settler;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client extends Application {
    private Socket socket;
    private Socket socket2;


    public static void main(String[] args) throws IOException {
        launch();
    }

    private void connect() throws IOException {
        socket = new Socket("localhost", 8000);
        socket2 = new Socket("localhost", 8000);
    }

    @Override
    public void start(Stage stage) throws Exception {
        connect();
        String xml = new String(Files.readAllBytes(Paths.get("data/gameInformation.xml")));
        System.out.println(xml);

        SceneController.getInstance().setStage(stage);
        UserDatabase.loadUsers();
        //   GameDataBaseSaving.loadGame();
        GameDataBase.runGameForFirstTime(UserDatabase.getUsers());
        ((Settler) (GameDataBase.getCurrentCivilization().getUnits().get(0))).foundCity();
        stage.setTitle("CivilizationV");
        SceneController.getInstance().game();
    }
}
