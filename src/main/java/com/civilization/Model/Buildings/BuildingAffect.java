package com.civilization.Model.Buildings;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.City;
import com.civilization.Model.Civilization;
import com.civilization.Model.Resources.Resource;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Units.CombatType;
import com.civilization.Model.Units.Unit;
import com.civilization.Model.Units.UnitType;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

public class BuildingAffect {
    private ArrayList<BuildingType> buildings;

    private void Barracks() {
        if (buildings.contains(BuildingType.BARRACKS))
            for (Unit unit : getCivilization().getUnits()) {
                unit.setXp(unit.getXp() + 15);
            }
    }

    private void Gnarary() {
        if (buildings.contains(BuildingType.GRANARY))
            getCity().getFood().setAdditionFood(getCity().getFood().getAdditionFood() + 2);
    }

    private void Library() {
        if (buildings.contains(BuildingType.LIBRARY))
            getCivilization().getScience().setAdditionScience(getCivilization().getScience().getAdditionScience() + (float) ((getCity().getCitizens().size()) / 2));
    }

    private void addWall() {
        getCity().setHp(getCity().getHp() + 5);
    }

    private void watermill() {
        if (buildings.contains(BuildingType.WATERMILL))
            getCity().getFood().setAdditionFood(getCity().getFood().getAdditionFood() + 2);
    }

    private void addCastle() {
        getCity().setHp(getCity().getHp() + 7.5);
    }

    private void Armory() {
        if (buildings.contains(BuildingType.ARMORY))
            for (Unit unit : getCivilization().getUnits()) {
                unit.setXp(unit.getXp() + 15);
            }
    }

    private void burialTob() {
        if (buildings.contains(BuildingType.BURIALTOMB))
            getCivilization().getHappiness().setAdditionHappiness(getCivilization().getHappiness().getAdditionHappiness() + 2);
    }

    private void circus() {
        if (buildings.contains(BuildingType.CIRCUS))
            getCivilization().getHappiness().setAdditionHappiness(getCivilization().getHappiness().getAdditionHappiness() + 3);
    }

    private void colosseum() {
        if (buildings.contains(BuildingType.COLOSSEUM))
            getCivilization().getHappiness().setAdditionHappiness(getCivilization().getHappiness().getAdditionHappiness() + 4);
    }


    private void addCourthhouse() {
        getCity().setUnHappiness(false);
    }

    private void Stable() {
        if (buildings.contains(BuildingType.STABLE))
            for (Map.Entry<Integer, UnitType> entry : getCity().getMakingUnit().entrySet()) {
                if (entry.getValue().getCombatType() == CombatType.MOUNTED)
                    getCity().getProduction().setCurrentProduct(getCity().getProduction().getCurrentProduct() * 5 / 4);
            }
    }

    private void Forage() {
        if (buildings.contains(BuildingType.FORGE))
            getCity().getProduction().setCurrentProduct(getCity().getProduction().getCurrentProduct() * 115 / 100);
    }

    private void garden() {
        //hich kari nemikone in
    }

    private void market() {
        if (getBuildings().contains(BuildingType.MARKET))
            getCity().getGold().setAdditionGold(getCity().getGold().getAdditionGold() * 5 / 4);
    }

    private void mint() {
        if (buildings.contains(BuildingType.MINT)) {
            int number = 0;
            for (Resource resource : getCity().getResources()) {
                if (resource == Resource.GOLD || resource == Resource.SILVER)
                    number++;
            }
            getCity().getGold().setAdditionGold(getCity().getGold().getAdditionGold() + number * 3);
        }
    }

    private void monastery() {
        //hich gohi nemikhore
    }

    private void university() {
        if (buildings.contains(BuildingType.UNIVERSITY)) {
            int num = 0;
            for (Terrain citizen : getCity().getCitizens()) {
                if (citizen != null)
                    if (citizen.getTerrainFeatures().contains(TerrainFeature.JUNGLE))
                        num++;
            }
            getCivilization().getScience().setAdditionScience(getCivilization().getScience().getAdditionScience() + 2 * num);
            getCivilization().getScience().setAdditionScience(getCivilization().getScience().getAdditionScience() * 3 / 2);
        }
    }

    private void workshop() {
        if (buildings.contains(BuildingType.WORKSHOP)) {
            for (Map.Entry<Integer, BuildingType> entry : getCity().getMakingBuilding().entrySet()) {
                if (entry.getValue() != null)
                    getCity().getProduction().setCurrentProduct(getCity().getProduction().getCurrentProduct() * 6 / 5);
            }
        }
    }

    private void bank() {
        if (buildings.contains(BuildingType.BANK)) {
            getCity().getGold().setAdditionGold(getCity().getGold().getAdditionGold() * 5 / 4);
        }
    }

    private void militaryAcademy() {
        if (buildings.contains(BuildingType.MILITARYACADEMY))
            for (Unit unit : getCivilization().getUnits()) {
                unit.setXp(unit.getXp() + 15);
            }
    }

    private void museum() {
        //hichi
    }

    private void operaHouse() {
        //hichi
    }

    private void publicSchool() {
        if (buildings.contains(BuildingType.PUBLICSCHOOL)) {
            getCivilization().getScience().setAdditionScience(getCivilization().getScience().getAdditionScience() * 3 / 2);
        }
    }

    private void satrapCourt() {
        if (buildings.contains(BuildingType.SATRAPSCOURT)) {
            getCity().getGold().setAdditionGold(getCity().getGold().getAdditionGold() * 5 / 4);
            getCivilization().getHappiness().setAdditionHappiness(getCivilization().getHappiness().getAdditionHappiness() + 2);
        }
    }

    private void theater() {
        if (buildings.contains(BuildingType.THEATER))
            getCivilization().getHappiness().setAdditionHappiness(getCivilization().getHappiness().getAdditionHappiness() + 4);
    }

    private void windMill() {
        if (buildings.contains(BuildingType.WINDMILL))
            getCity().getProduction().setCurrentProduct(getCity().getProduction().getCurrentProduct() * 115 / 100);
    }

    private void arsenal() {
        if (buildings.contains(BuildingType.ARSENAL)) {
            for (Map.Entry<Integer, UnitType> entry : getCity().getMakingUnit().entrySet()) {
                if (entry.getValue() != null)
                    getCity().getProduction().setCurrentProduct(getCity().getProduction().getCurrentProduct() * 6 / 5);
            }
        }
    }

    private void broadcastTower() {
        //hichi
    }

    private void Factory() {
        if (buildings.contains(BuildingType.FACTORY)) {
            getCity().getProduction().setCurrentProduct(getCity().getProduction().getCurrentProduct() * 3 / 2);
        }
    }

    private void hospital() {
        if (buildings.contains(BuildingType.HOSPITAL))
            getCity().getFood().setAdditionFood(getCity().getFood().getAdditionFood() / 2);
    }

    private void addmilitaryBase() {
        if (buildings.contains(BuildingType.MILITARYBASE))
            getCity().setHp(getCity().getHp() + 12);
    }

    private void stockExchange() {
        if (buildings.contains(BuildingType.STOCKEXCHANGE)) {
            getCity().getGold().setAdditionGold(getCity().getGold().getAdditionGold() * 4 / 3);
        }
    }

    public void DoBuildingsWork() {

    }


    public BuildingAffect(ArrayList<BuildingType> buildings) {
        this.buildings = buildings;
    }

    public BuildingAffect() {
        this.buildings = new ArrayList<>();
    }

    public ArrayList<BuildingType> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<BuildingType> buildings) {
        this.buildings = buildings;
    }

    public void addBuildings(BuildingType building) {
        this.buildings.add(building);
    }

    public Civilization getCivilization() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            for (City city : civilization.getCities()) {
                if (city.getBuildings() == this)
                    return civilization;
            }
        }
        throw new RuntimeException();
    }

    public City getCity() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            for (City city : civilization.getCities()) {
                if (city.getBuildings() == this)
                    return city;
            }
        }
        throw new RuntimeException();
    }

}
