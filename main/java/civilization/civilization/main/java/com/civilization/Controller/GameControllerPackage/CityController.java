package civilization.civilization.main.java.com.civilization.Controller.GameControllerPackage;

import civilization.civilization.main.java.com.civilization.Model.Buildings.BuildingType;
import civilization.civilization.main.java.com.civilization.Model.City;
import civilization.civilization.main.java.com.civilization.Model.Coordination;
import civilization.civilization.main.java.com.civilization.Model.Terrains.Terrain;
import civilization.civilization.main.java.com.civilization.Model.Units.UnitType;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class CityController {

    public String delete() {
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        city.deleteCity();
        return "city deleted";
    }

    public String buyTerrain(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordination = new Coordination(x, y);
        if (!coordination.isValidCoordination())
            return "coordination is invalid";
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        for (Terrain terrain : city.getTerrains()) {
            for (Terrain terrain1 : terrain.getSurroundingTerrain()) {
                if (terrain1 == coordination.getTerrain()) {
                    if (terrain1.getCivilization() == null) {
                        GameDataBase.getCurrentCivilization().getGold().addCurrentGold(-10);
                        /// gheimat tile = 10;
                        city.addTerrain(terrain1);
                        GameDataBase.getCurrentCivilization()
                                .updateNotification("bought a terrain on " + terrain1.getCoordination().toString());
                        return "kharide shod";
                    } else
                        return "in terrain sahab dare";
                }
            }
        }
        return "in terrain dar mahdoode kharid shoma nist va ddoore";
    }

    public String showCityInfo() {
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        city.update();
        return city.getDetails() + city.getDemographics() + "\n" + city.showMakingUnit();
    }

    public String setCitizen(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordination = new Coordination(x, y);
        if (!coordination.isValidCoordination())
            return "coordination is invalid";
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        if (!city.getTerrains().contains(coordination.getTerrain()))
            return "in tile male shoma nist";

        for (int i = 0; i < city.getCitizens().size(); i++) {
            if (city.getCitizens().get(i) == null) {
                city.getCitizens().set(i, coordination.getTerrain());
                city.update();
                GameDataBase.getCurrentCivilization().updateNotification("set a citizen on " + coordination.toString());
                return "anjam shod";
            }
        }
        return "hame citizen ha mashghool kar hastand";
    }

    public String removeCitizen(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordination = new Coordination(x, y);
        if (!coordination.isValidCoordination())
            return "coordination is invalid";
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        if (!city.getTerrains().contains(coordination.getTerrain()))
            return "in tile male shoma nist";
        for (int i = 0; i < city.getCitizens().size(); i++) {
            if (city.getCitizens().get(i).getCoordination().equal(coordination))
                if (city.getCitizens().get(i) == null)
                    return "in bikar bood az ghabl";
                else {
                    city.getCitizens().set(i, null);
                    GameDataBase.getCurrentCivilization()
                            .updateNotification("citizen on " + coordination.toString() + " is now unemployed");
                    return "in citizen bikar shod";
                }
        }
        return "hich citizeni inja aslan kar nemikone";
    }

    public String moveCitizen(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        int xx = Integer.parseInt(matcher.group("xx"));
        int yy = Integer.parseInt(matcher.group("yy"));
        Coordination first = new Coordination(x, y);
        Coordination second = new Coordination(xx, yy);
        if (!first.isValidCoordination())
            return "coordination is invalid";
        if (!second.isValidCoordination())
            return "coordination is invalid";
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        if (!city.getTerrains().contains(first.getTerrain()))
            return "in tile male shoma nist";
        if (!city.getTerrains().contains(second.getTerrain()))
            return "in tile male shoma nist";

        for (int i = 0; i < city.getCitizens().size(); i++) {
            if (city.getCitizens().get(i) == first.getTerrain()) {
                city.getCitizens().set(i, second.getTerrain());
                city.update();
                return "move shod";
            }
        }
        return "citizeni dar mabda dar hale kar nist";
    }

    public String showBuildings() {
        StringBuilder buildingString = new StringBuilder();
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        int i = 0;
        buildingString.append("*BUILDINGS*\n");
        for (BuildingType building : city.buildingsCanBeBuilt()) {
            i++;
            buildingString.append(i).append(" ").append(building.toString().toLowerCase()).append(" turns: ")
                    .append((int) ((building.getCost() - 1) / city.getProduction().getCurrentProduct()) + 1)
                    .append(" maintenance: ").append(building.getMaintenance()).append("\n");
            // TODO turn ok nist
        }
        return String.valueOf(buildingString);
    }

    public String showUnits() {
        StringBuilder unitString = new StringBuilder();
        City city = (City) GameDataBase.getSelected();
        if (city == null)
            return "city select nashode";
        int i = 0;
        unitString.append("*UINTS*\n");
        ArrayList<UnitType> unitTypes = city.unitsCanBeBuilt();
        for (UnitType unit : unitTypes) {
            i++;
            unitString.append(i).append(" ").append(unit.toString().toLowerCase()).append(" turns: ")
                    .append((int) ((unit.getCost() - 1) / city.getProduction().getCurrentProduct()) + 1)
                    .append(" required resource: ").append(unit.getRequiredResourse())
                    .append(" required technology: ").append(unit.getRequiredTechnology()).append(" combat strength: ")
                    .append(unit.getCombatStrengh()).append(" combat type: ")
                    .append(unit.getCombatType().toString().toLowerCase()).append("\n");
        }
        return String.valueOf(unitString);
    }

    public String buildBuilding(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        City city = (City) GameDataBase.getSelected();
        if (city == null) {
            return "city select nashode";
        }
        ArrayList<BuildingType> buildings = city.buildingsCanBeBuilt();
        if (number > buildings.size() || number < 1) {
            return "invalid number";
        }
        if (!city.getCivilization().equals(GameDataBase.getCurrentCivilization())) {
            return "in tile male shoma nist";
        }
        city.createBuilding(buildings.get(number - 1));
        GameDataBase.getCurrentCivilization().updateNotification(
                "built a building " + buildings.get(number - 1).name() + " in city " + city.getName());
        return "building created successfully!";
    }

    public String buildBuildingWithGold(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        City city = (City) GameDataBase.getSelected();
        if (city == null) {
            return "city select nashode";
        }
        ArrayList<BuildingType> buildings = city.buildingsCanBeBuilt();
        if (number > buildings.size() || number < 1) {
            return "invalid number";
        }
        if (!city.getCivilization().equals(GameDataBase.getCurrentCivilization())) {
            return "in tile male shoma nist";
        }
        if (buildings.get(number - 1).getCost() > GameDataBase.getCurrentCivilization().getGold().getCurrentGold()) {
            return "You don't have enough money to buy this unit";
        }
        city.createBuildingInstantly(buildings.get(number - 1));
        GameDataBase.getCurrentCivilization().updateNotification(
                "built a building " + buildings.get(number - 1).name() + " in city " + city.getName());
        return "building created successfully!";
    }

    public String buildUnit(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        City city = (City) GameDataBase.getSelected();
        if (city == null) {
            return "city select nashode";
        }
        ArrayList<UnitType> units = city.unitsCanBeBuilt();
        if (number > units.size() || number < 1) {
            return "invalid number";
        }
        if (!city.getCivilization().equals(GameDataBase.getCurrentCivilization())) {
            return "in tile male shoma nist";
        }
        if (units.get(number - 1).equals(UnitType.SETTLER)
                && ((City) GameDataBase.getSelected()).getCitizens().size() < 2) {
            return "number of citizens is not enough!";
        }
        city.CreateUnit(units.get(number - 1));
        GameDataBase.getCurrentCivilization().updateNotification(
                "built a unit " + units.get(number - 1).name() + " in city " + city.getName());
        return "unit created successfully!";
    }

    public String buildUnitWithGold(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("number"));
        City city = (City) GameDataBase.getSelected();
        if (city == null) {
            return "city select nashode";
        }
        ArrayList<UnitType> units = city.unitsCanBeBuilt();
        if (number > units.size() || number < 1) {
            return "invalid number";
        }
        if (!city.getCivilization().equals(GameDataBase.getCurrentCivilization())) {
            return "in tile male shoma nist";
        }
        if (units.get(number - 1).getCost() > GameDataBase.getCurrentCivilization().getGold().getCurrentGold()) {
            return "You don't have enough money to buy this unit";
        }
        if (units.get(number - 1).equals(UnitType.SETTLER)
                && ((City) GameDataBase.getSelected()).getCitizens().size() < 2) {
            return "number of citizens is not enough!";
        }
        city.createUnitInstantly(units.get(number - 1));
        GameDataBase.getCurrentCivilization().updateNotification(
                "built a unit " + units.get(number - 1).name() + " in city " + city.getName());
        return "unit created successfully!";
    }

}
