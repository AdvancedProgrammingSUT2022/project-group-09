package Model.Improvements;

import Model.Resources.ResourceType;
import Model.Resources.TerrainTypeOrTerrainFeatureType;
import Model.TechnologyPackage.TechnologyType;
import Model.TerrainFeatures.TerrainFeatureType;
import Model.Terrains.TerrainType;

import java.util.ArrayList;

public enum Improvement {
    CAMP(0, 0, 0, new ArrayList<ResourceType>() {
        {
            add(ResourceType.IVORY);
            add(ResourceType.FURS);
            add(ResourceType.DEER);
        }
    }, TechnologyType.TRAPPING, new ArrayList<TerrainTypeOrTerrainFeatureType>() {
        {
            add(TerrainFeatureType.FOREST);
            add(TerrainType.TUNDRA);
            add(TerrainType.PLAIN);
            add(TerrainType.HILLS);
        }
    }),
    FARM(1, 0, 0, new ArrayList<ResourceType>() {
        {
            add(ResourceType.WHEAT);
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
            add(TerrainFeatureType.FOREST);
        }
    }),
    MINE(0, 1, 0, new ArrayList<ResourceType>() {
        {
            add(ResourceType.WHEAT);
            add(ResourceType.IRON);
            add(ResourceType.COAL);
            add(ResourceType.GEMS);
            add(ResourceType.GOLD);
            add(ResourceType.SILVER);
        }
    }, TechnologyType.MINING, new ArrayList<TerrainTypeOrTerrainFeatureType>() {
        {
            add(TerrainFeatureType.FOREST);
            add(TerrainType.PLAIN);
            add(TerrainType.DESERT);
            add(TerrainType.TUNDRA);
            add(TerrainFeatureType.JUNGLE);
            add(TerrainType.SNOW);
            add(TerrainType.HILLS);
        }
    }),
    PASTURE(0, 0, 0, new ArrayList<ResourceType>() {
        {
            add(ResourceType.HORSE);
            add(ResourceType.CATTLE);
            add(ResourceType.SHEEP);
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
    PLANTATION(0, 0, 0, new ArrayList<ResourceType>() {
        {
            add(ResourceType.BANANA);
            add(ResourceType.DYES);
            add(ResourceType.SILK);
            add(ResourceType.SUGAR);
            add(ResourceType.COTTON);
            add(ResourceType.INCENSE);
        }
    }, TechnologyType.CALENDER, new ArrayList<TerrainTypeOrTerrainFeatureType>() {
        {
            add(TerrainType.GRASSLLAND);
            add(TerrainType.PLAIN);
            add(TerrainType.DESERT);
            add(TerrainFeatureType.FOREST);
            add(TerrainFeatureType.MARSH);
            add(TerrainFeatureType.FLOODPLAINS);
            add(TerrainFeatureType.JUNGLE);
        }
    }),
    QUARRY(0, 0, 0, new ArrayList<ResourceType>() {
        {
            add(ResourceType.MARBLE);
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
    final ArrayList<ResourceType> improvesResources;
    final TechnologyType requiredTechnology;
    final ArrayList<TerrainTypeOrTerrainFeatureType> canBeBuiltON; //TODO ... terraintype and terrainfeaturetype

    Improvement(int food, int production, int gold, ArrayList<ResourceType> improvesResources, TechnologyType requiredTechnology, ArrayList<TerrainTypeOrTerrainFeatureType> canBeBuiltOn) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.improvesResources = improvesResources;
        this.requiredTechnology = requiredTechnology;
        this.canBeBuiltON = canBeBuiltOn;
    }


}
