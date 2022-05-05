package com.civilization.Controller.GameControllerPackage;

import com.civilization.Model.City;
import com.civilization.Model.Civilization;
import com.civilization.Model.Units.Unit;
import com.civilization.Model.Units.UnitType;

import java.util.regex.Matcher;

public class InfoController {
    public String showResearch() {
        return GameDataBase.getCurrentCivilization().getTechnologies().technologyTree();
    }

    public String showHappines() {
        Civilization civilization = GameDataBase.getCurrentCivilization();
        return "happines : " + civilization.getHappiness().getAdditionHappiness();
    }

    public String showUnits() {
        Civilization civilization = GameDataBase.getCurrentCivilization();
        StringBuilder units = new StringBuilder();
        for (Unit unit : civilization.getUnits()) {
            units.append(unit.showInfo());
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
        StringBuilder stringBuilder = new StringBuilder();
        for (Unit unit : GameDataBase.getCurrentCivilization().getUnits()) {
            if (UnitType.getSiegeMilitaryUnit().contains(unit.getMyType()))
                stringBuilder.append(unit.showInfo());
        }
        return String.valueOf(stringBuilder);
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

    public String showNotification() {
        return null;
    }
}
