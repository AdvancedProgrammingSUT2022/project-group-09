package Improvements;

import Resources.Resource;
import Resources.ResourceType;
import Technologies.Technology;
import Technologies.TechnologyType;
import TerrainFeatures.TerrainFeatureType;
import Terrains.Terrain;
import Terrains.TerrainType;

import java.util.ArrayList;

public enum Improvement {

    final int food;
    final int production;
    final int gold;
    final ArrayList<ResourceType> improvesResources;
    final TechnologyType requiredTechnology;
    final ArrayList<Object> canBeBuiltON; //TODO ... terraintype and terrainfeaturetype

    Improvement(int food, int production, int gold, ArrayList<ResourceType> improvesResources, TechnologyType requiredTechnology, ArrayList<Object> canBeBuiltOn) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.improvesResources = improvesResources;
        this.requiredTechnology = requiredTechnology;
        this.canBeBuiltON = canBeBuiltOn;
    }


}
