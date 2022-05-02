package com.civilization.Model.Units;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.Civilization;
import com.civilization.Model.Coordination;
import com.civilization.Model.Selectable;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainType;

import java.util.ArrayList;

public class Unit implements Combatble, Selectable {

    private int xp;
    private boolean workDone;
    private UnitType myType;
    private boolean isSleep;
    private int remainingMove;
    private int hp;
    private ArrayList<Coordination> path = new ArrayList<>();

    public Unit(UnitType myType) {
        this.myType = myType;
    }

    public Unit(UnitType myType, Terrain terrain, Civilization civilization) {
        this.myType = myType;
        this.hp = 10;
        this.remainingMove = myType.getMovement();
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

    public ArrayList<Coordination> getPath() {
        return this.path;
    }

    public void setPath(ArrayList<Coordination> path) {
        this.path = path;
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
                    terrain1.setCivilianUnit(null);
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
        getCivilization().getMap().updateExploration();
        if (isSleep() || isWorkDone())
            return;
        if (path.isEmpty())
            return;
        System.out.println("aval masir");
        for (Coordination coordination : path) {
            System.out.println(coordination.toString());
        }
        System.out.println("akhar masir");
        for (int i = 0; i < path.size(); i++) {
            System.out.println("alan injaem " + getTerrain().getCoordination().toString());
            Terrain terrain = path.get(i).getTerrain();
            if (terrain.getMp() > this.remainingMove) {
                break;
            }
            this.remainingMove -= terrain.getMp();
            if (terrain.getTerrainFeatures().contains(TerrainFeature.RIVER)
                    && this.getTerrain().getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                this.remainingMove = 0;
            }
            this.setTerrain(terrain);
            path.remove(i);
            i = 0;
            if (remainingMove == 0)
                workDone = true;
        }
    }


    public void DoNothing() {
        workDone = true;
        isSleep = false;
    }

    public void sleep() {
        workDone = true;
        isSleep = true;
    }

    public void wake() {
        workDone = false;
        isSleep = false;
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
                if (!result.contains(targetTerrain))
                    result.add(targetTerrain);

                if (!(targetTerrain.getType() == TerrainType.MOUNTAIN ||
                        targetTerrain.getType() == TerrainType.HILLS ||
                        targetTerrain.getTerrainFeatures().contains(TerrainFeature.FOREST)))
                    targetTerrains.addAll(targetTerrain.getSurroundingTerrain());
            }
            targetTerrainsBackUp = new ArrayList<>(targetTerrains);
        }
        return result;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public void attack(Combatble target) {
        System.err.println("in attack nabayad call mishod va attack military unit bayad call she");
        throw new RuntimeException();
    }

    @Override
    public void defend(Combatble target) {
        System.err.println("in defend nabayad call mishod va defend military unit bayad call she");
        throw new RuntimeException();
    }

    public void getConqueredBy(Civilization civilization) {
        if (this instanceof MilitaryUnit)
            delete();
        else
            this.setCivilization(civilization);
    }

    public boolean isWorkDone() {
        return workDone;
    }

    public void setWorkDone(boolean workDone) {
        this.workDone = workDone;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public String showInfo() {
        return myType + " at " + getTerrain().getCoordination().toString() + " Worke done : " + workDone + " sleep : " + isSleep +
                " remaining Mp : " + remainingMove + " hp : " + hp +
                " size of masiri ke bayad bere : " + path.size();
    }

}
