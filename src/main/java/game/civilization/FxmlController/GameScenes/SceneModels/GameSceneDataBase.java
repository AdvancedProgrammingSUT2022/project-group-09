package game.civilization.FxmlController.GameScenes.SceneModels;

import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.FxmlController.BuildMapController;
import game.civilization.FxmlController.GameScenes.GameSceneController;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.control.ScrollPane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class GameSceneDataBase {
    private static GameSceneDataBase gameSceneDataBase = null;

    public Pane getBackPane() {
        return backPane;
    }

    public void setBackPane(Pane backPane) {
        this.backPane = backPane;
    }

    public Pane getCityDetailsPane() {
        return this.cityDetailsPane;
    }

    public void setCityDetailsPane(Pane cityDetailsPane) {
        this.cityDetailsPane = cityDetailsPane;
    }

    public Pane getCityButtonsPane() {
        return this.cityButtonsPane;
    }

    public void setCityButtonsPane(Pane cityButtonsPane) {
        this.cityButtonsPane = cityButtonsPane;
    }

    public void setScrollPane(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public ScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public static GameSceneDataBase getInstance() {
        if (gameSceneDataBase == null)
            gameSceneDataBase = new GameSceneDataBase();
        return gameSceneDataBase;
    }

    private GameSceneDataBase() {
    }

    private GameSceneController gameSceneController;
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private final ArrayList<Circle> tileFeatures = new ArrayList<>();
    private final ArrayList<Circle> units = new ArrayList<>();
    private final ArrayList<Circle> militaryUnits = new ArrayList<>();
    private final ArrayList<Circle> cityIcons = new ArrayList<>();
    private Pane pane;
    private Pane backPane = null;
    private Pane cityButtonsPane;
    private Pane cityDetailsPane;
    private ScrollPane scrollPane;
    private Label scienceLabel;
    private Label goldLabel;
    private Label happinessLabel;
    private Integer score;
    private Label year;
    private BuildMapController buildMapController;


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
        units.clear();
        militaryUnits.clear();
        cityIcons.clear();
    }

    public ArrayList<Circle> getUnits() {
        return units;
    }

    public ArrayList<Circle> getMilitaryUnits() {
        return militaryUnits;
    }

    public ArrayList<Circle> getCityIcons() {
        return cityIcons;
    }

    public GameSceneController getGameSceneController() {
        return gameSceneController;
    }

    public void setGameSceneController(GameSceneController gameSceneController) {
        this.gameSceneController = gameSceneController;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Label getYear() {
        return year;
    }

    public void setYear(Label year) {
        this.year = year;
    }

    public BuildMapController getBuildMapController() {
        return buildMapController;
    }

    public void setBuildMapController(BuildMapController buildMapController) {
        this.buildMapController = buildMapController;
    }
}
