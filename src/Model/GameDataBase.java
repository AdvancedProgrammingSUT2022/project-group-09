package Model;

import java.util.HashMap;

public class GameDataBase{
    private static User currentUser;
    private static Civilization currentCivilization;
    private static HashMap<User, Civilization> civilizations;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        GameDataBase.currentUser = currentUser;
    }

    public static Civilization getCurrentCivilization() {
        return currentCivilization;
    }

    public static void setCurrentCivilization(Civilization currentCivilization) {
        GameDataBase.currentCivilization = currentCivilization;
    }

    public static HashMap<User, Civilization> getCivilizations() {
        return civilizations;
    }

    public static void setCivilizations(HashMap<User, Civilization> civilizations) {
        GameDataBase.civilizations = civilizations;
    }
}
