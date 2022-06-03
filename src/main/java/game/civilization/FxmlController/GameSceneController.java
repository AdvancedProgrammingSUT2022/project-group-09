package game.civilization.FxmlController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameMenuController;
import game.civilization.SceneController.MapController;
import game.civilization.SceneController.UnitsController;
import game.civilization.SceneModels.GameSceneDataBase;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable {
    @FXML
    private Label civilizationName;
    @FXML
    private Label scienceLabel;
    @FXML
    private Label goldLabel;
    @FXML
    private Label happinessLabel;
    @FXML
    private Button nextTurnButton;
    @FXML
    private Pane backPane;
    @FXML
    private Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        civilizationName.setText(GameDataBase.getCurrentCivilization().getName());
        setDataToGameSceneDataBase();
        MapController.getInstance().run();
        UnitsController.getInstance().run();
        MapMovement.getInstance().run();
        loadPane();
    }

    private void clearPane() {
        pane.getChildren().clear();
    }

    private void loadPane() {
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getTiles());
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getCityIcons());
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getTileFeatures());
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getUnits());
    }

    private void setDataToGameSceneDataBase() {
        GameSceneDataBase.getInstance().setGameSceneController(this);
        GameSceneDataBase.getInstance().setBackPane(backPane);
        GameSceneDataBase.getInstance().setPane(pane);
        GameSceneDataBase.getInstance().setGoldLabel(goldLabel);
        GameSceneDataBase.getInstance().setHappinessLabel(happinessLabel);
        GameSceneDataBase.getInstance().setScienceLabel(scienceLabel);
    }

    public void nextTurn(ActionEvent actionEvent) {
        GameSceneDataBase.getInstance().clear();
        clearPane();
        new GameMenuController().doNextTurn();
        GameDataBase.getCurrentCivilization().getMap().updateExploration();
        civilizationName.setText(GameDataBase.getCurrentCivilization().getName());
        MapController.getInstance().run();
        UnitsController.getInstance().run();
        loadPane();
    }

    public void refresh() {
        GameSceneDataBase.getInstance().clear();
        clearPane();
        GameDataBase.getCurrentCivilization().getMap().updateExploration();
        MapController.getInstance().run();
        UnitsController.getInstance().run();
        loadPane();
    }
}
