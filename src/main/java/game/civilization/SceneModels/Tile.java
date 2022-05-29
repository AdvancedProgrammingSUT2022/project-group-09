package game.civilization.SceneModels;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Model.TerrainFeatures.TerrainFeature;
import game.civilization.Model.Terrains.Terrain;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

public class Tile extends Polygon {
    private final Terrain terrain;

    public Tile(double x, double y, double dy, Terrain terrain) {
        this.terrain = terrain;
        double size = 100, v = Math.sqrt(3) / 2.0;
        super.getPoints().addAll(x, dy,
                x + size, dy,
                x + size * (3.0 / 2.0), dy + size * v,
                x + size, dy + size * Math.sqrt(3),
                x, dy + size * Math.sqrt(3),
                x - (size / 2.0), dy + size * v);
        super.setFill(new ImagePattern(terrain.getType().getImage()));
    }

    public void loadTerrainFeature() {
        TerrainFeature terrainFeature;
        if (terrain.getTerrainFeatures().size() == 2) {
            ArrayList<TerrainFeature> terrainFeatures = new ArrayList<>(terrain.getTerrainFeatures());
            terrainFeatures.remove(TerrainFeature.RIVER);
            terrainFeature = terrainFeatures.get(0);
        } else if (terrain.getTerrainFeatures().size() == 1) {
            terrainFeature = terrain.getTerrainFeatures().get(0);
            if (terrainFeature == TerrainFeature.RIVER)
                return;
        } else
            return;
        Circle circle = new Circle();
        circle.setCenterX(getPoints().get(0) + 10);
        circle.setCenterY(getPoints().get(1) + 20);
        circle.setRadius(20);
        circle.setStroke(Paint.valueOf("#F4B800"));
        circle.setStrokeWidth(2);
        circle.setFill(new ImagePattern(terrainFeature.getImage()));
        GameSceneDataBase.getInstance().getTileFeatures().add(circle);
    }

}
