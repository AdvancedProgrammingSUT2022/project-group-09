package game.civilization.Controller.GameControllerPackage;

import game.civilization.Model.City;
import game.civilization.Model.Coordination;
import game.civilization.Model.Terrains.Terrain;
import game.civilization.Model.Units.MilitaryUnit;
import game.civilization.Model.Units.SiegeMilitaryUnit;
import game.civilization.Model.Units.Unit;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class CombatController {

    public String militaryAttack(Coordination coordination) {
        int x = coordination.getX();
        int y = coordination.getY();
        Coordination coordinate2 = new Coordination(x, y);
        MilitaryUnit militaryUnit = ((MilitaryUnit) GameDataBase.getSelected());
        Coordination coordinate1 = militaryUnit.getTerrain().getCoordination();
        //start
        boolean safa = false;
        for (Terrain terrain : coordination.getTerrain().getSurroundingTerrain()) {
            for (Terrain terrain1 : terrain.getSurroundingTerrain()) {
                if (terrain.getMilitaryUnit() == ((MilitaryUnit) GameDataBase.getSelected())) {
                    safa = true;
                    break;
                }
            }
        }
        if (!safa) {
            ArrayList<Coordination> path = new UnitController().getBestPath(coordinate2.getTerrain(),
                    coordinate1.getTerrain(),
                    militaryUnit);
            if (path == null)
                return "unfortunately there is no available path for your unit to move to your desired destination";
            int turnNeed = new UnitController().turnNeedToMove(coordinate2.getTerrain(), coordinate1.getTerrain(),
                    militaryUnit);
            if (turnNeed <= 1)
                while (path.size() > militaryUnit.getMyType().getRange()) {
                    militaryUnit.setTerrain(path.get(0).getTerrain());
                    path.remove(0);
                }
            else
                return "bish az 1 turn mikhad va mojaz nist";
        }
        //end
        if (!coordinate2.isValidCoordination()) {
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
                !((SiegeMilitaryUnit) GameDataBase.getSelected()).isInSiege()) {
            GameDataBase.getCurrentCivilization().updateNotification("first you have to setup");
            return "first you have to setup";
        }
        if (coordinate2.getTerrain() instanceof City) {
            ((MilitaryUnit) GameDataBase.getSelected()).attack((City) coordinate2.getTerrain());
        } else if (coordinate2.getTerrain().getMilitaryUnit() != null) {
            ((MilitaryUnit) GameDataBase.getSelected()).attack(coordinate2.getTerrain().getMilitaryUnit());
        } else if (coordinate2.getTerrain().getCivilianUnit() != null) {
            ((MilitaryUnit) GameDataBase.getSelected()).attack(coordinate2.getTerrain().getCivilianUnit());
        } else {
            return "You can't attack this position!";
        }
        GameDataBase.getCurrentCivilization().updateNotification("Military unit " + militaryUnit.getMyType().name()
                + " attacked from " + coordinate1.toString() + " to " + coordinate2.toString());
        return "Attacked!";
    }

    public String cityAttack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordinate = new Coordination(x, y);
        // Coordination coordinate2 = new Coordination(x, y);
        // City city = (City) GameDataBase.getSelected();
        // Coordination coordinate1 = city.getCoordination();
        // ArrayList<Coordination> path = new
        // UnitController().getBestPath(coordinate2.getTerrain(),
        // coordinate1.getTerrain(),
        // militaryUnit);
        // TODO fasele city ta oon cordinate ro mikham
        // if (path.size() > 2)
        // return "doore va attack nemishe";
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
