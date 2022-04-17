package Model.Units;

import Model.Civilization;
import Model.Terrains.Terrain;

public class Settler extends Unit {
    public Settler(Terrain terrain, Civilization civilization) {
        super(UnitType.SETTLER, terrain, civilization);
    }

    public void foundCity() {

    }

}
