package com.civilization.Model.Units;

import com.civilization.Model.Civilization;
import com.civilization.Model.Improvements.Improvement;
import com.civilization.Model.Terrains.Terrain;

public class Worker extends Unit {
    public Worker(Terrain terrain, Civilization civilization) {
        super(UnitType.WORKER, terrain, civilization);
    }

    public void makeImprovement(Improvement improvement) {

    }
}
