package com.civilization.Model.Info;

public class CivilizationGold {
    private double additionGold;
    private double currentGold;
    public void add(double number) {
        additionGold += number;
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
}
