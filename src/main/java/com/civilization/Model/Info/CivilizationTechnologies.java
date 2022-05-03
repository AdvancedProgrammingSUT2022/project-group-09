package com.civilization.Model.Info;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Model.Civilization;
import com.civilization.Model.TechnologyPackage.TechnologyType;

import java.util.ArrayList;
import java.util.HashMap;

public class CivilizationTechnologies {
    private ArrayList<TechnologyType> technologiesResearched;
    private TechnologyType technologyCurrentlyResearching;
    private HashMap<TechnologyType, Integer> technologiesAvailable;
    private ArrayList<TechnologyType> technologiesUnavailable;
    private int remainCost;

    public CivilizationTechnologies() {
        technologiesUnavailable = TechnologyType.getAllTechnologies();
        technologiesAvailable = new HashMap<>();
        technologiesResearched = new ArrayList<>();
        loookingForAvailable();
    }

    public void loookingForAvailable() {
        boolean isAvalable;
        TechnologyType technology;
        for (int i = technologiesUnavailable.size() - 1; i >= 0; i--) {
            technology = technologiesUnavailable.get(i);
            isAvalable = true;
            for (TechnologyType requirement : technology.getRequirement()) {
                if (!technologiesResearched.contains(requirement)) {
                    isAvalable = false;
                    break;
                }
            }
            if (isAvalable) {
                technologiesAvailable.put(technology, technology.getCost());
                technologiesUnavailable.remove(technology);
            }
        }
    }

    public void startWorkingOnTechnology(TechnologyType newTechnology, int cost) {
        if (technologyCurrentlyResearching != null) {
            technologiesAvailable.put(technologyCurrentlyResearching, remainCost);
        }
        technologiesAvailable.remove(newTechnology);//checkBeshe
        technologyCurrentlyResearching = newTechnology;
        remainCost = cost;
        checkTechnologyCurrentlyResearching();
    }

    public void checkTechnologyCurrentlyResearching() {
        if (technologyCurrentlyResearching != null) {
            remainCost -= GameDataBase.getCurrentCivilization().getScience().getAdditionScience();
            if (remainCost <= 0) {
                technologiesResearched.add(technologyCurrentlyResearching);
                remainCost = 0;
                technologyCurrentlyResearching = null;
                GameDataBase.getCurrentCivilization().getScience().setAdditionScience(0);
                loookingForAvailable();
            }
        }
    }

    public ArrayList<TechnologyType> getTechnologiesResearched() {
        return technologiesResearched;
    }

    public void setTechnologiesResearched(ArrayList<TechnologyType> technologiesResearched) {
        this.technologiesResearched = technologiesResearched;
    }

    public TechnologyType getTechnologyCurrentlyResearching() {
        return technologyCurrentlyResearching;
    }

    public void setTechnologyCurrentlyResearching(TechnologyType technologyCurrentlyResearching) {
        this.technologyCurrentlyResearching = technologyCurrentlyResearching;
    }

    public HashMap<TechnologyType, Integer> getTechnologiesAvailable() {
        return technologiesAvailable;
    }

    public void setTechnologiesAvailable(HashMap<TechnologyType, Integer> technologiesAvailable) {
        this.technologiesAvailable = technologiesAvailable;
    }

    public ArrayList<TechnologyType> getTechnologiesUnavailable() {
        return technologiesUnavailable;
    }

    public void setTechnologiesUnavailable(ArrayList<TechnologyType> technologiesUnavailable) {
        this.technologiesUnavailable = technologiesUnavailable;
    }

    public Civilization getCivilization() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            if (civilization.getTechnologies() == this)
                return civilization;
        }
        return null;
    }
}
