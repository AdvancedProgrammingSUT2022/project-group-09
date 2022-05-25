package civilization.civilization.main.java.com.civilization.Model.Terrains;

import civilization.civilization.main.java.com.civilization.Model.Resources.Resource;
import civilization.civilization.main.java.com.civilization.Model.Resources.TerrainTypeOrTerrainFeatureType;
import civilization.civilization.main.java.com.civilization.Model.TerrainFeatures.TerrainFeature;

import java.util.ArrayList;

public enum TerrainType implements TerrainTypeOrTerrainFeatureType {
    DESERT(0, 0, 0, -33, 1, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.RIVER);
            add(TerrainFeature.OASIS);
            add(TerrainFeature.FLOODPLAINS);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.IRON);
            add(Resource.GOLD);
            add(Resource.SILVER);
            add(Resource.GEMS);
            add(Resource.MARBLE);
            add(Resource.COTTON);
            add(Resource.INCENSE);
            add(Resource.SHEEP);
        }
    }),
    GRASSLLAND(2, 0, 0, -33, 1, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.RIVER);
            add(TerrainFeature.FOREST);
            add(TerrainFeature.MARSH);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.IRON);
            add(Resource.HORSE);
            add(Resource.COAL);
            add(Resource.CATTLE);
            add(Resource.GOLD);
            add(Resource.GEMS);
            add(Resource.MARBLE);
            add(Resource.COTTON);
            add(Resource.SHEEP);
        }
    }),
    HILLS(0, 2, 0, 25, 2, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.RIVER);
            add(TerrainFeature.FOREST);
            add(TerrainFeature.JUNGLE);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.IRON);
            add(Resource.COAL);
            add(Resource.DEER);
            add(Resource.GOLD);
            add(Resource.SILVER);
            add(Resource.GEMS);
            add(Resource.MARBLE);
            add(Resource.SHEEP);
        }
    }),
    MOUNTAIN(0, 0, 0, 25, Integer.MAX_VALUE,  new ArrayList<TerrainFeature>(),  new ArrayList<Resource>()),
    OCEAN(1, 0, 1, 0, Integer.MAX_VALUE, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.ICE);
        }
    }, new ArrayList<Resource>() {
        {

        }
    }),
    PLAIN(1, 1, 0, -33, 1, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.RIVER);
            add(TerrainFeature.FOREST);
            add(TerrainFeature.JUNGLE);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.IRON);
            add(Resource.HORSE);
            add(Resource.COAL);
            add(Resource.WHEAT);
            add(Resource.GOLD);
            add(Resource.GEMS);
            add(Resource.MARBLE);
            add(Resource.IVORY);
            add(Resource.COTTON);
            add(Resource.INCENSE);
            add(Resource.SHEEP);
        }
    }),
    SNOW(0, 0, 0, -33, 1, new ArrayList<TerrainFeature>(), new ArrayList<Resource>() {
        {
            add(Resource.IRON);
        }
    }),
    TUNDRA(1, 0, 0, -33, 1, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.FOREST);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.IRON);
            add(Resource.HORSE);
            add(Resource.DEER);
            add(Resource.SILVER);
            add(Resource.GEMS);
            add(Resource.MARBLE);
            add(Resource.FURS);
        }
    });

    TerrainType(int food, int product, int gold, int combatModifier, int MP, ArrayList<TerrainFeature> possibleFeatures, ArrayList<Resource> possibleResources) {
        this.food = food;
        this.product = product;
        this.gold = gold;
        this.MP = MP;
        this.combatModifier = combatModifier;
        this.possibleFeatures = possibleFeatures;
        this.possibleResources = possibleResources;
    }

    final int food;
    final int product;
    final int gold;
    final int MP;
    final int combatModifier;
    final ArrayList<TerrainFeature> possibleFeatures;
    final ArrayList<Resource> possibleResources;

    public int getFood() {
        return food;
    }

    public int getProduct() {
        return product;
    }

    public int getGold() {
        return gold;
    }

    public int getMP() {
        return MP;
    }

    public int getCombatModifier() {
        return combatModifier;
    }

    public ArrayList<TerrainFeature> getPossibleFeatures() {
        return possibleFeatures;
    }

    public ArrayList<Resource> getPossibleResources() {
        return possibleResources;
    }
}
