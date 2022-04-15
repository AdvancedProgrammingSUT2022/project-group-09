package Model.Buildings;

import Model.Terrains.CitizenCanWork;
import Model.Terrains.Terrain;

public class Building implements CitizenCanWork {
    private BuildingType myType;
    private Terrain terrain;
    private int remainCost;
    public void doWork() {
        BuildingWork.work(this);
    }
//BUILDING
}