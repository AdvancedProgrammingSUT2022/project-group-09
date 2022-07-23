package game.civilization.Controller.NetworkController.Server;

import game.civilization.Controller.LoginMenuController;
import game.civilization.Controller.NetworkController.GameServer.Proxy;
import game.civilization.Controller.NetworkController.GameServer.ProxySocketController;
import game.civilization.Controller.ProfileMenuController;
import game.civilization.Controller.UserDatabase;
import game.civilization.Model.NetworkModels.Message;
import game.civilization.Model.Request;
import game.civilization.Model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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
                Message message = getMessage();
                System.out.println("message " + message.getAction() + " action " + " received");
                if (message.getAction().equals("exit")) {
                    System.out.println("socket " + socket + "disconnected");
                    return;
                }
                handleReq(message);
            }
        } catch (Exception ignored) {

        }
    }

    private void handleReq(Message message) throws IOException {
        switch (message.getAction()) {
            case "introduction" -> {
                System.out.println("introduction done client name is :" + message.getMessage());
                name = "";
            }
            case "register" -> register(message);
            case "login" -> login(message);
            case "changeNickname" -> changeNickname(message);
            case "changePassword" -> changePassword(message);
            case "changePicture" -> changePicture(message);
        }
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
        dataOutputStream2.writeInt(data.length);
        dataOutputStream2.write(data);
        dataOutputStream2.flush();
    }

    private void sendMessageOnFirstSocket(Message message) throws IOException {
        String temp = message.toJson();
        byte[] data = temp.getBytes(StandardCharsets.UTF_8);
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
        dataOutputStream.flush();
    }

    private void register(Message message) throws IOException {
        Request request = Request.fromJson(message.getMessage());
        String username = (String) request.getData().get("username");
        String nickname = (String) request.getData().get("nickname");
        String password = (String) request.getData().get("password");
        String res = new LoginMenuController().registerServer(username, nickname, password);
        Message message1 = new Message();
        message1.setMessage(res);
        message1.setAction("register");
        sendMessageOnFirstSocket(message1);
    }

    private void login(Message message) throws IOException {
        Request request = Request.fromJson(message.getMessage());
        String username = (String) request.getData().get("username");
        String password = (String) request.getData().get("password");
        User user = new User(username, password, "");
        user = UserDatabase.getUserFromUsers(user);
        if (user == null) {
            String res = new LoginMenuController().loginServer(username, password);
            Message message1 = new Message();
            message1.setMessage(res);
            message1.setAction("login failed");
            sendMessageOnFirstSocket(message1);
        } else {
            name = user.getUsername();
            String res = user.toJson();
            Message message1 = new Message();
            message1.setMessage(res);
            message1.setAction("login done");
            sendMessageOnFirstSocket(message1);
        }

    }

    private void changePicture(Message message) {
        User user = UserDatabase.findUserByUsername(name);
        assert user != null;
        new ProfileMenuController().changeProfileServer(user, message.getMessage());
    }

    private void changePassword(Message message) throws IOException {
        User user = UserDatabase.findUserByUsername(name);
        assert user != null;
        String res = new ProfileMenuController().changePasswordServer(user,
                (String) Request.fromJson(message.getMessage()).getData().get("newPassword"),
                (String) Request.fromJson(message.getMessage()).getData().get("oldPassword"));
        if (res.equals("password changed successfully!")) {
            Message message1 = new Message();
            message1.setMessage(user.toJson());
            message1.setAction("change done");
            sendMessageOnFirstSocket(message1);
        } else {
            Message message1 = new Message();
            message1.setMessage(res);
            message1.setAction("change failed");
            sendMessageOnFirstSocket(message1);
        }
    }

    private void changeNickname(Message message) throws IOException {
        User user = UserDatabase.findUserByUsername(name);
        assert user != null;
        String res = new ProfileMenuController().changeNicknameServer(user,
                (String) Request.fromJson(message.getMessage()).getData().get("nickName"));
        if (res.equals("nickname changed successfully!")) {
            Message message1 = new Message();
            message1.setMessage(user.toJson());
            message1.setAction("change done");
            sendMessageOnFirstSocket(message1);
        } else {
            Message message1 = new Message();
            message1.setMessage(res);
            message1.setAction("changeNickname failed");
            sendMessageOnFirstSocket(message1);
        }
    }


}
