package com.civilization.Model.Terrains;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.City;
import com.civilization.Model.Civilization;
import com.civilization.Model.Coordination;
import com.civilization.Model.Improvements.Improvement;
import com.civilization.Model.Resources.Resource;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.Units.MilitaryUnit;
import com.civilization.Model.Units.Unit;

import java.util.ArrayList;

public class Terrain {
    private Improvement improvement;
    private boolean hasRoad;
    private TerrainType type;
    private ArrayList<TerrainFeature> terrainFeatures;
    private ArrayList<Resource> resources;
    private Unit civilianUnit;
    private MilitaryUnit militaryUnit;


    public Terrain() {
        this.improvement = null;
        this.hasRoad = false;
        this.type = null;
        this.terrainFeatures = new ArrayList<>();
        this.resources = new ArrayList<>();
        this.civilianUnit = null;
        this.militaryUnit = null;
    }

    public Terrain(Terrain terrain) {
        this.improvement = terrain.getImprovement();
        this.hasRoad = terrain.isHasRoad();
        this.type = terrain.getType();
        this.terrainFeatures = terrain.getTerrainFeatures();
        this.resources = terrain.getResources();
        this.civilianUnit = terrain.getCivilianUnit();
        this.militaryUnit = terrain.getMilitaryUnit();
    }

    public Terrain(TerrainType type) {
        this.type = type;
        this.improvement = null;
        this.hasRoad = false;
        this.terrainFeatures = new ArrayList<>();
        this.resources = new ArrayList<>();
        this.civilianUnit = null;
        this.militaryUnit = null;
    }

    public Improvement getImprovement() {
        return improvement;
    }

    public void setImprovement(Improvement improvement) {
        this.improvement = improvement;
    }

    public boolean isHasRoad() {
        return hasRoad;
    }

    public void setHasRoad(boolean hasRoad) {
        this.hasRoad = hasRoad;
    }

    public TerrainType getType() {
        return type;
    }

    public void setType(TerrainType type) {
        this.type = type;
    }

    public ArrayList<TerrainFeature> getTerrainFeatures() {
        return terrainFeatures;
    }

    public void setTerrainFeatures(ArrayList<TerrainFeature> terrainFeatures) {
        this.terrainFeatures = terrainFeatures;
    }

    public void addTerrainFeature(TerrainFeature terrainFeature) {
        this.terrainFeatures.add(terrainFeature);
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public void addResource(Resource resource) {
        this.resources.add(resource);
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


    public City getCity() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            for (City city : civilization.getCities()) {
                if (city.getTerrains().contains(this))
                    return city;
            }
        }
        return null;
    }

    public void SetCity(City city) {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            for (City city1 : civilization.getCities()) {
                city1.getTerrains().remove(this);
            }
        }
        city.addTerrain(this);
    }


    public Civilization getCivilization() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            for (City city : civilization.getCities()) {
                if (city.getTerrains().contains(this))
                    return civilization;
            }
        }
        return null;
    }

    public void setCivilization(Civilization civilization) {
        for (Civilization civilization1 : GameDataBase.getCivilizations()) {
            for (City city : civilization1.getCities()) {
                city.getTerrains().remove(this);
            }
        }
        for (Civilization civilization1 : GameDataBase.getCivilizations()) {
            for (City city : civilization1.getCities()) {
                if (city.isCapital()) {
                    city.addTerrain(this);
                    return;
                }
            }
        }
        System.err.println("ERROR! setCivilization-Terrain(civilization ma paytakht nadare!)");
        throw new RuntimeException();

    }

    public ArrayList<Terrain> getSurroundingTerrain() {
        ArrayList<Terrain> terrains = new ArrayList<>();
        int x, y;
        if (GameDataBase.getMainMap().getYpositionTerrain(this) % 2 == 0) {
            x = GameDataBase.getMainMap().getXpositionTerrain(this) + 1;
            y = GameDataBase.getMainMap().getYpositionTerrain(this);
            if (GameDataBase.getMainMap().isValidTerran(x, y))
                terrains.add(GameDataBase.getMainMap().getTerrain(x, y));

            x = GameDataBase.getMainMap().getXpositionTerrain(this) - 1;
            y = GameDataBase.getMainMap().getYpositionTerrain(this);
            if (GameDataBase.getMainMap().isValidTerran(x, y))
                terrains.add(GameDataBase.getMainMap().getTerrain(x, y));

            x = GameDataBase.getMainMap().getXpositionTerrain(this) - 1;
            y = GameDataBase.getMainMap().getYpositionTerrain(this) - 1;
            if (GameDataBase.getMainMap().isValidTerran(x, y))
                terrains.add(GameDataBase.getMainMap().getTerrain(x, y));

            x = GameDataBase.getMainMap().getXpositionTerrain(this) - 1;
            y = GameDataBase.getMainMap().getYpositionTerrain(this) + 1;
            if (GameDataBase.getMainMap().isValidTerran(x, y))
                terrains.add(GameDataBase.getMainMap().getTerrain(x, y));

            x = GameDataBase.getMainMap().getXpositionTerrain(this);
            y = GameDataBase.getMainMap().getYpositionTerrain(this) - 1;
            if (GameDataBase.getMainMap().isValidTerran(x, y))
                terrains.add(GameDataBase.getMainMap().getTerrain(x, y));

            x = GameDataBase.getMainMap().getXpositionTerrain(this);
            y = GameDataBase.getMainMap().getYpositionTerrain(this) + 1;
            if (GameDataBase.getMainMap().isValidTerran(x, y))
                terrains.add(GameDataBase.getMainMap().getTerrain(x, y));
        } else {
            x = GameDataBase.getMainMap().getXpositionTerrain(this) + 1;
            y = GameDataBase.getMainMap().getYpositionTerrain(this);
            if (GameDataBase.getMainMap().isValidTerran(x, y))
                terrains.add(GameDataBase.getMainMap().getTerrain(x, y));

            x = GameDataBase.getMainMap().getXpositionTerrain(this) - 1;
            y = GameDataBase.getMainMap().getYpositionTerrain(this);
            if (GameDataBase.getMainMap().isValidTerran(x, y))
                terrains.add(GameDataBase.getMainMap().getTerrain(x, y));

            x = GameDataBase.getMainMap().getXpositionTerrain(this) + 1;
            y = GameDataBase.getMainMap().getYpositionTerrain(this) - 1;
            if (GameDataBase.getMainMap().isValidTerran(x, y))
                terrains.add(GameDataBase.getMainMap().getTerrain(x, y));

            x = GameDataBase.getMainMap().getXpositionTerrain(this) + 1;
            y = GameDataBase.getMainMap().getYpositionTerrain(this) + 1;
            if (GameDataBase.getMainMap().isValidTerran(x, y))
                terrains.add(GameDataBase.getMainMap().getTerrain(x, y));

            x = GameDataBase.getMainMap().getXpositionTerrain(this);
            y = GameDataBase.getMainMap().getYpositionTerrain(this) - 1;
            if (GameDataBase.getMainMap().isValidTerran(x, y))
                terrains.add(GameDataBase.getMainMap().getTerrain(x, y));

            x = GameDataBase.getMainMap().getXpositionTerrain(this);
            y = GameDataBase.getMainMap().getYpositionTerrain(this) + 1;
            if (GameDataBase.getMainMap().isValidTerran(x, y))
                terrains.add(GameDataBase.getMainMap().getTerrain(x, y));
        }
        return terrains;
    }

    public int getXPosition() {
        return GameDataBase.getMainMap().getXpositionTerrain(this);
    }

    public int getYPosition() {
        return GameDataBase.getMainMap().getYpositionTerrain(this);
    }

    public Coordination getCoordination() {
        return new Coordination(getXPosition(), getYPosition());
    }

    public String getDetails() {
        return "this Terrain belongs to: " + this.getCivilization().getName() + "\n" +
                "Terrain Type is: " + this.type + "\n" +
                "Terrain Features are: " + this.terrainFeatures + "\n" +
                "Terrain Resources are: " + this.resources + "\n";
    }

    public int getMp() {
        int sumOfMps = type.getMP();
        for (TerrainFeature terrainFeature : terrainFeatures) {
            sumOfMps += terrainFeature.getMP();
        }
        return sumOfMps;
    }
}
