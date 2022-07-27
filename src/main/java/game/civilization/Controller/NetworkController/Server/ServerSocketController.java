package game.civilization.Controller.NetworkController.Server;

import game.civilization.Controller.LoginMenuController;
import game.civilization.Controller.NetworkController.GameServer.Proxy;
import game.civilization.Controller.NetworkController.GameServer.ProxySocketController;
import game.civilization.Controller.ProfileMenuController;
import game.civilization.Controller.UserDatabase;
import game.civilization.Controller.ChatController.ServerChatController;
import game.civilization.Model.NetworkModels.Message;
import game.civilization.Model.JSONWebToken;
import game.civilization.Model.Request;
import game.civilization.Model.Response;
import game.civilization.Model.User;
import game.civilization.Model.Chat.ChatMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

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

    private void handleReq(Request request) throws IOException {
        switch (request.getAction()) {
            case "register" -> register(request);
            case "login" -> login(request);
            case "changeNickname" -> changeNickname(request);
            case "changePassword" -> changePassword(request);
            case "changePicture" -> changePicture(request);
            case "isOnline" -> isOnline(request);
            case "stayin alive" -> isAlive = true;
            case "get online users" -> getOnlineUsers();
            case "get_messages" -> getMessages(request);
            case "send_message" -> sendMessage(request);
        }
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

    public void sendMessageDirectly(Message message) throws IOException {
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

    public void sendResponseDirectly(Response message) throws IOException {
        String temp = new XStream().toXML(message);
        byte[] data = temp.getBytes(StandardCharsets.UTF_8);
        dataOutputStream2.writeInt(data.length);
        dataOutputStream2.write(data);
        dataOutputStream2.flush();
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
