package game.civilization.FxmlController;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ChatMenuViewController {

    public void back(ActionEvent actionEvent) throws IOException {
        SceneController.getInstance().MainMenu();
    }
}
