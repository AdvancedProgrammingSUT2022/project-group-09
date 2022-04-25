package com.civilization.Model.Improvements;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.Resources.Resource;
import com.civilization.Model.Resources.TerrainTypeOrTerrainFeatureType;
import com.civilization.Model.TechnologyPackage.TechnologyType;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainType;

import java.util.ArrayList;

public enum Improvement {
    CAMP(0, 0, 0, new ArrayList<Resource>() {
        {
            add(Resource.IVORY);
            add(Resource.FURS);
            add(Resource.DEER);
        }
    }, TechnologyType.TRAPPING, new ArrayList<TerrainTypeOrTerrainFeatureType>() {
        {
            add(TerrainFeature.FOREST);
            add(TerrainType.TUNDRA);
            add(TerrainType.PLAIN);
            add(TerrainType.HILLS);
        }
    }),
    FARM(1, 0, 0, new ArrayList<Resource>() {
        {
            add(Resource.WHEAT);
        }
    }, TechnologyType.ARGICULTURE, new ArrayList<TerrainTypeOrTerrainFeatureType>() {
        {
            add(TerrainType.GRASSLLAND);
            add(TerrainType.PLAIN);
            add(TerrainType.DESERT);
        }
    }),
    LUMBERMILL(0, 1, 0, null, TechnologyType.ENGINEERING, new ArrayList<TerrainTypeOrTerrainFeatureType>() {
        {
            add(TerrainFeature.FOREST);
        }
    }),
    MINE(0, 1, 0, new ArrayList<Resource>() {
        {
            add(Resource.WHEAT);
            add(Resource.IRON);
            add(Resource.COAL);
            add(Resource.GEMS);
            add(Resource.GOLD);
            add(Resource.SILVER);
        }
    }, TechnologyType.MINING, new ArrayList<TerrainTypeOrTerrainFeatureType>() {
        {
            add(TerrainFeature.FOREST);
            add(TerrainType.PLAIN);
            add(TerrainType.DESERT);
            add(TerrainType.TUNDRA);
            add(TerrainFeature.JUNGLE);
            add(TerrainType.SNOW);
            add(TerrainType.HILLS);
        }
    }),
    PASTURE(0, 0, 0, new ArrayList<Resource>() {
        {
            add(Resource.HORSE);
            add(Resource.CATTLE);
            add(Resource.SHEEP);
        }
    }, TechnologyType.ANIMALHUSBANDARY, new ArrayList<TerrainTypeOrTerrainFeatureType>() {
        {
            add(TerrainType.GRASSLLAND);
            add(TerrainType.PLAIN);
            add(TerrainType.DESERT);
            add(TerrainType.TUNDRA);
            add(TerrainType.HILLS);
        }
    }),
    PLANTATION(0, 0, 0, new ArrayList<Resource>() {
        {
            add(Resource.BANANA);
            add(Resource.DYES);
            add(Resource.SILK);
            add(Resource.SUGAR);
            add(Resource.COTTON);
            add(Resource.INCENSE);
        }
    }, TechnologyType.CALENDER, new ArrayList<TerrainTypeOrTerrainFeatureType>() {
        {
            add(TerrainType.GRASSLLAND);
            add(TerrainType.PLAIN);
            add(TerrainType.DESERT);
            add(TerrainFeature.FOREST);
            add(TerrainFeature.MARSH);
            add(TerrainFeature.FLOODPLAINS);
            add(TerrainFeature.JUNGLE);
        }
    }),
    QUARRY(0, 0, 0, new ArrayList<Resource>() {
        {
            add(Resource.MARBLE);
        }
    }, TechnologyType.MASONRY, new ArrayList<TerrainTypeOrTerrainFeatureType>() {
        {
            add(TerrainType.GRASSLLAND);
            add(TerrainType.PLAIN);
            add(TerrainType.DESERT);
            add(TerrainType.TUNDRA);
            add(TerrainType.HILLS);
        }
    }),
    TRADINGPOST(0, 0, 1, null, TechnologyType.TRAPPING, new ArrayList<TerrainTypeOrTerrainFeatureType>() {
        {
            add(TerrainType.GRASSLLAND);
            add(TerrainType.PLAIN);
            add(TerrainType.DESERT);
            add(TerrainType.TUNDRA);
        }
    }),
    FACTORY(0, 2, 0, null, TechnologyType.ENGINEERING, new ArrayList<TerrainTypeOrTerrainFeatureType>() {
        {
            add(TerrainType.GRASSLLAND);
            add(TerrainType.PLAIN);
            add(TerrainType.DESERT);
            add(TerrainType.TUNDRA);
            add(TerrainType.SNOW);
        }
    }),
    ROAD(0, 0, 0, null, null, null);
    //TODO ROAD RO HANDLE KON

    final int food;
    final int production;
    final int gold;
    final ArrayList<Resource> improvesResources;
    final TechnologyType requiredTechnology;
    final ArrayList<TerrainTypeOrTerrainFeatureType> canBeBuiltON; //TODO ... terraintype and terrainfeaturetype

    Improvement(int food, int production, int gold, ArrayList<Resource> improvesResources, TechnologyType requiredTechnology, ArrayList<TerrainTypeOrTerrainFeatureType> canBeBuiltOn) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.improvesResources = improvesResources;
        this.requiredTechnology = requiredTechnology;
        this.canBeBuiltON = canBeBuiltOn;
    }

    public ArrayList<Resource> getImprovesResources() {
        return improvesResources;
    }

    public ArrayList<TerrainTypeOrTerrainFeatureType> getCanBeBuiltON() {
        return canBeBuiltON;
    }

    public TechnologyType getRequiredTechnology() {
        return requiredTechnology;
    }

    public boolean checkIsPossible(Terrain terrain) {
        if (!GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesResearched().contains(requiredTechnology))
            return false;
        if (canBeBuiltON.contains(terrain.getType())){
            return true;
        }
        for (TerrainFeature feature : terrain.getTerrainFeatures()) {
            if (canBeBuiltON.contains(feature)){
                return true;
            }
        }
        return false;
    }

}
