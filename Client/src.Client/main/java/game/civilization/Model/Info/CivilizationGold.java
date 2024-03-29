package game.civilization.Model.Info;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Model.City;
import game.civilization.Model.Civilization;

public class CivilizationGold {
    private double additionGold;
    private double currentGold;

    private double addedFromCheat;

    public void update() {
        setAdditionGold(0);
        addAdditionGold(getAddedFromCheat());
        for (City city : getCivilization().getCities()) {
            addAdditionGold(city.getGold().getAdditionGold());
        }
    }

    public double getAddedFromCheat() {
        return addedFromCheat;
    }

    public void setAddedFromCheat(double addedFromCheat) {
        this.addedFromCheat = addedFromCheat;
    }

    public void addAdditionGold(double number) {
        additionGold += number;
    }

    public void addCurrentGold(double number) {
        currentGold += number;
    }

    public double getAdditionGold() {
        return additionGold;
    }

    public void setAdditionGold(double additionGold) {
        this.additionGold = additionGold;
    }

    public double getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(double currentGold) {
        this.currentGold = currentGold;
    }

    public Civilization getCivilization() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            if (civilization.getGold() == this)
                return civilization;
        }
        return null;
    }
}
