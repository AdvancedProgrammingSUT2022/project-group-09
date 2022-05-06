package com.civilization.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Matcher;

import com.civilization.MenuRegex.LoginMenuRegex;
import com.civilization.Model.User;

public class LoginMenuControllerTest {
    @Mock
    UserDatabase userDatabase;

    @BeforeEach
    void loadUsers() {
        UserDatabase.loadUsers();
        User e = new User("username123", "password123", "nicknameHastam");
        UserDatabase.getUsers().add(e);
    }

    @Test
    void registerInvalidUsernameFormat() {
        LoginMenuController loginMenuController = new LoginMenuController();
        String input = "user create -u hello***0sad -n nicknameHastam -p password123";
        Matcher matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.CREATE1);
        assertEquals(loginMenuController.register(matcher), "username format is invalid");
    }

    @Test
    void registerInvalidNicknameForamt() {
        LoginMenuController loginMenuController = new LoginMenuController();
        String input = "user create -u username123 -n nicknameH*7923astam -p password123";
        Matcher matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.CREATE1);
        assertEquals(loginMenuController.register(matcher), "nickname format is invalid");
    }

    @Test
    void registerInvalidPasswordFormat() {
        LoginMenuController loginMenuController = new LoginMenuController();
        String input = "user create -u username123 -n nicknameHastam -p 8";
        Matcher matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.CREATE1);
        assertEquals(loginMenuController.register(matcher), "nickname format is invalid");
    }
    
}
