package game.civilization.SceneModels;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Timer;

public class GameSceneDataBase {
    private static GameSceneDataBase gameSceneDataBase = null;

    public Pane getBackPane() {
        return backPane;
    }

    public void setBackPane(Pane backPane) {
        this.backPane = backPane;
    }


    public static GameSceneDataBase getInstance() {
        if (gameSceneDataBase == null)
            gameSceneDataBase = new GameSceneDataBase();
        return gameSceneDataBase;
    }

    private GameSceneDataBase() {

    }

    private final ArrayList<Tile> tiles = new ArrayList<>();
    private final ArrayList<Circle> tileFeatures = new ArrayList<>();
    private final ArrayList<Circle> units = new ArrayList<>();
    private Pane pane;
    private Pane backPane = null;
    private Label scienceLabel;
    private Label goldLabel;
    private Label happinessLabel;


    public Label getScienceLabel() {
        return scienceLabel;
    }

    public void setScienceLabel(Label scienceLabel) {
        this.scienceLabel = scienceLabel;
    }

    public Label getGoldLabel() {
        return goldLabel;
    }

    public void setGoldLabel(Label goldLabel) {
        this.goldLabel = goldLabel;
    }

    public Label getHappinessLabel() {
        return happinessLabel;
    }

    public void setHappinessLabel(Label happinessLabel) {
        this.happinessLabel = happinessLabel;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public ArrayList<Circle> getTileFeatures() {
        return tileFeatures;
    }

    public void clear() {
        tiles.clear();
        tileFeatures.clear();
    }

    public ArrayList<Circle> getUnits() {
        return units;
    }
}
