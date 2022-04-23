package com.civilization;

import com.civilization.Controller.GameControllerPackage.GameDataBase;
import com.civilization.Controller.GameControllerPackage.GameMenuController;
import com.civilization.Controller.LoginMenuController;
import com.civilization.Controller.MainMenuController;
import com.civilization.Controller.ProfileMenuController;
import com.civilization.Controller.UserDatabase;
import com.civilization.Model.Coordination;
import com.civilization.Model.MainMap;
import com.civilization.Model.Map;
import com.civilization.Model.Terrains.Terrain;
import com.civilization.Model.Units.Settler;
import com.civilization.Model.User;
import com.civilization.View.*;

import java.lang.reflect.GenericArrayType;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        GameDataBase.runGameForFirstTime(new ArrayList<>(Collections.singleton(new User("s", "sd", "sdf"))));
        //   for (int i = 0; i < 24; i++)
        //       for (int j = 0; j < 24; j++)
        //           System.out.println(GameDataBase.getMainMap().showMap(i, j));

        Settler settler = (Settler) GameDataBase.getCivilizations().get(0).getUnits().get(0);
        Coordination coordination=settler.getTerrain().getCoordination();
        System.out.println(settler.getTerrain().getCoordination().toString());

       // settler.foundCaptalCity();
        GameDataBase.getCivilizations().get(0).getMap().updateExploration();
        System.out.println(GameDataBase.getCivilizations().get(0).getMap().showMap(coordination.getX()-1,coordination.getY()-1));

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
