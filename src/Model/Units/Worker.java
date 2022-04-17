package Model.Units;

import Model.Civilization;
import Model.Improvements.Improvement;
import Model.Terrains.Terrain;

public class Worker extends Unit {
    public Worker(Terrain terrain, Civilization civilization) {
        super(UnitType.WORKER, terrain, civilization);
    }
    public void makeImprovement(Improvement improvement) {

    }
}
