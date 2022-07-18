package game.civilization.FxmlController.GameScenes.SceneModels;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Main;
import game.civilization.Model.City;
import game.civilization.Model.TerrainFeatures.TerrainFeature;
import game.civilization.Model.Terrains.Terrain;
import game.civilization.Model.Terrains.TerrainState;
import game.civilization.Model.Units.MilitaryUnit;
import game.civilization.Model.Units.Unit;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Tile extends Polygon {
    public Terrain getTerrain() {
        return terrain;
    }

    private final Terrain terrain;
    private Circle unitCircle;
    private Circle militaryUnitCircle;

    public Tile(double x, double y, double dy, Terrain terrain) {
        this.terrain = terrain;
        double size = 100, v = Math.sqrt(3) / 2.0;
        super.getPoints().addAll(x, dy,
                x + size, dy,
                x + size * (3.0 / 2.0), dy + size * v,
                x + size, dy + size * Math.sqrt(3),
                x, dy + size * Math.sqrt(3),
                x - (size / 2.0), dy + size * v);
        if (GameDataBase.getCurrentCivilization().getMap().getTerrainStates()[terrain.getXPosition()][terrain.getYPosition()] == TerrainState.FOG_OF_WAR) {
            super.setFill(Color.GRAY);
            return;
        } else if (GameDataBase.getCurrentCivilization().getMap().getTerrainStates()[terrain.getXPosition()][terrain.getYPosition()] == TerrainState.KNOWN) {
            super.setFill(new ImagePattern(terrain.getType().getImage()));
            super.setEffect(new BoxBlur(5, 5, 7));
        } else
            super.setFill(new ImagePattern(terrain.getType().getImage()));
        if (terrain.isRuin()) {
            super.setEffect(new InnerShadow(99999, Color.HOTPINK));
//            List<Color> colors = new ArrayList<Color>();
//            for (int r = 0; r < 100; r++) colors.add(new Color(r / (float) 100, 1, 0, 1));
//            for (int g = 100; g > 0; g--) colors.add(new Color(1, g / (float) 100, 0, 1));
//            for (int b = 0; b < 100; b++) colors.add(new Color(1, 0, b / (float) 100, 1));
//            for (int r = 100; r > 0; r--) colors.add(new Color(r / (float) 100, 0, 1, 1));
//            for (int g = 0; g < 100; g++) colors.add(new Color(0, g / (float) 100, 1, 1));
//            for (int b = 100; b > 0; b--) colors.add(new Color(0, 1, b / (float) 100, 1));
//            colors.add(new Color(0, 1, 0, 1));
//            System.out.println(colors.size());
//            final int[] i = {0};
//            Timer timer = new Timer();
//            timer.scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//                    if (i[0] >= colors.size())
//                        i[0] = 0;
//                    System.out.println(i[0]);
//                    Color color = colors.get(i[0]);
//                    Tile.super.setEffect(new InnerShadow(99999, color));
//                    i[0] = i[0] + 1;
//                }
//            }, 0, 5);
        }
    }

    public void loadCityIcon() {
        if (!(terrain instanceof City))
            return;
        Circle circle = new Circle();
        circle.setCenterX((getPoints().get(4) + getPoints().get(10)) / 2);
        circle.setCenterY(getPoints().get(5));
        circle.setRadius(80);
        circle.setStrokeWidth(0);
        circle.setFill(new ImagePattern(new Image(Main.class.getResource("images/GamePictures/TerrainPicture/city.jpg").toExternalForm())));
        GameSceneDataBase.getInstance().getCityIcons().add(circle);
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

    public void loadUnit() {
        Unit unit = null;
        if (terrain.getMilitaryUnit() != null) {
            unit = terrain.getMilitaryUnit();
            Circle circle = new Circle();
            circle.setCenterX((getPoints().get(4) + getPoints().get(10)) / 2 - 35);
            circle.setCenterY(getPoints().get(5));
            circle.setStroke(Paint.valueOf("#FF0000"));
            circle.setStrokeWidth(2);
            circle.setFill(new ImagePattern(unit.getMyType().getImage()));
            circle.setRadius(35);
            this.militaryUnitCircle = circle;
            GameSceneDataBase.getInstance().getMilitaryUnits().add(circle);
        }
        if (terrain.getCivilianUnit() != null) {
            unit = terrain.getCivilianUnit();
            Circle circle = new Circle();
            circle.setCenterX((getPoints().get(4) + getPoints().get(10)) / 2 + 35);
            circle.setCenterY(getPoints().get(5));
            circle.setStroke(Paint.valueOf("#FF0000"));
            circle.setStrokeWidth(2);
            circle.setFill(new ImagePattern(unit.getMyType().getImage()));
            circle.setRadius(35);
            this.unitCircle = circle;
            GameSceneDataBase.getInstance().getUnits().add(circle);
        }
    }

    public Circle getUnitCircle() {
        return unitCircle;
    }

    public Circle getMilitaryUnitCircle() {
        return militaryUnitCircle;
    }

    public void setUnitCircle(Circle unitCircle) {
        this.unitCircle = unitCircle;
    }
}
