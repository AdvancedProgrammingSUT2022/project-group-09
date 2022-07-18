package game.civilization.FxmlController.GameScenes.SceneController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameMenuController;
import game.civilization.Controller.GameControllerPackage.UnitController;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.FxmlController.GameScenes.SceneModels.Tile;
import game.civilization.MenuRegex.GameMenuRegex;
import game.civilization.Model.Improvements.Improvement;
import game.civilization.Model.Terrains.Terrain;
import game.civilization.Model.Units.Unit;
import game.civilization.Model.Units.UnitType;
import game.civilization.Model.Units.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class WorkerController {
    private static WorkerController WorkerController = null;

    public static WorkerController getInstance() {
        if (WorkerController == null)
            WorkerController = new WorkerController();
        return WorkerController;
    }

    private WorkerController() {

    }

    private static final String res = """
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

    private static final String res2 = """
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

    private Pane makeWorkerPanel() {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(50);
        pane.setStyle(res2);
        Button moveButon = new Button();
        moveButon.setLayoutY(0);
        moveButon.setText("move");
        moveButon.setStyle(res);
        moveButon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MovementController.getInstance().run();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });

        Button deleteButton = new Button();
        deleteButton.setLayoutY(30);
        deleteButton.setStyle(res);
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
        wakeButton.setLayoutY(60);
        wakeButton.setText("wake");
        wakeButton.setStyle(res);
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
        doNothingButton.setLayoutY(90);
        doNothingButton.setStyle(res);
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
        sleepButton.setLayoutY(120);
        sleepButton.setText("sleep");
        sleepButton.setStyle(res);
        sleepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().sleep());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });
/////
        ArrayList<Button> workButtons = new ArrayList<>();
        int y = 120;
        for (Improvement improvement : Improvement.getAllImprovements()) {
            if (improvement.checkIsPossible((((Worker) GameDataBase.getSelected()).getTerrain()))) {
                y += 30;
                Button button = new Button();
                button.setStyle(res);
                button.setLayoutY(y);
                button.setText(improvement.name());
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (improvement == Improvement.ROAD)
                            System.out.println(new UnitController().buildRoad());
                        else if (improvement == Improvement.FARM)
                            System.out.println(new UnitController().buildFarm());
                        else if (improvement == Improvement.MINE)
                            System.out.println(new UnitController().buildMine());
                        else if (improvement == Improvement.TRADINGPOST)
                            System.out.println(new UnitController().buildTradingPost());
                        else if (improvement == Improvement.LUMBERMILL)
                            System.out.println(new UnitController().buildLumberMill());
                        else if (improvement == Improvement.PASTURE)
                            System.out.println(new UnitController().buildPasture());
                        else if (improvement == Improvement.CAMP)
                            System.out.println(new UnitController().buildCamp());
                        else if (improvement == Improvement.PLANTATION)
                            System.out.println(new UnitController().buildPlantation());
                        else if (improvement == Improvement.QUARRY)
                            System.out.println(new UnitController().buildQuarry());
                        else if (improvement == Improvement.REMOVE_FOREST)
                            System.out.println(new UnitController().removeForest());
                        else if (improvement == Improvement.REMOVE_JUNGLE)
                            System.out.println(new UnitController().removeJungle());
                        else if (improvement == Improvement.REMOVE_ROUTE)
                            System.out.println(new UnitController().removeRoute());
                        else if (improvement == Improvement.REMOVE_MARSH)
                            System.out.println(new UnitController().removeMarsh());
                        else if (improvement == Improvement.REPAIR)
                            System.out.println(new UnitController().repair());
                    }
                });
                workButtons.add(button);
            }
        }
        Label label = new Label();
        label.setLayoutY(y + 30);
        label.setText(((Worker) GameDataBase.getSelected()).showInfo());

        ImageView logo = new ImageView((UnitType.WORKER.getImage()));
        logo.setLayoutX(100);

        pane.getChildren().add(logo);
        pane.getChildren().add(label);
        pane.getChildren().addAll(workButtons);
        pane.getChildren().add(deleteButton);
        pane.getChildren().add(doNothingButton);
        pane.getChildren().add(sleepButton);
        pane.getChildren().add(wakeButton);
        pane.getChildren().add(moveButon);
        return pane;
    }

    public void workerClicked() {
        for (Circle unit : GameSceneDataBase.getInstance().getUnits()) {
            Unit unit1 = null;
            for (Tile tile : GameSceneDataBase.getInstance().getTiles()) {
                if (tile.getUnitCircle() == unit)
                    unit1 = tile.getTerrain().getCivilianUnit();
            }
            Unit finalUnit = unit1;
            if (finalUnit == null)
                continue;
            if (finalUnit.getMyType() != UnitType.WORKER)
                continue;
            unit.setOnMouseClicked(mouseEvent -> {
                if (!UnitsController.getInstance().isUnitClicked()) {
                    if (!(finalUnit.getCivilization() == GameDataBase.getCurrentCivilization()))
                        return;
                    GameDataBase.setSelected(finalUnit);
                    UnitsController.getInstance().setUnitClicked(true);
                    UnitsController.getInstance().setUnitPanel(makeWorkerPanel());
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
