package com.civilization.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Matcher;

import com.civilization.MenuRegex.ProfileMenuRegex;
import com.civilization.Model.User;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class ProfileChangeTest {

    @BeforeEach
    public void loadUsers() {
        User user1 = new User("username1", "password1", "nickname1");
        User user2 = new User("username2", "password2", "nickname2");
        UserDatabase.addUser(user1);
        UserDatabase.addUser(user2);
        UserDatabase.setCurrentUser(user1);
    }

    @AfterEach
    public void removeUsers() {
        UserDatabase.getUsers().clear();
        UserDatabase.setCurrentUser(null);
    }

    @Test
    public void duplicateNickname() {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        Matcher matcher = ProfileMenuRegex.getMatcher("profile change -n nickname2", ProfileMenuRegex.CHANGE_NICKNAME);
        assertEquals("user with nickname " + matcher.group("nickname") + " already exists", profileMenuController.changeNickname(matcher));
    }

    @Test
    public void invalidNickname() {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        Matcher matcher = ProfileMenuRegex.getMatcher("profile change -n ndaf*732@@$3", ProfileMenuRegex.CHANGE_NICKNAME);
        assertEquals("nickname format is invalid", profileMenuController.changeNickname(matcher));
    }

    @Test
    public void changeNickname() {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        Matcher matcher = ProfileMenuRegex.getMatcher("profile change -n nickname", ProfileMenuRegex.CHANGE_NICKNAME);
        assertEquals("nickname changed successfully", profileMenuController.changeNickname(matcher));
    }
}
