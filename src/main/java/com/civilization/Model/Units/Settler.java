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

    public void foundCaptalCity() {
        City city = new City();
        city.setCapital(true);
        city.setCivilization(getCivilization());
        GameDataBase.getMainMap().setTerrain(getTerrain().getXPosition(), getTerrain().getYPosition(), city);
        //Todo set kardan terrain haye doresh
       // city.getSurroundingTerrain()
    }

    public void foundCity() {
        City city = new City();
        city.setCapital(false);
        city.setCivilization(getCivilization());
        GameDataBase.getMainMap().setTerrain(getTerrain().getXPosition(), getTerrain().getYPosition(), city);
    }

}
