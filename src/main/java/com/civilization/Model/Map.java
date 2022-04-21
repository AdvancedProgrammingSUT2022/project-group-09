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
        // TODO change constructor
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
}
