package game.civilization.FxmlController;

import java.io.IOException;

import game.civilization.Controller.UserDatabase;

public class MainMenuViewController {
    public void logout() throws IOException{
        UserDatabase.updateData();
        SceneController.getInstance().LoginMenu();
    }

    public void switchToGameMenu() throws IOException{
        SceneController.getInstance().gameMenu();
    }

    public void switchToScoreBoard() throws IOException{
        SceneController.getInstance().scoreBoard();
    }

    public void switchToChatMenu() throws IOException{
        SceneController.getInstance().chatMenu();
    }

    public void switchToProfileMenu() throws IOException{
        SceneController.getInstance().profileMenu();
    }
}
