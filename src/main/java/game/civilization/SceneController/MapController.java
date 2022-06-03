package game.civilization.SceneController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Model.Map;
import game.civilization.Model.Units.Unit;
import game.civilization.SceneModels.GameSceneDataBase;
import game.civilization.SceneModels.Tile;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

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
        loadCityIcon();
        loadTerrainFeatures();
        loadUnits();
        showInfoTileInfo();
        showUnitInfo();
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

    private void loadCityIcon() {
        for (Tile tile : GameSceneDataBase.getInstance().getTiles()) {
            tile.loadCityIcon();
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

    private Pane makeUnitInfoPane(Unit unit) {
        Pane pane = new Pane();
        pane.prefWidth(6000);
        pane.prefHeight(6000);
        pane.setLayoutX(0);
        pane.setLayoutY(50);
        pane.setStyle("-fx-background-color: #e0b600");
        Label label = new Label(unit.showInfo());
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

    private void showUnitInfo() {
        for (Circle circle : GameSceneDataBase.getInstance().getUnits()) {


            Unit unit = null;
            for (Tile tile : GameSceneDataBase.getInstance().getTiles()) {
                if (tile.getUnitCircle() == circle) {
                    if (tile.getTerrain().getCivilianUnit() == null)
                        unit = tile.getTerrain().getMilitaryUnit();
                    else
                        unit = tile.getTerrain().getCivilianUnit();
                }
            }

            Unit finalUnit = unit;
            circle.setOnMouseEntered(mouseEvent -> {
                setTerrainInfoPane(makeUnitInfoPane(finalUnit));
                GameSceneDataBase.getInstance().getBackPane().getChildren().add(getTerrainInfoPane());
            });
            circle.setOnMouseExited(mouseEvent -> {
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
