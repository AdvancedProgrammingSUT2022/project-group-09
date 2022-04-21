package com.civilization.Controller.GameControllerPackage;

import com.civilization.Model.City;
import com.civilization.Model.Civilization;
import com.civilization.Model.MainMap;
import com.civilization.Model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameDataBase {
    static private int turn;
    static private MainMap mainMap;
    static private ArrayList<User> players = new ArrayList<>(); //ina bazi mikonan avalesh inja sabt mishe
    static private User currentUser = null;
    static private Civilization currentCivilization = null;
    static private HashMap<User, Civilization> civilizations = new HashMap<>();

    public static void runGameForFirstTime(ArrayList<User> players) {
        GameDataBase.players = players;
        setGameDataBase();
    }

    private static void setGameDataBase() {

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

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        GameDataBase.currentUser = currentUser;
    }

    public static Civilization getCurrentCivilization() {
        return currentCivilization;
    }

    public static void setCurrentCivilization(Civilization currentCivilization) {
        GameDataBase.currentCivilization = currentCivilization;
    }
}
