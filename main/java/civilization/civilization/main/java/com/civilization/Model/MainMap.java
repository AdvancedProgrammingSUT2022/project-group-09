package civilization.civilization.main.java.com.civilization.Model;

import civilization.civilization.main.java.com.civilization.Model.Resources.Resource;
import civilization.civilization.main.java.com.civilization.Model.TerrainFeatures.TerrainFeature;
import civilization.civilization.main.java.com.civilization.Model.Terrains.Terrain;
import civilization.civilization.main.java.com.civilization.Model.Terrains.TerrainType;

import java.util.ArrayList;
import java.util.Random;

public class MainMap extends Map {
    private final Terrain[][] terrains = new Terrain[row][column];
    private final ArrayList<Coordination> drought = new ArrayList<>();
    private final ArrayList<Terrain> hasRiver = new ArrayList<>();
    private final int numberOfDrought = 400, numberOfRivers = 100;

    public Terrain[][] getTerrains() {
        return terrains;
    }

    public MainMap() {
        Random random = new Random();
        int xStart = random.nextInt(row);
        int yStart = random.nextInt(column);
        randomDrought(random, xStart, yStart);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                terrains[i][j] = new Terrain(randomTerrainType(random, i, j));
                terrains[i][j].setTerrainFeatures(randomTerrainFeature(random, terrains[i][j]));
                terrains[i][j].setResources(randomResources(random, terrains[i][j]));
            }
        }
        Coordination randomDrought = drought.get(random.nextInt(drought.size() - 1));
        int xRiver = randomDrought.getX();
        int yRiver = randomDrought.getY();
        randomRiver(random, xRiver, yRiver);
        // for (int i = 0; i < GameDataBase.getCivilizations().size(); i++) {
        // Coordination coordination = drought.get(random.nextInt(drought.size()));
        // int x = coordination.getX();
        // int y = coordination.getY();
        // if (getTerrain(x, y).getCivilization() == null) {
        // getTerrain(x, y).setCivilization(GameDataBase.getCivilizations().get(i));
        // } else {
        // i--;
        // }
        // }
        // aylin oomade inja be har civilization yedoone terrain dade
    }

    private boolean isCoordinationUsed(int x, int y) {
        for (Coordination coordination : drought) {
            if (coordination.getX() == x && coordination.getY() == y) {
                return true;
            }
        }
        return false;
    }

    private void randomDrought(Random random, int x, int y) {
        drought.add(new Coordination(x, y));
        if (drought.size() >= numberOfDrought)
            return;
        for (int i = 0; i < 6; i++) {
            boolean stste = random.nextBoolean();
            if (stste) {
                if (y % 2 == 0) {
                    if (i == 0 && x - 1 >= 0 && y - 1 >= 0 && !isCoordinationUsed(x - 1, y - 1)) {
                        randomDrought(random, x - 1, y - 1);
                    }
                    if (i == 1 && x - 1 >= 0 && !isCoordinationUsed(x - 1, y)) {
                        randomDrought(random, x - 1, y);
                    }
                    if (i == 2 && x - 1 >= 0 && y + 1 < column && !isCoordinationUsed(x - 1, y + 1)) {
                        randomDrought(random, x - 1, y + 1);
                    }
                    if (i == 3 && y - 1 >= 0 && !isCoordinationUsed(x, y - 1)) {
                        randomDrought(random, x, y - 1);
                    }
                    if (i == 4 && x + 1 < row && !isCoordinationUsed(x + 1, y)) {
                        randomDrought(random, x + 1, y);
                    }
                    if (i == 5 && y + 1 < column && !isCoordinationUsed(x, y + 1)) {
                        randomDrought(random, x, y + 1);
                    }
                }
                if (y % 2 == 1) {
                    if (i == 0 && y - 1 >= 0 && !isCoordinationUsed(x, y - 1)) {
                        randomDrought(random, x, y - 1);
                    }
                    if (i == 1 && x - 1 >= 0 && !isCoordinationUsed(x - 1, y)) {
                        randomDrought(random, x - 1, y);
                    }
                    if (i == 2 && y + 1 < column && !isCoordinationUsed(x, y + 1)) {
                        randomDrought(random, x, y + 1);
                    }
                    if (i == 3 && y - 1 >= 0 && x + 1 < row && !isCoordinationUsed(x + 1, y - 1)) {
                        randomDrought(random, x + 1, y - 1);
                    }
                    if (i == 4 && x + 1 < row && !isCoordinationUsed(x + 1, y)) {
                        randomDrought(random, x + 1, y);
                    }
                    if (i == 5 && x + 1 < row && y + 1 < column && !isCoordinationUsed(x + 1, y + 1)) {
                        randomDrought(random, x + 1, y + 1);
                    }
                }
            }
        }
    }

    private void randomRiver(Random random, int x, int y) {
        terrains[x][y].getTerrainFeatures().add(TerrainFeature.RIVER);
        hasRiver.add(terrains[x][y]);
        if (hasRiver.size() >= numberOfRivers)
            return;
        for (int i = 0; i < 6; i++) {
            boolean stste = random.nextBoolean();
            if (stste) {
                if (y % 2 == 0) {
                    if (i == 0 && x - 1 >= 0 && y - 1 >= 0 && !terrains[x - 1][y - 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                        randomRiver(random, x - 1, y - 1);
                    }
                    if (i == 1 && x - 1 >= 0 && !terrains[x - 1][y].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                        randomRiver(random, x - 1, y);
                    }
                    if (i == 2 && x - 1 >= 0 && y + 1 < column && !terrains[x - 1][y + 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                        randomRiver(random, x - 1, y + 1);
                    }
                    if (i == 3 && y - 1 >= 0 && !terrains[x][y - 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                        randomRiver(random, x, y - 1);
                    }
                    if (i == 4 && x + 1 < row && !terrains[x + 1][y].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                        randomRiver(random, x + 1, y);
                    }
                    if (i == 5 && y + 1 < column && !terrains[x][y + 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                        randomRiver(random, x, y + 1);
                    }
                }
                if (y % 2 == 1) {
                    if (i == 0 && y - 1 >= 0 && !terrains[x][y - 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                        randomRiver(random, x, y - 1);
                    }
                    if (i == 1 && x - 1 >= 0 && !terrains[x - 1][y].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                        randomRiver(random, x - 1, y);
                    }
                    if (i == 2 && y + 1 < column && !terrains[x][y + 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                        randomRiver(random, x, y + 1);
                    }
                    if (i == 3 && y - 1 >= 0 && x + 1 < row && !terrains[x + 1][y - 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                        randomRiver(random, x + 1, y - 1);
                    }
                    if (i == 4 && x + 1 < row && !terrains[x + 1][y].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                        randomRiver(random, x + 1, y);
                    }
                    if (i == 5 && x + 1 < row && y + 1 < column && !terrains[x + 1][y + 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                        randomRiver(random, x + 1, y + 1);
                    }
                }
            }
        }
    }

    private TerrainType randomTerrainType(Random random, int x, int y) {
        if (!isCoordinationUsed(x, y))
            return TerrainType.OCEAN;
        ArrayList<TerrainType> types = new ArrayList<TerrainType>() {
            {
                add(TerrainType.DESERT);
                add(TerrainType.HILLS);
                add(TerrainType.GRASSLLAND);
                add(TerrainType.MOUNTAIN);
                add(TerrainType.PLAIN);
                add(TerrainType.SNOW);
                add(TerrainType.TUNDRA);
            }
        };
        int whichType = random.nextInt(7);
        return types.get(whichType);
    }

    private ArrayList<TerrainFeature> randomTerrainFeature(Random random, Terrain terrain) {
        ArrayList<TerrainFeature> features = new ArrayList<>();
        for (TerrainFeature terrainFeature : terrain.getType().getPossibleFeatures()) {
            boolean state = random.nextBoolean();
            if (state) {
                if ((!terrainFeature.equals(TerrainFeature.FLOODPLAINS) || features.contains(TerrainFeature.RIVER)) && !features.equals(TerrainFeature.RIVER))
                    features.add(terrainFeature);
            }
        }
        return features;
    }

    private ArrayList<Resource> randomResources(Random random, Terrain terrain) {
        ArrayList<Resource> resources = new ArrayList<>();
        for (Resource resource : terrain.getType().getPossibleResources()) {
            boolean state = random.nextBoolean();
            if (state) {
                resources.add(resource);
            }
        }
        for (TerrainFeature terrainFeature : terrain.getTerrainFeatures()) {
            for (Resource resource : terrainFeature.getPossibleResources()) {
                boolean state = random.nextBoolean();
                if (state) {
                    resources.add(resource);
                }
            }
        }
        return resources;
    }

    public ArrayList<Coordination> getDrought() {
        return drought;
    }

    public void setTerrain(int x, int y, Terrain terrain) {
        terrains[x][y] = terrain;
    }

    public Terrain getTerrain(int x, int y) {
        if (x > Map.getRow() - 1 || x < 0 || y > Map.getColumn() - 1 || y < 0) {
            System.err.println("ERROR: position of out bounds! MainMap.getTerrain(int x, int y)");
            throw new RuntimeException();
        }
        return terrains[x][y];
    }

    //TODO terrain -> coordination
    public int getXpositionTerrain(Terrain terrain) {
        int horizental = terrains[0].length;
        int vertical = terrains.length;
        for (int i = 0; i < vertical; i++)
            for (int j = 0; j < horizental; j++) {
                if (terrains[i][j] == terrain)
                    return i;
            }
        System.err.println("ERROR! in getXpositionTerrain ");
        throw new RuntimeException();
    }

    public int getYpositionTerrain(Terrain terrain) {
        int horizental = terrains[0].length;
        for (Terrain[] value : terrains)
            for (int j = 0; j < horizental; j++) {
                if (value[j] == terrain)
                    return j;
            }
        System.err.println("ERROR! in getXpositionTerrain ");
        throw new RuntimeException();
    }

    public boolean isValidTerran(Terrain terrain) {
        int horizental = terrains[0].length;
        for (Terrain[] value : terrains)
            for (int j = 0; j < horizental; j++) {
                if (value[j] == terrain)
                    return true;
            }
        return false;
    }

    private Coordination findDroughtByXY(int x, int y) {
        for (Coordination coordination : drought) {
            if (coordination.getX() == x && coordination.getY() == y) {
                return coordination;
            }
        }
        return null;
    }

}
