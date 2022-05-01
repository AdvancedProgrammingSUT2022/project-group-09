package com.civilization.Controller.GameControllerPackage;

import java.util.ArrayList;
import java.util.regex.Matcher;

import com.civilization.Model.City;
import com.civilization.Model.Coordination;
import com.civilization.Model.Improvements.Improvement;
import com.civilization.Model.Map;
import com.civilization.Model.Selectable;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Units.*;

public class UnitController {

    public String moveUnit(Matcher matcher) {
        if (GameDataBase.getSelected() instanceof Unit)
            return move(matcher, (Unit) GameDataBase.getSelected());
        return "no unit selected";
    }

    public String sleep() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        ((Unit) GameDataBase.getSelected()).sleep();
        return "Unit slept successfully!";
    }

    public String alert() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(GameDataBase.getSelected() instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        ((MilitaryUnit) GameDataBase.getSelected()).alert();
        return "Unit is in alert!";
    }

    public String fortiry() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(GameDataBase.getSelected() instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        ((MilitaryUnit) GameDataBase.getSelected()).fortify();
        return "Unit is fortify!";
    }

    public String fortiryHeal() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(GameDataBase.getSelected() instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        ((MilitaryUnit) GameDataBase.getSelected()).fortifyHeal();
        return "Unit is fortify until heal!";
    }

    public String garrison() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(GameDataBase.getSelected() instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        if (!(((MilitaryUnit) GameDataBase.getSelected()).getTerrain() instanceof City)) {
            return "This unit is not in city!";
        }
        ((MilitaryUnit) GameDataBase.getSelected()).garrison();
        return "Unit is in garrison!";
    }

    public String setUp() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(GameDataBase.getSelected() instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        // TODO.. setup ro nazadim
        return "Unit is set up!";
    }

    public String attack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordinate = new Coordination(x, y);
        if (!coordinate.isValidCoordination()) {
            return "Coordinate is not valid!";
        }
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(GameDataBase.getSelected() instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        if (coordinate.getTerrain() instanceof City) {
            ((MilitaryUnit) GameDataBase.getSelected()).attack((City) coordinate.getTerrain());
        } else if (coordinate.getTerrain().getMilitaryUnit() != null) {
            ((MilitaryUnit) GameDataBase.getSelected()).attack(coordinate.getTerrain().getMilitaryUnit());
        } else if (coordinate.getTerrain().getCivilianUnit() != null) {
            ((MilitaryUnit) GameDataBase.getSelected()).attack(coordinate.getTerrain().getCivilianUnit());
        } else {
            return "You can't attack this position!";
        }
        return "Attacked!";
    }

    public String foundCity() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(GameDataBase.getSelected() instanceof Settler)) {
            return "This is not a settler unit!";
        }
        if (((Settler) GameDataBase.getSelected()).getTerrain() instanceof City) {
            return "There is City in this position!";
        }
        ((Settler) GameDataBase.getSelected()).foundCaptalCity();// TODO.. capital nemitoone besaze ? va erroraye dige
                                                                 // chi mitoone bokhore?
        return "City created successfully!";
    }

    public String cancellMission() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        ((Unit) GameDataBase.getSelected()).DoNothing();
        return "Cancelled!";
    }

    public String wake() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        ((Unit) GameDataBase.getSelected()).setSleep(false);
        ((Unit) GameDataBase.getSelected()).wake();
        return "Unit waked up successfully!";
    }

    public String delete() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        ((Unit) GameDataBase.getSelected()).delete();
        GameDataBase.setSelected(null);
        return "Unit deleted successfully!";
    }

    private String checkWorker() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(GameDataBase.getSelected() instanceof Worker)) {
            return "This is not a worker unit!";
        }
        return null;
    }

    public String buildRoad() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().isHasRoad()) {
            return "There is road in this position!";
        }
        if (!Improvement.ROAD.checkIsPossible(((Worker) GameDataBase.getSelected()).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) GameDataBase.getSelected()).makeImprovement(Improvement.ROAD);
        return "Road created successfully!";
    }

    public String buildMine() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovement() == Improvement.FARM) {
            return "There is mine in this position!";
        }
        if (!Improvement.MINE.checkIsPossible(((Worker) GameDataBase.getSelected()).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) GameDataBase.getSelected()).makeImprovement(Improvement.FARM);
        return "Mine created successfully!";
    }

    public String buildTradingPost() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovement() == Improvement.TRADINGPOST) {
            return "There is farm in this position!";
        }
        if (!Improvement.TRADINGPOST.checkIsPossible(((Worker) GameDataBase.getSelected()).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) GameDataBase.getSelected()).makeImprovement(Improvement.TRADINGPOST);
        return "Farm created successfully!";
    }

    public String buildFarm() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovement() == Improvement.FARM) {
            return "There is farm in this position!";
        }
        if (!Improvement.FARM.checkIsPossible(((Worker) GameDataBase.getSelected()).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) GameDataBase.getSelected()).makeImprovement(Improvement.FARM);
        return "Farm created successfully!";
    }

    public String buildLumberMill() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovement() == Improvement.LUMBERMILL) {
            return "There is lumber mill in this position!";
        }
        if (!Improvement.LUMBERMILL.checkIsPossible(((Worker) GameDataBase.getSelected()).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) GameDataBase.getSelected()).makeImprovement(Improvement.LUMBERMILL);
        return "Lumber mill created successfully!";
    }

    public String buildPasture() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovement() == Improvement.PASTURE) {
            return "There is pasture in this position!";
        }
        if (!Improvement.PASTURE.checkIsPossible(((Worker) GameDataBase.getSelected()).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) GameDataBase.getSelected()).makeImprovement(Improvement.PASTURE);
        return "Pasture created successfully!";
    }

    public String buildCamp() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovement() == Improvement.CAMP) {
            return "There is camp mill in this position!";
        }
        if (!Improvement.CAMP.checkIsPossible(((Worker) GameDataBase.getSelected()).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) GameDataBase.getSelected()).makeImprovement(Improvement.CAMP);
        return "Camp created successfully!";
    }

    public String buildPlantation() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovement() == Improvement.PLANTATION) {
            return "There is plantation in this position!";
        }
        if (!Improvement.PLANTATION.checkIsPossible(((Worker) GameDataBase.getSelected()).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) GameDataBase.getSelected()).makeImprovement(Improvement.PLANTATION);
        return "Plantation created successfully!";
    }

    public String buildQuarry() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovement() == Improvement.QUARRY) {
            return "There is quarry in this position!";
        }
        if (!Improvement.QUARRY.checkIsPossible(((Worker) GameDataBase.getSelected()).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) GameDataBase.getSelected()).makeImprovement(Improvement.QUARRY);
        return "Quarry created successfully!";
    }

    public String removeJungle() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (!((Worker) GameDataBase.getSelected()).getTerrain().getTerrainFeatures().contains(TerrainFeature.JUNGLE) &&
                !((Worker) GameDataBase.getSelected()).getTerrain().getTerrainFeatures()
                        .contains(TerrainFeature.FOREST)) {
            return "There is no jungle or forrest in this place!";
        }
        ((Worker) GameDataBase.getSelected()).removeJungle();
        return "Jungle removed successfully!";
    }

    public String removeRoute() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (!((Worker) GameDataBase.getSelected()).getTerrain().getImprovement().equals(Improvement.ROAD)) {
            return "There is no road in this place!";
        }
        ((Worker) GameDataBase.getSelected()).removeRoute();
        return "Road removed successfully!";
    }

    public String repair() {
        String command = checkWorker();
        if (command != null)
            return command;
        // TODO kharabe kojast ?
        ((Worker) GameDataBase.getSelected()).repair();
        return "Repair successfully!";
    }

    public String move(Matcher matcher, Unit unit) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));

        Coordination coordination = new Coordination(x, y);
        System.out.println(coordination.toString());
        if (!coordination.isValidCoordination())
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

        //TODO handel moving to different terrainstates
        if (!isDestinationEmpty(unitType, destination))
            return "destination is not empty for you";
        // return backTrack(destination, origin, MP, maxMp, unitType, unit);
        return DjikstraPathFind(destination, origin, MP, maxMp, unitType, unit);
    }

    private String DjikstraPathFind(Terrain destination, Terrain origin, int MP, int maxMp, UnitType unitType, Unit unit) {
        //TODO implement djikstra if needed
        return "";
    }

    private String backTrack(Terrain destination, Terrain origin, int MP, int maxMp, UnitType unitType, Unit unit) {
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
        pathCoordination.remove(0);
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
            if (isMovePossible(MP, nextTerrain, origin) && !path.contains(nextTerrain)) {
                if (path.size() > 5)
                    continue;
                ArrayList<Terrain> nextPath = (ArrayList<Terrain>) path.clone();
                nextPath.add(nextTerrain);
                findAllPaths(destination, nextTerrain, MP, paths, nextPath);
            }
        }
    }

    private boolean isMovePossible(int MP, Terrain nextTerrain, Terrain terrain) {
        if (nextTerrain.getTerrainFeatures().contains(TerrainFeature.RIVER)
                && terrain.getTerrainFeatures().contains(TerrainFeature.RIVER))
            return false;
        if (MP > terrain.getMp())
            return true;
        return false;
    }

}
