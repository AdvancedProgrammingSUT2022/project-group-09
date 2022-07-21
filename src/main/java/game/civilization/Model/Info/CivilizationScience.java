package game.civilization.Model.Info;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Model.City;
import game.civilization.Model.Civilization;

public class CivilizationScience {
    private double additionScience;
    private double additionScienceCopy;
    private double addedFromCheat;

    public double getAddedFromCheat() {
        return addedFromCheat;
    }

    public void setAddedFromCheat(double addedFromCheat) {
        this.addedFromCheat = addedFromCheat;
    }

    public void add(double number) {
        additionScience += number;
        additionScienceCopy = additionScience;
    }

    public double getAdditionScience() {
        return additionScience;
    }

    public void setAdditionScience(double additionScience) {
        this.additionScience = additionScience;
        if (additionScience != 0)
            additionScienceCopy = additionScience;
    }

    public Civilization getCivilization() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            if (civilization.getScience() == this)
                return civilization;
        }
        return null;
    }

    public void update() {
        setAdditionScience(50);
        add(getAddedFromCheat());
        for (City city : getCivilization().getCities()) {
            add(city.getCityScience().getAdditionScience());
        }
    }
    public double getAdditionScienceCopy() {
        return additionScienceCopy;
    }

    public void setAdditionScienceCopy(double additionScienceCopy) {
        this.additionScienceCopy = additionScienceCopy;
    }
}
