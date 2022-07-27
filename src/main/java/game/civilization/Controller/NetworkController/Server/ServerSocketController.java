package game.civilization.Controller.NetworkController.Server;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import game.civilization.Controller.*;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.NetworkController.GameServer.Proxy;
import game.civilization.Controller.NetworkController.GameServer.ProxySocketController;
import game.civilization.Controller.ChatController.ServerChatController;
import game.civilization.FxmlController.ScoreBoardViewController;
import game.civilization.Model.*;
import game.civilization.Model.NetworkModels.Message;
import game.civilization.Model.Chat.ChatMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ServerSocketController {
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final Socket socket2;
    private final DataInputStream dataInputStream2;
    private final DataOutputStream dataOutputStream2;
    private String name;
    private boolean isAlive = true;

    public String getName() {
        return this.name;
    }

    public ServerSocketController(Socket socket, Socket socket2) throws IOException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.socket2 = socket2;
        dataInputStream2 = new DataInputStream(socket2.getInputStream());
        dataOutputStream2 = new DataOutputStream(socket2.getOutputStream());
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
                Request request = getMessage();
                System.out.println("message " + request.getAction() + " action " + " received");
                if (request.getAction().equals("exit")) {
                    System.out.println("socket " + socket + "disconnected");
                    return;
                }
                handleReq(request);
            }
        } catch (Exception ignored) {

        }
    }

    private void handleReq(Request request) throws IOException, InterruptedException {
        switch (request.getAction()) {
            case "register" -> register(request);
            case "login" -> login(request);
            case "changeNickname" -> changeNickname(request);
            case "changePassword" -> changePassword(request);
            case "changePicture" -> changePicture(request);

            case "init" -> initTables(request);
            case "add game" -> addGame(request);
            case "add to game" -> addToGame(request);
            case "leave game" -> leaveGame(request);
            case "search game" -> searchForGame(request);
            case "launch game" -> launchGame(request);
            case "isOnline" -> isOnline(request);
            case "stayin alive" -> isAlive = true;
            case "get online users" -> getOnlineUsers();
            case "get_messages" -> getMessages(request);
            case "send_message" -> sendMessage(request);
            case "init invitation" -> initInvitation(request);
            case "send invitation" -> sendInvitation(request);
            case "accept invitation" -> acceptInvitation(request);
            case "deny invitation" -> denyInvitation(request);
        }
    }

    private void initInvitation(Request request) throws IOException {
        User user = (User) request.getData().get("this");
        ArrayList<Invitation> invitations = InvitationDatabase.getInstance().getInvitationByReceiver(user.getUsername());
        Response response = new Response();
        response.addData("invitations", invitations);
        sendResponse(response);
    }
    private void sendInvitation(Request request) {
        Invitation invitation = (Invitation) request.getData().get("invitation");
        InvitationDatabase.getInstance().addInvitation(invitation);
    }
    private void acceptInvitation(Request request) throws IOException {
        Invitation invitation = (Invitation) request.getData().get("invitation");
        InvitationDatabase.getInstance().removeInvitation(invitation);
        Request requestt = new Request();
        requestt.setAction("add to game");
        Game game = LobbyDatabase.getInstance().findGameById(invitation.getGameId());
        game.addPlayer((User) request.getData().get("this"));
        requestt.addData("game", game);
        requestt.addData("this", request.getData().get("this"));
        addToGame(requestt);
    }
    private void denyInvitation(Request request) {
        Invitation invitation = (Invitation) request.getData().get("invitation");
        InvitationDatabase.getInstance().removeInvitation(invitation);
    }

    private void launchGame(Request request) throws IOException, InterruptedException {
        Game game = (Game) request.getData().get("game");
        if (game.getPlayers().size() <= 1)
            return;
        Response message = new Response();
        message.setAction("launch game");
        for (ServerSocketController clientSocket : Server.getClientSockets()) {
            for (User player : game.getPlayers()) {
                if (player.getUsername().equals(clientSocket.getName())) {
                    clientSocket.sendResponseDirectly(message);
                    TimeUnit.MILLISECONDS.sleep(300);
                }
            }
        }
    }

//    public Response initializeTabel() throws IOException {
//        Request request = new Request();
//        request.setAction("init");
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("games", buildList());
//        request.setData(hashMap);
//        return sendRequestAndGetResponse(request);
//    }


    public void addToGame(Request request) throws IOException {
        Game game = (Game) request.getData().get("game");
        for (Game allGame : LobbyDatabase.getInstance().getAllGames()) {
            if (allGame.getId().equals(game.getId())) {
                int x = LobbyDatabase.getInstance().getAllGames().indexOf(allGame);
                LobbyDatabase.getInstance().getAllGames().set(LobbyDatabase.getInstance().getAllGames().indexOf(allGame), game);
            }
        }
        sendGameToAll(request);
    }

    public void leaveGame(Request request) throws IOException {
        Game game = (Game) request.getData().get("game");
        if (((User) request.getData().get("this")).getUsername().equals(game.getAdmin().getUsername())) {
            if (game.getPlayers().size() > 0) {
                game.setAdmin(game.getPlayers().get(0));
            }
            else {
                LobbyDatabase.getInstance().getAllGames().remove(findGame(game));
            }
        }
        for (Game allGame : LobbyDatabase.getInstance().getAllGames()) {
            if (allGame.getId().equals(game.getId())) {
                LobbyDatabase.getInstance().getAllGames().set(LobbyDatabase.getInstance().getAllGames().indexOf(allGame), game);
            }
            sendGameToAll(request);
        }
    }

    private Game findGame(Game game){
        for (Game allGame : LobbyDatabase.getInstance().getAllGames()) {
            if (allGame.getId().equals(game.getId()))
                return allGame;
        }
        return null;
    }

    public void searchForGame(Request request) throws IOException {
        String id = (String) request.getData().get("id");
        Game game = findPrivateGameById(id);
        Response response = new Response();
        response.addData("game", game);
        sendResponse(response);
    }

    private void sendGameToAll(Request request) throws IOException {
        Response response = new Response();
        response.setAction("update list");
        response.addData("list1", LobbyDatabase.getInstance().getAllGames());
        response.addData("list2", buildList2(request));
        for (ServerSocketController clientSocket : Server.getClientSockets()) {
            clientSocket.sendResponseDirectly(response);
        }
    }

    public void sendResponseDirectly(Response message) throws IOException {
        String temp = new XStream().toXML(message);
        byte[] data = temp.getBytes(StandardCharsets.UTF_8);
        dataOutputStream2.writeInt(data.length);
        dataOutputStream2.write(data);
        dataOutputStream2.flush();
    }

    private void initTables(Request request) throws IOException {
        ArrayList<Game> list1 = buildList1(request);
        ArrayList<Game> list2 = buildList2(request);
        Response response = new Response();
        response.addData("list1", list1);
        response.addData("list2", list2);
        sendResponse(response);
    }

    private ArrayList<Game> buildList1(Request request) {
        ArrayList<Game> arrayListPublic = findPublicGames();
        if (arrayListPublic.size() <= 10) {
            return arrayListPublic;
        }
        ArrayList<Game> arrayList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(LobbyDatabase.getInstance().getAllGames().size());
            if (arrayList.contains(LobbyDatabase.getInstance().getAllGames().get(x)) || LobbyDatabase.getInstance().getAllGames().get(x).isPrivate()) {
                i--;
            } else {
                arrayList.add(LobbyDatabase.getInstance().getAllGames().get(x));
            }
        }
        return arrayList;
    }

    private ArrayList<Game> findPublicGames(){
        ArrayList<Game> arrayList = new ArrayList<>();
        for (Game game : LobbyDatabase.getInstance().getAllGames()) {
            if (!game.isPrivate())
                arrayList.add(game);
        }
        return arrayList;
    }

    private Game findPrivateGameById(String id){
        for (Game game : LobbyDatabase.getInstance().getAllGames()) {
            if (game.getId().equals(id)){
                return game;
            }
        }
        return null;
    }

    private ArrayList<Game> buildList2(Request request) {
        ArrayList<Game> arrayList = new ArrayList<>();
        for (Game game : LobbyDatabase.getInstance().getAllGames()) {
            if (game.getAdmin().getUsername().equals(((User) request.getData().get("this")).getUsername())) {
                arrayList.add(game);
            }
        }
        return arrayList;
    }

    public void addGame(Request request) throws IOException {
        Game game = (Game) request.getData().get("game");
        LobbyDatabase.getInstance().addGame(game);
    }

    private Request getMessage() throws IOException {
        int length = dataInputStream.readInt();
        byte[] data = new byte[length];
        dataInputStream.readFully(data);
        String messageJson = new String(data, StandardCharsets.UTF_8);
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        return (Request) xStream.fromXML(messageJson);
    }

    private void sendResponse(Response message) throws IOException {
        String temp = new XStream().toXML(message);
        byte[] data = temp.getBytes(StandardCharsets.UTF_8);
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
        dataOutputStream.flush();
        System.out.println("message  action " + message.getAction() + " send");
    }

    private void getOnlineUsers() throws IOException {
        Response response = new Response();
        response.setAction("khaste nabashid");
        response.setMessage("faghat baraye inke inja khali nabashe");
        ArrayList<User> users = Server.getOnlineUsers();
        response.addData("count", users.size());
        for (int i = 0; i < users.size(); i++) {
            response.addData("user" + i, users.get(i).toJson());
        }
        sendResponse(response);
    }

    private void getMessages(Request request) throws IOException {
        String senderUsername = (String) request.getData().get("senderUsername");
        String receiverUsername = (String) request.getData().get("receiverUsername");
        ArrayList<ChatMessage> messages = ServerChatController.getMessagesOfChat(receiverUsername, senderUsername);
        Response response = new Response();
        response.setAction("get messages done");
        // response.addData("count", messages.size());
        // for (int i = 0; i< messages.size(); i++) {
        //     response.addData("message" + i, messages.get(i));
        // }
        response.addData("messages", messages);
        sendResponse(response);
    }

    private void sendMessage(Request request) throws IOException {
        String text = (String) request.getData().get("text");
        String senderUsername = (String) request.getData().get("senderUsername");
        String receiverUsername = (String) request.getData().get("receiverUsername");
        ServerChatController.addMessage(text, receiverUsername, senderUsername);
    }

    private void isOnline(Request request) throws IOException {
        String username = (String) request.getData().get("username");
        Response response = new Response();
        response.setAction("isOnline done");
        if (isUsernameOnline(username)) {
            response.setMessage("online");
        } else {
            response.setMessage("offline");
        }
        sendResponse(response);
    }

    private void register(Request request) throws IOException {
        String username = (String) request.getData().get("username");
        String nickname = (String) request.getData().get("nickname");
        String password = (String) request.getData().get("password");
        String res = new LoginMenuController().registerServer(username, nickname, password);
        Response response = new Response();
        response.setMessage(res);
        response.setAction("register");
        sendResponse(response);
    }

    private void login(Request message) throws IOException {
        String username = (String) message.getData().get("username");
        String password = (String) message.getData().get("password");
        User user = new User(username, password, "");
        user = UserDatabase.getUserFromUsers(user);
        if (user == null) {
            String res = new LoginMenuController().loginServer(username, password);
            Response response = new Response();
            response.setMessage(res);
            response.setAction("login failed");
            sendResponse(response);
        } else {
            name = user.getUsername();
            String res = user.toJson();
            Response response = new Response();
            response.setMessage(res);
            response.setAction("login done");
            sendResponse(response);
        }

    }

    private void changePicture(Request message) {
        User user = UserDatabase.findUserByUsername(name);
        assert user != null;
        new ProfileMenuController().changeProfileServer(user, (String) message.getData().get("url"));
    }

    private void changePassword(Request message) throws IOException {
        User user = UserDatabase.findUserByUsername(name);
        assert user != null;
        String res = new ProfileMenuController().changePasswordServer(user,
                (String) message.getData().get("newPassword"),
                (String) message.getData().get("oldPassword"));
        if (res.equals("password changed successfully!")) {
            Response response = new Response();
            response.setMessage(user.toJson());
            response.setAction("change done");
            sendResponse(response);
        } else {
            Response response = new Response();
            response.setMessage(res);
            response.setAction("change failed");
            sendResponse(response);
        }
    }

    private void changeNickname(Request message) throws IOException {
        System.out.println("inja");
        User user = UserDatabase.findUserByUsername(name);
        assert user != null;
        System.out.println("inja");
        String res = new ProfileMenuController().changeNicknameServer(user,
                (String) message.getData().get("nickname"));
        System.out.println("inja");
        if (res.equals("nickname changed successfully!")) {
            Response response = new Response();
            response.setMessage(user.toJson());
            response.setAction("change done");
            sendResponse(response);
        } else {
            Response response = new Response();
            response.setMessage(res);
            response.setAction("changeNickname failed");
            sendResponse(response);
        }
    }

    private boolean isUsernameOnline(String username) {
        for (ServerSocketController s : Server.getClientSockets())
                if (s != null && s.name != null)
                    if (s.name.equals(username))
                        return true;
        return false;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

}
