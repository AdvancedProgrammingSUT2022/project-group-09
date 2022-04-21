package com.civilization;

import com.civilization.Model.Map;

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
