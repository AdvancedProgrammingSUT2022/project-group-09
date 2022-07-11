package game.civilization.Controller.GameControllerPackage;

import com.google.gson.Gson;
import game.civilization.Controller.UserDatabase;
import game.civilization.Model.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class GameDataBaseSaving {
    private int turn;
    private MainMap mainMap;
    private ArrayList<User> players; //ina bazi mikonan avalesh inja sabt mishe
    private Civilization currentCivilization;
    private ArrayList<Civilization> civilizations;

    private Selectable selected;


    private GameDataBaseSaving() {
    }

    private static GameDataBaseSaving getInstance() {
        GameDataBaseSaving data = new GameDataBaseSaving();
        data.turn = GameDataBase.getTurn();
        data.mainMap = GameDataBase.getMainMap();
        data.players = GameDataBase.getPlayers();
        data.currentCivilization = GameDataBase.getCurrentCivilization();
        data.civilizations = GameDataBase.getCivilizations();
        return data;
    }

    private void setToGameDataBase() {
        GameDataBase.setTurn(turn);
        GameDataBase.setSelected(selected);
        GameDataBase.setMainMap(mainMap);
        GameDataBase.setPlayers(players);
        GameDataBase.setCurrentUser(UserDatabase.getCurrentUser());
        GameDataBase.setCurrentCivilization(currentCivilization);
        HashMap<User, Civilization> civilizationHashMap = new HashMap<>();
        for (int i = 0; i < civilizations.size(); i++) {
            civilizationHashMap.put(players.get(i), civilizations.get(i));
        }
        GameDataBase.setCivilizations(civilizationHashMap);
    }


    public static boolean saveGame(){
        try {
            FileWriter fileWriter;
            if (Files.exists(Paths.get("data/gameInformation.xml")))
                fileWriter = new FileWriter("data/gameInformation.xml",false);
            else{
                new File("data").mkdir();
                fileWriter = new FileWriter("data/gameInformation.xml",false);
            }
            XStream xStream = new XStream();
            fileWriter.write(xStream.toXML(GameDataBaseSaving.getInstance()));
            fileWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void loadGame(){
        try {
            String xml = new String(Files.readAllBytes(Paths.get("data/gameInformation.xml")));
            XStream xStream = new XStream();
            xStream.addPermission(AnyTypePermission.ANY);
            if(xml.length() != 0){
                GameDataBaseSaving game = (GameDataBaseSaving)xStream.fromXML(xml);
                game.setToGameDataBase();
            }
        } catch (IOException ignored) {
        }
    }

}
