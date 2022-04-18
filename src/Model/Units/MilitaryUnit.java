package Model.Units;

import Model.Civilization;
import Model.Terrains.Terrain;

public class MilitaryUnit extends Unit {
    private boolean isInAlert;
    private boolean isInSiege;
    private boolean isFortify;

    public MilitaryUnit(UnitType myType, Terrain terrain, Civilization civilization) {
        super(myType, terrain, civilization);
        this.isInSiege = true;
    }

    
    public void attack() {

    }

    public void setPosition() {

    }

    public void upgradeUnit() {

    }

    public void alert() {

    }

    public void fortify() {

    }

    public boolean isInAlert() {
        return isInAlert;
    }

    public void setInAlert(boolean inAlert) {
        isInAlert = inAlert;
    }

    public boolean isFortify() {
        return isFortify;
    }

    public void setFortify(boolean fortify) {
        isFortify = fortify;
    }

    public boolean isInSiege() {
        return isInSiege;
    }

    public void setInSiege(boolean inSiege) {
        isInSiege = inSiege;
    }
}
