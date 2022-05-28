package game.civilization.FxmlController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Model.Map;
import game.civilization.SceneModels.GameSceneDataBase;
import game.civilization.SceneModels.Tile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable {
    @FXML
    private Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameSceneDataBase.getInstance().setPane(pane);
        initializeMap();
        MapMovement.getInstance().run();
    }

    private void initializeMap() {
        int numberOfTerrainX = Map.getColumn();
        int numberOfTerrainY = Map.getRow();
        double y = 0;
        double size = 100, v = Math.sqrt(3) / 2.0;
        for (int j = 0; j < numberOfTerrainY; j++) {
            y += size * Math.sqrt(3);
            double x = -25, dy = y;
            for (int k = 0; k < numberOfTerrainX; k++) {
                Tile tile = new Tile(x, y, dy, GameDataBase.getMainMap().getTerrain(k, j));
                GameSceneDataBase.getInstance().getTiles().add(tile);
                pane.getChildren().add(tile);
                dy = dy == y ? dy + size * v : y;
                x += (3.0 / 2.0) * size;
            }
        }
    }

}
