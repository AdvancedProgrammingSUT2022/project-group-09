package com.civilization.Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Matcher;

import com.civilization.MenuRegex.LoginMenuRegex;
import com.civilization.Model.User;

public class MenuNavigateLoginMenuTest {
    @Test
    public void enterMainMenu() {
        LoginMenuController loginMenuController = new LoginMenuController();
        String input = "menu enter Main menu";
        Matcher matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.ENTER);
        UserDatabase.setCurrentUser(new User("", "", ""));
        assertEquals("entered MainMenu", loginMenuController.menuNavigate(matcher));
    }

    @Test
    public void noUser() {
        LoginMenuController loginMenuController = new LoginMenuController();
        String input = "menu enter Main menu";
        Matcher matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.ENTER);
        assertEquals("please login first", loginMenuController.menuNavigate(matcher));
    }

    @Test
    public void menuNavigationNotPossible() {
        LoginMenuController loginMenuController = new LoginMenuController();
        String input = "menu enter Game menu";
        Matcher matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.ENTER);
        assertEquals("menu navigation is not possible!", loginMenuController.menuNavigate(matcher));
    }

    @Test
    public void exit() {
        LoginMenuController loginMenuController = new LoginMenuController();
        assertEquals("Game Ended!", loginMenuController.exit());
    }
}
