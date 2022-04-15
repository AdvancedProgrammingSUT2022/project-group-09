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


}
