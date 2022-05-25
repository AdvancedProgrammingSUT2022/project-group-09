package civilization.civilization.main.java.com.civilization.Model.Units;

import civilization.civilization.main.java.com.civilization.Model.City;
import civilization.civilization.main.java.com.civilization.Model.Civilization;
import civilization.civilization.main.java.com.civilization.Model.Terrains.Terrain;

public class MilitaryUnit extends Unit {
    private boolean isInAlert;
    private boolean isFortifyHeal;

    public MilitaryUnit(UnitType myType, Terrain terrain, Civilization civilization) {
        super(myType, terrain, civilization);
    }

    public void alert() {
        //har roound call she
        setSleep(true);
        setWorkDone(true);
        for (Terrain terrain : getTerrain().getSurroundingTerrain()) {
            if (terrain.getMilitaryUnit() != null)
                if (terrain.getMilitaryUnit().getCivilization() != getCivilization()) {
                    isInAlert = false;
                    wake();
                }
        }
    }

    public void fortify() {
        setWorkDone(true);
        if (getHp() < 50 - 5)
            setHp(getHp() + 5);
    }

    public void fortifyHeal() {
        //har round call she
        if (getHp() == 50) {
            isFortifyHeal = false;
            setSleep(false);
            setWorkDone(false);
        }
        setSleep(true);
        fortify();
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
        super.setSleep(true);
        isInAlert = inAlert;
    }

    @Override
    public void attack(Combatble target) {
        if (target instanceof City) {
            attack((City) target);
        } else if (target instanceof MilitaryUnit) {
            attack((MilitaryUnit) target);
        } else {
            attack((Unit) target);
        }
    }


    private void attack(Unit civilianUnit) {
        if (civilianUnit instanceof MilitaryUnit) {
            System.err.println("civilian unit jaye military dade shode");
            throw new RuntimeException();
        }
        (civilianUnit).getConqueredBy(this.getCivilization());
    }


    private void attack(MilitaryUnit militaryUnit) {
        if (getMyType().getRangedCombatStrengh() == 0)
            militaryUnit.setHp(militaryUnit.getHp() - getMyType().getCombatStrengh()
                    * getTerrain().getCombatModifier() * UnitType.getDefensiveBonus(this) + attackPenalty(militaryUnit));
        else
            militaryUnit.setHp(militaryUnit.getHp() - getMyType().getRangedCombatStrengh()
                    * getTerrain().getCombatModifier() * UnitType.getDefensiveBonus(this) + attackPenalty(militaryUnit));
        militaryUnit.defend(this);
        if (this.getHp() > 0)
            if (militaryUnit.getHp() <= 0)
                militaryUnit.delete();

    }

    private void attack(City city) {
        if (getMyType().getRangedCombatStrengh() == 0)
            city.setHp(city.getHp() - getMyType().getCombatStrengh()
                    * getTerrain().getCombatModifier() * UnitType.getDefensiveBonus(this) + attackPenalty(city));
        else
            city.setHp(city.getHp() - getMyType().getRangedCombatStrengh()
                    * getTerrain().getCombatModifier() * UnitType.getDefensiveBonus(this) + attackPenalty(city));
        city.defend(this);
        if (this.getHp() > 0)
            if (city.getHp() <= 0)
                city.getConqueredBy(this.getCivilization());
    }

    public void defend(Combatble target) {
        if (target instanceof MilitaryUnit) {
            MilitaryUnit targetMilitaryUnit = (MilitaryUnit) target;
            if (targetMilitaryUnit.getMyType().getRangedCombatStrengh() == 0)
                targetMilitaryUnit.setHp(targetMilitaryUnit.getHp() - getMyType().getCombatStrengh()
                        * getTerrain().getCombatModifier());
            if (targetMilitaryUnit.getHp() <= 0)
                targetMilitaryUnit.delete();
        } else {
            System.err.println("defend dar moghabel military va city nist");
            throw new RuntimeException();
        }
    }

    public void pillage() {
        if (getTerrain().getImprovementPair().getKey() == null
                && getTerrain().getImprovementPair().getValue())
            getTerrain().getImprovementPair().setValue(false);
        else
            throw new RuntimeException();
    }

    private int attackPenalty(Combatble target) {
        if (this.getMyType() == UnitType.TANK && target instanceof City)
            return -10;
        if (this.getMyType() == UnitType.ARTILLERY && target instanceof City)
            return 10;
        if (this.getMyType() == UnitType.ANTITANKGUN && ((MilitaryUnit) target).getMyType() == UnitType.TANK)
            return 10;
        if (this.getMyType() == UnitType.CANON && target instanceof City)
            return 10;
        if (this.getMyType() == UnitType.TREBUCHET && target instanceof City)
            return 10;
        if (this.getMyType() == UnitType.PIKEMAN && ((MilitaryUnit) target).getMyType().getCombatType() == CombatType.MOUNTED)
            return 10;
        if (this.getMyType() == UnitType.CATAPULT && target instanceof City)
            return 10;
        if (this.getMyType() == UnitType.SPEARMAN && ((MilitaryUnit) target).getMyType().getCombatType() == CombatType.MOUNTED)
            return 10;
        return 0;
    }
}
