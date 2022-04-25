package com.civilization.View;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Controller.GameControllerPackage.GameMenuController;
import com.civilization.MenuRegex.GameMenuRegex;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenuView extends View {
    private final GameMenuController gameMenuController;

    public GameMenuView(Scanner scanner, GameMenuController gameMenuController) {
        super(scanner);
        this.gameMenuController = gameMenuController;
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
                showMap(matcher);
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.ENTER)) != null) {
                System.out.println(gameMenuController.menuNavigate(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.MOVEUNIT)) != null) {
                System.out.println(gameMenuController.moveUnit(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWRESEARCHINFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showResearch());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWUNITS)) != null) {
                System.out.println(gameMenuController.getInfoController().showUnits());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWCITIES)) != null) {
                System.out.println(gameMenuController.getInfoController().showCities());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWDIPLOMACYINFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showDiplomacy());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWVICTORYINFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showVictory());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWDEMOGRAPHICSINFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showDemographics());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWNOTIFICATIONS)) != null) {
                System.out.println(gameMenuController.getInfoController().showNotification());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWMILITARYINFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showMilitary());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWECONOMICINFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showEconomy());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWDIPLOMATICINFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showDiplomotics());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWDEALS)) != null) {
                System.out.println(gameMenuController.getInfoController().showDeals());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECTMILITARYUNIT)) != null) {
                System.out.println(gameMenuController.selectMilitaryUnit(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECTCIVILIANUNIT)) != null) {
                System.out.println(gameMenuController.selectCivilianUnit(matcher));
            }else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECTCITYCOORDINATE)) != null) {
                System.out.println(gameMenuController.selectCityByPosision(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECTCITYNAME)) != null) {
                System.out.println(gameMenuController.selectCityByName(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SLEEP)) != null) {
                System.out.println(gameMenuController.sleep());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.ALERT)) != null) {
                System.out.println(gameMenuController.alert());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.FORTIFY)) != null) {
                System.out.println(gameMenuController.fortiry());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.FORTIFYHEAL)) != null) {
                System.out.println(gameMenuController.fortiryHeal());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.GARRISON)) != null) {
                System.out.println(gameMenuController.garrison());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SETUP)) != null) {
                System.out.println(gameMenuController.setUp());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.ATTACK)) != null) {
                System.out.println(gameMenuController.attack(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.FOUND)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.CANCEL)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.WAKE)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.DELETE)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDROAD)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDRAILROAD)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDFARM)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDMINE)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDTRADINGPOST)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDLUMBERMILL)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDPASTURE)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDCAMP)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDPLANTATION)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDQUARRY)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.REMOVEJUNGLE)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.REMOVEROUTE)) != null) {
                System.out.println("");
                //TODO
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWMAPCITY)) != null) {
                System.out.println("");
                //TODO
            } else
                System.out.println("invalid command");

        }
    }

    private void showMap(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        while (true) {
            System.out.println(GameDataBase.getCurrentCivilization().getMap().showMap(x, y));
            System.out.println("move map <number> to <direction>(right|left|up|down) \n back for end showing map");
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.MOVEMAP)) != null) {
                int number = Integer.parseInt(matcher.group("number"));
                String direction = matcher.group("direction");
                switch (direction) {
                    case "right":
                        y += number;
                        break;
                    case "left":
                        y -= number;
                        break;
                    case "up":
                        x -= number;
                        break;
                    case "down":
                        x += number;
                        break;
                }
            } else if (Objects.equals(input, "back"))
                break;
            else
                System.out.println("invalid command");
            input = scanner.nextLine();
        }
    }

}
