package Model;

import Model.Terrains.Terrain;

public class Map {
    private final Terrain[][] terrains = new Terrain[30][30];

    public void setTerrain(int x, int y, Terrain terrain) {

    }

    public Terrain getTerrain(int x, int y) {
        return null;
    }

    public int getXpositionTerrain(Terrain terrain) {
        return 0;
    }

    public int getypositionTerrain(Terrain terrain) {
        return 0;
    }

    public boolean isValidTerran(Terrain terrain) {
        return false;
    }

    public boolean isValidTerran(int x, int y) {
        return false;
    }

    public String showmap()
    {
        return "";
    }
}
