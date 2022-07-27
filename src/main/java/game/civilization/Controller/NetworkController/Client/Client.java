package game.civilization.Controller.NetworkController.Client;

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
    private static ClientProxySocketController clientProxySocketController;
    private static ClientServerSocketController clientServerSocketController;

    public static void main(String[] args) throws IOException {
        launch();
    }

    public static ClientProxySocketController getClientProxySocketController() {
        return clientProxySocketController;
    }

    public static ClientServerSocketController getClientServerSocketController() {
        return clientServerSocketController;
    }

    private void connectForGame() throws IOException {
        socket = new Socket("localhost", 700);
        socket2 = new Socket("localhost", 700);
    }

    private void connect() throws IOException {
        socket = new Socket("localhost", 8000);
        socket2 = new Socket("localhost", 8000);
    }

    @Override
    public void start(Stage stage) throws Exception {
        connect();
        Client.clientServerSocketController = new ClientServerSocketController(socket, socket2);
        SceneController.getInstance().setStage(stage);
        stage.setTitle("CivilizationV");
        UserDatabase.loadUsers();
        UserDatabase.setCurrentUser(new User("pouya", "1234@asDF", "pouyaL"));
        SceneController.getInstance().LoginMenu();

        //******* BE IN PAEINI HA DAST NAZANID *******
//        debugImprovement();
//        UserDatabase.setCurrentUser(new User("a", "ll", "kk"));
////        GameDataBase.buildCustomisableMap();
////        SceneController.getInstance().setStage(stage);
////        SceneController.getInstance().buildMap();
//        connect();
//        Client.clientSocketController = new ClientSocketController(socket, socket2);
//        while (!clientSocketController.isGameLoadedFOrFirstTime()) {
//            System.out.println("game not loaded yet");
//            TimeUnit.MILLISECONDS.sleep(400);
//        }
//        System.out.println(GameDataBase.getMainMap().getTerrain(0,0).getImprovementPair().getKey());
//        SceneController.getInstance().setStage(stage);
//        stage.setTitle("CivilizationV ");//+UserDatabase.getCurrentUser().getUsername());
//        SceneController.getInstance().game();
    }

//    private void debugImprovement() {
//        for (Improvement improvement : Improvement.getAllImprovements()) {
//            improvement.getCanBeBuiltON();
//            improvement.getRequiredTechnology();
//        }
//    }
}
