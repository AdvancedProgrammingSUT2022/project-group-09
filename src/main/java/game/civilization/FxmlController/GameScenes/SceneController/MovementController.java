package game.civilization.FxmlController.GameScenes.SceneController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.UnitController;
import game.civilization.Model.Coordination;
import game.civilization.Model.Units.Unit;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import javafx.event.EventHandler;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

import static javafx.scene.paint.Color.*;

public class MovementController {
    private static MovementController movementController = null;
    private ArrayList<Coordination> coordinations;


    public static MovementController getInstance() {
        if (movementController == null)
            movementController = new MovementController();
        return movementController;
    }

    private MovementController() {

    }

    public void run() {
        coordinations = new UnitController().findOneTurnCoordination((Unit) GameDataBase.getSelected());
        showAccessibleTile();
        setClicked();
    }

    private void showAccessibleTile() {
        for (Coordination coordination : coordinations) {
            coordination.getTerrain().getTile().setEffect(new InnerShadow(100, 1, 1, RED));
        }
    }

    private void setClicked() {
        for (Coordination coordination : coordinations) {
            coordination.getTerrain().getTile().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println(new UnitController().moveUnit(coordination));
                    finish();
                }
            });
        }
    }

    public void finish() {
        for (Coordination coordination : coordinations) {
            coordination.getTerrain().getTile().setEffect(null);
            coordination.getTerrain().getTile().setOnMouseClicked(null);
        }
        GameSceneDataBase.getInstance().getGameSceneController().refresh();
        coordinations = null;

    }
}
