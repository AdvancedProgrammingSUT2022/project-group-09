package game.civilization.Controller.GameControllerPackage;


import game.civilization.Model.Terrains.Terrain;
import game.civilization.Model.Terrains.TerrainType;
import game.civilization.Model.Units.Settler;
import game.civilization.Model.*;

import java.util.*;
import java.util.Map;

public class GameDataBase {
    static private final int turnToYear = 50;
    static private int turn;
    static private MainMap mainMap;
    static private ArrayList<User> players; //ina bazi mikonan avalesh inja sabt mishe
    static private Civilization currentCivilization;
    static private HashMap<User, Civilization> civilizations;

    static private Selectable selected;

    public static void runGameForFirstTime(ArrayList<User> players) {
        GameDataBase.civilizations = new HashMap<>();
        GameDataBase.players = players;
        setGameDataBase();
    }

    private static void setGameDataBase() {
        for (User player : players) {
            civilizations.put(player, new Civilization(player.getUsername()));
        }
        currentCivilization = civilizations.get(players.get(0));
        turn = 0;
        mainMap = new MainMap();
        Random random = new Random();
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            Coordination coordination = mainMap.getDrought().get(random.nextInt(mainMap.getDrought().size()));
            while (coordination.getTerrain().getType() == TerrainType.MOUNTAIN ||
                    coordination.getTerrain().getCivilization() != null)
                coordination = mainMap.getDrought().get(random.nextInt(mainMap.getDrought().size()));
            Terrain terrain = GameDataBase.getMainMap().getTerrain(coordination.getX(), coordination.getY());
            new Settler(terrain, civilization);
        }
        setRuins();
        GameDataBase.getCurrentCivilization().getMap().updateExploration();
    }

    private static void setRuins() {
        for (int i = 0; i < 30; i++) {
            Random random = new Random();
            Coordination coordination = mainMap.getDrought().get(random.nextInt(mainMap.getDrought().size()));
            while (coordination.getTerrain().getType() == TerrainType.MOUNTAIN ||
                    coordination.getTerrain().getCivilization() != null
                    || coordination.getTerrain() instanceof City)
                coordination = mainMap.getDrought().get(random.nextInt(mainMap.getDrought().size()));
            coordination.getTerrain().setRuin(true);
        }
    }

    public static ArrayList<Civilization> getCivilizations() {
        ArrayList<Civilization> civs = new ArrayList<>();
        for (User player : players) {
            civs.add(civilizations.get(player));
        }
        return civs;
    }

    public static void setCivilizations(HashMap<User, Civilization> civilizations) {
        GameDataBase.civilizations = civilizations;
    }

    public static MainMap getMainMap() {
        return mainMap;
    }

    public static void setMainMap(MainMap mainMap) {
        GameDataBase.mainMap = mainMap;
    }

    public static int getTurn() {
        return turn;
    }

    public static void setTurn(int turn) {
        GameDataBase.turn = turn;
    }

    public static ArrayList<User> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<User> players) {
        GameDataBase.players = players;
    }

    public static Civilization getCurrentCivilization() {
        return currentCivilization;
    }

    public static void setCurrentCivilization(Civilization currentCivilization) {
        GameDataBase.currentCivilization = currentCivilization;
    }

    public static City getCityByName(String name) {
        for (User player : players) {
            for (City city : civilizations.get(player).getCities()) {
                if (city.getName().equals(name)) {
                    return city;
                }
            }
        }
        return null;
    }

    public static Selectable getSelected() {
        return selected;
    }

    public static void setSelected(Selectable selected) {
        GameDataBase.selected = selected;
    }

    public static void nextTurn() {
        handleYear2050();
        removeLosers();
        turn++;
        setCurrentCivilization(getCivilizations().get(turn % getCivilizations().size()));
    }

    private static void handleYear2050() {
        if (turn * turnToYear >= 2050) {
            ArrayList<User> userArrayList = new ArrayList<>();
            ArrayList<Civilization> civilizationArrayList = new ArrayList<>();
            Civilization winner = null;
            int winnerScore = 0;
            for (Civilization civilization : getCivilizations()) {
                if (winnerScore < civilization.getScore()) {
                    winner = civilization;
                    winnerScore = civilization.getScore();
                }
            }
            for (Map.Entry<User, Civilization> entry : civilizations.entrySet()) {
                User key = entry.getKey();
                Civilization value = entry.getValue();
                if (value != winner) {
                    userArrayList.add(key);
                    civilizationArrayList.add(value);
                }
            }
            removeUser(userArrayList, civilizationArrayList);
        }
    }

    private static void removeLosers() {
        ArrayList<User> userArrayList = new ArrayList<>();
        ArrayList<Civilization> civilizationArrayList = new ArrayList<>();
        for (Civilization civilization : getCivilizations()) {
            if (civilization.getCities().size() <= 0 && civilization.getUnits().size() <= 0) {
                System.out.println("civilization " + civilization.getName() + " will die");
                for (Map.Entry<User, Civilization> entry : civilizations.entrySet()) {
                    User key = entry.getKey();
                    Civilization value = entry.getValue();
                    if (key.getUsername().equals(civilization.getName())) {
                        userArrayList.add(key);
                        civilizationArrayList.add(value);
                    }
                }
            }
        }
        removeUser(userArrayList, civilizationArrayList);
    }

    private static void removeUser(ArrayList<User> userArrayList, ArrayList<Civilization> civilizationArrayList) {
        for (int i = 0; i < userArrayList.size(); i++) {
            userArrayList.get(i).setScore(civilizationArrayList.get(i).getScore());
            civilizations.remove(userArrayList.get(i), civilizationArrayList.get(i));
            players.remove(userArrayList.get(i));
        }
    }

    public static Civilization findCiv(String name) {
        for (Civilization civilization : getCivilizations()) {
            if (civilization.getName().equals(name))
                return civilization;
        }
        return null;
    }

    public static int getYear() {
        return turn * turnToYear;
    }

}
