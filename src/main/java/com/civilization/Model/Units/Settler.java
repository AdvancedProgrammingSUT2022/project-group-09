package com.civilization.Model.Units;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.City;
import com.civilization.Model.Civilization;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainType;

public class Settler extends Unit {
    public Settler(Terrain terrain, Civilization civilization) {
        super(UnitType.SETTLER, terrain, civilization);
    }

    public void foundCity() {
        for (City city : getCivilization().getCities()) {
            if (city.isCapital()) {
                foundNormalCity();
                return;
            }
        }
        foundCapitalCity();
    }

    private void foundCapitalCity() {
        City city = new City(getTerrain());
        city.setCapital(true);
        city.setCivilization(getCivilization());
        GameDataBase.getMainMap().setTerrain(getTerrain().getXPosition(), getTerrain().getYPosition(), city);
        for (Terrain terrain : city.getSurroundingTerrain()) {
            terrain.setCivilization(getCivilization());
        }
        if (getTerrain().getType() == TerrainType.HILLS)
            city.setHp(city.getHp() + 20);
        delete();
    }

    private void foundNormalCity() {
        City city = new City();
        city.setCapital(false);
        city.setCivilization(getCivilization());
        GameDataBase.getMainMap().setTerrain(getTerrain().getXPosition(), getTerrain().getYPosition(), city);
        for (Terrain terrain : city.getSurroundingTerrain()) {
            terrain.setCivilization(getCivilization());
        }
        if (getTerrain().getType() == TerrainType.HILLS)
            city.setHp(city.getHp() + 20);
        delete();
    }

}
