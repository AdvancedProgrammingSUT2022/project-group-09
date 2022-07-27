package game.civilization.Model;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Game {
    private int numberOfPlayers;
    private ArrayList<User> players = new ArrayList<>();
    private User admin;
    private boolean isPrivate;
    private String id;

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public ArrayList<User> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<User> players) {
        this.players = players;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }


    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addPlayer(User user) {
        players.add(user);
    }

    public static Game fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Game.class);
    }

}
