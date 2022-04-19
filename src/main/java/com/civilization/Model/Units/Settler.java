package com.civilization.Model.Units;

import com.civilization.Model.Civilization;
import com.civilization.Model.Terrains.Terrain;

public class Settler extends Unit {
    public Settler(Terrain terrain, Civilization civilization) {
        super(UnitType.SETTLER, terrain, civilization);
    }

    public void foundCity() {

    }

}
