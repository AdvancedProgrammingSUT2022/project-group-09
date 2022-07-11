package game.civilization.Controller.NetworkController.Server;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Application {
    public static String getXml() {
        return xml;
    }

    public static void setXml(String xml) {
        Server.xml = xml;
    }

    private static ArrayList<ServerSocketHandler> clientSockets = new ArrayList<>();
    private static String xml;
    private ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        launch();
    }

    public static ArrayList<ServerSocketHandler> getClientSockets() {
        return clientSockets;
    }


    private void connect() throws IOException {
        serverSocket = new ServerSocket(8000);
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
        connect();
    }
}
