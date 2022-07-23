package game.civilization.Controller.NetworkController.GameServer;

import com.thoughtworks.xstream.XStream;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.UserDatabase;
import game.civilization.Model.Resources.Resource;
import game.civilization.Model.Units.Settler;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer extends Application {
    public static String getXml() {
        return xml;
    }

    public static void setXml(String xml) {
        GameServer.xml = xml;
    }

    private static ArrayList<GameServerSocketHandler> clientSockets = new ArrayList<>();
    private static String xml;
    private ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        launch();
    }

    public static ArrayList<GameServerSocketHandler> getClientSockets() {
        return clientSockets;
    }


    private void connect() throws IOException {
        serverSocket = new ServerSocket(700);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(socket + " first is connected");
            Socket socket2 = serverSocket.accept();
            System.out.println(socket + " second is connected");
            GameServerSocketHandler socketHandler = new GameServerSocketHandler(socket, socket2);
            GameServer.getClientSockets().add(socketHandler);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        GameDataBase.runGameForFirstTime(UserDatabase.getUsers());
        ((Settler) (GameDataBase.getCurrentCivilization().getUnits().get(0))).foundCity();
        GameDataBase.getCurrentCivilization().getMap().setAllVisible();
        XStream xStream = new XStream();
        GameServer.xml = xStream.toXML(GameDataBaseSaving.getInstance());
        connect();
    }
}
