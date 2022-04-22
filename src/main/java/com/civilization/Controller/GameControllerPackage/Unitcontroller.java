package com.civilization.Controller.GameControllerPackage;

import java.util.regex.Matcher;

import com.civilization.Model.Coordination;
import com.civilization.Model.Map;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Units.Unit;

public class Unitcontroller {
    public String move(Matcher matcher, Unit unit) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));

        Coordination coordination = new Coordination(x, y);
        if (coordination.getX() > Map.getRow() - 1 || coordination.getX() < 0
                || coordination.getY() > Map.getColumn() - 1 || coordination.getY() < 0)
            return "position out of bounds";
        if (unit == null)
            return "no unit selected";
        if (unit.getCivilization() != GameDataBase.getCurrentCivilization())
            return "this unit doesn't belong to you good sir";
        Terrain terrain = GameDataBase.getMainMap().getTerrain(x, y);
        unit.move(terrain);
        return "";
    }

    public String sleep(Matcher matcher) {
        return "";
    }

    public String alert(Matcher matcher) {
        return "";
    }

    public String attack(Matcher matcher) {
        return "";
    }

    public String fortify(Matcher matcher) {
        return "";
    }

    public String repair(Matcher matcher) {
        return "";
    }

    public String remove(Matcher matcher) {
        return "";
    }

    public String delete(Matcher matcher) {
        return "";
    }

    public String wake(Matcher matcher) {
        return "";
    }

    public String cancle(Matcher matcher) {
        return "";
    }

    public String setup(Matcher matcher) {
        return "";
    }

    public String garrison(Matcher matcher) {
        return "";
    }

}
