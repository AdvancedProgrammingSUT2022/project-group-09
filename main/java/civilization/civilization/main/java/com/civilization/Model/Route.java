package civilization.civilization.main.java.com.civilization.Model;

import civilization.civilization.main.java.com.civilization.Controller.GameControllerPackage.GameDataBase;

import java.util.ArrayList;

public class Route {
    ArrayList<Coordination> coordinations;
    int mp;

    public Route(){
        coordinations = new ArrayList<>();
        mp = 0;
    }

    public void addTerrain(int x, int y) {
        coordinations.add(new Coordination(x, y));
        mp += GameDataBase.getMainMap().getTerrain(x, y).getMp();
    }

    public ArrayList<Coordination> getRouteCoordinations() {
        return coordinations;
    }

    public int getMp() {
        return mp;
    }
}