package Model;

import Model.Civilization;
import Model.Units.CivilianUnit;
import Model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class GameDataBase{
    private static User currentUser;
    private static Civilization currentCivilization;
    private static HashMap<User, Civilization> civilizations;
}
