package com.civilization.Model.Buildings;

import com.civilization.Model.Terrains.CitizenCanWork;
import com.civilization.Model.Terrains.Terrain;

public class Building implements CitizenCanWork {
    private BuildingType myType;
    private Terrain terrain;
    private int remainCost;

    public Building(BuildingType myType, Terrain terrain, int remainCost) {
        this.myType = myType;
        this.terrain = terrain;
        this.remainCost = remainCost;
    }

    public BuildingType getMyType() {
        return myType;
    }

    public void setMyType(BuildingType myType) {
        this.myType = myType;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public int getRemainCost() {
        return remainCost;
    }

    public void setRemainCost(int remainCost) {
        this.remainCost = remainCost;
    }

    public void doWork() {
        BuildingWork.work(this);
    }
//BUILDING
}