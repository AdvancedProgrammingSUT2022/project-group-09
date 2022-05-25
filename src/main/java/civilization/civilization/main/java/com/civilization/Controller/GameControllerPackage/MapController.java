package civilization.civilization.main.java.com.civilization.Controller.GameControllerPackage;

import java.util.ArrayList;
import java.util.regex.Matcher;

import civilization.civilization.main.java.com.civilization.Model.ConsoleColors;
import civilization.civilization.main.java.com.civilization.Model.Map;
import civilization.civilization.main.java.com.civilization.Model.Pair;
import civilization.civilization.main.java.com.civilization.Model.Improvements.Improvement;
import civilization.civilization.main.java.com.civilization.Model.Resources.Resource;
import civilization.civilization.main.java.com.civilization.Model.TechnologyPackage.TechnologyType;
import civilization.civilization.main.java.com.civilization.Model.TerrainFeatures.TerrainFeature;
import civilization.civilization.main.java.com.civilization.Model.Terrains.Terrain;
import civilization.civilization.main.java.com.civilization.Model.Terrains.TerrainState;
import civilization.civilization.main.java.com.civilization.Model.Terrains.TerrainType;

public class MapController {

    private Terrain[][] terrains;
    private TerrainState[][] terrainStates;

    public MapController(Terrain[][] terrains, TerrainState[][] terrainStates) {
        this.terrains = terrains;
        this.terrainStates = terrainStates;
    }

    public MapController() {

    }

    private String getBackgroundColor(Terrain terrain) {
        TerrainType type = terrain.getType();
        if (type == TerrainType.DESERT) {
            return ConsoleColors.BROWN_BACKGROUND;
        } else if (type == TerrainType.GRASSLLAND) {
            return ConsoleColors.LIGHTGREEN_BACKGROUND;
        } else if (type == TerrainType.HILLS) {
            return ConsoleColors.DARKGREEN_BACKGROUND;
        } else if (type == TerrainType.MOUNTAIN) {
            return ConsoleColors.DARKBROWN_BACKGROUND;
        } else if (type == TerrainType.OCEAN) {
            return ConsoleColors.BLUE_BACKGROUND;
        } else if (type == TerrainType.PLAIN) {
            return ConsoleColors.GREEN_BACKGROUND;
        } else if (type == TerrainType.SNOW) {
            return ConsoleColors.WHITE_BACKGROUND;
        } else if (type == TerrainType.TUNDRA) {
            return ConsoleColors.DARKRED_BACKGROUND;
        }
        return ConsoleColors.RESET;
    }

    private void drawHex(String[][] mapString, int istart, int jstart) {
        for (int k = 2; k > -1; k--) {
            mapString[istart + 2 - k][jstart + k] = "/";
            mapString[istart + 2 - k][jstart + 10 - k] = "\\";
            mapString[istart + 5 - k][jstart + 2 - k] = "\\";
            mapString[istart + 5 - k][jstart + 8 + k] = "/";
        }
    }

    private void drawMainDetails(String[][] mapString, int istart, int jstart, int xCenter, int yCenter,
            String backgroundColor) {
        for (int k = 2; k > -1; k--) {
            for (int z = jstart + k + 1; z < jstart + k + 1 + 5 + 4 - 2 * k; z++) {
                mapString[istart + 2 - k][z] = backgroundColor + " " + ConsoleColors.RESET;
                mapString[istart + 3 + k][z] = backgroundColor + " " + ConsoleColors.RESET;
            }
        }

        if (xCenter / 10 != 0)
            mapString[istart + 2][jstart + 3] = ConsoleColors.BLACK + backgroundColor + xCenter / 10
                    + ConsoleColors.RESET;
        mapString[istart + 2][jstart + 4] = ConsoleColors.BLACK + backgroundColor + xCenter % 10 + ConsoleColors.RESET;
        mapString[istart + 2][jstart + 5] = ConsoleColors.BLACK + backgroundColor + "," + ConsoleColors.RESET;
        if (yCenter / 10 != 0)
            mapString[istart + 2][jstart + 6] = ConsoleColors.BLACK + backgroundColor + yCenter / 10
                    + ConsoleColors.RESET;
        mapString[istart + 2][jstart + 7] = ConsoleColors.BLACK + backgroundColor + yCenter % 10 + ConsoleColors.RESET;
        for (int k = 0; k < 5; k++) {
            mapString[istart + 5][jstart + 3 + k] = backgroundColor + "_" + ConsoleColors.RESET;
        }
    }

    private void drawMap(String[][] mapString, int x, int y) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                mapString[2][11 + j] = "_";
                mapString[2][27 + j] = "_";
                mapString[2][43 + j] = "_";
            }
        }

        int remainder = 0;
        if (y % 2 == 1)
            remainder = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                String backgroundColor;
                if (j % 2 == 1)
                    backgroundColor = getBackgroundColor(terrains[x + i + remainder][y + j]);// inja bug dare az size
                // ararye mizane biroon
                else
                    backgroundColor = getBackgroundColor(terrains[x + i][y + j]);
                // before entering this function we have checked if it's out of bonds or not
                int istart = i * 6;
                int jstart = j * 8;
                if (j % 2 == 1) {
                    istart += 3;
                }

                drawHex(mapString, istart, jstart);

                if (j % 2 == 1) {
                    if (terrainStates[x + i + remainder][y + j] == TerrainState.FOG_OF_WAR) {
                        backgroundColor = ConsoleColors.GRAY_BACKGROUND;
                    }
                } else if (terrainStates[x + i][y + j] == TerrainState.FOG_OF_WAR) {
                    backgroundColor = ConsoleColors.GRAY_BACKGROUND;
                }

                if (j % 2 == 1)
                    drawMainDetails(mapString, istart, jstart, x + i + remainder, y + j, backgroundColor);
                else
                    drawMainDetails(mapString, istart, jstart, x + i, y + j, backgroundColor);

                if (j % 2 == 0 & terrainStates[x + i][y + j] == TerrainState.VISIBLE) {
                    // TODO set name in civilization constructor
                    if (terrains[x + i][y + j].getCivilization() == null) {
                        mapString[istart + 1][jstart + 5] = backgroundColor + " " + ConsoleColors.RESET;
                    } else {
                        mapString[istart + 1][jstart + 5] = backgroundColor
                                + terrains[x + i][y + j].getCivilization().getName().charAt(0) + ConsoleColors.RESET;
                    }
                } else {
                    if (terrains[x + i + remainder][y + j].getCivilization() == null) {
                        mapString[istart + 1][jstart + 5] = backgroundColor + " " + ConsoleColors.RESET;
                    } else {
                        mapString[istart + 1][jstart + 5] = backgroundColor
                                + terrains[x + i + remainder][y + j].getCivilization().getName().charAt(0)
                                + ConsoleColors.RESET;
                    }
                }
            }
        }

    }

    private int handelXBoundaries(int x, int y) {
        if (x > Map.getRow() - 3)
            return Map.getRow() - 3;
        if (x < 0)
            return 0;
        if (y % 2 == 1 && x > Map.getRow() - 4)
            return Map.getRow() - 4;
        return x;
    }

    private int handelYBoundaries(int y) {
        if (y > Map.getColumn() - 6)
            return Map.getColumn() - 6;
        if (y < 0)
            return 0;
        return y;
    }

    public boolean isValidTerran(int x, int y) {
        return x < Map.getRow() && x >= 0 && y < Map.getColumn() && y >= 0;
    }

    public String showMap(int x, int y) {
        x = handelXBoundaries(x, y);
        y = handelYBoundaries(y);

        if (!isValidTerran(x, y)) {
            return "ERROR x: " + x + " , y: " + y + " in show map is invalid";
        }

        // creating mapString
        String[][] mapString = new String[21][51];
        for (int i = 0; i < mapString.length; i++) {
            for (int j = 0; j < mapString[i].length; j++) {
                mapString[i][j] = " ";
            }
        }

        drawMap(mapString, x, y);

        StringBuilder mapStringBuilder = new StringBuilder();
        for (int i = 0; i < mapString.length; i++) {
            for (int j = 0; j < mapString[i].length; j++) {
                mapStringBuilder.append(mapString[i][j]);
            }
            mapStringBuilder.append("\n");
        }

        return mapStringBuilder.toString();
    }

    public String showMap(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        return showMap(x, y);
    }

    public void showRivers(StringBuilder stringBuilder, Terrain terrain) {
        if (terrain.getTerrainFeatures().contains(TerrainFeature.RIVER)) {
            stringBuilder.append("this terrain has a river with following terrains:\n");
            for (Terrain adjTerrain : terrain.getSurroundingTerrain()) {
                if (adjTerrain.getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                    stringBuilder.append(
                            "terrain on x: " + adjTerrain.getXPosition() + " y: " + adjTerrain.getYPosition() + "\n");
                }
            }
        }
    }

    private void showVisibleDetails(StringBuilder stringBuilder, Terrain terrain) {
        if (terrain.getCivilianUnit() == null) {
            stringBuilder.append("there is no civilization unit in this terrain\n");
        } else {
            stringBuilder.append("civilzation unit: " + terrain.getCivilianUnit().getMyType() + " belonging to: "
                    + terrain.getCivilianUnit().getCivilization().getName() + "\n");
        }
        if (terrain.getMilitaryUnit() == null) {
            stringBuilder.append("there is no military unit in this terrain\n");
        } else {
            stringBuilder.append("military unit: " + terrain.getMilitaryUnit().getMyType() + " belonging to: " +
                    terrain.getMilitaryUnit().getCivilization().getName() + "\n");
        }
        showResources(stringBuilder, terrain);
        showImprovements(stringBuilder, terrain);
        showRivers(stringBuilder, terrain);
    }

    private void showImprovements(StringBuilder stringBuilder, Terrain terrain) {
        Pair<Improvement, Boolean> improvementPair = terrain.getImprovementPair();
        if (improvementPair != null) {
            Improvement improvement = improvementPair.getKey();
            if (improvement != null && improvementPair.getValue() == true) {
                if (improvement.getRequiredTechnology() == null || GameDataBase.getCurrentCivilization()
                        .getTechnologies().getTechnologiesResearched().contains(improvement.getRequiredTechnology())) {
                            stringBuilder.append("this terrain has " + improvement.name() + " improvement\n");
                }
            }
        }
    }

    private void showResources(StringBuilder stringBuilder, Terrain terrain) {
        stringBuilder.append("list of resources in this terrain:\n");
        ArrayList<TechnologyType> technologies = GameDataBase.getCurrentCivilization().getTechnologies()
                .getTechnologiesResearched();
        for (Resource resource : terrain.getResources()) {
            if (technologies.contains(resource.getRequiredTechnology())) {
                stringBuilder.append(resource + "\n");
            }
            if (resource.getRequiredTechnology() == null) {
                stringBuilder.append(resource + "\n");
            }
        }
    }

    public String showDetails(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        isValidTerran(x, y);
        Terrain terrain = GameDataBase.getMainMap().getTerrain(x, y);
        TerrainState terrainState = GameDataBase.getCurrentCivilization().getTerrainState(x, y);

        if (terrainState == TerrainState.FOG_OF_WAR)
            return "this terrain is in fog for you";

        StringBuilder stringBuilder = new StringBuilder();
        if (terrain.getCivilization() == null)
            stringBuilder.append("this terrain belongs to: no one");
        else
            stringBuilder.append("this terrain belongs to: ").append(terrain.getCivilization().getName());
        stringBuilder.append("\n" + "Terrain type is: ")
                .append(terrain.getType()).append("\n").append("Terrain features are: ")
                .append(terrain.getTerrainFeatures())
                .append("\n");
        if (terrainState == TerrainState.VISIBLE) {
            showVisibleDetails(stringBuilder, terrain);
        }
        return stringBuilder.toString();
    }
}
