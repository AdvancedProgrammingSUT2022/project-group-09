package game.civilization.Controller.NetworkController.Client;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.ProfileMenuController;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.Model.Civilization;
import game.civilization.Model.JSONWebToken;
import game.civilization.Model.NetworkModels.Message;
import game.civilization.Model.Request;
import game.civilization.Model.Response;
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
                        Response message = getMessage();
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

    private void handleReq(Response message) throws IOException, InterruptedException {
        switch (message.getAction()) {
            case "are you alive?" -> stayinAlive();
        }
    }

    public void stayinAlive() throws IOException {
        Request request = new Request();
        request.setAction("stayin alive");
        justSendRequest(request);
    }

    public void justSendRequest(Request request) throws IOException {
        String messageJson = new XStream().toXML(request);
        byte[] data = messageJson.getBytes(StandardCharsets.UTF_8);
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
        dataOutputStream.flush();
        System.out.println("message  action " + request.getAction() + " send");
    }

    public Response sendRequestAndGetResponse(Request request) throws IOException {
        justSendRequest(request);
        int length = dataInputStream.readInt();
        byte[] data = new byte[length];
        dataInputStream.readFully(data);
        String messageJson = new String(data, StandardCharsets.UTF_8);
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        System.out.println(((Response) xStream.fromXML(messageJson)).getAction() + " received");
        return (Response) xStream.fromXML(messageJson);
    }
    private Response getMessage() throws IOException {
        int length = dataInputStream2.readInt();
        byte[] data = new byte[length];
        dataInputStream2.readFully(data);
        String messageJson = new String(data, StandardCharsets.UTF_8);
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        return (Response) xStream.fromXML(messageJson);
    }

}
