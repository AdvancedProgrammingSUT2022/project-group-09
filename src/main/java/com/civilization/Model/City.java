package com.civilization.Model;

import com.civilization.Model.Buildings.BuildingType;
import com.civilization.Model.Info.Food;
import com.civilization.Model.Info.Gold;
import com.civilization.Model.Info.Product;
import com.civilization.Model.Terrains.CitizenCanWork;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainType;
import com.civilization.Model.Units.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class City extends Terrain {
    private ArrayList<CitizenCanWork> citizens; //length=number of citizens and arraylisti az jahaei hast ke citizen ha kar mikonnand
    private boolean isCapital;
    private Product production;
    private Food food;
    private Gold gold;
    private ArrayList<Terrain> terrains;
    private HashMap<Integer, BuildingType> makingBuilding;//<remaining turn to build,building>
    private HashMap<Integer, Unit> makingUnit;//<remaining turn to build,Unit>
    private ArrayList<BuildingType> buildings;

    public City() {
        this.citizens = new ArrayList<>();
        this.isCapital = false;
        this.production = new Product();
        this.food = new Food();
        this.gold = new Gold();
        this.terrains = new ArrayList<>();
        this.makingBuilding = new HashMap<>();
        this.makingUnit = new HashMap<>();
        this.buildings = new ArrayList<>();
    }

    public City(City city) {
        this.citizens = city.getCitizens();
        this.isCapital = city.isCapital();
        this.production = city.getProduction();
        this.food = city.getFood();
        this.gold = city.getGold();
        this.terrains = city.getTerrains();
        this.makingBuilding = city.getMakingBuilding();
        this.makingUnit = city.getMakingUnit();
        this.buildings = city.getBuildings();
    }

    public ArrayList<CitizenCanWork> getCitizens() {
        return citizens;
    }

    public void setCitizens(ArrayList<CitizenCanWork> citizens) {
        this.citizens = citizens;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    public Product getProduction() {
        return production;
    }

    public void setProduction(Product production) {
        this.production = production;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Gold getGold() {
        return gold;
    }

    public void setGold(Gold gold) {
        this.gold = gold;
    }

    public ArrayList<Terrain> getTerrains() {
        return terrains;
    }

    public void setTerrains(ArrayList<Terrain> terrains) {
        this.terrains = terrains;
    }

    public void addTerrain(Terrain terrain) {
        this.terrains.add(terrain);
    }

    public HashMap<Integer, BuildingType> getMakingBuilding() {
        return makingBuilding;
    }

    public void setMakingBuilding(HashMap<Integer, BuildingType> makingBuilding) {
        this.makingBuilding = makingBuilding;
    }

    public HashMap<Integer, Unit> getMakingUnit() {
        return makingUnit;
    }

    public void setMakingUnit(HashMap<Integer, Unit> makingUnit) {
        this.makingUnit = makingUnit;
    }

    public ArrayList<BuildingType> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<BuildingType> buildings) {
        this.buildings = buildings;
    }
}