package com.civilization.Model.Info;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.City;
import com.civilization.Model.Civilization;
import com.civilization.Model.Improvements.Improvement;
import com.civilization.Model.Resources.Resource;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.Terrains.Terrain;

public class CityGold {
    private double additionGold;

    public void updateAdditionGold() {
        double res = 0;
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            for (City city : civilization.getCities()) {
                if (city.getGold() == this) {
                    for (Terrain terrain : city.getCitizens()) {
                        if (terrain == null)
                            continue;
                        for (Resource resource : terrain.getResources()) {
                            res += resource.getGold();
                        }
                        for (TerrainFeature terrainFeature : terrain.getTerrainFeatures()) {
                            res += terrainFeature.getGold();
                        }
                    }
                    //TODO PAYAM buidldingo nazaDi
                    return;
                }
            }
        }
        System.err.println("gold dar hich shahri naboode");
        throw new RuntimeException();
    }

    public double getAdditionGold() {
        return additionGold;
    }

    public void setAdditionGold(double additionGold) {
        this.additionGold = additionGold;
    }
}
