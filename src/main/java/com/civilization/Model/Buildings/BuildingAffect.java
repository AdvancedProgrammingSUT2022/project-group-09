package com.civilization.Model.Buildings;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.City;
import com.civilization.Model.Civilization;
import com.civilization.Model.Units.Unit;

import java.util.ArrayList;

public class BuildingAffect {
    private ArrayList<BuildingType> buildings;
    private double gold;
    private double product;
    private double science;

    private void Barracks() {
        //  if (buildings.contains(BuildingType.BARRACKS)) {
        //      for (Unit unit : getCivilization().getUnits()) {
        //if(unit.getXp==mainXp)
        //unit.getXP+=15;
        //felan chizi be esme xp tarif nashode
        //      }
        //  }
    }

    private void Library() {

    }

    private void addWall() {

    }

    private void addCastle() {

    }

    private void addArmory() {

    }

    private void addCourthhouse() {

    }

    private void Stable() {
        //nemidoonam in gharare che gohi cokhoe
    }

    private void Forage() {
        //nemidoonam in che mikone
    }

    ///va baghie


    public double getBuilldingGold() {
        return 0;
    }

    public double getBuilldingScience() {
        return 0;
    }

    public double getBuilldingFood() {
        return 0;
    }

    public void DoBuildingsWork() {

    }


    public BuildingAffect(ArrayList<BuildingType> buildings) {
        this.buildings = buildings;
    }

    public BuildingAffect() {
        this.buildings = new ArrayList<>();
    }

    public ArrayList<BuildingType> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<BuildingType> buildings) {
        this.buildings = buildings;
    }

    public void addBuildings(BuildingType building) {
        this.buildings.add(building);
    }

    public Civilization getCivilization() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            for (City city : civilization.getCities()) {
                if (city.getBuildings() == this)
                    return civilization;
            }
        }
        throw new RuntimeException();
    }

    public City getCity() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            for (City city : civilization.getCities()) {
                if (city.getBuildings() == this)
                    return city;
            }
        }
        throw new RuntimeException();
    }

}
