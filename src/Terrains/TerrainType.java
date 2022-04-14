package Terrains;

import Resources.Resource;
import Resources.ResourceType;
import TerrainFeatures.TerrainFeature;
import TerrainFeatures.TerrainFeatureType;

import java.util.ArrayList;

public enum TerrainType {
    DESERT(0, 0, 0, -33, 1, new ArrayList<TerrainFeatureType>() {
        {
            add(TerrainFeatureType.OASIS);
            add(TerrainFeatureType.FOODPLAINS);
        }
    }, new ArrayList<ResourceType>() {
        {
            add(ResourceType.IRON);
            add(ResourceType.OIL);
            add(ResourceType.ALUMINUM);
            add(ResourceType.URANIUM);
            add(ResourceType.GOLD);
            add(ResourceType.SILVER);
            add(ResourceType.GEM);
            add(ResourceType.MARBLE);
            add(ResourceType.COTTON);
            add(ResourceType.INCENSE);
            add(ResourceType.SHEEP);
        }
    }),
    GRASSLLAND(2, 0, 0, -33, 1, new ArrayList<TerrainFeatureType>() {
        {
            add(TerrainFeatureType.FOREST);
            add(TerrainFeatureType.MARSH);
        }
    }, new ArrayList<ResourceType>() {
        {
            add(ResourceType.IRON);
            add(ResourceType.HORSE);
            add(ResourceType.COAL);
            add(ResourceType.URANIUME);
            add(ResourceType.CATTLE);
            add(ResourceType.GOLD);
            add(ResourceType.GEM);
            add(ResourceType.MARBLE);
            add(ResourceType.COTTON);
            add(ResourceType.WINE);
            add(ResourceType.SHEEP);
        }
    }),
    HILLS(0, 2, 0, 25, 2, new ArrayList<TerrainFeatureType>() {
        {
            add(TerrainFeatureType.FOREST);
            add(TerrainFeatureType.JUNGLE);
        }
    }, new ArrayList<ResourceType>() {
        {
            add(ResourceType.IRON);
            add(ResourceType.COAL);
            add(ResourceType.ALUMINUME);
            add(ResourceType.URANIUME);
            add(ResourceType.DEER);
            add(ResourceType.GOLD);
            add(ResourceType.SILVER);
            add(ResourceType.GEM);
            add(ResourceType.MARBLE);
            add(ResourceType.SHEEP);
        }
    }),
    MOUNTAIN(0, 0, 0, 25, 9999999, null, null),
    OCEAN(1, 0, 1, 0, 1, new ArrayList<TerrainFeatureType>() {
        {
            add(TerrainFeatureType.ICE);
        }
    }, new ArrayList<ResourceType>() {
        {
            add(ResourceType.OIL);
        }
    }),
    PLAIN(1, 1, 0, -33, 1, new ArrayList<TerrainFeatureType>() {
        {
            add(TerrainFeatureType.FOREST);
            add(TerrainFeatureType.JUNGLE);
        }
    }, new ArrayList<ResourceType>() {
        {
            add(ResourceType.IRON);
            add(ResourceType.HORSE);
            add(ResourceType.COAL);
            add(ResourceType.ALUMINUM);
            add(ResourceType.URANIUM);
            add(ResourceType.WHEAT);
            add(ResourceType.GOLD);
            add(ResourceType.GEMS);
            add(ResourceType.MARBLE);
            add(ResourceType.IVORY);
            add(ResourceType.COTTON);
            add(ResourceType.WINE);
            add(ResourceType.INCENSE);
            add(ResourceType.SHEEP);
        }
    }),
    SNOW(0, 0, 0, -33, 1, null, new ArrayList<ResourceType>() {
        {
            add(ResourceType.IRON);
            add(ResourceType.OIL);
            add(ResourceType.URANIUME);
        }
    }),
    TUNDRA(1, 0, 0, -33, 1, new ArrayList<TerrainFeatureType>() {
        {
            add(TerrainFeatureType.FOREST);
        }
    }, new ArrayList<ResourceType>() {
        {
            add(ResourceType.IRON);
            add(ResourceType.HORSE);
            add(ResourceType.OIL);
            add(ResourceType.ALUMINUM);
            add(ResourceType.URANIUME);
            add(ResourceType.DEER);
            add(ResourceType.SILVER);
            add(ResourceType.GEM);
            add(ResourceType.MARBLE);
            add(ResourceType.FURS);
        }
    });

    TerrainType(int food, int product, int gold, int combatModifier, int MP, ArrayList<TerrainFeatureType> possibleFeatures, ArrayList<ResourceType> possibleResources) {
        this.food = food;
        this.product = product;
        this.gold = gold;
        this.MP = MP;
        this.combatModifier = combatModifier;
        this.possibleFeatures = possibleFeatures;
        this.possibleResources = possibleResources;
    }

    int food;
    int product;
    int gold;
    int MP;
    int combatModifier;
    ArrayList<TerrainFeatureType> possibleFeatures;
    ArrayList<ResourceType> possibleResources;
}
