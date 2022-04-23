package com.civilization.Model.Info;

import java.util.ArrayList;
import java.util.HashMap;

import com.civilization.Model.TechnologyPackage.TechnologyType;

public class CivilizationTechnologies {
    private ArrayList<TechnologyType> technologiesResearched;
    private TechnologyType technologyCurrentlyResearching;
    private HashMap<TechnologyType,Integer> technologiesAvailable;
    private ArrayList<TechnologyType> technologiesUnavailable;

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
}
