package com.civilization.Model.Units;

import com.civilization.Model.Civilization;
import com.civilization.Model.Terrains.Terrain;

public class Unit {
    private UnitType myType;
    private boolean isSleep;
    private Terrain terrain;
    private Civilization civilization;

    private int remainingMove;


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

    public int getRemainingMove() {
        return remainingMove;
    }

    public void setRemainingMove(int remainingMove) {
        this.remainingMove = remainingMove;
    }
}
