package com.civilization.Model.Units;

import com.civilization.Model.Resources.Resource;
import com.civilization.Model.TechnologyPackage.TechnologyType;

public enum UnitType implements Combatble {
    ARCHER(70, CombatType.ARCHERY, 4, 6, 2, 2, null, TechnologyType.ARCHERY) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    CHARIOTARCHER(60, CombatType.MOUNTED, 3, 6, 2, 4, Resource.HORSE, TechnologyType.THEWHEEL) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    SCOUT(25, CombatType.RECON, 4, 0, 1, 2, null, null) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    SETTLER(89, CombatType.CIVILIAN, 0, 0, 1, 2, null, null) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    SPEARMAN(50, CombatType.MELEE, 6, 0, 1, 2, null, null) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    WARRIOR(40, CombatType.MELEE, 6, 0, 1, 2, null, null) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    WORKER(70, CombatType.CIVILIAN, 0, 0, 1, 2, null, null) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    CATAPULT(100, CombatType.SIEGE, 4, 14, 2, 2, Resource.IRON, TechnologyType.MATHEMATICS) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    HORSEMAN(80, CombatType.MOUNTED, 12, 0, 1, 4, Resource.HORSE, TechnologyType.HORSEBACKRIDING) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    SWORDSMAN(80, CombatType.MELEE, 11, 0, 1, 2, Resource.IRON, TechnologyType.IRONWORKING) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    CROSSBOWMAN(120, CombatType.ARCHERY, 6, 12, 2, 2, null, TechnologyType.MACHINERY) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    KNIGHT(150, CombatType.MOUNTED, 18, 0, 1, 3, Resource.HORSE, TechnologyType.CHIVALRY) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    LONGSWORDMAN(150, CombatType.MELEE, 18, 0, 1, 3, Resource.IRON, TechnologyType.STEEL) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    PIKEMAN(100, CombatType.MELEE, 10, 0, 1, 2, null, TechnologyType.CIVILISERVICE) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    TREBUCHET(170, CombatType.SIEGE, 6, 20, 2, 2, Resource.IRON, TechnologyType.PHYSICS) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    CANON(250, CombatType.SIEGE, 10, 26, 2, 2, null, TechnologyType.CHEMISTRY) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    CAVALRY(260, CombatType.MOUNTED, 25, 0, 1, 3, Resource.HORSE, TechnologyType.MILITARYSCIENCE) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    LANCER(220, CombatType.MOUNTED, 22, 0, 1, 4, Resource.HORSE, TechnologyType.METALLURGY) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    MUSKETMAN(120, CombatType.GUNPOWDER, 16, 0, 1, 2, null, TechnologyType.GUNPOWDER) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    RIFLEMAN(200, CombatType.GUNPOWDER, 25, 0, 1, 2, null, TechnologyType.RIFLING) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    ANTITANKGUN(300, CombatType.GUNPOWDER, 32, 0, 1, 2, null, TechnologyType.REPLACEABLEPARTS) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    ARTILLERY(420, CombatType.SIEGE, 16, 32, 3, 2, null, TechnologyType.DYNAMITE) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    INFANTRY(300, CombatType.GUNPOWDER, 36, 0, 1, 2, null, TechnologyType.REPLACEABLEPARTS) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    PANZER(450, CombatType.ARMORED, 60, 0, 1, 5, null, TechnologyType.COMBUSTION) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    },
    TANK(450, CombatType.ARMORED, 50, 0, 1, 4, null, TechnologyType.COMBUSTION) {
        @Override
        public void attack(Combatble target) {

        }

        @Override
        public void defend(Combatble target) {

        }
    };


    UnitType(int cost, CombatType combatType, int combatStrengh, int rangedCombatStrengh, int range, int movement, Resource requiredResourse, TechnologyType requiredTechnology) {
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

    public Resource getRequiredResourse() {
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
    private final Resource requiredResourse;
    private final TechnologyType requiredTechnology;

}
