package game.civilization.FxmlController.GameScenes.SceneController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.UnitController;
import game.civilization.Model.Units.Unit;
import game.civilization.Model.Units.UnitType;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.FxmlController.GameScenes.SceneModels.Tile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class SettlerController {
    private static SettlerController SettlerController = null;

    public static SettlerController getInstance() {
        if (SettlerController == null)
            SettlerController = new SettlerController();
        return SettlerController;
    }

    private SettlerController() {

    }

    public static final String res = """
            -fx-background-color:
                      linear-gradient(#ffd65b, #e68400),
                      linear-gradient(#ffef84, #f2ba44),
                      linear-gradient(#ffea6a, #ff9921),
                      linear-gradient(#ffe657 0%, #ff9921 50%, #ff9921 100%);

              -fx-text-fill: #654b00;
              -fx-font-weight: bold;
              -fx-font-size: 14px;
              -fx-font-family: "Baloo Bhaina";
              -fx-background-radius: 30;""".indent(2);

    public static final String res2 = """
            -fx-background-color:
                      linear-gradient(#ab0e30, #f62222),
                      linear-gradient(#ffef84, #f2ba44),
                      linear-gradient(#ffea6a, #00d3ff),
                      linear-gradient(#ffe657 0%, #574839 50%, #75c52d 100%);

              -fx-text-fill: #654b00;
              -fx-font-weight: bold;
              -fx-font-size: 14px;
              -fx-font-family: "Baloo Bhaina";
              -fx-background-radius: 30;""".indent(2);

    public Pane makeSettlerPane() {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(50);
        pane.setStyle(res2);
        Button foundCityButon = new Button();
        foundCityButon.setStyle(res);
        foundCityButon.setText("foundCity");
        foundCityButon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().foundCity());
                UnitsController.getInstance().setUnitClicked(false);
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });
        Button moveButon = new Button();
        moveButon.setStyle(res);
        moveButon.setLayoutY(30);
        moveButon.setText("move");
        moveButon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MovementController.getInstance().run();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });

        Button deleteButton = new Button();
        deleteButton.setStyle(res);
        deleteButton.setLayoutY(60);
        deleteButton.setText("delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().delete());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });

        Button wakeButton = new Button();
        wakeButton.setStyle(res);
        wakeButton.setLayoutY(90);
        wakeButton.setText("wake");
        wakeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().wake());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });

        Button doNothingButton = new Button();
        doNothingButton.setStyle(res);
        doNothingButton.setLayoutY(120);
        doNothingButton.setText("doNothing");
        doNothingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().doNothing());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });

        Button sleepButton = new Button();
        sleepButton.setStyle(res);
        sleepButton.setLayoutY(150);
        sleepButton.setText("sleep");
        sleepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().sleep());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });
        pane.getChildren().add(foundCityButon);
        pane.getChildren().add(deleteButton);
        pane.getChildren().add(doNothingButton);
        pane.getChildren().add(sleepButton);
        pane.getChildren().add(wakeButton);
        pane.getChildren().add(moveButon);
        ImageView logo = new ImageView((UnitType.SETTLER.getImage()));
        logo.setLayoutX(100);
        pane.getChildren().add(logo);
        Label label = new Label();
        label.setText(((Unit) (GameDataBase.getSelected())).showInfo());
        label.setLayoutY(180);
        pane.getChildren().add(label);
        return pane;
    }

    public void settlerClicked() {
        for (Circle unit : GameSceneDataBase.getInstance().getUnits()) {
            Unit unit1 = null;
            for (Tile tile : GameSceneDataBase.getInstance().getTiles()) {
                if (tile.getUnitCircle() == unit)
                    unit1 = tile.getTerrain().getCivilianUnit();
            }
            Unit finalUnit = unit1;
            if (finalUnit == null)
                continue;
            if (finalUnit.getMyType() != UnitType.SETTLER)
                continue;
            unit.setOnMouseClicked(mouseEvent -> {
                if (!UnitsController.getInstance().isUnitClicked()) {
                    if (!(finalUnit.getCivilization() == GameDataBase.getCurrentCivilization()))
                        return;
                    GameDataBase.setSelected(finalUnit);
                    UnitsController.getInstance().setUnitClicked(true);
                    UnitsController.getInstance().setUnitPanel(makeSettlerPane());
                    GameSceneDataBase.getInstance().getBackPane().getChildren().add(UnitsController.getInstance().getUnitPanel());
                } else {
                    UnitsController.getInstance().setUnitClicked(false);
                    GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                    UnitsController.getInstance().setUnitPanel(null);
                }
            });
        }

    }
}
