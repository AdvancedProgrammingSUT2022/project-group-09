package game.civilization.FxmlController.GameScenes;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.FxmlController.GameScenes.SceneController.*;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.FxmlController.MapMovement;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class StreamSceneController implements Initializable {
    public Pane backPane;
    public Pane pane;
    public Label turnLabel;
    public Label civilizationName;
    public TextArea notification;
    public ImageView demographic;
    public ImageView currentTech;
    public Label scienceLabel;
    public Label happinessLabel;
    public Label goldLabel;
    public Label year;
    public Pane cityButtonsPane;
    public Label currentTechTurn;
    public ImageView military;
    public static StreamSceneController streamSceneController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameSceneDataBase.getInstance().setGameSceneController(new GameSceneController());
        setDataToGameSceneDataBase();
        MapController.getInstance().run();
        MapMovement.getInstance().run();
        loadPane();
        refresh();
        StreamSceneController.streamSceneController = this;
    }

    private void setDataToGameSceneDataBase() {
        GameSceneDataBase.getInstance().setYear(year);
        GameSceneDataBase.getInstance().setBackPane(backPane);
        GameSceneDataBase.getInstance().setPane(pane);
        GameSceneDataBase.getInstance().setGoldLabel(goldLabel);
        GameSceneDataBase.getInstance().setHappinessLabel(happinessLabel);
        GameSceneDataBase.getInstance().setScienceLabel(scienceLabel);
        GameSceneDataBase.getInstance().setCityButtonsPane(cityButtonsPane);
        GameSceneDataBase.getInstance().setCurrentTechTurn(currentTechTurn);
        GameSceneDataBase.getInstance().setCurrentTech(currentTech);
    }

    private void clearPane() {
        pane.getChildren().clear();
        turnLabel.setText("");
    }

    private void loadPane() {
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getTiles());
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getCityIcons());
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getTileFeatures());
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getUnits());
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getMilitaryUnits());
    }

    public void refresh() {
        civilizationName.setText(GameDataBase.getCurrentCivilization().getName());
        GameSceneDataBase.getInstance().getYear().setText("year : " + GameDataBase.getYear());
        GameSceneDataBase.getInstance().clear();
        clearPane();
        GameDataBase.getCurrentCivilization().getMap().setAllVisible();
        MapController.getInstance().run();
        loadPane();
    }
}
