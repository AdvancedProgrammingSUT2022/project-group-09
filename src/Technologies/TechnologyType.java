package Technologies;

import java.util.ArrayList;

public enum TechnologyType {
    kossher();

    ArrayList<TechnologyType> technologyUnlocks;
    ArrayList<TechnologyType> requirement;
    ArrayList<Object> unlocks;
    int cost;

}
