package TerrainFeatures;

import Resources.Resource;
import Resources.ResourceType;

import java.util.ArrayList;

public enum TerrainFeatureType {
    FLOODPLAINS(2, 0, 0, -33, 1, new ArrayList<ResourceType>(){
        {
            add(ResourceType.WHEAT);
            add(ResourceType.SUGAR);
        }
    }),
    FOREST(1, 1, 0, 25, 2, new ArrayList<ResourceType>(){
        {
            add(ResourceType.URANIUM);
            add(ResourceType.DEER);
            add(ResourceType.FURS);
            add(ResourceType.DYES);
            add(ResourceType.SILK);
        }
    }),
    ICE(0, 0, 0, 0, 99999, null),
    JUNGLE(1, -1, 0, 25, 2, new ArrayList<ResourceType>(){
        {
            add(ResourceType.OIL);
            add(ResourceType.URANIUM);
            add(ResourceType.BANANA);
            add(ResourceType.GEM);
            add(ResourceType.DYES);
            add(ResourceType.SPICES);
        }
    }),
    MARSH(-1, 0, 0, -33, 2, new ArrayList<ResourceType>(){
        {
            add(ResourceType.OIL);
            add(ResourceType.URANIUM);
            add(ResourceType.SUGAR);
        }
    }),
    OASIS(3, 0, 1, -33, 1, null),
    RIVER(0, 0, 1, 0, -1, null);
    //TODO... -1 -> all remanining points, 999999 -> impossible

    TerrainFeatureType(int food, int product, int gold, int combatModifier, int MP, ArrayList<ResourceType> possibleFeatures) {
        this.food = food;
        this.product = product;
        this.gold = gold;
        this.MP = MP;
        this.combatModifier = combatModifier;
        this.possibleFeatures = possibleFeatures;
    }

    int food;
    int product;
    int gold;
    int MP;
    int combatModifier;
    ArrayList<ResourceType> possibleFeatures;
}
