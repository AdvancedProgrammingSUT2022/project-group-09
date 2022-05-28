package game.civilization.FxmlController;

import game.civilization.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneController {

    private static SceneController mainSceneController = null;
    public static SceneController getInstance() {
        if (mainSceneController == null)
            mainSceneController = new SceneController();
        return mainSceneController;
    }

    public void LoginMenu(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Login.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void MainMenu(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Main.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void GameMenu(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Game.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
