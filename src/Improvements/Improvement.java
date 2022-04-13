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
    CAMP(0, 0, 0, new ArrayList<ResourceType>(){
        {
            add(ResourceType.IVORY);
            add(ResourceType.FURS);
            add(ResourceType.DEER);
        }
    }, TechnologyType.TRAPPING, new ArrayList<Object>(){
        {
            add(TerrainFeatureType.FOREST);
            add(TerrainType.TUNDRA);
            add(TerrainType.PLAIN);
            add(TerrainType.HILLS);
        }
    }),
    FARM(1, 0, 0, new ArrayList<ResourceType>(){
        {
            add(ResourceType.WHEAT);
        }
    }, TechnologyType.AGRICULTURE, new ArrayList<Object>(){
        {
            add(TerrainType.GRASSLLAND);
            add(TerrainType.PLAIN);
            add(TerrainType.DESERT);
        }
    }),
    LUMBERMILL(0, 1, 0, null, TechnologyType.ENGINEERING, new ArrayList<Object>(){
        {
            add(TerrainFeatureType.FOREST);
        }
    }),
    MINE(0, 1, 0,  new ArrayList<ResourceType>(){
        {
            add(ResourceType.WHEAT);
            add(ResourceType.IRON);
            add(ResourceType.COAL);
            add(ResourceType.ALUMINUME);
            add(ResourceType.URANIUM);
            add(ResourceType.GEM);
            add(ResourceType.GOLD);
            add(ResourceType.SILVER);
        }
    }, TechnologyType.MINIMG, new ArrayList<Object>(){
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
    PASTURE(0, 0, 0,  new ArrayList<ResourceType>(){
        {
            add(ResourceType.HORSES);
            add(ResourceType.CATTLE);
            add(ResourceType.SHEEP);
        }
    }, TechnologyType.ANIMALHUSBANDRY, new ArrayList<Object>(){
        {
            add(TerrainType.GRASSLLAND);
            add(TerrainType.PLAIN);
            add(TerrainType.DESERT);
            add(TerrainType.TUNDRA);
            add(TerrainType.HILLS);
        }
    }),
    PLANTATION(0, 0, 0,  new ArrayList<ResourceType>(){
        {
            add(ResourceType.BANANA);
            add(ResourceType.DYES);
            add(ResourceType.SILK);
            add(ResourceType.SPICES);
            add(ResourceType.SUGAR);
            add(ResourceType.COTTON);
            add(ResourceType.WINE);
            add(ResourceType.INCENSE);
        }
    }, TechnologyType.CALENDAR, new ArrayList<Object>(){
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
    QUARRY(0, 0, 0,  new ArrayList<ResourceType>(){
        {
            add(ResourceType.MARBLE);
        }
    }, TechnologyType.MASONRY, new ArrayList<Object>(){
        {
            add(TerrainType.GRASSLLAND);
            add(TerrainType.PLAIN);
            add(TerrainType.DESERT);
            add(TerrainType.TUNDRA);
            add(TerrainType.HILLS);
        }
    }),
    TRADINGPOST(0, 0, 1,  null, TechnologyType.TRAPPING, new ArrayList<Object>(){
        {
            add(TerrainType.GRASSLLAND);
            add(TerrainType.PLAIN);
            add(TerrainType.DESERT);
            add(TerrainType.TUNDRA);
        }
    }),
    FACTORY(0, 2, 0, null, TechnologyType.ENGINEERING, new ArrayList<Object>(){
        {
            add(TerrainType.GRASSLLAND);
            add(TerrainType.PLAIN);
            add(TerrainType.DESERT);
            add(TerrainType.TUNDRA);
            add(TerrainType.SNOW);
        }
    });

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
