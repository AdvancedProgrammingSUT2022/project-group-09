package game.civilization.FxmlController.GameScenes.SceneController;

import game.civilization.Controller.GameControllerPackage.CombatController;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.UnitController;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.Model.City;
import game.civilization.Model.Coordination;
import game.civilization.Model.Terrains.Terrain;
import game.civilization.Model.Units.Unit;
import javafx.event.EventHandler;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

import static javafx.scene.paint.Color.*;

public class AttackController {
    private static AttackController attackController = null;
    private ArrayList<Coordination> coordinations;


    public static AttackController getInstance() {
        if (attackController == null)
            attackController = new AttackController();
        return attackController;
    }

    private AttackController() {

    }

    private void setCoordinations() {
        coordinations = new ArrayList<>();
        for (Terrain[] terrains : GameDataBase.getMainMap().getTerrains()) {
            for (Terrain terrain : terrains) {
                if (terrain.getCivilianUnit() != null)
                    if (terrain.getCivilianUnit().getCivilization() != GameDataBase.getCurrentCivilization())
                        coordinations.add(terrain.getCoordination());
                if (terrain.getMilitaryUnit() != null)
                    if (terrain.getMilitaryUnit().getCivilization() != GameDataBase.getCurrentCivilization())
                        coordinations.add(terrain.getCoordination());
                if (terrain instanceof City)
                    if (((City) terrain).getCivilization() != GameDataBase.getCurrentCivilization())
                        coordinations.add(terrain.getCoordination());
            }
        }
        coordinations.add(((Unit) GameDataBase.getSelected()).getTerrain().getCoordination());
    }

    public void run() {
        setCoordinations();
        showAccessibleTile();
        setClicked();
    }

    private void showAccessibleTile() {
        for (Coordination coordination : coordinations) {
            coordination.getTerrain().getTile().setEffect(new InnerShadow(100, 1, 1, DARKBLUE));
        }
    }

    private void setClicked() {
        for (Coordination coordination : coordinations) {
            coordination.getTerrain().getTile().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println(new CombatController().militaryAttack(coordination));
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
