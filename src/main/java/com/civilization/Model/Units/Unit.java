package com.civilization.Model.Units;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.City;
import com.civilization.Model.Civilization;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainType;

import java.util.ArrayList;

public class Unit {
    private UnitType myType;
    private boolean isSleep;
    private int remainingMove;

    public Unit(UnitType myType) {
        this.myType = myType;
    }

    public Unit(UnitType myType, Terrain terrain, Civilization civilization) {
        this.myType = myType;
        setTerrain(terrain);
        setCivilization(civilization);
    }

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

        for (Terrain[] terrains : GameDataBase.getMainMap().getTerrains()) {
            for (Terrain terrain : terrains) {
                if (terrain.getCivilianUnit() == this || terrain.getMilitaryUnit() == this)
                    return terrain;
            }
        }
        return null;
    }

    public void setTerrain(Terrain terrain) {
        for (Terrain[] terrains : GameDataBase.getMainMap().getTerrains()) {
            for (Terrain terrain1 : terrains) {
                if (terrain1.getCivilianUnit() == this)
                    terrain1.setCivilization(null);
                if (terrain.getMilitaryUnit() == this)
                    terrain1.setMilitaryUnit(null);
            }
        }
        if (this instanceof MilitaryUnit) {
            if (terrain.getMilitaryUnit() != null) {
                System.err.println("ERROR! 2 ta military nemitoonan yeja bashan");
                throw new RuntimeException();
            } else
                terrain.setMilitaryUnit((MilitaryUnit) this);
        } else {
            if (terrain.getCivilianUnit() != null) {
                System.err.println("ERROR! 2 ta civilian unit nemitoonan yeja bashan");
                throw new RuntimeException();
            } else
                terrain.setCivilianUnit(this);
        }

    }

    public Civilization getCivilization() {

        for (Civilization civilization : GameDataBase.getCivilizations()) {
            if (civilization.getUnits().contains(this))
                return civilization;
        }
        return null;
    }

    public void setCivilization(Civilization civilization) {
        for (Civilization civilization1 : GameDataBase.getCivilizations()) {
            civilization1.getUnits().remove(this);
        }
        civilization.addUnit(this);
    }

    public void move() {

    }

    public void Donothong() {

    }


    public void sleep() {

    }

    public void delete() {
        getCivilization().removeUnit(this);
        for (Terrain[] terrains : GameDataBase.getMainMap().getTerrains()) {
            for (Terrain terrain : terrains) {
                if (terrain.getMilitaryUnit() == this)
                    terrain.setMilitaryUnit(null);
                if (terrain.getCivilianUnit() == this)
                    terrain.setCivilianUnit(null);
            }
        }
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
                        targetTerrain.getTerrainFeatures().contains(TerrainFeature.FOREST)))
                    targetTerrains.addAll(targetTerrain.getSurroundingTerrain());
            }
            targetTerrainsBackUp = new ArrayList<>(targetTerrains);
        }
        return result;
    }

}
