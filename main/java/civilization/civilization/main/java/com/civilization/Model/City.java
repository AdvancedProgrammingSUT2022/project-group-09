package civilization.civilization.main.java.com.civilization.Model;

import civilization.civilization.main.java.com.civilization.Controller.GameControllerPackage.GameDataBase;
import civilization.civilization.main.java.com.civilization.Model.Buildings.BuildingAffect;
import civilization.civilization.main.java.com.civilization.Model.Buildings.BuildingType;
import civilization.civilization.main.java.com.civilization.Model.Info.CityFood;
import civilization.civilization.main.java.com.civilization.Model.Info.CityGold;
import civilization.civilization.main.java.com.civilization.Model.Info.CityProduct;
import civilization.civilization.main.java.com.civilization.Model.Info.CityScience;
import civilization.civilization.main.java.com.civilization.Model.TerrainFeatures.TerrainFeature;
import civilization.civilization.main.java.com.civilization.Model.Terrains.Terrain;
import civilization.civilization.main.java.com.civilization.Model.Units.*;

import java.util.ArrayList;

public class City extends Terrain implements Combatble, Selectable {
    private ArrayList<Terrain> citizens; // length=number of citizens and arraylisti az jahaei hast ke citizen ha kar
    // mikonnand
    private boolean isCapital;
    private CityProduct cityProduct;
    private CityFood cityFood;
    private CityGold cityGold;
    private CityScience cityScience;
    private ArrayList<Terrain> terrains;

    private int makingCitizen;
    private Pair<Double, BuildingType> makingBuilding;// <remaining Product to build,building>

    private Pair<Double, UnitType> makingUnit;// <remaining Product to build,Unit>
    private String name = "default";
    private BuildingAffect buildings;
    private double hp = 50;

    private boolean unHappiness;

    public void CreateUnit(UnitType unitType) {
        if (!(getCivilization().getTechnologies().getTechnologiesResearched().contains(unitType.getRequiredTechnology())
                || unitType.getRequiredTechnology() == null)) {
            System.err.println("technology mored nazara ro nadari");
            throw new RuntimeException();
        }
        if (makingBuilding != null || makingUnit != null) {
            System.err.println("2 ta chiz hamzaman nemitooni besazi");
            throw new RuntimeException();
        }
        makingUnit = new Pair<>((double) unitType.getCost(), unitType);
    }

    public void createUnitInstantly(UnitType unitType) {
        if (!(getCivilization().getTechnologies().getTechnologiesResearched().contains(unitType.getRequiredTechnology())
                || unitType.getRequiredTechnology() == null)) {
            System.err.println("technology mored nazara ro nadari");
            throw new RuntimeException();
        }
        if (makingBuilding != null || makingUnit != null) {
            System.err.println("2 ta chiz hamzaman nemitooni besazi");
            throw new RuntimeException();
        }
        if (UnitType.getNormalMilitaryUnit().contains(unitType) && this.getMilitaryUnit() == null) {
            new MilitaryUnit(unitType, this, this.getCivilization());
        }
        if (UnitType.getSiegeMilitaryUnit().contains(unitType) && this.getMilitaryUnit() == null) {
            new SiegeMilitaryUnit(unitType, this, this.getCivilization());
        }
        if (unitType == UnitType.SETTLER && this.getCivilianUnit() == null) {
            new Settler(this, this.getCivilization());
        }
        if (unitType == UnitType.WORKER && getCivilianUnit() == null) {
            new Worker(this, this.getCivilization());
        }
        getResources().remove(unitType.getRequiredResourse());
        GameDataBase.getCurrentCivilization().getGold().setCurrentGold(
                GameDataBase.getCurrentCivilization().getGold().getCurrentGold() - unitType.getCost());
    }

    public void createBuilding(BuildingType buildingType) {
        if (!(getCivilization().getTechnologies().getTechnologiesResearched().contains(buildingType.getRequirement())
                || buildingType.getRequirement() == null)) {
            System.err.println("technology mored nazara ro nadari");
            throw new RuntimeException();
        }
        if (makingBuilding != null || makingUnit != null) {
            System.err.println("2 ta chiz hamzaman nemitooni besazi");
            throw new RuntimeException();
        }
        makingBuilding = new Pair<>((double) buildingType.getCost(), buildingType);
    }

    public void createBuildingInstantly(BuildingType buildingType) {
        if (!(getCivilization().getTechnologies().getTechnologiesResearched().contains(buildingType.getRequirement())
                || buildingType.getRequirement() == null)) {
            System.err.println("technology mored nazara ro nadari");
            throw new RuntimeException();
        }
        if (makingBuilding != null || makingUnit != null) {
            System.err.println("2 ta chiz hamzaman nemitooni besazi");
            throw new RuntimeException();
        }
        buildings.addBuilding(buildingType);
        GameDataBase.getCurrentCivilization().getGold().setCurrentGold(
                GameDataBase.getCurrentCivilization().getGold().getCurrentGold() - buildingType.getCost());
    }

    public City(Terrain terrain) {
        super(terrain);
        this.citizens = new ArrayList<>();
        this.isCapital = true;
        this.cityProduct = new CityProduct();
        this.cityFood = new CityFood();
        this.cityGold = new CityGold();
        this.cityScience = new CityScience();
        this.terrains = new ArrayList<>();
        this.makingBuilding = null;
        this.makingUnit = null;
        this.buildings = new BuildingAffect();
        this.hp = 20;

        this.citizens.add(null);
        cityProduct.setCurrentProduct(5);
        cityFood.setAdditionFood(5);
        cityGold.setAdditionGold(5);
        cityScience.setAdditionScience(5);

    }

    public City() {
        this.citizens = new ArrayList<>();
        this.citizens.add(null);
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
            civilization1.getCities().remove(this);
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
            targetUnit.setHp(targetUnit.getHp() - 2);
            if (targetUnit.getHp() <= 0)
                targetUnit.delete();
        } else {
            Unit targetUnit = (Unit) target;
            targetUnit.getConqueredBy(this.getCivilization());
        }
    }

    @Override
    public void defend(Combatble target) {
        attack(target);
    }

    public String showMakingUnit() {
        if (makingUnit == null && makingBuilding == null)
            return "chizi nemisaze";
        if (makingBuilding != null)
            return makingBuilding.getKey() + " product moonde baraye " + makingBuilding.getValue();
        return makingUnit.getKey() + " product moonde baraye " + makingUnit.getValue();
    }

    public String getDetails() {
        StringBuilder buildingString = new StringBuilder();
        for (BuildingType building : getBuildings().getBuildings()) {
            buildingString.append(building).append(" ");
        }
        return "city name: " + name + " number of citizens: " + citizens.size() + " x position: " + getXPosition()
                + " y position: " + getYPosition() + "\n" +
                "production : " + getProduction().getCurrentProduct() + "\n" +
                "gold : " + getGold().getAdditionGold() + "\n" +
                "science : " + getCityScience().getAdditionScience() + "\n" +
                "food : " + getFood().getAdditionFood() + "\n" +
                "hp : " + getHp() + "\n" +
                "citizens : " + getCitizens().size() + "\n" +
                "buildings: " + buildingString;
    }

    public void getConqueredBy(Civilization civilization) {
        setUnHappiness(true);
        setCivilization(civilization);
    }

    public void deleteCity() {
        Terrain terrain = new Terrain(this);
        GameDataBase.getMainMap().setTerrain(getXPosition(), getYPosition(), terrain);
        for (Civilization civilization1 : GameDataBase.getCivilizations()) {
            civilization1.getCities().remove(this);
            for (City city : civilization1.getCities()) {
                city.getTerrains().remove(this);
            }
        }
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
        update();
        if (cityFood.getAdditionFood() < 0) {
            citizens.remove(0);
            cityFood.setAdditionFood(0);
        } else
            makingCitizen += cityFood.getAdditionFood(); // badaz ye adadi citizen jadid miad
        if (makingCitizen > 2) {
            citizens.add(null);
            makingCitizen = 0;
        }
        if (makingBuilding != null) {
            makingBuilding.setKey(makingBuilding.getKey() - getProduction().getCurrentProduct());
            if (makingBuilding.getKey() <= 0)
                deployBuilding();
        }
        if (makingUnit != null) {
            makingUnit.setKey(makingUnit.getKey() - getProduction().getCurrentProduct());
            if (makingUnit.getKey() <= 0)
                deployUnit();
        }
    }

    public void update() {
        cityProduct.setCurrentProduct(5);
        cityFood.setAdditionFood(5);
        cityGold.setAdditionGold(5);
        cityScience.setAdditionScience(5);
        doCitizenWork();
        buildings.DoBuildingsWork();
        if (makingUnit != null)
            if (makingUnit.getValue() == UnitType.SETTLER)
                cityFood.add(-2); // kam kardan ghaza agar settler boodi
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
                if (terrain.getImprovement() != null) {
                    cityFood.add(terrain.getImprovement().getFood());
                    cityProduct.add(terrain.getImprovement().getProduction());
                    cityGold.add(terrain.getImprovement().getGold());
                }
            }
        }
        cityFood.add((-2) * citizens.size());
    }

    private void deployUnit() {
        UnitType unitType = makingUnit.getValue();
        if (UnitType.getNormalMilitaryUnit().contains(unitType) && getMilitaryUnit() == null) {
            new MilitaryUnit(makingUnit.getValue(), this, getCivilization());
            getResources().remove(unitType.getRequiredResourse());
            makingUnit = null;
        }
        if (UnitType.getSiegeMilitaryUnit().contains(unitType) && getMilitaryUnit() == null) {
            new SiegeMilitaryUnit(makingUnit.getValue(), this, getCivilization());
            getResources().remove(unitType.getRequiredResourse());
            makingUnit = null;
        }
        if (unitType == UnitType.SETTLER && getCivilianUnit() == null) {
            new Settler(this, getCivilization());
            makingUnit = null;
        }
        if (unitType == UnitType.WORKER && getCivilianUnit() == null) {
            new Worker(this, getCivilization());
            makingUnit = null;
        }
    }

    private void deployBuilding() {
        buildings.addBuilding(makingBuilding.getValue());
        makingBuilding = null;
    }

    public ArrayList<BuildingType> buildingsCanBeBuilt() {
        ArrayList<BuildingType> possibleBuildings = new ArrayList<>();
        for (BuildingType building : BuildingType.getAllBuildings()) {
            if (getCivilization().getTechnologies().getTechnologiesResearched().contains(building.getRequirement())
                    || building.getRequirement() == null) {
                possibleBuildings.add(building);
            }
        }
        return possibleBuildings;
    }

    public ArrayList<UnitType> unitsCanBeBuilt() {
        ArrayList<UnitType> possibleUnits = new ArrayList<>();
        ArrayList<UnitType> all = UnitType.getAllUnits();
        for (UnitType unit : all) {
            if (getCivilization().getTechnologies().getTechnologiesResearched().contains(unit.getRequiredTechnology())
                    || unit.getRequiredTechnology() == null) {
                if (getCivilization().getResources().contains(unit.getRequiredResourse()) ||
                        unit.getRequiredResourse() == null) {
                    possibleUnits.add(unit);
                }
            }
        }
        return possibleUnits;
    }

    public String getDemographics() {
        int counter = 0;
        StringBuilder res = new StringBuilder();
        for (Terrain citizen : citizens) {
            if (citizen == null)
                res.append("citizen").append(counter).append(" bikare");
            else
                res.append("citizen").append(counter).append(" ").append(citizen.getCoordination().toString())
                        .append("ke midahad : ").append(citizen.showGoldProductFood());
            counter++;
        }
        return res.toString();
    }
}