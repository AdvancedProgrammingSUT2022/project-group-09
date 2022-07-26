package game.civilization.Controller.NetworkController.Server;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.LobbyDatabase;
import game.civilization.Controller.LoginMenuController;
import game.civilization.Controller.NetworkController.GameServer.Proxy;
import game.civilization.Controller.NetworkController.GameServer.ProxySocketController;
import game.civilization.Controller.ProfileMenuController;
import game.civilization.Controller.UserDatabase;
import game.civilization.Model.Game;
import game.civilization.Model.NetworkModels.Message;
import game.civilization.Model.Request;
import game.civilization.Model.Response;
import game.civilization.Model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ServerSocketController {
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final Socket socket2;
    private final DataInputStream dataInputStream2;
    private final DataOutputStream dataOutputStream2;
    private String name;

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

    private void handleReq(Request request) throws IOException {
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
            case "search game" -> changePicture(request);
            case "change visibility" -> changePicture(request);
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
        if (UserDatabase.getCurrentUser().equals(game.getAdmin())){
            if (game.getPlayers().size() > 0){
                game.setAdmin(game.getPlayers().get(0));
            }
            else {
                LobbyDatabase.getInstance().getAllGames().remove(game);
            }
        }
        for (Game allGame : LobbyDatabase.getInstance().getAllGames()) {
            if (allGame.getId().equals(game.getId())) {
                LobbyDatabase.getInstance().getAllGames().set(LobbyDatabase.getInstance().getAllGames().indexOf(allGame), game);
            }
            sendGameToAll(request);
        }
    }

    public Response searchForGame(Game game) throws IOException {
//        Request request = new Request();
//        request.setAction("add game");
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("game", game);
//        request.setData(hashMap);
//        return sendRequestAndGetResponse(request);
        //TODO
        return null;
    }

    public Response changeVisibility(Game game) throws IOException {
//        Request request = new Request();
//        request.setAction("add game");
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("game", game);
//        request.setData(hashMap);
//        return sendRequestAndGetResponse(request);
        //TODO
        return null;
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
        ArrayList<Game> list1 = buildList1();
        ArrayList<Game> list2 = buildList2(request);
        Response response = new Response();
        response.addData("list1", list1);
        response.addData("list2", list2);
        sendResponse(response);
    }

    private ArrayList<Game> buildList1() {
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

    private void sendMessageDirectly(Message message) throws IOException {
        String temp = new XStream().toXML(message);
        byte[] data = temp.getBytes(StandardCharsets.UTF_8);
        dataOutputStream2.writeInt(data.length);
        dataOutputStream2.write(data);
        dataOutputStream2.flush();
    }

    private void sendResponse(Response message) throws IOException {
        String temp = new XStream().toXML(message);
        byte[] data = temp.getBytes(StandardCharsets.UTF_8);
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
        dataOutputStream.flush();
        System.out.println("message  action " + message.getAction() + " send");
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
        User user = UserDatabase.findUserByUsername(name);
        assert user != null;
        String res = new ProfileMenuController().changeNicknameServer(user,
                (String) message.getData().get("nickName"));
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


}
