package game.civilization.Controller.NetworkController.Client;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Model.NetworkModels.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClientSocketController {
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final Socket socket2;
    private final DataInputStream dataInputStream2;
    private final DataOutputStream dataOutputStream2;
    private boolean isListenCalledBefore = false;

    public ClientSocketController(Socket socket, Socket socket2) throws IOException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.socket2 = socket2;
        dataInputStream2 = new DataInputStream(socket2.getInputStream());
        dataOutputStream2 = new DataOutputStream(socket2.getOutputStream());
    }

    public void listenForGame() throws IOException {
        if (isListenCalledBefore)
            return;
        isListenCalledBefore = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        int length = dataInputStream2.readInt();
                        byte[] data = new byte[length];
                        dataInputStream2.readFully(data);
                        String messageJson = new String(data, StandardCharsets.UTF_8);
                        Message message = Message.fromJson(messageJson);
                        String xml =message.getXml();
                        XStream xStream = new XStream();
                        xStream.addPermission(AnyTypePermission.ANY);
                        if (xml.length() != 0) {
                            GameDataBaseSaving game = (GameDataBaseSaving) xStream.fromXML(xml);
                            game.setToGameDataBase();
                            System.out.println("game loaded from Server");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void setGame() throws IOException {
        XStream xStream = new XStream();
        String xml=xStream.toXML(GameDataBaseSaving.getInstance());

        Message message=new Message();
        message.setAction("set GameDatabase");

        String messageJson = message.toJson();
        byte[] data = messageJson.getBytes(StandardCharsets.UTF_8);
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
    }

//    private Message getMessage() throws IOException {
//        int length = dataInputStream.readInt();
//        byte[] data = new byte[length];
//        dataInputStream.readFully(data);
//        String messageJson = new String(data, StandardCharsets.UTF_8);
//        return Message.fromJson(messageJson);
//    }

//    private void sendMessage(Message message) throws IOException {
//        String temp = message.toJson();
//        byte[] data = temp.getBytes(StandardCharsets.UTF_8);
//        dataOutputStream.writeInt(data.length);
//        dataOutputStream.write(data);
//    }
}
