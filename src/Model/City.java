package Model;

import Model.Buildings.Building;
import Model.Resources.Resource;
import Model.TerrainFeatures.TerrainFeature;
import Model.Terrains.Terrain;
import Model.Terrains.TerrainState;
import Model.Terrains.TerrainType;

import java.util.ArrayList;

public class City extends Terrain {
    private boolean isCapital;
    private Product production;
    private Food food;
    private Gold gold;

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

    private ArrayList<Terrain> terrains;
    private ArrayList<Citizen> citizens;

    public City(TerrainType type, TerrainState state, ArrayList<TerrainFeature> terrainFeatures, ArrayList<Resource> resources, Building building) {
        super(type, state, terrainFeatures, resources, building);
    }

    public String showCity()
    {
        return "";
    }
}