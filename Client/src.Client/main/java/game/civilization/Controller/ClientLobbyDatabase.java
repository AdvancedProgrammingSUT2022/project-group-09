package game.civilization.Controller;

import game.civilization.FxmlController.LobbyController;
import game.civilization.Model.Game;

import java.util.ArrayList;

public class ClientLobbyDatabase {
    private static ClientLobbyDatabase instance;
    private LobbyController lobbyController;
    private ArrayList<Game> availableGames = new ArrayList<>();
    private ArrayList<Game> myGames = new ArrayList<>();
    private ArrayList<Game> gamesIn = new ArrayList<>();

    public static void setInstance(ClientLobbyDatabase instance) {
        ClientLobbyDatabase.instance = instance;
    }

    public ArrayList<Game> getAvailableGames() {
        return availableGames;
    }

    public void setAvailableGames(ArrayList<Game> availableGames) {
        this.availableGames = availableGames;
    }

    public ArrayList<Game> getMyGames() {
        return myGames;
    }

    public void setMyGames(ArrayList<Game> myGames) {
        this.myGames = myGames;
    }

    public ArrayList<Game> getGamesIn() {
        return gamesIn;
    }

    public void setGamesIn(ArrayList<Game> gamesIn) {
        this.gamesIn = gamesIn;
    }

    private ClientLobbyDatabase(){

    }

    public static ClientLobbyDatabase getInstance() {
        if (instance == null)
            instance = new ClientLobbyDatabase();
        return instance;
    }

    public void setLobbyController(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    public LobbyController getLobbyController() {
        return lobbyController;
    }

    public Game getAvailableGameById(String id){
        for (Game availableGame : availableGames) {
            if (availableGame.getId().equals(id))
                return availableGame;
        }
        return null;
    }

    public Game getMyGameById(String id){
        for (Game myGame : myGames) {
            if (myGame.getId().equals(id))
                return myGame;
        }
        return null;
    }
}
