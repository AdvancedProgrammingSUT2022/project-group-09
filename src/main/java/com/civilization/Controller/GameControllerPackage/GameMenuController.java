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


    //GameDataBase darim
    private final Cheat cheat = new Cheat();
    private final CheatController cheatConteroller;
    private final CityController cityController;
    private final InfoController infoController;
    private final MapController mapController;
    private final UnitController unitcontroller;
    private final TechnologyMenuController technologyMenuController;

    public GameMenuController() {
        cheatConteroller = new CheatController();
        cityController = new CityController();
        infoController = new InfoController();
        mapController = new MapController();
        unitcontroller = new UnitController();
        technologyMenuController = new TechnologyMenuController();
    }

    public String nextTurn() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            for (City city : civilization.getCities()) {
                city.nextTurn();
            }
            civilization.nextTurn();
            for (Unit unit : civilization.getUnits()) {
                unit.setRemainingMove(unit.getMyType().getMovement());
                unit.move();
            }
        }
        return "next turn";
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
        GameDataBase.setSelected(unit);
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
        GameDataBase.setSelected(unit);
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
        GameDataBase.setSelected(city);
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
        GameDataBase.setSelected(city);
        return "City selected successfully!";
    }


    protected Unit getSelectedUnit() {
        if (GameDataBase.getSelected() instanceof Unit)
            return (Unit) GameDataBase.getSelected();
        return null;
    }

    protected City getSelectedCity() {
        if (GameDataBase.getSelected() instanceof City)
            return (City) GameDataBase.getSelected();
        return null;
    }


    public InfoController getInfoController() {
        return infoController;
    }


    public Cheat getCheat() {
        return cheat;
    }

    public CheatController getCheatConteroller() {
        return cheatConteroller;
    }

    public CityController getCityController() {
        return cityController;
    }

    public MapController getMapController() {
        return mapController;
    }

    public UnitController getUnitcontroller() {
        return unitcontroller;
    }

    public TechnologyMenuController getTechnologyMenuController() {
        return technologyMenuController;
    }
}
