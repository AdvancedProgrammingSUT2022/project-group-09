package com.civilization.Model.Info;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.Civilization;

public class CivilizationScience {
    private double additionScience;
    private double addedFromCheat;

    public double getAddedFromCheat() {
        return addedFromCheat;
    }

    public void setAddedFromCheat(double addedFromCheat) {
        this.addedFromCheat = addedFromCheat;
    }

    public void add(double number) {
        additionScience += number;
    }

    public double getAdditionScience() {
        return additionScience;
    }

    public void setAdditionScience(double additionScience) {
        this.additionScience = additionScience;
    }

    public Civilization getCivilization() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            if (civilization.getScience() == this)
                return civilization;
        }
        return null;
    }
}
