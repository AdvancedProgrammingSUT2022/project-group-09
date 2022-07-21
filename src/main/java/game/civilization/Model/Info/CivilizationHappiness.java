package game.civilization.Model.Info;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Model.City;
import game.civilization.Model.Civilization;
import game.civilization.Model.Resources.Resource;

import java.util.ArrayList;

public class CivilizationHappiness {
    private int additionHappiness;
    private double addedFromCheat;
    private int removedHappiness;

    public double getAddedFromCheat() {
        return addedFromCheat;
    }

    public void setAddedFromCheat(double addedFromCheat) {
        this.addedFromCheat = addedFromCheat;
    }

    public void nexTurn() {
        removedHappiness += 2 * getCivilization().getCities().size();//unhappines baraye shahr ha
        for (City city : getCivilization().getCities()) {
            removedHappiness += city.getCitizens().size() / 3;//har 3 citizen ye hapines kam mikone
        }
    }

    public void update() {
        additionHappiness = 10;
        additionHappiness += addedFromCheat;
        ArrayList<Resource> luxuryResource = new ArrayList<>(Resource.getLuxuryResources());
        for (Resource resource : getCivilization().getResources()) {
            if (luxuryResource.contains(resource)) {
                add(4); //luxury ha
                luxuryResource.remove(resource);
            }
        }
        additionHappiness -= removedHappiness;
    }

    private Civilization getCivilization() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            if (civilization.getHappiness() == this)
                return civilization;
        }
        throw new RuntimeException();
    }

    public void add(double number) {
        additionHappiness += number;
    }

    public int getAdditionHappiness() {
        return additionHappiness;
    }

    public void setAdditionHappiness(int additionHappiness) {
        this.additionHappiness = additionHappiness;
    }
}
