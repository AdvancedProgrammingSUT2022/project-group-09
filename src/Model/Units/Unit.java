package Model.Units;

import Model.Civilization;
import Model.Terrains.Terrain;

public class Unit {
    private UnitType myType;

    public UnitType getMyType() {
        return myType;
    }

    public void setMyType(UnitType myType) {
        this.myType = myType;
    }

    public boolean isSleep() {
        return isSleep;
    }

    public void setSleep(boolean sleep) {
        isSleep = sleep;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }

    private boolean isSleep;
    private Terrain terrain;
    private Civilization civilization;

    public Unit(UnitType myType, Terrain terrain, Civilization civilization) {
        this.myType = myType;
        this.terrain = terrain;
        this.civilization = civilization;
    }

    public void move() {

    }

    public void Donothong() {

    }


    public void sleep() {

    }

    public void delete() {

    }
}
