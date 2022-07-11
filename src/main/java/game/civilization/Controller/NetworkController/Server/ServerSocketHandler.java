package game.civilization.Controller.NetworkController.Server;

import game.civilization.Controller.NetworkController.Server.Server;
import game.civilization.Model.NetworkModels.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerSocketHandler extends Thread {


    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public ServerSocketHandler(Socket socket) throws IOException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = getMessage();
                if (message.getAction().equals("exit")) {
                    dataOutputStream.writeUTF(new Message().toJson());
                    dataOutputStream.flush();
                    System.out.println("socket " + socket + "disconnected");
                    return;
                }
                System.out.println("data get from " + socket + " data is :" + message.getAction());
                Message message2 = handleReq(message);
                sendMessage(message2);
            }
        } catch (Exception ignored) {

        }
    }

    private Message handleReq(Message request) {
        Message message = new Message();
        switch (request.getAction()) {
            case "get GameDatabase" -> message.setXml(Server.getXml());
            case "set GameDatabase" -> Server.setXml(message.getXml());
        }
        return message;
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
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
    }
}
