package game.civilization.FxmlController.GameScenes.SceneController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.UnitController;
import game.civilization.Model.Units.Unit;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.FxmlController.GameScenes.SceneModels.Tile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class MilitaryUnitController {
    private static MilitaryUnitController MilitaryUnitController = null;

    public static MilitaryUnitController getInstance() {
        if (MilitaryUnitController == null)
            MilitaryUnitController = new MilitaryUnitController();
        return MilitaryUnitController;
    }

    private MilitaryUnitController() {

    }

    private Pane makeMilitaryUnitPanel() {
        Pane pane = new Pane();
        pane.setMinSize(200, 400);
        pane.setMaxSize(200, 400);
        pane.setLayoutX(0);
        pane.setLayoutY(50);
        pane.setStyle("-fx-background-color: #00ff59");
        Button pillageButton = new Button();
        pillageButton.setText("Pilage");
        pillageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().pillage());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });


        Button moveButon = new Button();
        moveButon.setLayoutY(20);
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
        deleteButton.setLayoutY(40);
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
        doNothingButton.setLayoutY(80);
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
        sleepButton.setLayoutY(100);
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

        Button attackButton = new Button();
        attackButton.setLayoutY(120);
        attackButton.setText("attack");
        attackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AttackController.getInstance().run();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });


        Button setupButton = new Button();
        setupButton.setLayoutY(140);
        setupButton.setText("setup");
        setupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().setUp());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });

        Button garrisonButton = new Button();
        garrisonButton.setLayoutY(160);
        garrisonButton.setText("garrison");
        garrisonButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().garrison());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });

        Button fortifyButton = new Button();
        fortifyButton.setLayoutY(180);
        fortifyButton.setText("fortify");
        fortifyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().fortify());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });


        Button fortifyUntilHealButton = new Button();
        fortifyUntilHealButton.setLayoutY(200);
        fortifyUntilHealButton.setText("fortifyUntilHeal");
        fortifyUntilHealButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().fortifyHeal());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });

        Button alertButton = new Button();
        alertButton.setLayoutY(220);
        alertButton.setText("alert");
        alertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().alert());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });


        pane.getChildren().add(alertButton);
        pane.getChildren().add(fortifyButton);
        pane.getChildren().add(fortifyUntilHealButton);
        pane.getChildren().add(setupButton);
        pane.getChildren().add(garrisonButton);
        pane.getChildren().add(pillageButton);
        pane.getChildren().add(attackButton);
        pane.getChildren().add(deleteButton);
        pane.getChildren().add(doNothingButton);
        pane.getChildren().add(sleepButton);
        pane.getChildren().add(wakeButton);
        pane.getChildren().add(moveButon);
        return pane;
    }

    public void MilitaryUnitClicked() {
        for (Circle unit : GameSceneDataBase.getInstance().getMilitaryUnits()) {
            Unit unit1 = null;
            for (Tile tile : GameSceneDataBase.getInstance().getTiles()) {
                if (tile.getMilitaryUnitCircle() == unit)
                    unit1 = tile.getTerrain().getMilitaryUnit();
            }
            Unit finalUnit = unit1;
            if (finalUnit == null)
                continue;
            unit.setOnMouseClicked(mouseEvent -> {
                if (!UnitsController.getInstance().isUnitClicked()) {
                    if (!(finalUnit.getCivilization() == GameDataBase.getCurrentCivilization()))
                        return;
                    GameDataBase.setSelected(finalUnit);
                    UnitsController.getInstance().setUnitClicked(true);
                    UnitsController.getInstance().setUnitPanel(makeMilitaryUnitPanel());
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
