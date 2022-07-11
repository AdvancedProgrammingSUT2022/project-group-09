package game.civilization.Controller.NetworkController.Server;

import game.civilization.Controller.NetworkController.Server.Server;
import game.civilization.Model.NetworkModels.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerSocketHandler {


    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final Socket socket2;
    private final DataInputStream dataInputStream2;
    private final DataOutputStream dataOutputStream2;

    public ServerSocketHandler(Socket socket, Socket socket2) throws IOException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.socket2 = socket2;
        dataInputStream2 = new DataInputStream(socket2.getInputStream());
        dataOutputStream2 = new DataOutputStream(socket2.getOutputStream());
    }

    public void listenForClient() {
        try {
            while (true) {
                Message message = getMessage();
                if (message.getAction().equals("exit")) {
                    System.out.println("socket " + socket + "disconnected");
                    return;
                }
                System.out.println("GameDatabase get from " + socket);
                Server.setXml(message.getXml());
                System.out.println("Xml Server updated");
            }
        } catch (Exception ignored) {

        }
    }

    private void sendGameToAll() throws IOException {
        for (ServerSocketHandler socketHandler : Server.getClientSockets()) {
            System.out.println("game is sending for " + socketHandler);
            socketHandler.sendGame();
        }
    }

    private void sendGame() throws IOException {
        Message message = new Message();
        message.setXml(Server.getXml());
        sendMessage(message);
    }
//    private Message handleReq(Message request) {
//        Message message = new Message();
//        switch (request.getAction()) {
//            case "get GameDatabase" -> message.setXml(Server.getXml());
//            case "set GameDatabase" -> Server.setXml(message.getXml());
//        }
//        return message;
//    }

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
    }
}
