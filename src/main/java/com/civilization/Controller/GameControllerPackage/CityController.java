package com.civilization.Controller.GameControllerPackage;

import com.civilization.Model.Buildings.BuildingType;
import com.civilization.Model.City;
import com.civilization.Model.Coordination;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Units.UnitType;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class CityController {

    public String delete() {
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        city.deleteCity();
        return "city deleted";
    }

    public String buyTerrain(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordination = new Coordination(x, y);
        if (!coordination.isValidCoordination())
            return "coordination is invalid";
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        for (Terrain terrain : city.getTerrains()) {
            for (Terrain terrain1 : terrain.getSurroundingTerrain()) {
                if (terrain1 == coordination.getTerrain()) {
                    if (terrain1.getCivilization() == null) {
                        GameDataBase.getCurrentCivilization().getGold().addCurrentGold(-10);
                        ///gheimat tile =  10;
                        city.addTerrain(terrain1);
                        return "kharide shod";
                    } else
                        return "in terrain sahab dare";
                }
            }
        }
        return "in terrain dar mahdoode kharid shoma nist va ddoore";
    }

    public String showCityInfo() {
        StringBuilder buildingString = new StringBuilder();
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        city.update();
        return city.getDetails() + city.getDemographics() + "\\n" + city.showMakingUnit();
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
                city.update();
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
        Coordination second = new Coordination(xx, yy);
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
                city.update();
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
        for (BuildingType building : city.buildingsCanBeBuilt()) {
            i++;
            buildingString.append(i).append(" ").append(building.toString().toLowerCase()).append(" turns: ").append((int) ((building.getCost()) / city.getProduction().getCurrentProduct())).append("\n");
            //TODO turn ok nist
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
        ArrayList<UnitType> unitTypes = city.unitsCanBeBuilt();
        for (UnitType unit : unitTypes) {
            i++;
            unitString.append(i).append(" ").append(" turns: ").append((int) (unit.getCost() / city.getProduction().getCurrentProduct())).append(" ").append(unit).append("\n");
        }
        return String.valueOf(unitString);
    }

    public String buildBuilding(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        City city = (City) GameDataBase.getSelected();
        if (city == null) {
            return "city select nashode";
        }
        ArrayList<BuildingType> buildings = city.buildingsCanBeBuilt();
        if (number > buildings.size() || number < 1) {
            return "invalid number";
        }
        if (!city.getCivilization().equals(GameDataBase.getCurrentCivilization())) {
            return "in tile male shoma nist";
        }
        city.createBuilding(buildings.get(number - 1));
        return "building created successfully!";
    }

    public String buildBuildingWithGold(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        City city = (City) GameDataBase.getSelected();
        if (city == null) {
            return "city select nashode";
        }
        ArrayList<BuildingType> buildings = city.buildingsCanBeBuilt();
        if (number > buildings.size() || number < 1) {
            return "invalid number";
        }
        if (!city.getCivilization().equals(GameDataBase.getCurrentCivilization())) {
            return "in tile male shoma nist";
        }
        if (buildings.get(number - 1).getCost() > GameDataBase.getCurrentCivilization().getGold().getCurrentGold()) {
            return "You don't have enough money to buy this unit";
        }
        city.createBuildingInstantly(buildings.get(number - 1));
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

    public String buildUnitWithGold(Matcher matcher) {
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
        if (units.get(number - 1).getCost() > GameDataBase.getCurrentCivilization().getGold().getCurrentGold()) {
            return "You don't have enough money to buy this unit";
        }
        city.createUnitInstantly(units.get(number - 1));
        return "unit created successfully!";
    }


}
