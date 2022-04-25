package com.civilization.Model;

import com.civilization.Model.Info.CityGold;
import com.civilization.Model.Info.CivilizationHappiness;
import com.civilization.Model.Info.CityScience;
import com.civilization.Model.Info.CivilizationTechnologies;
import com.civilization.Model.Units.Unit;

import java.security.PublicKey;
import java.util.ArrayList;

public class Civilization {
    private Map map;//faghat baraye showmap
    private String name;
    private ArrayList<City> cities;
    private ArrayList<War> wars;
    private CivilizationTechnologies civilizationTechnologies;
    private CityGold cityGold;
    private CityScience cityScience;
    private CivilizationHappiness civilizationHappiness;

    private ArrayList<Unit> units;

    public Civilization(Map map, ArrayList<City> cities, ArrayList<War> wars, CivilizationTechnologies civilizationTechnologies, CityGold cityGold, CityScience cityScience, CivilizationHappiness civilizationHappiness, ArrayList<Unit> units) {
        this.map = map;
        this.cities = cities;
        this.wars = wars;
        this.civilizationTechnologies = civilizationTechnologies;
        this.cityGold = cityGold;
        this.cityScience = cityScience;
        this.civilizationHappiness = civilizationHappiness;
        this.units = units;
    }

    public Civilization(String name) {
        this.name = name;
        this.map = new Map();
        this.cities = new ArrayList<>();
        this.wars = new ArrayList<>();
        this.civilizationTechnologies = new CivilizationTechnologies();
        this.cityGold = new CityGold();
        this.cityScience = new CityScience();
        this.civilizationHappiness = new CivilizationHappiness();
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

    public CityGold getGold() {
        return this.cityGold;
    }

    public void setGold(CityGold cityGold) {
        this.cityGold = cityGold;
    }

    public CityScience getScience() {
        return this.cityScience;
    }

    public void setScience(CityScience cityScience) {
        this.cityScience = cityScience;
    }

    public String showInfo() {
        return "";
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

    public String getDemographics() {
        int numberOfCitizens = 0;
        for (City city : cities) {
            numberOfCitizens += city.getCitizens().size();
        }
        return "civilization name: " + name + " number of citizens :" + numberOfCitizens + " happiness: " + civilizationHappiness.getCurrentHappiness();
    }

}
