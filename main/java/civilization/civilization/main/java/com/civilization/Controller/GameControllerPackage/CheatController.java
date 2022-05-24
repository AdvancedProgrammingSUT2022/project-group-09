package civilization.civilization.main.java.com.civilization.Controller.GameControllerPackage;

import civilization.civilization.main.java.com.civilization.Model.Coordination;
import civilization.civilization.main.java.com.civilization.Model.Info.CivilizationTechnologies;
import civilization.civilization.main.java.com.civilization.Model.Units.MilitaryUnit;
import civilization.civilization.main.java.com.civilization.Model.Units.Settler;
import civilization.civilization.main.java.com.civilization.Model.Units.Unit;
import civilization.civilization.main.java.com.civilization.Model.Units.UnitType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class CheatController {
    public String increaseScience(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        GameDataBase.getCurrentCivilization().getScience().setAddedFromCheat(
                GameDataBase.getCurrentCivilization().getScience().getAddedFromCheat() + number
        );
        return "added. your current science was : " + GameDataBase.getCurrentCivilization().getScience().getAdditionScience();
    }

    public String increaseGold(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        GameDataBase.getCurrentCivilization().getGold().setAddedFromCheat(
                GameDataBase.getCurrentCivilization().getGold().getAddedFromCheat() + number
        );
        return "cash ziad eyne hatami balam az zamane khatami\nadded. your current gold was : " + GameDataBase.getCurrentCivilization().getGold().getCurrentGold();

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
                GameDataBase.getCurrentCivilization().getHappiness().getAddedFromCheat() + number
        );
        return "added. your current science was : " + GameDataBase.getCurrentCivilization().getScience().getAdditionScience();

    }

    public String increaseTurn(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        for (int i = 0; i < number; i++) {
            new GameMenuController().doNextTurn();
        }
        return "done";

    }

    public String setArcher(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        new MilitaryUnit(UnitType.ARCHER,
                new Coordination(x, y).getTerrain(),
                GameDataBase.getCurrentCivilization());
        return "archer added";
    }

    public String setTank(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        new MilitaryUnit(UnitType.TANK,
                new Coordination(x, y).getTerrain(),
                GameDataBase.getCurrentCivilization());
        return "tank added";
    }

    public String setArtillery(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        new MilitaryUnit(UnitType.ARTILLERY,
                new Coordination(x, y).getTerrain(),
                GameDataBase.getCurrentCivilization());
        return "artillery added";
    }

    public String setCannon(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        new MilitaryUnit(UnitType.CANON,
                new Coordination(x, y).getTerrain(),
                GameDataBase.getCurrentCivilization());
        return "cannon added";
    }

    public String setLancer(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        new MilitaryUnit(UnitType.LANCER,
                new Coordination(x, y).getTerrain(),
                GameDataBase.getCurrentCivilization());
        return "lancer added";
    }

    public String setSettler(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        new Settler(
                new Coordination(x, y).getTerrain(),
                GameDataBase.getCurrentCivilization());
        return "setller added";
    }

    public String openTechnologies() {
        CivilizationTechnologies technologies = GameDataBase.getCurrentCivilization().getTechnologies();
        technologies.getTechnologiesResearched().addAll(technologies.getTechnologiesUnavailable());
        technologies.getTechnologiesResearched().addAll(technologies.getTechnologiesAvailable().keySet());
        if (technologies.getTechnologyCurrentlyResearching() != null)
            technologies.getTechnologiesResearched().add(technologies.getTechnologyCurrentlyResearching());
        technologies.setTechnologiesAvailable(new HashMap<>());
        technologies.setTechnologiesUnavailable(new ArrayList<>());
        technologies.setTechnologyCurrentlyResearching(null);
        technologies.setRemainCost(0);
        return "all technologies were opened!";
    }
}
