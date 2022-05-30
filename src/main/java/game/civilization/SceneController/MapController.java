package game.civilization.SceneController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Model.Map;
import game.civilization.SceneModels.GameSceneDataBase;
import game.civilization.SceneModels.Tile;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MapController {

    private static MapController GameController = null;
    private Pane TerrainInfoPane = null;

    public static MapController getInstance() {
        if (GameController == null)
            GameController = new MapController();
        return GameController;
    }

    private MapController() {

    }

    public void run() {
        initializeMap();
        loadTerrainFeatures();
        loadUnits();
        showInfoTileInfo();
        loadCivilizationInfo();

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

    private void loadUnits() {
        for (Tile tile : GameSceneDataBase.getInstance().getTiles()) {
            tile.loadUnit();
        }
    }

    private void loadCivilizationInfo() {
        GameDataBase.getCurrentCivilization().updateData();
        GameSceneDataBase.getInstance().getGoldLabel().setText(String.valueOf(GameDataBase.getCurrentCivilization().getGold().getCurrentGold()));
        GameSceneDataBase.getInstance().getScienceLabel().setText(String.valueOf(GameDataBase.getCurrentCivilization().getScience().getAdditionScience()));
        GameSceneDataBase.getInstance().getHappinessLabel().setText(String.valueOf(GameDataBase.getCurrentCivilization().getHappiness().getAdditionHappiness()));
    }

    private Pane makeTerrainPanelPane(Tile tile) {
        Pane pane = new Pane();
        pane.prefWidth(6000);
        pane.prefHeight(6000);
        pane.setLayoutX(0);
        pane.setLayoutY(50);
        pane.setStyle("-fx-background-color: #e0b600");
        Label label = new Label(new game.civilization.Controller.GameControllerPackage.MapController().showDetails(tile.getTerrain().getCoordination()));
        label.setMinWidth(50);
        label.setMinHeight(50);
        pane.getChildren().add(label);
        return pane;
    }

    private void showInfoTileInfo() {
        for (Tile tile : GameSceneDataBase.getInstance().getTiles()) {
            tile.setOnMouseEntered(mouseEvent -> {
                setTerrainInfoPane(makeTerrainPanelPane(tile));
                GameSceneDataBase.getInstance().getBackPane().getChildren().add(getTerrainInfoPane());
            });
            tile.setOnMouseExited(mouseEvent -> {
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(getTerrainInfoPane());
                setTerrainInfoPane(null);
            });
        }

    }

    public Pane getTerrainInfoPane() {
        return TerrainInfoPane;
    }

    public void setTerrainInfoPane(Pane terrainInfoPane) {
        TerrainInfoPane = terrainInfoPane;
    }
}
