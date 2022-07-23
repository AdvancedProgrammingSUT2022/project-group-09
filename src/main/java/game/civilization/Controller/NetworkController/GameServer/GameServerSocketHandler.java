package game.civilization.Controller.NetworkController.GameServer;

import game.civilization.Controller.LoginMenuController;
import game.civilization.Controller.ProfileMenuController;
import game.civilization.Controller.UserDatabase;
import game.civilization.Model.NetworkModels.Message;
import game.civilization.Model.Request;
import game.civilization.Model.TradingObject;
import game.civilization.Model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class GameServerSocketHandler {

    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final Socket socket2;
    private final DataInputStream dataInputStream2;
    private final DataOutputStream dataOutputStream2;
    private String name;

    public GameServerSocketHandler(Socket socket, Socket socket2) throws IOException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.socket2 = socket2;
        dataInputStream2 = new DataInputStream(socket2.getInputStream());
        dataOutputStream2 = new DataOutputStream(socket2.getOutputStream());
        sendGame();
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
        for (GameServerSocketHandler socketHandler : GameServer.getClientSockets()) {
            if (socketHandler == this)
                continue;
            System.out.println("game is sending for " + socketHandler.socket2);
            socketHandler.sendGame();
        }
    }

    private void sendGame() throws IOException {
        Message message = new Message();
        message.setAction("GameDatabase");
        message.setMessage(GameServer.getXml());
        sendMessage(message);
        System.out.println("game data base send");
    }

    private void handleReq(Message message) throws IOException {
        if (message.getAction().equals("set GameDatabase")) {
            System.out.println("GameDatabase get from " + socket);
            GameServer.setXml(message.getMessage());
            sendGameToAll();
            System.out.println("Xml Server updated");
        } else if (message.getAction().equals("introduction")) {
            System.out.println("introduction done client name is :" + message.getMessage());
            name = message.getMessage();
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

    public String getName() {
        return name;
    }

}
