package com.civilization.Model;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Controller.GameControllerPackage.MapController;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainState;
import com.civilization.Model.Terrains.TerrainType;
import com.civilization.Model.Terrains.TerrainState;

import java.util.ArrayList;

public class Map {
    protected final static int row = 30, column = 30, length = 30; // length for graphic
    private final TerrainState[][] terrainStates = new TerrainState[row][column];

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
        if (this instanceof MainMap)
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    terrainStates[i][j] = TerrainState.VISIBLE;
                }
            }
        else
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    terrainStates[i][j] = TerrainState.FOGOFWAR;
                }
            }
    }

    public void updateExploration() {
        if (this instanceof MainMap) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    terrainStates[i][j] = TerrainState.VISIBLE;
                }
            }
            return;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (terrainStates[i][j] == TerrainState.VISIBLE)
                    terrainStates[i][j] = TerrainState.KNOWN;
            }
        }
        setVisibleTerrains();
    }

    private void setVisibleTerrains() {
        Terrain[][] terrains = GameDataBase.getMainMap().getTerrains();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {

                if (terrains[i][j].getMilitaryUnit() != null)
                    if (terrains[i][j].getMilitaryUnit().getCivilization() == getCivilization())
                        for (Terrain terrain : terrains[i][j].getMilitaryUnit().getVisibleTerrain()) {
                            terrainStates[terrain.getCoordination().getX()][terrain.getCoordination()
                                    .getY()] = TerrainState.VISIBLE;
                        }
                if (terrains[i][j].getCivilianUnit() != null)
                    if (terrains[i][j].getCivilianUnit().getCivilization() == getCivilization())
                        for (Terrain terrain : terrains[i][j].getCivilianUnit().getVisibleTerrain()) {
                            terrainStates[terrain.getCoordination().getX()][terrain.getCoordination()
                                    .getY()] = TerrainState.VISIBLE;
                        }
                if (terrains[i][j].getCivilization() == getCivilization())
                    for (Terrain terrain : terrains[i][j].getSurroundingTerrain()) {
                        terrainStates[terrain.getCoordination().getX()][terrain.getCoordination()
                                .getY()] = TerrainState.VISIBLE;
                    }
            }
        }
    }

    public boolean isValidTerran(int x, int y) {
        return x < row && x >= 0 && y < column && y >= 0;
    }

    public TerrainState[][] getTerrainStates() {
        return terrainStates;
    }

    private int handelXBoundaries(int x, int y) {
        if (x > row - 3)
            return row - 3;
        if (x < 0)
            return 0;
        if (y % 2 == 1 && x > row - 4)
            return row - 4;
        return x;
    }

    private int handelYBoundaries(int y) {
        if (y > column - 6)
            return column - 6;
        if (y < 0)
            return 0;
        return y;
    }

    public String showMap(int x, int y) {
        // if (y % 2 == 1)
        //     y++;
        x = handelXBoundaries(x, y);
        y = handelYBoundaries(y);

        if (!isValidTerran(x, y)) {
            return "ERROR x: " + x + " , y: " + y + " in show map is invalid";
        }
        return new MapController().showMap(x, y, this.getTerrainStates());
    }

    public static int getRow() {
        return row;
    }

    public static int getColumn() {
        return column;
    }
}
