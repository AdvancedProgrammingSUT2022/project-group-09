package game.civilization.SceneController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.UnitController;
import game.civilization.FxmlController.GameSceneController;
import game.civilization.MenuRegex.GameMenuRegex;
import game.civilization.Model.Units.Unit;
import game.civilization.SceneModels.GameSceneDataBase;
import game.civilization.SceneModels.Tile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class UnitsController {
    private static UnitsController UnitsController = null;
    private Pane unitPane = null;
    private boolean unitClicked = false;

    public static UnitsController getInstance() {
        if (UnitsController == null)
            UnitsController = new UnitsController();
        return UnitsController;
    }

    private UnitsController() {

    }

    public void run() {
        settlerClicked();
    }


    private Pane makeSettlerPane() {
        Pane pane = new Pane();
        pane.prefWidth(6000);
        pane.prefHeight(6000);
        pane.setLayoutX(0);
        pane.setLayoutY(50);
        pane.setStyle("-fx-background-color: #00ff59");
        Button foundCityButon = new Button();
        foundCityButon.setText("foundCity");
        foundCityButon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().foundCity());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(getSettlerPanel());
                setSettlerPanel(null);
            }
        });
        Button moveButon = new Button();
        moveButon.setLayoutY(20);
        moveButon.setText("move");
        moveButon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO
                System.out.println("gooz");
            }
        });

        Button deleteButton = new Button();
        deleteButton.setLayoutY(40);
        deleteButton.setText("delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().delete());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(getSettlerPanel());
                setSettlerPanel(null);
            }
        });

        Button wakeButton = new Button();
        wakeButton.setLayoutY(60);
        wakeButton.setText("wake");
        wakeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().wake());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(getSettlerPanel());
                setSettlerPanel(null);
            }
        });

        Button doNothingButton = new Button();
        doNothingButton.setLayoutY(80);
        doNothingButton.setText("doNothing");
        doNothingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().doNothing());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(getSettlerPanel());
                setSettlerPanel(null);
            }
        });

        Button sleepButton = new Button();
        sleepButton.setLayoutY(100);
        sleepButton.setText("sleep");
        sleepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().sleep());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(getSettlerPanel());
                setSettlerPanel(null);
            }
        });
        pane.getChildren().add(foundCityButon);
        pane.getChildren().add(deleteButton);
        pane.getChildren().add(doNothingButton);
        pane.getChildren().add(sleepButton);
        pane.getChildren().add(wakeButton);
        pane.getChildren().add(moveButon);
        return pane;
    }

    private void settlerClicked() {
        for (Circle unit : GameSceneDataBase.getInstance().getUnits()) {
            Unit unit1 = null;
            for (Tile tile : GameSceneDataBase.getInstance().getTiles()) {
                if (tile.getUnitCircle() == unit)
                    unit1 = tile.getTerrain().getCivilianUnit();
            }
            Unit finalUnit = unit1;
            unit.setOnMouseClicked(mouseEvent -> {
                if (!unitClicked) {
                    if (!(finalUnit.getCivilization() == GameDataBase.getCurrentCivilization()))
                        return;
                    GameDataBase.setSelected(finalUnit);
                    unitClicked = true;
                    setSettlerPanel(makeSettlerPane());
                    GameSceneDataBase.getInstance().getBackPane().getChildren().add(getSettlerPanel());
                } else {
                    unitClicked = false;
                    GameSceneDataBase.getInstance().getBackPane().getChildren().remove(getSettlerPanel());
                    setSettlerPanel(null);
                }
            });
        }

    }

    public Pane getSettlerPanel() {
        return unitPane;
    }

    public void setSettlerPanel(Pane terrainInfoPane) {
        unitPane = terrainInfoPane;
    }
}

