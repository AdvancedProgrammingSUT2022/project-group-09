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
        if (!getCivilization().getTechnologies().getTechnologiesResearched().contains(improvement.getRequiredTechnology())) {
            System.err.println("technology sho nadari");
            throw new RuntimeException();
        }

        if (makingImprovement != null) {
            System.err.println(" 2 ta kar hamzaman nemishe");
            throw new RuntimeException();
        }
        makingImprovement = new Pair<>(improvement, 2);
        setWorkDone(true);
        //TODO har improvement chand turn mikhad ro nagofte
    }

    public void removeJungle() {
        getTerrain().getTerrainFeatures().remove(TerrainFeature.JUNGLE);
        setWorkDone(true);
    }

    public void removeForest() {
        getTerrain().getTerrainFeatures().remove(TerrainFeature.FOREST);
        setWorkDone(true);
    }


    public void removeMarsh() {
        getTerrain().getTerrainFeatures().remove(TerrainFeature.MARSH);
        setWorkDone(true);
    }


    public void removeRoute() {
        getTerrain().setHasRoad(false);
        setWorkDone(true);
    }

    public void repair() {
        getTerrain().getImprovementPair().setValue(true);
        setWorkDone(true);
    }

    public Pair<Improvement, Integer> getMakingImprovement() {
        return makingImprovement;
    }

    public void setMakingImprovement(Pair<Improvement, Integer> makingImprovement) {
        this.makingImprovement = makingImprovement;
    }

    public void nextTurn() {
        if (makingImprovement != null) {
            makingImprovement.setValue(makingImprovement.getValue() - 1);
            if (makingImprovement.getValue() <= 0) {
                deployImprovement();
                makingImprovement = null;
            }
        }
    }

    private void deployImprovement() {
        if (makingImprovement.getKey() == Improvement.ROAD)
            getTerrain().setHasRoad(true);
        else
            getTerrain().setImprovement(makingImprovement.getKey());
    }

    @Override
    public void DoNothing() {
        getPath().clear();
        setWorkDone(true);
        setSleep(false);
        makingImprovement = null;
    }
}
