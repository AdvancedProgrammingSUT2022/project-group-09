package game.civilization.Controller;

import game.civilization.Model.Game;
import game.civilization.Model.User;

import java.util.ArrayList;

public class LobbyDatabase {
    private static LobbyDatabase instance;
    private ArrayList<Game> allGames = new ArrayList<>();
    private ArrayList<User> onlineUsers = new ArrayList<>();//todo

    private LobbyDatabase() {

    }

    public static LobbyDatabase getInstance() {
        if (instance == null)
            instance = new LobbyDatabase();
        return instance;
    }

    public ArrayList<Game> getAllGames() {
        return allGames;
    }

    public void setAllGames(ArrayList<Game> allGames) {
        this.allGames = allGames;
    }

    public void addGame(Game game) {
        allGames.add(game);
    }
}
