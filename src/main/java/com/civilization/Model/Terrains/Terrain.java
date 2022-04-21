package com.civilization.Model.Terrains;

import com.civilization.Model.Buildings.Building;
import com.civilization.Model.Citizen;
import com.civilization.Model.City;
import com.civilization.Model.Civilization;
import com.civilization.Model.Resources.Resource;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.TerrainFeatures.TerrainFeatureType;
import com.civilization.Model.Units.MilitaryUnit;
import com.civilization.Model.Units.Unit;

import java.util.ArrayList;

public class Terrain implements CitizenCanWork {

    private TerrainType type;
    private TerrainState state;
    //private ArrayList<TerrainFeature> terrainFeatures;
    //in ezafi bood zadam pak she

    private ArrayList<TerrainFeatureType> terrainFeatures;
    private ArrayList<Resource> resources;
    private Building building;

    private Unit civilianUnit;

    private Civilization civilization;

    private City city;

    private ArrayList<Citizen> citizens;
    private MilitaryUnit militaryUnit;

    public ArrayList<Citizen> getCitizens() {
        return citizens;
    }

    public void setCitizens(ArrayList<Citizen> citizens) {
        this.citizens = citizens;
    }


    public Unit getCivilianUnit() {
        return civilianUnit;
    }

    public void setCivilianUnit(Unit civilianUnit) {
        this.civilianUnit = civilianUnit;
    }

    public MilitaryUnit getMilitaryUnit() {
        return militaryUnit;
    }

    public void setMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    public TerrainType getType() {
        return type;
    }

    public void setType(TerrainType type) {
        this.type = type;
    }

    public TerrainState getState() {
        return state;
    }

    public void setState(TerrainState state) {
        this.state = state;
    }


    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Terrain(TerrainType type, TerrainState state, ArrayList<TerrainFeatureType> terrainFeatures, ArrayList<Resource> resources, Building building, Unit civilianUnit, Civilization civilization, ArrayList<Citizen> citizens, MilitaryUnit militaryUnit, City city) {
        this.type = type;
        this.state = state;
        this.terrainFeatures = terrainFeatures;
        this.resources = resources;
        this.building = building;
        this.civilianUnit = civilianUnit;
        this.civilization = civilization;
        this.citizens = citizens;
        this.militaryUnit = militaryUnit;
        this.city = city;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ArrayList<Terrain> getSurroundingTerrain() {
        ArrayList<Terrain> terrains = new ArrayList<>();
        int x, y;
        if (getCivilization().getMap().getYpositionTerrain(this) % 2 == 0) {
            x = getCivilization().getMap().getXpositionTerrain(this) + 1;
            y = getCivilization().getMap().getXpositionTerrain(this);
            if (getCivilization().getMap().isValidTerran(x, y))
                terrains.add(getCivilization().getMap().getTerrain(x, y));

            x = getCivilization().getMap().getXpositionTerrain(this) - 1;
            y = getCivilization().getMap().getXpositionTerrain(this);
            if (getCivilization().getMap().isValidTerran(x, y))
                terrains.add(getCivilization().getMap().getTerrain(x, y));

            x = getCivilization().getMap().getXpositionTerrain(this) - 1;
            y = getCivilization().getMap().getXpositionTerrain(this) - 1;
            if (getCivilization().getMap().isValidTerran(x, y))
                terrains.add(getCivilization().getMap().getTerrain(x, y));

            x = getCivilization().getMap().getXpositionTerrain(this) - 1;
            y = getCivilization().getMap().getXpositionTerrain(this) + 1;
            if (getCivilization().getMap().isValidTerran(x, y))
                terrains.add(getCivilization().getMap().getTerrain(x, y));

            x = getCivilization().getMap().getXpositionTerrain(this);
            y = getCivilization().getMap().getXpositionTerrain(this) - 1;
            if (getCivilization().getMap().isValidTerran(x, y))
                terrains.add(getCivilization().getMap().getTerrain(x, y));

            x = getCivilization().getMap().getXpositionTerrain(this);
            y = getCivilization().getMap().getXpositionTerrain(this) + 1;
            if (getCivilization().getMap().isValidTerran(x, y))
                terrains.add(getCivilization().getMap().getTerrain(x, y));
        } else {
            x = getCivilization().getMap().getXpositionTerrain(this) + 1;
            y = getCivilization().getMap().getXpositionTerrain(this);
            if (getCivilization().getMap().isValidTerran(x, y))
                terrains.add(getCivilization().getMap().getTerrain(x, y));

            x = getCivilization().getMap().getXpositionTerrain(this) - 1;
            y = getCivilization().getMap().getXpositionTerrain(this);
            if (getCivilization().getMap().isValidTerran(x, y))
                terrains.add(getCivilization().getMap().getTerrain(x, y));

            x = getCivilization().getMap().getXpositionTerrain(this) + 1;
            y = getCivilization().getMap().getXpositionTerrain(this) - 1;
            if (getCivilization().getMap().isValidTerran(x, y))
                terrains.add(getCivilization().getMap().getTerrain(x, y));

            x = getCivilization().getMap().getXpositionTerrain(this) + 1;
            y = getCivilization().getMap().getXpositionTerrain(this) + 1;
            if (getCivilization().getMap().isValidTerran(x, y))
                terrains.add(getCivilization().getMap().getTerrain(x, y));

            x = getCivilization().getMap().getXpositionTerrain(this);
            y = getCivilization().getMap().getXpositionTerrain(this) - 1;
            if (getCivilization().getMap().isValidTerran(x, y))
                terrains.add(getCivilization().getMap().getTerrain(x, y));

            x = getCivilization().getMap().getXpositionTerrain(this);
            y = getCivilization().getMap().getXpositionTerrain(this) + 1;
            if (getCivilization().getMap().isValidTerran(x, y))
                terrains.add(getCivilization().getMap().getTerrain(x, y));
        }
        return terrains;
    }

    public ArrayList<TerrainFeatureType> getTerrainFeatures() {
        return terrainFeatures;
    }

    public void setTerrainFeatures(ArrayList<TerrainFeatureType> terrainFeatures) {
        this.terrainFeatures = terrainFeatures;
    }

    public int getXpositionTerrain() {
        return getCivilization().getMap().getXpositionTerrain(this);
    }

    public int getYpositionTerrain() {
        return getCivilization().getMap().getYpositionTerrain(this);
    }
}
