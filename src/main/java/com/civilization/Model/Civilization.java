package com.civilization.Model;

import com.civilization.Model.Buildings.BuildingType;
import com.civilization.Model.Info.*;
import com.civilization.Model.Resources.Resource;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainState;
import com.civilization.Model.Units.Unit;
import com.civilization.Model.Units.UnitType;

import java.security.PublicKey;
import java.util.ArrayList;

public class Civilization {
    private Map map;//faghat baraye showmap
    private String name;
    private ArrayList<City> cities;
    private ArrayList<War> wars;
    private CivilizationTechnologies civilizationTechnologies;
    private CivilizationGold civilizationGold;
    private CivilizationScience civilizationScience;
    private CivilizationHappiness civilizationHappiness;

    private ArrayList<Resource> resources;
    private ArrayList<Unit> units;

    public Civilization(Map map, ArrayList<Resource> resources, ArrayList<City> cities, ArrayList<War> wars, CivilizationTechnologies civilizationTechnologies, CivilizationGold civilizationGold, CivilizationScience civilizationScience, CivilizationHappiness civilizationHappiness, ArrayList<Unit> units) {
        this.map = map;
        this.cities = cities;
        this.wars = wars;
        this.civilizationTechnologies = civilizationTechnologies;
        this.civilizationGold = civilizationGold;
        this.civilizationScience = civilizationScience;
        this.civilizationHappiness = civilizationHappiness;
        this.resources = resources;
        this.units = units;
    }

    public Civilization(String name) {
        this.name = name;
        this.map = new Map();
        this.cities = new ArrayList<>();
        this.wars = new ArrayList<>();
        this.civilizationTechnologies = new CivilizationTechnologies();
        this.civilizationGold = new CivilizationGold();
        this.civilizationScience = new CivilizationScience();
        this.civilizationHappiness = new CivilizationHappiness();
        this.resources = new ArrayList<>();
        this.units = new ArrayList<>();
    }

    public ArrayList<City> getCities() {
        return this.cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public void addCity(City city) {
        this.cities.add(city);
    }

    public ArrayList<War> getWars() {
        return this.wars;
    }

    public void setWars(ArrayList<War> wars) {
        this.wars = wars;
    }

    public CivilizationTechnologies getTechnologies() {
        return this.civilizationTechnologies;
    }

    public void setTechnologies(CivilizationTechnologies civilizationTechnologies) {
        this.civilizationTechnologies = civilizationTechnologies;
    }

    public CivilizationGold getGold() {
        return this.civilizationGold;
    }

    public void setGold(CivilizationGold cityGold) {
        this.civilizationGold = cityGold;
    }

    public CivilizationScience getScience() {
        return civilizationScience;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public void setScience(CivilizationScience cityScience) {
        this.civilizationScience = cityScience;
    }

    public CivilizationHappiness getHappiness() {
        return this.civilizationHappiness;
    }

    public void setHappiness(CivilizationHappiness civilizationHappiness) {
        this.civilizationHappiness = civilizationHappiness;
    }

    public Map getMap() {
        return this.map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getInformation() {
        return "name : " + name + "\n" + "Gold : " + getGold().getCurrentGold() + " addition gold :" + getGold().getAdditionGold() +
                "\n" + "happines : " + getHappiness().getAdditionHappiness() + " science : " + getScience().getAdditionScience();
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
    }

    public void removeUnit(Unit unit) {
        this.units.remove(unit);
    }

    public TerrainState getTerrainState(int x, int y) {
        return this.map.getTerrainStates()[x][y];
    }

    public String getDemographics() {
        StringBuilder res = new StringBuilder();
        int numberOfCitizens = 0;
        for (City city : cities) {
            res.append("city ").append(city.getName()).append("demographic:").append("\n")
                    .append(city.getDemographics());
        }
        return res.toString();
    }

    public void nextTurn() {
        updateResource();
        for (City city : cities) {
            getScience().add(city.getCityScience().getAdditionScience());
            getGold().add(city.getGold().getAdditionGold());
        }
        getHappiness().nexTurn();
    }

    private void updateResource() {
        resources = new ArrayList<>();
        for (City city : getCities()) {
            for (Terrain terrain : city.getTerrains()) {
                for (Resource resource : terrain.getResources()) {
                    if (getTechnologies().getTechnologiesResearched().contains(resource.getRequiredTechnology())
                            && terrain.getImprovement() == resource.getRequiredImprovement())
                        resources.add(resource);
                }
            }
        }
    }

    public ArrayList<BuildingType> buildingsCanBeBuilt() {
        ArrayList<BuildingType> possibleBuildings = new ArrayList<>();
        for (BuildingType building : BuildingType.getAllBuildings()) {
            if (civilizationTechnologies.getTechnologiesResearched().contains(building.getRequirement())) {
                possibleBuildings.add(building);
            }
        }
        return possibleBuildings;
    }

    public ArrayList<UnitType> unitsCanBeBuilt() {
        ArrayList<UnitType> possibleUnits = new ArrayList<>();
        for (UnitType unit : UnitType.getAllUnits()) {
            if (civilizationTechnologies.getTechnologiesResearched().contains(unit.getRequiredTechnology())) {
                possibleUnits.add(unit);
            }
        }
        return possibleUnits;
    }

}
