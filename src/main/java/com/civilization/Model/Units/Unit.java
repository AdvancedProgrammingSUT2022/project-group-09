package com.civilization.Model.Units;

import com.civilization.Model.Civilization;
import com.civilization.Model.TerrainFeatures.TerrainFeatureType;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainState;
import com.civilization.Model.Terrains.TerrainType;

import java.util.ArrayList;

public class Unit {
    private UnitType myType;
    private boolean isSleep;
    private Terrain terrain;
    private Civilization civilization;
    private int remainingMove;


    public UnitType getMyType() {
        return myType;
    }

    public void setMyType(UnitType myType) {
        this.myType = myType;
    }

    public boolean isSleep() {
        return isSleep;
    }

    public void setSleep(boolean sleep) {
        isSleep = sleep;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }

    public Unit(UnitType myType, Terrain terrain, Civilization civilization) {
        this.myType = myType;
        this.terrain = terrain;
        this.civilization = civilization;
    }

    public void move() {

    }

    public void Donothong() {

    }


    public void sleep() {

    }

    public void delete() {

    }

    public int getRemainingMove() {
        return remainingMove;
    }

    public void setRemainingMove(int remainingMove) {
        this.remainingMove = remainingMove;
    }

    public ArrayList<Terrain> getVisibleTerrain() {
        ArrayList<Terrain> result = new ArrayList<>();
        result.add(getTerrain());

        ArrayList<Terrain> targetTerrainsBackUp = getTerrain().getSurroundingTerrain();
        ArrayList<Terrain> targetTerrains = new ArrayList<>(targetTerrainsBackUp);
        for (int i = 0; i < getMyType().getRange(); i++) {

            for (Terrain targetTerrain : targetTerrainsBackUp) {
                if (!result.contains(targetTerrain)) result.add(targetTerrain);

                if (!(targetTerrain.getType() == TerrainType.MOUNTAIN ||
                        targetTerrain.getType() == TerrainType.HILLS ||
                        targetTerrain.getTerrainFeatures().contains(TerrainFeatureType.FOREST)))
                    targetTerrains.addAll(targetTerrain.getSurroundingTerrain());
            }
            targetTerrainsBackUp = new ArrayList<>(targetTerrains);
        }
        return result;
    }


}
