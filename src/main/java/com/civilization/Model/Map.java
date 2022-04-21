package com.civilization.Model;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainState;

import java.util.ArrayList;

public class Map {
    protected final Terrain[][] terrains = new Terrain[30][30];

    public Terrain[][] getTerrains() {
        return terrains;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }

    protected Civilization civilization;

    public void updateExploration() {
        int horizental = terrains[0].length;
        int vertical = terrains.length;
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

                if (value[j].getMilitaryUnit().getCivilization() == getCivilization())
                    for (Terrain terrain : value[j].getMilitaryUnit().getVisibleTerrain()) {
                        terrain.setState(TerrainState.VISIBLE);
                    }
                if (value[j].getCivilianUnit().getCivilization() == getCivilization())
                    for (Terrain terrain : value[j].getCivilianUnit().getVisibleTerrain()) {
                        terrain.setState(TerrainState.VISIBLE);
                    }
                if (value[j] instanceof City && value[j].getCivilization() == getCivilization())
                    for (Terrain terrain : ((City) value[j]).getVisibleTerrain()) {
                        terrain.setState(TerrainState.VISIBLE);
                    }

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

    public String showmap() {
        return "";
    }
}
