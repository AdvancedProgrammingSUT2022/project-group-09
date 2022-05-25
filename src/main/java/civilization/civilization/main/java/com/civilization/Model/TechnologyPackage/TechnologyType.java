package civilization.civilization.main.java.com.civilization.Model.TechnologyPackage;

import java.util.ArrayList;
import java.util.Arrays;

import civilization.civilization.main.java.com.civilization.Model.Buildings.BuildingType;
import civilization.civilization.main.java.com.civilization.Model.Improvements.Improvement;
import civilization.civilization.main.java.com.civilization.Model.Resources.Resource;
import civilization.civilization.main.java.com.civilization.Model.Units.UnitType;

public enum TechnologyType {
    AGRICULTURE("agriculture", 20, new ArrayList<TechnologyType>()
    ), POTTERY("pottery", 35, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.AGRICULTURE);
        }
    }), MINING("mining", 35, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.AGRICULTURE);
        }
    }), ANIMALHUSBANDARY("animal husbandry", 35, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.AGRICULTURE);
        }
    }), ARCHERY("archery", 35, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.AGRICULTURE);
        }
    }), BRONZEWORKING("bronze working", 55, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MINING);
        }
    }), CALENDER("calender", 70, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.POTTERY);
        }
    }), MASONRY("masonary", 55, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MINING);
        }
    }), THEWHEEL("the wheel", 55, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ANIMALHUSBANDARY);
        }
    }), TRAPPING("trapping", 55, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ANIMALHUSBANDARY);
        }
    }), WRITING("writing", 55, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.POTTERY);
        }
    }), CONSTRUCTION("construction", 100, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MASONRY);
        }
    }), HORSEBACKRIDING("horse back riding", 100, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.THEWHEEL);
        }
    }), IRONWORKING("iron working", 150, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.BRONZEWORKING);
        }
    }), MATHEMATICS("mathematics", 100, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.THEWHEEL);
            add(TechnologyType.ARCHERY);
        }
    }), PHILOSOPHY("philosophy", 100, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.WRITING);
        }
    }), CIVILSERVICE("civil service", 400, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.PHILOSOPHY);
            add(TechnologyType.TRAPPING);
        }
    }), CURRENCY("currency", 250, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MATHEMATICS);
        }
    }), CHIVALRY("chivalry", 440, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CIVILSERVICE);
            add(TechnologyType.HORSEBACKRIDING);
            add(TechnologyType.CURRENCY);
        }
    }), THEOLOGY("theology", 250, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CALENDER);
            add(TechnologyType.PHILOSOPHY);
        }
    }), EDUCATION("education", 440, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.THEOLOGY);
        }
    }), ENGINEERING("engineering", 250, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MATHEMATICS);
            add(TechnologyType.CONSTRUCTION);
            //6221061078032721
            //EASTER EGG
            //payam taebi shomare kart
            //man I'm your servant
            //anything which is your karam please pour
        }
    }), MACHINERY("machinery", 440, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ENGINEERING);
        }
    }), METALCASTING("metal casting", 240, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.IRONWORKING);
        }
    }), PHYSICS("physics", 440, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ENGINEERING);
            add(TechnologyType.METALCASTING);
        }
    }), STEEL("steel", 440, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.METALCASTING);
        }
    }), ACOUSTICS("acoustics", 650, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.EDUCATION);
        }
    }), ARCHAEOLOGY("archaeology", 1300, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ACOUSTICS);
        }
    }), BANKING("banking", 650, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.EDUCATION);
            add(TechnologyType.CHIVALRY);
        }
    }), GUNPOWDER("gunpowder", 680, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.PHYSICS);
            add(TechnologyType.STEEL);
        }
    }), CHEMISTRY("chemistry", 900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.GUNPOWDER);
        }
    }), PRINTINGPRESS("printing press", 650, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.MACHINERY);
            add(TechnologyType.PHYSICS);
        }
    }), ECONOMICS("economics", 900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.BANKING);
            add(TechnologyType.PRINTINGPRESS);
        }
    }), FERTILIZER("fertilizer", 1300, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.CHEMISTRY);
        }
    }), METALLURGY("metallurgy", 900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.GUNPOWDER);
        }
    }), MILITARYSCIENCE("military science", 1300, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ECONOMICS);
            add(TechnologyType.CHEMISTRY);
        }
    }), RIFLING("rifling", 1425, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.METALLURGY);
        }
    }), SCIENTIFICTHEORY("scientific theory", 1300, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ACOUSTICS);
        }
    }), BIOLOGY("biology", 1680, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ARCHAEOLOGY);
            add(TechnologyType.SCIENTIFICTHEORY);
        }
    }), STEAMPOWER("steam power", 1680, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.SCIENTIFICTHEORY);
            add(TechnologyType.MILITARYSCIENCE);
        }
    }), ELECTRICITY("electricity", 1900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.BIOLOGY);
            add(TechnologyType.STEAMPOWER);
        }
    }), RADIO("radio", 2200, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ELECTRICITY);
        }
    }), RAILROAD("rail road", 1900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.STEAMPOWER);
        }
    }), REPLACEABLEPARTS("replaceable parts", 1900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.STEAMPOWER);
        }
    }), TELEGRAPH("telegraph", 2200, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.ELECTRICITY);
        }
    }), DYNAMITE("dynamite", 1900, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.FERTILIZER);
            add(TechnologyType.RIFLING);
        }
    }), COMBUSTION("combustion", 2200, new ArrayList<TechnologyType>() {
        {
            add(TechnologyType.REPLACEABLEPARTS);
            add(TechnologyType.RAILROAD);
            add(TechnologyType.DYNAMITE);
        }
    });

    TechnologyType(String name, int cost, ArrayList<TechnologyType> requirement) {
        this.name = name;
        this.cost = cost;
        this.requirement = requirement;
    }

    final int cost;
    final ArrayList<TechnologyType> requirement;
    final String name;

    public static ArrayList<TechnologyType> getAllTechnologies() {
        TechnologyType[] yourEnums = TechnologyType.class.getEnumConstants();
        return new ArrayList<>(Arrays.asList(yourEnums));
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<TechnologyType> getRequirement() {
        return requirement;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Object> getUnlocks() {
        ArrayList<Object> unlocks = new ArrayList<>();
        for (UnitType unit : UnitType.getAllUnits()) {
            if (unit.getRequiredTechnology() == this) {
                unlocks.add(unit);
            }
        }
        for (Improvement improvement : Improvement.getAllImprovements()) {
            if (improvement.getRequiredTechnology() == this) {
                unlocks.add(improvement);
            }
        }
        for (Resource resource : Resource.getAllResources()) {
            if (resource.getRequiredTechnology() == this) {
                unlocks.add(resource);
            }
        }
        for (BuildingType building : BuildingType.getAllBuildings()) {
            if (building.getRequirement() == this) {
                unlocks.add(building);
            }
        }
        return unlocks;
    }

    public ArrayList<TechnologyType> getTechnologyUnlocks() {
        ArrayList<TechnologyType> unlocks = new ArrayList<>();
        for (TechnologyType allTechnology : getAllTechnologies()) {
            if (allTechnology.getRequirement().contains(this))
                unlocks.add(allTechnology);
        }
        return unlocks;
    }
}
