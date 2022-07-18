package game.civilization.Controller.NetworkController.Server;

import game.civilization.Controller.LoginMenuController;
import game.civilization.Model.NetworkModels.Message;
import game.civilization.Model.Request;

public class ServerController {
    private static ServerController instance;

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null)
            instance = new ServerController();
        return instance;
    }

}
