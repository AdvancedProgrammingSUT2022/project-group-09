package com.civilization.Model.Units;

import com.civilization.Model.City;
import com.civilization.Model.Civilization;
import com.civilization.Model.Terrains.Terrain;

public class MilitaryUnit extends Unit {
    private boolean isInAlert;
    private boolean isInSiege;
    private boolean isFortifyHeal;

    public MilitaryUnit(UnitType myType, Terrain terrain, Civilization civilization) {
        super(myType, terrain, civilization);
        this.isInSiege = true;
    }

    public void alert() {
        setSleep(true);
        setWorkDone(true);
        for (Terrain terrain : getTerrain().getSurroundingTerrain()) {
            if (terrain.getMilitaryUnit().getCivilization() != getCivilization())
                wake();
        }
    }

    public void fortify() {
        setWorkDone(true);
        if (getHp() < 50 - 5)
            setHp(getHp() + 5);
    }

    public void fortifyHeal() {
        isFortifyHeal = true;
        setWorkDone(true);
        setSleep(true);
    }

    public void garrison() {
        if (getTerrain() instanceof City) {
            City city = (City) getTerrain();
            if (city.getCivilization() == getCivilization()) {
                city.setHp(city.getHp() + this.getHp());
                delete();
            }
        }

    }

    public void setUp() {
        isInSiege = true;
        setWorkDone(true);
    }

    public void setFortifyHeal(boolean fortifyHeal) {
        isFortifyHeal = fortifyHeal;
    }

    public boolean isFortifyHeal() {
        return isFortifyHeal;
    }

    public boolean isInAlert() {
        return isInAlert;
    }

    public void setInAlert(boolean inAlert) {
        isInAlert = inAlert;
    }

    public boolean isInSiege() {
        return isInSiege;
    }

    public void setInSiege(boolean inSiege) {
        isInSiege = inSiege;
    }

    @Override
    public void attack(Combatble target) {
        if (target instanceof City) {
            City targetCity = (City) target;
            if (getMyType().getRangedCombatStrengh() == 0)
                targetCity.setHp(targetCity.getHp() - getMyType().getCombatStrengh());
            else
                targetCity.setHp(targetCity.getHp() - getMyType().getRangedCombatStrengh());
            targetCity.defend(this);
            if (targetCity.getHp() <= 0)
                targetCity.getConqueredBy(getCivilization());
        } else if (target instanceof MilitaryUnit) {
            MilitaryUnit targetUnit = (MilitaryUnit) target;
            if (getMyType().getRangedCombatStrengh() == 0)
                targetUnit.setHp(targetUnit.getHp() - getMyType().getCombatStrengh());
            else
                targetUnit.setHp(targetUnit.getHp() - getMyType().getRangedCombatStrengh());
            targetUnit.defend(this);
            if (targetUnit.getHp() <= 0)
                targetUnit.delete();
        } else {
            Unit targetUnit = (Unit) target;
            targetUnit.getConqueredBy(this.getCivilization());
        }
    }

    public void defend(Combatble target) {
        if (target instanceof MilitaryUnit) {
            MilitaryUnit targetMilitaryUnit = (MilitaryUnit) target;
            if (targetMilitaryUnit.getMyType().getRangedCombatStrengh() == 0)
                targetMilitaryUnit.setHp(targetMilitaryUnit.getHp() - getMyType().getCombatStrengh());
            if (targetMilitaryUnit.getHp() <= 0)
                targetMilitaryUnit.delete();
        } else if (target instanceof City) {

            //dar moghabel shahr defaei nadarim
        } else {
            System.err.println("defend dar moghabel military va city nist");
            throw new RuntimeException();
        }

    }
}
