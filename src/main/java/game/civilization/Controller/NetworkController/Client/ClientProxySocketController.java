package game.civilization.Controller.NetworkController.Client;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
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

public class ClientProxySocketController {
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final Socket socket2;
    private final DataInputStream dataInputStream2;
    private final DataOutputStream dataOutputStream2;
    private boolean isListenCalledBefore = false;
    private boolean isGameLoadedFOrFirstTime = false;

    public ClientProxySocketController(Socket socket, Socket socket2) throws IOException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.socket2 = socket2;
        dataInputStream2 = new DataInputStream(socket2.getInputStream());
        dataOutputStream2 = new DataOutputStream(socket2.getOutputStream());
        Message message = new Message();
        message.setAction("introduction");
        message.setMessage(UserDatabase.getCurrentUser().getUsername());
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
        if (message.getAction().equals("GameDatabase"))
            readGame(message);
    }


    private Civilization findCiv() {
        for (Civilization civilization1 : GameDataBase.getCivilizations()) {
            if (civilization1.getName().equals(UserDatabase.getCurrentUser().getUsername()))
                return civilization1;
        }
        return null;
    }

    private void readGame(Message message) throws InterruptedException {
        String xml = message.getMessage();
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        if (xml.length() != 0) {
            GameDataBaseSaving game = (GameDataBaseSaving) xStream.fromXML(xml);
            game.setToGameDataBase();
            isGameLoadedFOrFirstTime = true;
            while (GameSceneDataBase.getInstance().getGameSceneController() == null) {
                System.out.println("waiting!");
                TimeUnit.MILLISECONDS.sleep(400);
            }
            if (GameSceneDataBase.getInstance().getGameSceneController() != null) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        GameSceneDataBase.getInstance().getGameSceneController().refresh();
                    }
                });
            }
            System.out.println("game loaded from Server");
        }
    }

    public void setGame() throws IOException {
        XStream xStream = new XStream();
        String xml = xStream.toXML(GameDataBaseSaving.getInstance());

        Message message = new Message();
        message.setAction("set GameDatabase");
        message.setMessage(xml);

        sendMessage(message);
    }

    public void sendMessage(Message message) throws IOException {
        String messageJson = message.toJson();
        byte[] data = messageJson.getBytes(StandardCharsets.UTF_8);
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
        dataOutputStream.flush();
        System.out.println("message  action " + message.getAction() + " send");
    }

//    public Message sendMessageAndGetMessage(Request request) throws IOException {
//        Message message = new Message();
//        message.setMessage(request.toJson());
//        message.setAction(request.getAction());
//        sendMessage(message);
//        int length = dataInputStream.readInt();
//        byte[] data = new byte[length];
//        dataInputStream.readFully(data);
//        String messageJson = new String(data, StandardCharsets.UTF_8);
//        System.out.println(Message.fromJson(messageJson).getAction() + " received");
//        return Message.fromJson(messageJson);
//    }

    public boolean isGameLoadedFOrFirstTime() {
        return isGameLoadedFOrFirstTime;
    }

    private Message getMessage() throws IOException {
        int length = dataInputStream2.readInt();
        byte[] data = new byte[length];
        dataInputStream2.readFully(data);
        String messageJson = new String(data, StandardCharsets.UTF_8);
        return Message.fromJson(messageJson);
    }


}
