package Buildings;

public class Building {
    BuildingType myType;
    int remainCost;
    public void doWork() {
        BuildingWork.tmp(this);
    }
//BUILDING
}