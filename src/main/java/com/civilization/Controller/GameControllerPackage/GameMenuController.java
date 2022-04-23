package com.civilization.Controller.GameControllerPackage;

import com.civilization.Controller.Controller;
import com.civilization.Model.Cheat;
import com.civilization.View.CurrentMenu;

import java.util.regex.Matcher;

public class GameMenuController extends Controller {
    //GameDataBase darim
    Cheat cheat=new Cheat();
    CheatController cheatConteroller=new CheatController();
    CityController cityController=new CityController();
    InfoController infoController=new InfoController();
    MapController mapController=new MapController();
    UnitController unitcontroller=new UnitController();

    public String nextTurn(Matcher matcher) {
        //TODO INJA MIAD HARCHI GOLD O INAST RO EZAFE MIKONE BARA TURN BADI
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
