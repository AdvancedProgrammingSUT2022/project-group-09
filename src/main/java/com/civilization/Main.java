package com.civilization;

import com.civilization.Controller.GameControllerPackage.GameMenuController;
import com.civilization.Model.ConsoleColors;
import com.civilization.Model.Map;
import com.civilization.Model.TerrainFeatures.TerrainFeature;
import com.civilization.Model.TerrainFeatures.TerrainFeatureType;
import com.civilization.Controller.LoginMenuController;
import com.civilization.Controller.MainMenuController;
import com.civilization.Controller.ProfileMenuController;
import com.civilization.Controller.UserDatabase;
import com.civilization.View.*;

import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // UserDatabase.loadUsers();
        // Scanner scanner = new Scanner(System.in);
        // LoginMenuView loginMenu = new LoginMenuView(scanner, new LoginMenuController());
        // MainMenuView mainMenu = new MainMenuView(scanner, new MainMenuController());
        // ProfileMenuView profileMenu = new ProfileMenuView(scanner, new ProfileMenuController());
        // GameMenuView gameMenu = new GameMenuView(scanner, new GameMenuController());
        // while (CurrentMenu.get() != CurrentMenu.EndGame) {
        //     loginMenu.run();
        //     mainMenu.run();
        //     profileMenu.run();
        //     gameMenu.run();
        // }
        // UserDatabase.saveUsers();
        Map map = new Map();
        System.out.print(map.showmap(10, 10));
    }
}
