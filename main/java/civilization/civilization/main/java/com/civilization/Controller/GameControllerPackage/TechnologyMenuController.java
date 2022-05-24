package civilization.civilization.main.java.com.civilization.Controller.GameControllerPackage;

import civilization.civilization.main.java.com.civilization.Model.Info.CivilizationTechnologies;
import civilization.civilization.main.java.com.civilization.Model.TechnologyPackage.TechnologyType;

import java.util.Map;
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
            technologies.append("- Cost: ").append(technologyAvailable.getValue()).append("    ");
            technologies.append("- Required techs:");
            for (TechnologyType technologyType : technologyAvailable.getKey().getRequirement()) {
                technologies.append(" ").append(technologyType.getName());
            }
            technologies.append("- Leads to techs:");
            for (TechnologyType technologyType : technologyAvailable.getKey().getTechnologyUnlocks()) {
                technologies.append(" ").append(technologyType.getName());
            }
            technologies.append("\t");
            technologies.append("- Unlocks:");
            for (Object unlocks : technologyAvailable.getKey().getUnlocks()) {
                technologies.append(" ").append(unlocks.toString().toLowerCase()); // TODO name
            }
            technologies.append("\n");
        }
        return String.valueOf(technologies);
    }

    public TechnologyType findTechnologyByName(String name) {
        for (TechnologyType technology : TechnologyType.getAllTechnologies()) {
            if (technology.getName().toLowerCase().equals(name)) {
                return technology;
            }
        }
        return null;
    }

    public String technologyTree() {
        return GameDataBase.getCurrentCivilization().getTechnologies().technologyTree();
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
        GameDataBase.getCurrentCivilization().updateNotification("started working on technology " + technology.getName());
        return "technology is researching!";
    }
}
