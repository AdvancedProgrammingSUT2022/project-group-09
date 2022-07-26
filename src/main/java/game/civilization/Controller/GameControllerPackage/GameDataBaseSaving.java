package game.civilization.Controller.GameControllerPackage;

import game.civilization.Controller.UserDatabase;
import game.civilization.Model.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import game.civilization.Model.Improvements.Improvement;
import game.civilization.Model.Terrains.Terrain;

public class GameDataBaseSaving {
    private int turn;
    private MainMap mainMap;
    private ArrayList<User> players; //ina bazi mikonan avalesh inja sabt mishe
    private Civilization currentCivilization;
    private ArrayList<Civilization> civilizations;
    private Selectable selected;
    private Improvement[][] improvements = new Improvement[Map.getRow()][Map.getColumn()];
    private Boolean[][] improvementsBoolean = new Boolean[Map.getRow()][Map.getColumn()];
    private boolean online;


    private GameDataBaseSaving() {
    }

    public static GameDataBaseSaving getInstance() {
        GameDataBaseSaving data = new GameDataBaseSaving();
        data.turn = GameDataBase.getTurn();
        data.mainMap = GameDataBase.getMainMap();
        data.players = GameDataBase.getPlayers();
        data.currentCivilization = GameDataBase.getCurrentCivilization();
        data.civilizations = GameDataBase.getCivilizations();
        for (int i = 0; i < Map.getColumn(); i++)
            for (int j = 0; j < Map.getRow(); j++) {
                data.improvements[i][j] = GameDataBase.getMainMap().getTerrain(i, j).getImprovement();
                data.improvementsBoolean[i][j] = GameDataBase.getMainMap().getTerrain(i, j).getImprovementPair().getValue();
            }
        data.online = GameDataBase.isOnline();
        return data;
    }

    public void setToGameDataBase() {
        GameDataBase.setTurn(turn);
        GameDataBase.setSelected(selected);
        GameDataBase.setMainMap(mainMap);
        GameDataBase.setPlayers(players);
        GameDataBase.setCurrentCivilization(currentCivilization);
        HashMap<User, Civilization> civilizationHashMap = new HashMap<>();
        for (int i = 0; i < civilizations.size(); i++) {
            civilizationHashMap.put(players.get(i), civilizations.get(i));
        }
        GameDataBase.setCivilizations(civilizationHashMap);
        GameDataBase.setOnline(online);
        for (int i = 0; i < Map.getColumn(); i++)
            for (int j = 0; j < Map.getRow(); j++) {
                GameDataBase.getMainMap().getTerrain(i, j).setImprovement(improvements[i][j]);
                GameDataBase.getMainMap().getTerrain(i, j).getImprovementPair().setValue(improvementsBoolean[i][j]);
            }
    }


    public static void saveGame() {
        try {
            FileWriter fileWriter;
            if (Files.exists(Paths.get("data/gameInformation.xml")))
                fileWriter = new FileWriter("data/gameInformation.xml", false);
            else {
                new File("data").mkdir();
                fileWriter = new FileWriter("data/gameInformation.xml", false);
            }
            XStream xStream = new XStream();
            fileWriter.write(xStream.toXML(GameDataBaseSaving.getInstance()));
            fileWriter.close();
        } catch (IOException ignored) {
        }
    }

    public static void saveMap() {
        try {
            FileWriter fileWriter;
            if (Files.exists(Paths.get("data/map.xml")))
                fileWriter = new FileWriter("data/map.xml", false);
            else {
                new File("data").mkdir();
                fileWriter = new FileWriter("data/map.xml", false);
            }
            XStream xStream = new XStream();
            fileWriter.write(xStream.toXML(GameDataBaseSaving.getInstance()));
            fileWriter.close();
        } catch (IOException ignored) {
        }
    }

    public static void loadGame() {
        try {
            String xml = new String(Files.readAllBytes(Paths.get("data/gameInformation.xml")));
            XStream xStream = new XStream();
            xStream.addPermission(AnyTypePermission.ANY);
            if (xml.length() != 0) {
                GameDataBaseSaving game = (GameDataBaseSaving) xStream.fromXML(xml);
                game.setToGameDataBase();
            }
        } catch (IOException ignored) {
        }
    }

    public static void loadMap() {
        try {
            String xml = new String(Files.readAllBytes(Paths.get("data/map.xml")));
            XStream xStream = new XStream();
            xStream.addPermission(AnyTypePermission.ANY);
            if (xml.length() != 0) {
                GameDataBaseSaving game = (GameDataBaseSaving) xStream.fromXML(xml);
                game.setToGameDataBase();
            }
        } catch (IOException ignored) {
        }
    }

}
