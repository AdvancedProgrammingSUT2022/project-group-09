package game.civilization.FxmlController.GameScenes.SceneController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.Main;
import game.civilization.Model.TechnologyPackage.TechnologyType;
import game.civilization.Model.Units.Unit;
import game.civilization.Model.Units.UnitType;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.Map;

public class UnitPanel {
    private static UnitPanel instance = null;
    private int state = 0;
    private Pane unitPane;
    private ImageView icon;

    private UnitPanel() {

    }

    public static UnitPanel getInstance() {
        if (instance == null)
            instance = new UnitPanel();
        return instance;
    }

    public void run() {
        GameSceneDataBase.getInstance().getBackPane().getChildren().remove(icon);
        clear();
        makeUnitIcon();
    }

    public void clear() {
        GameSceneDataBase.getInstance().getBackPane().getChildren().remove(unitPane);
    }

    private void makeUnitIcon() {
        ImageView imageView = new ImageView(new Image((Main.class.getResource("images/GamePictures/mainIcons/unit.png").toExternalForm())));
        imageView.setX(430);
        imageView.setY(2);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        icon = imageView;
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (state == 0) {
                    makeUnitPanel();
                    state = 1;
                } else {
                    clear();
                    state = 0;
                }
            }
        });
        GameSceneDataBase.getInstance().getBackPane().getChildren().add(imageView);
    }

    private void makeUnitPanel() {
        Pane pane = new Pane();
        unitPane = pane;
        Rectangle rectangle = new Rectangle();
        rectangle.setStyle("-fx-arc-height: 20;-fx-arc-width: 20;-fx-fill: linear-gradient(#ffda4a, #ffd700)");
        rectangle.setX(0);
        rectangle.setY(0);
        rectangle.setWidth(220);
        rectangle.setHeight(60);
        pane.getChildren().add(rectangle);
        double y = 10, x = 35;
        pane.minWidth(6000);
        pane.minHeight(6000);
        pane.maxWidth(6000);
        pane.maxHeight(6000);
        pane.prefHeight(6000);
        pane.prefWidth(6000);
        pane.setLayoutX(icon.getX());
        pane.setLayoutY(50);
        Label label3 = new Label("*units*");
        label3.setLayoutX(x + 55);
        label3.setLayoutY(y);
        label3.setFont(new Font("Baloo Bhaina Regular", 14));
        pane.getChildren().add(label3);
        for (Unit unit : GameDataBase.getCurrentCivilization().getUnits()) {
            Label label1 = new Label(unit.getMyType().toString() + " -> is work done: " + unit.isWorkDone());
            label1.setLayoutY(y += 35);
            label1.setPrefHeight(30);
            label1.setMinHeight(30);
            label1.setLayoutX(x - 10);
            label1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    GameDataBase.setSelected(unit);
                    UnitsController.getInstance().setUnitClicked(true);
                    if (unit.getMyType() == UnitType.SETTLER)
                        UnitsController.getInstance().setUnitPanel(SettlerController.getInstance().makeSettlerPane());
                    else if (unit.getMyType() == UnitType.WORKER)
                        UnitsController.getInstance().setUnitPanel(WorkerController.getInstance().makeWorkerPanel());
                    else
                        UnitsController.getInstance().setUnitPanel(MilitaryUnitController.getInstance().makeMilitaryUnitPanel());
                    GameSceneDataBase.getInstance().getBackPane().getChildren().add(UnitsController.getInstance().getUnitPanel());
                }
            });
            label1.setFont(new Font("Baloo Bhaina Regular", 13));
            pane.getChildren().add(label1);
            rectangle.setHeight(rectangle.getHeight() + 40);
        }
        GameSceneDataBase.getInstance().getBackPane().getChildren().add(pane);
    }
}
