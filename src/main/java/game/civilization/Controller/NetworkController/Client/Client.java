package game.civilization.Controller.NetworkController.Client;

import com.thoughtworks.xstream.XStream;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.NetworkController.GameServer.Proxy;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.SceneController;
import game.civilization.Model.Improvements.Improvement;
import game.civilization.Model.NetworkModels.Message;
import game.civilization.Model.Resources.Resource;
import game.civilization.Model.Terrains.Terrain;
import game.civilization.Model.Units.Settler;
import game.civilization.Model.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Client extends Application {
    private Socket socket; //send
    private Socket socket2;//receive
    private static ClientProxySocketController clientProxySocketController;
    private static ClientServerSocketController clientServerSocketController;

    public static void main(String[] args) throws IOException {
        launch();
    }

    public static Client me;

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
        UserDatabase.setCurrentUser(new User("a", "a", "a"));
        connect();
        SceneController.getInstance().setStage(stage);
        stage.setTitle("CivilizationV " + UserDatabase.getCurrentUser().getUsername());
        SceneController.getInstance().Lobby();

//        Client.me = this;
//        UserDatabase.loadUsers();
//        UserDatabase.setCurrentUser(new User("c", "a", "a"));
//        iJoinedLobby(stage);
//        Scanner scanner = new Scanner(System.in);
//        scanner.next();
//        startOnlineGame(stage);
    }

    private void iJoinedLobby(Stage stage) throws IOException, InterruptedException {
        connectForGame();
        Client.clientProxySocketController = new ClientProxySocketController(socket, socket2);
    }

    private void startOnlineGame(Stage stage) throws IOException, InterruptedException {
        Message message = new Message();
        message.setAction("play game");
        Client.clientProxySocketController.sendMessage(message);
        while (!clientProxySocketController.isGameLoadedFOrFirstTime()) {
            System.out.println("game not loaded yet");
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        debugImprovement();
        SceneController.getInstance().setStage(stage);
        stage.setTitle("CivilizationV " + UserDatabase.getCurrentUser().getUsername());
        SceneController.getInstance().game();
    }


    private void startOfflineGame(Stage stage, ArrayList<User> users) throws IOException {
        debugImprovement();
        GameDataBase.runGameForFirstTime(users);
        SceneController.getInstance().setStage(stage);
        stage.setTitle("CivilizationV ");
        SceneController.getInstance().game();
    }


    private void startWatchingStream(Stage stage) throws IOException, InterruptedException {
        connectForGame();
        Client.clientProxySocketController = new ClientProxySocketController(socket, socket2);
        while (!clientProxySocketController.isGameLoadedFOrFirstTime()) {
            System.out.println("game not loaded yet");
            TimeUnit.MILLISECONDS.sleep(400);
        }
        debugImprovement();
        SceneController.getInstance().setStage(stage);
        stage.setTitle("CivilizationV " + UserDatabase.getCurrentUser().getUsername());
        SceneController.getInstance().stream();
    }

    private void playSavedOfflineGame(Stage stage) throws IOException {
        debugImprovement();
        GameDataBaseSaving.loadGame();
        SceneController.getInstance().setStage(stage);
        stage.setTitle("CivilizationV ");
        SceneController.getInstance().game();
    }

    private void startSavedMapOfflineGame(Stage stage, ArrayList<User> users) throws IOException {
        debugImprovement();
        GameDataBaseSaving.loadMap();
        GameDataBase.runGameForFirstTimeWithSavedMap(users);
        SceneController.getInstance().setStage(stage);
        stage.setTitle("CivilizationV ");
        SceneController.getInstance().game();
    }

    private void startBuildMap(Stage stage) throws IOException {
        debugImprovement();
        GameDataBase.buildCustomisableMap();
        SceneController.getInstance().setStage(stage);
        stage.setTitle("CivilizationV ");
        SceneController.getInstance().buildMap();
    }


    private void debugImprovement() {
        for (Improvement improvement : Improvement.getAllImprovements()) {
            improvement.getCanBeBuiltON();
            improvement.getRequiredTechnology();
        }
    }
}
