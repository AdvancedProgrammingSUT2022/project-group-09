package com.civilization.Controller.GameControllerPackage;

import com.civilization.Model.Civilization;
import com.civilization.Model.Info.CivilizationTechnologies;
import com.civilization.Model.TechnologyPackage.TechnologyType;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;

public class TechnologyMenuController {


    public String showTechnologies() {
        StringBuilder technologies = new StringBuilder();
        technologies.append("*researched technologies*\n");
        for (TechnologyType technologyResearched : GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesResearched()) {
            technologies.append("Technology: ").append(technologyResearched.getName()).append("\n");
        }
        technologies.append("\n*available technologies*\n");
        for (Map.Entry<TechnologyType, Integer> technologyAvailable : GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesAvailable().entrySet()) {
            technologies.append("Technology: ").append(technologyAvailable.getKey().getName()).append("\t");
            technologies.append("Cost: ").append(technologyAvailable.getValue()).append("\t");
            technologies.append("Required techs:");
            for (TechnologyType technologyType : technologyAvailable.getKey().getRequirement()) {
                technologies.append(" ").append(technologyType.getName());
            }
            technologies.append("Leads to techs:");
            for (TechnologyType technologyType : technologyAvailable.getKey().getTechnologyUnlocks()) {
                technologies.append(" ").append(technologyType.getName());
            }
            technologies.append("\t");
            technologies.append("Unlocks:");
            for (Object unlocks : technologyAvailable.getKey().getUnlocks()) {
                technologies.append(" ").append(unlocks.toString().toLowerCase()); // TODO name
            }
            technologies.append("\n");
        }
        return String.valueOf(technologies);
    }

    public String technologyTree() {
        StringBuilder map = new StringBuilder();
        map.append("*Researched*\n");
        for (TechnologyType technologyResearched : GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesResearched()) {
            map.append(technologyResearched.getName()).append("\t");
        }
        map.append("\n*Available*\n");
        for (Map.Entry<TechnologyType, Integer> technologyAvailable : GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesAvailable().entrySet()) {
            map.append(technologyAvailable.getKey().getName()).append("\t");
        }
        map.append("\n*Unavailable*\n");
        for (TechnologyType technologyResearched : GameDataBase.getCurrentCivilization().getTechnologies().getTechnologiesUnavailable()) {
            map.append(technologyResearched.getName()).append("\t");
        }
        map.append("\n");
        return String.valueOf(map);
    }

    public TechnologyType findTechnologyByName(String name) {
        for (TechnologyType technology : TechnologyType.getAllTechnologies()) {
            if (technology.getName().equals(name)){
                return technology;
            }
        }
        return null;
    }

    public String chooseTechnology(Matcher matcher) {
        TechnologyType technology = findTechnologyByName(matcher.group("technology").toLowerCase());
        if (technology == null) {
            return "invalid technology name!";
        }
        CivilizationTechnologies civilizationTechnologies = GameDataBase.getCurrentCivilization().getTechnologies();
        if (civilizationTechnologies.getTechnologiesResearched().contains(technology)) {
            return "technology is already researched!";
        }
        if (civilizationTechnologies.getTechnologiesUnavailable().contains(technology)) {
            return "technology is not available!";
        }
        civilizationTechnologies.startWorkingOnTechnology(technology, civilizationTechnologies.getTechnologiesAvailable().get(technology));
        return "technology is researching!";
    }
}