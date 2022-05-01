package com.civilization.Controller.GameControllerPackage;

import com.civilization.Model.Buildings.BuildingType;
import com.civilization.Model.City;
import com.civilization.Model.Coordination;
import com.civilization.Model.Units.UnitType;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class CityController {


    public String showCityInfo() {
        StringBuilder buildingString = new StringBuilder();
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        return city.getDetails();
    }

    public String setCitizen(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordination = new Coordination(x, y);
        if (!coordination.isValidCoordination())
            return "coordination is invalid";
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        if (!city.getTerrains().contains(coordination.getTerrain()))
            return "in tile male shoma nist";

        for (int i = 0; i < city.getCitizens().size(); i++) {
            if (city.getCitizens().get(i) == null) {
                city.getCitizens().set(i, coordination.getTerrain());
                return "anjam shod";
            }
        }
        return "hame citizen ha mashghool kar hastand";
    }

    public String moveCitizen(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        int xx = Integer.parseInt(matcher.group("xx"));
        int yy = Integer.parseInt(matcher.group("yy"));
        Coordination first = new Coordination(x, y);
        Coordination second = new Coordination(x, y);
        if (!first.isValidCoordination())
            return "coordination is invalid";
        if (!second.isValidCoordination())
            return "coordination is invalid";
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        if (!city.getTerrains().contains(first.getTerrain()))
            return "in tile male shoma nist";
        if (!city.getTerrains().contains(second.getTerrain()))
            return "in tile male shoma nist";

        for (int i = 0; i < city.getCitizens().size(); i++) {
            if (city.getCitizens().get(i) == first.getTerrain()) {
                city.getCitizens().set(i, second.getTerrain());
                return "move shod";
            }
        }
        return "citizeni dar mabda dar hale kar nist";
    }

    public String showBuildings() {
        StringBuilder buildingString = new StringBuilder();
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        int i = 0;
        buildingString.append("*BUILDINGS*\n");
        for (BuildingType building : GameDataBase.getCurrentCivilization().buildingsCanBeBuilt()) {
            i++;
            buildingString.append(i).append(" ").append(building).append(" turns: ").append((building.getCost() - 1) / city.getProduction().getCurrentProduct()).append("\n");//TODO turn ok nist
        }
        return String.valueOf(buildingString);
    }

    public String showUnits() {
        StringBuilder unitString = new StringBuilder();
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        int i = 0;
        unitString.append("*UINTS*\n");
        for (UnitType unit : city.unitsCanBeBuilt()) {
            i++;
            unitString.append(i).append(" ").append(" turns: ").append((unit.getCost() - 1) / city.getProduction().getCurrentProduct()).append(unit).append("\n");
        }
        return String.valueOf(unitString);
    }

    public String buildBuilding(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        ArrayList<BuildingType> buildings = GameDataBase.getCurrentCivilization().buildingsCanBeBuilt();
        City city = (City) GameDataBase.getSelected();
        if (city == null) {
            return "city select nashode";
        }
        if (number > buildings.size() || number < 1) {
            return "invalid number";
        }
        if (!city.getCivilization().equals(GameDataBase.getCurrentCivilization())) {
            return "in tile male shoma nist";
        }
        city.CreateBuilding(buildings.get(number - 1));
        return "building created successfully!";
    }

    public String buildUnit(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        City city = (City) GameDataBase.getSelected();
        if (city == null) {
            return "city select nashode";
        }
        ArrayList<UnitType> units = city.unitsCanBeBuilt();
        if (number > units.size() || number < 1) {
            return "invalid number";
        }
        if (!city.getCivilization().equals(GameDataBase.getCurrentCivilization())) {
            return "in tile male shoma nist";
        }
        city.CreateUnit(units.get(number - 1));
        return "unit created successfully!";
    }


}
