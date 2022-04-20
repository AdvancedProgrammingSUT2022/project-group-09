package com.civilization.Model;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.Terrains.Terrain;

public class Map {
    protected final Terrain[][] terrains = new Terrain[30][30];

    public void updateExploration() {
        int horizental = 80;
        int vertical = 50;
        for (int i = 0; i < vertical; i++)
            for (int j = 0; j < horizental; j++) {
                Terrain targetTerrain = terrains[i][j];
                //badbakht shodim
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

    public String showmap() {
        return "";
    }
}
