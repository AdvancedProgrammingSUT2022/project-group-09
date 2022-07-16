package game.civilization.FxmlController.GameScenes.SceneController;

import javafx.scene.layout.Pane;

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
        MilitaryUnitController.getInstance().MilitaryUnitClicked();
        WorkerController.getInstance().workerClicked();
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

