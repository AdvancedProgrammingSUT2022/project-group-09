package com.civilization.Controller.GameControllerPackage;

import com.civilization.Controller.Controller;
import com.civilization.Model.*;
import com.civilization.Model.Units.Combatble;
import com.civilization.Model.Units.MilitaryUnit;
import com.civilization.Model.Units.Unit;
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
            return "selectedo bayad har turn null mikardim";
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
            return "selectedo bayad har turn null mikardim";
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
            return "selectedo bayad har turn null mikardim";
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
            return "selectedo bayad har turn null mikardim";
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
            return "selectedo bayad har turn null mikardim";
        }
        if (!(selected instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        if (!(((MilitaryUnit) selected).getTerrain() instanceof City)){
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
            return "selectedo bayad har turn null mikardim";
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
            return "selectedo bayad har turn null mikardim";
        }
        if (!(selected instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        if(coordinate.getTerrain() instanceof City) {
            ((MilitaryUnit) selected).attack((City) coordinate.getTerrain());
        }
        else if(coordinate.getTerrain().getMilitaryUnit() != null) {
            ((MilitaryUnit) selected).attack(coordinate.getTerrain().getMilitaryUnit());
        }
        else if(coordinate.getTerrain().getCivilianUnit() != null) {
            ((MilitaryUnit) selected).attack(coordinate.getTerrain().getCivilianUnit());
        }
        else {
            return "You can't attack this position!";
        }
        return "Attacked!";
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
