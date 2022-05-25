package civilization.civilization.main.java.com.civilization.Model.Info;

import civilization.civilization.main.java.com.civilization.Controller.GameControllerPackage.GameDataBase;
import civilization.civilization.main.java.com.civilization.Model.Civilization;
import civilization.civilization.main.java.com.civilization.Model.TechnologyPackage.TechnologyType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        lookingForAvailable();
    }

    public void lookingForAvailable() {
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
                lookingForAvailable();
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

    public String technologyTree() {
        StringBuilder map = new StringBuilder();
        map.append("*Researched*\n");
        for (TechnologyType technologyResearched : GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesResearched()) {
            map.append(technologyResearched.getName()).append("\n");
        }
        map.append("\n*Available*\n");
        for (Map.Entry<TechnologyType, Integer> technologyAvailable : GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesAvailable().entrySet()) {
            map.append(technologyAvailable.getKey().getName()).append("\n");
        }
        map.append("\n*Unavailable*\n");
        for (TechnologyType technologyUnavailable : GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesUnavailable()) {
            map.append(technologyUnavailable.getName()).append(" - requirements:");
            for (TechnologyType technologyType : technologyUnavailable.getRequirement()) {
                if (technologyType != null)
                    map.append(" ").append(technologyType.getName());
                else
                    map.append(" null");
            }
            map.append("\n");
        }
        return String.valueOf(map);
    }

    public void setRemainCost(int remainCost) {
        this.remainCost = remainCost;
    }
}
