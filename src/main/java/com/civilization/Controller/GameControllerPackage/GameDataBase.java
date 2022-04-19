package com.civilization.Controller.GameControllerPackage;

import com.civilization.Model.Civilization;
import com.civilization.Model.MainMap;
import com.civilization.Model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class GameDataBase {
    static private int turn;
    static private MainMap mainMap;
    static private ArrayList<User> players; //ina bazi mikonan avalesh inja sabt mishe
    static private User currentUser;
    static private Civilization currentCivilization;
    static private HashMap<User, Civilization> civilizations;

    public static void runGameForFirstTime(ArrayList<User> players) {
        GameDataBase.players = players;
        setGameDataBase();
    }

    private static void setGameDataBase() {

    }

}
