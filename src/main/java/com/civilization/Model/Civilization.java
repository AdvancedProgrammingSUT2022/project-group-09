package com.civilization.Model;

import com.civilization.Model.Info.Gold;
import com.civilization.Model.Info.Happiness;
import com.civilization.Model.Info.Science;
import com.civilization.Model.Info.Technologies;
import com.civilization.Model.Units.Unit;

import java.util.ArrayList;

public class Civilization {
    private Map map;//faghat baraye showmap
    private String name;
    private ArrayList<City> cities;
    private ArrayList<War> wars;
    private Technologies technologies;
    private Gold gold;
    private Science science;
    private Happiness happiness;

    private ArrayList<Unit> units;

    public Civilization(Map map, ArrayList<City> cities, ArrayList<War> wars, Technologies technologies, Gold gold, Science science, Happiness happiness, ArrayList<Unit> units) {
        this.map = map;
        this.cities = cities;
        this.wars = wars;
        this.technologies = technologies;
        this.gold = gold;
        this.science = science;
        this.happiness = happiness;
        this.units = units;
    }

    public Civilization(String name) {
        this.name = name;
        this.map = new Map();
        this.cities = new ArrayList<>();
        this.wars = new ArrayList<>();
        this.technologies = new Technologies();
        this.gold = new Gold();
        this.science = new Science();
        this.happiness = new Happiness();
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

    public Technologies getTechnologies() {
        return this.technologies;
    }

    public void setTechnologies(Technologies technologies) {
        this.technologies = technologies;
    }

    public Gold getGold() {
        return this.gold;
    }

    public void setGold(Gold gold) {
        this.gold = gold;
    }

    public Science getScience() {
        return this.science;
    }

    public void setScience(Science science) {
        this.science = science;
    }

    public String showInfo() {
        return "";
    }

    public Happiness getHappiness() {
        return this.happiness;
    }

    public void setHappiness(Happiness happiness) {
        this.happiness = happiness;
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

    //TODO complete all information functions


}
