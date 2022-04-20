package com.civilization.Model;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.Terrains.Terrain;

import java.util.ArrayList;

public class MainMap extends Map {
    public void updateMap() {
        int horizental = 80;
        int vertical = 50;
        for (int q = 0; q < GameDataBase.getCivilizations().size(); q++) {
            for (int i = 0; i < vertical; i++)
                for (int j = 0; j < horizental; j++) {
                    Terrain targetTerrain = GameDataBase.getCivilizations().get(q).getMap().getTerrain(i, j);
                    Terrain mainMapTerrain = terrains[i][j];
                    //TADA .. !
                    targetTerrain.setBuilding(mainMapTerrain.getBuilding());
                    targetTerrain.setCitizens(mainMapTerrain.getCitizens());
                    targetTerrain.setTerrainFeatures(mainMapTerrain.getTerrainFeatures());
                    targetTerrain.setCity(mainMapTerrain.getCity());
                    targetTerrain.setCivilization(mainMapTerrain.getCivilization());
                    targetTerrain.setCivilianUnit(mainMapTerrain.getCivilianUnit());
                    targetTerrain.setResources(mainMapTerrain.getResources());
                    targetTerrain.setCitizens(mainMapTerrain.getCitizens());
                    targetTerrain.setType(mainMapTerrain.getType());
                    targetTerrain.setMilitaryUnit(mainMapTerrain.getMilitaryUnit());
                }
        }
    }



    public MainMap() {
        //TODO GENERATE MAP
    }
}
