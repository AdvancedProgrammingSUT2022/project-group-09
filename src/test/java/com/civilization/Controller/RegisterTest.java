package com.civilization.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.regex.Matcher;

import com.civilization.MenuRegex.LoginMenuRegex;
import com.civilization.Model.User;

public class RegisterTest {

    @BeforeEach
    void loadUsers() {
        UserDatabase.loadUsers();
        User user = new User("username123", "*9adAdklduS", "nicknameHastam");
        UserDatabase.addUser(user);
    }

    @AfterEach
    void removeAdditionalUser() {
        UserDatabase.getUsers().remove(UserDatabase.getUsers().size() - 1);
        UserDatabase.saveUsers();
        UserDatabase.getUsers().clear();
    }

    @Test
    void registerInvalidUsernameFormat() {
        // userDatabase.when(() -> UserDatabase.getUsers()).thenReturn(new ArrayList<User>());
        LoginMenuController loginMenuController = new LoginMenuController();
        String input = "user create -u hello***0sad -n nicknameHastam -p password123";
        Matcher matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.CREATE1);
        assertEquals("username format is invalid", loginMenuController.register(matcher));
    }

    @Test
    void registerInvalidNicknameForamt() {
        LoginMenuController loginMenuController = new LoginMenuController();
        String input = "user create -u username123 -n nicknameH*7923astam -p password123";
        Matcher matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.CREATE1);
        assertEquals("nickname format is invalid", loginMenuController.register(matcher));
    }

    @Test
    void registerWeakPassword() {
        LoginMenuController loginMenuController = new LoginMenuController();
        String input = "user create -u username123 -n nicknameHastam -p 8";
        Matcher matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.CREATE1);
        assertEquals("password is weak", loginMenuController.register(matcher));
    }

    @Test
    void registerDuplicateUsername() {
        LoginMenuController loginMenuController = new LoginMenuController();
        String input = "user create -u username123 -n nicknameads -p *9adAdklduS";
        Matcher matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.CREATE1);
        assertEquals("user with username " + matcher.group("username") + " already exists",
                loginMenuController.register(matcher));
    }

    @Test
    void registerDuplicateNickname() {
        LoginMenuController loginMenuController = new LoginMenuController();
        String input = "user create -u username1234 -n nicknameHastam -p *9adAdklduS";
        Matcher matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.CREATE1);
        assertEquals("user nickname " + matcher.group("nickname") + " already exists",
                loginMenuController.register(matcher));
    }

    @Test
    void registerSuccessfull() {
        LoginMenuController loginMenuController = new LoginMenuController();
        String input = "user create -u asfsdjhfjadufa -n temporaryNickname -p *9adAdklduS";
        Matcher matcher = LoginMenuRegex.getMatcher(input, LoginMenuRegex.CREATE1);
        assertEquals("user created successfully!", loginMenuController.register(matcher));
        UserDatabase.getUsers().remove(UserDatabase.getUsers().size() - 1);
    }

}
