package com.civilization.Model;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.Terrains.Terrain;

public class Coordination {
    int x, y;

    public Coordination(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "x : " + x + " y : " + y;
    }

    public Terrain getTerrain() {
        return GameDataBase.getMainMap().getTerrain(this.x, this.y);
    }

    public boolean isValidCoordination() {
        if (x >= 0 && x < Map.row && y >= 0 && y < Map.column) {
            return true;
        }
        return false;
    }

    public boolean equal(Coordination coordination) {
        if (x == coordination.getX())
            return y == coordination.getY();
        return false;
    }
}
