package game.civilization.FxmlController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.FxmlController.GameScenes.SceneController.*;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.FxmlController.GameScenes.SceneModels.Tile;
import game.civilization.Model.Civilization;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class BuildMapController implements Initializable {
    public Label turnLabel;
    public Pane pane;
    public Button finishButton;
    public Pane backPane;
    private Tile Tile;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDataToGameSceneDataBase();
        GameDataBase.setCurrentCivilization(new Civilization("builder"));
        GameDataBase.setCivilizations(new HashMap<>());
        GameDataBase.setPlayers(new ArrayList<>());
        refresh();
    }

    private void setDataToGameSceneDataBase() {
        GameSceneDataBase.getInstance().setBuildMapController(this);
        GameSceneDataBase.getInstance().setBackPane(backPane);
        GameSceneDataBase.getInstance().setPane(pane);
    }

    private void loadPane() {
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getTiles());
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getCityIcons());
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getTileFeatures());
    }

    public void refresh() {
        GameDataBase.getCurrentCivilization().getMap().setAllVisible();
        pane.getChildren().clear();
        MapController.getInstance().runForBuild();
        MapMovement.getInstance().run();
        for (Tile tile : GameSceneDataBase.getInstance().getTiles()) {
            tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Tile = tile;
                    try {
                        SceneController.getInstance().build();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        loadPane();
    }

    public void finish(ActionEvent actionEvent) {
    }

    public Tile getTile() {
        return Tile;
    }

    public void setTile(Tile tile) {
        this.Tile = tile;
    }
}
