package game.civilization.SceneModels;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Timer;

public class GameSceneDataBase {
    private static GameSceneDataBase gameSceneDataBase = null;

    public static GameSceneDataBase getInstance() {
        if (gameSceneDataBase == null)
            gameSceneDataBase = new GameSceneDataBase();
        return gameSceneDataBase;
    }

    private GameSceneDataBase() {

    }

    private final ArrayList<Tile> tiles = new ArrayList<>();
    private Pane pane;

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
