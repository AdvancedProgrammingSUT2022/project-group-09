package Model.Units;

import Model.Civilization;
import Model.Terrains.Terrain;

public class CivilianUnit extends Unit{

    public CivilianUnit(UnitType myType, Terrain terrain, Civilization civilization) {
        super(myType, terrain, civilization);
    }
}
