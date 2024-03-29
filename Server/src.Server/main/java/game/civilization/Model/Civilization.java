package game.civilization.Model;

import game.civilization.Model.Info.CivilizationGold;
import game.civilization.Model.Info.CivilizationHappiness;
import game.civilization.Model.Info.CivilizationScience;
import game.civilization.Model.Info.CivilizationTechnologies;
import game.civilization.Model.Resources.Resource;
import game.civilization.Model.Terrains.Terrain;
import game.civilization.Model.Terrains.TerrainState;
import game.civilization.Model.Units.Unit;

import java.util.ArrayList;

public class Civilization {
    private Map map;//faghat baraye showmap
    private String name;
    private ArrayList<City> cities;
    private ArrayList<War> wars;
    private CivilizationTechnologies civilizationTechnologies;
    private CivilizationGold civilizationGold;
    private CivilizationScience civilizationScience;
    private CivilizationHappiness civilizationHappiness;
    private Notification notification;

    private ArrayList<Resource> resources;
    private ArrayList<Unit> units;
    private final ArrayList<TradingObject> tradingObjects = new ArrayList<>();

    public Civilization(Map map, ArrayList<Resource> resources, ArrayList<City> cities, ArrayList<War> wars, CivilizationTechnologies civilizationTechnologies, CivilizationGold civilizationGold, CivilizationScience civilizationScience, CivilizationHappiness civilizationHappiness, ArrayList<Unit> units) {
        this.map = map;
        this.cities = cities;
        this.wars = wars;
        this.civilizationTechnologies = civilizationTechnologies;
        this.civilizationGold = civilizationGold;
        this.civilizationScience = civilizationScience;
        this.civilizationHappiness = civilizationHappiness;
        this.resources = resources;
        this.units = units;
        this.notification = new Notification();
    }

    public Civilization(String name) {
        this.name = name;
        this.map = new Map();
        this.cities = new ArrayList<>();
        this.wars = new ArrayList<>();
        this.civilizationTechnologies = new CivilizationTechnologies();
        this.civilizationGold = new CivilizationGold();
        this.civilizationScience = new CivilizationScience();
        this.civilizationHappiness = new CivilizationHappiness();
        this.resources = new ArrayList<>();
        this.units = new ArrayList<>();
        civilizationGold.setCurrentGold(50);
        this.notification = new Notification();
    }

    public ArrayList<City> getCities() {
        return this.cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public void addCity(City city) {
        this.cities.add(city);
    }

    public ArrayList<War> getWars() {
        return this.wars;
    }

    public void setWars(ArrayList<War> wars) {
        this.wars = wars;
    }

    public CivilizationTechnologies getTechnologies() {
        return this.civilizationTechnologies;
    }

    public void setTechnologies(CivilizationTechnologies civilizationTechnologies) {
        this.civilizationTechnologies = civilizationTechnologies;
    }

    public CivilizationGold getGold() {
        return this.civilizationGold;
    }

    public void setGold(CivilizationGold cityGold) {
        this.civilizationGold = cityGold;
    }

    public CivilizationScience getScience() {
        return civilizationScience;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public void setScience(CivilizationScience cityScience) {
        this.civilizationScience = cityScience;
    }

    public CivilizationHappiness getHappiness() {
        return this.civilizationHappiness;
    }

    public void setHappiness(CivilizationHappiness civilizationHappiness) {
        this.civilizationHappiness = civilizationHappiness;
    }

    public Map getMap() {
        return this.map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotification() {
        return this.notification.getNotification();
    }

    public void updateNotification(String input) {
        this.notification.updateNotification(input);
    }

    public void resetNotification() {
        this.notification.resetNotifiction();
    }

    public String getName() {
        return this.name;
    }

    public String getInformation() {
        return "name : " + name + "\n" + "Gold : " + getGold().getCurrentGold() + " addition gold :" + getGold().getAdditionGold() +
                "\n" + "happines : " + getHappiness().getAdditionHappiness() + " science : " + getScience().getAdditionScience();
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
    }

    public void removeUnit(Unit unit) {
        this.units.remove(unit);
    }

    public TerrainState getTerrainState(int x, int y) {
        return this.map.getTerrainStates()[x][y];
    }

    public String getDemographics() {
        StringBuilder res = new StringBuilder();
        for (City city : cities) {
            res.append("city ").append(city.getName()).append("demographic:")
                    .append(city.getDemographics()).append("\n");
        }
        return res.toString();
    }

    public void nextTurn() {
        this.notification.resetNotifiction();
        updateData();
        getHappiness().nexTurn();
        getTechnologies().nextTurn();
        getGold().addCurrentGold(getGold().getAdditionGold());
        if (getGold().getAdditionGold() < 0)  //age manfi shod az scien`ce kam one
        {
            getScience().add(getGold().getAdditionGold());
            getGold().setCurrentGold(0);
        }
    }

    public void updateData() {
        for (City city : getCities()) {
            city.update();
        }
        updateResource();
        getGold().update();
        getScience().update();
        getHappiness().update();
    }

    private void updateResource() {
        resources = new ArrayList<>();
        for (City city : getCities()) {
            for (Terrain terrain : city.getTerrains()) {
                for (Resource resource : terrain.getResources()) {
                    if (getTechnologies().getTechnologiesResearched().contains(resource.getRequiredTechnology())
                            && terrain.getImprovement() == resource.getRequiredImprovement())
                        resources.add(resource);
                }
            }
        }
    }

    public int getScore() {
        int res = 0;
        res += cities.size() * 50;
        for (City city : cities) {
            res += city.getTerrains().size() * 10;
        }
        res += units.size() * 20;
        res += civilizationTechnologies.getTechnologiesResearched().size() * 40;
        res += civilizationGold.getCurrentGold();
        return res;
    }


    public ArrayList<TradingObject> getTradingObjects() {
        return tradingObjects;
    }
}
