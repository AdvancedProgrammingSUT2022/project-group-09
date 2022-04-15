package Model;

import Model.Terrains.CitizenCanWork;
import Model.Terrains.Terrain;

public class Citizen {
    private Terrain terrain;
    private City city;
    private CitizenCanWork workOn;

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public CitizenCanWork getWorkOn() {
        return workOn;
    }

    public void setWorkOn(CitizenCanWork workOn) {
        this.workOn = workOn;
    }

    public Citizen(Terrain terrain, City city, CitizenCanWork workOn) {
        this.terrain = terrain;
        this.city = city;
        this.workOn = workOn;
    }
}
