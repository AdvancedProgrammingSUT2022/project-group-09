package com.civilization.Model;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.Resources.ResourceType;
import com.civilization.Model.TerrainFeatures.TerrainFeatureType;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Terrains.TerrainType;

import java.util.ArrayList;
import java.util.Random;

public class MainMap extends Map {
    private ArrayList<Coordination> drought = new ArrayList<>();
    private int numberOfDrought;


    public void updateMap() {
        int horizental = 80;
        int vertical = 50;
        for (int q = 0; q < GameDataBase.getCivilizations().size(); q++) {
            for (int i = 0; i < vertical; i++)
                for (int j = 0; j < horizental; j++) {
                    Terrain targetTerrain = GameDataBase.getCivilizations().get(q).getMap().getTerrain(i, j);
                    Terrain mainMapTerrain = terrains[i][j];
                    //TADA .. !
                    targetTerrain.setBuilding(mainMapTerrain.getBuilding());
                    targetTerrain.setCitizens(mainMapTerrain.getCitizens());
                    targetTerrain.setTerrainFeatures(mainMapTerrain.getTerrainFeatures());
                    targetTerrain.setCity(mainMapTerrain.getCity());
                    targetTerrain.setCivilization(mainMapTerrain.getCivilization());
                    targetTerrain.setCivilianUnit(mainMapTerrain.getCivilianUnit());
                    targetTerrain.setResources(mainMapTerrain.getResources());
                    targetTerrain.setCitizens(mainMapTerrain.getCitizens());
                    targetTerrain.setType(mainMapTerrain.getType());
                    targetTerrain.setMilitaryUnit(mainMapTerrain.getMilitaryUnit());
                }
        }
    }

    public MainMap() {
        Random random = new Random();
        int xStart = random.nextInt() % row;
        int yStart = random.nextInt() % column;
        randomDrought(random, xStart, yStart);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                terrains[i][j] = new Terrain(randomTerrainType(random, i, j));
                terrains[i][j].setTerrainFeatures(randomTerrainFeature(random, terrains[i][j]));
                terrains[i][j].setResources(randomResources(random, terrains[i][j]));
            }
        }
        for (int i = 0; i < GameDataBase.getCivilizations().size(); i++) {
            Coordination coordination = drought.get(random.nextInt() % drought.size());
            int x = coordination.getX();
            int y = coordination.getY();
            if (getTerrain(x, y).getCivilization() == null) {
                getTerrain(x, y).setCivilization(GameDataBase.getCivilizations().get(i));
            }
            else {
                i--;
            }
        }
    }

    public boolean isCoordinationUsed(int x, int y) {
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
        int whichType = random.nextInt() % 7;
        return types.get(whichType);
    }

    private ArrayList<TerrainFeatureType> randomTerrainFeature(Random random, Terrain terrain) {
        ArrayList<TerrainFeatureType> features = new ArrayList<>();
        for (TerrainFeatureType terrainFeature : terrain.getTerrainFeatures()) {
            boolean state = random.nextBoolean();
            if (state) {
                features.add(terrainFeature);
            }
        }
        return features;
    }

    private ArrayList<ResourceType> randomResources(Random random, Terrain terrain) {
        ArrayList<ResourceType> resources = new ArrayList<>();
        for (ResourceType resource : terrain.getResources()) {
            boolean state = random.nextBoolean();
            if (state) {
                resources.add(resource);
            }
        }
        for (TerrainFeatureType terrainFeature : terrain.getTerrainFeatures()) {
            for (ResourceType resource : terrainFeature.getPossibleResources()) {
                boolean state = random.nextBoolean();
                if (state) {
                    resources.add(resource);
                }
            }
        }
        return resources;
    }
}
