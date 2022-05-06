package com.civilization.Controller.GameControllerPackage;

import com.civilization.Model.City;
import com.civilization.Model.Coordination;
import com.civilization.Model.Units.MilitaryUnit;
import com.civilization.Model.Units.SiegeMilitaryUnit;
import com.civilization.Model.Units.Unit;

import java.util.regex.Matcher;

public class CombatController {

    public String militaryAttack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordinate = new Coordination(x, y);
        if (!coordinate.isValidCoordination()) {
            return "Coordinate is not valid!";
        }
        if (!(GameDataBase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDataBase.getSelected()).getCivilization() != GameDataBase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        if (!(GameDataBase.getSelected() instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        if (GameDataBase.getSelected() instanceof SiegeMilitaryUnit &&
                !((SiegeMilitaryUnit) GameDataBase.getSelected()).isInSiege())
            return "first you have to setup";
        if (coordinate.getTerrain() instanceof City) {
            ((MilitaryUnit) GameDataBase.getSelected()).attack((City) coordinate.getTerrain());
        } else if (coordinate.getTerrain().getMilitaryUnit() != null) {
            ((MilitaryUnit) GameDataBase.getSelected()).attack(coordinate.getTerrain().getMilitaryUnit());
        } else if (coordinate.getTerrain().getCivilianUnit() != null) {
            ((MilitaryUnit) GameDataBase.getSelected()).attack(coordinate.getTerrain().getCivilianUnit());
        } else {
            return "You can't attack this position!";
        }
        return "Attacked!";
    }


    public String cityAttack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordinate = new Coordination(x, y);
        if (!coordinate.isValidCoordination()) {
            return "Coordinate is not valid!";
        }
        if (!(GameDataBase.getSelected() instanceof City)) {
            return "This is not a city";
        }
        if (coordinate.getTerrain().getMilitaryUnit() != null) {
            ((City) GameDataBase.getSelected()).attack(coordinate.getTerrain().getMilitaryUnit());
        } else if (coordinate.getTerrain().getCivilianUnit() != null) {
            ((City) GameDataBase.getSelected()).attack(coordinate.getTerrain().getCivilianUnit());
        } else {
            return "You can't attack this position!";
        }
        return "Attacked!";
    }

}
