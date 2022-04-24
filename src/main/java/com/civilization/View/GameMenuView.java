package com.civilization.View;

import java.util.Scanner;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Controller.GameControllerPackage.GameMenuController;
import com.civilization.Controller.GameControllerPackage.MapController;
import com.civilization.MenuRegex.GameMenuRegex;
import com.civilization.MenuRegex.MainMenuRegex;

public class GameMenuView extends View {
    private final GameMenuController gameMenuController;
    private final MapController mapController;

    public GameMenuView(Scanner scanner, GameMenuController gameMenuController) {
        super(scanner);
        this.gameMenuController = gameMenuController;
        this.mapController = new MapController();
    }

    @Override
    public void run() {

        while (CurrentMenu.get() == CurrentMenu.GameMenu) {
            input = scanner.nextLine();
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.EXIT)) != null)
                System.out.println(gameMenuController.exit());
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWCURRENTMENU)) != null)
                System.out.println(CurrentMenu.get());
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWMAP)) != null)
                System.out.print(mapController.showMap(matcher, GameDataBase.getCurrentCivilization().getMap()));
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.ENTER)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.MOVEUNIT)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWRESEARCHINFORMATION)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWUNITS)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWCITIES)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWDIPLOMACYINFORMATION)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWVICTORYINFORMATION)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWDEMOGRAPHICSINFORMATION)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWNOTIFICATIONS)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWMILITARYINFORMATION)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWECONOMICINFORMATION)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWDIPLOMATICINFORMATION)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWDEALS)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECTUNIT)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECTCITY1)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECTCITY2)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.MOVETO)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SLEEP)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.ALERT)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.FORTIFY)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.FORTIFYHEAL)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.GARRISON)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SETUP)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.ATTACK)) != null) {
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.FOUND)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.CANCEL)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.WAKE)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.DELETE)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDROAD)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDRAILROAD)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDFARM)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDMINE)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDTRADINGPOST)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDLUMBERMILL)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDPASTURE)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDCAMP)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDPLANTATION)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDQUARRY)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.REMOVEjungle)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.REMOVEROUTE)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWMAPCITY)) != null) {
                System.out.println("");
                //TODO
            }
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.MOVEMAP)) != null) {
                System.out.println("");
                //TODO
            }
            else
                System.out.println("invalid command");

        }
    }

    private void showMenu() {

    }
}
