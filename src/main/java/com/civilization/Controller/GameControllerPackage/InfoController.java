package com.civilization.Controller.GameControllerPackage;

import com.civilization.Model.City;
import com.civilization.Model.Civilization;
import com.civilization.Model.Units.Unit;

import java.util.regex.Matcher;

public class InfoController {
    public String showResearch() {
        return null;
    }

    public String showUnits() {
        Civilization civilization = GameDataBase.getCurrentCivilization();
        StringBuilder units = new StringBuilder();
        for (Unit unit : civilization.getUnits()) {
            units.append("x: ").append(unit.getTerrain().getCoordination().getX()).append(" y: ").append(unit.getTerrain().getCoordination().getY())
                    .append(" type: ").append(unit.getMyType().toString()).append("\n");
            //TODO .. mitoonim detail bezarim baraye unit
        }
        return String.valueOf(units);
    }

    public String showCities() {
        Civilization civilization = GameDataBase.getCurrentCivilization();
        StringBuilder cities = new StringBuilder();
        for (City city : civilization.getCities()) {
            cities.append(city.getDetails()).append("\n");
        }
        return String.valueOf(cities);
    }

    public String showDiplomacy() {
        return null;
    }

    public String showVictory() {
        return "victory nadarim hanooz";
        //TODO...
    }

    public String showDemographics() {
        return GameDataBase.getCurrentCivilization().getDemographics();
    }

    public String showMilitary() {
        return null;
    }

    public String showEconomy() {
        return null;
    }

    public String showDiplomotics() {
        return null;
    }

    public String showDeals() {
        return null;
    }

    public String showNotification(){return null;}
}
