package game.civilization.Controller.NetworkController.Client;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import game.civilization.Controller.ClientLobbyDatabase;
import game.civilization.Controller.LobbyDatabase;
import game.civilization.Controller.UserDatabase;
import game.civilization.Controller.ChatController.ClientChatController;
import game.civilization.FxmlController.ChatPageController;
import game.civilization.FxmlController.SceneController;
import game.civilization.Model.*;
import game.civilization.Model.Request;
import game.civilization.Model.Response;
import game.civilization.Model.Chat.ChatMessage;
import javafx.application.Platform;

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
            case "launch game" -> launchRealGame();
            case "are you alive?" -> stayinAlive();
            case "message" -> receiveMessage(response);
            case "remove message" -> removeMessage(response);
        }
    }

    private void removeMessage(Response response) {
        String senderUsername = (String) response.getData().get("senderUsername");
        String receiverUsername = (String) response.getData().get("receiverUsername");
        String text = (String) response.getData().get("text");
        new ClientChatController().removeMessageFromChat(text, senderUsername, receiverUsername);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ChatPageController.getChatPageController().showMessages();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void receiveMessage(Response response) {
        ChatMessage message = ChatMessage.fromJson((String) response.getData().get("message"));
        String senderUsername = (String) response.getData().get("fromUsername");
        new ClientChatController().addMessageToChat(message);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ChatPageController.getChatPageController().showMessages();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void launchRealGame() throws IOException, InterruptedException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Client.me.iJoinedLobby();
                    Client.me.startOnlineGame(SceneController.getInstance().getStage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateList(Response response) throws IOException {
        if (ClientLobbyDatabase.getInstance().getLobbyController() == null)
            return;
        ClientLobbyDatabase.getInstance().getLobbyController().setMyGames((ArrayList<Game>) response.getData().get("list2"));
        for (Game availableGame : ClientLobbyDatabase.getInstance().getLobbyController().getAvailableGames()) {
            for (Game game : ((ArrayList<Game>) response.getData().get("list2"))) {
                if (game.getId().equals(availableGame.getId())) {
                    ClientLobbyDatabase.getInstance().getLobbyController().getAvailableGames().set(ClientLobbyDatabase.getInstance().getLobbyController().getAvailableGames().indexOf(availableGame), game);
                }
            }
        }
    }

    public void stayinAlive() throws IOException {
        Request request = new Request();
        request.setAction("stayin alive");
        justSendRequest(request);
    }

    public void justSendRequest(Request request) throws IOException {
        String messageJson = new XStream().toXML(request);
       // messageJson = JSONWebToken.create(messageJson, UserDatabase.getCurrentUser().getUsername());
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
        request.addData("game", game);
        System.out.println(request.getData());
        justSendRequest(request);
    }

    public void launchGame(Game game) throws IOException {
        Request request = new Request();
        request.setAction("launch game");
        request.addData("game", game);
        justSendRequest(request);
    }

    public void addToGame(Game game) throws IOException {
        Request request = new Request();
        request.setAction("add to game");
        request.addData("game", game);
        request.addData("this", UserDatabase.getCurrentUser());
        justSendRequest(request);
    }

    public void leaveGame(Game game) throws IOException {
        Request request = new Request();
        request.setAction("leave game");
        request.addData("game", game);
        request.addData("this", UserDatabase.getCurrentUser());
        justSendRequest(request);
    }

    public Response searchForGame(String id) throws IOException {
        Request request = new Request();
        request.setAction("search game");
        request.addData("id", id);
        return sendRequestAndGetResponse(request);
    }

    public Response initInvitation() throws IOException {
        Request request = new Request();
        request.setAction("init invitation");
        request.addData("this", UserDatabase.getCurrentUser());
        return sendRequestAndGetResponse(request);
    }

    public void sendInvitation(Invitation invitation) throws IOException {
        Request request = new Request();
        request.setAction("send invitation");
        request.addData("invitation", invitation);
        justSendRequest(request);
    }

    public void acceptInvitation(Invitation invitation) throws IOException {
        Request request = new Request();
        request.setAction("accept invitation");
        request.addData("invitation", invitation);
        request.addData("this", UserDatabase.getCurrentUser());
        justSendRequest(request);
    }

    public void denyInvitation(Invitation invitation) throws IOException {
        Request request = new Request();
        request.setAction("deny invitation");
        request.addData("invitation", invitation);
        justSendRequest(request);
    }

    public Response findGameById(String id) throws IOException {
        Request request = new Request();
        request.setAction("find game");
        request.addData("id", id);
        return sendRequestAndGetResponse(request);
    }


}
