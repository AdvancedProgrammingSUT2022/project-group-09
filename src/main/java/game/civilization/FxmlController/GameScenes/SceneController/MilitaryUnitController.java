package game.civilization.FxmlController.GameScenes.SceneController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.UnitController;
import game.civilization.Model.Units.Unit;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.FxmlController.GameScenes.SceneModels.Tile;
import game.civilization.Model.Units.UnitType;
import game.civilization.Model.Units.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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


    private Pane makeMilitaryUnitPanel() {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(50);
        pane.setStyle(res2);
        Button pillageButton = new Button();
        pillageButton.setStyle(res);
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
        moveButon.setLayoutY(30);
        moveButon.setStyle(res);
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
        deleteButton.setLayoutY(60);
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
        wakeButton.setLayoutY(90);
        wakeButton.setStyle(res);
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
        doNothingButton.setLayoutY(120);
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
        sleepButton.setLayoutY(150);
        sleepButton.setStyle(res);
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
        attackButton.setLayoutY(180);
        attackButton.setStyle(res);
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
        setupButton.setLayoutY(210);
        setupButton.setText("setup");
        setupButton.setStyle(res);
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
        garrisonButton.setLayoutY(240);
        garrisonButton.setText("garrison");
        garrisonButton.setStyle(res);
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
        fortifyButton.setLayoutY(270);
        fortifyButton.setText("fortify");
        fortifyButton.setStyle(res);
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
        fortifyUntilHealButton.setLayoutY(300);
        fortifyUntilHealButton.setStyle(res);
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
        alertButton.setLayoutY(330);
        alertButton.setText("alert");
        alertButton.setStyle(res);
        alertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(new UnitController().alert());
                GameSceneDataBase.getInstance().getGameSceneController().refresh();
                GameSceneDataBase.getInstance().getBackPane().getChildren().remove(UnitsController.getInstance().getUnitPanel());
                UnitsController.getInstance().setUnitPanel(null);
            }
        });


        Label label = new Label();
        label.setLayoutY(360);
        label.setText(((Unit) GameDataBase.getSelected()).showInfo());

        ImageView logo = new ImageView((((Unit) GameDataBase.getSelected()).getMyType().getImage()));
        logo.setLayoutX(100);

        pane.getChildren().add(logo);
        pane.getChildren().add(label);
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
