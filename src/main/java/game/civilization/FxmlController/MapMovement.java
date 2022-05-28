package game.civilization.FxmlController;

import game.civilization.SceneModels.GameSceneDataBase;
import game.civilization.SceneModels.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class MapMovement {
    private static MapMovement instance;

    public static MapMovement getInstance() {
        if (instance == null)
            instance = new MapMovement();
        return instance;
    }

    private MapMovement() {
    }

    private final BooleanProperty wPressed = new SimpleBooleanProperty();
    private final BooleanProperty aPressed = new SimpleBooleanProperty();
    private final BooleanProperty sPressed = new SimpleBooleanProperty();
    private final BooleanProperty dPressed = new SimpleBooleanProperty();

    public void run() {
        movementSetup();
        timer.start();
    }

    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            if (wPressed.get() && GameSceneDataBase.getInstance().getTiles().get(0).getLayoutY() < 200) {
                for (Tile tile : GameSceneDataBase.getInstance().getTiles())
                    tile.goDown();
            }

            if (sPressed.get() && GameSceneDataBase.getInstance().getTiles().get(0).getLayoutY() > -1400) {
                for (Tile tile : GameSceneDataBase.getInstance().getTiles())
                    tile.goUp();
            }

            if (aPressed.get() && GameSceneDataBase.getInstance().getTiles().get(0).getLayoutX() < 200) {
                for (Tile tile : GameSceneDataBase.getInstance().getTiles())
                    tile.goRight();
            }

            if (dPressed.get() && GameSceneDataBase.getInstance().getTiles().get(0).getLayoutX() > -200) {
                for (Tile tile : GameSceneDataBase.getInstance().getTiles())
                    tile.goLeft();
            }
        }
    };


    private void movementSetup() {
        Pane pane = GameSceneDataBase.getInstance().getPane();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                pane.requestFocus();
            }
        });
        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                wPressed.set(true);
            }

            if (e.getCode() == KeyCode.LEFT) {
                aPressed.set(true);
            }

            if (e.getCode() == KeyCode.DOWN) {
                sPressed.set(true);
            }

            if (e.getCode() == KeyCode.RIGHT) {
                dPressed.set(true);
            }
        });

        pane.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.UP) {
                wPressed.set(false);
            }

            if (e.getCode() == KeyCode.LEFT) {
                aPressed.set(false);
            }

            if (e.getCode() == KeyCode.DOWN) {
                sPressed.set(false);
            }

            if (e.getCode() == KeyCode.RIGHT) {
                dPressed.set(false);
            }
        });
    }

}
