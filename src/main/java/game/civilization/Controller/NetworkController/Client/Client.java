package game.civilization.Controller.NetworkController.Client;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.SceneController;
import game.civilization.Model.Resources.Resource;
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
import java.util.concurrent.TimeUnit;

public class Client extends Application {
    private Socket socket; //send
    private Socket socket2;//receive
    private static ClientSocketController clientSocketController;

    public static void main(String[] args) throws IOException {
        launch();
    }

    public static ClientSocketController getClientSocketController() {
        return clientSocketController;
    }

    private void connect() throws IOException {
        socket = new Socket("localhost", 700);
        socket2 = new Socket("localhost", 700);
    }

    @Override
    public void start(Stage stage) throws Exception {
        UserDatabase.setCurrentUser(new User("a", "ll", "kk"));
//        GameDataBase.buildCustomisableMap();
//        SceneController.getInstance().setStage(stage);
//        SceneController.getInstance().buildMap();
        connect();
        Client.clientSocketController = new ClientSocketController(socket, socket2);
        while (!clientSocketController.isGameLoadedFOrFirstTime()) {
            System.out.println("game not loaded yet");
            TimeUnit.MILLISECONDS.sleep(400);
        }
        SceneController.getInstance().setStage(stage);
        stage.setTitle("CivilizationV ");//+UserDatabase.getCurrentUser().getUsername());
        SceneController.getInstance().game();
    }
}
