package Resources;

import Improvements.Improvement;
import Technologies.Technology;
import Terrains.Terrain;

import java.util.ArrayList;

public enum ResourceType {
    BANANA(1, 0, 0, null, null, null);
    final int food;
    final int production;
    final int gold;
    final ArrayList<Terrain> canBeFoundOn;
    final Improvement requiredImprovement;
    final Technology requiredTechnology;

    ResourceType(int food, int production, int gold, ArrayList<Terrain> canBeFoundOn, Improvement requiredImprovement, Technology requiredTechnology) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.canBeFoundOn = canBeFoundOn;
        this.requiredImprovement = requiredImprovement;
        this.requiredTechnology = requiredTechnology;
    }
}
