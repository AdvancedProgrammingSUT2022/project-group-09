package Buildings;

import java.util.ArrayList;
import java.util.List;

import Technologies.Technology;

public enum BuildingType {
    Barracks(80,60,null);

    BuildingType(int cost, int maintenance, Technology requirement) {
        this.cost = cost;
        this.maintenance = maintenance;
        this.requirement = requirement;
    }

    final int cost;
    final int maintenance;
    final Technology requirement;

}
