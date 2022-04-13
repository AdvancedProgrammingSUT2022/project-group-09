package Units;

import Resources.Resource;
import Technologies.Technology;

public enum UnitType {
    ARCHER(70, CombatType.ARCHERY,4,6,2,2,null,null );

    UnitType(int cost, CombatType combatType, int combatStrengh, int rangedCombatStrengh, int range, int movement, Resource requiredResourse, Technology requiredTechnology) {
        this.cost = cost;
        this.combatType = combatType;
        this.combatStrengh = combatStrengh;
        this.rangedCombatStrengh = rangedCombatStrengh;
        Range = range;
        this.movement = movement;
        this.requiredResourse = requiredResourse;
        this.requiredTechnology = requiredTechnology;
    }

    final int cost;

    public int getCost() {
        return cost;
    }

    public CombatType getCombatType() {
        return combatType;
    }

    public int getCombatStrengh() {
        return combatStrengh;
    }

    public int getRangedCombatStrengh() {
        return rangedCombatStrengh;
    }

    public int getRange() {
        return Range;
    }

    public int getMovement() {
        return movement;
    }

    public Resource getRequiredResourse() {
        return requiredResourse;
    }

    public Technology getRequiredTechnology() {
        return requiredTechnology;
    }

    private final CombatType combatType;
    private final int combatStrengh;
    private final int rangedCombatStrengh;
    private final int Range;
    private final int movement;
    private final Resource requiredResourse;
    private final Technology requiredTechnology;
}
