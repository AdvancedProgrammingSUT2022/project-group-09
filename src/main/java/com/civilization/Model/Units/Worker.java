package com.civilization.Model.Units;

import com.civilization.Model.Civilization;
import com.civilization.Model.Improvements.Improvement;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.Terrains.Terrain;

import java.util.HashMap;

public class Worker extends Unit {
    private HashMap<Improvement, Integer> makingImprovement;

    public Worker(Terrain terrain, Civilization civilization) {
        super(UnitType.WORKER, terrain, civilization);
        this.makingImprovement = new HashMap<>();
    }

    public void makeImprovement(Improvement improvement) {
        if (makingImprovement.size() != 0) {
            System.err.println(makingImprovement.size() + " ta kar dare sakhte mishe ke ghalate");
            throw new RuntimeException();
        }
        makingImprovement.put(improvement, 2);
        //TODO har improvement chand turn mikhad ro nagofte
    }

    public void removeJungle() {
        getTerrain().getTerrainFeatures().remove(TerrainFeature.JUNGLE);
    }

    public void removeRoute() {
        getTerrain().setHasRoad(false);
    }

    public void repair() {
        //
    }

    public HashMap<Improvement, Integer> getMakingImprovement() {
        return makingImprovement;
    }

    public void setMakingImprovement(HashMap<Improvement, Integer> makingImprovement) {
        this.makingImprovement = makingImprovement;
    }
}
