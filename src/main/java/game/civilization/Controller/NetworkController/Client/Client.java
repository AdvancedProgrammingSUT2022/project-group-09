package game.civilization.Controller.NetworkController.Client;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.SceneController;
import game.civilization.Model.Units.Settler;
import game.civilization.Model.User;
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
    private static ClientSocketController clientSocketController;

    public static void main(String[] args) throws IOException {
        launch();
    }

    public static ClientSocketController getClientSocketController() {
        return clientSocketController;
    }

    private void connect() throws IOException {
        socket = new Socket("localhost", 8000);
        socket2 = new Socket("localhost", 8000);
    }

    @Override
    public void start(Stage stage) throws Exception {
        UserDatabase.setCurrentUser(new User("payam", "ll", "kk"));
        connect();
        Client.clientSocketController = new ClientSocketController(socket, socket2);
        SceneController.getInstance().setStage(stage);
        while (!clientSocketController.isGameLoadedFOrFirstTime()) {
            System.out.println("game not loaded yet");
        }
        stage.setTitle("CivilizationV");
        SceneController.getInstance().game();
    }
}
