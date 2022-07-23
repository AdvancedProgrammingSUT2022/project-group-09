package game.civilization.Controller.NetworkController.Server;

import com.thoughtworks.xstream.XStream;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.NetworkController.GameServer.Proxy;
import game.civilization.Controller.NetworkController.GameServer.ProxySocketController;
import game.civilization.Controller.UserDatabase;
import game.civilization.Model.Improvements.Improvement;
import game.civilization.Model.Units.Settler;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Application {

    private static final ArrayList<ServerSocketController> clientSockets = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        launch();
    }


    private void connect() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(socket + " first is connected");
            Socket socket2 = serverSocket.accept();
            System.out.println(socket + " second is connected");
            ServerSocketController socketHandler = new ServerSocketController(socket, socket2);
            Server.getClientSockets().add(socketHandler);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        UserDatabase.loadUsers();
        connect();
    }

    public static ArrayList<ServerSocketController> getClientSockets() {
        return clientSockets;
    }
}
