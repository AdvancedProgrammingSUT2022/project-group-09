package game.civilization.Controller.NetworkController.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import game.civilization.Controller.UserDatabase;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Model.Units.Settler;
import javafx.application.Application;
import javafx.stage.Stage;

public class Server extends Application{
    public static void main(String[] args) {
        launch();
    }

    private static ArrayList<ServerSocketHandler> clientSockets = new ArrayList<>();

    private ServerSocket serverSocket;

    public static ArrayList<ServerSocketHandler> getClientSockets() {
        return clientSockets;
    }

    private void connect() throws IOException {
        serverSocket = new ServerSocket(800);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(socket + " first is connected");
            Socket socket2 = serverSocket.accept();
            System.out.println(socket + " second is connected");
            ServerSocketHandler socketHandler = new ServerSocketHandler(socket, socket2);
            Server.getClientSockets().add(socketHandler);
        }
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        UserDatabase.loadUsers();
        connect();
    }
}
