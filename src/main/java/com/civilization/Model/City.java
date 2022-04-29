package com.civilization.Model;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.Buildings.BuildingAffect;
import com.civilization.Model.Buildings.BuildingType;
import com.civilization.Model.Info.CityFood;
import com.civilization.Model.Info.CityGold;
import com.civilization.Model.Info.CityProduct;
import com.civilization.Model.Info.CityScience;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Units.*;

import java.util.ArrayList;
import java.util.HashMap;

public class City extends Terrain implements Combatble, Selectable {
    private ArrayList<Terrain> citizens; //length=number of citizens and arraylisti az jahaei hast ke citizen ha kar mikonnand
    private boolean isCapital;
    private CityProduct cityProduct;
    private CityFood cityFood;
    private CityGold cityGold;
    private CityScience cityScience;
    private ArrayList<Terrain> terrains;

    private int makingCitizen;
    private Pair<Double, BuildingType> makingBuilding;//<remaining Product to build,building>

    private Pair<Double, UnitType> makingUnit;//<remaining Product to build,Unit>
    private String name = "default";
    private BuildingAffect buildings;
    private double hp = 50;

    private boolean unHappiness;

    public void CreateUnit(UnitType unitType) {
        if (!getCivilization().getTechnologies().getTechnologiesResearched().contains(unitType.getRequiredTechnology())) {
            System.err.println("technology mored nazara ro nadari");
            throw new RuntimeException();
        }
        if (makingBuilding != null || makingUnit != null) {
            System.err.println("2 ta chiz hamzaman nemitooni besazi");
            throw new RuntimeException();
        }
        makingUnit = new Pair<>((double) unitType.getCost(), unitType);
    }

    public void CreateBuilding(BuildingType buildingType) {
        if (!getCivilization().getTechnologies().getTechnologiesResearched().contains(buildingType.getRequirement())) {
            System.err.println("technology mored nazara ro nadari");
            throw new RuntimeException();
        }
        if (makingBuilding != null || makingUnit != null) {
            System.err.println("2 ta chiz hamzaman nemitooni besazi");
            throw new RuntimeException();
        }
        makingBuilding = new Pair<>((double) buildingType.getCost(), buildingType);
    }

    public City(Terrain terrain) {
        super(terrain);
        this.citizens = new ArrayList<>();
        this.isCapital = false;
        this.cityProduct = new CityProduct();
        this.cityFood = new CityFood();
        this.cityGold = new CityGold();
        this.cityScience = new CityScience();
        this.terrains = new ArrayList<>();
        this.makingBuilding = null;
        this.makingUnit = null;
        this.buildings = new BuildingAffect();
        this.hp = 20;
    }

    public City() {
        this.citizens = new ArrayList<>();
        this.isCapital = false;
        this.cityProduct = new CityProduct();
        this.cityFood = new CityFood();
        this.cityGold = new CityGold();
        this.cityScience = new CityScience();
        this.terrains = new ArrayList<>();
        this.makingBuilding = null;
        this.makingUnit = null;
        this.buildings = new BuildingAffect();
        this.hp = 20;
    }

    public City(City city) {
        this.citizens = city.getCitizens();
        this.isCapital = city.isCapital();
        this.cityProduct = city.getProduction();
        this.cityFood = city.getFood();
        this.cityGold = city.getGold();
        this.cityScience = new CityScience();
        this.terrains = city.getTerrains();
        this.makingBuilding = city.getMakingBuilding();
        this.makingUnit = city.getMakingUnit();
        this.buildings = city.getBuildings();
        this.hp = 20;
    }

    public ArrayList<Terrain> getCitizens() {
        return citizens;
    }

    public void setCitizens(ArrayList<Terrain> citizens) {
        this.citizens = citizens;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    public CityProduct getProduction() {
        return cityProduct;
    }

    public void setProduction(CityProduct production) {
        this.cityProduct = production;
    }

    public CityFood getFood() {
        return cityFood;
    }

    public void setFood(CityFood cityFood) {
        this.cityFood = cityFood;
    }

    public CityGold getGold() {
        return cityGold;
    }

    public void setGold(CityGold cityGold) {
        this.cityGold = cityGold;
    }

    public ArrayList<Terrain> getTerrains() {
        return terrains;
    }

    public void setTerrains(ArrayList<Terrain> terrains) {
        this.terrains = terrains;
    }

    public void addTerrain(Terrain terrain) {
        this.terrains.add(terrain);
    }

    public Pair<Double, BuildingType> getMakingBuilding() {
        return makingBuilding;
    }

    public void setMakingBuilding(Pair<Double, BuildingType> makingBuilding) {
        this.makingBuilding = makingBuilding;
    }

    public Pair<Double, UnitType> getMakingUnit() {
        return makingUnit;
    }

    public void setMakingUnit(Pair<Double, UnitType> makingUnit) {
        this.makingUnit = makingUnit;
    }

    public BuildingAffect getBuildings() {
        return buildings;
    }

    public void setBuildings(BuildingAffect buildings) {
        this.buildings = buildings;
    }

    @Override
    public void setCivilization(Civilization civilization) {
        for (Civilization civilization1 : GameDataBase.getCivilizations()) {
            for (City city : civilization1.getCities()) {
                city.getTerrains().remove(this);
            }
        }
        civilization.addCity(this);
    }

    @Override
    public Civilization getCivilization() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            for (City city : civilization.getCities()) {
                if (city == this)
                    return civilization;
            }
        }
        return null;
    }


    @Override
    public void attack(Combatble target) {
        if (target instanceof City) {
            System.err.println("hamle shahr be shahr nadarim");
            throw new RuntimeException();
        } else if (target instanceof MilitaryUnit) {
            MilitaryUnit targetUnit = (MilitaryUnit) target;
            //TODO ino az ghasd gozashtam 0 = ghodrat hamle shahr ke nemidoonam chande ridam be in doc naghes
            targetUnit.setHp(targetUnit.getHp() - 0);
            if (targetUnit.getHp() <= 0)
                targetUnit.delete();
        } else {
            Unit targetUnit = (Unit) target;
            targetUnit.getConqueredBy(this.getCivilization());
        }
    }

    @Override
    public void defend(Combatble target) {
        if (super.getMilitaryUnit() != null) {
            //garison shode
        } else {
            //bedoon garison defa kon
        }
        //shahr defend nadare
    }

    @Override
    public String getDetails() {
        return "city name: " + name + " number of citizens: " + citizens.size() + " x position: " + getXPosition()
                + " y position: " + getYPosition();
    }

    public void getConqueredBy(Civilization civilization) {
        setUnHappiness(true);
    }

    public void moveCitizen(int citizenNumber, Terrain targetTerrain) {
        citizens.set(citizenNumber, targetTerrain);
    }

    public void moveCitizen(Terrain currentTerrain, Terrain targetTerrain) {
        for (int i = 0; i < citizens.size(); i++)
            if (citizens.get(i) == currentTerrain) {
                citizens.set(i, targetTerrain);
                return;
            }
        System.err.println("citizeni ke dar inja kar konad vojood nadarad");
        throw new RuntimeException();
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public boolean isUnHappiness() {
        return unHappiness;
    }

    public void setUnHappiness(boolean unHappiness) {
        this.unHappiness = unHappiness;
    }

    public CityScience getCityScience() {
        return cityScience;
    }

    public void setCityScience(CityScience cityScience) {
        this.cityScience = cityScience;
    }

    public void nextTurn() {
        cityProduct.setCurrentProduct(0);
        cityFood.setAdditionFood(0);
        cityGold.setAdditionGold(0);
        cityScience.setAdditionScience(0);
        doCitizenWork();
        buildings.DoBuildingsWork();
        if (cityFood.getAdditionFood() < 0) {
            //koshtan citizen ha
        } else
            makingCitizen += cityFood.getAdditionFood();
        if (makingCitizen > 2) {
            citizens.add(null);
            makingCitizen = 0;
        }
        if (makingBuilding != null) {
            makingBuilding.setKey(makingBuilding.getKey() - getProduction().getCurrentProduct());
            if (makingBuilding.getKey() <= 0)
                deployUnit();
        }
        if (makingUnit != null) {
            makingUnit.setKey(makingUnit.getKey() - getProduction().getCurrentProduct());
            if (makingUnit.getKey() <= 0)
                deployBuilding();
        }
    }

    private void doCitizenWork() {
        for (Terrain terrain : citizens) {
            if (terrain != null) {
                cityFood.add(terrain.getType().getFood());
                cityProduct.add(terrain.getType().getProduct());
                cityGold.add(terrain.getType().getGold());
                for (TerrainFeature terrainFeature : terrain.getTerrainFeatures()) {
                    cityFood.add(terrainFeature.getFood());
                    cityProduct.add(terrainFeature.getProduct());
                    cityGold.add(terrainFeature.getGold());
                }
            }
            cityFood.add((-2) * citizens.size());
        }
    }

    private void deployUnit() {
        new Unit(makingUnit.getValue(), this, getCivilization());
        makingUnit = null;
    }

    private void deployBuilding() {
        buildings.addBuildings(makingBuilding.getValue());
        makingBuilding = null;
    }
}