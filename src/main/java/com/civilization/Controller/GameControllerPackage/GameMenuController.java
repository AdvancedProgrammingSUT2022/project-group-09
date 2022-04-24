package com.civilization.Controller.GameControllerPackage;

import com.civilization.Controller.Controller;
import com.civilization.Model.Cheat;
import com.civilization.Model.City;
import com.civilization.Model.Civilization;
import com.civilization.Model.Selectable;
import com.civilization.Model.Units.Unit;
import com.civilization.View.CurrentMenu;

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
        return null;
    }

    public String exit() {
        CurrentMenu.set(CurrentMenu.MainMenu);
        return "entered main menu";
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
}
