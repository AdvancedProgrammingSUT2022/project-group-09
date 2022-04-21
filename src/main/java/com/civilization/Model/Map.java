package com.civilization.Model;

import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainState;
import com.civilization.Model.Terrains.TerrainType;

public class Map {
    protected final Terrain[][] terrains = new Terrain[30][30];

    public Map() {
        for (int i = 0; i < terrains.length; i++) {
            for (int j = 0; j < terrains[i].length; j++) {
                if (j > 12) {
                    terrains[i][j] = new Terrain(TerrainType.PLAIN, TerrainState.FOGOFWAR, null, null, null, null, null,
                            null, null, null);
                } else {
                    terrains[i][j] = new Terrain(TerrainType.SNOW, TerrainState.KNOWN , null, null, null, null, null,
                            null, null, null);
                }
            }
        }
        // TODO change constructor
    }

    public void updateExploration() {
        int horizental = 80;
        int vertical = 50;
        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < horizental; j++) {
                Terrain targetTerrain = terrains[i][j];
                // badbakht shodim
            }
        }
    }

    public void setTerrain(int x, int y, Terrain terrain) {

    }

    public Terrain getTerrain(int x, int y) {
        return null;
    }

    public int getXpositionTerrain(Terrain terrain) {
        return 0;
    }

    public int getypositionTerrain(Terrain terrain) {
        return 0;
    }

    public boolean isValidTerran(Terrain terrain) {
        return false;
    }

    public boolean isValidTerran(int x, int y) {
        return false;
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

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                String backgroundColor = getBackgroundColor(terrains[x + i][y + j]);
                int istart = i * 6;
                int jstart = j * 8;
                if (j % 2 == 1) {
                    istart += 3;
                }

                drawHex(mapString, istart, jstart);

                if (terrains[x + i][y + j].getState() == TerrainState.FOGOFWAR) {
                    backgroundColor = ConsoleColors.GRAY_BACKGROUND;
                }

                drawMainDetails(mapString, istart, jstart, x + i, y + j, backgroundColor);

                if (terrains[x + i][y + j].getState() == TerrainState.VISIBLE) {
                    // TODO set name in civilization constructor
                    mapString[istart + 1][jstart + 5] = backgroundColor
                            + terrains[x + i][y + j].getCivilization().getName().charAt(0) + ConsoleColors.RESET;
                }
            }
        }

    }

    public String showmap(int x, int y) {
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
}
