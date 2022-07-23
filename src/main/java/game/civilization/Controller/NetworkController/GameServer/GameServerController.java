package game.civilization.Controller.NetworkController.GameServer;

import game.civilization.Controller.LoginMenuController;
import game.civilization.Model.NetworkModels.Message;
import game.civilization.Model.Request;

public class GameServerController {
    private static GameServerController instance;

    private GameServerController() {
    }

    public static GameServerController getInstance() {
        if (instance == null)
            instance = new GameServerController();
        return instance;
    }

}
