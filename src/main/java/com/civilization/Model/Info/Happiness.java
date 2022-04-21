package com.civilization.Model.Info;

public class Happiness {
    private int currentHappiness;
    private int additionHappiness;

    public int getCurrentHappiness() {
        return currentHappiness;
    }

    public void updateHappiness() {
        this.currentHappiness += this.additionHappiness;
    }

    public int getAdditionHappiness() {
        return additionHappiness;
    }

    public void setAdditionHappiness(int additionHappiness) {
        this.additionHappiness = additionHappiness;
    }
}
