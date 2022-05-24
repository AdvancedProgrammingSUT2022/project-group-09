package civilization.civilization.main.java.com.civilization.View;

import civilization.civilization.main.java.com.civilization.Controller.GameControllerPackage.GameDataBase;
import civilization.civilization.main.java.com.civilization.Controller.GameControllerPackage.GameMenuController;
import civilization.civilization.main.java.com.civilization.Controller.GameControllerPackage.MapController;
import civilization.civilization.main.java.com.civilization.MenuRegex.GameMenuRegex;

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
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_CURRENT_MENU)) != null)
                System.out.println(CurrentMenu.get());
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_MAP)) != null)
                showMap(matcher);
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.ENTER)) != null) {
                System.out.println(gameMenuController.menuNavigate(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_INFO)) != null) {
                showInfo(matcher);
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECT_CITY_COORDINATE)) != null) {
                selectCity(matcher);
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECT_MILITARY_UNIT)) != null) {
                selectMilitaryUnit(matcher);
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECT_SETTLER)) != null) {
                selectSettler(matcher);
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SELECT_WORKER)) != null) {
                selectWorker(matcher);
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.TECHNOLOGY_MENU)) != null) {
                technologyMenu(matcher);
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.ENTER_CHEAT_MENU)) != null) {
                cheat();
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.NEXT_TURN)) != null) {
                System.out.println(gameMenuController.nextTurn());
            } else
                System.out.println("invalid command");

        }

    }

    private void cheat() {
        System.out.println("entered cheat menu");
        while (true) {
            input = scanner.nextLine();
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.INCREASE_GOLD)) != null) {
                System.out.println(gameMenuController.getCheatConteroller().increaseGold(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SET_ARCHER)) != null) {
                System.out.println(gameMenuController.getCheatConteroller().setArcher(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SET_TANK)) != null) {
                System.out.println(gameMenuController.getCheatConteroller().setTank(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SET_SETTLER)) != null) {
                System.out.println(gameMenuController.getCheatConteroller().setSettler(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SET_LANCER)) != null) {
                System.out.println(gameMenuController.getCheatConteroller().setLancer(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SET_ARTILLERY)) != null) {
                System.out.println(gameMenuController.getCheatConteroller().setArtillery(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SET_CANNON)) != null) {
                System.out.println(gameMenuController.getCheatConteroller().setCannon(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.INCREASE_HAPPINESS)) != null) {
                System.out.println(gameMenuController.getCheatConteroller().increaseHappiness(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.INCREASE_SCIENCE)) != null) {
                System.out.println(gameMenuController.getCheatConteroller().increaseScience(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.INCREASE_TURN)) != null) {
                System.out.println(gameMenuController.getCheatConteroller().increaseTurn(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.UNIT_RESET)) != null) {
                System.out.println(gameMenuController.getCheatConteroller().resetUnit());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.OPEN_ALL_TECHNOLOGIES)) != null) {
                System.out.println(gameMenuController.getCheatConteroller().openTechnologies());
            } else if (Objects.equals(input, "back")) {
                return;
            } else
                System.out.println("invalid command");
        }
    }

    private void selectCity(Matcher matcher) {
        System.out.println(gameMenuController.selectCityByPosition(matcher));
        if (gameMenuController.selectCityIsValid(matcher))
            while (true) {
                input = scanner.nextLine();
                if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_CITY_INFO)) != null) {
                    System.out.println(gameMenuController.getCityController().showCityInfo());
                } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SET_CITIZEN)) != null) {
                    System.out.println(gameMenuController.getCityController().setCitizen(matcher));
                } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.MOVE_CITIZEN)) != null) {
                    System.out.println(gameMenuController.getCityController().moveCitizen(matcher));
                } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.REMOVE_CITIZEN)) != null) {
                    System.out.println(gameMenuController.getCityController().removeCitizen(matcher));
                } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILD_MENU)) != null) {
                    buildMenu(matcher);
                } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUY_TILE)) != null) {
                    System.out.println(gameMenuController.getCityController().buyTerrain(matcher));
                } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.ATTACK)) != null) {
                    System.out.println(gameMenuController.getCombatController().cityAttack(matcher));
                } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.DELETE_CITY)) != null) {
                    {
                        System.out.println(gameMenuController.getCityController().delete());
                        return;
                    }
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
            input = scanner.nextLine();
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILD_UNIT)) != null) {
                System.out.println(gameMenuController.getCityController().buildUnit(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILD_BUILDING)) != null) {
                System.out.println(gameMenuController.getCityController().buildBuilding(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILD_UNIT_GOLD)) != null) {
                System.out.println(gameMenuController.getCityController().buildUnitWithGold(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILD_BUILDING_GOLD)) != null) {
                System.out.println(gameMenuController.getCityController().buildBuildingWithGold(matcher));
            } else if (Objects.equals(input, "back")) {
                return;
            } else
                System.out.println("invalid command");
        }
    }

    private void showInfo(Matcher matcher) {
        GameDataBase.getCurrentCivilization().updateData();
        System.out.println(GameDataBase.getCurrentCivilization().getInformation());
        while (true) {
            input = scanner.nextLine();
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_RESEARCH_INFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showResearch());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_UNITS)) != null) {
                System.out.println(gameMenuController.getInfoController().showUnits());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_CITIES)) != null) {
                System.out.println(gameMenuController.getInfoController().showCities());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_DIPLOMACY_INFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showDiplomacy());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_VICTORY_INFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showVictory());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_DEMOGRAPHICS_INFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showDemographics());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_NOTIFICATIONS)) != null) {
                System.out.println(gameMenuController.getInfoController().showNotification());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.RESET_NOTIFICATIONS)) != null) {
                System.out.println(gameMenuController.getInfoController().resetNotification());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_MILITARY_INFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showMilitary());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_ECONOMIC_INFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showEconomy());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_DIPLOMATIC_INFORMATION)) != null) {
                System.out.println(gameMenuController.getInfoController().showDiplomatics());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_DEALS)) != null) {
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
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.MOVE_UNIT)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().moveUnit(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.DO_NOTHING)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().doNothing());
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
            input = scanner.nextLine();
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SLEEP)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().sleep());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_INFO)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().showWorkerInfo());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.MOVE_UNIT)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().moveUnit(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.DO_NOTHING)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().doNothing());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.WAKE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().wake());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.DELETE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().delete());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILD_ROAD)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildRoad());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILD_FARM)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildFarm());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILD_MINE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildMine());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILD_TRADING_POST)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildTradingPost());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILD_LUMBER_MILL)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildLumberMill());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILD_PASTURE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildPasture());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILD_CAMP)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildCamp());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILD_PLANTATION)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildPlantation());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.BUILD_QUARRY)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().buildQuarry());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.REMOVE_JUNGLE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().removeJungle());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.REMOVE_ROUTE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().removeRoute());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.REMOVE_MARSH)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().removeMarsh());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.REMOVE_FOREST)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().removeForest());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.REPAIR)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().repair());
            } else if (Objects.equals(input, "back")) {
                GameDataBase.setSelected(null);
                return;
            } else
                System.out.println("invalid command");
        }
    }

    private void selectMilitaryUnit(Matcher matcher) {
        System.out.println(gameMenuController.selectMilitaryUnit(matcher));
        if (GameDataBase.getSelected() == null)
            return;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SLEEP)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().sleep());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.DO_NOTHING)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().doNothing());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.WAKE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().wake());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.DELETE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().delete());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.MOVE_UNIT)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().moveUnit(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.ALERT)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().alert());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.FORTIFY)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().fortify());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.FORTIFY_UNTIL_HEAL)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().fortifyHeal());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.GARRISON)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().garrison());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SETUP)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().setUp());
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.ATTACK)) != null) {
                System.out.println(gameMenuController.getCombatController().militaryAttack(matcher));
            } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.PILLAGE)) != null) {
                System.out.println(gameMenuController.getUnitcontroller().pillage());
            } else if (Objects.equals(input, "back")) {
                GameDataBase.setSelected(null);
                return;
            } else
                System.out.println("invalid command");
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
            needShowMap = true;
            System.out.println("move map <number> to <direction>(right|left|up|down) \nback for end showing map");
            input = scanner.nextLine();
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.MOVE_MAP)) != null) {
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
            else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_DETAILS)) != null) {
                System.out.println(mapController.showDetails(matcher));
                needShowMap = false;
            } else
                System.out.println("invalid command");
        }
    }

    public void technologyMenu(Matcher matcher) {
        System.out.println("entered technology menu");
        System.out.println(gameMenuController.getTechnologyMenuController().showTechnologies());
        while (true) {
            input = scanner.nextLine();
            if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.CHOOSE_TECHNOLOGY)) != null) {
                System.out.println(gameMenuController.getTechnologyMenuController().chooseTechnology(matcher));
            } else if (GameMenuRegex.getMatcher(input, GameMenuRegex.SHOW_TECHNOLOGY_TREE) != null) {
                System.out.println(gameMenuController.getTechnologyMenuController().technologyTree());
            } else if (Objects.equals(input, "back"))
                break;
            else
                System.out.println("invalid command");
        }
    }

}
