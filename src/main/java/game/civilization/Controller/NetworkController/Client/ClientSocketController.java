package game.civilization.Controller.NetworkController.Client;

import game.civilization.Model.NetworkModels.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientSocketController {
    private Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public ClientSocketController(Socket socket) throws IOException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void getGame() throws IOException {
        Message message = new Message();
        message.setAction("get GameDatabase");
        Message answer=sendMessage(message);
    }

    public void setGame() {
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
