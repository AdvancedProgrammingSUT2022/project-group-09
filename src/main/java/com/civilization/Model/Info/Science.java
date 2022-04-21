package com.civilization.Model.Info;

public class Science {
    private int currentScience;
    private int additionScience;

    public int getCurrentScience() {
        return currentScience;
    }

    public void updateScience() {
        this.currentScience += this.additionScience;
    }

    public int getAdditionScience() {
        return additionScience;
    }

    public void setAdditionScience(int additionScience) {
        this.additionScience = additionScience;
    }
}
