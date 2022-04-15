package Model;

import Model.Terrains.Terrain;

import java.util.ArrayList;

public class City extends Terrain {
    private boolean isCapital;
    private Product production;
    private Food food;
    private Gold gold;
    private ArrayList<Terrain> terrains;
    private ArrayList<Citizen> citizens;

}