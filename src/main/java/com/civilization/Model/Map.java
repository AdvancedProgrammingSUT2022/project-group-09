package com.civilization.Model;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainState;

import java.util.ArrayList;

public class Map {
    protected final Terrain[][] terrains = new Terrain[30][30];

    protected Civilization civilization;

    public void updateExploration() {
        int horizental = 80;
        int vertical = 50;
        setVisibleTerrain();
        for (int i = 0; i < vertical; i++)
            for (int j = 0; j < horizental; j++) {
                Terrain targetTerrain = terrains[i][j];
                if (targetTerrain.getState() == TerrainState.VISIBLE)
                    targetTerrain.setState(TerrainState.KNOWN);
                setVisibleTerrain();
            }
    }

    private void setVisibleTerrain() {
        //biad tebgh unit ha terrain haei ke visible and ro darare set kone
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

    public String showmap() {
        return "";
    }
}
