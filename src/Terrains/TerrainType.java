package Terrains;

public enum TerrainType {
    COAST(),
    DESERT(),
    GRASSLLAND(),
    HILLS(),
    MOUNTAIN(),
    OCEAN(),
    PLAIN(),
    SNOW(),
    TUNDRA();

    TerrainType(int food, int product, int gold, int MP) {
        this.food = food;
        this.product = product;
        this.gold = gold;
        this.MP = MP;
    }

    int food;
    int product;
    int gold;
    int MP;
}
