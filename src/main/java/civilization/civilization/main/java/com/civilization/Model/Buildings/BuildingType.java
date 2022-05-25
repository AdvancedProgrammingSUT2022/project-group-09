package civilization.civilization.main.java.com.civilization.Model.Buildings;

import civilization.civilization.main.java.com.civilization.Model.TechnologyPackage.TechnologyType;

import java.util.ArrayList;

public enum BuildingType implements BuildingNote {
    BARRACKS(80, 1, TechnologyType.BRONZEWORKING) {
        @Override
        public void doNote() {

        }
    },
    GRANARY(100, 1, TechnologyType.POTTERY) {
        @Override
        public void doNote() {

        }
    },
    LIBRARY(80, 1, TechnologyType.WRITING) {
        @Override
        public void doNote() {

        }
    },
    MONUMENT(60, 1, null) {
        @Override
        public void doNote() {

        }
    },
    WALLS(100, 1, TechnologyType.MASONRY) {
        @Override
        public void doNote() {

        }
    },
    WATERMILL(120, 2, TechnologyType.THEWHEEL) {
        @Override
        public void doNote() {

        }
    },
    ARMORY(130, 3, TechnologyType.IRONWORKING) {
        @Override
        public void doNote() {

        }
    },
    BURIALTOMB(120, 0, TechnologyType.PHILOSOPHY) {
        @Override
        public void doNote() {

        }
    },
    CIRCUS(150, 3, TechnologyType.HORSEBACKRIDING) {
        @Override
        public void doNote() {

        }
    },
    COLOSSEUM(150, 3, TechnologyType.CONSTRUCTION) {
        @Override
        public void doNote() {

        }
    },
    COURTHOUSE(200, 5, TechnologyType.MATHEMATICS) {
        @Override
        public void doNote() {

        }
    },
    STABLE(100, 1, TechnologyType.HORSEBACKRIDING) {
        @Override
        public void doNote() {

        }
    },
    TEMPLE(120, 2, TechnologyType.PHILOSOPHY) {
        @Override
        public void doNote() {

        }
    },
    CASTLE(200, 3, TechnologyType.CHIVALRY) {
        @Override
        public void doNote() {

        }
    },
    FORGE(150, 2, TechnologyType.METALCASTING) {
        @Override
        public void doNote() {

        }
    },
    GARDEN(120, 2, TechnologyType.THEOLOGY) {
        @Override
        public void doNote() {

        }
    },
    MARKET(120, 0, TechnologyType.CURRENCY) {
        @Override
        public void doNote() {

        }
    },
    MINT(120, 0, TechnologyType.CURRENCY) {
        @Override
        public void doNote() {

        }
    },
    MONASTERY(120, 2, TechnologyType.THEOLOGY) {
        @Override
        public void doNote() {

        }
    },
    UNIVERSITY(200, 3, TechnologyType.EDUCATION) {
        @Override
        public void doNote() {

        }
    },
    WORKSHOP(100, 2, TechnologyType.METALCASTING) {
        @Override
        public void doNote() {

        }
    },
    BANK(220, 0, TechnologyType.BANKING) {
        @Override
        public void doNote() {

        }
    },
    MILITARYACADEMY(350, 3, TechnologyType.MILITARYSCIENCE) {
        @Override
        public void doNote() {

        }
    },
    MUSEUM(350, 3, TechnologyType.ARCHAEOLOGY) {
        @Override
        public void doNote() {

        }
    },
    OPERAHOUSE(220, 3, TechnologyType.ARCHAEOLOGY) {
        @Override
        public void doNote() {

        }
    },
    PUBLICSCHOOL(350, 3, TechnologyType.SCIENTIFICTHEORY) {
        @Override
        public void doNote() {

        }
    },
    SATRAPSCOURT(220, 0, TechnologyType.BANKING) {
        @Override
        public void doNote() {

        }
    },
    THEATER(300, 5, TechnologyType.PRINTINGPRESS) {
        @Override
        public void doNote() {

        }
    },
    WINDMILL(180, 2, TechnologyType.ECONOMICS) {
        @Override
        public void doNote() {

        }
    },
    ARSENAL(350, 3, TechnologyType.RAILROAD) {
        @Override
        public void doNote() {

        }
    },
    BROADCASTTOWER(600, 3, TechnologyType.RADIO) {
        @Override
        public void doNote() {

        }
    },
    FACTORY(300, 3, TechnologyType.STEAMPOWER) {
        @Override
        public void doNote() {

        }
    },
    HOSPITAL(400, 2, TechnologyType.BIOLOGY) {
        @Override
        public void doNote() {

        }
    },
    MILITARYBASE(450, 4, TechnologyType.TELEGRAPH) {
        @Override
        public void doNote() {

        }
    },
    STOCKEXCHANGE(650, 0, TechnologyType.ELECTRICITY) {
        @Override
        public void doNote() {

        }
    };

    BuildingType(int cost, int maintenance, TechnologyType requirement) {
        this.cost = cost;
        this.maintenance = maintenance;
        this.requirement = requirement;
    }

    final int cost;
    final int maintenance;
    final TechnologyType requirement;

    public int getCost() {
        return cost;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public TechnologyType getRequirement() {
        return requirement;
    }

    public static ArrayList<BuildingType> getAllBuildings() {
        ArrayList<BuildingType> allBuildings = new ArrayList<>();
        for (BuildingType building : BuildingType.values())
            allBuildings.add(building);
        return allBuildings;
    }
}
