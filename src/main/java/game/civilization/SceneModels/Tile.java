package game.civilization.SceneModels;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Model.Terrains.Terrain;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class Tile extends Polygon {
    private final int movementVariable = 30;
    private Terrain terrain;

    public Tile(double x, double y, double dy, Terrain terrain) {
        this.terrain = terrain;
        double size = 100, v = Math.sqrt(3) / 2.0;
        super.getPoints().addAll(x, dy,
                x + size, dy,
                x + size * (3.0 / 2.0), dy + size * v,
                x + size, dy + size * Math.sqrt(3),
                x, dy + size * Math.sqrt(3),
                x - (size / 2.0), dy + size * v);
        super.setStrokeWidth(10);
        super.setStroke(Paint.valueOf("#FF2D00"));
        super.setFill(new ImagePattern(terrain.getType().getImage()));
    }

    public void goUp() {
        this.setLayoutY(this.getLayoutY() - movementVariable);
    }

    public void goDown() {
        this.setLayoutY(this.getLayoutY() + movementVariable);
    }


    public void goLeft() {
        this.setLayoutX(this.getLayoutX() - movementVariable);
    }

    public void goRight() {
        this.setLayoutX(this.getLayoutX() + movementVariable);
    }
}
