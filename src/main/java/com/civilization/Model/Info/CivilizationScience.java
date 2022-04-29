package com.civilization.Model.Info;

public class CivilizationScience {
    private double additionScience;

    public void add(double number) {
        additionScience += number;
    }

    public double getAdditionScience() {
        return additionScience;
    }

    public void setAdditionScience(double additionScience) {
        this.additionScience = additionScience;
    }
}
