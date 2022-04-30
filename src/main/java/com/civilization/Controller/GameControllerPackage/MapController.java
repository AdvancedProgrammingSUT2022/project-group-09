package com.civilization.Controller.GameControllerPackage;

import java.util.PriorityQueue;
import java.util.regex.Matcher;

import com.civilization.Main;
import com.civilization.Model.ConsoleColors;
import com.civilization.Model.Map;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainState;
import com.civilization.Model.Terrains.TerrainType;

public class MapController {

    private Terrain[][] terrains;
    private TerrainState[][] terrainStates;

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
                String backgroundColor = getBackgroundColor(terrains[x + i][y + j]);// inja bug dare az size ararye
                                                                                    // mizane biroon
                // before entering this function we have checked if it's out of bonds or not
                int istart = i * 6;
                int jstart = j * 8;
                if (j % 2 == 1) {
                    istart += 3;
                }

                drawHex(mapString, istart, jstart);

                if (terrainStates[x + i][y + j] == TerrainState.FOGOFWAR) {
                    backgroundColor = ConsoleColors.GRAY_BACKGROUND;
                }

                if (j % 2 == 1)
                    drawMainDetails(mapString, istart, jstart, x + i + remainder, y + j, backgroundColor);
                else
                    drawMainDetails(mapString, istart, jstart, x + i, y + j, backgroundColor);

                if (terrainStates[x + i][y + j] == TerrainState.VISIBLE) {
                    // TODO set name in civilization constructor
                    if (terrains[x + i][y + j].getCivilization() == null) {
                        mapString[istart + 1][jstart + 5] = backgroundColor + " " + ConsoleColors.RESET;
                    } else {
                        mapString[istart + 1][jstart + 5] = backgroundColor
                                + terrains[x + i][y + j].getCivilization().getName().charAt(0) + ConsoleColors.RESET;
                    }
                }
            }
        }

    }

    public String showMap(int x, int y, TerrainState terrainStates[][]) {
        this.terrains = GameDataBase.getMainMap().getTerrains();
        this.terrainStates = terrainStates;
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

    public String showMap(Matcher matcher, Map map) {
        this.terrainStates = map.getTerrainStates();
        this.terrains = GameDataBase.getMainMap().getTerrains();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        return showMap(x, y, terrainStates);
    }

    public String showMap(int x, int y, Map map) {
        this.terrainStates = map.getTerrainStates();
        this.terrains = GameDataBase.getMainMap().getTerrains();
        return showMap(x, y, terrainStates);
    }
}
