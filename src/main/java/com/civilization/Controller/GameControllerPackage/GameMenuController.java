package com.civilization.Controller.GameControllerPackage;

import com.civilization.Controller.Controller;
import com.civilization.Model.*;
import com.civilization.Model.Improvements.Improvement;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.Units.MilitaryUnit;
import com.civilization.Model.Units.Settler;
import com.civilization.Model.Units.Unit;
import com.civilization.Model.Units.Worker;
import com.civilization.View.CurrentMenu;

import java.util.Objects;
import java.util.regex.Matcher;

public class GameMenuController extends Controller {
    private Selectable selected;

    //GameDataBase darim
    private final Cheat cheat = new Cheat();
    private final CheatController cheatConteroller = new CheatController();
    private final CityController cityController = new CityController();
    private final InfoController infoController = new InfoController();
    private final MapController mapController = new MapController();
    private final UnitController unitcontroller = new UnitController();

    public String nextTurn() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {

        }
        //TODO yeseri chiza bayad ezafe beshe
        //TODO set kardan mp ha
        //call kardan unit.move() baraye hameye unit haye civilizationi k nobatesh shode
        return null;
    }

    @Override
    public String menuNavigate(Matcher matcher) {
        String menu = matcher.group("menaname");
        if (Objects.equals(menu, "Main Menu"))
            return exit();
        return "menu navigation is not possible";
    }

    public String exit() {
        CurrentMenu.set(CurrentMenu.MainMenu);
        return "entered Main Menu";
    }

    public String moveUnit(Matcher matcher) {
        if (selected instanceof Unit)
            return unitcontroller.move(matcher, (Unit) selected);
        return "no unit selected";
    }

    public String selectMilitaryUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordinate = new Coordination(x, y);
        Unit unit = coordinate.getTerrain().getMilitaryUnit();
        if (!coordinate.isValidCoordination()) {
            return "Coordinate is not valid!";
        }
        if (unit == null) {
            return "There is no military unit in this place!";
        }
        if (unit.getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "This is not your unit";
        }
        selected = unit;
        return "Unit selected successfully!";
    }

    public String selectCivilianUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordinate = new Coordination(x, y);
        Unit unit = coordinate.getTerrain().getCivilianUnit();
        if (!coordinate.isValidCoordination()) {
            return "Coordinate is not valid!";
        }
        if (unit == null) {
            return "There is no civilian unit in this place!";
        }
        if (unit.getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "This is not your unit";
        }
        selected = unit;
        return "Unit selected successfully!";
    }

    public String selectCityByPosision(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordinate = new Coordination(x, y);
        City city = null;
        if (!coordinate.isValidCoordination()) {
            return "Coordinate is not valid!";
        }
        if (coordinate.getTerrain() instanceof City) {
            city = (City) coordinate.getTerrain();
        }
        if (city == null) {
            return "There is no city in this place!";
        }
        if (city.getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "This is not your city";
        }
        selected = city;
        return "City selected successfully!";
    }

    public String selectCityByName(Matcher matcher) {
        String name = matcher.group("name");
        City city = GameDataBase.getCityByName(name);
        if (city == null) {
            return "You have not a city with this name!";
        }
        if (city.getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "This is not your city";
        }
        selected = city;
        return "City selected successfully!";
    }

    public String sleep() {
        if (!(selected instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) selected).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        ((Unit) selected).setSleep(true);
        ((Unit) selected).sleep();
        return "Unit slept successfully!";
    }

    public String alert() {
        if (!(selected instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) selected).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(selected instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        ((MilitaryUnit) selected).setInAlert(true);
        ((MilitaryUnit) selected).alert();
        return "Unit is in alert!";
    }

    public String fortiry() {
        if (!(selected instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) selected).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(selected instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        ((MilitaryUnit) selected).setFortify(true);
//        ((MilitaryUnit) selected).fortify();
        return "Unit is fortify!";
    }

    public String fortiryHeal() {
        if (!(selected instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) selected).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(selected instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        ((MilitaryUnit) selected).setFortifyHeal(true);
//        ((MilitaryUnit) selected).fortifyHeal();
        return "Unit is fortify until heal!";
    }

    public String garrison() {
        if (!(selected instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) selected).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(selected instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        if (!(((MilitaryUnit) selected).getTerrain() instanceof City)) {
            return "This unit is not in city!";
        }
        ((MilitaryUnit) selected).garrison();
        return "Unit is in garrison!";
    }

    public String setUp() {
        if (!(selected instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) selected).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(selected instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        //TODO.. setup ro nazadim
        return "Unit is set up!";
    }

    public String attack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordinate = new Coordination(x, y);
        if (!coordinate.isValidCoordination()) {
            return "Coordinate is not valid!";
        }
        if (!(selected instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) selected).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(selected instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        if (coordinate.getTerrain() instanceof City) {
            ((MilitaryUnit) selected).attack((City) coordinate.getTerrain());
        } else if (coordinate.getTerrain().getMilitaryUnit() != null) {
            ((MilitaryUnit) selected).attack(coordinate.getTerrain().getMilitaryUnit());
        } else if (coordinate.getTerrain().getCivilianUnit() != null) {
            ((MilitaryUnit) selected).attack(coordinate.getTerrain().getCivilianUnit());
        } else {
            return "You can't attack this position!";
        }
        return "Attacked!";
    }

    public String foundCity() {
        if (!(selected instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) selected).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(selected instanceof Settler)) {
            return "This is not a settler unit!";
        }
        if (((Settler) selected).getTerrain() instanceof City) {
            return "There is City in this position!";
        }
        ((Settler) selected).foundNormalCity();//TODO.. capital nemitoone besaze ? va erroraye dige chi mitoone bokhore?
        return "City created successfully!";
    }

    public String cancellMission() {
        if (!(selected instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) selected).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        ((Unit) selected).Donothong();
        return "Cancelled!";
    }

    public String wake() {
        if (!(selected instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) selected).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        ((Unit) selected).setSleep(false);
        ((Unit) selected).wake();
        return "Unit waked up successfully!";
    }

    public String delete() {
        if (!(selected instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) selected).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        ((Unit) selected).delete();
        return "Unit deleted successfully!";
    }

    private String checkWorker() {
        if (!(selected instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) selected).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(selected instanceof Worker)) {
            return "This is not a worker unit!";
        }
        return null;
    }

    public String buildRoad() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) selected).getTerrain().getImprovement() == Improvement.ROAD) {
            return "There is road in this position!";
        }
        if (!Improvement.ROAD.checkIsPossible(((Worker) selected).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) selected).makeImprovement(Improvement.ROAD);
        return "Road created successfully!";
    }

    public String buildMine() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) selected).getTerrain().getImprovement() == Improvement.FARM) {
            return "There is mine in this position!";
        }
        if (!Improvement.MINE.checkIsPossible(((Worker) selected).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) selected).makeImprovement(Improvement.FARM);
        return "Mine created successfully!";
    }

    public String buildTradingPost() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) selected).getTerrain().getImprovement() == Improvement.TRADINGPOST) {
            return "There is farm in this position!";
        }
        if (!Improvement.TRADINGPOST.checkIsPossible(((Worker) selected).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) selected).makeImprovement(Improvement.TRADINGPOST);
        return "Farm created successfully!";
    }

    public String buildFarm() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) selected).getTerrain().getImprovement() == Improvement.FARM) {
            return "There is farm in this position!";
        }
        if (!Improvement.FARM.checkIsPossible(((Worker) selected).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) selected).makeImprovement(Improvement.FARM);
        return "Farm created successfully!";
    }

    public String buildLumberMill() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) selected).getTerrain().getImprovement() == Improvement.LUMBERMILL) {
            return "There is lumber mill in this position!";
        }
        if (!Improvement.LUMBERMILL.checkIsPossible(((Worker) selected).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) selected).makeImprovement(Improvement.LUMBERMILL);
        return "Lumber mill created successfully!";
    }

    public String buildPasture() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) selected).getTerrain().getImprovement() == Improvement.PASTURE) {
            return "There is pasture in this position!";
        }
        if (!Improvement.PASTURE.checkIsPossible(((Worker) selected).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) selected).makeImprovement(Improvement.PASTURE);
        return "Pasture created successfully!";
    }

    public String buildCamp() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) selected).getTerrain().getImprovement() == Improvement.CAMP) {
            return "There is camp mill in this position!";
        }
        if (!Improvement.CAMP.checkIsPossible(((Worker) selected).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) selected).makeImprovement(Improvement.CAMP);
        return "Camp created successfully!";
    }

    public String buildPlantation() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) selected).getTerrain().getImprovement() == Improvement.PLANTATION) {
            return "There is plantation in this position!";
        }
        if (!Improvement.PLANTATION.checkIsPossible(((Worker) selected).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) selected).makeImprovement(Improvement.PLANTATION);
        return "Plantation created successfully!";
    }

    public String buildQuarry() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) selected).getTerrain().getImprovement() == Improvement.QUARRY) {
            return "There is quarry in this position!";
        }
        if (!Improvement.QUARRY.checkIsPossible(((Worker) selected).getTerrain())) {
            return "Build is not possible!";
        }
        ((Worker) selected).makeImprovement(Improvement.QUARRY);
        return "Quarry created successfully!";
    }

    public String removeJungle() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (!((Worker) selected).getTerrain().getTerrainFeatures().contains(TerrainFeature.JUNGLE) &&
                !((Worker) selected).getTerrain().getTerrainFeatures().contains(TerrainFeature.FOREST)) {
            return "There is no jungle or forrest in this place!";
        }
        ((Worker) selected).removeJungle(((Worker) selected).getTerrain());
        return "Jungle removed successfully!";
    }

    public String removeRoute() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (!((Worker) selected).getTerrain().getImprovement().equals(Improvement.ROAD)) {
            return "There is no road in this place!";
        }
        ((Worker) selected).removeRoute(((Worker) selected).getTerrain());
        return "Road removed successfully!";
    }

    public String repair() {
        String command = checkWorker();
        if (command != null)
            return command;
        //TODO kharabe kojast ?
        ((Worker) selected).repair(((Worker) selected).getTerrain());
        return "Repair successfully!";
    }

    protected Unit getSelectedUnit() {
        if (selected instanceof Unit)
            return (Unit) selected;
        return null;
    }

    protected void setSelected(Unit selectedUnit) {
        this.selected = selectedUnit;
    }

    protected City getSelectedCity() {
        if (selected instanceof City)
            return (City) selected;
        return null;
    }

    protected void setSelected(City selectedCity) {
        this.selected = selectedCity;
    }

    public InfoController getInfoController() {
        return infoController;
    }
}
