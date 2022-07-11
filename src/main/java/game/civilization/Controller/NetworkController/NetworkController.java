package game.civilization.Controller.NetworkController;

public class NetworkController {
    private static NetworkController instance;

    public static NetworkController getInstance() {
        if (instance == null)
            instance = new NetworkController();
        return instance;
    }

}