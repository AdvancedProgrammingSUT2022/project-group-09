package com.civilization.Controller.GameControllerPackage;

import java.util.ArrayList;
import java.util.regex.Matcher;

import com.civilization.Model.Coordination;
import com.civilization.Model.Map;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Units.Unit;
import com.civilization.Model.Units.UnitType;

public class UnitController {
    private Unit unit;

    public UnitController(Unit unit) {
        this.unit = unit;
    }

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
        int maxMp = unit.getMyType().getMovement();
        UnitType unitType = unit.getMyType();

        if (isDestinationEmpty(unitType, destination))
            return "destination is not empty for you";

        ArrayList<ArrayList<Terrain>> paths = new ArrayList<>();
        ArrayList<Terrain> path = new ArrayList<>();
        path.add(origin);

        findAllPaths(destination, origin, MP, paths, path);
        if (paths.isEmpty())
            return "unfortunately there is no available path for your unit to move to your desired destination";
        
        ArrayList<Terrain> bestPath = findBestPath(paths, unit.getMyType().getMovement(), maxMp, unitType);
        if (bestPath == null)
            return "unfortunately there is no available path for your unit to move to your desired destination";

        ArrayList<Coordination> pathCoordination = new ArrayList<>();
        setPathCoordinates(pathCoordination, bestPath);
        unit.setPath(pathCoordination);
        unit.move();
        return "unit moved successfully";
    }

    private void setPathCoordinates(ArrayList<Coordination> coordinates, ArrayList<Terrain> path) {
        for (int i = 0; i < path.size(); i++) {
            coordinates.add(path.get(i).getCoordination());
        }
    }

    private boolean isDestinationEmpty(UnitType unitType, Terrain destination) {
        if (unitType.equals(UnitType.WORKER) || unitType.equals(UnitType.SETTLER)) {
            if (destination.getCivilianUnit() != null)
                return false;
        }
        if (destination.getMilitaryUnit() != null)
            return false;
        return true;
    }

    private ArrayList<Terrain> findBestPath(ArrayList<ArrayList<Terrain>> paths, int MP, int maxMp, UnitType unitType) {
        sortPathsByMP(paths);
        // TODO finding a path from origin to destination
        // without breaking any rules
        // not standing on a terrain which already has a unit in it
        for (ArrayList<Terrain> path : paths) {
            if (isPathAvailable(path, MP, maxMp, unitType)) {
                return path;
            }
        }
        return null;
    }

    private boolean isPathAvailable(ArrayList<Terrain> path, int MP, int MaxMp, UnitType unitType) {
        if (path.isEmpty())
            return false;
        if (path.get(0).getMp() > MP)
            return false;
        MP -= path.get(0).getMp();
        for (int i = 1; i < path.size(); i++) {
            Terrain terrain = path.get(i);
            MP -= terrain.getMp();
            if (MP < 0) {
                MP = MaxMp;
                if (unitType.equals(UnitType.SETTLER) || unitType.equals(UnitType.WORKER)) {
                    if (terrain.getCivilianUnit() != null)
                        return false;
                } else if (terrain.getMilitaryUnit() != null)
                    return false;
                i--;
            }
        }
        return true;
    }

    private void sortPathsByMP(ArrayList<ArrayList<Terrain>> paths) {
        // TODO finding a better way to sort except than bubble sort
        for (int i = 0; i < paths.size(); i++) {
            for (int j = i + 1; j < paths.size(); j++) {
                if (getPathMP(paths.get(j)) < getPathMP(paths.get(i))) {
                    ArrayList<Terrain> pathi = paths.get(i);
                    ArrayList<Terrain> pathj = paths.get(j);
                    paths.add(i, pathj);
                    paths.remove(i);
                    paths.add(j, pathi);
                    paths.remove(j);
                }
            }
        }
    }

    private int getPathMP(ArrayList<Terrain> path) {
        int MP = 0;
        for (Terrain terrain : path) {
            MP += terrain.getMp();
        }
        return MP;
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
