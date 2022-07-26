package game.civilization.FxmlController;

import game.civilization.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneController {
    private Stage stage = null;

    private static SceneController mainSceneController = null;

    public static SceneController getInstance() {
        if (mainSceneController == null)
            mainSceneController = new SceneController();
        return mainSceneController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void LoginMenu() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("fxml/LoginMenuView.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void MainMenu() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("fxml/MainMenuView.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void game() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("fxml/Game.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void buildMap() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("fxml/buildMap.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gameMenu() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("fxml/GameMenuView.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void scoreBoard() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("fxml/ScoreBoardView.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void profileMenu() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("fxml/ProfileMenuView.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void chatMenu() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("fxml/ChatMenuView.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Lobby() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("fxml/Lobby.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cheat() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("fxml/Cheat.fxml")));
        Scene scene = new Scene(root);
        Stage cheatStage = new Stage();
        cheatStage.setTitle("CHEAT !");
        cheatStage.setScene(scene);
        cheatStage.show();
    }

    public void trade() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("fxml/trade.fxml")));
        Scene scene = new Scene(root);
        Stage cheatStage = new Stage();
        cheatStage.setTitle("Trade !");
        cheatStage.setScene(scene);
        cheatStage.show();
    }


    public void build() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("fxml/build.fxml")));
        Scene scene = new Scene(root);
        Stage cheatStage = new Stage();
        cheatStage.setTitle("build !");
        cheatStage.setScene(scene);
        cheatStage.show();
    }
}
