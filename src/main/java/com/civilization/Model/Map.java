package com.civilization.Model;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainState;
import com.civilization.Model.Terrains.TerrainType;
import com.civilization.Model.Terrains.TerrainState;

import java.util.ArrayList;

public class Map {
    protected final int row = 30, column = 30, length = 30; //length for graphic
    protected final Terrain[][] terrains = new Terrain[row][column];

    public Terrain[][] getTerrains() {
        return terrains;
    }

    public Civilization getCivilization() {
        for (Civilization civilization1 : GameDataBase.getCivilizations()) {
            if (civilization1.getMap() == this)
                return civilization1;
        }
        return null;
    }

    public void setCivilization(Civilization civilization) {
        for (Civilization civilization1 : GameDataBase.getCivilizations()) {
            if (civilization1.getMap() == this)
                civilization1.setMap(null);
        }
        civilization.setMap(this);
    }


    public Map() {
        for (int i = 0; i < terrains.length; i++) {
            for (int j = 0; j < terrains[i].length; j++) {
                if (j > 12) {
                    terrains[i][j] = new Terrain(TerrainType.PLAIN);
                } else {
                    terrains[i][j] = new Terrain(TerrainType.SNOW);
                }
            }
        }
        //TODO change constructor
    }

    public void updateExploration() {
        int horizental = terrains[0].length;
        int vertical = terrains.length;
        if (this instanceof MainMap) {
            for (Terrain[] terrain : terrains)
                for (int j = 0; j < horizental; j++) {
                    terrain[j].setState(TerrainState.VISIBLE);
                }
            return;
        }
        for (Terrain[] terrain : terrains)
            for (int j = 0; j < horizental; j++) {
                Terrain targetTerrain = terrain[j];
                if (targetTerrain.getState() == TerrainState.VISIBLE)
                    targetTerrain.setState(TerrainState.KNOWN);
            }
        setVisibleTerrains();
    }

    private void setVisibleTerrains() {
        int horizental = terrains[0].length;
        int vertical = terrains.length;
        for (Terrain[] value : terrains)
            for (int j = 0; j < horizental; j++) {

                if (value[j].getMilitaryUnit() != null)
                    if (value[j].getMilitaryUnit().getCivilization() == getCivilization())
                        for (Terrain terrain : value[j].getMilitaryUnit().getVisibleTerrain()) {
                            terrain.setState(TerrainState.VISIBLE);
                        }
                if (value[j].getCivilianUnit() != null)
                    if (value[j].getCivilianUnit().getCivilization() == getCivilization())
                        for (Terrain terrain : value[j].getCivilianUnit().getVisibleTerrain()) {
                            terrain.setState(TerrainState.VISIBLE);
                        }
                if (value[j].getCivilization() == getCivilization())
                    value[j].setState(TerrainState.VISIBLE);

            }
    }


    public void setTerrain(int x, int y, Terrain terrain) {
        terrains[x][y] = terrain;
    }

    public Terrain getTerrain(int x, int y) {
        return terrains[x][y];
    }

    public int getXpositionTerrain(Terrain terrain) {
        int horizental = terrains[0].length;
        int vertical = terrains.length;
        for (int i = 0; i < vertical; i++)
            for (int j = 0; j < horizental; j++) {
                if (terrains[i][j] == terrain)
                    return i;
            }
        System.err.println("ERROR! in getXpositionTerrain ");
        throw new RuntimeException();
    }

    public int getYpositionTerrain(Terrain terrain) {
        int horizental = terrains[0].length;
        int vertical = terrains.length;
        for (Terrain[] value : terrains)
            for (int j = 0; j < horizental; j++) {
                if (value[j] == terrain)
                    return j;
            }
        System.err.println("ERROR! in getXpositionTerrain ");
        throw new RuntimeException();
    }

    public boolean isValidTerran(Terrain terrain) {
        int horizental = terrains[0].length;
        int vertical = terrains.length;
        for (Terrain[] value : terrains)
            for (int j = 0; j < horizental; j++) {
                if (value[j] == terrain)
                    return true;
            }
        return false;
    }

    public boolean isValidTerran(int x, int y) {
        return x < terrains.length && x >= 0 && y < terrains[0].length && y >= 0;
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

    public String showmap(int x, int y) {
        //creating mapString
        String[][] mapString = new String[21][51];
        for (int i = 0; i < mapString.length; i++) {
            for (int j = 0; j < mapString[i].length; j++) {
                mapString[i][j] = " ";
            }
        }

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
                for (int k = 2; k > -1; k--) {
                    mapString[istart + 2 - k][jstart + k] = "/";
                    mapString[istart + 2 - k][jstart + 10 - k] = "\\";
                    mapString[istart + 5 - k][jstart + 2 - k] = "\\";
                    mapString[istart + 5 - k][jstart + 8 + k] = "/";
                }
                for (int k = 2; k > -1; k--) {
                    for (int z = jstart + k + 1; z < jstart + k + 1 + 5 + 4 - 2 * k; z++) {
                        mapString[istart + 2 - k][z] = backgroundColor + " " + ConsoleColors.RESET;
                        mapString[istart + 3 + k][z] = backgroundColor + " " + ConsoleColors.RESET;
                    }
                }
                if ((x + i) / 10 != 0)
                    mapString[istart + 2][jstart + 3] = ConsoleColors.BLACK + backgroundColor + (x + i) / 10 + ConsoleColors.RESET;
                mapString[istart + 2][jstart + 4] = ConsoleColors.BLACK + backgroundColor + (x + i) % 10 + ConsoleColors.RESET;
                mapString[istart + 2][jstart + 5] = ConsoleColors.BLACK + backgroundColor + "," + ConsoleColors.RESET;
                if ((y + j) / 10 != 0)
                    mapString[istart + 2][jstart + 6] = ConsoleColors.BLACK + backgroundColor + (y + j) / 10 + ConsoleColors.RESET;
                mapString[istart + 2][jstart + 7] = ConsoleColors.BLACK + backgroundColor + (y + j) % 10 + ConsoleColors.RESET;
                for (int k = 0; k < 5; k++) {
                    mapString[istart + 5][jstart + 3 + k] = backgroundColor + "_" + ConsoleColors.RESET;
                }
            }
        }

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
