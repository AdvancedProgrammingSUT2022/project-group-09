package com.civilization.Model.Units;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.City;
import com.civilization.Model.Civilization;
import com.civilization.Model.MainMap;
import com.civilization.Model.Terrains.Terrain;

import java.util.ArrayList;

public class Settler extends Unit {
    public Settler(Terrain terrain, Civilization civilization) {
        super(UnitType.SETTLER, terrain, civilization);
    }

    public void foundCity() {
        City city = new City(getTerrain(), new ArrayList<>());
        GameDataBase.getMainMap().setTerrain(getTerrain().getXpositionTerrain(), getTerrain().getYpositionTerrain(), city);
    }

}
