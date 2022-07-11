package game.civilization.Controller.NetworkController.Client;

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
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public static void main(String[] args) throws IOException {
        launch();
    }

    private void connect() throws IOException {
        socket = new Socket("localhost", 8000);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void start(Stage stage) throws Exception {
        connect();
        String xml = new String(Files.readAllBytes(Paths.get("data/gameInformation.xml")));
        System.out.println(xml);

        byte[] data = xml.getBytes(StandardCharsets.UTF_8);
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
    }
}
