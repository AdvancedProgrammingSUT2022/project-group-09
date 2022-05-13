package com.civilization.Controller;

import java.util.regex.Matcher;

import com.civilization.MenuRegex.ProfileMenuRegex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class MenuNavigateAndExitProfileMenuTest {
    @Test
    public void mainMenu() {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        Matcher matcher = ProfileMenuRegex.getMatcher("menu enter Main menu", ProfileMenuRegex.ENTER);
        Assertions.assertEquals("entered Main Menu", profileMenuController.menuNavigate(matcher));
    }

    @Test
    public void notValid() {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        Matcher matcher = ProfileMenuRegex.getMatcher("menu enter Profile menu", ProfileMenuRegex.ENTER);
        Assertions.assertEquals("menu navigation is not possible", profileMenuController.menuNavigate(matcher));
    }
}