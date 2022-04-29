package com.civilization.Model.TechnologyPackage;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.civilization.Model.Buildings.BuildingType;
import com.civilization.Model.Improvements.Improvement;
import com.civilization.Model.Resources.Resource;
import com.civilization.Model.Units.UnitType;

import javax.print.attribute.standard.Finishings;

public enum TechnologyType {
    AGRICULTURE("agriculture", 20, new ArrayList<TechnologyType>(), new ArrayList<TechnologyType>() {
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
    }), ANIMALHUSBANDARY("animal husbandry",35, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.AGRICULTURE);
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
    }), ARCHERY("archery",35, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.AGRICULTURE);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MATHEMATICS);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitType.ARCHER);
        }
    }), BRONZEWORKING("bronze working",55, new ArrayList<TechnologyType>() {
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
    }), CALENDER("calender",70, new ArrayList<TechnologyType>() {
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
    }), MASONRY("masonary",55, new ArrayList<TechnologyType>() {
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
    }), MINING("mining",35, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.AGRICULTURE);
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
    }), POTTERY("pottery",35, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.AGRICULTURE);
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
    }), THEWHEEL("the wheel",55, new ArrayList<TechnologyType>() {
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
    }), TRAPPING("trapping",55, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ANIMALHUSBANDARY);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CIVILSERVICE);
        }
    }, new ArrayList<Object>() {
        {
            add(Improvement.TRADINGPOST);
            add(Improvement.CAMP);
        }
    }), WRITING("writing",55, new ArrayList<TechnologyType>() {
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
    }), CONSTRUCTION("construction",100, new ArrayList<TechnologyType>() {
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
    }), HORSEBACKRIDING("horse back riding",100, new ArrayList<TechnologyType>() {
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
    }), IRONWORKING("iron working",150, new ArrayList<TechnologyType>() {
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
    }), MATHEMATICS("mathematics",100, new ArrayList<TechnologyType>() {
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
    }), PHILOSOPHY("philosophy",100, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.WRITING);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.THEOLOGY);
            add(TechnologyType.CIVILSERVICE);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingType.BURIALTOMB);
            add(BuildingType.TEMPLE);
        }
    }), CHIVALRY("chivalry",440, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CIVILSERVICE);
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
    }), CIVILSERVICE("civil service",400, new ArrayList<TechnologyType>() {
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
    }), CURRENCY("currency",250, new ArrayList<TechnologyType>() {
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
    }), EDUCATION("education",440, new ArrayList<TechnologyType>() {
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
    }), ENGINEERING("engineering",250, new ArrayList<TechnologyType>() {
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
    }), MACHINERY("machinery",440, new ArrayList<TechnologyType>() {
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
    }), METALCASTING("metal casting",240, new ArrayList<TechnologyType>() {
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
    }), PHYSICS("physics",440, new ArrayList<TechnologyType>() {
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
    }), STEEL("steel",440, new ArrayList<TechnologyType>() {
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
    }), THEOLOGY("theology",250, new ArrayList<TechnologyType>() {
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
    }), ACOUSTICS("acoustics",650, new ArrayList<TechnologyType>() {
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
    }), ARCHAEOLOGY("archaeology",1300, new ArrayList<TechnologyType>() {
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
    }), BANKING("banking",650, new ArrayList<TechnologyType>() {
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
    }), CHEMISTRY("chemistry",900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.GUNPOWDER);
        }
    }, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MILITARYSCIENCE);
            add(TechnologyType.FERTILIZER);
        }
    }, new ArrayList<Object>() {
        {
            //TODO ironworks
        }
    }), ECONOMICS("economics",900, new ArrayList<TechnologyType>() {
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
    }), FERTILIZER("fertilizer",1300, new ArrayList<TechnologyType>() {
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
    }), GUNPOWDER("gunpowder",680, new ArrayList<TechnologyType>() {
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
    }), METALLURGY("metallurgy",900, new ArrayList<TechnologyType>() {
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
    }), MILITARYSCIENCE("military science",1300, new ArrayList<TechnologyType>() {
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
    }), PRINTINGPRESS("printing press",650, new ArrayList<TechnologyType>() {
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
    }), RIFLING("rifling",1425, new ArrayList<TechnologyType>() {
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
    }), SCIENTIFICTHEORY("scientific theory",1300, new ArrayList<TechnologyType>() {
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
    }), BIOLOGY("biology",1680, new ArrayList<TechnologyType>() {
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
    }), COMBUSTION("combustion",2200, new ArrayList<TechnologyType>() {
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
    }), DYNAMITE("dynamite",1900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.FERTILIZER);
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
    }), ELECTRICITY("electricity",1900, new ArrayList<TechnologyType>() {
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
    }), RADIO("radio",2200, new ArrayList<TechnologyType>() {
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
    }), RAILROAD("rail road",1900, new ArrayList<TechnologyType>() {
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
    }), REPLACEABLEPARTS("replaceable parts",1900, new ArrayList<TechnologyType>() {
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
    }), STEAMPOWER("steam power",1680, new ArrayList<TechnologyType>() {
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
    }), TELEGRAPH("telegraph",2200, new ArrayList<TechnologyType>() {
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

    TechnologyType(String name, int cost, ArrayList<TechnologyType> requirement, ArrayList<TechnologyType> technologyUnlcoks,
                   ArrayList<Object> unlocks) {
        this.name = name;
        this.cost = cost;
        this.requirement = requirement;
        this.technologyUnlocks = technologyUnlcoks;
        this.unlocks = unlocks;
    }

    final int cost;
    final ArrayList<TechnologyType> requirement;
    final ArrayList<TechnologyType> technologyUnlocks;
    final ArrayList<Object> unlocks;
    final String name;

    final static ArrayList<TechnologyType> allTechnologies = new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.AGRICULTURE);
            add(TechnologyType.ANIMALHUSBANDARY);
            add(TechnologyType.ARCHERY);
            add(TechnologyType.BRONZEWORKING);
            add(TechnologyType.CALENDER);
            add(TechnologyType.MASONRY);
            add(TechnologyType.MINING);
            add(TechnologyType.POTTERY);
            add(TechnologyType.THEWHEEL);
            add(TechnologyType.TRAPPING);
            add(TechnologyType.WRITING);
            add(TechnologyType.CONSTRUCTION);
            add(TechnologyType.HORSEBACKRIDING);
            add(TechnologyType.IRONWORKING);
            add(TechnologyType.MATHEMATICS);
            add(TechnologyType.PHILOSOPHY);
            add(TechnologyType.CHIVALRY);
            add(TechnologyType.CIVILSERVICE);
            add(TechnologyType.CURRENCY);
            add(TechnologyType.EDUCATION);
            add(TechnologyType.ENGINEERING);
            add(TechnologyType.MACHINERY);
            add(TechnologyType.METALCASTING);
            add(TechnologyType.PHYSICS);
            add(TechnologyType.STEEL);
            add(TechnologyType.THEOLOGY);
            add(TechnologyType.ACOUSTICS);
            add(TechnologyType.ARCHAEOLOGY);
            add(TechnologyType.BANKING);
            add(TechnologyType.CHEMISTRY);
            add(TechnologyType.ECONOMICS);
            add(TechnologyType.FERTILIZER);
            add(TechnologyType.GUNPOWDER);
            add(TechnologyType.METALLURGY);
            add(TechnologyType.MILITARYSCIENCE);
            add(TechnologyType.PRINTINGPRESS);
            add(TechnologyType.RIFLING);
            add(TechnologyType.SCIENTIFICTHEORY);
            add(TechnologyType.BIOLOGY);
            add(TechnologyType.COMBUSTION);
            add(TechnologyType.DYNAMITE);
            add(TechnologyType.ELECTRICITY);
            add(TechnologyType.RADIO);
            add(TechnologyType.REPLACEABLEPARTS);
            add(TechnologyType.STEAMPOWER);
            add(TechnologyType.TELEGRAPH);
        }
    };

    public static ArrayList<TechnologyType> getAllTechnologies() {
        return allTechnologies;
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<TechnologyType> getRequirement() {
        return requirement;
    }

    public ArrayList<Object> getUnlocks() {
        return unlocks;
    }

    public String getName() {
        return name;
    }

    public ArrayList<TechnologyType> getTechnologyUnlocks() {
        return technologyUnlocks;
    }
}
