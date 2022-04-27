package com.civilization.Model.Units;

import com.civilization.Model.Civilization;
import com.civilization.Model.Improvements.Improvement;
import com.civilization.Model.Terrains.Terrain;

public class Worker extends Unit {
    public Worker(Terrain terrain, Civilization civilization) {
        super(UnitType.WORKER, terrain, civilization);
    }

    public void makeImprovement(Improvement improvement) {
        if (improvement == Improvement.ROAD)
            getTerrain().setHasRoad(true);
        else
            getTerrain().setImprovement(improvement);
    }

    public void removeJungle(Terrain terrain) {
        //TODO .. controller azash estefade shode
    }

    public void removeRoute(Terrain terrain) {

    }

    public void repair(Terrain terrain) {

    }
}
