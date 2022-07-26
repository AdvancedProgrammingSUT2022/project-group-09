package game.civilization.Controller.NetworkController.Client;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.LobbyDatabase;
import game.civilization.Controller.ProfileMenuController;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.Model.Civilization;
import game.civilization.Model.Game;
import game.civilization.Model.NetworkModels.Message;
import game.civilization.Model.Request;
import game.civilization.Model.Response;
import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
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
        //TODO update lobby menu
        }

    public void justSendRequest(Request request) throws IOException {
        String messageJson = request.toJson();
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
        System.out.println(Response.fromJson(messageJson).getAction()+ " received");
        return Response.fromJson(messageJson);
    }
    private Message getMessage() throws IOException {
        int length = dataInputStream2.readInt();
        byte[] data = new byte[length];
        dataInputStream2.readFully(data);
        String messageJson = new String(data, StandardCharsets.UTF_8);
        return Message.fromJson(messageJson);
    }

    private ArrayList<Game> buildList(){
        if (LobbyDatabase.getInstance().getAllGames().size() <= 10){
            return LobbyDatabase.getInstance().getAllGames();
        }
        ArrayList<Game> arrayList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            int x = random.nextInt(LobbyDatabase.getInstance().getAllGames().size());
            if (arrayList.contains(LobbyDatabase.getInstance().getAllGames().get(x))){
                i--;
            }
            else {
                arrayList.add(LobbyDatabase.getInstance().getAllGames().get(x));
            }
        }
        return arrayList;
    }

    public Response initializeTabel() throws IOException {
        Request request = new Request();
        request.setAction("init");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("this", UserDatabase.getCurrentUser());
        request.setData(hashMap);
        return sendRequestAndGetResponse(request);
    }

    public void addGame(Game game) throws IOException {
        Request request = new Request();
        request.setAction("add game");
        XStream xStream=new XStream();
        String res=xStream.toXML(game);
        request.addData("game",res);
        System.out.println(request.getData());
        justSendRequest(request);
    }

    public Response addToGame(Game game) throws IOException {
        Request request = new Request();
        request.setAction("add to game");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("game", game);
        request.setData(hashMap);
        return sendRequestAndGetResponse(request);
    }

    public Response leaveGame(Game game) throws IOException {
        Request request = new Request();
        request.setAction("leave game");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("game", game);
        request.setData(hashMap);
        return sendRequestAndGetResponse(request);
    }

    public Response searchForGame(String id) throws IOException {
        Request request = new Request();
        request.setAction("search game");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", id);
        request.setData(hashMap);
        return sendRequestAndGetResponse(request);
    }

    public Response changeVisibility(Game game) throws IOException {
        Request request = new Request();
        request.setAction("chhange visibility");
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("game", game);
//        request.setData(hashMap);
        return sendRequestAndGetResponse(request);
    }


}
