package com.civilization.Controller.GameControllerPackage;

import com.civilization.Model.Buildings.BuildingType;
import com.civilization.Model.City;
import com.civilization.Model.Coordination;
import com.civilization.Model.Terrains.Terrain;

import java.util.regex.Matcher;

public class CityController {


    public String showCityInfo() {
        StringBuilder buildingString = new StringBuilder();
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        for (BuildingType building : city.getBuildings().getBuildings()) {
            buildingString.append(building.name()).append(" ");
        }
        return "production : " + city.getProduction().getCurrentProduct() + "\n" +
                "gold : " + city.getGold().getAdditionGold() + "\n" +
                "science : " + city.getCityScience() + "\n" +
                "food : " + city.getFood().getAdditionFood() + "\n" +
                "citizens : " + city.getCitizens().size() + "\n" +
                "buildings: " + buildingString;
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


}
