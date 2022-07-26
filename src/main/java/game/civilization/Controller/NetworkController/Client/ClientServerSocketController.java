package game.civilization.Controller.NetworkController.Client;

import com.thoughtworks.xstream.XStream;
import game.civilization.Controller.ClientLobbyDatabase;
import game.civilization.Controller.LobbyDatabase;
import game.civilization.Controller.UserDatabase;
import game.civilization.Model.Game;
import game.civilization.Model.Request;
import game.civilization.Model.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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

    private void handleReq(Response response) throws IOException, InterruptedException {
        switch (response.getAction()) {
            case "update list" -> updateList(response);

        }
    }

    private void updateList(Response response) {
        ClientLobbyDatabase.getInstance().getLobbyController().setMyGames((ArrayList<Game>) response.getData().get("list2"));
        for (Game availableGame : ClientLobbyDatabase.getInstance().getLobbyController().getAvailableGames()) {
            for (Game game : ((ArrayList<Game>) response.getData().get("list2"))) {
                if (game.getId().equals(availableGame.getId())) {
                    ClientLobbyDatabase.getInstance().getLobbyController().getAvailableGames().set(ClientLobbyDatabase.getInstance().getLobbyController().getAvailableGames().indexOf(availableGame), game);
                }
            }
        }
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
        System.out.println(Response.fromJson(messageJson).getAction() + " received");
        return Response.fromJson(messageJson);
    }

    private Response getMessage() throws IOException {
        int length = dataInputStream2.readInt();
        byte[] data = new byte[length];
        dataInputStream2.readFully(data);
        String messageJson = new String(data, StandardCharsets.UTF_8);
        return Response.fromJson(messageJson);
    }

    private ArrayList<Game> buildList() {
        if (LobbyDatabase.getInstance().getAllGames().size() <= 10) {
            return LobbyDatabase.getInstance().getAllGames();
        }
        ArrayList<Game> arrayList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(LobbyDatabase.getInstance().getAllGames().size());
            if (arrayList.contains(LobbyDatabase.getInstance().getAllGames().get(x))) {
                i--;
            } else {
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
        XStream xStream = new XStream();
        request.addData("game", game);
        System.out.println(request.getData());
        justSendRequest(request);
    }

    public Response addToGame(Game game) throws IOException {
        Request request = new Request();
        request.setAction("add to game");
        XStream xStream = new XStream();
        request.addData("game", game);
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("game", game);
//        request.setData(hashMap);
        return sendRequestAndGetResponse(request);
    }

    public Response leaveGame(Game game) throws IOException {
        Request request = new Request();
        request.setAction("leave game");
        XStream xStream = new XStream();
        request.addData("game", game);
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("game", game);
//        request.setData(hashMap);
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
