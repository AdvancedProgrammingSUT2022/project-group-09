package game.civilization.SceneController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Model.Map;
import game.civilization.Model.TerrainFeatures.TerrainFeature;
import game.civilization.SceneModels.GameSceneDataBase;
import game.civilization.SceneModels.Tile;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class GameController {

    private static GameController GameController = null;

    public static GameController getInstance() {
        if (GameController == null)
            GameController = new GameController();
        return GameController;
    }

    private GameController() {

    }

    public void run() {
        initializeMap();
        loadTerrainFeatures();
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
                dy = dy == y ? dy + size * v : y;
                x += (3.0 / 2.0) * size;
            }
        }
    }

    private void loadTerrainFeatures() {
        for (Tile tile : GameSceneDataBase.getInstance().getTiles()) {
            tile.loadTerrainFeature();
        }
    }
}
