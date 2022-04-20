package com.civilization.Model.Units;

import com.civilization.Model.Resources.ResourceType;
import com.civilization.Model.TechnologyPackage.TechnologyType;

public enum UnitType implements Combatble {
    ARCHER(70, CombatType.ARCHERY, 4, 6, 2, 2, null, TechnologyType.ARCHERY) {
        @Override
        public void attack() {

        }
    },
    CHARIOTARCHER(60, CombatType.MOUNTED, 3, 6, 2, 4, ResourceType.HORSE, TechnologyType.THEWHEEL) {
        @Override
        public void attack() {

        }
    },
    SCOUT(25, CombatType.RECON, 4, 0, 0, 2, null, null) {
        @Override
        public void attack() {

        }
    },
    SETTLER(89, CombatType.CIVILIAN, 0, 0, 1, 2, null, null) {
        @Override
        public void attack() {

        }
    },
    SPEARMAN(50, CombatType.MELEE, 6, 0, 1, 2, null, null) {
        @Override
        public void attack() {

        }
    },
    WARRIOR(40, CombatType.MELEE, 6, 0, 1, 2, null, null) {
        @Override
        public void attack() {

        }
    },
    WORKER(70, CombatType.CIVILIAN, 0, 0, 1, 2, null, null) {
        @Override
        public void attack() {

        }
    },
    CATAPULT(100, CombatType.SIEGE, 4, 14, 2, 2, ResourceType.IRON, TechnologyType.MATHEMATICS) {
        @Override
        public void attack() {

        }
    },
    HORSEMAN(80, CombatType.MOUNTED, 12, 0, 1, 4, ResourceType.HORSE, TechnologyType.HORSEBACKRIDING) {
        @Override
        public void attack() {

        }
    },
    SWORDSMAN(80, CombatType.MELEE, 11, 0, 1, 2, ResourceType.IRON, TechnologyType.IRONWORKING) {
        @Override
        public void attack() {

        }
    },
    CROSSBOWMAN(120, CombatType.ARCHERY, 6, 12, 2, 2, null, TechnologyType.MACHINERY) {
        @Override
        public void attack() {

        }
    },
    KNIGHT(150, CombatType.MOUNTED, 18, 0, 1, 3, ResourceType.HORSE, TechnologyType.CHIVALRY) {
        @Override
        public void attack() {

        }
    },
    LONGSWORDMAN(150, CombatType.MELEE, 18, 0, 1, 3, ResourceType.IRON, TechnologyType.STEEL) {
        @Override
        public void attack() {

        }
    },
    PIKEMAN(100, CombatType.MELEE, 10, 0, 1, 2, null, TechnologyType.CIVILISERVICE) {
        @Override
        public void attack() {

        }
    },
    TREBUCHET(170, CombatType.SIEGE, 6, 20, 2, 2, ResourceType.IRON, TechnologyType.PHYSICS) {
        @Override
        public void attack() {

        }
    },
    CANON(250, CombatType.SIEGE, 10, 26, 2, 2, null, TechnologyType.CHEMISTRY) {
        @Override
        public void attack() {

        }
    },
    CAVALRY(260, CombatType.MOUNTED, 25, 0, 1, 3, ResourceType.HORSE, TechnologyType.MILITARYSCIENCE) {
        @Override
        public void attack() {

        }
    },
    LANCER(220, CombatType.MOUNTED, 22, 0, 1, 4, ResourceType.HORSE, TechnologyType.METALLURGY) {
        @Override
        public void attack() {

        }
    },
    MUSKETMAN(120, CombatType.GUNPOWDER, 16, 0, 1, 2, null, TechnologyType.GUNPOWDER) {
        @Override
        public void attack() {

        }
    },
    RIFLEMAN(200, CombatType.GUNPOWDER, 25, 0, 1, 2, null, TechnologyType.RIFLING) {
        @Override
        public void attack() {

        }
    },
    ANTITANKGUN(300, CombatType.GUNPOWDER, 32, 0, 1, 2, null, TechnologyType.REPLACEABLEPARTS) {
        @Override
        public void attack() {

        }
    },
    ARTILLERY(420, CombatType.SIEGE, 16, 32, 3, 2, null, TechnologyType.DYNAMITE) {
        @Override
        public void attack() {

        }
    },
    INFANTRY(300, CombatType.GUNPOWDER, 36, 0, 1, 2, null, TechnologyType.REPLACEABLEPARTS) {
        @Override
        public void attack() {

        }
    },
    PANZER(450, CombatType.ARMORED, 60, 0, 1, 5, null, TechnologyType.COMBUSTION) {
        @Override
        public void attack() {

        }
    },
    TANK(450, CombatType.ARMORED, 50, 0, 1, 4, null, TechnologyType.COMBUSTION) {
        @Override
        public void attack() {

        }
    };


    UnitType(int cost, CombatType combatType, int combatStrengh, int rangedCombatStrengh, int range, int movement, ResourceType requiredResourse, TechnologyType requiredTechnology) {
        this.cost = cost;
        this.combatType = combatType;
        this.combatStrength = combatStrengh;
        this.rangedCombatStrengh = rangedCombatStrengh;
        Range = range;
        this.movement = movement;
        this.requiredResourse = requiredResourse;
        this.requiredTechnology = requiredTechnology;
    }

    public int getCost() {
        return cost;
    }

    public CombatType getCombatType() {
        return combatType;
    }

    public int getCombatStrengh() {
        return combatStrength;
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

    final int cost;
    private final CombatType combatType;

    private final int combatStrength;
    private final int rangedCombatStrengh;
    private final int Range;
    private final int movement;
    private final ResourceType requiredResourse;
    private final TechnologyType requiredTechnology;

}
