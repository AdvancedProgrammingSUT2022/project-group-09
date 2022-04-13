package Improvements;

import Resources.Resource;
import Technologies.Technology;
import Terrains.Terrain;

import java.util.ArrayList;

public enum Improvement {

    CAMP(0, 0, 0, null, null, null);

    final int food;
    final int production;
    final int gold;
    final ArrayList<Resource> requiredResources;
    final Technology requiredTechnology;
    final ArrayList<Terrain> canBeBuiltON;

    Improvement(int food, int production, int gold, ArrayList<Resource> requiredResources, Technology requiredTechnology, ArrayList<Terrain> canBeBuiltOn) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.requiredResources = requiredResources;
        this.requiredTechnology = requiredTechnology;
        this.canBeBuiltON = canBeBuiltOn;
    }


}
