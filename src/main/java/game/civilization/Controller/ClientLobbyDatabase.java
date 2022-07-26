package game.civilization.Controller;

import game.civilization.FxmlController.LobbyController;

public class ClientLobbyDatabase {
    private static ClientLobbyDatabase instance;
    private LobbyController lobbyController;

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
}
