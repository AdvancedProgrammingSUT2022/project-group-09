package com.civilization.Model.TechnologyPackage;

import java.util.ArrayList;

import com.civilization.Model.Buildings.BuildingType;
import com.civilization.Model.Improvements.Improvement;
import com.civilization.Model.Resources.Resource;
import com.civilization.Model.Units.UnitType;

public enum TechnologyType {
    ARGICULTURE(20, new ArrayList<TechnologyType>(), new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.POTTERY);
            add(TechnologyType.ANIMALHUSBANDARY);
            add(TechnologyType.ARCHERY);
            add(TechnologyType.MINING);
        }
    }, new ArrayList<Object>() {
        {
            add(Improvement.FARM);
        }
    }), ANIMALHUSBANDARY(35, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ARGICULTURE);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.TRAPPING);
            add(TechnologyType.THEWHEEL);
        }
    }, new ArrayList<Object>() {
        {
            add(Resource.HORSE);
            add(Improvement.PASTURE);
        }
    }), ARCHERY(35, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ARGICULTURE);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MATHEMATICS);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.ARCHER);
        }
    }), BRONZEWORKING(55, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MINING);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.IRONWORKING);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.SPEARMAN);
            add(BuildingType.BARRACKS);
            //TODO remove jungle
        }
    }), CALENDER(70, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.POTTERY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.THEOLOGY);
        }
    }, new ArrayList<Object>() {
        {
            add(Improvement.PLANTATION);
        }
    }), MASONRY(55, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MINING);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CONSTRUCTION);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.WALLS);
            add(Improvement.QUARRY);
            //TODO remove marsh
        }
    }), MINING(35, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ARGICULTURE);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MASONRY);
            add(TechnologyType.BRONZEWORKING);
        }
    }, new ArrayList<Object>() {
        {
            //TODO remove forest
            add(Improvement.MINE);
        }
    }), POTTERY(35, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ARGICULTURE);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CALENDER);
            add(TechnologyType.WRITING);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.GRANARY);
        }
    }), THEWHEEL(55, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ANIMALHUSBANDARY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.HORSEBACKRIDING);
            add(TechnologyType.MATHEMATICS);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.CHARIOTARCHER);
            add(BuildingType.WATERMILL);
            //TODO build road
        }
    }), TRAPPING(55, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ANIMALHUSBANDARY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CIVILISERVICE);
        }
    }, new ArrayList<Object>() {
        {
            add(Improvement.TRADINGPOST);
            add(Improvement.CAMP);
        }
    }), WRITING(55, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.POTTERY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.PHILOSOPHY);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.LIBRARY);
        }
    }), CONSTRUCTION(100, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MASONRY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ENGINEERING);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.COLOSSEUM);
            //TODO bridges over rivers
        }
    }), HORSEBACKRIDING(100, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.THEWHEEL);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CHIVALRY);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.CIRCUS);
        }
    }), IRONWORKING(150, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.BRONZEWORKING);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.METALCASTING);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.SWORDSMAN);
            add(BuildingType.ARMORY);
        }
    }), MATHEMATICS(100, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.THEWHEEL);
            add(TechnologyType.ARCHERY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CURRENCY);
            add(TechnologyType.ENGINEERING);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.CATAPULT);
            add(BuildingType.COURTHOUSE);
        }
    }), PHILOSOPHY(100, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.WRITING);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.THEOLOGY);
            add(TechnologyType.CIVILISERVICE);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.BURIALTOMB);
            add(BuildingType.TEMPLE);
        }
    }), CHIVALRY(440, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CIVILISERVICE);
            add(TechnologyType.HORSEBACKRIDING);
            add(TechnologyType.CURRENCY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.BANKING);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.KNIGHT);
            add(BuildingType.CASTLE);
        }
    }), CIVILISERVICE(400, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.PHILOSOPHY);
            add(TechnologyType.TRAPPING);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CHIVALRY);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.MARKET);
        }
    }), CURRENCY(250, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MATHEMATICS);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CHIVALRY);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.MARKET);
        }
    }), EDUCATION(440, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.THEOLOGY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ACOUSTICS);
            add(TechnologyType.BANKING);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.UNIVERSITY);
        }
    }), ENGINEERING(250, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MATHEMATICS);
            add(TechnologyType.CONSTRUCTION);
            //6221061078032721
            //EASTER EGG
            //payam taebi shomare kart
            //man I'm your servant
            //anything which is your karam please pour
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MACHINERY);
            add(TechnologyType.PHYSICS);
        }
    }, new ArrayList<Object>() {
        {

        }
    }), MACHINERY(440, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ENGINEERING);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.PRINTINGPRESS);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.CROSSBOWMAN);
            //TODO 1.2 faster road movement
        }
    }), METALCASTING(240, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.IRONWORKING);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.PHYSICS);
            add(TechnologyType.STEEL);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.FORGE);
            add(BuildingType.WORKSHOP);
        }
    }), PHYSICS(440, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ENGINEERING);
            add(TechnologyType.METALCASTING);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.PRINTINGPRESS);
            add(TechnologyType.GUNPOWDER);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.TREBUCHET);
        }
    }), STEEL(440, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.METALCASTING);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.GUNPOWDER);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.LONGSWORDMAN);
        }
    }), THEOLOGY(250, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CALENDER);
            add(TechnologyType.PHILOSOPHY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.EDUCATION);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.GARDEN);
            add(BuildingType.MONASTERY);
        }
    }), ACOUSTICS(650, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.EDUCATION);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.SCIENTIFICTHEORY);
        }
    }, new ArrayList<Object>() {
        {

        }
    }), ARCHAEOLOGY(1300, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ACOUSTICS);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.BIOLOGY);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.MUSEUM);
        }
    }), BANKING(650, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.EDUCATION);
            add(TechnologyType.CHIVALRY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ECONOMICS);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.SATRAPSCOURT);

        }
    }), CHEMISTRY(900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.GUNPOWDER);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MILITARYSCIENCE);
            add(TechnologyType.FERTILIIZER);
        }
    }, new ArrayList<Object>() {
        {
            //TODO ironworks
        }
    }), ECONOMICS(900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.BANKING);
            add(TechnologyType.PRINTINGPRESS);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MILITARYSCIENCE);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.WINDMILL);
        }
    }), FERTILIIZER(1300, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CHEMISTRY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.DYNAMITE);
        }
    }, new ArrayList<Object>() {
        {
            //TODO Farms without Fresh Water yield increased by 1
        }
    }), GUNPOWDER(680, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.PHYSICS);
            add(TechnologyType.STEEL);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CHEMISTRY);
            add(TechnologyType.METALLURGY);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.MUSKETMAN);
        }
    }), METALLURGY(900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.GUNPOWDER);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.RIFLING);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.LANCER);
        }
    }), MILITARYSCIENCE(1300, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ECONOMICS);
            add(TechnologyType.CHEMISTRY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.STEAMPOWER);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.CAVALRY);
            add(BuildingType.MILITARYACADEMY);
        }
    }), PRINTINGPRESS(650, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MACHINERY);
            add(TechnologyType.PHYSICS);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ECONOMICS);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.THEATER);
        }
    }), RIFLING(1425, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.METALLURGY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.DYNAMITE);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.RIFLEMAN);
        }
    }), SCIENTIFICTHEORY(1300, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ACOUSTICS);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.BIOLOGY);
            add(TechnologyType.STEAMPOWER);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.PUBLICSCHOOL);
            add(Resource.COAL);
        }
    }), BIOLOGY(1680, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ARCHAEOLOGY);
            add(TechnologyType.SCIENTIFICTHEORY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ELECTRICITY);
        }
    }, new ArrayList<Object>() {
        {

        }
    }), COMBUSTION(2200, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.REPLACEABLEPARTS);
            add(TechnologyType.RAILROAD);
            add(TechnologyType.DYNAMITE);
        }
    }, new ArrayList<TechnologyType>() {
        {

        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.PANZER);
            add(UnitType.TANK);
        }
    }), DYNAMITE(1900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.FERTILIIZER);
            add(TechnologyType.RIFLING);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.COMBUSTION);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.ARTILLERY);
        }
    }), ELECTRICITY(1900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.BIOLOGY);
            add(TechnologyType.STEAMPOWER);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.TELEGRAPH);
            add(TechnologyType.RADIO);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.STOCKEXCHANGE);
        }
    }), RADIO(2200, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ELECTRICITY);
        }
    }, new ArrayList<TechnologyType>() {
        {

        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.BROADCASTTOWER);
        }
    }), RAILROAD(1900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.STEAMPOWER);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.COMBUSTION);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.ARSENAL);
        }
    }), REPLACEABLEPARTS(1900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.STEAMPOWER);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.COMBUSTION);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.ANTITANKGUN);
            add(UnitType.INFANTRY);
        }
    }), STEAMPOWER(1680, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.SCIENTIFICTHEORY);
            add(TechnologyType.MILITARYSCIENCE);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ELECTRICITY);
            add(TechnologyType.REPLACEABLEPARTS);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.FACTORY);
        }
    }), TELEGRAPH(2200, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ELECTRICITY);
        }
    }, new ArrayList<TechnologyType>() {
        {

        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.MILITARYBASE);
        }
    });

    TechnologyType(int cost, ArrayList<TechnologyType> requirement, ArrayList<TechnologyType> technologyUnlcoks,
                   ArrayList<Object> unlocks) {
        this.cost = cost;
        this.requirement = requirement;
        this.technologyUnlocks = technologyUnlcoks;
        this.unlocks = unlocks;
    }

    final int cost;
    final ArrayList<TechnologyType> requirement;
    final ArrayList<TechnologyType> technologyUnlocks;
    final ArrayList<Object> unlocks;

}
