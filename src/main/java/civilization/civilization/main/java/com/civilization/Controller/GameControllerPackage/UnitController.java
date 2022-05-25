package civilization.civilization.main.java.com.civilization.Controller.GameControllerPackage;

import java.util.ArrayList;
import java.util.regex.Matcher;

import civilization.civilization.main.java.com.civilization.Model.City;
import civilization.civilization.main.java.com.civilization.Model.Coordination;
import civilization.civilization.main.java.com.civilization.Model.Improvements.Improvement;
import civilization.civilization.main.java.com.civilization.Model.TerrainFeatures.TerrainFeature;
import civilization.civilization.main.java.com.civilization.Model.Terrains.Terrain;
import civilization.civilization.main.java.com.civilization.Model.Terrains.TerrainState;
import civilization.civilization.main.java.com.civilization.Model.Terrains.TerrainType;
import civilization.civilization.main.java.com.civilization.Model.Units.*;

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
        Unit unit = ((Unit) GameDataBase.getSelected());
        unit.sleep();
        GameDataBase.getCurrentCivilization().updateNotification("Unit " + unit.getMyType().name() + " on "
                + unit.getTerrain().getCoordination().toString() + " is now sleeping!");
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
        MilitaryUnit unit = ((MilitaryUnit) GameDataBase.getSelected());
        unit.setInAlert(true);
        GameDataBase.getCurrentCivilization().updateNotification("Military unit " + unit.getMyType().name() + " on "
                + unit.getTerrain().getCoordination().toString() + " is now in alert!");
        return "Unit is in alert!";
    }

    public String fortify() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(GameDataBase.getSelected() instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        MilitaryUnit unit = ((MilitaryUnit) GameDataBase.getSelected());
        unit.fortify();
        GameDataBase.getCurrentCivilization().updateNotification("Military unit " + unit.getMyType().name() + " on "
                + unit.getTerrain().getCoordination().toString() + " is now in fortify!");
        return "Unit is fortify!";
    }

    public String fortifyHeal() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(GameDataBase.getSelected() instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        MilitaryUnit unit = ((MilitaryUnit) GameDataBase.getSelected());
        unit.setFortifyHeal(true);
        GameDataBase.getCurrentCivilization().updateNotification("Military unit " + unit.getMyType().name() + " on "
                + unit.getTerrain().getCoordination().toString() + " is now in fortify until full health!");
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
        MilitaryUnit unit = ((MilitaryUnit) GameDataBase.getSelected());
        GameDataBase.getCurrentCivilization().updateNotification("Military unit " + unit.getMyType().name() + " on "
                + unit.getTerrain().getCoordination().toString() + " is now in garrison!");
        unit.garrison();
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
        if (!UnitType.getSiegeMilitaryUnit().contains(((Unit) GameDataBase.getSelected()).getMyType()))
            return "this unit can not siege";
        SiegeMilitaryUnit unit = ((SiegeMilitaryUnit) GameDataBase.getSelected());
        unit.setUp();
        GameDataBase.getCurrentCivilization().updateNotification("Military unit " + unit.getMyType().name() + " on "
                + unit.getTerrain().getCoordination().toString() + " is now set up!");
        return "Unit is set up!";
    }

    public String pillage() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(GameDataBase.getSelected() instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        if (((MilitaryUnit) GameDataBase.getSelected()).getTerrain().getImprovement() == null) {
            return "There is not improvement in this position!";
        }
        if (!((MilitaryUnit) GameDataBase.getSelected()).getTerrain().getImprovementPair().getValue()) {
            return "improvement kharab shode va dobare pillage nemishe";
        }
        MilitaryUnit unit = ((MilitaryUnit) GameDataBase.getSelected());
        unit.pillage();
        GameDataBase.getCurrentCivilization().updateNotification("Military unit " + unit.getMyType().name() + " on "
                + unit.getTerrain().getCoordination().toString() + " pillaged the city");
        return "City successfully pillaged!";
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
        for (Terrain terrain : ((Settler) GameDataBase.getSelected()).getTerrain().getSurroundingTerrain()) {
            if (terrain instanceof City) {
                return "There is City around this position!";
            }
        }
        Settler settler = (Settler) GameDataBase.getSelected();
        GameDataBase.getCurrentCivilization()
                .updateNotification("settler found a city on " + settler.getTerrain().getCoordination().toString());
        settler.foundCity();
        return "City created successfully!";
    }

    public String doNothing() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        ((Unit) GameDataBase.getSelected()).DoNothing();
        return "done!";
    }

    public String wake() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        Unit unit = (Unit) GameDataBase.getSelected();
        unit.wake();
        GameDataBase.getCurrentCivilization().updateNotification("Unit " + unit.getMyType().name() + " on "
                + unit.getTerrain().getCoordination().toString() + " is now awake!");
        return "Unit waked up successfully!";
    }

    public String delete() {
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        Unit unit = ((Unit) GameDataBase.getSelected());
        GameDataBase.getCurrentCivilization().updateNotification("Unit " + unit.getMyType().name() + " on "
                + unit.getTerrain().getCoordination().toString() + " is now deleted!");
        unit.delete();
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
        if (((Unit) GameDataBase.getSelected()).getTerrain().getCivilization() != GameDataBase
                .getCurrentCivilization()) {
            return "This terrain is not yours!";
        }
        return null;
    }

    public String showWorkerInfo() {
        String command = checkWorker();
        if (command != null)
            return command;
        return ((Worker) GameDataBase.getSelected()).showInfo();
    }

    public String buildRoad() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().isHasRoad()) {
            return "There is road in this position!";
        }
        if (((Worker) GameDataBase.getSelected()).getTerrain().isHasRoad()) {
            return "There is road in this position!";
        }
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.ROAD);
        GameDataBase.getCurrentCivilization()
                .updateNotification("A worker made a road on " + worker.getTerrain().getCoordination().toString());
        return "Road created successfully!";
    }

    public String buildMine() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovement() == Improvement.MINE) {
            return "There is mine in this position!";
        }
        if (!Improvement.MINE.checkIsPossible(((Worker) GameDataBase.getSelected()).getTerrain())) {
            return "Build is not possible!";
        }
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = (Worker) GameDataBase.getSelected();
        worker.makeImprovement(Improvement.MINE);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker made a mine on " + worker.getTerrain().getCoordination().toString());
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
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.TRADINGPOST);
        GameDataBase.getCurrentCivilization().updateNotification(
                "a worker made a trading post on " + worker.getTerrain().getCoordination().toString());
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
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.FARM);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker made a farm on " + worker.getTerrain().getCoordination().toString());
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
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.LUMBERMILL);
        GameDataBase.getCurrentCivilization().updateNotification(
                "a worker made a lubmermill on " + worker.getTerrain().getCoordination().toString());
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
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.PASTURE);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker made a pasture on " + worker.getTerrain().getCoordination().toString());
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
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.CAMP);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker made a camp on " + worker.getTerrain().getCoordination().toString());
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
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.PLANTATION);
        GameDataBase.getCurrentCivilization().updateNotification(
                "a worker made a plantation on " + worker.getTerrain().getCoordination().toString());
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
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.QUARRY);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker made a quarry on " + worker.getTerrain().getCoordination().toString());
        return "Quarry created successfully!";
    }

    public String removeJungle() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (!((Worker) GameDataBase.getSelected()).getTerrain().getTerrainFeatures().contains(TerrainFeature.JUNGLE)) {
            return "There is no jungle or forrest in this place!";
        }
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.REMOVE_JUNGLE);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker removed jungle on " + worker.getTerrain().getCoordination().toString());
        return "Jungle removed successfully!";
    }

    public String removeRoute() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (!((Worker) GameDataBase.getSelected()).getTerrain().isHasRoad()) {
            return "There is no road in this place!";
        }
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.REMOVE_ROUTE);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker removed route on " + worker.getTerrain().getCoordination().toString());
        return "Road removed successfully!";
    }

    public String removeMarsh() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (!((Worker) GameDataBase.getSelected()).getTerrain().getTerrainFeatures().contains(TerrainFeature.MARSH)) {
            return "There is no marsh or forrest in this place!";
        }
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.REMOVE_MARSH);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker removed marsh on " + worker.getTerrain().getCoordination().toString());
        return "marsh removed successfully!";
    }

    public String removeForest() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (!((Worker) GameDataBase.getSelected()).getTerrain().getTerrainFeatures()
                .contains(TerrainFeature.FOREST)) {
            return "There is no forest or forrest in this place!";
        }
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.REMOVE_FOREST);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker removed forest on " + worker.getTerrain().getCoordination().toString());
        return "forest removed successfully!";
    }

    public String repair() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovementPair() == null)
            return "improvementi nadarim";
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovementPair().getValue())
            return "salem hast in improvement";
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.REPAIR);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker repaired on " + worker.getTerrain().getCoordination().toString());
        return "Repair successfully!";
    }

    public String move(Matcher matcher, Unit unit) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordination = new Coordination(x, y);
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
        GameDataBase.getCurrentCivilization().getMap().updateExploration();
        Terrain origin = unit.getTerrain();
        int MP = unit.getRemainingMove();
        int maxMp = unit.getMyType().getMovement();
        UnitType unitType = unit.getMyType();
        Coordination coordination = destination.getCoordination();
        TerrainState state = GameDataBase.getCurrentCivilization().getTerrainState(coordination.getX(),
                coordination.getY());
        if (unit.getRemainingMove() <= 0)
            return "unfortunately you don't have enough moving point to move your unit";
        if (state == TerrainState.FOG_OF_WAR)
            return "your destination is in fog of war";
        if (!isDestinationEmpty(unitType, destination))
            return "destination is not empty for you";
        return backTrack(destination, origin, MP, maxMp, unitType, unit);
        // return DjikstraPathFind(destination, origin, MP, maxMp, unitType, unit);
    }

    // private String DjikstraPathFind(Terrain destination, Terrain origin, int MP,
    // int maxMp, UnitType unitType,
    // Unit unit) {
    // return "";
    // }

    private String backTrack(Terrain destination, Terrain origin, int MP, int maxMp, UnitType unitType, Unit unit) {
        ArrayList<ArrayList<Terrain>> paths = new ArrayList<>();
        ArrayList<Terrain> path = new ArrayList<>();
        path.add(origin);

        Coordination minimum = new Coordination(Math.min(origin.getXPosition(), destination.getXPosition()),
                Math.min(origin.getYPosition(), destination.getYPosition()));
        Coordination maximum = new Coordination(Math.max(origin.getXPosition(), destination.getXPosition()),
                Math.max(origin.getYPosition(), destination.getYPosition()));

        findAllPaths(destination, origin, maxMp, paths, path, maximum, minimum);
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
        GameDataBase.getCurrentCivilization().updateNotification("unit " + unitType.name() + " started moving from "
                + origin.getCoordination().toString() + " to " + destination.getCoordination().toString());
        return "unit moved successfully";
    }

    public ArrayList<Coordination> getBestPath(Terrain destination, Terrain origin, Unit unit) {
        int maxMp = unit.getMyType().getMovement();
        UnitType unitType = unit.getMyType();
        ArrayList<ArrayList<Terrain>> paths = new ArrayList<>();
        ArrayList<Terrain> path = new ArrayList<>();
        path.add(origin);

        Coordination minimum = new Coordination(Math.min(origin.getXPosition(), destination.getXPosition()),
                Math.min(origin.getYPosition(), destination.getYPosition()));
        Coordination maximum = new Coordination(Math.max(origin.getXPosition(), destination.getXPosition()),
                Math.max(origin.getYPosition(), destination.getYPosition()));
        findAllPaths(destination, origin, maxMp, paths, path, maximum, minimum);
        if (paths.isEmpty())
            return null;

        ArrayList<Terrain> bestPath = findBestPath(paths, unitType.getMovement(), maxMp, unitType);
        if (bestPath == null)
            return null;

        ArrayList<Coordination> pathCoordination = new ArrayList<>();
        setPathCoordinates(pathCoordination, bestPath);
        pathCoordination.remove(0);
        return pathCoordination;
    }

    public int turnNeedToMove(Terrain destination, Terrain origin, Unit unit) {
        Terrain currentTerrain = unit.getTerrain();
        int res = 0;
        int remainingMove = unit.getMyType().getMovement();
        ArrayList<Coordination> path = getBestPath(destination, origin, unit);
        if (path == null)
            return res;
        while (path.size() != 1) {
            for (int i = 0; i < path.size(); i++) {
                Terrain terrain = path.get(i).getTerrain();
                if (currentTerrain.isHasRoad() && terrain.isHasRoad()) {

                } else if (terrain.getTerrainFeatures().contains(TerrainFeature.RIVER)
                        && currentTerrain.getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                    remainingMove = 0;
                } else {
                    remainingMove -= terrain.getMp();
                }
                currentTerrain = terrain;
                path.remove(i);
                i = 0;
                if (remainingMove < 0) {
                    break;
                }
            }
            remainingMove = unit.getMyType().getMovement();
            res++;
        }
        return res;
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
        MP -= path.get(0).getMp();
        for (int i = 1; i < path.size(); i++) {
            Terrain terrain = path.get(i);
            if (terrain.isHasRoad() && path.get(i - 1).isHasRoad()) {

            } else if (terrain.getTerrainFeatures().contains(TerrainFeature.RIVER)
                    && path.get(i - 1).getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                MP = 0;
            } else {
                MP -= terrain.getMp();
            }
            if (MP <= 0) {
                MP = MaxMp;
                if (unitType.equals(UnitType.SETTLER) || unitType.equals(UnitType.WORKER)) {
                    if (terrain.getCivilianUnit() != null)
                        return false;
                } else if (terrain.getMilitaryUnit() != null)
                    return false;
            }
        }
        return true;
    }

    private void sortPathsByMP(ArrayList<ArrayList<Terrain>> paths) {
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
                              ArrayList<Terrain> path, Coordination maximum, Coordination minimum) {
        if (destination == origin) {
            paths.add(path);
            return;
        }
        for (Terrain nextTerrain : origin.getSurroundingTerrain()) {
            if (isMovePossible(MP, nextTerrain, origin) && !path.contains(nextTerrain)) {
                if (!isPathWorthChecking(path, nextTerrain, maximum, minimum))
                    continue;
                ArrayList<Terrain> nextPath = (ArrayList<Terrain>) path.clone();
                nextPath.add(nextTerrain);
                findAllPaths(destination, nextTerrain, MP, paths, nextPath, maximum, minimum);
            }
        }
    }

    private boolean isPathWorthChecking(ArrayList<Terrain> path, Terrain terrain, Coordination maximum,
                                        Coordination minimum) {
        if (path.size() > 8)
            return false;
        if (terrain.getXPosition() - maximum.getX() > 3 || terrain.getYPosition() - maximum.getY() > 3)
            return false;
        if (minimum.getX() - terrain.getXPosition() > 3 || minimum.getY() - terrain.getYPosition() > 3)
            return false;
        return true;
    }

    private boolean isMovePossible(int MP, Terrain nextTerrain, Terrain terrain) {
        if (nextTerrain.getType() == TerrainType.MOUNTAIN || nextTerrain.getType() == TerrainType.OCEAN)
            return false;
        if (GameDataBase.getCurrentCivilization().getTerrainState(nextTerrain.getXPosition(),
                nextTerrain.getYPosition()) == TerrainState.FOG_OF_WAR) {
            return false;
        }
        return true;
    }

}
