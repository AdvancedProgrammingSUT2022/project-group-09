package game.civilization.Controller.NetworkController.Server;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Application {
    public static String getXml() {
        return xml;
    }

    public static void setXml(String xml) {
        Server.xml = xml;
    }

    private static String xml;
    private ServerSocket serverSocket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public static void main(String[] args) throws IOException {
        launch();
    }

    private void connect() throws IOException {
        serverSocket = new ServerSocket(8000);
        while (true) {
            Socket socket = serverSocket.accept();
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println(socket + "is connected");
            ServerSocketHandler socketHandler = new ServerSocketHandler(socket);
            socketHandler.start();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        connect();
    }
}
