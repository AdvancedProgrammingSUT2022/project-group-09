package com.civilization.Controller.GameControllerPackage;

import com.civilization.Controller.Controller;
import com.civilization.Model.Cheat;
import com.civilization.Model.Civilization;
import com.civilization.View.CurrentMenu;

import java.util.regex.Matcher;

public class GameMenuController extends Controller {
    //GameDataBase darim
    Cheat cheat = new Cheat();
    CheatController cheatConteroller = new CheatController();
    CityController cityController = new CityController();
    InfoController infoController = new InfoController();
    MapController mapController = new MapController();
    UnitController unitcontroller = new UnitController();

    public String nextTurn() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {

        }
        //TODO yeseri chiza bayad ezafe beshe
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
}
