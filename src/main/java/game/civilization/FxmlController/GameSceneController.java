package game.civilization.FxmlController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Model.Map;
import game.civilization.Model.Terrains.Terrain;
import game.civilization.Model.Terrains.TerrainType;
import game.civilization.SceneController.GameController;
import game.civilization.SceneModels.GameSceneDataBase;
import game.civilization.SceneModels.Tile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable {
    @FXML
    private Pane backPane;
    @FXML
    private Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameSceneDataBase.getInstance().setPane(pane);
        GameController.getInstance().run();
        MapMovement.getInstance().run();
        loadPane();
    }

    private void clearPane() {
        pane.getChildren().clear();
    }

    private void loadPane() {
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getTiles());
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getTileFeatures());
    }

}
