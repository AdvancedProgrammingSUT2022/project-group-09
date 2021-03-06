package game.civilization.Model.Units;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Model.City;
import game.civilization.Model.Civilization;
import game.civilization.Model.Terrains.Terrain;
import game.civilization.Model.Terrains.TerrainType;

public class Settler extends Unit {
    public Settler(Terrain terrain, Civilization civilization) {
        super(UnitType.SETTLER, terrain, civilization);
    }

    public void foundCity() {
        for (City city : getCivilization().getCities()) {
            if (city.isCapital()) {
                foundNormalCity();
                GameDataBase.getCurrentCivilization().getMap().updateExploration();
                return;
            }
        }
        foundCapitalCity();
        GameDataBase.getCurrentCivilization().getMap().updateExploration();
    }

    private void foundCapitalCity() {
        City city = new City(getTerrain());
        city.setCapital(true);
        city.setCivilizationToCapitalCity(getCivilization());
        GameDataBase.getMainMap().setTerrain(getTerrain().getXPosition(), getTerrain().getYPosition(), city);
        for (Terrain terrain : city.getSurroundingTerrain()) {
            terrain.setCivilizationToCapitalCity(getCivilization());
        }
        if (getTerrain().getType() == TerrainType.HILLS)
            city.setHp(city.getHp() + 20);
        delete();
    }

    private void foundNormalCity() {
        City city = new City(getTerrain());
        city.setCapital(false);
        city.setCivilizationToCapitalCity(getCivilization());
        GameDataBase.getMainMap().setTerrain(getTerrain().getXPosition(), getTerrain().getYPosition(), city);
        for (Terrain terrain : city.getSurroundingTerrain()) {
            terrain.setCivilizationToCapitalCity(getCivilization());
        }
        if (getTerrain().getType() == TerrainType.HILLS)
            city.setHp(city.getHp() + 20);
        delete();
    }

}
