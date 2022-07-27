package game.civilization.Model.Units;

import game.civilization.Controller.GameControllerPackage.CheatController;
import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Model.City;
import game.civilization.Model.Civilization;
import game.civilization.Model.Coordination;
import game.civilization.Model.Selectable;
import game.civilization.Model.TerrainFeatures.TerrainFeature;
import game.civilization.Model.Terrains.Terrain;
import game.civilization.Model.Terrains.TerrainType;

import java.util.ArrayList;

public class Unit implements Combatble, Selectable {

    private int xp;
    private boolean workDone;
    private UnitType myType;
    private boolean isSleep;
    private int remainingMove;
    private double hp;
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
        if (this instanceof MilitaryUnit) {
            for (Terrain[] terrains : GameDataBase.getMainMap().getTerrains()) {
                for (Terrain terrain1 : terrains) {
                    if (terrain1.getMilitaryUnit() == (MilitaryUnit) this) {
                        terrain1.setMilitaryUnit(null);
                    }
                }
            }
            if (terrain.getMilitaryUnit() != null) {
                System.err.println("ERROR! 2 ta military nemitoonan yeja bashan");
                throw new RuntimeException();
            } else {
                terrain.setMilitaryUnit((MilitaryUnit) this);
                if (terrain.isRuin()) {
                    getCivilization().getGold().setCurrentGold(getCivilization().getGold().getCurrentGold() + 500);
                    new CheatController().openTechnologies();
                    new CheatController().openTerrains();
                    for (City city : getCivilization().getCities()) {
                        city.getCitizens().add(null);
                    }
                    terrain.setRuin(false);
                }
            }
        } else {
            for (Terrain[] terrains : GameDataBase.getMainMap().getTerrains()) {
                for (Terrain terrain1 : terrains) {
                    if (terrain1.getCivilianUnit() == (Unit) this)
                        terrain1.setCivilianUnit(null);
                }
            }
            if (terrain.getCivilianUnit() != null) {
                System.err.println("ERROR! 2 ta civilian unit nemitoonan yeja bashan");
                throw new RuntimeException();
            } else {
                terrain.setCivilianUnit(this);
                if (terrain.isRuin()) {
                    getCivilization().getGold().setCurrentGold(getCivilization().getGold().getCurrentGold() + 500);
                    new CheatController().openTechnologies();
                    new CheatController().openTerrains();
                    for (City city : getCivilization().getCities()) {
                        city.getCitizens().add(null);
                    }
                    terrain.setRuin(false);
                }
            }
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
        // System.out.println(this.path);
        if (isSleep() || isWorkDone())
            return;
        if (path.isEmpty())
            return;
        // for debugging purposes
        // System.out.println("aval masir");
        // for (Coordination coordination : path) {
        // System.out.println(coordination.toString());
        // }
        // System.out.println("akhar masir");
        for (int i = 0; i < path.size(); i++) {
            // System.out.println("alan injaem " + getTerrain().getCoordination().toString());
            Terrain terrain = path.get(i).getTerrain();
            if (!isMovePossible(terrain)) {
                path.clear();
                break;
            }
            if (this.getTerrain().isHasRoad() && terrain.isHasRoad()) {

            } else if (terrain.getTerrainFeatures().contains(TerrainFeature.RIVER)
                    && this.getTerrain().getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                this.remainingMove = 0;
            } else {
                this.remainingMove -= terrain.getMp();
            }
            this.setTerrain(terrain);
            this.path.remove(i);
            i = 0;
            if (this.remainingMove < 0) {
                this.remainingMove = 0;
                this.workDone = true;
                break;
            }
        }
        setWorkDone(true);
    }

    private boolean isMovePossible(Terrain nextTerrain) {
        if (this.myType == UnitType.SETTLER || this.myType == UnitType.WORKER)
            if (nextTerrain.getCivilianUnit() != null)
                return false;
            else if (nextTerrain.getMilitaryUnit() != null)
                if (nextTerrain.getMilitaryUnit().getCivilization() == this.getCivilization())
                    return false;
                else
                    return false;
        // TODO implement combat
        return true;
    }

    public void DoNothing() {
        path.clear();
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

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
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
        return myType + " at " + getTerrain().getCoordination().toString() + " for : " + getCivilization().getName() +
                " Worke done : " + workDone + " sleep : " + isSleep +
                "\n remaining Mp : " + remainingMove + " hp : " + hp +
                " size of masiri ke bayad bere : " + path.size();
    }

    public void nextTurn() {
        if (!this.isSleep())
            this.setWorkDone(false);
        setRemainingMove(getMyType().getMovement());
        move();
        if (this instanceof Worker) {
            Worker worker = (Worker) this;
            worker.nextTurn();
        }

        if (this instanceof MilitaryUnit) {
            if (((MilitaryUnit) this).isInAlert())
                ((MilitaryUnit) this).alert();

            if (((MilitaryUnit) this).isFortifyHeal())
                ((MilitaryUnit) this).fortifyHeal();
        }


    }

}
