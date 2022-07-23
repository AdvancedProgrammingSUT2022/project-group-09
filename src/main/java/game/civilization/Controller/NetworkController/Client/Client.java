package game.civilization.Controller.NetworkController.Client;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.SceneController;
import game.civilization.Model.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

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
        GameDataBase.buildCustomisableMap();
        SceneController.getInstance().setStage(stage);
        SceneController.getInstance().MainMenu();
//        connect();
//        Client.clientSocketController = new ClientSocketController(socket, socket2);
//        while (!clientSocketController.isGameLoadedFOrFirstTime()) {
//            System.out.println("game not loaded yet");
//            TimeUnit.MILLISECONDS.sleep(400);
//        }
//        SceneController.getInstance().setStage(stage);
//        stage.setTitle("CivilizationV ");//+UserDatabase.getCurrentUser().getUsername());
//        SceneController.getInstance().game();
    }
}
