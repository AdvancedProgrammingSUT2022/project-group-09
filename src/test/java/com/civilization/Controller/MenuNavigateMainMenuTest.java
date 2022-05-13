package com.civilization.Controller;

import java.util.regex.Matcher;

import com.civilization.MenuRegex.MainMenuRegex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class MenuNavigateMainMenuTest {

    @Test
    public void playGame() {
        MainMenuController mainMenuController = new MainMenuController();
        Matcher matcher = MainMenuRegex.getMatcher("menu enter Game menu", MainMenuRegex.ENTER);
        Assertions.assertEquals("use play game method", mainMenuController.menuNavigate(matcher));
    }

    @Test
    public void profileMenu() {
        MainMenuController mainMenuController = new MainMenuController();
        Matcher matcher = MainMenuRegex.getMatcher("menu enter Profile menu", MainMenuRegex.ENTER);
        Assertions.assertEquals("entered Profile menu", mainMenuController.menuNavigate(matcher));
    }

    @Test
    public void loginMenu() {
        MainMenuController mainMenuController = new MainMenuController();
        Matcher matcher = MainMenuRegex.getMatcher("menu enter Login menu", MainMenuRegex.ENTER);
        Assertions.assertEquals("use logout method", mainMenuController.menuNavigate(matcher));
    }

    @Test
    public void mainMenu() {
        MainMenuController mainMenuController = new MainMenuController();
        Matcher matcher = MainMenuRegex.getMatcher("menu enter Main menu", MainMenuRegex.ENTER);
        Assertions.assertEquals("menu name is not valid", mainMenuController.menuNavigate(matcher));
    }

    @Test
    public void logout() {
        MainMenuController mainMenuController = new MainMenuController();
        Assertions.assertEquals("user logged out successfully", mainMenuController.logout());
    }

}