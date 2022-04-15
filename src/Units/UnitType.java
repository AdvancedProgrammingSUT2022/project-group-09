package Units;

import Resources.Resource;
import Resources.ResourceType;
import Technologies.Technology;
import Technologies.TechnologyType;

public enum UnitType {
    ARCHER(70, CombatType.ARCHERY,4,6,2,2,null, TechnologyType.ARCHERY),
    CHARIOTARCHER(60, CombatType.MOUNTED,3,6,2,4, ResourceType.HORSE, TechnologyType.THEWHEEL),
    SCOUT(25, CombatType.RECON,4,0,0,2, null, null),
    SETTLER(89, CombatType.CIVILIAN,0,0,0,2,null,null),
    SPEARMAN(50, CombatType.MELEE,6,0,0,2,null,null),
    WARRIOR(40, CombatType.MELEE,6,0,0,2,null,null),
    WORKER(70, CombatType.CIVILIAN,0,0,0,2,null,null),
    CATAPULT(100, CombatType.SIEGE,4,14,2,2, ResourceType.IRON, TechnologyType.MATHEMATICS),
    HORSEMAN(80, CombatType.MOUNTED,12,0,0,4, ResourceType.HORSE, TechnologyType.HORSEBACKRIDING),
    SWORDSMAN(80, CombatType.MELEE,11,0,0,2, ResourceType.IRON, TechnologyType.IRONWORKING),
    CROSSBOWMAN(120, CombatType.ARCHERY,6,12,2,2, null, TechnologyType.MACHINERY),
    KNIGHT(150, CombatType.MOUNTED,18,0,0,3, ResourceType.HORSE, TechnologyType.CHIVALRY),
    LONGSWORDMAN(150, CombatType.MELEE,18,0,0,3, ResourceType.IRON, TechnologyType.STEEL),
    PIKEMAN(100, CombatType.MELEE,10,0,0,2,null, TechnologyType.CIVILSERVICE),
    TREBUCHET(170, CombatType.SIEGE,6,20,2,2, ResourceType.IRON, TechnologyType.PHYSICS),
    CANON(250, CombatType.SIEGE,10,26,2,2, null, TechnologyType.CHEMISTRY),
    CAVALRY(260, CombatType.MOUNTED,25,0,0,3, ResourceType.HORSE, TechnologyType.MILITARYSCIENCE),
    LANCER(220, CombatType.MOUNTED,22,0,0,4, ResourceType.HORSE, TechnologyType.METALLURGY),
    MUSKETMAN(120, CombatType.GUNPOWDER,16,0,0,2,null, TechnologyType.GUNPOWDER),
    RIFLEMAN(200, CombatType.GUNPOWDER,25,0,0,2,null, TechnologyType.RIFLING),
    ANTITANKGUN(300, CombatType.GUNPOWDER,32,0,0,2,null, TechnologyType.REPLACEABLEPARTS),
    ARTILLERY(420, CombatType.SIEGE,16,32,3,2, null, TechnologyType.DYNAMITE),
    INFANTRY(300, CombatType.GUNPOWDER,36,0,0,2, null, TechnologyType.REPLACEABLEPARTS),
    PANZER(450, CombatType.ARMORED,60,0,0,5, null, TechnologyType.COMBUSTION),
    TANK(450, CombatType.ARMORED,50,0,0,4, null, TechnologyType.COMBUSTION);

    
    UnitType(int cost, CombatType combatType, int combatStrengh, int rangedCombatStrengh, int range, int movement, ResourceType requiredResourse, TechnologyType requiredTechnology) {
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

    public ResourceType getRequiredResourse() {
        return requiredResourse;
    }

    public TechnologyType getRequiredTechnology() {
        return requiredTechnology;
    }

    private final CombatType combatType;
    private final int combatStrengh;
    private final int rangedCombatStrengh;
    private final int Range;
    private final int movement;
    private final ResourceType requiredResourse;
    private final TechnologyType requiredTechnology;
}
