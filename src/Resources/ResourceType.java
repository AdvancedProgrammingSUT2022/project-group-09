package Resources;

import Improvements.Improvement;
import Technologies.Technology;
import Technologies.TechnologyType;
import Terrains.Terrain;
import Terrains.TerrainType;

import java.util.ArrayList;

public enum ResourceType {
    BANANA(1, 0, 0, new ArrayList<TerrainType>() {{
        add(TerrainType.JUNGLE);
    }}, Improvement.PASTURE, null),

    CATTLE(1, 0, 0, new ArrayList<TerrainType>() {{
        add(TerrainType.GRASSLLAND);
    }}, Improvement.PASTURE, null),

    DEER(1, 0, 0, new ArrayList<TerrainType>() {{
        add(TerrainType.FOREST);
        add(TerrainType.TUNDRA);
        add(TerrainType.HILLS);
    }}, Improvement.CAMP, null),

    SHEEP(1, 0, 0, new ArrayList<TerrainType>() {{
        add(TerrainType.GRASSLLAND);
        add(TerrainType.PLAIN);
        add(TerrainType.DESERT);
        add(TerrainType.HILLS);
    }}, Improvement.PASTURE, null),


    COAL(0, 1, 0, new ArrayList<TerrainType>() {{
        add(TerrainType.GRASSLLAND);
        add(TerrainType.PLAIN);
        add(TerrainType.HILLS);
    }}, Improvement.MINE, TechnologyType.SCIENTIFICTHEORY),

    HORSE(0, 1, 0, new ArrayList<TerrainType>() {{
        add(TerrainType.GRASSLLAND);
        add(TerrainType.PLAIN);
        add(TerrainType.TUNDRA);
    }}, Improvement.PASTURE, TechnologyType.ANIMALHUSBANDARY),

    IRON(0, 1, 0, new ArrayList<TerrainType>() {{
        add(TerrainType.GRASSLLAND);
        add(TerrainType.PLAIN);
        add(TerrainType.DESERT);
        add(TerrainType.TUNDRA);
        add(TerrainType.SNOW);
        add(TerrainType.HILLS);
    }}, Improvement.MINE, TechnologyType.IRONWORKING),

    COTTON(0, 0, 2, new ArrayList<TerrainType>() {{
        add(TerrainType.GRASSLLAND);
        add(TerrainType.PLAIN);
        add(TerrainType.DESERT);
    }}, Improvement.PLANTATION, null),

    DYES(0, 0, 2, new ArrayList<TerrainType>() {{
        add(TerrainType.JUNGLE);
        add(TerrainType.FOREST);
    }}, Improvement.PLANTATION, null),

    FURS(0, 0, 2, new ArrayList<TerrainType>() {{
        add(TerrainType.FOREST);
        add(TerrainType.TUNDRA);
    }}, Improvement.CAMP, null),

    GEM(0, 0, 3, new ArrayList<TerrainType>() {{
        add(TerrainType.JUNGLE);
        add(TerrainType.GRASSLLAND);
        add(TerrainType.PLAIN);
        add(TerrainType.DESERT);
        add(TerrainType.TUNDRA);
        add(TerrainType.HILLS);
    }}, Improvement.MINE, null),

    GOLD(0, 0, 2, new ArrayList<TerrainType>() {{
        add(TerrainType.GRASSLLAND);
        add(TerrainType.PLAIN);
        add(TerrainType.DESERT);
        add(TerrainType.HILLS);
    }}, Improvement.MINE, null),

    INCENSE(0, 0, 2, new ArrayList<TerrainType>() {{
        add(TerrainType.PLAIN);
        add(TerrainType.DESERT);
    }}, Improvement.PLANTATION, null),

    IVORY(0, 0, 2, new ArrayList<TerrainType>() {{
        add(TerrainType.PLAIN);
    }}, Improvement.CAMP, null),

    MARBLE(0, 0, 2, new ArrayList<TerrainType>() {{
        add(TerrainType.GRASSLLAND);
        add(TerrainType.PLAIN);
        add(TerrainType.DESERT);
        add(TerrainType.TUNDRA);
        add(TerrainType.HILLS);
    }}, Improvement.QUARRY, null),

    SILK(0, 0, 2, new ArrayList<TerrainType>() {{
        add(TerrainType.FOREST);
    }}, Improvement.PLANTATION, null),

    SILVER(0, 0, 2, new ArrayList<TerrainType>() {{
        add(TerrainType.TUNDRA);
        add(TerrainType.DESERT);
        add(TerrainType.HILLS);
    }}, Improvement.MINE, null),

    SUGAR(0, 0, 2, new ArrayList<TerrainType>() {{
        add(TerrainType.FLOODPLAIN);
        add(TerrainType.MARSH);
    }}, Improvement.PLANTATION, null),


    final int food;
    final int production;
    final int gold;
    final ArrayList<TerrainType> canBeFoundOn;
    final Improvement requiredImprovement;
    final Technology requiredTechnology;

    ResourceType(int food, int production, int gold, ArrayList<TerrainType> canBeFoundOn, Improvement requiredImprovement, Technology requiredTechnology) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.canBeFoundOn = canBeFoundOn;
        this.requiredImprovement = requiredImprovement;
        this.requiredTechnology = requiredTechnology;
    }
    }
