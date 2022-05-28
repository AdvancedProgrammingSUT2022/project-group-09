package game.civilization.SceneModels;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class Tile extends Polygon {
    private final int movementVariable = 30;

    public Tile(double x, double y, double dy) {
        double size = 100, v = Math.sqrt(3) / 2.0;
        super.getPoints().addAll(x, dy,
                x + size, dy,
                x + size * (3.0 / 2.0), dy + size * v,
                x + size, dy + size * Math.sqrt(3),
                x, dy + size * Math.sqrt(3),
                x - (size / 2.0), dy + size * v);
        super.setFill(Paint.valueOf("#ffffff"));
        super.setStrokeWidth(2);
        super.setStroke(Paint.valueOf("#FF2D00"));
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
