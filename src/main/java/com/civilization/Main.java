package com.civilization;

import com.civilization.Controller.GameControllerPackage.CityController;
import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Controller.GameControllerPackage.GameMenuController;
import com.civilization.Controller.GameControllerPackage.UnitController;
import com.civilization.Controller.LoginMenuController;
import com.civilization.Controller.MainMenuController;
import com.civilization.Controller.ProfileMenuController;
import com.civilization.Controller.UserDatabase;
import com.civilization.MenuRegex.GameMenuRegex;
import com.civilization.Model.*;
import com.civilization.Model.Map;
import com.civilization.Model.TechnologyPackage.TechnologyType;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Units.Settler;
import com.civilization.Model.Units.UnitType;
import com.civilization.View.*;

import java.lang.reflect.GenericArrayType;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.RunnableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        UserDatabase.loadUsers();
        Scanner scanner = new Scanner(System.in);
        LoginMenuView loginMenu = new LoginMenuView(scanner, new LoginMenuController());
        MainMenuView mainMenu = new MainMenuView(scanner, new MainMenuController());
        ProfileMenuView profileMenu = new ProfileMenuView(scanner, new ProfileMenuController());
        GameMenuView gameMenu = new GameMenuView(scanner, new GameMenuController());
        while (CurrentMenu.get() != CurrentMenu.EndGame) {
            loginMenu.run();
            mainMenu.run();
            profileMenu.run();
            gameMenu.run();
        }
        UserDatabase.saveUsers();
    }
}
