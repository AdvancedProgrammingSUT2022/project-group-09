package civilization.civilization.main.java.com.civilization.Controller.GameControllerPackage;

import civilization.civilization.main.java.com.civilization.Controller.Controller;
import civilization.civilization.main.java.com.civilization.Model.*;
import civilization.civilization.main.java.com.civilization.Model.Units.Unit;
import civilization.civilization.main.java.com.civilization.View.CurrentMenu;

import java.util.Objects;
import java.util.regex.Matcher;

public class GameMenuController extends Controller {
    private final CombatController combatController;
    private final CheatController cheatController;
    private final CityController cityController;
    private final InfoController infoController;
    private final MapController mapController;
    private final UnitController unitcontroller;
    private final TechnologyMenuController technologyMenuController;

    public GameMenuController() {
        combatController = new CombatController();
        cheatController = new CheatController();
        cityController = new CityController();
        infoController = new InfoController();
        mapController = new MapController();
        unitcontroller = new UnitController();
        technologyMenuController = new TechnologyMenuController();
    }

    public String nextTurn() {
        for (Unit unit : GameDataBase.getCurrentCivilization().getUnits()) {
            if (!unit.isWorkDone())
                return GameDataBase.getCurrentCivilization().getName() + "unit :" + unit.getMyType() + "work done nashode";
        }
        doNextTurn();
        return GameDataBase.getCurrentCivilization().getInformation() + "\n next turn done";

    }

    public void doNextTurn() {
        GameDataBase.nextTurn();
        Civilization civilization = GameDataBase.getCurrentCivilization();
        for (City city : civilization.getCities()) {
            city.nextTurn();
        }
        civilization.nextTurn();
        for (Unit unit : civilization.getUnits()) {
            unit.nextTurn();
        }
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

    public boolean selectCityIsValid(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordinate = new Coordination(x, y);
        City city = null;
        if (!coordinate.isValidCoordination()) {
            return false;
        }
        if (coordinate.getTerrain() instanceof City) {
            city = (City) coordinate.getTerrain();
        }
        if (city == null) {
            return false;
        }
        return city.getCivilization() == GameDataBase.getCurrentCivilization();
    }

    public String selectCityByPosition(Matcher matcher) {
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


    public CheatController getCheatConteroller() {
        return cheatController;
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

    public CombatController getCombatController() {
        return combatController;
    }
}
