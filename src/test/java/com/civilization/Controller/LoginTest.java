package com.civilization.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.regex.Matcher;

import com.civilization.Model.User;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoginTest {

    private static MockedStatic<UserDatabase> userDatabase;

    @Mock
    User user;

    @Mock
    Matcher matcher;

    @BeforeAll
    public static void init() {
        userDatabase = Mockito.mockStatic(UserDatabase.class);
    }

    @AfterAll
    public static void close() {
        userDatabase.close();
    }

    @Test
    public void wrongUsername() {
        LoginMenuController loginMenuController = new LoginMenuController();
        userDatabase.when(() -> UserDatabase.isUsernameDuplicate(user)).thenReturn(false);
        when(matcher.group("username")).thenReturn("");
        when(matcher.group("password")).thenReturn("");

        assertEquals("Username and Password didn't match!", loginMenuController.login(matcher));
    }

    @Test
    public void wrongPassword() {
        LoginMenuController loginMenuController = new LoginMenuController();
        userDatabase.when(() -> UserDatabase.isUsernameDuplicate(user)).thenReturn(true);
        userDatabase.when(() -> UserDatabase.isUsernameAndPasswordTrue(user)).thenReturn(false);
        when(matcher.group("username")).thenReturn("");
        when(matcher.group("password")).thenReturn("");
        assertEquals("Username and Password didn't match!", loginMenuController.login(matcher));
    }

    @Test
    public void findBug() {
        LoginMenuController loginMenuController = new LoginMenuController();
        userDatabase.when(() -> UserDatabase.isUsernameDuplicate(user)).thenReturn(true);
        userDatabase.when(() -> UserDatabase.isUsernameAndPasswordTrue(user)).thenReturn(true);
        when(matcher.group("username")).thenReturn("");
        when(matcher.group("password")).thenReturn("");
        userDatabase.when(() -> UserDatabase.getUserFromUsers(user)).thenReturn(null);
        assertEquals("BUG!", loginMenuController.login(matcher));
    }

}
