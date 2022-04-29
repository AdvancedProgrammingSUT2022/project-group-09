package com.civilization.Model.Units;

import com.civilization.Model.Civilization;
import com.civilization.Model.Improvements.Improvement;
import com.civilization.Model.Pair;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.Terrains.Terrain;

import java.util.HashMap;

public class Worker extends Unit {
    private Pair<Improvement, Integer> makingImprovement;

    public Worker(Terrain terrain, Civilization civilization) {
        super(UnitType.WORKER, terrain, civilization);
        this.makingImprovement = null;
    }

    public void makeImprovement(Improvement improvement) {
        if (makingImprovement!=null) {
            System.err.println( " 2 ta kar hamzaman nemishe");
            throw new RuntimeException();
        }
        makingImprovement=new Pair<>(improvement, 2);
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

    public Pair<Improvement, Integer> getMakingImprovement() {
        return makingImprovement;
    }

    public void setMakingImprovement(Pair<Improvement, Integer> makingImprovement) {
        this.makingImprovement = makingImprovement;
    }
}
