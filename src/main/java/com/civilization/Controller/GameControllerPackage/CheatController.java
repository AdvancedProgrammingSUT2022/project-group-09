package com.civilization.Controller.GameControllerPackage;

import com.civilization.Model.City;
import com.civilization.Model.Civilization;
import com.civilization.Model.Units.Unit;

import java.util.regex.Matcher;

public class CheatController {
    public String increaseScience(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        GameDataBase.getCurrentCivilization().getScience().setAddedFromCheat(
                GameDataBase.getCurrentCivilization().getScience().getAddedFromCheat() + number
        );
        return "added. your current science is : " + GameDataBase.getCurrentCivilization().getScience().getAdditionScience();
    }

    public String increaseGold(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        GameDataBase.getCurrentCivilization().getGold().setAddedFromCheat(
                GameDataBase.getCurrentCivilization().getScience().getAddedFromCheat() + number
        );
        return "added. your current gold is : " + GameDataBase.getCurrentCivilization().getGold().getCurrentGold();

    }

    public String resetUnit() {
        for (Unit unit : GameDataBase.getCurrentCivilization().getUnits()) {
            unit.nextTurn();
        }
        return "all units reset";

    }

    public String increaseHappiness(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        GameDataBase.getCurrentCivilization().getHappiness().setAddedFromCheat(
                GameDataBase.getCurrentCivilization().getScience().getAddedFromCheat() + number
        );
        return "added. your current science is : " + GameDataBase.getCurrentCivilization().getScience().getAdditionScience();

    }

    public String increaseTurn(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        for (int i = 0; i < number; i++) {
            GameDataBase.nextTurn();
            for (Civilization civilization : GameDataBase.getCivilizations()) {
                for (City city : civilization.getCities()) {
                    city.nextTurn();
                }
                civilization.nextTurn();
                for (Unit unit : civilization.getUnits()) {
                    unit.nextTurn();
                }
            }
        }
        return "done";

    }


}
