package com.civilization.Model.Units;

import com.civilization.Model.Civilization;
import com.civilization.Model.Improvements.Improvement;
import com.civilization.Model.Pair;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.Terrains.Terrain;

public class Worker extends Unit {
    private Pair<Improvement, Integer> makingImprovement;

    public Worker(Terrain terrain, Civilization civilization) {
        super(UnitType.WORKER, terrain, civilization);
        this.makingImprovement = null;
    }

    public void makeImprovement(Improvement improvement) {
        if (!(getCivilization().getTechnologies().getTechnologiesResearched().contains(improvement.getRequiredTechnology()) ||
                improvement.getRequiredTechnology() == null)) {
            System.err.println("technology sho nadari");
            throw new RuntimeException();
        }

        if (makingImprovement != null) {
            System.err.println(" 2 ta kar hamzaman nemishe");
            throw new RuntimeException();
        }
        makingImprovement = new Pair<>(improvement, improvement.getTurn());
        setWorkDone(true);
    }

    private void removeJungle() {
        getTerrain().getTerrainFeatures().remove(TerrainFeature.JUNGLE);
        setWorkDone(true);
    }

    private void removeForest() {
        getTerrain().getTerrainFeatures().remove(TerrainFeature.FOREST);
        setWorkDone(true);
    }


    private void removeMarsh() {
        getTerrain().getTerrainFeatures().remove(TerrainFeature.MARSH);
        setWorkDone(true);
    }


    private void removeRoute() {
        getTerrain().setHasRoad(false);
        setWorkDone(true);
    }

    private void repair() {
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
        else if (makingImprovement.getKey() == Improvement.REMOVE_FOREST)
            removeForest();
        else if (makingImprovement.getKey() == Improvement.REPAIR)
            repair();
        else if (makingImprovement.getKey() == Improvement.REMOVE_ROUTE)
            removeRoute();
        else if (makingImprovement.getKey() == Improvement.REMOVE_MARSH)
            removeMarsh();
        else if (makingImprovement.getKey() == Improvement.REMOVE_JUNGLE)
            removeJungle();
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

    public String getWorkingDetail() {
        if (makingImprovement == null)
            return "chzi nemisaze";
        return makingImprovement.getKey() + " " + makingImprovement.getValue() + " turn baghimoonde";
    }
}
