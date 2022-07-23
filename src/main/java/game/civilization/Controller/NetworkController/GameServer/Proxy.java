package game.civilization.Controller.NetworkController.GameServer;

import com.thoughtworks.xstream.XStream;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.UserDatabase;
import game.civilization.Model.Improvements.Improvement;
import game.civilization.Model.Units.Settler;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Proxy extends Application {
    private static ArrayList<ProxySocketController> clientSockets = new ArrayList<>();
    private static String xml;
    private ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        launch();
    }

    public static ArrayList<ProxySocketController> getClientSockets() {
        return clientSockets;
    }

    public static String getXml() {
        return xml;
    }

    public static void setXml(String xml) {
        Proxy.xml = xml;
    }


    private void connect() throws IOException {
        serverSocket = new ServerSocket(700);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(socket + " first is connected");
            Socket socket2 = serverSocket.accept();
            System.out.println(socket + " second is connected");
            ProxySocketController socketHandler = new ProxySocketController(socket, socket2);
            Proxy.getClientSockets().add(socketHandler);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        UserDatabase.loadUsers();
        GameDataBase.runGameForFirstTime(UserDatabase.getUsers());
        ((Settler) (GameDataBase.getCurrentCivilization().getUnits().get(0))).foundCity();
        GameDataBase.getMainMap().getTerrain(0,0).setImprovement(Improvement.FARM);
        GameDataBaseSaving.saveGame();
        XStream xStream = new XStream();
        Proxy.xml = xStream.toXML(GameDataBaseSaving.getInstance());
        connect();
    }
}
