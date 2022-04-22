package com.civilization.Controller.GameControllerPackage;

import java.util.ArrayList;
import java.util.regex.Matcher;

import javax.print.attribute.standard.Destination;

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
        Terrain destination = GameDataBase.getMainMap().getTerrain(x, y);
        return moveUnit(destination, unit);
    }

    private String moveUnit(Terrain destination, Unit unit) {
        Terrain origin = unit.getTerrain();
        int MP = unit.getRemainingMove();
        ArrayList<ArrayList<Terrain>> paths = new ArrayList<>();
        ArrayList<Terrain> path = new ArrayList<>();
        path.add(origin);

        findAllPaths(destination, origin, MP, paths, path);
        return "";
    }

    private void findAllPaths(Terrain destination, Terrain origin, int MP, ArrayList<ArrayList<Terrain>> paths,
            ArrayList<Terrain> path) {
        if (destination == origin) {
            paths.add(path);
            return;
        }
        for (Terrain nextTerrain : origin.getSurroundingTerrain()) {
            if (isMovePossible(MP, nextTerrain) && !path.contains(nextTerrain)) {
                ArrayList<Terrain> nextPath = (ArrayList<Terrain>) path.clone();
                nextPath.add(nextTerrain);
                findAllPaths(destination, nextTerrain, MP, paths, nextPath);
            }
        }
    }

    private boolean isMovePossible(int MP, Terrain terrain) {
        if (MP > terrain.getMp())
            return true;
        return false;
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
