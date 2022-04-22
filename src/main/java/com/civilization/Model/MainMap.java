package com.civilization.Model;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.Resources.Resource;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.Terrains.CitizenCanWork;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainType;
import com.civilization.Model.Units.Settler;

import java.util.ArrayList;
import java.util.Random;

public class MainMap extends Map {
    private final ArrayList<Coordination> drought = new ArrayList<>();
    private final int numberOfDrought = 400;


    public void updateMap() {
        int horizental = 80;
        int vertical = 50;
        for (int q = 0; q < GameDataBase.getCivilizations().size(); q++) {
            for (int i = 0; i < vertical; i++)
                for (int j = 0; j < horizental; j++) {
                    Terrain targetTerrain = GameDataBase.getCivilizations().get(q).getMap().getTerrain(i, j);
                    Terrain mainMapTerrain = terrains[i][j];
                    if (targetTerrain == null)
                        targetTerrain = new Terrain();
                    //TADA .. !
                    targetTerrain.setImprovement(mainMapTerrain.getImprovement());
                    targetTerrain.setHasRoad(mainMapTerrain.isHasRoad());
                    targetTerrain.setTerrainFeatures(mainMapTerrain.getTerrainFeatures());
                    targetTerrain.setResources(mainMapTerrain.getResources());
                    targetTerrain.setCivilianUnit(mainMapTerrain.getCivilianUnit());
                    targetTerrain.setResources(mainMapTerrain.getResources());
                    targetTerrain.setType(mainMapTerrain.getType());
                    targetTerrain.setMilitaryUnit(mainMapTerrain.getMilitaryUnit());
                    //if terrain=City
                    if (mainMapTerrain instanceof City) {
                        City mainMapCity = (City) mainMapTerrain;
                        City targetCity;
                        if (targetTerrain instanceof City)
                            targetCity = (City) targetTerrain;
                        else
                            targetCity = new City(mainMapCity);
                        targetCity.setCitizens(mainMapCity.getCitizens());
                        targetCity.setCapital(mainMapCity.isCapital());
                        targetCity.setProduction(mainMapCity.getProduction());
                        targetCity.setFood(mainMapCity.getFood());
                        targetCity.setGold(mainMapCity.getGold());
                        targetCity.setTerrains(mainMapCity.getTerrains());
                        targetCity.setMakingBuilding(mainMapCity.getMakingBuilding());
                        targetCity.setMakingUnit(mainMapCity.getMakingUnit());
                        targetCity.setBuildings(mainMapCity.getBuildings());
                    }
                }
        }
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
//        for (int i = 0; i < GameDataBase.getCivilizations().size(); i++) {
//            Coordination coordination = drought.get(random.nextInt(drought.size()));
//            int x = coordination.getX();
//            int y = coordination.getY();
//            if (getTerrain(x, y).getCivilization() == null) {
//                getTerrain(x, y).setCivilization(GameDataBase.getCivilizations().get(i));
//            } else {
//                i--;
//            }
//        }
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
        for (TerrainFeature terrainFeature : terrain.getTerrainFeatures()) {
            boolean state = random.nextBoolean();
            if (state) {
                features.add(terrainFeature);
            }
        }
        return features;
    }

    private ArrayList<Resource> randomResources(Random random, Terrain terrain) {
        ArrayList<Resource> resources = new ArrayList<>();
        for (Resource resource : terrain.getResources()) {
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
}
