package game.civilization.Controller.NetworkController.Server;

import game.civilization.Controller.UserDatabase;
import game.civilization.Model.Response;
import game.civilization.Model.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Application {

    private static final ArrayList<ServerSocketController> clientSockets = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        launch();
    }

    private void connect() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(socket + " first is connected");
            Socket socket2 = serverSocket.accept();
            System.out.println(socket + " second is connected");
            ServerSocketController socketHandler = new ServerSocketController(socket, socket2);
            Server.getClientSockets().add(socketHandler);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        UserDatabase.loadUsers();
        checkIsClientAlive();
        connect();
    }

    public void checkIsClientAlive() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                while (true) {
                    for (int i = 0; i < clientSockets.size(); i++) {
                        ServerSocketController s = clientSockets.get(i);
                        Thread tmpThread = new Thread(new Runnable() {
                            @Override
                            public synchronized void run() {
                                try {
                                    wait(600);
                                    clientSockets.remove(s);
                                } catch (InterruptedException e) {
                                }
                            }
                        });
                        tmpThread.start();
                        Response message = new Response();
                        message.setAction("are you alive?");
                        try {
                            s.sendResponseDirectly(message);
                            wait(200);
                        } catch (InterruptedException | IOException e) {
                        }
                        if (s.getIsAlive()) {
                            tmpThread.interrupt();
                            s.setIsAlive(false);
                        }
                    }
                    try {
                        wait(10000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public static ArrayList<ServerSocketController> getClientSockets() {
        return clientSockets;
    }

    public static ServerSocketController getClientSocketByUsername(String username) {
        for (ServerSocketController s : clientSockets) 
            if (s.getName().equals(username))
                return s;
        return null;
    }

    public static ArrayList<User> getOnlineUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (ServerSocketController s : Server.getClientSockets()) {
            User user = UserDatabase.findUserByUsername(s.getName());
            if (user != null)
                users.add(user);
        }
        return users;
    }
}
