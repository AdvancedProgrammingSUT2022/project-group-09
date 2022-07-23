package game.civilization.Controller.NetworkController.Client;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.ProfileMenuController;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.Model.Civilization;
import game.civilization.Model.NetworkModels.Message;
import game.civilization.Model.Request;
import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class ClientServerSocketController {
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final Socket socket2;
    private final DataInputStream dataInputStream2;
    private final DataOutputStream dataOutputStream2;
    private boolean isListenCalledBefore;

    public ClientServerSocketController(Socket socket, Socket socket2) throws IOException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.socket2 = socket2;
        dataInputStream2 = new DataInputStream(socket2.getInputStream());
        dataOutputStream2 = new DataOutputStream(socket2.getOutputStream());
        Message message = new Message();
        message.setAction("introduction");
        message.setMessage("");
        sendMessage(message);
        listen();
    }

    private void listen() throws IOException {
        if (isListenCalledBefore)
            return;
        //ino zadam ke yebar faghat az in tabe estefade beshe
        isListenCalledBefore = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Message message = getMessage();
                        System.out.println(message.getAction() + " received");
                        handleReq(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void handleReq(Message message) throws IOException, InterruptedException {

    }

    public void sendMessage(Message message) throws IOException {
        String messageJson = message.toJson();
        byte[] data = messageJson.getBytes(StandardCharsets.UTF_8);
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
        dataOutputStream.flush();
        System.out.println("message  action " + message.getAction() + " send");
    }

    public Message sendMessageAndGetMessage(Request request) throws IOException {
        Message message = new Message();
        message.setMessage(request.toJson());
        message.setAction(request.getAction());
        sendMessage(message);
        int length = dataInputStream.readInt();
        byte[] data = new byte[length];
        dataInputStream.readFully(data);
        String messageJson = new String(data, StandardCharsets.UTF_8);
        System.out.println(Message.fromJson(messageJson).getAction() + " received");
        return Message.fromJson(messageJson);
    }
    private Message getMessage() throws IOException {
        int length = dataInputStream2.readInt();
        byte[] data = new byte[length];
        dataInputStream2.readFully(data);
        String messageJson = new String(data, StandardCharsets.UTF_8);
        return Message.fromJson(messageJson);
    }

}
