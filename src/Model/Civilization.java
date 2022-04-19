package Model;

import java.util.ArrayList;

public class Civilization {

    private Map map;
    private ArrayList<City> cities;
    private ArrayList<War> wars;
    private Technologies technologies;
    private Gold gold;
    private Science science;

    private Happiness happiness;

    public Civilization(Map map, ArrayList<City> cities, ArrayList<War> wars, Technologies technologies, Gold gold, Science science) {
        this.map = map;
        this.cities = cities;
        this.wars = wars;
        this.technologies = technologies;
        this.gold = gold;
        this.science = science;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public ArrayList<War> getWars() {
        return wars;
    }

    public void setWars(ArrayList<War> wars) {
        this.wars = wars;
    }

    public Technologies getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Technologies technologies) {
        this.technologies = technologies;
    }

    public Gold getGold() {
        return gold;
    }

    public void setGold(Gold gold) {
        this.gold = gold;
    }

    public Science getScience() {
        return science;
    }

    public void setScience(Science science) {
        this.science = science;
    }

    public String showInfo() {
        return "";
    }

    public Happiness getHappiness() {
        return happiness;
    }

    public void setHappiness(Happiness happiness) {
        this.happiness = happiness;
    }

    //TODO complete all information functions


}
