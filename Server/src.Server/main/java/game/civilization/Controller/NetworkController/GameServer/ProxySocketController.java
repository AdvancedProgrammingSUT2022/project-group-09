package game.civilization.Controller.NetworkController.GameServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import com.thoughtworks.xstream.XStream;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.UserDatabase;
import game.civilization.Model.Request;
import game.civilization.Model.User;
import game.civilization.Model.NetworkModels.Message;

public class ProxySocketController {

    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final Socket socket2;
    private final DataInputStream dataInputStream2;
    private final DataOutputStream dataOutputStream2;
    private String name;

    public ProxySocketController(Socket socket, Socket socket2) throws IOException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.socket2 = socket2;
        dataInputStream2 = new DataInputStream(socket2.getInputStream());
        dataOutputStream2 = new DataOutputStream(socket2.getOutputStream());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                listenForClient();
            }
        });
        thread.start();
    }

    public void listenForClient() {
        try {
            while (true) {
                Message message = getMessage();
                System.out.println("message " + message.getAction() + " action " + " received");
                if (message.getAction().equals("exit")) {
                    System.out.println("socket " + socket + "disconnected");
                    return;
                }
                handleReq(message);
            }
        } catch (Exception ignored) {

        }
    }

    private void sendGameToAll() throws IOException {
        for (ProxySocketController socketHandler : Proxy.getClientSockets()) {
//            if (socketHandler == this)
//                continue;
            System.out.println("game is sending for " + socketHandler.socket2);
            socketHandler.sendGame();
        }
    }
    private void sendGameToAllButMe() throws IOException {
        for (ProxySocketController socketHandler : Proxy.getClientSockets()) {
            if (socketHandler == this)
                continue;
            System.out.println("game is sending for " + socketHandler.socket2);
            socketHandler.sendGame();
        }
    }

    public void sendGame() throws IOException {
        Message message = new Message();
        message.setAction("GameDatabase");
        message.setMessage(Proxy.getXml());
        sendMessage(message);
        System.out.println("game data base send");
    }

    private void handleReq(Message message) throws IOException {
        switch (message.getAction()) {
            case "set GameDatabase" -> {
                System.out.println("GameDatabase get from " + socket);
                Proxy.setXml(message.getMessage());
                sendGameToAllButMe();
                System.out.println("Xml Server updated");
            }
            case "introduction" -> {
                System.out.println("introduction done client name is :" + User.fromJson(message.getMessage()).getUsername());
                name = User.fromJson(message.getMessage()).getUsername();
                UserDatabase.getUsers().add(User.fromJson(message.getMessage()));
            }
            case "play game" -> {
                if (!Objects.equals(Proxy.getXml(), null))
                    return;
                System.out.println("game created");
                GameDataBase.runGameForFirstTime(UserDatabase.getUsers());
                GameDataBase.setOnline(true);
                GameDataBaseSaving.saveGame();
                XStream xStream = new XStream();
                Proxy.setXml(xStream.toXML(GameDataBaseSaving.getInstance()));
                sendGameToAll();
            }
        }
    }

    private Message getMessage() throws IOException {
        int length = dataInputStream.readInt();
        byte[] data = new byte[length];
        dataInputStream.readFully(data);
        String messageJson = new String(data, StandardCharsets.UTF_8);
        return Message.fromJson(messageJson);
    }

    private void sendMessage(Message message) throws IOException {
        String temp = message.toJson();
        byte[] data = temp.getBytes(StandardCharsets.UTF_8);
        dataOutputStream2.writeInt(data.length);
        dataOutputStream2.write(data);
        dataOutputStream2.flush();
    }

    private void sendMessageOnFirstData(Message message) throws IOException {
        String temp = message.toJson();
        byte[] data = temp.getBytes(StandardCharsets.UTF_8);
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
        dataOutputStream.flush();
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

    public String getName() {
        return name;
    }

}
