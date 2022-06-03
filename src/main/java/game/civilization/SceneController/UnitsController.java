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
        SettlerController.getInstance().settlerClicked();
    }


    public Pane getUnitPanel() {
        return unitPane;
    }

    public void setUnitPanel(Pane pane) {
        unitPane = pane;
    }

    public boolean isUnitClicked() {
        return unitClicked;
    }

    public void setUnitClicked(boolean unitClicked) {
        this.unitClicked = unitClicked;
    }
}

