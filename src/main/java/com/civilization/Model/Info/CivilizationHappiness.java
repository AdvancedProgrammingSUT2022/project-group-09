package com.civilization.Model.Info;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.Civilization;
import com.civilization.Model.Resources.Resource;

public class CivilizationHappiness {
    private int additionHappiness;
    private double addedFromCheat;

    public double getAddedFromCheat() {
        return addedFromCheat;
    }

    public void setAddedFromCheat(double addedFromCheat) {
        this.addedFromCheat = addedFromCheat;
    }

    public void nexTurn() {
        additionHappiness -= 2 * getCivilization().getCities().size();//unhappines baraye shahr ha
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
