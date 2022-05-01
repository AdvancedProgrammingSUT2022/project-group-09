package com.civilization.View;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Controller.GameControllerPackage.GameMenuController;
import com.civilization.Controller.GameControllerPackage.MapController;
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
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWINFO)) != null) {
                showInfo(matcher);
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECTCITYCOORDINATE)) != null) {
                selectCity(matcher);
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECTMILITARYUNIT)) != null) {
                selectMilitaryUnit(matcher);
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECTSETTLER)) != null) {
                selectSettler(matcher);
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECTWORKER)) != null) {
                selectWorker(matcher);
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.TECHNOLOGYMENU)) != null) {
                technologyMenu(matcher);
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.NEXTTURN)) != null) {
                System.out.println(gameMenuController.nextTurn());
            } else
                System.out.println("invalid command");

        }

    }

    private void selectCity(Matcher matcher) {
        System.out.println(gameMenuController.selectCityByPosision(matcher));
        while (true) {
            input = scanner.nextLine();
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWCITYINFO)) != null) {
                System.out.println(gameMenuController.getCityController().showCityInfo());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SETCITIZEN)) != null) {
                System.out.println(gameMenuController.getCityController().setCitizen(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.MOVECITIZEN)) != null) {
                System.out.println(gameMenuController.getCityController().moveCitizen(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDMENU)) != null) {
                buildMenu(matcher);
            } else if (Objects.equals(input, "back")) {
                GameDataBase.setSelected(null);
                return;
            } else
                System.out.println("invalid command");
        }

    }

    private void buildMenu(Matcher matcher) {
        System.out.println("select a unit or building");
        System.out.println(gameMenuController.getCityController().showBuildings());
        System.out.println(gameMenuController.getCityController().showUnits());
        while (true) {
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDUNIT)) != null) {
                System.out.println(gameMenuController.getCityController().buildUnit(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDBUILDING)) != null) {
                System.out.println(gameMenuController.getCityController().buildBuilding(matcher));
            } else if (Objects.equals(input, "back")) {
                return;
            } else
                System.out.println("invalid command");
            input = scanner.nextLine();
        }
    }

    private void showInfo(Matcher matcher) {
        System.out.println(GameDataBase.getCurrentCivilization().getInformation());
        while (true) {
            input = scanner.nextLine();
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWRESEARCHINFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showResearch());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWHAPPINESS)) != null) {
                System.out.println(gameMenuController.getInfoController().showHappines());
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
            } else if (Objects.equals(input, "back")) {
                return;
            } else
                System.out.println("invalid command");
        }
    }

    private void selectSettler(Matcher matcher) {
        System.out.println(gameMenuController.selectCivilianUnit(matcher));
        if (GameDataBase.getSelected() == null)
            return;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SLEEP)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().sleep());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.MOVEUNIT)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().moveUnit(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.CANCEL)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().cancellMission());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.WAKE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().wake());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.DELETE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().delete());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.FOUND)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().foundCity());
                return;
            } else if (Objects.equals(input, "back")) {
                GameDataBase.setSelected(null);
                return;
            } else
                System.out.println("invalid command");
        }
    }

    private void selectWorker(Matcher matcher) {
        System.out.println(gameMenuController.selectCivilianUnit(matcher));
        if (GameDataBase.getSelected() == null)
            return;
        while (true) {
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SLEEP)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().sleep());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.MOVEUNIT)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().moveUnit(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.CANCEL)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().cancellMission());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.WAKE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().wake());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.DELETE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().delete());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDROAD)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildRoad());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDFARM)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildFarm());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDMINE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildMine());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDTRADINGPOST)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildTradingPost());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDLUMBERMILL)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildLumberMill());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDPASTURE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildPasture());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDCAMP)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildCamp());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDPLANTATION)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildPlantation());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILDQUARRY)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildQuarry());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.REMOVEJUNGLE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().removeJungle());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.REMOVEROUTE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().removeRoute());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.REPAIR)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().repair());
            } else if (Objects.equals(input, "back")) {
                GameDataBase.setSelected(null);
                return;
            } else
                System.out.println("invalid command");
            input = scanner.nextLine();
        }
    }

    private void selectMilitaryUnit(Matcher matcher) {
        System.out.println(gameMenuController.selectMilitaryUnit(matcher));
        if (GameDataBase.getSelected() == null)
            return;
        while (true) {
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SLEEP)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().sleep());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.CANCEL)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().cancellMission());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.WAKE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().wake());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.DELETE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().delete());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.MOVEUNIT)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().moveUnit(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.ALERT)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().alert());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.FORTIFY)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().fortiry());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.FORTIFYHEAL)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().fortiryHeal());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.GARRISON)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().garrison());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SETUP)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().setUp());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.ATTACK)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().attack(matcher));
            } else if (Objects.equals(input, "back")) {
                GameDataBase.setSelected(null);
                return;
            } else
                System.out.println("invalid command");
            input = scanner.nextLine();
        }
    }

    private void showMap(Matcher matcher) {
        GameDataBase.getCurrentCivilization().getMap().updateExploration();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        MapController mapController = new MapController(GameDataBase.getMainMap().getTerrains(),
                GameDataBase.getCurrentCivilization().getMap().getTerrainStates());
        boolean needShowMap = true;
        while (true) {
            if (needShowMap)
                System.out.println(mapController.showMap(x, y));
            System.out.println("move map <number> to <direction>(right|left|up|down) \nback for end showing map");
            input = scanner.nextLine();
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
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWDETAILS)) != null) {
                System.out.println(mapController.showDetails(matcher));
            } else
                System.out.println("invalid command");
        }
    }

    public void technologyMenu(Matcher matcher) {
        while (true) {
            input = scanner.nextLine();
            System.out.println(gameMenuController.getTechnologyMenuController().showTechnologies());
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.CHOOSETECHNOLOGY)) != null) {
                System.out.println(gameMenuController.getTechnologyMenuController().chooseTechnology(matcher));
            } else if (GameMenuRegex.getMatcher(input, GameMenuRegex.SHOWTECHNOLOGYTREE) != null) {
                System.out.println(gameMenuController.getTechnologyMenuController().technologyTree());
            } else if (Objects.equals(input, "back"))
                break;
            else
                System.out.println("invalid command");
        }
    }

}
