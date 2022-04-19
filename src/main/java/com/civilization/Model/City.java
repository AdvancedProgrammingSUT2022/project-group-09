package com.civilization.Model;

import com.civilization.Model.Buildings.Building;
import com.civilization.Model.Resources.Resource;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainState;
import com.civilization.Model.Terrains.TerrainType;
import com.civilization.Model.Units.MilitaryUnit;
import com.civilization.Model.Units.Unit;

import java.util.ArrayList;

public class City extends Terrain {
    private boolean isCapital;
    private Product production;
    private Food food;
    private Gold gold;
    private ArrayList<Terrain> terrains;
    private ArrayList<Citizen> citizens;


    public City(TerrainType type, TerrainState state, ArrayList<TerrainFeature> terrainFeatures, ArrayList<Resource> resources, Building building, Unit civilianUnit, Civilization civilization, ArrayList<Citizen> cityCitizens, MilitaryUnit militaryUnit, City city, boolean isCapital, ArrayList<Terrain> terrains, ArrayList<Citizen> terrainCitizens) {
        super(type, state, terrainFeatures, resources, building, civilianUnit, civilization, cityCitizens, militaryUnit, city);
        this.isCapital = isCapital;
        this.production = new Product();
        this.food = new Food();
        this.gold = new Gold();
        this.terrains = terrains;
        this.citizens = terrainCitizens;
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

    public ArrayList<Citizen> getCitizens() {
        return citizens;
    }

    public void setCitizens(ArrayList<Citizen> citizens) {
        this.citizens = citizens;
    }

    public String showCity() {
        return "";
    }

}