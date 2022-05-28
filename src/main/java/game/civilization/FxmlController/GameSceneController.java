package game.civilization.FxmlController;

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
        int height = 2000;
        int width = 1500;
        double size = 100, v = Math.sqrt(3) / 2.0;
        for (double y = 0; y < height; y += size * Math.sqrt(3)) {
            for (double x = -25, dy = y; x < width; x += (3.0 / 2.0) * size) {
                Tile tile = new Tile(x, y, dy);
                GameSceneDataBase.getInstance().getTiles().add(tile);
                pane.getChildren().add(tile);
                dy = dy == y ? dy + size * v : y;
            }
        }
    }

}
