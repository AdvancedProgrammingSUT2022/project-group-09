package game.civilization.Controller.NetworkController.GameServer;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Proxy extends Application {
    private static ArrayList<ProxySocketController> clientSockets = new ArrayList<>();
    private static String xml;

    public static void main(String[] args) throws IOException {
        launch();
    }

    public static ArrayList<ProxySocketController> getClientSockets() {
        return clientSockets;
    }

    public static String getXml() {
        return xml;
    }

    public static void setXml(String xml) {
        Proxy.xml = xml;
    }

    private void connect() throws IOException {
        ServerSocket serverSocket = new ServerSocket(700);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(socket + " first is connected");
            Socket socket2 = serverSocket.accept();
            System.out.println(socket + " second is connected");
            ProxySocketController socketHandler = new ProxySocketController(socket, socket2);
            Proxy.getClientSockets().add(socketHandler);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        connect();
    }
}
