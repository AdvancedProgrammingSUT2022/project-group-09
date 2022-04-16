package Model.Terrains;

import Model.Buildings.Building;
import Model.Resources.Resource;
import Model.TerrainFeatures.TerrainFeature;

import java.util.ArrayList;

public class Terrain implements CitizenCanWork {
    private TerrainType type;
    private TerrainState state;
    private ArrayList<TerrainFeature> terrainFeatures;
    private ArrayList<Resource> resources;
    private Building building;


    public TerrainType getType() {
        return type;
    }

    public void setType(TerrainType type) {
        this.type = type;
    }

    public TerrainState getState() {
        return state;
    }

    public void setState(TerrainState state) {
        this.state = state;
    }

    public ArrayList<TerrainFeature> getTerrainFeatures() {
        return terrainFeatures;
    }

    public void setTerrainFeatures(ArrayList<TerrainFeature> terrainFeatures) {
        this.terrainFeatures = terrainFeatures;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Terrain(TerrainType type, TerrainState state, ArrayList<TerrainFeature> terrainFeatures, ArrayList<Resource> resources, Building building) {
        this.type = type;
        this.state = state;
        this.terrainFeatures = terrainFeatures;
        this.resources = resources;
        this.building = building;
    }

}
